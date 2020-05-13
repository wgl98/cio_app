package com.cio_app.view.conference.conferenceContent;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.cio_app.R;
import com.cio_app.model.FragmentTag;
import com.cio_app.view.conference.conferenceContent.conferenceDetail.ConferenceDetailFragment;
import com.cio_app.view.conference.conferenceContent.conferenceIntroduce.ConferenceIntroduceFragment;
import com.e.lib_common_ui.base.BaseActivity;


public class ConferenceDetailActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    public final static int REGISTER = 1;

    private int type;  //会议的类型

    private FragmentManager manager;
    private FragmentTransaction transaction;
    private RadioGroup radioGroup;

    private RelativeLayout rl_conference_state;
    private TextView tv_conference_state;
    private RelativeLayout rl_conference_detail_bottom;
    private TextView tv_conference_detail_bottom;

    public static void start(Context context,int type) {
        Intent intent = new Intent(context, ConferenceDetailActivity.class);
        intent.putExtra("type",type);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conference_detail);
        type = getIntent().getIntExtra("type",0);
        initView();

        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        transaction.add(R.id.show_conference_content_detail_layout, ConferenceIntroduceFragment.newInstance());
        transaction.commit();
    }

    private void initView(){
        findViewById(R.id.btn_back_in_conference_detail_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        findViewById(R.id.btn_back_in_conference_detail).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        rl_conference_state = (RelativeLayout) findViewById(R.id.show_conference_state_in_detail);
        tv_conference_state = (TextView) findViewById(R.id.show_conference_state_text_in_detail);
        rl_conference_detail_bottom = (RelativeLayout) findViewById(R.id.conference_detail_bottom);
        tv_conference_detail_bottom = (TextView) findViewById(R.id.btn_in_conference_detail_bottom);

        //不同会议的状态加载不一样的布局
        switch (type){
            case FragmentTag.CONFERENCE_SIGN_ID:
                rl_conference_state.setBackground(getResources().getDrawable(R.mipmap.show_conference_sign_state));
                tv_conference_state.setText("报名中");
                tv_conference_detail_bottom.setText("我要报名");
                break;
            case FragmentTag.CONFERENCE_HANDLE_ID:
                rl_conference_state.setBackground(getResources().getDrawable(R.mipmap.show_conference_handle_state));
                tv_conference_state.setText("进行中");
                tv_conference_detail_bottom.setText("我要报名");
                break;
            case FragmentTag.CONFERENCE_FINISH_ID:
                rl_conference_state.setBackground(getResources().getDrawable(R.mipmap.show_conference_finish_state));
                tv_conference_state.setText("已结束");
                tv_conference_detail_bottom.setText("查看总结");
                break;
        }

        radioGroup = (RadioGroup) findViewById(R.id.conference_content_detail_title);
        radioGroup.setOnCheckedChangeListener(this);

        rl_conference_detail_bottom = (RelativeLayout) findViewById(R.id.conference_detail_bottom);
        rl_conference_detail_bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (type){
                    case FragmentTag.CONFERENCE_SIGN_ID:
                        dialog_sign_failed();
                        break;
                    case FragmentTag.CONFERENCE_HANDLE_ID:
                        dialog_sign_success();
                        break;
                    case FragmentTag.CONFERENCE_FINISH_ID:
                        break;
                }
            }
        });
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        transaction = manager.beginTransaction();
        switch (checkedId) {
            case R.id.conference_content_detail_title_introduce:
                transaction.replace(R.id.show_conference_content_detail_layout, ConferenceIntroduceFragment.newInstance());
                break;
            case R.id.conference_content_detail_title_detail:
                transaction.replace(R.id.show_conference_content_detail_layout, ConferenceDetailFragment.newInstance());
                break;
        }
        transaction.commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case REGISTER:
                if(resultCode == RESULT_OK){
                    rl_conference_detail_bottom.setVisibility(View.GONE);
                    Fragment current_fragment = (ConferenceIntroduceFragment)getSupportFragmentManager().findFragmentById(R.id.show_conference_content_detail_layout);
                    current_fragment.getView().findViewById(R.id.conference_content_detail_in_approve_state).setVisibility(View.VISIBLE);
                    dialog_sign_success();
                }
                break;
            default:
                break;
        }
    }

    private void dialog_sign_failed(){
        AlertDialog.Builder alterDiaglog = new AlertDialog.Builder(ConferenceDetailActivity.this);
        alterDiaglog.setView(R.layout.dialog_is_not_approve_layout);//加载进去
        final AlertDialog dialog = alterDiaglog.create();
        //显示
        dialog.show();
        //取消
        dialog.getWindow().findViewById(R.id.btn_negative_in_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        //前往
        dialog.getWindow().findViewById(R.id.btn_positive_in_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConferenceDetailActivity.this,ConferenceRegisterActivity.class);
                startActivityForResult(intent,REGISTER);
                dialog.dismiss();
            }
        });
    }

    private void dialog_sign_success(){
        AlertDialog.Builder alterDiaglog = new AlertDialog.Builder(ConferenceDetailActivity.this);
        alterDiaglog.setView(R.layout.dialog_is_sign_success_layout);//加载进去
        final AlertDialog dialog = alterDiaglog.create();
        //显示
        dialog.show();
        dialog.getWindow().findViewById(R.id.btn_positive_in_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

}
