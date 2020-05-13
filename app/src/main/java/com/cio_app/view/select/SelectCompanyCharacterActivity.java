package com.cio_app.view.select;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.cio_app.R;
import com.cio_app.view.select.adapter.SelectAdapter;
import com.e.lib_common_ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class SelectCompanyCharacterActivity extends BaseActivity implements View.OnClickListener {

    private ListView listView;
    private SelectAdapter mAdapter;
    private List<String> list = new ArrayList<>();
    private int pos = -1;
    private String firstFecture;
    private String secondFecture;
    private List<String> fecture_list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_company_character);
        initData();
        initView();
    }
    private void initView(){
        findViewById(R.id.btn_back_in_select_company_character_layout).setOnClickListener(this);
        findViewById(R.id.btn_back_in_select_company_character).setOnClickListener(this);
        listView = findViewById(R.id.list_company_character);
        mAdapter = new SelectAdapter(this,R.layout.list_item_select_company_type_layout,list);
        mAdapter.setOnClickListener(new SelectAdapter.onClickListener() {
            @Override
            public void onClick(int position, String str) {
                Log.d("内容",str);
                fecture_list.add(str);
            }
        });
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                fecture_list.clear();
                mAdapter.changeState(position);
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_back_in_select_company_character_layout:
            case R.id.btn_back_in_select_company_character:
                Intent intent = new Intent();
                if(fecture_list.size() == 0){
                    intent.putExtra("first","");
                    intent.putExtra("second","");
                }else if(fecture_list.size() == 1){
                    intent.putExtra("first",fecture_list.get(0));
                    intent.putExtra("second","");
                }else {
                    intent.putExtra("first",fecture_list.get(0));
                    intent.putExtra("second",fecture_list.get(1));
                }
                setResult(RESULT_OK,intent);
                finish();
                break;
        }
    }

    private void initData(){
        list.add("软件工程师");
        list.add("系统集成工程师");
        list.add("系统工程师");
        list.add("系统架构设计师");
        list.add("数据库工程师");
        list.add("PHP\\Jave\\C语言\\.NET\\Python开发工程师");
        list.add("软件UI设计师");
        list.add("互联网软件开发工程师");
        list.add("语音\\视频\\图形开发工程师");
        list.add("多媒体\\游戏开发工程师");
        list.add("移动开发工程师");
        list.add("Web前端开发");
        list.add("网络信息安全工程师");
        list.add("首席技术执行官CTO\\首席信息官CIO");
        list.add("信息技术经理/主管");
        list.add("项目总监\\经理\\主管");
        list.add("技术支持\\维护经理");
        list.add("技术支持\\维护工程师");
        list.add("电子工程师\\技术员");
        list.add("集成电路IC设计\\应用工程师");
        list.add("嵌入式软件开发");
        list.add("自动控制工程师\\技术员");

    }
}
