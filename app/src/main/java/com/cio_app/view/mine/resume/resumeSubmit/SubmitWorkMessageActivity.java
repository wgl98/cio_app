package com.cio_app.view.mine.resume.resumeSubmit;


import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.cio_app.R;
import com.cio_app.model.ListData;
import com.cio_app.view.select.SelectCompanyCharacterActivity;
import com.e.lib_common_ui.base.BaseActivity;
import com.lib_pickerview.Interface.OnItemClickListener;
import com.lib_pickerview.style.pickerview.PickerView;
import com.lvfq.pickerview.TimePickerView;
import com.lvfq.pickerview.utils.Util;

public class SubmitWorkMessageActivity extends BaseActivity implements View.OnClickListener{

    public static final int TYPE = 1;

    private ListData listData = new ListData();
    private PickerView company_character_pickerView = new PickerView();
    private PickerView company_business_pickerView = new PickerView();

    private RelativeLayout rl_select_beginTime;          //选择开始时间
    private TextView tv_beginTime;

    private RelativeLayout rl_select_finishTime;         //选择结束时间
    private TextView tv_finishTime;

    private EditText et_name;                            //公司名称

    private RelativeLayout rl_select_company_character;  //公司性质
    private TextView tv_company_character;

    private RelativeLayout rl_select_company_business;   //所属行业
    private TextView tv_company_business;

    private RelativeLayout rl_select_company_function;   //职能
    private TextView tv_company_function;

    private EditText et_company_department;              //所属部门
    private EditText et_company_position;                //职位
    private EditText et_company_description;             //描述

    private Button btn_submit;

