package com.cio_app.view.mine.demand.demandContent;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.cio_app.R;
import com.e.lib_common_ui.base.BaseActivity;

public class MineDemandDetailActivity extends BaseActivity {

    public static void start(Context context) {
        Intent intent = new Intent(context, MineDemandDetailActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_demand_detail);

        initView();
    }

    private void initView(){
        findViewById(R.id.btn_back_in_mine_demand_detail_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        findViewById(R.id.btn_back_in_mine_demand_detail).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }
}
