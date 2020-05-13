package com.cio_app.view.mine.member.memberApproveStatus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.cio_app.R;
import com.e.lib_common_ui.base.BaseActivity;

public class MemberApproveStatusActivity extends BaseActivity {

    private FragmentTransaction transaction;
    private FragmentManager manager;

    public static void start(Context context) {
        Intent intent = new Intent(context, MemberApproveStatusActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_approve_status);

        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        transaction.add(R.id.member_approve_status_page_content,MemberApproveFailureFragment.newInstance());
        transaction.commit();
        initView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        initFragmentView();
    }

    private void initView() {
        findViewById(R.id.btn_back_in_member_approve_status_page_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        findViewById(R.id.btn_back_in_member_approve_status_page).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void initFragmentView(){

    }
}
