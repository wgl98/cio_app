package com.cio_app.view.mine;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.cio_app.R;
import com.cio_app.view.mine.activity.MineActivityActivity;
import com.cio_app.view.mine.demand.MineDemandActivity;
import com.cio_app.view.mine.information.ModifyMineInformationActivity;
import com.cio_app.view.mine.member.MineMemberApproveActivity;
import com.cio_app.view.mine.member.memberApproveStatus.MemberApproveStatusActivity;
import com.cio_app.view.mine.message.MineMessageActivity;
import com.cio_app.view.mine.recruit.MineRecruitActivity;
import com.cio_app.view.mine.resume.MineResumeActivity;
import com.cio_app.view.mine.service.MineServiceActivity;
import com.e.lib_common_ui.base.BaseActivity;

public class MineActivity extends BaseActivity {

    public static void start(Context context) {
        Intent intent = new Intent(context, MineActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);

        initView();
    }

    private void initView(){
        findViewById(R.id.btn_back_in_mine_page).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        findViewById(R.id.btn_back_in_mine_page_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        findViewById(R.id.mine_page_content_approve_tag_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MineMemberApproveActivity.start(MineActivity.this);
            }
        });

        findViewById(R.id.to_modify_mine_information).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModifyMineInformationActivity.start(MineActivity.this);
            }
        });

        findViewById(R.id.mine_page_content_message_tag_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MineMessageActivity.start(MineActivity.this);
            }
        });

        findViewById(R.id.mine_page_content_activity_tag_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MineActivityActivity.start(MineActivity.this);
            }
        });
        findViewById(R.id.mine_page_content_demand_tag_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MineDemandActivity.start(MineActivity.this);
            }
        });

        findViewById(R.id.mine_page_content_resume_tag_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MineResumeActivity.start(MineActivity.this);
            }
        });
        findViewById(R.id.mine_page_content_recruit_tag_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MineRecruitActivity.start(MineActivity.this);
            }
        });
        findViewById(R.id.mine_page_content_service_tag_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MineServiceActivity.start(MineActivity.this);
            }
        });
    }
}
