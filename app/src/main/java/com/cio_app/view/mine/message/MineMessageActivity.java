package com.cio_app.view.mine.message;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.cio_app.R;
import com.cio_app.model.MessageTag;
import com.cio_app.view.mine.message.detail.MessageDetailActivity;
import com.e.lib_common_ui.base.BaseActivity;

public class MineMessageActivity extends BaseActivity {

    public static void start(Context context) {
        Intent intent = new Intent(context, MineMessageActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_message);
        initView();
    }

    private void initView(){
        findViewById(R.id.btn_back_in_message_page_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        findViewById(R.id.btn_back_in_message_page).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        findViewById(R.id.resume_notice_tag_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MessageDetailActivity.start(MineMessageActivity.this, MessageTag.TAG_RESUME_ID);
            }
        });

        findViewById(R.id.recruit_notice_tag_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MessageDetailActivity.start(MineMessageActivity.this, MessageTag.TAG_RECRUIT_ID);
            }
        });

        findViewById(R.id.demand_notice_tag_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MessageDetailActivity.start(MineMessageActivity.this, MessageTag.TAG_DEMAND_ID);
            }
        });

        findViewById(R.id.activity_notice_tag_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MessageDetailActivity.start(MineMessageActivity.this, MessageTag.TAG_ACTIVITY_ID);
            }
        });

        findViewById(R.id.project_notice_tag_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MessageDetailActivity.start(MineMessageActivity.this, MessageTag.TAG_PROJECT_ID);
            }
        });
    }
}
