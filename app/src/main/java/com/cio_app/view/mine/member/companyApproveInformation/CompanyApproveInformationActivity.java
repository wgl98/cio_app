package com.cio_app.view.mine.member.companyApproveInformation;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.cio_app.R;
import com.e.lib_common_ui.base.BaseActivity;

public class CompanyApproveInformationActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener{

    private FragmentManager manager;
    private FragmentTransaction transaction;
    private RadioGroup radioGroup;

    public static void start(Context context) {
        Intent intent = new Intent(context, CompanyApproveInformationActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_approve_information);
        initView();

        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        transaction.add(R.id.approve_company_information_content_layout, CompanyMessageInformationFragment.newInstance());
        transaction.commit();
    }

    private void initView(){
        findViewById(R.id.btn_back_in_approve_company_information_detail_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        findViewById(R.id.btn_back_in_approve_company_information_detail).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        radioGroup = findViewById(R.id.approve_company_information_content_radioGroup_title);
        radioGroup.setOnCheckedChangeListener(this);

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        transaction = manager.beginTransaction();
        switch (checkedId){
            case R.id.approve_company_information_content_title_information:
                transaction.replace(R.id.approve_company_information_content_layout, CompanyMessageInformationFragment.newInstance());
                break;
            case R.id.approve_company_information_content_title_contact:
                transaction.replace(R.id.approve_company_information_content_layout, CompanyContactInformationFragment.newInstance());
                break;
            case R.id.approve_company_information_content_title_example:
                transaction.replace(R.id.approve_company_information_content_layout, CompanyExampleInformationFragment.newInstance());
                break;
            case R.id.approve_company_information_content_title_license:
                transaction.replace(R.id.approve_company_information_content_layout, CompanyLicenseInformationFragment.newInstance());
                break;
        }
        transaction.commit();
    }
}
