package com.cio_app.view.mine.member.personApproveInformation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.cio_app.R;
import com.e.lib_common_ui.base.BaseActivity;

public class PersonApproveInformationActivity extends BaseActivity {

    public static void start(Context context) {
        Intent intent = new Intent(context, PersonApproveInformationActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_approve_information);
        initView();
    }

    private void initView(){
        findViewById(R.id.btn_back_in_approve_person_information_detail_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        findViewById(R.id.btn_back_in_approve_person_information_detail).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
