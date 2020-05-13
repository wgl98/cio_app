package com.cio_app.view.mine.message.detail;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.cio_app.R;
import com.cio_app.model.MessageTag;
import com.e.lib_common_ui.base.BaseActivity;

public class MessageDetailActivity extends BaseActivity {

    private TextView tv_page_title;   //标题

    private int type;  //消息的类型

    public static void start(Context context, int type) {
        Intent intent = new Intent(context, MessageDetailActivity.class);
        intent.putExtra("type",type);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_detail);
        type = getIntent().getIntExtra("type",0);

        initView();
    }

    private void initView(){
        findViewById(R.id.btn_back_in_message_detail_page_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        findViewById(R.id.btn_back_in_message_detail_page).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        tv_page_title = findViewById(R.id.message_detail_page_title_text);
        switch (type){
            case MessageTag.TAG_RESUME_ID:
                tv_page_title.setText("简历通知");
                findViewById(R.id.resume_item).setVisibility(View.VISIBLE);
                break;
            case MessageTag.TAG_RECRUIT_ID:
                tv_page_title.setText("招聘通知");
                findViewById(R.id.recruit_item).setVisibility(View.VISIBLE);
                break;
            case MessageTag.TAG_DEMAND_ID:
                tv_page_title.setText("需求通知");
                findViewById(R.id.demand_item).setVisibility(View.VISIBLE);
                break;
            case MessageTag.TAG_ACTIVITY_ID:
                tv_page_title.setText("活动通知");
                findViewById(R.id.activity_item).setVisibility(View.VISIBLE);
                break;
            case MessageTag.TAG_PROJECT_ID:
                tv_page_title.setText("方案通知");
                findViewById(R.id.project_item).setVisibility(View.VISIBLE);
                break;
        }
    }
}
