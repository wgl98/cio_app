package com.cio_app.view.mine.member;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.cio_app.R;
import com.cio_app.view.mine.member.companyMemberSubmit.SubmitCompanyInformationActivity;
import com.cio_app.view.mine.member.personMemberSubmit.SubmitPersonInformationActivity;
import com.e.lib_common_ui.base.BaseActivity;

public class SelectApproveTypeActivity extends BaseActivity {

    private RelativeLayout rl_company_member_approve;

    private RelativeLayout rl_person_member_approve;

    public static void start(Context context) {
        Intent intent = new Intent(context, SelectApproveTypeActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_approve_type);

        initView();
    }

    private void initView() {
        findViewById(R.id.btn_back_in_select_approve_type_page_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        findViewById(R.id.btn_back_in_select_approve_type_page).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        rl_company_member_approve = findViewById(R.id.tag_company_approve_type);
        rl_company_member_approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SubmitCompanyInformationActivity.start(SelectApproveTypeActivity.this);
            }
        });
        rl_person_member_approve = findViewById(R.id.tag_person_approve_type);
        rl_person_member_approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SubmitPersonInformationActivity.start(SelectApproveTypeActivity.this);
            }
        });
    }
}
