package com.cio_app.view.mine.recruit;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.cio_app.R;
import com.cio_app.view.mine.recruit.recruitContent.RecruitHasPublishedFrament;
import com.cio_app.view.mine.recruit.recruitContent.RecruitNoPublishedFragment;
import com.cio_app.view.mine.recruit.recruitList.HasReceiveRecruitActivity;
import com.cio_app.view.talents.talentsSubmit.TalentsSubmitActivity;
import com.e.lib_common_ui.base.BaseActivity;

public class MineRecruitActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener,View.OnClickListener{

    private FragmentManager manager;

    private FragmentTransaction transaction;

    private RadioGroup radioGroup;

    private RelativeLayout rl_has_receive_recruit;

    private Button btn_add_recruit;

    public static void start(Context context) {
        Intent intent = new Intent(context, MineRecruitActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_recruit);

        initView();

        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        transaction.add(R.id.mine_recruit_content_layout, RecruitHasPublishedFrament.newInstance());
        transaction.commit();

    }

    private void initView(){
        findViewById(R.id.btn_back_in_mine_recruit_page_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        findViewById(R.id.btn_back_in_mine_recruit_page).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        radioGroup = findViewById(R.id.mine_recruit_radioGroup_title);
        radioGroup.setOnCheckedChangeListener(this);

        rl_has_receive_recruit = findViewById(R.id.mine_recruit_page_have_recruit_message_tag_layout);
        rl_has_receive_recruit.setOnClickListener(this);

        btn_add_recruit = findViewById(R.id.btn_add_recruit_in_mine_recruit_pag);
        btn_add_recruit.setOnClickListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        transaction = manager.beginTransaction();
        switch (checkedId){
            case R.id.mine_recruit_radioButton_has_published:
                transaction.replace(R.id.mine_recruit_content_layout, RecruitHasPublishedFrament.newInstance());
                break;
            case R.id.mine_recruit_radioButton_no_published:
                transaction.replace(R.id.mine_recruit_content_layout, RecruitNoPublishedFragment.newInstance());
                break;
        }
        transaction.commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_add_recruit_in_mine_recruit_pag:
                TalentsSubmitActivity.start(this);
                break;
            case R.id.mine_recruit_page_have_recruit_message_tag_layout:
                HasReceiveRecruitActivity.start(this);
                break;
        }
    }
}
