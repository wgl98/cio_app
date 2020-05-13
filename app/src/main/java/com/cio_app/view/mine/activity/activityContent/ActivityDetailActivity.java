package com.cio_app.view.mine.activity.activityContent;


import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.cio_app.R;
import com.cio_app.model.FragmentTag;
import com.cio_app.view.mine.activity.activityContent.activityBottom.FinishBottomFragment;
import com.cio_app.view.mine.activity.activityContent.activityBottom.HandleBottomFragment;
import com.cio_app.view.mine.activity.activityContent.activityBottom.SignBottomFragment;
import com.cio_app.view.mine.activity.activityContent.activityDetail.ActivityDetailFragment;
import com.cio_app.view.mine.activity.activityContent.activityIntroduce.ActivityIntroduceFragment;
import com.e.lib_common_ui.base.BaseActivity;

import java.util.Timer;
import java.util.TimerTask;

public class ActivityDetailActivity extends BaseActivity implements View.OnClickListener,RadioGroup.OnCheckedChangeListener{

    private int type;  //会议的类型

    private FragmentManager manager;
    private FragmentTransaction transaction;
    private RadioGroup radioGroup;

    private ActivityIntroduceFragment introduceFragment;
    private ActivityDetailFragment detailFragment;

    private SignBottomFragment signBottomFragment;
    private FinishBottomFragment finishBottomFragment;
    private HandleBottomFragment handleBottomFragment;

    private Button btn_questionare;
    private Button btn_sign;

    private LinearLayout ll_activity_detail_bottom;

    private TextView tv_no_pass_state;

    private RelativeLayout rl_activity_state;
    private TextView tv_activity_state;


    public static void start(Context context, int type) {
        Intent intent = new Intent(context, ActivityDetailActivity.class);
        intent.putExtra("type",type);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        type = getIntent().getIntExtra("type",0);

        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        introduceFragment = new ActivityIntroduceFragment();
        detailFragment = new ActivityDetailFragment();
        signBottomFragment = new SignBottomFragment();
        finishBottomFragment = new FinishBottomFragment();
        handleBottomFragment = new HandleBottomFragment();
        transaction.add(R.id.show_activity_content_detail_layout, introduceFragment);
        transaction.add(R.id.show_activity_content_detail_layout,detailFragment);
        transaction.add(R.id.activity_detail_content_bottom,signBottomFragment);
        transaction.add(R.id.activity_detail_content_bottom,finishBottomFragment);
        transaction.add(R.id.activity_detail_content_bottom,handleBottomFragment);
        transaction.hide(signBottomFragment);
        transaction.hide(finishBottomFragment);
        transaction.hide(handleBottomFragment);
        transaction.hide(detailFragment);
        transaction.show(introduceFragment);
        transaction.commit();

        initView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        initFragmentView();
    }