    private boolean tv_beginTime_is_null = true;
    private boolean tv_finishTime_is_null = true;
    private boolean et_name_is_null = true;
    private boolean tv_company_character_is_null = true;
    private boolean tv_company_business_is_null = true;
    private boolean tv_company_function_is_null = true;
    private boolean et_company_department_is_null = true;
    private boolean et_company_position_is_null = true;
    private boolean et_company_description_is_null = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_work_message);
        company_character_pickerView.init(this,listData.getCompanyCharacterData());
        company_business_pickerView.init(this,listData.getCompanyBusinessData());
        initView();
    }

    private void initView(){
        findViewById(R.id.btn_back_in_submit_work_message_page_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        findViewById(R.id.btn_back_in_submit_work_message_page).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        rl_select_beginTime = findViewById(R.id.submit_work_message_page_content_beginTime_layout);
        rl_select_beginTime.setOnClickListener(this);

        tv_beginTime = findViewById(R.id.submit_work_message_page_content_beginTime);

        rl_select_finishTime = findViewById(R.id.submit_work_message_page_content_finishTime_layout);
        rl_select_finishTime.setOnClickListener(this);

        tv_finishTime = findViewById(R.id.submit_work_message_page_content_finishTime);

        et_name = findViewById(R.id.submit_work_message_page_content_company);
        et_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length() != 0){
                    et_name_is_null = false;
                }else {
                    et_name_is_null = true;
                }
                BtnToSave();
            }
        });

        rl_select_company_character = findViewById(R.id.submit_work_message_page_content_company_character_layout);
        rl_select_company_character.setOnClickListener(this);

        tv_company_character = findViewById(R.id.submit_work_message_page_content_company_character);

        rl_select_company_business = findViewById(R.id.submit_work_message_page_content_company_business_layout);
        rl_select_company_business.setOnClickListener(this);

        tv_company_business = findViewById(R.id.submit_work_message_page_content_company_business);

        rl_select_company_function = findViewById(R.id.submit_work_message_page_content_company_function_layout);
        rl_select_company_function.setOnClickListener(this);

        tv_company_function = findViewById(R.id.submit_work_message_page_content_company_function);

        et_company_department = findViewById(R.id.submit_work_message_page_content_company_department);
        et_company_department.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length() != 0){
                    et_company_department_is_null = false;
                }else {
                    et_company_department_is_null = true;
                }
                BtnToSave();
            }
        });

        et_company_position = findViewById(R.id.submit_work_message_page_content_company_position);
        et_company_position.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length() != 0){
                    et_company_position_is_null = false;
                }else {
                    et_company_position_is_null = true;
                }
                BtnToSave();
            }
        });

        et_company_description = findViewById(R.id.submit_work_message_page_content_company_description);
        et_company_description.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length() != 0){
                    et_company_description_is_null = false;
                }else {
                    et_company_description_is_null = true;
                }
                BtnToSave();
            }
        });

        btn_submit = findViewById(R.id.btn_submit_work_message);
        btn_submit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        TimePickerView.Type type = null;
        String format = "";
        switch (v.getId()){
            case R.id.submit_work_message_page_content_beginTime_layout:
                type = TimePickerView.Type.YEAR_MONTH;
                format = "yyyy/MM";
                Util.alertTimerPicker(this, type, format, new Util.TimerPickerCallBack() {
                    @Override
                    public void onTimeSelect(String date) {
                        tv_beginTime.setText(date);
                        tv_beginTime_is_null = false;
                    }
                });
                BtnToSave();
                break;
            case R.id.submit_work_message_page_content_finishTime_layout:
                type = TimePickerView.Type.YEAR_MONTH;
                format = "yyyy/MM";
                Util.alertTimerPicker(this, type, format, new Util.TimerPickerCallBack() {
                    @Override
                    public void onTimeSelect(String date) {
                        tv_finishTime.setText(date);
                        tv_finishTime_is_null = false;
                    }
                });
                BtnToSave();
                break;
            case R.id.submit_work_message_page_content_company_character_layout:
                initComapnyCharacterPickerView();
                break;
            case R.id.submit_work_message_page_content_company_business_layout:
                initCompanyBusinessPickerView();
                break;
            case R.id.submit_work_message_page_content_company_function_layout:
                Intent intent1 = new Intent(this, SelectCompanyCharacterActivity.class);
                startActivityForResult(intent1,TYPE);
                break;
            case R.id.btn_submit_work_message:
                Intent intent = new Intent();
                intent.putExtra("beginTime",tv_beginTime.getText());
                intent.putExtra("finishTime",tv_finishTime.getText());
                intent.putExtra("name",et_name.getText().toString());
                intent.putExtra("character",tv_company_character.getText());
                intent.putExtra("business",tv_company_business.getText());
                intent.putExtra("function",tv_company_function.getText());
                intent.putExtra("department",et_company_department.getText().toString());
                intent.putExtra("position",et_company_position.getText().toString());
                intent.putExtra("description",et_company_description.getText().toString());
                setResult(RESULT_OK,intent);
                finish();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case TYPE:
                if(resultCode == RESULT_OK){
                    String first = data.getStringExtra("first");
                    String second = data.getStringExtra("second");
                    if(first.equals("") && second.equals("")){
                        tv_company_function_is_null = true;
                        tv_company_function.setText("");
                    }else if(second.equals("")) {
                        tv_company_function_is_null = false;
                        tv_company_function.setText(first);
                    }else {
                        tv_company_function_is_null = false;
                        tv_company_function.setText(first+"；"+second);
                    }
                }
                BtnToSave();
                break;
        }
    }

    private void initComapnyCharacterPickerView(){
        company_character_pickerView.setOnItemClickListener(new OnItemClickListener(){
            @Override
            public void onSelected(String str) {
                tv_company_character.setText(str);
                tv_company_character_is_null = false;
                BtnToSave();
            }

            @Override
            public void onCancel() {
                tv_company_character.setText("");
                tv_company_character_is_null = true;
                BtnToSave();
            }
        });
        company_character_pickerView.showCityPicker();
    }

    private void initCompanyBusinessPickerView(){
        company_business_pickerView.setOnItemClickListener(new OnItemClickListener(){
            @Override
            public void onSelected(String str) {
                tv_company_business.setText(str);
                tv_company_business_is_null = false;
                BtnToSave();
            }

            @Override
            public void onCancel() {
                tv_company_business.setText("");
                tv_company_business_is_null = true;
                BtnToSave();
            }
        });
        company_business_pickerView.showCityPicker();
    }

    private boolean isReayToSave(){
        boolean is = false;
        if(!tv_beginTime_is_null && !tv_finishTime_is_null && !et_name_is_null && !tv_company_character_is_null
                && !tv_company_business_is_null && !tv_company_function_is_null && !et_company_department_is_null
        && !et_company_position_is_null && !et_company_description_is_null){
            is = true;
        }
        return is;
    }

    private void BtnToSave(){
        if(isReayToSave()){
            btn_submit.setEnabled(true);
        }else {
            btn_submit.setEnabled(false);
        }
    }
}
