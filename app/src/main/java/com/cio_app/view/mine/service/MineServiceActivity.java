package com.cio_app.view.mine.service;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import com.cio_app.R;
import com.cio_app.view.mine.service.serviceContent.ServiceHasPublishedFragment;
import com.cio_app.view.mine.service.serviceContent.ServiceNoPublishedFragment;
import com.cio_app.view.project.projectSubmit.projectSubmitPerson.ProjectPersonSubmitActivity;
import com.e.lib_common_ui.base.BaseActivity;

public class MineServiceActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener,View.OnClickListener{

    private FragmentManager manager;

    private FragmentTransaction transaction;

    private RadioGroup radioGroup;

    private Button btn_add_service;

    public static void start(Context context) {
        Intent intent = new Intent(context, MineServiceActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_service);
        initView();
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        transaction.add(R.id.mine_service_content_layout, ServiceHasPublishedFragment.newInstance());
        transaction.commit();
    }

    private void initView(){
        findViewById(R.id.btn_back_in_mine_service_page_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        findViewById(R.id.btn_back_in_mine_service_page).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        radioGroup = findViewById(R.id.mine_service_radioGroup_title);
        radioGroup.setOnCheckedChangeListener(this);

        btn_add_service = findViewById(R.id.btn_add_service_in_mine_service_pag);
        btn_add_service.setOnClickListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        transaction = manager.beginTransaction();
        switch (checkedId){
            case R.id.mine_service_radioButton_has_published:
                transaction.replace(R.id.mine_service_content_layout, ServiceHasPublishedFragment.newInstance());
                break;
            case R.id.mine_service_radioButton_no_published:
                transaction.replace(R.id.mine_service_content_layout, ServiceNoPublishedFragment.newInstance());
                break;
        }
        transaction.commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_add_service_in_mine_service_pag:
                ProjectPersonSubmitActivity.start(this);
                break;
        }
    }
}
