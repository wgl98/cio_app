package com.cio_app.view.mine.resume.resumeSubmit;


import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cio_app.R;
import com.cio_app.model.ListData;
import com.e.lib_common_ui.base.BaseActivity;
import com.lib_pickerview.Interface.OnCityItemClickListener;
import com.lib_pickerview.Interface.OnItemClickListener;
import com.lib_pickerview.bean.CityBean;
import com.lib_pickerview.bean.DistrictBean;
import com.lib_pickerview.bean.ProvinceBean;
import com.lib_pickerview.citywheel.CityConfig;
import com.lib_pickerview.style.citypickerview.CityPickerView;
import com.lib_pickerview.style.pickerview.PickerView;
import com.lvfq.pickerview.TimePickerView;
import com.lvfq.pickerview.utils.Util;

public class SubmitBaseMessageActivity extends BaseActivity implements View.OnClickListener{

    private EditText et_name;                              //姓名

    private RelativeLayout rl_select_sex;                  //选择性别
    private TextView tv_sex;

    private RelativeLayout rl_select_birthday;             //选择生日
    private TextView tv_birthday;

    private RelativeLayout rl_select_workTime;             //选择开始工作年份
    private TextView tv_workTime;

    private EditText et_phone;                             //手机

    private RelativeLayout rl_select_address;              //选择居住地
    private TextView tv_address;

    private EditText et_email;                             //邮箱

    private EditText et_income;                            //收入

    private RelativeLayout rl_select_public;               //选择是否公开收入
    private TextView tv_public;

    private Button btn_submit_base_message;                //保存信息

    private CityPickerView mPicker = new CityPickerView();  //城市选择器

    private ListData listData = new ListData();
    private PickerView sex_pickerView = new PickerView();
    private PickerView public_pickerView = new PickerView();

