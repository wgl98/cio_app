package com.cio_app.view.mine.member.personMemberSubmit;


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
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.cio_app.R;
import com.cio_app.model.ListData;
import com.cio_app.view.mine.member.personApproveInformation.PersonApproveInformationActivity;
import com.cio_app.view.select.SelectSiteActivity;
import com.e.lib_common_ui.base.BaseActivity;
import com.lib_pickerview.Interface.OnItemClickListener;
import com.lib_pickerview.style.pickerview.PickerView;
import com.lvfq.pickerview.TimePickerView;
import com.lvfq.pickerview.utils.Util;

import java.util.Timer;
import java.util.TimerTask;

public class SubmitPersonInformationActivity extends BaseActivity implements View.OnClickListener{

    public static final int ADDRESS = 1;

    private ProgressBar progressBar;
    private TextView tv_progress;

    private EditText et_name;                              //姓名

    private RelativeLayout rl_select_sex;                  //选择性别
    private TextView tv_sex;

    private RelativeLayout rl_select_birthday;             //选择生日
    private TextView tv_birthday;

    private EditText et_phone;

    private EditText et_workTime;

    private EditText et_background;

    private EditText et_profession;

    private EditText et_workUnit;

    private EditText et_department;

    private EditText et_position;

    private RelativeLayout rl_select_address;
    private TextView tv_address;

    private RelativeLayout rl_select_character;
    private TextView tv_character;

    private RelativeLayout rl_select_market;
    private TextView tv_market;

    private EditText et_size;

    private EditText et_profit;

    private RelativeLayout rl_select_business;
    private TextView tv_business;

    private EditText et_experience;
    private TextView tv_experience_text_amount;

    private EditText et_information;
    private TextView tv_information_text_amount;

    private Button btn_first_to_second;
    private Button btn_second_to_first;
    private Button btn_second_to_third;
    private Button btn_third_to_second;
    private Button btn_third_to_fourth;
    private Button btn_fourth_to_third;
    private Button btn_submit;

    private boolean et_name_is_null = true;
    private boolean tv_sex_is_null = true;
    private boolean tv_birthday_is_null = true;
    private boolean et_phone_is_null = true;
    private boolean et_workTime_is_null = true;
    private boolean et_background_is_null = true;
    private boolean et_profession_is_null = true;
    private boolean et_workUnit_is_null = true;
    private boolean et_department_is_null = true;
    private boolean et_position_is_null = true;
    private boolean tv_address_is_null = true;
    private boolean tv_character_is_null = true;
    private boolean tv_market_is_null = true;
    private boolean et_size_is_null = true;
    private boolean et_profit_is_null = true;
    private boolean tv_business_is_null = true;
    private boolean et_experience_is_null = true;
    private boolean et_information_is_null = true;

    private ListData listData = new ListData();
    private PickerView sex_pickerView = new PickerView();
    private PickerView character_pickerView = new PickerView();
    private PickerView market_pivkerView = new PickerView();
    private PickerView business_pickerView = new PickerView();

    private FragmentManager manager;
    private FragmentTransaction transaction;

    private SubmitPersonInformationFirstFragment firstFragment;
    private SubmitPersonInformationSecondFragment secondFragment;
    private SubmitPersonInformationThirdFragment thirdFragment;
    private SubmitPersonInformationFourthFragment fourthFragment;

