package com.cio_app.view.talents.talentsDetail.talentsMarket;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.cio_app.R;
import com.e.lib_common_ui.base.BaseActivity;

public class TalentsMarketDetailActivity extends BaseActivity {

    public static void start(Context context) {
        Intent intent = new Intent(context, TalentsMarketDetailActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talents_market_detail);
        initView();
    }

    private void initView(){
        findViewById(R.id.btn_back_in_talents_market_content_detail_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        findViewById(R.id.btn_back_in_talents_market_content_detail).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
