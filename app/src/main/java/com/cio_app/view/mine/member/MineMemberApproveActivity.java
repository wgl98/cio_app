package com.cio_app.view.mine.member;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cio_app.R;
import com.cio_app.view.mine.member.companyApproveInformation.CompanyApproveInformationActivity;
import com.cio_app.view.mine.member.companyMemberSubmit.SubmitCompanyInformationActivity;
import com.e.lib_common_ui.base.BaseActivity;

public class MineMemberApproveActivity extends BaseActivity {

    private Button btn_member_approve;

    private TextView tv_member_move;

    public static void start(Context context) {
        Intent intent = new Intent(context, MineMemberApproveActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_memeber_approve);
        initView();
    }

    private void initView(){
        findViewById(R.id.btn_back_in_mine_member_approve_page_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        findViewById(R.id.btn_back_in_mine_member_approve_page).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        tv_member_move = findViewById(R.id.tv_member_move_in_mine_member_approve_page);
        String str = getString(R.string.tv_member_move);
        SpannableStringBuilder stringBuilder = new SpannableStringBuilder(str);
        TextViewSpan textViewSpan = new TextViewSpan();
        stringBuilder.setSpan(textViewSpan,20,26, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv_member_move.setText(stringBuilder);
        tv_member_move.setMovementMethod(LinkMovementMethod.getInstance());
        tv_member_move.setHighlightColor(getResources().getColor(android.R.color.transparent));

        btn_member_approve = findViewById(R.id.btn_approve_in_mine_member_approve_page);
        btn_member_approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               SelectApproveTypeActivity.start(MineMemberApproveActivity.this);
            }
        });

    }

    private class TextViewSpan extends ClickableSpan {
        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setColor(getResources().getColor(R.color.colorBlue));
            //设置是否有下划线
            ds.setUnderlineText(false);
        }
        @Override
        public void onClick(View widget) {
            MoveMemberApproveActivity.start(MineMemberApproveActivity.this);
        }
    }

}
