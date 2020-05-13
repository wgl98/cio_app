package com.cio_app.view.select.selectCompanyType;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.cio_app.R;
import com.cio_app.view.select.adapter.SelectTypeAdapter;
import com.e.lib_common_ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class SelectCompanyTypeActivity extends BaseActivity implements View.OnClickListener{

    private FragmentManager manager;
    private FragmentTransaction transaction;

    private SelectCompanyTypeFirstFragment firstFragment;
    private SelectCompanyTypeSecondFragment secondFragment;
    private SelectCompanyTypeThirdFragment thirdFragment;

    private ListView firstListView;
    private ListView secondListView;
    private ListView thirdListView;

    private TextView tv_back_to_first;
    private TextView tv_back_to_second;

    private SelectTypeAdapter firstAdapter;
    private SelectTypeAdapter secondAdapter;
    private SelectTypeAdapter thirdAdapter;

    private List<String> firstList = new ArrayList<>();
    private List<String> secondList = new ArrayList<>();
    private List<String> thirdList = new ArrayList<>();

    private String str_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_company_type);

        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        firstFragment = new SelectCompanyTypeFirstFragment();
        secondFragment = new SelectCompanyTypeSecondFragment();
        thirdFragment = new SelectCompanyTypeThirdFragment();
        transaction.add(R.id.select_company_type_content_layout,firstFragment);
        transaction.add(R.id.select_company_type_content_layout,secondFragment);
        transaction.add(R.id.select_company_type_content_layout,thirdFragment);
        transaction.hide(secondFragment);
        transaction.hide(thirdFragment);
        transaction.show(firstFragment);
        transaction.commit();
        initFirstData();
        initSecondData();
        initThirdData();
        initView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        initFragmentView();
    }

    private void initView(){
        findViewById(R.id.btn_back_in_select_company_type_layout).setOnClickListener(this);
        findViewById(R.id.btn_back_in_select_company_type).setOnClickListener(this);
    }

    private void initFragmentView(){
        View firstView = firstFragment.getView();
        firstListView = firstView.findViewById(R.id.list_company_first_type);
        firstAdapter = new SelectTypeAdapter(this,R.layout.list_item_select_company_type_layout, firstList);
        firstAdapter.setOnClickListener(new SelectTypeAdapter.onClickListener() {
            @Override
            public void onClick(int position, String str) {
                transaction = manager.beginTransaction();
                transaction.hide(firstFragment);
                transaction.hide(thirdFragment);
                transaction.show(secondFragment);
                transaction.commit();
            }
        });
        firstListView.setAdapter(firstAdapter);
        firstListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                firstAdapter.changeState(position);
            }
        });

        View secondView = secondFragment.getView();
        secondListView = secondView.findViewById(R.id.list_company_second_type);
        secondAdapter = new SelectTypeAdapter(this,R.layout.list_item_select_company_type_layout,secondList);
        secondAdapter.setOnClickListener(new SelectTypeAdapter.onClickListener() {
            @Override
            public void onClick(int position, String str) {
                transaction = manager.beginTransaction();
                transaction.hide(firstFragment);
                transaction.hide(secondFragment);
                transaction.show(thirdFragment);
                transaction.commit();
            }
        });
        secondListView.setAdapter(secondAdapter);
        secondListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                secondAdapter.changeState(position);
            }
        });

        tv_back_to_first = secondView.findViewById(R.id.btn_in_select_company_type_second_fragment);
        tv_back_to_first.setOnClickListener(this);

        View thirdView = thirdFragment.getView();
        thirdListView = thirdView.findViewById(R.id.list_company_third_type);
        thirdAdapter = new SelectTypeAdapter(this,R.layout.list_item_select_company_type_layout,thirdList);
        thirdAdapter.setOnClickListener(new SelectTypeAdapter.onClickListener() {
            @Override
            public void onClick(int position, String str) {
               str_type = str;
            }
        });
        thirdListView.setAdapter(thirdAdapter);
        thirdListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                thirdAdapter.changeState(position);
            }
        });

        tv_back_to_second = thirdView.findViewById(R.id.btn_in_select_company_type_third_fragment);
        tv_back_to_second.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_back_in_select_company_type_layout:
            case R.id.btn_back_in_select_company_type:
                Intent intent = new Intent();
                intent.putExtra("type",str_type);
                setResult(RESULT_OK,intent);
                finish();
                break;
            case R.id.btn_in_select_company_type_second_fragment:
                transaction = manager.beginTransaction();
                transaction.hide(thirdFragment);
                transaction.hide(secondFragment);
                transaction.show(firstFragment);
                transaction.commit();
                break;
            case R.id.btn_in_select_company_type_third_fragment:
                transaction = manager.beginTransaction();
                transaction.hide(firstFragment);
                transaction.hide(thirdFragment);
                transaction.show(secondFragment);
                transaction.commit();
                break;

        }
    }


    private void initFirstData(){
        firstList.add("软硬件行业");
        firstList.add("非软硬件行业");
    }

    private void initSecondData(){
        secondList.add("软件类");
        secondList.add("网络硬件类");
        secondList.add("第三方服务");
    }

    private void initThirdData(){
        thirdList.add("ERP");
        thirdList.add("OA/HR");
        thirdList.add("MES/WMS");
        thirdList.add("BI/大数据管理与分析 ");
        thirdList.add("CRM/SCM");
        thirdList.add("PLM");
        thirdList.add("CAD");
        thirdList.add("网站开发/运维/推广");

    }
}
