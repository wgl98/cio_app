package com.cio_app.view.project.projectSubmit.projectSubmitCompany;


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
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.cio_app.R;
import com.cio_app.view.project.projectSubmit.projectSubmitPerson.ProjectPersonSubmitActivity;
import com.e.lib_common_ui.base.BaseActivity;

import java.util.Timer;
import java.util.TimerTask;

public class ProjectCompanySubmitActivity extends BaseActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private FragmentManager manager;
    private FragmentTransaction transaction;

    private ProjectCompanySubmitFirstFragment firstFragment;
    private ProjectCompanySubmitSecondFragment secondFragment;
    private ProjectCompanySubmitThirdFragment thirdFragment;

    private ProgressBar progressBar;
    private TextView tv_progress;

    private Button btn_first_to_second;
    private Button btn_second_to_first;
    private Button btn_second_to_third;
    private Button btn_third_to_second;
    private Button btn_submit;

    private RadioGroup rg_type;   //产品类型选择
    private LinearLayout ll_other_type;

    private EditText et_name;
    private EditText et_description;
    private EditText et_product_name;
    private EditText et_other_type;
    private EditText et_product_description;
    private EditText et_example;
    private TextView tv_text_size;


    private boolean et_name_is_null = true;
    private boolean et_description_is_null = true;
    private boolean et_product_name_is_null = true;
    private boolean et_other_type_is_null = true;
    private boolean et_product_description_is_null = true;
    private boolean et_example_is_null = true;

    public static void start(Context context) {
        Intent intent = new Intent(context, ProjectCompanySubmitActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_company_submit);

        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        firstFragment = new ProjectCompanySubmitFirstFragment();
        secondFragment = new ProjectCompanySubmitSecondFragment();
        thirdFragment = new ProjectCompanySubmitThirdFragment();
        transaction.add(R.id.project_company_submit_content_layout,firstFragment);
        transaction.add(R.id.project_company_submit_content_layout,secondFragment);
        transaction.add(R.id.project_company_submit_content_layout,thirdFragment);
        transaction.hide(thirdFragment);
        transaction.hide(secondFragment);
        transaction.show(firstFragment);
        transaction.commit();

        initView();

    }

    @Override
    protected void onStart() {
        super.onStart();
        initFragmentView();
    }

    private void initView(){
        findViewById(R.id.btn_back_in_project_company_submit_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        findViewById(R.id.btn_back_in_project_company_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        progressBar = findViewById(R.id.progressBar_in_project_company_submit);
        tv_progress = findViewById(R.id.show_progress_in_project_company_submit);
        progressBar.setProgress(10);
        tv_progress.setText(changeProgressToString(10));
    }

    private void initFragmentView(){

        View firstView = firstFragment.getView();

        et_name = firstView.findViewById(R.id.project_company_submit_content_name);
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
                        tv_progress.setText(changeProgressToString(current_progress+10));
                        et_name_is_null = false;
                    }
                }else {
                    if(!et_name_is_null){
                        progressBar.setProgress(current_progress-10);
                        tv_progress.setText(changeProgressToString(current_progress-10));
                        et_name_is_null = true;
                    }
                }
                if(isReadyToSecondFragment()){
                    btn_first_to_second.setEnabled(true);
                }else {
                    btn_first_to_second.setEnabled(false);
                }
            }
        });

        et_description = firstView.findViewById(R.id.project_company_submit_content_description);
        et_description.addTextChangedListener(new TextWatcher() {
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
                    if(et_description_is_null){
                        progressBar.setProgress(current_progress+10);
                        tv_progress.setText(changeProgressToString(current_progress+10));
                        et_description_is_null = false;
                    }
                }else {
                    if(!et_description_is_null){
                        progressBar.setProgress(current_progress-10);
                        tv_progress.setText(changeProgressToString(current_progress-10));
                        et_description_is_null = true;
                    }
                }
                if(isReadyToSecondFragment()){
                    btn_first_to_second.setEnabled(true);
                }else {
                    btn_first_to_second.setEnabled(false);
                }
            }
        });

        et_product_name = firstView.findViewById(R.id.project_company_submit_content_product_name);
        et_product_name.addTextChangedListener(new TextWatcher() {
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
                    if(et_product_name_is_null){
                        progressBar.setProgress(current_progress+10);
                        tv_progress.setText(changeProgressToString(current_progress+10));
                        et_product_name_is_null = false;
                    }
                }else {
                    if(!et_product_name_is_null){
                        progressBar.setProgress(current_progress-10);
                        tv_progress.setText(changeProgressToString(current_progress-10));
                        et_product_name_is_null = true;
                    }
                }
                if(isReadyToSecondFragment()){
                    btn_first_to_second.setEnabled(true);
                }else {
                    btn_first_to_second.setEnabled(false);
                }
            }
        });

        et_other_type = firstView.findViewById(R.id.project_company_submit_content_other_type);
        et_other_type.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if(s.length() != 0){
                        et_other_type_is_null = false;
                }else{
                        et_other_type_is_null = true;
                }
                if(isReadyToSecondFragment()){
                    btn_first_to_second.setEnabled(true);
                }else {
                    btn_first_to_second.setEnabled(false);
                }
            }
        });

        rg_type = firstView.findViewById(R.id.project_company_submit_content_type);
        rg_type.setOnCheckedChangeListener(this);

        ll_other_type = firstView.findViewById(R.id.project_company_submit_content_other_type_layout);

        btn_first_to_second = firstView.findViewById(R.id.btn_project_company_submit_first_to_second);
        btn_first_to_second.setOnClickListener(this);

        View secondView = secondFragment.getView();

        et_product_description = secondView.findViewById(R.id.project_company_submit_content_produce_description);
        et_product_description.addTextChangedListener(new TextWatcher() {
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
                    if(et_product_description_is_null){
                        progressBar.setProgress(current_progress+30);
                        tv_progress.setText(changeProgressToString(current_progress+30));
                        et_product_description_is_null = false;
                    }
                }else {
                    if(!et_product_description_is_null){
                        progressBar.setProgress(current_progress-30);
                        tv_progress.setText(changeProgressToString(current_progress-30));
                        et_product_description_is_null = true;
                    }
                }
                if(!et_product_description_is_null){
                    btn_second_to_third.setEnabled(true);
                }else {
                    btn_second_to_third.setEnabled(false);
                }
            }
        });

        btn_second_to_third = secondView.findViewById(R.id.btn_project_company_submit_second_to_third);
        btn_second_to_third.setOnClickListener(this);

        btn_second_to_first = secondView.findViewById(R.id.btn_project_company_submit_second_to_first);
        btn_second_to_first.setOnClickListener(this);

        View thirdView = thirdFragment.getView();

        tv_text_size = thirdView.findViewById(R.id.project_company_submit_content_text_size);
        et_example = thirdView.findViewById(R.id.project_company_submit_content_example);
        et_example.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                tv_text_size.setText(String.valueOf(s.length())+"/150");
                int current_progress = progressBar.getProgress();
                if(s.length() != 0){
                    if(et_example_is_null){
                        progressBar.setProgress(current_progress+30);
                        tv_progress.setText(changeProgressToString(current_progress+30));
                        et_example_is_null = false;
                    }
                }else {
                    if(!et_example_is_null){
                        progressBar.setProgress(current_progress-30);
                        tv_progress.setText(changeProgressToString(current_progress-30));
                        et_example_is_null = true;
                    }
                }
                if(!et_example_is_null){
                    btn_submit.setEnabled(true);
                }else {
                    btn_submit.setEnabled(false);
                }
            }
        });

        btn_third_to_second = thirdView.findViewById(R.id.btn_project_company_submit_third_to_second);
        btn_third_to_second.setOnClickListener(this);

        btn_submit = thirdView.findViewById(R.id.btn_project_company_submit_submit);
        btn_submit.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_project_company_submit_first_to_second:
                transaction = manager.beginTransaction();
                transaction.hide(firstFragment);
                transaction.show(secondFragment);
                transaction.commit();
                break;
            case R.id.btn_project_company_submit_second_to_first:
                transaction = manager.beginTransaction();
                transaction.hide(secondFragment);
                transaction.show(firstFragment);
                transaction.commit();
                break;
            case R.id.btn_project_company_submit_second_to_third:
                transaction = manager.beginTransaction();
                transaction.hide(secondFragment);
                transaction.show(thirdFragment);
                transaction.commit();
                break;
            case R.id.btn_project_company_submit_third_to_second:
                transaction = manager.beginTransaction();
                transaction.hide(thirdFragment);
                transaction.show(secondFragment);
                transaction.commit();
                break;
            case R.id.btn_project_company_submit_submit:
                dialog_submit_success();
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.project_company_submit_content_type_other:
                ll_other_type.setVisibility(View.VISIBLE);
                if(isReadyToSecondFragment()){
                    btn_first_to_second.setEnabled(true);
                }else {
                    btn_first_to_second.setEnabled(false);
                }
                break;
            default:
                ll_other_type.setVisibility(View.GONE);
                if(isReadyToSecondFragment()){
                    btn_first_to_second.setEnabled(true);
                }else {
                    btn_first_to_second.setEnabled(false);
                }
                break;
        }
    }

    private boolean otherTypeIsNull(){
        boolean is = true;
        if(ll_other_type.getVisibility() == View.VISIBLE && !et_other_type_is_null){
            is = false;
        }else if(ll_other_type.getVisibility() == View.GONE){
            is = false;
        }
        return is;
    }

    private boolean isReadyToSecondFragment(){
        boolean is = false;
        if(!et_name_is_null && !et_description_is_null && !et_product_name_is_null && !otherTypeIsNull()){
            is = true;
        }
        return is;
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

    private String changeProgressToString(int progress){
        return String.valueOf(progress)+"%";
    }
}
