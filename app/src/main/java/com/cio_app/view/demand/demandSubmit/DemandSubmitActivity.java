package com.cio_app.view.demand.demandSubmit;


import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.cio_app.R;
import com.e.lib_common_ui.base.BaseActivity;

import java.util.Timer;
import java.util.TimerTask;


public class DemandSubmitActivity extends BaseActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener{

    private FragmentManager manager;
    private FragmentTransaction transaction;

    private DemandSubmitFirstFragment demandSubmitFirstFragment;
    private DemandSubmitSecondFragment demandSubmitSecondFragment;


    private ProgressBar progressBar;
    private TextView tv_show_progress;   //展示进度值

    private Button btn_to_next;      //进入下一步
    private Button btn_to_before;    //回到上一步
    private Button btn_submit;       //提交

    private RadioGroup radioGroup;  //需求成熟度选择

    private EditText et_number;      //会员编号
    private EditText et_contacts;    //联系人
    private EditText et_name;        //单位名称
    private EditText et_department;  //所在部门
    private EditText et_phone;       //电话
    private EditText et_other_maturity;    //成熟度
    private EditText et_price;       //需求预算
    private EditText et_title;       //需求标题
    private EditText et_content;     //需求主要内容

    private TextView tv_show_text_number; //显示字的个数

    private boolean et_number_is_null = true;
    private boolean et_contacts_is_null = true;
    private boolean et_name_is_null = true;
    private boolean et_department_is_null = true;
    private boolean et_phone_is_null = true;
    private boolean et_other_maturity_is_null = true;
    private boolean et_price_is_null = true;
    private boolean et_title_is_null = true;
    private boolean et_content_is_null = true;

    public static void start(Context context) {
        Intent intent = new Intent(context, DemandSubmitActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demand_submit);

        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        demandSubmitFirstFragment =  new DemandSubmitFirstFragment();
        demandSubmitSecondFragment = new DemandSubmitSecondFragment();
        transaction.add(R.id.demand_submit_content_layout, demandSubmitFirstFragment);
        transaction.add(R.id.demand_submit_content_layout,demandSubmitSecondFragment);
        transaction.hide(demandSubmitSecondFragment);
        transaction.show(demandSubmitFirstFragment);
        transaction.commit();
        initView();

    }

