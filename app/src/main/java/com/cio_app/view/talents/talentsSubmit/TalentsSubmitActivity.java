package com.cio_app.view.talents.talentsSubmit;


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
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.cio_app.R;
import com.cio_app.model.ListData;
import com.cio_app.view.select.SelectCompanyCharacterActivity;
import com.cio_app.view.select.SelectSiteActivity;
import com.e.lib_common_ui.base.BaseActivity;
import com.lib_pickerview.Interface.OnCityItemClickListener;
import com.lib_pickerview.Interface.OnItemClickListener;
import com.lib_pickerview.bean.CityBean;
import com.lib_pickerview.bean.DistrictBean;
import com.lib_pickerview.bean.ProvinceBean;
import com.lib_pickerview.citywheel.CityConfig;
import com.lib_pickerview.style.citypickerview.CityPickerView;
import com.lib_pickerview.style.pickerview.PickerView;

import java.util.Timer;
import java.util.TimerTask;

public class TalentsSubmitActivity extends BaseActivity implements View.OnClickListener{

    public static final int SITE = 1;
    public static final int TYPE = 2;

    private FragmentManager manager;
    private FragmentTransaction transaction;

    private TalentsSubmitFirstFragment talentsSubmitFirstFragment;
    private TalentsSubmitSecondFragment talentsSubmitSecondFragment;
    private TalentsSubmitThirdFragment talentsSubmitThirdFragment;

    private CityPickerView mPicker = new CityPickerView();  //城市选择器

    private ListData listData = new ListData();
    private PickerView pickerView = new PickerView();
    private PickerView type_pickerView =  new PickerView();

    private ProgressBar progressBar;
    private TextView tv_show_progress;   //展示进度值


    private Button btn_to_second_fragment_in_first_fragment;
    private Button btn_to_first_fragment;
    private Button btn_to_third_fragment;
    private Button btn_to_second_fragment_in_third_fragment;
    private Button btn_submit;


    private EditText et_name;                    //职位名称
    private RelativeLayout rl_address_layout;    //城市
    private TextView tv_address;
    private RelativeLayout rl_site_layout;       //地址
    private TextView tv_site;
    private RelativeLayout rl_company_type_layout; //公司类型
    private TextView tv_comany_type;
    private RelativeLayout rl_amount_layout;     //招聘人数
    private TextView tv_amount;
    private RelativeLayout rl_character_layout;  //工作性质
    private TextView tv_character;
    private EditText et_price;                   //薪资
    private EditText et_description;             //描述
    private TextView tv_size;                    //字数
    private EditText et_profit;                  //福利
    private EditText et_work_time;               //工作年限
    private EditText et_education;               //学习
    private EditText et_age;                     //年龄
    private EditText et_language;                //语言
    private EditText et_profession;              //专业

    private boolean et_name_is_null = true;
    private boolean tv_address_is_null = true;
    private boolean tv_site_is_null = true;
    private boolean tv_company_type_is_null = true;
    private boolean tv_amount_is_null = true;
    private boolean tv_character_is_null = true;
    private boolean et_price_is_null = true;
    private boolean et_description_is_null = true;
    private boolean et_profit_is_null = true;
    private boolean et_work_time_is_null = true;
    private boolean et_education_is_null = true;
    private boolean et_age_is_null = true;
    private boolean et_language_is_null = true;
    private boolean et_profession_is_null = true;


    public static void start(Context context) {
        Intent intent = new Intent(context, TalentsSubmitActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talents_submit);
        mPicker.init(this);
        pickerView.init(this,listData.getAmountData());
        type_pickerView.init(this,listData.getTypeData());

        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        talentsSubmitFirstFragment=  new TalentsSubmitFirstFragment();
        talentsSubmitSecondFragment = new TalentsSubmitSecondFragment();
        talentsSubmitThirdFragment = new TalentsSubmitThirdFragment();
        transaction.add(R.id.talents_submit_content_layout, talentsSubmitFirstFragment);
        transaction.add(R.id.talents_submit_content_layout,talentsSubmitSecondFragment);
        transaction.add(R.id.talents_submit_content_layout,talentsSubmitThirdFragment);
        transaction.hide(talentsSubmitThirdFragment);
        transaction.hide(talentsSubmitSecondFragment);
        transaction.show(talentsSubmitFirstFragment);
        transaction.commit();

        initView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        initFragmentView();
    }

