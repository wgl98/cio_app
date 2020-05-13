package com.cio_app.view.conference.conferenceContent;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.cio_app.R;
import com.e.lib_common_ui.base.BaseActivity;

public class ConferenceRegisterActivity extends BaseActivity implements View.OnClickListener {

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
                Intent intent1 = new Intent();
                intent1.putExtra("name",et_name.getText());
                intent1.putExtra("workAddress",et_workAddress.getText());
                intent1.putExtra("department",et_department.getText());
                intent1.putExtra("position",et_position.getText());
                intent1.putExtra("phone",et_phone.getText());
                intent1.putExtra("email",et_email.getText());
                setResult(RESULT_OK,intent1);
                finish();
                break;
            default:
                break;
        }
    }
}
