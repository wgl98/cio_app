package com.cio_app.view.mine.recruit.recruitContent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.cio_app.R;
import com.e.lib_common_ui.base.BaseActivity;

public class MineRecruitDetailActivity extends BaseActivity {

    public static void start(Context context) {
        Intent intent = new Intent(context, MineRecruitDetailActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_recruit_detail);
        initView();

    }

    private void initView(){
        findViewById(R.id.btn_back_in_mine_recruit_content_detail_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        findViewById(R.id.btn_back_in_mine_recruit_content_detail).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