    private void initView(){
        findViewById(R.id.btn_back_in_talents_submit_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        findViewById(R.id.btn_back_in_talents_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        progressBar = (ProgressBar) findViewById(R.id.progressBar_in_talents_submit);

        tv_show_progress = (TextView) findViewById(R.id.show_progress_in_talents_submit);
        tv_show_progress.setText(changeProgressToString(0));
    }

    private void initFragmentView(){
        View firstView = talentsSubmitFirstFragment.getView();

        et_name = firstView.findViewById(R.id.talents_submit_content_name);
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
                btnToSecondFragmentIsEnable();
            }
        });

        rl_address_layout = firstView.findViewById(R.id.talents_submit_content_address_layout);
        rl_address_layout.setOnClickListener(this);
        tv_address = firstView.findViewById(R.id.talents_submit_content_address);

        rl_site_layout = firstView.findViewById(R.id.talents_submit_content_site_layout);
        rl_site_layout.setOnClickListener(this);
        tv_site = firstView.findViewById(R.id.talents_submit_content_site);

        rl_company_type_layout = firstView.findViewById(R.id.talents_submit_content_company_type_layout);
        rl_company_type_layout.setOnClickListener(this);
        tv_comany_type = firstView.findViewById(R.id.talents_submit_content_company_type);

        rl_amount_layout = firstView.findViewById(R.id.talents_submit_content_amount_layout);
        rl_amount_layout.setOnClickListener(this);
        tv_amount = firstView.findViewById(R.id.talents_submit_content_amount);

        rl_character_layout = firstView.findViewById(R.id.talents_submit_content_character_layout);
        rl_character_layout.setOnClickListener(this);
        tv_character = firstView.findViewById(R.id.talents_submit_content_character);

