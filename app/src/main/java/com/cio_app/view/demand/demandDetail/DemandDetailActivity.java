package com.cio_app.view.demand.demandDetail;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.cio_app.R;
import com.cio_app.view.demand.demandDetail.demandContent.DemandContentFragment;
import com.cio_app.view.demand.demandDetail.demandResult.DemandResultFragment;
import com.e.lib_common_ui.base.BaseActivity;

public class DemandDetailActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener{

    private FragmentManager manager;
    private FragmentTransaction transaction;
    private RadioGroup radioGroup;

    public static void start(Context context) {
        Intent intent = new Intent(context, DemandDetailActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demand_detail);
        initView();

        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        transaction.add(R.id.show_demand_content_detail_layout, DemandContentFragment.newInstance());
        transaction.commit();
    }

    private void initView(){
        findViewById(R.id.btn_back_in_demand_detail_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        findViewById(R.id.btn_back_in_demand_detail).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        radioGroup = (RadioGroup) findViewById(R.id.demand_content_detail_radioGroup_title);
        radioGroup.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        transaction = manager.beginTransaction();
        switch (checkedId) {
            case R.id.demand_content_detail_title_content:
                transaction.replace(R.id.show_demand_content_detail_layout, DemandContentFragment.newInstance());
                break;
            case R.id.demand_content_detail_title_result:
                transaction.replace(R.id.show_demand_content_detail_layout, DemandResultFragment.newInstance());
                break;
        }
        transaction.commit();
    }
}
