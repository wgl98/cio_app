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

import com.cio_app.R;
import com.cio_app.model.ListData;
import com.e.lib_common_ui.base.BaseActivity;
import com.lib_pickerview.Interface.OnItemClickListener;
import com.lib_pickerview.style.pickerview.PickerView;
import com.lvfq.pickerview.TimePickerView;
import com.lvfq.pickerview.utils.Util;

public class SubmitEducationMessageActivity extends BaseActivity implements View.OnClickListener{

    private RelativeLayout rl_select_begin_time;          //选择入学时间
    private TextView tv_begin_time;

    private RelativeLayout rl_select_finish_time;         //选择毕业时间
    private TextView tv_finish_time;

    private RelativeLayout rl_select_background;          //选择学历
    private TextView tv_background;

    private EditText et_school;                           //学校
    private EditText et_profession;                       //专业

    private Button btn_submit;

    private ListData listData = new ListData();
    private PickerView background_pickerView = new PickerView();

    private boolean tv_begin_time_is_null = true;
    private boolean tv_finish_time_is_null = true;
    private boolean tv_background_is_null = true;
    private boolean et_school_is_null = true;
    private boolean et_profession_is_null = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_education_message);
        background_pickerView.init(this,listData.getBackgroundData());
        initView();
    }

    private void initView(){
        findViewById(R.id.btn_back_in_submit_education_message_page_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        findViewById(R.id.btn_back_in_submit_education_message_page).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        rl_select_begin_time = findViewById(R.id.submit_education_message_page_content_beginTime_layout);
        rl_select_begin_time.setOnClickListener(this);

        tv_begin_time = findViewById(R.id.submit_education_message_page_content_beginTime);

        rl_select_finish_time = findViewById(R.id.submit_education_message_page_content_finishTime_layout);
        rl_select_finish_time.setOnClickListener(this);

        tv_finish_time = findViewById(R.id.submit_education_message_page_content_finishTime);

        rl_select_background = findViewById(R.id.submit_education_message_page_content_background_layout);
        rl_select_background.setOnClickListener(this);

        tv_background = findViewById(R.id.submit_education_message_page_content_background);

        et_school = findViewById(R.id.submit_education_message_page_content_school);
        et_school.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length() != 0){
                    et_school_is_null = false;
                }else {
                    et_school_is_null = true;
                }
                BtnToSave();
            }
        });

        et_profession = findViewById(R.id.submit_education_message_page_content_profession);
        et_profession.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length() != 0){
                    et_profession_is_null = false;
                }else {
                    et_profession_is_null = true;
                }
                BtnToSave();
            }
        });

        btn_submit = findViewById(R.id.btn_submit_education_message);
        btn_submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        TimePickerView.Type type = null;
        String format = "";
        switch (v.getId()){
            case R.id.submit_education_message_page_content_beginTime_layout:
                type = TimePickerView.Type.YEAR_MONTH;
                format = "yyyy/MM";
                Util.alertTimerPicker(this, type, format, new Util.TimerPickerCallBack() {
                    @Override
                    public void onTimeSelect(String date) {
                        tv_begin_time.setText(date);
                        tv_begin_time_is_null = false;
                        BtnToSave();
                    }
                });
                break;
            case R.id.submit_education_message_page_content_finishTime_layout:
                type = TimePickerView.Type.YEAR_MONTH;
                format = "yyyy/MM";
                Util.alertTimerPicker(this, type, format, new Util.TimerPickerCallBack() {
                    @Override
                    public void onTimeSelect(String date) {
                        tv_finish_time.setText(date);
                        tv_finish_time_is_null = false;
                        BtnToSave();
                    }
                });
                break;
            case R.id.submit_education_message_page_content_background_layout:
                initBackGroundPickerView();
                break;
            case R.id.btn_submit_education_message:
                Intent intent = new Intent();
                intent.putExtra("beginTime",tv_begin_time.getText());
                intent.putExtra("finishTime",tv_finish_time.getText());
                intent.putExtra("background",tv_background.getText());
                intent.putExtra("school",et_school.getText().toString());
                intent.putExtra("profession",et_profession.getText().toString());
                setResult(RESULT_OK,intent);
                finish();
                break;
        }
    }

    private void initBackGroundPickerView(){
        background_pickerView.setOnItemClickListener(new OnItemClickListener(){
            @Override
            public void onSelected(String str) {
                tv_background.setText(str);
                tv_background_is_null = false;
                BtnToSave();
            }

            @Override
            public void onCancel() {
                tv_background.setText("");
                tv_background_is_null = true;
                BtnToSave();
            }
        });
        background_pickerView.showCityPicker();
    }

    private boolean isReayToSave(){
        boolean is = false;
        if(!tv_begin_time_is_null && !tv_finish_time_is_null && !tv_background_is_null && !et_school_is_null
                && !et_profession_is_null){
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
