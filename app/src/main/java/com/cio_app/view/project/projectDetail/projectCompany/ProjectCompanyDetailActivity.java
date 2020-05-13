package com.cio_app.view.project.projectDetail.projectCompany;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.cio_app.R;
import com.cio_app.view.project.projectDetail.projectCompany.projectContent.projectBase.ProjectBaseFragment;
import com.cio_app.view.project.projectDetail.projectCompany.projectContent.projectExample.ProjectExampleFragment;
import com.cio_app.view.project.projectDetail.projectCompany.projectContent.projectIntroduce.ProjectIntroduceFragment;
import com.e.lib_common_ui.base.BaseActivity;

public class ProjectCompanyDetailActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    private FragmentManager manager;
    private FragmentTransaction transaction;
    private RadioGroup radioGroup;

    public static void start(Context context) {
        Intent intent = new Intent(context, ProjectCompanyDetailActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_company_detail);

        initView();

        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        transaction.add(R.id.project_company_content_detail_layout, ProjectBaseFragment.newInstance());
        transaction.commit();
    }

    private void initView(){
        findViewById(R.id.btn_back_in_project_company_detail_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        findViewById(R.id.btn_back_in_project_company_detail).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        radioGroup = findViewById(R.id.project_company_content_detail_radioGroup_title);
        radioGroup.setOnCheckedChangeListener(this);

    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        transaction = manager.beginTransaction();
        switch (checkedId) {
            case R.id.project_company_content_detail_title_base:
                transaction.replace(R.id.project_company_content_detail_layout, ProjectBaseFragment.newInstance());
                break;
            case R.id.project_company_content_detail_title_introduce:
                transaction.replace(R.id.project_company_content_detail_layout, ProjectIntroduceFragment.newInstance());
                break;
            case R.id.project_company_content_detail_title_example:
                transaction.replace(R.id.project_company_content_detail_layout, ProjectExampleFragment.newInstance());
                break;
        }
        transaction.commit();
    }
}