        et_price = firstView.findViewById(R.id.talents_submit_content_price);
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
                btnToSecondFragmentIsEnable();
            }
        });

        btn_to_second_fragment_in_first_fragment = firstView.findViewById(R.id.btn_talents_submit_next);
        btn_to_second_fragment_in_first_fragment.setOnClickListener(this);

        View secondView = talentsSubmitSecondFragment.getView();

        btn_to_first_fragment = secondView.findViewById(R.id.btn_talents_submit_before);
        btn_to_first_fragment.setOnClickListener(this);

        tv_size = secondView.findViewById(R.id.talents_submit_content_text_size);
        et_description = secondView.findViewById(R.id.talents_submit_content_job_description);
        et_description.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                tv_size.setText(String.valueOf(s.length())+"/150");
                int current_progress = progressBar.getProgress();
                if(s.length() != 0){
                    if(et_description_is_null){
                        progressBar.setProgress(current_progress+30);
                        tv_show_progress.setText(changeProgressToString(current_progress+30));
                        et_description_is_null = false;
                    }
                }else {
                    if(!et_description_is_null){
                        progressBar.setProgress(current_progress-30);
                        tv_show_progress.setText(changeProgressToString(current_progress-30));
                        et_description_is_null = true;
                    }
                }
                if(!et_description_is_null){
                    btn_to_third_fragment.setEnabled(true);
                }else {
                    btn_to_third_fragment.setEnabled(false);
                }
            }
        });

        btn_to_third_fragment = secondView.findViewById(R.id.btn_talents_submit_next_in_second);
        btn_to_third_fragment.setOnClickListener(this);

        View thirdView = talentsSubmitThirdFragment.getView();

        et_profit = thirdView.findViewById(R.id.talents_submit_content_profit);
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
                        progressBar.setProgress(current_progress+5);
                        tv_show_progress.setText(changeProgressToString(current_progress+5));
                        et_profit_is_null = false;
                    }
                }else {
                    if(!et_profit_is_null){
                        progressBar.setProgress(current_progress-5);
                        tv_show_progress.setText(changeProgressToString(current_progress-5));
                        et_profit_is_null = true;
                    }
                }
                if(isReadyToSubmit()){
                    btn_submit.setEnabled(true);
                }else {
                    btn_submit.setEnabled(false);
                }
            }
        });

        et_work_time = thirdView.findViewById(R.id.talents_submit_content_work_time);
        et_work_time.addTextChangedListener(new TextWatcher() {
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
                    if(et_work_time_is_null){
                        progressBar.setProgress(current_progress+5);
                        tv_show_progress.setText(changeProgressToString(current_progress+5));
                        et_work_time_is_null = false;
                    }
                }else {
                    if(!et_work_time_is_null){
                        progressBar.setProgress(current_progress-5);
                        tv_show_progress.setText(changeProgressToString(current_progress-5));
                        et_work_time_is_null = true;
                    }
                }
                if(isReadyToSubmit()){
                    btn_submit.setEnabled(true);
                }else {
                    btn_submit.setEnabled(false);
                }
            }
        });

        et_education = thirdView.findViewById(R.id.talents_submit_content_education);
        et_education.addTextChangedListener(new TextWatcher() {
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
                    if(et_education_is_null){
                        progressBar.setProgress(current_progress+5);
                        tv_show_progress.setText(changeProgressToString(current_progress+5));
                        et_education_is_null = false;
                    }
                }else {
                    if(!et_education_is_null){
                        progressBar.setProgress(current_progress-5);
                        tv_show_progress.setText(changeProgressToString(current_progress-5));
                        et_education_is_null = true;
                    }
                }
                if(isReadyToSubmit()){
                    btn_submit.setEnabled(true);
                }else {
                    btn_submit.setEnabled(false);
                }
            }
        });

        et_age = thirdView.findViewById(R.id.talents_submit_content_age);
        et_age.addTextChangedListener(new TextWatcher() {
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
                    if(et_age_is_null){
                        progressBar.setProgress(current_progress+5);
                        tv_show_progress.setText(changeProgressToString(current_progress+5));
                        et_age_is_null = false;
                    }
                }else {
                    if(!et_age_is_null){
                        progressBar.setProgress(current_progress-5);
                        tv_show_progress.setText(changeProgressToString(current_progress-5));
                        et_age_is_null = true;
                    }
                }
                if(isReadyToSubmit()){
                    btn_submit.setEnabled(true);
                }else {
                    btn_submit.setEnabled(false);
                }
            }
        });

        et_language = thirdView.findViewById(R.id.talents_submit_content_language);
        et_language.addTextChangedListener(new TextWatcher() {
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
                    if(et_language_is_null){
                        progressBar.setProgress(current_progress+5);
                        tv_show_progress.setText(changeProgressToString(current_progress+5));
                        et_language_is_null = false;
                    }
                }else {
                    if(!et_language_is_null){
                        progressBar.setProgress(current_progress-5);
                        tv_show_progress.setText(changeProgressToString(current_progress-5));
                        et_language_is_null = true;
                    }
                }
                if(isReadyToSubmit()){
                    btn_submit.setEnabled(true);
                }else {
                    btn_submit.setEnabled(false);
                }
            }
        });

        et_profession = thirdView.findViewById(R.id.talents_submit_content_profession);
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
                        tv_show_progress.setText(changeProgressToString(current_progress+5));
                        et_profession_is_null = false;
                    }
                }else {
                    if(!et_profession_is_null){
                        progressBar.setProgress(current_progress-5);
                        tv_show_progress.setText(changeProgressToString(current_progress-5));
                        et_profession_is_null = true;
                    }
                }
                if(isReadyToSubmit()){
                    btn_submit.setEnabled(true);
                }else {
                    btn_submit.setEnabled(false);
                }
            }
        });

        btn_to_second_fragment_in_third_fragment = thirdView.findViewById(R.id.btn_talents_submit_before_in_third);
        btn_to_second_fragment_in_third_fragment.setOnClickListener(this);

        btn_submit = thirdView.findViewById(R.id.btn_talents_submit_submit);
        btn_submit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.talents_submit_content_address_layout:
                initCityPickerView();
                break;
            case R.id.talents_submit_content_site_layout:
                Intent intent = new Intent(this, SelectSiteActivity.class);
                startActivityForResult(intent,SITE);
                break;
            case R.id.talents_submit_content_company_type_layout:
                Intent intent1 = new Intent(this, SelectCompanyCharacterActivity.class);
                startActivityForResult(intent1,TYPE);
                break;
            case R.id.talents_submit_content_amount_layout:
                initRecruitAmountPickerView();
                break;
            case R.id.talents_submit_content_character_layout:
                initTypePickerView();
                break;
            case R.id.btn_talents_submit_next:
                transaction = manager.beginTransaction();
                transaction.hide(talentsSubmitFirstFragment);
                transaction.hide(talentsSubmitThirdFragment);
                transaction.show(talentsSubmitSecondFragment);
                transaction.commit();
                break;
            case R.id.btn_talents_submit_before:
                transaction = manager.beginTransaction();
                transaction.hide(talentsSubmitSecondFragment);
                transaction.hide(talentsSubmitThirdFragment);
                transaction.show(talentsSubmitFirstFragment);
                transaction.commit();
                break;
            case R.id.btn_talents_submit_next_in_second:
                transaction = manager.beginTransaction();
                transaction.hide(talentsSubmitSecondFragment);
                transaction.hide(talentsSubmitFirstFragment);
                transaction.show(talentsSubmitThirdFragment);
                transaction.commit();
                break;
            case R.id.btn_talents_submit_before_in_third:
                transaction = manager.beginTransaction();
                transaction.hide(talentsSubmitThirdFragment);
                transaction.hide(talentsSubmitFirstFragment);
                transaction.show(talentsSubmitSecondFragment);
                transaction.commit();
                break;
            case R.id.btn_talents_submit_submit:
                dialog_submit_success();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        int current_progress = progressBar.getProgress();
        switch (requestCode){
            case SITE:
                //double latitute = data.getDoubleExtra("latitude",0);
                //double longitude = data.getDoubleExtra("longitude",0);
                String name = data.getStringExtra("name");
                String location = data.getStringExtra("location");
                if(name.equals("") && location.equals("")){
                    if(!tv_site_is_null){
                        progressBar.setProgress(current_progress-4);
                        tv_show_progress.setText(changeProgressToString(current_progress-4));
                        tv_site_is_null = true;
                    }
                    tv_site.setText("");
                }else {
                    if(tv_site_is_null){
                        progressBar.setProgress(current_progress+4);
                        tv_show_progress.setText(changeProgressToString(current_progress+4));
                        tv_site_is_null = false;
                    }
                    tv_site.setText(location);
                }
                btnToSecondFragmentIsEnable();
                break;
            case TYPE:
                String first = data.getStringExtra("first");
                String second = data.getStringExtra("second");
                if(first.equals("") && second.equals("")){
                    if(!tv_company_type_is_null){
                        progressBar.setProgress(current_progress-4);
                        tv_show_progress.setText(changeProgressToString(current_progress-4));
                        tv_company_type_is_null = true;
                    }
                    tv_comany_type.setText("");
                }else if(second.equals("")) {
                   if(tv_company_type_is_null){
                       progressBar.setProgress(current_progress+4);
                       tv_show_progress.setText(changeProgressToString(current_progress+4));
                       tv_company_type_is_null = false;
                   }
                   tv_comany_type.setText(first);
                }else {
                    if(tv_company_type_is_null){
                        progressBar.setProgress(current_progress+4);
                        tv_show_progress.setText(changeProgressToString(current_progress+4));
                        tv_company_type_is_null = false;
                    }
                    tv_comany_type.setText(first+"；"+second);
                }
                btnToSecondFragmentIsEnable();
                break;
            default:
                break;
        }
    }

    private void initCityPickerView(){
        CityConfig cityConfig = new CityConfig.Builder()
                .title(" ")//标题
                .titleTextSize(18)//标题文字大小
                .titleTextColor("#585858")//标题文字颜  色
                .titleBackgroundColor("#FFFFFF")//标题栏背景色
                .confirTextColor("#3B70FF")//确认按钮文字颜色
                .confirmText("确认")//确认按钮文字
                .confirmTextSize(18)//确认按钮文字大小
                .cancelTextColor("#1E1E1E")//取消按钮文字颜色
                .cancelText("取消")//取消按钮文字
                .cancelTextSize(18)//取消按钮文字大小
                .setCityWheelType(CityConfig.WheelType.PRO_CITY)//显示类，只显示省份一级，显示省市两级还是显示省市区三级
                .showBackground(true)//是否显示半透明背景
                .visibleItemsCount(5)//显示item的数量
                .province("广东省")//默认显示的省份
                .city("东莞市")//默认显示省份下面的城市
                .provinceCyclic(false)//省份滚轮是否可以循环滚动
                .cityCyclic(false)//城市滚轮是否可以循环滚动
                .districtCyclic(false)//区县滚轮是否循环滚动
                .setCustomItemLayout(R.layout.item)//自定义item的布局
                .setCustomItemTextViewId(R.id.item_name_tv)//自定义item布局里面的textViewid
                .drawShadows(false)//滚轮不显示模糊效果
                .setLineColor("#585858")//中间横线的颜色
                .setLineHeigh(3)//中间横线的高度
                .setShowGAT(false)//是否显示港澳台数据，默认不显示
                .build();
        mPicker.setConfig(cityConfig);
        //监听选择点击事件及返回结果
        mPicker.setOnCityItemClickListener(new OnCityItemClickListener() {
            @Override
            public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {

                int current_progress = progressBar.getProgress();
                if(tv_address_is_null){
                    progressBar.setProgress(current_progress+4);
                    tv_show_progress.setText(changeProgressToString(current_progress+4));
                    tv_address_is_null = false;
                }
                tv_address.setText(province.getName()+city.getName());
                btnToSecondFragmentIsEnable();
                //省份province
                //城市city
                //地区district
            }

            @Override
            public void onCancel() {
                int current_progress = progressBar.getProgress();
                if(!tv_address_is_null){
                    progressBar.setProgress(current_progress-4);
                    tv_show_progress.setText(changeProgressToString(current_progress-4));
                    tv_address_is_null = true;
                }
                tv_address.setText("");
                btnToSecondFragmentIsEnable();

                //ToastUtils.showLongToast(TalentsSubmitActivity.this, "已取消");
            }

        });
        //显示
        mPicker.showCityPicker( );
    }

    private void initRecruitAmountPickerView(){
        pickerView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onSelected(String str) {
                int current_progress = progressBar.getProgress();
                if(tv_amount_is_null){
                    progressBar.setProgress(current_progress+4);
                    tv_show_progress.setText(changeProgressToString(current_progress+4));
                    tv_amount_is_null = false;
                }
                tv_amount.setText(str);
                btnToSecondFragmentIsEnable();
            }

            @Override
            public void onCancel() {
                int current_progress = progressBar.getProgress();
                if(!tv_amount_is_null){
                    progressBar.setProgress(current_progress-4);
                    tv_show_progress.setText(changeProgressToString(current_progress-4));
                    tv_amount_is_null = true;
                }
                tv_amount.setText("");
                btnToSecondFragmentIsEnable();
            }
        });

        pickerView.showCityPicker();
    }

    private void initTypePickerView(){
        type_pickerView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onSelected(String str) {
                int current_progress = progressBar.getProgress();
                if(tv_character_is_null){
                    progressBar.setProgress(current_progress+4);
                    tv_show_progress.setText(changeProgressToString(current_progress+4));
                    tv_character_is_null = false;
                }
                tv_character.setText(str);
                btnToSecondFragmentIsEnable();
            }

            @Override
            public void onCancel() {
                int current_progress = progressBar.getProgress();
                if(!tv_character_is_null){
                    progressBar.setProgress(current_progress-4);
                    tv_show_progress.setText(changeProgressToString(current_progress-4));
                    tv_character_is_null = true;
                }
                tv_character.setText("");
                btnToSecondFragmentIsEnable();
            }
        });

        type_pickerView.showCityPicker();
    }

    private String changeProgressToString(int progress){
        return String.valueOf(progress)+"%";
    }

    private boolean isReadyToSecondFragmnet(){
        boolean is = false;
        if(!et_name_is_null && !tv_site_is_null && !tv_address_is_null && !tv_company_type_is_null
        && !tv_amount_is_null && !tv_character_is_null && !et_price_is_null){
            is = true;
        }
        Log.d("是否","***************");
        Log.d("是否",String.valueOf(is));
        Log.d("是否name",String.valueOf(et_name_is_null));
        Log.d("是否site",String.valueOf(tv_site_is_null));
        Log.d("是否address",String.valueOf(tv_address_is_null));
        Log.d("是否type",String.valueOf(tv_company_type_is_null));
        Log.d("是否amount",String.valueOf(tv_amount_is_null));
        Log.d("是否character",String.valueOf(tv_character_is_null));
        Log.d("是否price",String.valueOf(et_price_is_null));
        return is;
    }

    private boolean isReadyToSubmit(){
        boolean is = false;
        if(!et_profit_is_null && !et_work_time_is_null && !et_education_is_null && !et_age_is_null
        && !et_language_is_null && !et_profession_is_null){
            is = true;
        }
        return is;
    }

    private void btnToSecondFragmentIsEnable(){
        if(isReadyToSecondFragmnet()){
            btn_to_second_fragment_in_first_fragment.setEnabled(true);
        }else {
            btn_to_second_fragment_in_first_fragment.setEnabled(false);
        }
    }

    private void dialog_submit_success(){
        AlertDialog.Builder alterDiaglog = new AlertDialog.Builder(TalentsSubmitActivity.this,R.style.DialogStyle);
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
