package com.cio_app.view.mine.activity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.cio_app.R;
import com.cio_app.view.mine.activity.acticityNoPass.ActivityNoPassFragment;
import com.cio_app.view.mine.activity.activityFinished.ActivityFinishedFragment;
import com.cio_app.view.mine.activity.activityHandling.ActivityHandleFragment;
import com.cio_app.view.mine.activity.activitySigning.ActivitySignFragment;
import com.e.lib_common_ui.base.BaseActivity;

public class MineActivityActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    private FragmentManager manager;

    private FragmentTransaction transaction;

    private RadioGroup radioGroup;

    public static void start(Context context) {
        Intent intent = new Intent(context, MineActivityActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_activity);

        initView();

        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        transaction.add(R.id.mine_activity_content_layout, ActivitySignFragment.newInstance());
        transaction.commit();
    }

    private void initView(){
        findViewById(R.id.btn_back_in_mine_activity_page_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        findViewById(R.id.btn_back_in_mine_activity_page).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        radioGroup = findViewById(R.id.mine_activity_radioGroup_title);
        radioGroup.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        transaction = manager.beginTransaction();
        switch (checkedId){
            case R.id.mine_activity_radioButton_signing:
                transaction.replace(R.id.mine_activity_content_layout,ActivitySignFragment.newInstance());
                break;
            case R.id.mine_activity_radioButton_handling:
                transaction.replace(R.id.mine_activity_content_layout, ActivityHandleFragment.newInstance());
                break;
            case R.id.mine_activity_radioButton_finish:
                transaction.replace(R.id.mine_activity_content_layout, ActivityFinishedFragment.newInstance());
                break;
            case R.id.mine_activity_radioButton_no_pass:
                transaction.replace(R.id.mine_activity_content_layout, ActivityNoPassFragment.newInstance());
                break;
        }
        transaction.commit();
    }
}