    public static void start(Context context) {
        Intent intent = new Intent(context, SubmitPersonInformationActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_person_information);

        sex_pickerView.init(this,listData.getSexData());
        character_pickerView.init(this,listData.getUnitCharacterData());
        market_pivkerView.init(this,listData.getPublicData());
        business_pickerView.init(this,listData.getCompanyBusinessData());

        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        firstFragment = new SubmitPersonInformationFirstFragment();
        secondFragment = new SubmitPersonInformationSecondFragment();
        thirdFragment = new SubmitPersonInformationThirdFragment();
        fourthFragment = new SubmitPersonInformationFourthFragment();
        transaction.add(R.id.person_information_submit_content_layout,firstFragment);
        transaction.add(R.id.person_information_submit_content_layout,secondFragment);
        transaction.add(R.id.person_information_submit_content_layout,thirdFragment);
        transaction.add(R.id.person_information_submit_content_layout,fourthFragment);
        transaction.hide(secondFragment);
        transaction.hide(thirdFragment);
        transaction.hide(fourthFragment);
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
        findViewById(R.id.btn_back_in_person_information_submit_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        findViewById(R.id.btn_back_in_person_information_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        progressBar = findViewById(R.id.progressBar_in_person_information_submit);
        tv_progress = findViewById(R.id.show_progress_in_person_information_submit);
    }

    private void initFragmentView(){
        View firstView = firstFragment.getView();
        et_name = firstView.findViewById(R.id.person_information_submit_content_name);
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
                        progressBar.setProgress(current_progress+5);
                        tv_progress.setText(changeProgressToString(current_progress+5));
                        et_name_is_null = false;
                    }
                }else {
                    if(!et_name_is_null){
                        progressBar.setProgress(current_progress-5);
                        tv_progress.setText(changeProgressToString(current_progress-5));
                        et_name_is_null = true;
                    }
                }
                btnToSecondFragment();
            }
        });

        rl_select_sex = firstView.findViewById(R.id.person_information_submit_content_sex_layout);
        rl_select_sex.setOnClickListener(this);
        tv_sex = firstView.findViewById(R.id.person_information_submit_content_sex);

        rl_select_birthday = firstView.findViewById(R.id.person_information_submit_content_birthday_layout);
        rl_select_birthday.setOnClickListener(this);
        tv_birthday = firstView.findViewById(R.id.person_information_submit_birthday_sex);

        et_phone = firstView.findViewById(R.id.person_information_submit_content_phone);
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
                        progressBar.setProgress(current_progress+5);
                        tv_progress.setText(changeProgressToString(current_progress+5));
                        et_phone_is_null = false;
                    }
                }else {
                    if(!et_phone_is_null){
                        progressBar.setProgress(current_progress-5);
                        tv_progress.setText(changeProgressToString(current_progress-5));
                        et_phone_is_null = true;
                    }
                }
                btnToSecondFragment();
            }
        });

        et_workTime = firstView.findViewById(R.id.person_information_submit_content_workTime);
        et_workTime.addTextChangedListener(new TextWatcher() {
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
                    if(et_workTime_is_null){
                        progressBar.setProgress(current_progress+5);
                        tv_progress.setText(changeProgressToString(current_progress+5));
                        et_workTime_is_null = false;
                    }
                }else {
                    if(!et_workTime_is_null){
                        progressBar.setProgress(current_progress-5);
                        tv_progress.setText(changeProgressToString(current_progress-5));
                        et_workTime_is_null = true;
                    }
                }
                btnToSecondFragment();
            }
        });

        et_background = firstView.findViewById(R.id.person_information_submit_content_background);
        et_background.addTextChangedListener(new TextWatcher() {
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
                    if(et_background_is_null){
                        progressBar.setProgress(current_progress+5);
                        tv_progress.setText(changeProgressToString(current_progress+5));
                        et_background_is_null = false;
                    }
                }else {
                    if(!et_background_is_null){
                        progressBar.setProgress(current_progress-5);
                        tv_progress.setText(changeProgressToString(current_progress-5));
                        et_background_is_null = true;
                    }
                }
                btnToSecondFragment();
            }
        });

        et_profession = firstView.findViewById(R.id.person_information_submit_content_profession);
        et_profession.addTextChangedListener(new TextWatcher() {
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
                    if(et_profession_is_null){
                        progressBar.setProgress(current_progress+5);
                        tv_progress.setText(changeProgressToString(current_progress+5));
                        et_profession_is_null = false;
                    }
                }else {
                    if(!et_profession_is_null){
                        progressBar.setProgress(current_progress-5);
                        tv_progress.setText(changeProgressToString(current_progress-5));
                        et_profession_is_null = true;
                    }
                }
                btnToSecondFragment();
            }
        });

        btn_first_to_second = firstView.findViewById(R.id.btn_person_information_submit_first_to_second);
        btn_first_to_second.setOnClickListener(this);

        View secondView = secondFragment.getView();
        btn_second_to_first = secondView.findViewById(R.id.btn_person_information_submit_second_to_first);
        btn_second_to_first.setOnClickListener(this);

        btn_second_to_third = secondView.findViewById(R.id.btn_person_information_submit_second_to_third);
        btn_second_to_third.setOnClickListener(this);

        et_workUnit = secondView.findViewById(R.id.person_information_submit_content_workUnit);
        et_workUnit.addTextChangedListener(new TextWatcher() {
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
                    if(et_workUnit_is_null){
                        progressBar.setProgress(current_progress+5);
                        tv_progress.setText(changeProgressToString(current_progress+5));
                        et_workUnit_is_null = false;
                    }
                }else {
                    if(!et_workUnit_is_null){
                        progressBar.setProgress(current_progress-5);
                        tv_progress.setText(changeProgressToString(current_progress-5));
                        et_workUnit_is_null = true;
                    }
                }
                btnToThirdFragment();
            }
        });

        et_department = secondView.findViewById(R.id.person_information_submit_content_department);
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
                        progressBar.setProgress(current_progress+5);
                        tv_progress.setText(changeProgressToString(current_progress+5));
                        et_department_is_null = false;
                    }
                }else {
                    if(!et_department_is_null){
                        progressBar.setProgress(current_progress-5);
                        tv_progress.setText(changeProgressToString(current_progress-5));
                        et_department_is_null = true;
                    }
                }
                btnToThirdFragment();
            }
        });

        et_position = secondView.findViewById(R.id.person_information_submit_content_position);
        et_position.addTextChangedListener(new TextWatcher() {
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
                    if(et_position_is_null){
                        //progressBar.setProgress(current_progress+5);
                        //tv_progress.setText(changeProgressToString(current_progress+5));
                        et_position_is_null = false;
                    }
                }else {
                    if(!et_position_is_null){
                        //progressBar.setProgress(current_progress-5);
                        //tv_progress.setText(changeProgressToString(current_progress-5));
                        et_position_is_null = true;
                    }
                }
                btnToThirdFragment();
            }
        });

        rl_select_address = secondView.findViewById(R.id.person_information_submit_content_address_layout);
        rl_select_address.setOnClickListener(this);
        tv_address = secondView.findViewById(R.id.person_information_submit_content_address);

        rl_select_character = secondView.findViewById(R.id.person_information_submit_content_character_layout);
        rl_select_character.setOnClickListener(this);
        tv_character = secondView.findViewById(R.id.person_information_submit_content_character);

        rl_select_market = secondView.findViewById(R.id.person_information_submit_content_market_layout);
        rl_select_market.setOnClickListener(this);
        tv_market = secondView.findViewById(R.id.person_information_submit_content_market);

        et_size = secondView.findViewById(R.id.person_information_submit_content_size);
        et_size.addTextChangedListener(new TextWatcher() {
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
                    if(et_size_is_null){
                        progressBar.setProgress(current_progress+5);
                        tv_progress.setText(changeProgressToString(current_progress+5));
                        et_size_is_null = false;
                    }
                }else {
                    if(!et_size_is_null){
                        progressBar.setProgress(current_progress-5);
                        tv_progress.setText(changeProgressToString(current_progress-5));
                        et_size_is_null = true;
                    }
                }
                btnToThirdFragment();
            }
        });
        et_profit = secondView.findViewById(R.id.person_information_submit_content_profit);
        et_profit.addTextChangedListener(new TextWatcher() {
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
                    if(et_profit_is_null){
                        progressBar.setProgress(current_progress+6);
                        tv_progress.setText(changeProgressToString(current_progress+6));
                        et_profit_is_null = false;
                    }
                }else {
                    if(!et_profit_is_null){
                        progressBar.setProgress(current_progress-6);
                        tv_progress.setText(changeProgressToString(current_progress-6));
                        et_profit_is_null = true;
                    }
                }
                btnToThirdFragment();
            }
        });

        rl_select_business = secondView.findViewById(R.id.person_information_submit_content_business_layout);
        rl_select_business.setOnClickListener(this);
        tv_business = secondView.findViewById(R.id.person_information_submit_content_business);

        View thirdView = thirdFragment.getView();
        btn_third_to_second = thirdView.findViewById(R.id.btn_person_information_submit_third_to_second);
        btn_third_to_second.setOnClickListener(this);

        btn_third_to_fourth = thirdView.findViewById(R.id.btn_person_information_submit_third_to_fourth);
        btn_third_to_fourth.setOnClickListener(this);

        et_experience = thirdView.findViewById(R.id.person_information_submit_content_experience);
        et_experience.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                tv_experience_text_amount.setText(String.valueOf(s.length())+"/150");
                int current_progress = progressBar.getProgress();
                if(s.length() != 0){
                    if(et_experience_is_null){
                        progressBar.setProgress(current_progress+25);
                        tv_progress.setText(changeProgressToString(current_progress+25));
                        et_experience_is_null = false;
                    }
                }else {
                    if(!et_experience_is_null){
                        progressBar.setProgress(current_progress-25);
                        tv_progress.setText(changeProgressToString(current_progress-25));
                        et_experience_is_null = true;
                    }
                }
                if(!et_experience_is_null){
                    btn_third_to_fourth.setEnabled(true);
                }else {
                    btn_third_to_fourth.setEnabled(false);
                }
            }
        });

        tv_experience_text_amount = thirdView.findViewById(R.id.person_information_submit_content_experience_text_amount);

        View fourthView = fourthFragment.getView();
        et_information = fourthView.findViewById(R.id.person_information_submit_content_information);
        et_information.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                tv_information_text_amount.setText(String.valueOf(s.length())+"/150");
                int current_progress = progressBar.getProgress();
                if(s.length() != 0){
                    if(et_information_is_null){
                        progressBar.setProgress(current_progress+25);
                        tv_progress.setText(changeProgressToString(current_progress+25));
                        et_information_is_null = false;
                    }
                }else {
                    if(!et_information_is_null){
                        progressBar.setProgress(current_progress-25);
                        tv_progress.setText(changeProgressToString(current_progress-25));
                        et_information_is_null = true;
                    }
                }
                if(!et_information_is_null){
                    btn_submit.setEnabled(true);
                }else {
                    btn_submit.setEnabled(false);
                }
            }
        });

        tv_information_text_amount = fourthView.findViewById(R.id.person_information_submit_content_information_text_amount);

        btn_fourth_to_third = fourthView.findViewById(R.id.btn_person_information_submit_fourth_to_third);
        btn_fourth_to_third.setOnClickListener(this);

        btn_submit = fourthView.findViewById(R.id.btn_person_information_submit_submit);
        btn_submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        TimePickerView.Type type = null;
        String format = "";
        switch (v.getId()){
            case R.id.person_information_submit_content_sex_layout:
                initSexPickerView();
                break;

            case R.id.person_information_submit_content_birthday_layout:
                type = TimePickerView.Type.YEAR_MONTH_DAY;
                format = "yyyy-MM-dd";
                Util.alertTimerPicker(this, type, format, new Util.TimerPickerCallBack() {
                    @Override
                    public void onTimeSelect(String date) {
                        tv_birthday.setText(date);
                        tv_birthday_is_null = false;
                        btnToSecondFragment();
                    }
                });
                break;

            case R.id.btn_person_information_submit_first_to_second:
                transaction = manager.beginTransaction();
                transaction.hide(firstFragment);
                transaction.show(secondFragment);
                transaction.commit();
                break;

            case R.id.btn_person_information_submit_second_to_first:
                transaction = manager.beginTransaction();
                transaction.hide(secondFragment);
                transaction.show(firstFragment);
                transaction.commit();
                break;

            case R.id.btn_person_information_submit_second_to_third:
                transaction = manager.beginTransaction();
                transaction.hide(secondFragment);
                transaction.show(thirdFragment);
                transaction.commit();
                break;
            case R.id.person_information_submit_content_address_layout:
                Intent intent = new Intent(this, SelectSiteActivity.class);
                startActivityForResult(intent,ADDRESS);
                break;
            case R.id.person_information_submit_content_character_layout:
                initCharacterPickerView();
                break;
            case R.id.person_information_submit_content_market_layout:
                initMraketPickerView();
                break;
            case R.id.person_information_submit_content_business_layout:
                initBusinessPickerView();
                break;
            case R.id.btn_person_information_submit_third_to_second:
                transaction = manager.beginTransaction();
                transaction.hide(thirdFragment);
                transaction.show(secondFragment);
                transaction.commit();
                break;
            case R.id.btn_person_information_submit_third_to_fourth:
                transaction = manager.beginTransaction();
                transaction.hide(thirdFragment);
                transaction.show(fourthFragment);
                transaction.commit();
                break;
            case R.id.btn_person_information_submit_fourth_to_third:
                transaction = manager.beginTransaction();
                transaction.hide(fourthFragment);
                transaction.show(thirdFragment);
                transaction.commit();
                break;
            case R.id.btn_person_information_submit_submit:
                dialog_submit_success();
                PersonApproveInformationActivity.start(this);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        int current_progress = progressBar.getProgress();
        switch (requestCode) {
            case ADDRESS:
                if(resultCode == RESULT_OK){
                    //double latitute = data.getDoubleExtra("latitude",0);
                    //double longitude = data.getDoubleExtra("longitude",0);
                    String name = data.getStringExtra("name");
                    String location = data.getStringExtra("location");
                    if (name.equals("") && location.equals("")) {
                        if (!tv_address_is_null) {
                            progressBar.setProgress(current_progress - 4);
                            tv_progress.setText(changeProgressToString(current_progress - 4));
                            tv_address_is_null = true;
                        }
                        tv_address.setText("");
                    } else {
                        if (tv_address_is_null) {
                            progressBar.setProgress(current_progress + 4);
                            tv_progress.setText(changeProgressToString(current_progress + 4));
                            tv_address_is_null = false;
                        }
                        tv_address.setText(location);
                    }
                }
                btnToThirdFragment();
                break;
        }
    }

    private void initSexPickerView(){
        sex_pickerView.setOnItemClickListener(new OnItemClickListener(){
            @Override
            public void onSelected(String str) {
                tv_sex.setText(str);
                tv_sex_is_null = false;
            }

            @Override
            public void onCancel() {
                tv_sex.setText("");
                tv_sex_is_null = true;
            }
        });
        sex_pickerView.showCityPicker();
    }

    private void initCharacterPickerView(){
        character_pickerView.setOnItemClickListener(new OnItemClickListener(){
            @Override
            public void onSelected(String str) {
                tv_character.setText(str);
                tv_character_is_null = false;
                btnToThirdFragment();
            }

            @Override
            public void onCancel() {
                tv_character.setText("");
                tv_character_is_null = true;
                btnToThirdFragment();
            }
        });
        character_pickerView.showCityPicker();
    }

    private void initMraketPickerView(){
        market_pivkerView.setOnItemClickListener(new OnItemClickListener(){
            @Override
            public void onSelected(String str) {
                tv_market.setText(str);
                tv_market_is_null = false;
                btnToThirdFragment();
            }

            @Override
            public void onCancel() {
                tv_market.setText("");
                tv_market_is_null = true;
                btnToThirdFragment();
            }
        });
        market_pivkerView.showCityPicker();
    }

    private void initBusinessPickerView(){
        business_pickerView.setOnItemClickListener(new OnItemClickListener(){
            @Override
            public void onSelected(String str) {
                tv_business.setText(str);
                tv_business_is_null = false;
                btnToThirdFragment();
            }

            @Override
            public void onCancel() {
                tv_business.setText("");
                tv_business_is_null = true;
                btnToThirdFragment();
            }
        });
        business_pickerView.showCityPicker();
    }


    private String changeProgressToString(int progress){
        return String.valueOf(progress)+"%";
    }

    private boolean isReayToSecondFragment(){
        boolean is = false;
        if(!et_name_is_null && !tv_sex_is_null && !tv_birthday_is_null && !et_phone_is_null
                && !et_workTime_is_null && !et_background_is_null && !et_profession_is_null){
            is = true;
        }
        return is;
    }

    private boolean isReayToThirdFragment(){
        boolean is = false;
        if(!et_workTime_is_null && !et_department_is_null && !et_position_is_null && !tv_character_is_null
                && !tv_market_is_null && !et_size_is_null && !et_profit_is_null && !tv_business_is_null){
            is = true;
        }
        return is;
    }

    private void btnToSecondFragment(){
        if(isReayToSecondFragment()){
            btn_first_to_second.setEnabled(true);
        }else {
            btn_first_to_second.setEnabled(false);
        }
    }

    private void btnToThirdFragment(){
        if(isReayToThirdFragment()){
            btn_second_to_third.setEnabled(true);
        }else {
            btn_second_to_third.setEnabled(false);
        }
    }

    private void dialog_submit_success(){
        AlertDialog.Builder alterDiaglog = new AlertDialog.Builder(SubmitPersonInformationActivity.this,R.style.DialogStyle);
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