    private boolean tv_birthday_is_null = true;
    private boolean tv_sex_is_null = true;
    private boolean tv_workTime_is_null = true;
    private boolean tv_address_is_null = true;
    private boolean tv_public_is_null = true;
    private boolean et_name_is_null = true;
    private boolean et_phone_is_null = true;
    private boolean et_email_is_null = true;
    private boolean et_income_is_null = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_base_message);
        sex_pickerView.init(this,listData.getSexData());
        public_pickerView.init(this,listData.getPublicData());
        mPicker.init(this);
        initView();
    }

    private void initView(){
        findViewById(R.id.btn_back_in_submit_base_message_page_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        findViewById(R.id.btn_back_in_submit_base_message_page).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        et_name = findViewById(R.id.submit_base_message_page_content_name);
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
                BtnToSave();
            }
        });

        et_phone = findViewById(R.id.submit_base_message_page_content_phone);
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
                BtnToSave();
            }
        });

        et_email = findViewById(R.id.submit_base_message_page_content_email);
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
                BtnToSave();
            }
        });

        et_income = findViewById(R.id.submit_base_message_page_content_income);
        et_income.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length() != 0){
                    if(et_income_is_null){
                        et_income_is_null = false;
                    }
                }else {
                    if(!et_income_is_null){
                        et_income_is_null = true;
                    }
                }
                BtnToSave();
            }
        });

        rl_select_birthday = findViewById(R.id.submit_base_message_page_content_birthday_layout);
        rl_select_birthday.setOnClickListener(this);

        tv_birthday = findViewById(R.id.submit_base_message_page_content_birthday);

        rl_select_sex = findViewById(R.id.submit_base_message_page_content_sex_layout);
        rl_select_sex.setOnClickListener(this);

        tv_sex = findViewById(R.id.submit_base_message_page_content_sex);

        rl_select_workTime = findViewById(R.id.submit_base_message_page_content_workTime_layout);
        rl_select_workTime.setOnClickListener(this);

        tv_workTime = findViewById(R.id.submit_base_message_page_content_workTime);

        rl_select_address = findViewById(R.id.submit_base_message_page_content_address_layout);
        rl_select_address.setOnClickListener(this);

        tv_address = findViewById(R.id.submit_base_message_page_content_address);


        rl_select_public = findViewById(R.id.submit_base_message_page_content_public_layout);
        rl_select_public.setOnClickListener(this);

        tv_public = findViewById(R.id.submit_base_message_page_content_public);

        btn_submit_base_message = findViewById(R.id.btn_submit_base_message);
        btn_submit_base_message.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        TimePickerView.Type type = null;
        String format = "";
        switch (v.getId()){
            case R.id.submit_base_message_page_content_birthday_layout:
                type = TimePickerView.Type.YEAR_MONTH_DAY;
                format = "yyyy-MM-dd";
                Util.alertTimerPicker(this, type, format, new Util.TimerPickerCallBack() {
                    @Override
                    public void onTimeSelect(String date) {
                       tv_birthday.setText(date);
                       tv_birthday_is_null = false;
                        BtnToSave();
                    }
                });
                break;
            case R.id.submit_base_message_page_content_sex_layout:
                initSexPickerView();
                break;
            case R.id.submit_base_message_page_content_workTime_layout:
                type = TimePickerView.Type.YEAR;
                format = "yyyy";
                Util.alertTimerPicker(this, type, format, new Util.TimerPickerCallBack() {
                    @Override
                    public void onTimeSelect(String date) {
                        tv_workTime.setText(date);
                        tv_workTime_is_null = false;
                        BtnToSave();
                    }
                });
                break;
            case R.id.submit_base_message_page_content_address_layout:
                initCityPickerView();
                break;
            case R.id.submit_base_message_page_content_public_layout:
                initPublicPickerView();
                break;
            case R.id.btn_submit_base_message:
                Intent intent = new Intent();
                intent.putExtra("name",et_name.getText().toString());
                intent.putExtra("sex",tv_sex.getText());
                intent.putExtra("birthday",tv_birthday.getText());
                intent.putExtra("workTime",tv_workTime.getText());
                intent.putExtra("phone",et_phone.getText().toString());
                intent.putExtra("address",tv_address.getText());
                intent.putExtra("email",et_email.getText().toString());
                intent.putExtra("income",et_income.getText().toString());
                intent.putExtra("public",tv_public.getText());
                Log.d("姓名",et_name.getText().toString());
                setResult(RESULT_OK,intent);
                finish();
                break;
        }
    }

    private void initSexPickerView(){
        sex_pickerView.setOnItemClickListener(new OnItemClickListener(){
            @Override
            public void onSelected(String str) {
                tv_sex.setText(str);
                tv_sex_is_null = false;
                BtnToSave();
            }

            @Override
            public void onCancel() {
                tv_sex.setText("");
                tv_sex_is_null = true;
                BtnToSave();
            }
        });
        sex_pickerView.showCityPicker();
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

                if(tv_address_is_null){
                    tv_address_is_null = false;
                }
                tv_address.setText(province.getName()+city.getName());
                BtnToSave();
                //省份province
                //城市city
                //地区district
            }

            @Override
            public void onCancel() {
                if(!tv_address_is_null){
                    tv_address_is_null = true;
                }
                tv_address.setText("");
                BtnToSave();

                //ToastUtils.showLongToast(TalentsSubmitActivity.this, "已取消");
            }

        });
        //显示
        mPicker.showCityPicker( );
    }

    private void initPublicPickerView(){
        public_pickerView.setOnItemClickListener(new OnItemClickListener(){
            @Override
            public void onSelected(String str) {
               tv_public.setText(str);
               tv_public_is_null = false;
                BtnToSave();
            }

            @Override
            public void onCancel() {
               tv_public.setText("");
               tv_public_is_null = true;
                BtnToSave();
            }
        });
        public_pickerView.showCityPicker();
    }

    private boolean isReayToSave(){
        boolean is = false;
        if(!et_name_is_null && !tv_sex_is_null && !tv_birthday_is_null && !tv_workTime_is_null
                && !et_phone_is_null && !tv_address_is_null && !et_email_is_null && !et_income_is_null
        && !tv_public_is_null){
            is = true;
        }
        return is;
    }

    private void BtnToSave(){
        if(isReayToSave()){
            btn_submit_base_message.setEnabled(true);
        }else {
            btn_submit_base_message.setEnabled(false);
        }
    }

}
