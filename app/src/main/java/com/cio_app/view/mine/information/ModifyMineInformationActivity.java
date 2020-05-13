package com.cio_app.view.mine.information;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.cio_app.R;
import com.e.lib_common_ui.base.BaseActivity;

public class ModifyMineInformationActivity extends BaseActivity {

    public static void start(Context context) {
        Intent intent = new Intent(context, ModifyMineInformationActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_mine_information);

        initView();
    }

    private void initView(){
        findViewById(R.id.btn_back_in_modify_information_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        findViewById(R.id.btn_back_in_modify_information_page).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
