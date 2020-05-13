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
import com.lib_pickerview.Interface.OnCityItemClickListener;
import com.lib_pickerview.Interface.OnItemClickListener;
import com.lib_pickerview.bean.CityBean;
import com.lib_pickerview.bean.DistrictBean;
import com.lib_pickerview.bean.ProvinceBean;
import com.lib_pickerview.citywheel.CityConfig;
import com.lib_pickerview.style.citypickerview.CityPickerView;
import com.lib_pickerview.style.pickerview.PickerView;

public class SubmitIntentMessageActivity extends BaseActivity implements View.OnClickListener{

    private CityPickerView mPicker = new CityPickerView();  //城市选择器
    private PickerView business_picker = new PickerView();
    private ListData listData = new ListData();

    private EditText et_expect_pay;

    private RelativeLayout rl_select_address;
    private TextView tv_expect_address;

    private EditText et_expect_position;

    private RelativeLayout rl_select_expect_business;
    private TextView tv_expect_business;

    private EditText et_description;

    private Button btn_submit;

    private boolean et_expect_pay_is_null = true;
    private boolean tv_expect_address_is_null = true;
    private boolean et_expect_position_is_null = true;
    private boolean tv_expect_business_is_null = true;
    private boolean et_description_is_null = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_intent_message);
        mPicker.init(this);
        business_picker.init(this,listData.getCompanyBusinessData());
        initView();
    }

    private void initView(){
        findViewById(R.id.btn_back_in_submit_intent_message_page_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        findViewById(R.id.btn_back_in_submit_intent_message_page).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        et_expect_pay = findViewById(R.id.submit_intent_message_page_content_expect_pay);
        et_expect_pay.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() != 0){
                    et_expect_pay_is_null = false;
                }else {
                    et_expect_pay_is_null = true;
                }
                BtnToSave();
            }
        });

        rl_select_address = findViewById(R.id.submit_intent_message_page_content_expect_address_layout);
        rl_select_address.setOnClickListener(this);

        tv_expect_address = findViewById(R.id.submit_intent_message_page_content_expect_address);

        et_expect_position = findViewById(R.id.submit_intent_message_page_content_expect_position);
        et_expect_position.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length() != 0){
                    et_expect_position_is_null = false;
                }else{
                    et_expect_position_is_null = true;
                }
                BtnToSave();
            }
        });

        rl_select_expect_business = findViewById(R.id.submit_intent_message_page_content_expect_business_layout);
        rl_select_expect_business.setOnClickListener(this);

        tv_expect_business = findViewById(R.id.submit_intent_message_page_content_expect_business);

        et_description = findViewById(R.id.submit_intent_message_page_content_expect_description);
        et_description.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length() != 0){
                    et_description_is_null = false;
                }else {
                    et_description_is_null = true;
                }
                BtnToSave();
            }
        });

        btn_submit = findViewById(R.id.btn_submit_intent_message);
        btn_submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.submit_intent_message_page_content_expect_address_layout:
                initCityPickerView();
                break;
            case R.id.submit_intent_message_page_content_expect_business_layout:
                initBusinessPicker();
                break;
            case R.id.btn_submit_intent_message:
                Intent intent = new Intent();
                intent.putExtra("pay",et_expect_pay.getText().toString());
                intent.putExtra("address",tv_expect_address.getText());
                intent.putExtra("position",et_expect_position.getText().toString());
                intent.putExtra("business",tv_expect_business.getText());
                intent.putExtra("description",et_description.getText().toString());
                setResult(RESULT_OK,intent);
                finish();
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

                if(tv_expect_address_is_null){
                    tv_expect_address_is_null= false;
                }
                tv_expect_address.setText(province.getName()+city.getName());
                BtnToSave();
                //省份province
                //城市city
                //地区district
            }

            @Override
            public void onCancel() {
                if(!tv_expect_address_is_null){
                    tv_expect_address_is_null = true;
                }
                tv_expect_address.setText("");
                BtnToSave();

                //ToastUtils.showLongToast(TalentsSubmitActivity.this, "已取消");
            }

        });
        //显示
        mPicker.showCityPicker( );
    }

    private void initBusinessPicker(){
        business_picker.setOnItemClickListener(new OnItemClickListener(){
            @Override
            public void onSelected(String str) {
                tv_expect_business.setText(str);
                tv_expect_business_is_null = false;
                BtnToSave();
            }

            @Override
            public void onCancel() {
                tv_expect_business.setText("");
                tv_expect_business_is_null = true;
                BtnToSave();
            }
        });
        business_picker.showCityPicker();
    }

    private boolean isReayToSave(){
        boolean is = false;
        if(!et_expect_pay_is_null && !tv_expect_address_is_null && !et_expect_position_is_null
                && !tv_expect_business_is_null && !et_description_is_null ){
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
