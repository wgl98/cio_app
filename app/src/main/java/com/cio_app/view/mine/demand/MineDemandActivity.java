package com.cio_app.view.mine.demand;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.cio_app.R;
import com.cio_app.view.mine.demand.demandAll.DemandAllFragment;
import com.cio_app.view.mine.demand.demandFinish.DemandFinishFragment;
import com.cio_app.view.mine.demand.demandHandling.DemandHandleFragment;
import com.e.lib_common_ui.base.BaseActivity;

public class MineDemandActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener{

    private RadioGroup radioGroup;

    private FragmentManager manager;

    private FragmentTransaction transaction;

    public static void start(Context context) {
        Intent intent = new Intent(context, MineDemandActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_demand);

        initView();

        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        transaction.add(R.id.mine_demand_content_layout, DemandAllFragment.newInstance());
        transaction.commit();
    }

    private void initView(){
        findViewById(R.id.btn_back_in_mine_demand_page_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        findViewById(R.id.btn_back_in_mine_demand_page).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        radioGroup = findViewById(R.id.mine_demand_radioGroup_title);
        radioGroup.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        transaction = manager.beginTransaction();
        switch (checkedId){
            case R.id.mine_demand_radioButton_all:
                transaction.replace(R.id.mine_demand_content_layout,DemandAllFragment.newInstance());
                break;
            case R.id.mine_demand_radioButton_handling:
                transaction.replace(R.id.mine_demand_content_layout, DemandHandleFragment.newInstance());
                break;
            case R.id.mine_demand_radioButton_finish:
                transaction.replace(R.id.mine_demand_content_layout, DemandFinishFragment.newInstance());
                break;
        }
        transaction.commit();
    }
}
