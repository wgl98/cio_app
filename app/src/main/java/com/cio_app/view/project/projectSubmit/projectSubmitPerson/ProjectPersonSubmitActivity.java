package com.cio_app.view.project.projectSubmit.projectSubmitPerson;


import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.cio_app.R;
import com.e.lib_common_ui.base.BaseActivity;

import java.util.Timer;
import java.util.TimerTask;

public class ProjectPersonSubmitActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener{

    private EditText et_name;
    private EditText et_phone;
    private EditText et_email;
    private EditText et_content;
    private TextView tv_size;
    private RadioGroup rg_type;
    private RadioButton rb_softWare_implementation;
    private RadioButton rb_softWare_develop;
    private RadioButton rb_train;
    private RadioButton rb_consult;
    private Button btn_submit;

    private boolean et_name_is_null = true;
    private boolean et_phone_is_null = true;
    private boolean et_email_is_null = true;
    private boolean et_content_is_null = true;


    public static void start(Context context) {
        Intent intent = new Intent(context, ProjectPersonSubmitActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_person_submit);

        initView();

        initRadioButton();
    }

    private void initView(){
        findViewById(R.id.btn_back_in_project_person_submit_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        findViewById(R.id.btn_back_in_project_person_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        et_name = findViewById(R.id.project_person_submit_content_name);
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
                    if(et_name_is_null){
                        et_name_is_null = false;
                    }
                }else {
                    if(!et_name_is_null){
                        et_name_is_null = true;
                    }
                }
                if(isReadyToSubmit()){
                    btn_submit.setEnabled(true);
                }else {
                    btn_submit.setEnabled(false);
                }
            }
        });

        et_phone = findViewById(R.id.project_person_submit_content_email);
        et_phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if(s.length() != 0){
                    if(et_phone_is_null){
                        et_phone_is_null = false;
                    }
                }else {
                    if(!et_phone_is_null){
                        et_phone_is_null = true;
                    }
                }
                if(isReadyToSubmit()){
                    btn_submit.setEnabled(true);
                }else {
                    btn_submit.setEnabled(false);
                }
            }
        });

        et_email = findViewById(R.id.project_person_submit_content_phone);
        et_email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if(s.length() != 0){
                    if(et_email_is_null){
                        et_email_is_null = false;
                    }
                }else {
                    if(!et_email_is_null){
                        et_email_is_null = true;
                    }
                }
                if(isReadyToSubmit()){
                    btn_submit.setEnabled(true);
                }else {
                    btn_submit.setEnabled(false);
                }
            }
        });

        tv_size = findViewById(R.id.project_person_submit_content_text_size);

        et_content = findViewById(R.id.project_person_submit_content_main_content);
        et_content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                tv_size.setText(String.valueOf(s.length())+"/150");
                if(s.length() != 0){
                    if(et_content_is_null){
                        et_content_is_null = false;
                    }
                }else {
                    if(!et_content_is_null){
                        et_content_is_null = true;
                    }
                }
                if(isReadyToSubmit()){
                    btn_submit.setEnabled(true);
                }else {
                    btn_submit.setEnabled(false);
                }
            }
        });

        rg_type = findViewById(R.id.project_person_submit_content_type);
        rg_type.setOnCheckedChangeListener(this);
        rb_softWare_implementation = findViewById(R.id.project_person_submit_content_software_implementation);
        rb_softWare_develop = findViewById(R.id.project_person_submit_content_software_develop);
        rb_train = findViewById(R.id.project_person_submit_content_train);
        rb_consult = findViewById(R.id.project_person_submit_content_consult);

        btn_submit = findViewById(R.id.btn_project_person_submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_submit_success();
            }
        });
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

    }

    private void initRadioButton(){
        int width = dpToPx(this,316);
        // 这里设置的是radio button的高度
        int height = dpToPx(this, 21);

        LinearLayout.LayoutParams params4 = (LinearLayout.LayoutParams) rb_softWare_develop.getLayoutParams();
        params4.setMargins(width / 2, -height, 0, 0);
        rb_softWare_develop.setLayoutParams(params4);

        LinearLayout.LayoutParams params5 = (LinearLayout.LayoutParams) rb_consult.getLayoutParams();
        params5.setMargins(width / 2, -height, 0, 0);
        rb_consult.setLayoutParams(params5);
    }

    private boolean isReadyToSubmit(){
        boolean is = false;
        if(!et_name_is_null && !et_phone_is_null && !et_email_is_null && !et_content_is_null){
            is = true;
        }
        return  is;
    }

    public static int dpToPx(final Context context, final float dp) {
        return (int) (dp * context.getResources().getDisplayMetrics().density);
    }

    private void dialog_submit_success(){
        AlertDialog.Builder alterDiaglog = new AlertDialog.Builder(this,R.style.DialogStyle);
        alterDiaglog.setView(R.layout.dialog_is_submit_success);//加载进去
        final AlertDialog dialog = alterDiaglog.create();
        //显示
        dialog.show();
        WindowManager windowManager = getWindowManager();
        Point point = new Point();
        windowManager.getDefaultDisplay().getSize(point);
        int height = point.y;
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.height = (int) (height * 0.5); // 高度设置为屏幕的0.5 
        dialog.getWindow().setAttributes(params);
        final Timer t = new Timer();
        t.schedule(new TimerTask() {
            public void run() {
                dialog.dismiss();
                t.cancel();
                //finish();
            }
        }, 3000);
    }

}