    private void initView() {
        findViewById(R.id.btn_back_in_activity_detail_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        findViewById(R.id.btn_back_in_activity_detail).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        ll_activity_detail_bottom = (LinearLayout) findViewById(R.id.activity_detail_content_bottom);

        rl_activity_state = (RelativeLayout) findViewById(R.id.show_activity_state_in_detail);
        tv_activity_state = (TextView) findViewById(R.id.show_activity_state_text_in_detail);
        //rl_activity_detail_bottom = (RelativeLayout) findViewById(R.id.activity_detail_bottom);
        //tv_activity_detail_bottom = (TextView) findViewById(R.id.btn_in_activity_detail_bottom);

        //不同会议的状态加载不一样的布局
        switch (type){

            case FragmentTag.CONFERENCE_SIGN_ID:
                rl_activity_state.setBackground(getResources().getDrawable(R.mipmap.show_conference_sign_state));
                tv_activity_state.setText("报名中");
                break;
            case FragmentTag.CONFERENCE_HANDLE_ID:
                rl_activity_state.setBackground(getResources().getDrawable(R.mipmap.show_conference_handle_state));
                tv_activity_state.setText("进行中");
                break;
            case FragmentTag.CONFERENCE_FINISH_ID:
                rl_activity_state.setBackground(getResources().getDrawable(R.mipmap.show_conference_finish_state));
                tv_activity_state.setText("已结束");
                break;
            case FragmentTag.CONFERENCE_NO_PASS_ID:
                rl_activity_state.setBackground(getResources().getDrawable(R.mipmap.show_conference_sign_state));
                tv_activity_state.setText("报名中");
                break;

        }

        radioGroup = (RadioGroup) findViewById(R.id.activity_content_detail_title);
        radioGroup.setOnCheckedChangeListener(this);

    }

    private void initFragmentView(){
        transaction = manager.beginTransaction();
        View view = introduceFragment.getView();
        tv_no_pass_state = view.findViewById(R.id.activity_content_detail_in_approve_state);
        switch (type){
            case FragmentTag.CONFERENCE_SIGN_ID:
                tv_no_pass_state.setVisibility(View.VISIBLE);
                tv_no_pass_state.setText("报名审核中");
                tv_no_pass_state.setBackgroundColor(getResources().getColor(R.color.colorYellow));
                ll_activity_detail_bottom.setVisibility(View.GONE);
                break;
            case FragmentTag.CONFERENCE_HANDLE_ID:
                transaction.show(handleBottomFragment);
                break;
            case FragmentTag.CONFERENCE_FINISH_ID:
                transaction.show(finishBottomFragment);
                break;
            case FragmentTag.CONFERENCE_NO_PASS_ID:
                tv_no_pass_state.setVisibility(View.VISIBLE);
                tv_no_pass_state.setText("报名不通过");
                tv_no_pass_state.setBackgroundColor(getResources().getColor(R.color.colorRed_));
                ll_activity_detail_bottom.setVisibility(View.GONE);
                break;
        }

        View handle_bottom_view = handleBottomFragment.getView();
        btn_questionare =  handle_bottom_view.findViewById(R.id.btn_in_activity_detail_submit_questionnaire);
        btn_questionare.setOnClickListener(this);
        btn_sign = handle_bottom_view.findViewById(R.id.btn_in_activity_detail_submit_sign);
        btn_sign.setOnClickListener(this);

        View finish_bottom_view = finishBottomFragment.getView();
        finish_bottom_view.findViewById(R.id.btn_in_activity_detail_view_summary).setOnClickListener(this);

        transaction.commit();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        transaction = manager.beginTransaction();
        switch (checkedId) {
            case R.id.activity_content_detail_title_introduce:
               transaction.hide(detailFragment);
               transaction.show(introduceFragment);
                break;
            case R.id.activity_content_detail_title_detail:
                transaction.hide(introduceFragment);
                transaction.show(detailFragment);
                break;
        }
        transaction.commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_in_activity_detail_submit_questionnaire:
                dialog_submit_questionare_success();
                btn_questionare.setEnabled(false);
                btn_questionare.setText("已填写调查问卷");
                btn_questionare.setTextColor(getResources().getColor(R.color.colorBlack));
                btn_questionare.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                break;
            case R.id.btn_in_activity_detail_submit_sign:
                dialog_register_success();
                btn_sign.setEnabled(false);
                btn_sign.setText("已签到");
                btn_sign.setTextColor(getResources().getColor(R.color.colorBlack));
                btn_sign.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                break;
            case R.id.btn_in_activity_detail_view_summary:
                break;
        }
    }

    private void dialog_submit_questionare_success(){
        AlertDialog.Builder alterDiaglog = new AlertDialog.Builder(this,R.style.DialogStyle);
        alterDiaglog.setView(R.layout.dialog_is_submit_questionare_success);//加载进去
        final AlertDialog dialog = alterDiaglog.create();
        //显示
        dialog.show();
        WindowManager windowManager = getWindowManager();
        Point point = new Point();
        windowManager.getDefaultDisplay().getSize(point);
        int height = point.y;
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.height = (int) (height * 0.5); // 高度设置为屏幕的0.5 
        dialog.getWindow().setAttributes(params);
        final Timer t = new Timer();
        t.schedule(new TimerTask() {
            public void run() {
                dialog.dismiss();
                t.cancel();
                //finish();
            }
        }, 3000);
    }

    private void dialog_register_success(){
        AlertDialog.Builder alterDiaglog = new AlertDialog.Builder(this,R.style.DialogStyle);
        alterDiaglog.setView(R.layout.dialog_is_register_success_layout);//加载进去
        final AlertDialog dialog = alterDiaglog.create();
        //显示
        dialog.show();
        WindowManager windowManager = getWindowManager();
        Point point = new Point();
        windowManager.getDefaultDisplay().getSize(point);
        int height = point.y;
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.height = (int) (height * 0.5); // 高度设置为屏幕的0.5 
        dialog.getWindow().setAttributes(params);
        final Timer t = new Timer();
        t.schedule(new TimerTask() {
            public void run() {
                dialog.dismiss();
                t.cancel();
                //finish();
            }
        }, 3000);
    }
}
