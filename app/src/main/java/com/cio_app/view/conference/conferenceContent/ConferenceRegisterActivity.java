package com.cio_app.view.conference.conferenceContent;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.cio_app.R;
import com.cio_app.api.RequestCenter;
import com.cio_app.model.conference.conferenceRegistered.ConferenceRegisteredModel;
import com.e.lib_common_ui.base.BaseActivity;
import com.lib_network.listener.DisposeDataListener;
import com.lib_network.request.RequestParams;

public class ConferenceRegisterActivity extends BaseActivity implements View.OnClickListener {

    private int id;

    private EditText et_name;
    private EditText et_workAddress;
    private EditText et_department;
    private EditText et_position;
    private EditText et_phone;
    private EditText et_email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conference_register);
        id = getIntent().getIntExtra("id",-1);
        initView();
    }

    private void initView(){
        findViewById(R.id.btn_back_in_conference_register_layout).setOnClickListener(this);
        findViewById(R.id.btn_back_in_conference_register).setOnClickListener(this);
        findViewById(R.id.conference_register_btn).setOnClickListener(this);

        et_name = (EditText) findViewById(R.id.conference_register_name);
        et_workAddress = (EditText) findViewById(R.id.conference_register_workAddress);
        et_department = (EditText) findViewById(R.id.conference_register_department);
        et_position = (EditText) findViewById(R.id.conference_register_position);
        et_phone = (EditText) findViewById(R.id.conference_register_phone);
        et_email = (EditText) findViewById(R.id.conference_register_email);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_back_in_conference_register:
            case R.id.btn_back_in_conference_register_layout:
                Intent intent = new Intent();
                setResult(RESULT_CANCELED,intent);
                finish();
                break;
            case R.id.conference_register_btn:
                postRegisterData();
                break;
            default:
                break;
        }
    }

    //提交报名信息
    private void postRegisterData(){
        RequestParams params = new RequestParams();
        params.put("name",et_name.getText().toString());
        params.put("phone",et_phone.getText().toString());
        params.put("company",et_workAddress.getText().toString());
        params.put("position",et_position.getText().toString());
        params.put("email",et_email.getText().toString());
        RequestCenter.postConferenceRegisterData(String.valueOf(id),params, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                ConferenceRegisteredModel value = (ConferenceRegisteredModel) responseObj;
                Intent intent = new Intent();
               if(value.code.equals("200")){
                   setResult(RESULT_OK,intent);
               }else {
                   setResult(RESULT_CANCELED,intent);
               }
               finish();
            }

            @Override
            public void onFailure(Object reasonObj) {
                Log.d("上传报名数据","失败");
                Intent intent = new Intent();
                setResult(RESULT_CANCELED,intent);
                finish();
            }
        });
    }
}