    @Override
    protected void onStart() {
        super.onStart();
        initFragmentView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_demand_submit_next:
                transaction = manager.beginTransaction();
                transaction.hide(demandSubmitFirstFragment);
                transaction.show(demandSubmitSecondFragment);
                transaction.commit();
                break;
            case R.id.btn_demand_submit_before:
                transaction = manager.beginTransaction();
                transaction.hide(demandSubmitSecondFragment);
                transaction.show(demandSubmitFirstFragment);
                transaction.commit();
                break;
            case R.id.btn_demand_submit_submit:
                dialog_submit_success();
                break;
            default:
                break;
        }
    }

    private void initView(){
        findViewById(R.id.btn_back_in_demand_submit_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        findViewById(R.id.btn_back_in_demand_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        progressBar = (ProgressBar) findViewById(R.id.progressBar_in_demand_submit);
        progressBar.setProgress(10);

        tv_show_progress = (TextView) findViewById(R.id.show_progress_in_demand_submit);
        tv_show_progress.setText(changeProgressToString(10));

    }

    private void initFragmentView(){
        //获取当前fragment内的控件
            View firstView = demandSubmitFirstFragment.getView();
            Log.d("碎片",firstView.toString());

            et_number = firstView.findViewById(R.id.demand_submit_content_number);
            et_number.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }
                @Override
                public void afterTextChanged(Editable s) {
                    int current_progress = progressBar.getProgress();
                    if(s.length() != 0){
                        if(et_number_is_null){
                            progressBar.setProgress(current_progress+10);
                            tv_show_progress.setText(changeProgressToString(current_progress+10));
                            et_number_is_null = false;
                        }
                    }else {
                        if(!et_number_is_null){
                            progressBar.setProgress(current_progress-10);
                            tv_show_progress.setText(changeProgressToString(current_progress-10));
                            et_number_is_null = true;
                        }
                    }
                    if(isReadyToNext()){
                        btn_to_next.setEnabled(true);
                    }else {
                        btn_to_next.setEnabled(false);
                    }
                }
            });

            et_contacts = firstView.findViewById(R.id.demand_submit_content_contacts);
            et_contacts.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }
                @Override
                public void afterTextChanged(Editable s) {
                    int current_progress = progressBar.getProgress();
                    if(s.length() != 0){
                        if(et_contacts_is_null){
                            progressBar.setProgress(current_progress+10);
                            tv_show_progress.setText(changeProgressToString(current_progress+10));
                            et_contacts_is_null = false;
                        }
                    }else {
                        if(!et_contacts_is_null){
                            progressBar.setProgress(current_progress-10);
                            tv_show_progress.setText(changeProgressToString(current_progress-10));
                            et_contacts_is_null = true;
                        }
                    }
                    if(isReadyToNext()){
                        btn_to_next.setEnabled(true);
                    }else {
                        btn_to_next.setEnabled(false);
                    }
                }
            });

            et_name = firstView.findViewById(R.id.demand_submit_content_name);
            et_name.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }
                @Override
                public void afterTextChanged(Editable s) {
                    int current_progress = progressBar.getProgress();
                    if(s.length() != 0){
                        if(et_name_is_null){
                            progressBar.setProgress(current_progress+10);
                            tv_show_progress.setText(changeProgressToString(current_progress+10));
                            et_name_is_null = false;
                        }
                    }else {
                        if(!et_name_is_null){
                            progressBar.setProgress(current_progress-10);
                            tv_show_progress.setText(changeProgressToString(current_progress-10));
                            et_name_is_null = true;
                        }
                    }
                    if(isReadyToNext()){
                        btn_to_next.setEnabled(true);
                    }else {
                        btn_to_next.setEnabled(false);
                    }
                }
            });

            et_department = firstView.findViewById(R.id.demand_submit_content_department);
            et_department.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }

                @Override
                public void afterTextChanged(Editable s) {
                    int current_progress = progressBar.getProgress();
                    if(s.length() != 0){
                        if(et_department_is_null){
                            progressBar.setProgress(current_progress+10);
                            tv_show_progress.setText(changeProgressToString(current_progress+10));
                            et_department_is_null = false;
                        }
                    }else {
                        if(!et_department_is_null){
                            progressBar.setProgress(current_progress-10);
                            tv_show_progress.setText(changeProgressToString(current_progress-10));
                            et_department_is_null = true;
                        }
                    }
                    if(isReadyToNext()){
                        btn_to_next.setEnabled(true);
                    }else {
                        btn_to_next.setEnabled(false);
                    }
                }
            });

            et_phone = firstView.findViewById(R.id.demand_submit_content_phone);
            et_phone.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }
                @Override
                public void afterTextChanged(Editable s) {
                    int current_progress = progressBar.getProgress();
                    if(s.length() != 0){
                        if(et_phone_is_null){
                            progressBar.setProgress(current_progress+10);
                            tv_show_progress.setText(changeProgressToString(current_progress+10));
                            et_phone_is_null = false;
                        }
                    }else {
                        if(!et_phone_is_null){
                            progressBar.setProgress(current_progress-10);
                            tv_show_progress.setText(changeProgressToString(current_progress-10));
                            et_phone_is_null = true;
                        }
                    }
                    if(isReadyToNext()){
                        btn_to_next.setEnabled(true);
                    }else {
                        btn_to_next.setEnabled(false);
                    }
                }
            });

            radioGroup = firstView.findViewById(R.id.demand_submit_content_maturity);
            radioGroup.setOnCheckedChangeListener(this);

            et_other_maturity = firstView.findViewById(R.id.demand_submit_content_other_maturity);
            et_other_maturity.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }
                @Override
                public void afterTextChanged(Editable s) {
                    if(s.length() != 0){
                        et_other_maturity_is_null = false;
                    }else {
                        et_other_maturity_is_null = true;
                    }
                    if(isReadyToNext()){
                        btn_to_next.setEnabled(true);
                    }else {
                        btn_to_next.setEnabled(false);
                    }
                }
            });

            et_price = firstView.findViewById(R.id.demand_submit_content_price);
            et_price.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }
                @Override
                public void afterTextChanged(Editable s) {
                    int current_progress = progressBar.getProgress();
                    if(s.length() != 0){
                        if(et_price_is_null){
                            progressBar.setProgress(current_progress+10);
                            tv_show_progress.setText(changeProgressToString(current_progress+10));
                            et_price_is_null = false;
                        }
                    }else {
                        if(!et_price_is_null){
                            progressBar.setProgress(current_progress-10);
                            tv_show_progress.setText(changeProgressToString(current_progress-10));
                            et_price_is_null = true;
                        }
                    }
                    if(isReadyToNext()){
                        btn_to_next.setEnabled(true);
                    }else {
                        btn_to_next.setEnabled(false);
                    }
                }
            });

            btn_to_next = firstView.findViewById(R.id.btn_demand_submit_next);
            btn_to_next.setOnClickListener(this);

            View secondView = demandSubmitSecondFragment.getView();
            Log.d("碎片",secondView.toString());

            et_title = secondView.findViewById(R.id.demand_submit_content_title);
            et_title.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }
                @Override
                public void afterTextChanged(Editable s) {
                    int current_progress = progressBar.getProgress();
                    if(s.length() != 0){
                        if(et_title_is_null){
                            progressBar.setProgress(current_progress+10);
                            tv_show_progress.setText(changeProgressToString(current_progress+10));
                            et_title_is_null = false;
                        }
                    }else {
                        if(!et_title_is_null){
                            progressBar.setProgress(current_progress-10);
                            tv_show_progress.setText(changeProgressToString(current_progress-10));
                            et_title_is_null = true;
                        }
                    }
                    if(isReadyToSubmit()){
                        btn_submit.setEnabled(true);
                    }else {
                        btn_submit.setEnabled(false);
                    }
                }
            });
            et_content = secondView.findViewById(R.id.demand_submit_content_main_content);
            et_content.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }
                @Override
                public void afterTextChanged(Editable s) {
                    tv_show_text_number.setText(String.valueOf(s.length())+"/150");
                    int current_progress = progressBar.getProgress();
                    if(s.length() != 0){
                        if(et_content_is_null){
                            progressBar.setProgress(current_progress+20);
                            tv_show_progress.setText(changeProgressToString(current_progress+20));
                            et_content_is_null = false;
                        }
                    }else {
                        if(!et_content_is_null){
                            progressBar.setProgress(current_progress-20);
                            tv_show_progress.setText(changeProgressToString(current_progress-20));
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
            tv_show_text_number = secondView.findViewById(R.id.demand_submit_content_text_size);
            btn_to_before = secondView.findViewById(R.id.btn_demand_submit_before);
            btn_to_before.setOnClickListener(this);
            btn_submit = secondView.findViewById(R.id.btn_demand_submit_submit);
            btn_submit.setOnClickListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.demand_submit_content_investigate_stage:
                et_other_maturity.setVisibility(View.GONE);
                if(isReadyToNext()){
                    btn_to_next.setEnabled(true);
                }else {
                    btn_to_next.setEnabled(false);
                }
                break;
            case R.id.demand_submit_content_argue_stage:
                et_other_maturity.setVisibility(View.GONE);
                if(isReadyToNext()){
                    btn_to_next.setEnabled(true);
                }else {
                    btn_to_next.setEnabled(false);
                }
                break;
            case R.id.demand_submit_content_project_stage:
                et_other_maturity.setVisibility(View.GONE);
                if(isReadyToNext()){
                    btn_to_next.setEnabled(true);
                }else {
                    btn_to_next.setEnabled(false);
                }
                break;
            case R.id.demand_submit_content_call_stage:
                et_other_maturity.setVisibility(View.GONE);
                if(isReadyToNext()){
                    btn_to_next.setEnabled(true);
                }else {
                    btn_to_next.setEnabled(false);
                }
                break;
            case R.id.demand_submit_content_implement_stage:
                et_other_maturity.setVisibility(View.GONE);
                if(isReadyToNext()){
                    btn_to_next.setEnabled(true);
                }else {
                    btn_to_next.setEnabled(false);
                }
                break;
            case R.id.demand_submit_content_other_stage:
                et_other_maturity.setVisibility(View.VISIBLE);
                if(isReadyToNext()){
                    btn_to_next.setEnabled(true);
                }else {
                    btn_to_next.setEnabled(false);
                }
                break;
        }
    }

    private String changeProgressToString(int progress){
        return String.valueOf(progress)+"%";
    }

    private boolean otherMaturityIsNull(){
        boolean is = true;
        if(et_other_maturity.getVisibility() == View.VISIBLE && !et_other_maturity_is_null){
            is = false;
        }else if(et_other_maturity.getVisibility() == View.GONE){
            is = false;
        }
        return is;
    }

    private boolean isReadyToNext(){
        boolean is = false;
        if(!et_number_is_null && !et_contacts_is_null && !et_name_is_null && !et_phone_is_null
                && !et_department_is_null && !otherMaturityIsNull() && !et_price_is_null) {
            is = true;
        }
        return is;
    }

    private boolean isReadyToSubmit(){
        boolean is = false;
        if(!et_number_is_null && !et_contacts_is_null && !et_name_is_null && !et_phone_is_null
            && !et_department_is_null && !otherMaturityIsNull() && !et_price_is_null && !et_title_is_null
            && !et_content_is_null) {
            is = true;
        }
        return is;
    }

    private void dialog_submit_success(){
        AlertDialog.Builder alterDiaglog = new AlertDialog.Builder(DemandSubmitActivity.this,R.style.DialogStyle);
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
