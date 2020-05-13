package com.cio_app.view.mine.resume;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.Nullable;

import com.cio_app.R;
import com.cio_app.view.mine.resume.resumeList.HasReceicedResumeActivity;
import com.cio_app.view.mine.resume.resumeList.HasSubmitResumeActivity;
import com.cio_app.view.mine.resume.resumeSubmit.SubmitBaseMessageActivity;
import com.cio_app.view.mine.resume.resumeSubmit.SubmitEducationMessageActivity;
import com.cio_app.view.mine.resume.resumeSubmit.SubmitIntentMessageActivity;
import com.cio_app.view.mine.resume.resumeSubmit.SubmitWorkMessageActivity;
import com.e.lib_common_ui.base.BaseActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MineResumeActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener,View.OnClickListener {


    public static final int BASE = 1;
    public static final int EDUCATION = 2;
    public static final int WORK = 3;
    public static final int INTENT = 4;


    private ToggleButton tb;
    private TextView tv_publish_status;

    private LinearLayout ll_add_base_message;
    private LinearLayout ll_add_education_message;
    private LinearLayout ll_add_work_message;
    private LinearLayout ll_add_intent_message;

    private LinearLayout ll_base_message;
    private TextView tv_name;
    private TextView tv_sex;
    private TextView tv_age;
    private TextView tv_workAge;
    private TextView tv_address;
    private TextView tv_phone;
    private TextView tv_email;
    private TextView tv_income;
    private LinearLayout ll_income_layout;

    private LinearLayout ll_education_message;
    private TextView tv_education_time;
    private TextView tv_education_school;
    private TextView tv_education_background;
    private TextView tv_education_profession;

    private LinearLayout ll_work_message;
    private TextView tv_work_position;
    private TextView tv_work_time;
    private TextView tv_work_department;
    private TextView tv_work_company;
    private TextView tv_work_character;
    private TextView tv_work_business;
    private TextView tv_work_description;

    private LinearLayout ll_intent_message;
    private TextView tv_expect_position;
    private TextView tv_expect_pay;
    private TextView tv_expect_address;
    private TextView tv_expect_business;
    private TextView tv_description;

    private TextView tv_show_invite_message;
    private TextView tv_show_submit_message;


    public static void start(Context context) {
        Intent intent = new Intent(context, MineResumeActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_resume);

        initView();
    }

    private void initView(){
        findViewById(R.id.btn_back_in_mine_resume_page_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        findViewById(R.id.btn_back_in_mine_resume_page).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        tb = findViewById(R.id.toggleBtn_in_mine_resume);
        tb.setOnCheckedChangeListener(this);
        tv_publish_status = findViewById(R.id.mine_resume_publish_status);

        ll_add_base_message = findViewById(R.id.mine_resume_tag_base_item);
        ll_add_base_message.setOnClickListener(this);

        ll_add_education_message = findViewById(R.id.mine_resume_tag_education_item);
        ll_add_education_message.setOnClickListener(this);

        ll_add_work_message = findViewById(R.id.mine_resume_tag_work_item);
        ll_add_work_message.setOnClickListener(this);

        ll_add_intent_message = findViewById(R.id.mine_resume_tag_intent_item);
        ll_add_intent_message.setOnClickListener(this);

        ll_base_message = findViewById(R.id.mine_resume_base_item);
        tv_name = findViewById(R.id.mine_resume_item_base_name);
        tv_sex = findViewById(R.id.mine_resume_item_base_sex);
        tv_workAge = findViewById(R.id.mine_resume_item_base_workAge);
        tv_address = findViewById(R.id.mine_resume_item_base_address);
        tv_phone = findViewById(R.id.mine_resume_item_base_phone);
        tv_email = findViewById(R.id.mine_resume_item_base_email);
        ll_income_layout = findViewById(R.id.mine_resume_item_base_income_layout);
        tv_income = findViewById(R.id.mine_resume_item_base_income);
        tv_age = findViewById(R.id.mine_resume_item_base_age);

        ll_education_message = findViewById(R.id.mine_resume_education_item);
        tv_education_time = findViewById(R.id.mine_resume_item_education_time);
        tv_education_school = findViewById(R.id.mine_resume_item_education_address);
        tv_education_background = findViewById(R.id.mine_resume_item_education_educate_state);
        tv_education_profession = findViewById(R.id.mine_resume_item_education_profession);

        ll_work_message = findViewById(R.id.mine_resume_work_item);
        tv_work_time = findViewById(R.id.mine_resume_tag_work_item_work_time);
        tv_work_position = findViewById(R.id.mine_resume_education_item_position);
        tv_work_department = findViewById(R.id.mine_resume_education_item_department);
        tv_work_company = findViewById(R.id.mine_resume_education_item_company);
        tv_work_character = findViewById(R.id.mine_resume_education_item_company_character);
        tv_work_business = findViewById(R.id.mine_resume_education_item_company_business);
        tv_work_description = findViewById(R.id.mine_resume_education_item_work_description);

        ll_intent_message = findViewById(R.id.mine_resume_intent_item);
        tv_expect_position = findViewById(R.id.mine_resume_intent_item_expect_position);
        tv_expect_pay = findViewById(R.id.mine_resume_intent_item_expect_pay);
        tv_expect_address = findViewById(R.id.mine_resume_intent_item_expect_address);
        tv_expect_business = findViewById(R.id.mine_resume_intent_item_expect_business);
        tv_description = findViewById(R.id.mine_resume_intent_item_evaluate);

        tv_show_invite_message = findViewById(R.id.btn_in_mine_resume_receive_invite);
        tv_show_invite_message.setOnClickListener(this);

        tv_show_submit_message = findViewById(R.id.btn_in_mine_resume_has_submit);
        tv_show_submit_message.setOnClickListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked){
            tb.setBackgroundResource(R.mipmap.turn_on);
            tv_publish_status.setText("发布中");
            tv_publish_status.setTextColor(getResources().getColor(R.color.colorBlue));
        }else {
            tb.setBackgroundResource(R.mipmap.turn_off);
            tv_publish_status.setText("未发布");
            tv_publish_status.setTextColor(getResources().getColor(R.color.colorBlack));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mine_resume_tag_base_item:
                Intent intent = new Intent(MineResumeActivity.this, SubmitBaseMessageActivity.class);
                startActivityForResult(intent,BASE);
                break;
            case R.id.mine_resume_tag_education_item:
                Intent intent1 = new Intent(MineResumeActivity.this, SubmitEducationMessageActivity.class);
                startActivityForResult(intent1,EDUCATION);
                break;
            case R.id.mine_resume_tag_work_item:
                Intent intent2 = new Intent(MineResumeActivity.this, SubmitWorkMessageActivity.class);
                startActivityForResult(intent2,WORK);
                break;
            case R.id.mine_resume_tag_intent_item:
                Intent intent3 = new Intent(MineResumeActivity.this, SubmitIntentMessageActivity.class);
                startActivityForResult(intent3,INTENT);
                break;
            case R.id.btn_in_mine_resume_receive_invite:
                HasReceicedResumeActivity.start(this);
                break;
            case R.id.btn_in_mine_resume_has_submit:
                HasSubmitResumeActivity.start(this);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case BASE:
                if (resultCode == RESULT_OK){
                    String name = data.getStringExtra("name");
                    String sex = data.getStringExtra("sex");
                    String birthday = data.getStringExtra("birthday");
                    String workTime = data.getStringExtra("workTime");
                    String phone = data.getStringExtra("phone");
                    String address = data.getStringExtra("address");
                    String email = data.getStringExtra("email");
                    String income = data.getStringExtra("income");
                    String public_tag = data.getStringExtra("public");
                    ll_add_base_message.setVisibility(View.GONE);
                    ll_base_message.setVisibility(View.VISIBLE);
                    tv_name.setText(name);
                    tv_sex.setText(sex);
                    tv_age.setText(getAge(birthday)+"岁");
                    tv_workAge.setText(getAge(workTime)+"年");
                    tv_address.setText(address);
                    tv_phone.setText(phone);
                    tv_email.setText(email);
                    if(public_tag.equals("是")){
                        tv_income.setText(income);
                    }else {
                        ll_income_layout.setVisibility(View.GONE);
                    }
                }
                break;
            case EDUCATION:
                if (resultCode == RESULT_OK){
                    String beginTime = data.getStringExtra("beginTime");
                    String finishTime = data.getStringExtra("finishTime");
                    String background = data.getStringExtra("background");
                    String school = data.getStringExtra("school");
                    String profession = data.getStringExtra("profession");
                    ll_add_education_message.setVisibility(View.GONE);
                    ll_education_message.setVisibility(View.VISIBLE);
                    tv_education_time.setText(beginTime+"-"+finishTime);
                    tv_education_school.setText(school);
                    tv_education_background.setText(background);
                    tv_education_profession.setText(profession);
                }
                break;
            case WORK:
                if(resultCode == RESULT_OK){
                    String beginTime = data.getStringExtra("beginTime");
                    String finishTime = data.getStringExtra("finishTime");
                    String name = data.getStringExtra("name");
                    String character = data.getStringExtra("character");
                    String business = data.getStringExtra("business");
                    String function = data.getStringExtra("function");
                    String department = data.getStringExtra("department");
                    String position = data.getStringExtra("position");
                    String description = data.getStringExtra("description");
                    ll_add_work_message.setVisibility(View.GONE);
                    ll_work_message.setVisibility(View.VISIBLE);
                    tv_work_time.setText(beginTime+"-"+finishTime);
                    tv_work_company.setText(name);
                    tv_work_character.setText(character);
                    tv_work_business.setText(business);
                    tv_work_position.setText(function);
                    tv_work_department.setText(department);
                    tv_work_description.setText(description);
                }
                break;
            case INTENT:
                if(resultCode == RESULT_OK){
                    String pay = data.getStringExtra("pay");
                    String address = data.getStringExtra("address");
                    String position = data.getStringExtra("position");
                    String business = data.getStringExtra("business");
                    String description = data.getStringExtra("description");
                    ll_intent_message.setVisibility(View.VISIBLE);
                    ll_add_intent_message.setVisibility(View.GONE);
                    tv_expect_position.setText(position);
                    tv_expect_pay.setText(pay);
                    tv_expect_address.setText(address);
                    tv_expect_business.setText(business);
                    tv_description.setText(description);
                }
                break;
        }
    }

    private String getAge(String birthday){
        int age = 0;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str  = formatter.format(curDate);
        String bir_year = birthday.substring(0,4);
        Log.d("年",bir_year);
        age = Integer.parseInt(str) - Integer.parseInt(bir_year) + 1;
        return String.valueOf(age);
    }
}
