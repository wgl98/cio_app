package com.cio_app.view.mine.member.companyMemberSubmit;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cio_app.R;
import com.cio_app.view.mine.member.companyMemberSubmit.adapter.LicenseAdapter;
import com.cio_app.view.select.selectCompanyType.SelectCompanyTypeActivity;
import com.e.lib_common_ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class SubmitCompanyInformationActivity extends BaseActivity implements View.OnClickListener{

    public static final int TYPE = 1;

    public static final int CHOOSE_PHOTO = 2;

    private ProgressBar progressBar;
    private TextView tv_progress;

    private FragmentManager manager;
    private FragmentTransaction transaction;

    private EditText et_unit;
    private EditText et_address;
    private EditText et_name;
    private EditText et_position;
    private EditText et_phone;
    private EditText et_email;

    private EditText et_introduce;
    private TextView tv_intrdocue_text_amount;

    private EditText et_example;
    private TextView tv_example_text_amount;

    private RelativeLayout rl_select_type;
    private TextView tv_type;

    private ListView license_list;
    private LicenseAdapter mAdapter;
    private List<String> list = new ArrayList<>();

    private ImageButton btn_add_license;

    private Button btn_first_to_second;
    private Button btn_second_to_first;
    private Button btn_second_to_third;
    private Button btn_third_to_second;
    private Button btn_third_to_fourth;
    private Button btn_fourth_to_third;
    private Button btn_submit;

    private boolean et_unit_is_null = true;
    private boolean et_address_is_null = true;
    private boolean et_name_is_null = true;
    private boolean tv_type_is_null = true;
    private boolean et_position_is_null = true;
    private boolean et_phone_is_null = true;
    private boolean et_email_is_null = true;
    private boolean et_introduce_is_null = true;
    private boolean et_example_is_null = true;
    private boolean img_license_is_null = true;

    private SubmitCompanyInformationFirstFragment firstFragment;
    private SubmitCompanyInformationSecondFragment secondFragment;
    private SubmitCompanyInformationThirdFragment thirdFragment;
    private SubmitCompanyInformationFourthFragment fourthFragment;

    public static void start(Context context) {
        Intent intent = new Intent(context, SubmitCompanyInformationActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_company_information);

        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        firstFragment = new SubmitCompanyInformationFirstFragment();
        secondFragment = new SubmitCompanyInformationSecondFragment();
        thirdFragment = new SubmitCompanyInformationThirdFragment();
        fourthFragment = new SubmitCompanyInformationFourthFragment();
        transaction.add(R.id.company_information_submit_content_layout,firstFragment);
        transaction.add(R.id.company_information_submit_content_layout,secondFragment);
        transaction.add(R.id.company_information_submit_content_layout,thirdFragment);
        transaction.add(R.id.company_information_submit_content_layout,fourthFragment);
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
        findViewById(R.id.btn_back_in_company_information_submit_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        findViewById(R.id.btn_back_in_company_information_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        progressBar = findViewById(R.id.progressBar_in_company_information_submit);
        tv_progress = findViewById(R.id.show_progress_in_company_information_submit);
    }

    private void initFragmentView(){
        View firstView = firstFragment.getView();

        et_unit = firstView.findViewById(R.id.company_information_submit_content_unit);
        et_unit.addTextChangedListener(new TextWatcher() {
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
                    if(et_unit_is_null){
                        progressBar.setProgress(current_progress+5);
                        tv_progress.setText(changeProgressToString(current_progress+5));
                        et_unit_is_null = false;
                    }
                }else {
                    if(!et_unit_is_null){
                        progressBar.setProgress(current_progress-5);
                        tv_progress.setText(changeProgressToString(current_progress-5));
                        et_unit_is_null = true;
                    }
                }
                btnToSecondFragment();
            }
        });

        et_address = firstView.findViewById(R.id.company_information_submit_content_address);
        et_address.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length() != 0){
                    et_address_is_null = false;
                }else {
                    et_address_is_null = true;
                }
                btnToSecondFragment();
            }
        });

        et_name = findViewById(R.id.company_information_submit_content_name);
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

        et_position = firstView.findViewById(R.id.company_information_submit_content_position);
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
                        progressBar.setProgress(current_progress+5);
                        tv_progress.setText(changeProgressToString(current_progress+5));
                        et_position_is_null = false;
                    }
                }else {
                    if(!et_position_is_null){
                        progressBar.setProgress(current_progress-5);
                        tv_progress.setText(changeProgressToString(current_progress-5));
                        et_position_is_null = true;
                    }
                }
                btnToSecondFragment();
            }
        });

        et_phone = firstView.findViewById(R.id.company_information_submit_content_phone);
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

        et_email = firstView.findViewById(R.id.company_information_submit_content_email);
        et_email.addTextChangedListener(new TextWatcher() {
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
                    if(et_email_is_null){
                        progressBar.setProgress(current_progress+5);
                        tv_progress.setText(changeProgressToString(current_progress+5));
                        et_email_is_null = false;
                    }
                }else {
                    if(!et_email_is_null){
                        progressBar.setProgress(current_progress-5);
                        tv_progress.setText(changeProgressToString(current_progress-5));
                        et_email_is_null = true;
                    }
                }
                btnToSecondFragment();
            }
        });
        rl_select_type = firstView.findViewById(R.id.company_information_submit_content_type_layout);
        rl_select_type.setOnClickListener(this);
        tv_type = firstView.findViewById(R.id.company_information_submit_content_type);

        btn_first_to_second = firstView.findViewById(R.id.btn_company_information_submit_first_to_second);
        btn_first_to_second.setOnClickListener(this);

        View secondView = secondFragment.getView();
        et_introduce = secondView.findViewById(R.id.company_information_submit_content_introduce);
        et_introduce.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                tv_intrdocue_text_amount.setText(String.valueOf(s.length())+"/150");
                int current_progress = progressBar.getProgress();
                if(s.length() != 0){
                    if(et_introduce_is_null){
                        progressBar.setProgress(current_progress+25);
                        tv_progress.setText(changeProgressToString(current_progress+25));
                        et_introduce_is_null = false;
                    }
                }else {
                    if(!et_introduce_is_null){
                        progressBar.setProgress(current_progress-25);
                        tv_progress.setText(changeProgressToString(current_progress-25));
                        et_introduce_is_null = true;
                    }
                }
                if(!et_introduce_is_null){
                    btn_second_to_third.setEnabled(true);
                }else {
                    btn_second_to_third.setEnabled(false);
                }
            }
        });

        tv_intrdocue_text_amount = secondView.findViewById(R.id.company_information_submit_content_introduce_text_amount);
        btn_second_to_first = secondView.findViewById(R.id.btn_company_information_submit_second_to_first);
        btn_second_to_first.setOnClickListener(this);
        btn_second_to_third = secondView.findViewById(R.id.btn_company_information_submit_second_to_third);
        btn_second_to_third.setOnClickListener(this);

        View thirdView = thirdFragment.getView();
        et_example = thirdView.findViewById(R.id.company_information_submit_content_example);
        et_example.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                tv_example_text_amount.setText(String.valueOf(s.length())+"/150");
                int current_progress = progressBar.getProgress();
                if(s.length() != 0){
                    if(et_example_is_null){
                        progressBar.setProgress(current_progress+25);
                        tv_progress.setText(changeProgressToString(current_progress+25));
                        et_example_is_null = false;
                    }
                }else {
                    if(!et_example_is_null){
                        progressBar.setProgress(current_progress-25);
                        tv_progress.setText(changeProgressToString(current_progress-25));
                        et_example_is_null = true;
                    }
                }
                if(!et_example_is_null){
                    btn_third_to_fourth.setEnabled(true);
                }else {
                    btn_third_to_fourth.setEnabled(false);
                }
            }
        });

        tv_example_text_amount = thirdView.findViewById(R.id.company_information_submit_content_example_text_amount);
        btn_third_to_second = thirdView.findViewById(R.id.btn_company_information_submit_third_to_second);
        btn_third_to_second.setOnClickListener(this);
        btn_third_to_fourth = thirdView.findViewById(R.id.btn_company_information_submit_third_to_fourth);
        btn_third_to_fourth.setOnClickListener(this);

        View fourthView = fourthFragment.getView();
        btn_fourth_to_third = fourthView.findViewById(R.id.btn_company_information_submit_fourth_to_third);
        btn_fourth_to_third.setOnClickListener(this);
        btn_submit = fourthView.findViewById(R.id.btn_company_information_submit_submit);
        btn_submit.setOnClickListener(this);
        btn_add_license = fourthView.findViewById(R.id.btn_add_license_in_company_information_submit);
        btn_add_license.setOnClickListener(this);
        license_list = fourthView.findViewById(R.id.list_license);
        setListViewHeightBasedOnChilds(license_list);
        mAdapter = new LicenseAdapter(this,R.layout.list_item_img_layout,list);
        license_list.setAdapter(mAdapter);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.company_information_submit_content_type_layout:
                Intent intent = new Intent(this, SelectCompanyTypeActivity.class);
                startActivityForResult(intent,TYPE);
                break;
            case R.id.btn_company_information_submit_first_to_second:
                transaction = manager.beginTransaction();
                transaction.hide(firstFragment);
                transaction.show(secondFragment);
                transaction.commit();
                break;
            case R.id.btn_company_information_submit_second_to_first:
                transaction = manager.beginTransaction();
                transaction.hide(secondFragment);
                transaction.show(firstFragment);
                transaction.commit();
                break;
            case R.id.btn_company_information_submit_second_to_third:
                transaction = manager.beginTransaction();
                transaction.hide(secondFragment);
                transaction.show(thirdFragment);
                transaction.commit();
                break;
            case R.id.btn_company_information_submit_third_to_second:
                transaction = manager.beginTransaction();
                transaction.show(secondFragment);
                transaction.hide(thirdFragment);
                transaction.commit();
                break;
            case R.id.btn_company_information_submit_third_to_fourth:
                transaction = manager.beginTransaction();
                transaction.hide(thirdFragment);
                transaction.show(fourthFragment);
                transaction.commit();
                break;
            case R.id.btn_company_information_submit_fourth_to_third:
                transaction = manager.beginTransaction();
                transaction.show(thirdFragment);
                transaction.hide(fourthFragment);
                transaction.commit();
                break;
            case R.id.btn_add_license_in_company_information_submit:
                if(ContextCompat.checkSelfPermission(SubmitCompanyInformationActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(SubmitCompanyInformationActivity.this,new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                }else {
                    openAlbum();
                }
                if(!img_license_is_null){
                    btn_submit.setEnabled(true);
                }else {
                    btn_submit.setEnabled(false);
                }
                break;
            case R.id.btn_company_information_submit_submit:
                dialog_submit_success();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        int current_progress = progressBar.getProgress();
        switch (requestCode){
            case TYPE:
                if(resultCode == RESULT_OK){
                    String str = data.getStringExtra("type");
                    tv_type.setText(str);
                    tv_type_is_null = false;
                }
                btnToSecondFragment();
                break;
            case CHOOSE_PHOTO:
                if(resultCode == RESULT_OK){
                    if(resultCode == RESULT_OK){
                        if(Build.VERSION.SDK_INT >= 19){
                            handleImageOnKitKat(data);
                        }else {
                            handleImageBeforeKitKat(data);
                        }
                    }
                }
                break;
        }
    }

    private String changeProgressToString(int progress){
        return String.valueOf(progress)+"%";
    }

    private boolean isReayToSecondFragment(){
        boolean is = false;
        if(!et_unit_is_null && !et_address_is_null && !tv_type_is_null && !et_name_is_null
                && !et_position_is_null && !et_phone_is_null && !et_email_is_null){
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

    private void dialog_submit_success(){
        AlertDialog.Builder alterDiaglog = new AlertDialog.Builder(SubmitCompanyInformationActivity.this,R.style.DialogStyle);
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

    private void openAlbum(){
        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent,CHOOSE_PHOTO);
    }

    @TargetApi(19)
    private void handleImageOnKitKat(Intent data){
        String imagePath = null;
        Uri uri =data.getData();
        Log.d("标志",uri.getPath());
        if(DocumentsContract.isDocumentUri(this,uri)){
            String docId = DocumentsContract.getDocumentId(uri);
            if("com.android.providers.media,documents".equals(uri.getAuthority())){
                String id = docId.split(":")[1];
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,selection);
            }else if("com.android.providers.downloads,documents".equals(uri.getAuthority())){
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),Long.valueOf(docId));
                imagePath = getImagePath(contentUri,null);
            }
        }else if("content".equalsIgnoreCase(uri.getScheme())){
            imagePath = getImagePath(uri,null);
        }else if("file".equalsIgnoreCase(uri.getScheme())){
            imagePath = uri.getPath();
        }
        displayImage(imagePath);
    }

    private void handleImageBeforeKitKat(Intent data){
        Uri uri = data.getData();
        String imagePath = getImagePath(uri,null);
        displayImage(imagePath);
    }

    private String getImagePath(Uri uri,String selection){
        String path = null;
        Cursor cursor = getContentResolver().query(uri,null,selection,null,null);
        if(cursor != null){
            if(cursor.moveToFirst()){
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    private void displayImage(String path){
        list.add(path);
        int current_progress = progressBar.getProgress();
        if(img_license_is_null){
            progressBar.setProgress(current_progress+25);
            tv_progress.setText(changeProgressToString(current_progress+25));
            img_license_is_null = false;
        }
        mAdapter.notifyDataSetChanged();
    }

    public static void setListViewHeightBasedOnChilds(ListView listView){
        ListAdapter listAdapter = listView.getAdapter();
        if(listAdapter==null){
            return;
        }
        int totalHeight=0;
        int totalDividerHeight=0;
        for(int i = 0;i<listAdapter.getCount();i++){
            View listItem=listAdapter.getView(i, null, listView);
            listItem.measure(0,0);
            totalHeight+=listItem.getMeasuredHeight();
        }
        totalDividerHeight=listView.getDividerHeight()*(listAdapter.getCount()-1);
        ViewGroup.LayoutParams params=listView.getLayoutParams();
        params.height=totalHeight+totalDividerHeight;
        listView.setLayoutParams(params);
    }

}
