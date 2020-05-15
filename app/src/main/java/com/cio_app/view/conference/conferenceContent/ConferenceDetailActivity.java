package com.cio_app.view.conference.conferenceContent;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.TextureMapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.bumptech.glide.Glide;
import com.cio_app.R;
import com.cio_app.api.RequestCenter;
import com.cio_app.model.conference.conferenceItem.BaseConferenceItem;
import com.cio_app.model.conference.conferenceItem.ConferenceItemBodyValue;
import com.cio_app.model.conference.conferenceRegistered.ConferenceRegisteredModel;
import com.cio_app.model.user.UserVerifiedModel;
import com.cio_app.view.conference.conferenceContent.conferenceBottom.ConferenceFinishBottomFragment;
import com.cio_app.view.conference.conferenceContent.conferenceBottom.ConferenceSignBottomFragment;
import com.cio_app.view.conference.conferenceContent.conferenceDetail.ConferenceDetailFragment;
import com.cio_app.view.conference.conferenceContent.conferenceIntroduce.ConferenceIntroduceFragment;
import com.cio_app.view.mine.member.MineMemberApproveActivity;
import com.e.lib_common_ui.base.BaseActivity;
import com.e.lib_common_ui.loading_view.LoadingView;
import com.lib_network.listener.DisposeDataListener;
import com.lib_network.listener.DisposeDownloadListener;
import com.lib_network.request.RequestParams;

import java.io.File;


public class ConferenceDetailActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener,View.OnClickListener {

    public final static int REGISTER = 1;

    private static final int REQUEST_EXTERNAL_STORAGE = 1;

    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE" };

    private Bundle mBundle;

    private UiSettings uiSettings;

    private int id;  //会议的id

    private LoadingView loadingView;                                             //加载圈效果

    private FragmentManager manager;
    private FragmentTransaction transaction;
    private RadioGroup radioGroup;

    private ConferenceIntroduceFragment introduceFragment;                          //会议介绍页面
    private ConferenceDetailFragment detailFragment;                                //会议详情页面

    private ConferenceFinishBottomFragment finishBottomFragment;                    //会议结束、查看会议总结底部界面
    private ConferenceSignBottomFragment signBottomFragment;                        //会议报名中/进行中，可报名顶部界面

    private ImageView img;                          //封面
    private TextView tv_title;                      //标题
    private TextView tv_date;                       //时间
    private TextView tv_approve_state;              //审核状态
    private TextView tv_address;                    //地址
    private TextureMapView mapView;                 //地图
    private AMap aMap;
    private TextView tv_contact;                   //联系人
    private TextView tv_phone;                     //电话
    private WebView webView ;                      //详情

    private Button btn_sign;                       //报名
    private Button btn_summary;                    //查看总结

    private LinearLayout ll_detail_bottom;         //底部布局

    private RelativeLayout rl_conference_state;    //状态布局
    private TextView tv_conference_state;          //会议状态

    private ConferenceItemBodyValue value;         //会议实体

    public static void start(Context context,int id) {
        Intent intent = new Intent(context, ConferenceDetailActivity.class);
        intent.putExtra("id",id);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conference_detail);
        mBundle = savedInstanceState;
        id = getIntent().getIntExtra("id",-1);

        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();


        introduceFragment = (ConferenceIntroduceFragment) ConferenceIntroduceFragment.newInstance();
        detailFragment = (ConferenceDetailFragment) ConferenceDetailFragment.newInstance();

        finishBottomFragment = new ConferenceFinishBottomFragment();
        signBottomFragment = new ConferenceSignBottomFragment();

        transaction.add(R.id.show_conference_content_detail_layout,introduceFragment);
        transaction.add(R.id.show_conference_content_detail_layout,detailFragment);
        transaction.add(R.id.activity_conference_detail_content_bottom,signBottomFragment);
        transaction.add(R.id.activity_conference_detail_content_bottom,finishBottomFragment);
        transaction.show(introduceFragment);
        transaction.hide(detailFragment);
        transaction.hide(signBottomFragment);
        transaction.hide(finishBottomFragment);
        transaction.commit();

        initView();
        verifyStoragePermissions(this);
        //requestRegisteredData();
        requestData();
    }

    @Override
    protected void onStart() {
        super.onStart();
        initFragmentView();
    }

    private void initView(){
        findViewById(R.id.btn_back_in_conference_detail_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        findViewById(R.id.btn_back_in_conference_detail).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        rl_conference_state = (RelativeLayout) findViewById(R.id.show_conference_state_in_detail);
        tv_conference_state = (TextView) findViewById(R.id.show_conference_state_text_in_detail);

        img = (ImageView) findViewById(R.id.show_conference_picture_in_detail);


        radioGroup = (RadioGroup) findViewById(R.id.conference_content_detail_title);
        radioGroup.setOnCheckedChangeListener(this);

        loadingView = new LoadingView(this,R.style.DialogStyle);
        loadingView.show();

        ll_detail_bottom = (LinearLayout) findViewById(R.id.activity_conference_detail_content_bottom);
    }

    private void initFragmentView(){
        View introduceView = introduceFragment.getView();
        tv_title = introduceView.findViewById(R.id.conference_content_detail_title_text);
        tv_approve_state = introduceView.findViewById(R.id.conference_content_detail_in_approve_state);
        tv_date = introduceView.findViewById(R.id.conference_content_detail_date);
        tv_address = introduceView.findViewById(R.id.conference_content_detail_address);
        mapView = introduceView.findViewById(R.id.conference_content_detail_map);
        mapView.onCreate(mBundle);
        if(aMap == null){
            aMap = mapView.getMap();
            aMap.moveCamera(CameraUpdateFactory.zoomTo(16));
        }
        uiSettings = aMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(false);
        uiSettings.setAllGesturesEnabled(false);


        tv_contact = introduceView.findViewById(R.id.conference_content_detail_contacts);
        tv_phone = introduceView.findViewById(R.id.conference_content_detail_contactsDetails);

        View detailView = detailFragment.getView();
        webView = new WebView(this);
        webView = detailView.findViewById(R.id.webview);

        View signBottomView = signBottomFragment.getView();
        btn_sign = signBottomView.findViewById(R.id.btn_in_conference_detail_sign_bottom);
        btn_sign.setOnClickListener(this);

        View finishBottomView = finishBottomFragment.getView();
        btn_summary = finishBottomView.findViewById(R.id.btn_in_conference_detail_finish_bottom);
        btn_summary.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_in_conference_detail_sign_bottom:
                requestVerifiedData();
                break;
            case R.id.btn_in_conference_detail_finish_bottom:
                downloadFile();
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        transaction = manager.beginTransaction();
        switch (checkedId) {
            case R.id.conference_content_detail_title_introduce:
                transaction.show(introduceFragment);
                transaction.hide(detailFragment);
                break;
            case R.id.conference_content_detail_title_detail:
               transaction.hide(introduceFragment);
               transaction.show(detailFragment);
                break;
        }
        transaction.commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case REGISTER:
                if(resultCode == RESULT_OK){
                    requestData();
                    dialog_sign_success();
                }else {
                    Toast.makeText(this,"报名不成功，你再次尝试",Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    //请求数据
    private void requestData() {
        RequestParams params = new RequestParams();
        params.put("",String.valueOf(id));
        RequestCenter.requestConferenceItem(params,new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                Log.d("请求数据","成功");
                value = ((BaseConferenceItem) responseObj).data;
                updateView();
            }
            @Override
            public void onFailure(Object reasonObj) {
                Log.d("请求数据","失败");
            }
        });
    }

    //判断是否已经报名
    private void requestRegisteredData(){
        RequestParams params = new RequestParams();
        params.put("eventId",String.valueOf(id));
        RequestCenter.requestConferenceIsRegistered(params, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                Log.d("请求报名数据","成功");
                ConferenceRegisteredModel value = (ConferenceRegisteredModel) responseObj;
                if(value.data){
                    tv_approve_state.setVisibility(View.VISIBLE);
                    ll_detail_bottom.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Object reasonObj) {
                Log.d("请求报名数据","失败");
            }
        });
    }

    //判断是否已经认证
    private void requestVerifiedData(){
        RequestParams params = new RequestParams();
        RequestCenter.requestUserIsVerified(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                Log.d("请求认证数据","成功");
                UserVerifiedModel model = (UserVerifiedModel) responseObj;
                if(model.data){
                    Intent intent = new Intent(ConferenceDetailActivity.this, ConferenceRegisterActivity.class);
                    intent.putExtra("id",value.id);
                    startActivityForResult(intent,REGISTER);
                }else {
                    dialog_no_approve();
                }
            }

            @Override
            public void onFailure(Object reasonObj) {
                Log.d("请求认证数据","失败");
            }
        });
    }

    //请求数据后更新页面
    private void updateView(){
        loadingView.dismiss();
        tv_conference_state.setText(value.eventStatus);                                                           //会议状态

        Glide.with(this).load(value.coverImage).into(img);                                                //会议封面

        if(value.registrationStatus != null){
            if(value.registrationStatus.equals("待审核")){
                tv_approve_state.setText("报名审核中");
                tv_approve_state.setBackgroundResource(R.drawable.shape_conference_approve_wait_state_background);
            }else if(value.registrationStatus.equals("不通过")){
                tv_approve_state.setText("报名不通过");
                tv_approve_state.setBackgroundResource(R.drawable.shape_conference_approve_fail_state_background);
            }else {
                tv_approve_state.setText("报名通过");
                tv_approve_state.setBackgroundResource(R.drawable.shape_conference_approve_pass_state_background);
            }
            tv_approve_state.setVisibility(View.VISIBLE);
            ll_detail_bottom.setVisibility(View.GONE);
        }

        transaction = manager.beginTransaction();
        if(value.eventStatus.equals("报名中")){
            rl_conference_state.setBackground(getResources().getDrawable(R.mipmap.show_conference_sign_state));   //会议状态背景色
            transaction.show(signBottomFragment);
            transaction.hide(finishBottomFragment);
        }else if (value.eventStatus.equals("进行中")){
            rl_conference_state.setBackground(getResources().getDrawable(R.mipmap.show_conference_handle_state));
            transaction.show(signBottomFragment);
            transaction.hide(finishBottomFragment);
        }else {
            rl_conference_state.setBackground(getResources().getDrawable(R.mipmap.show_conference_finish_state));
            transaction.show(finishBottomFragment);
            transaction.hide(signBottomFragment);
        }
        transaction.commit();

        tv_title.setText(value.title);                                                                             //标题

        tv_date.setText(value.startTime);                                                                          //时间

        tv_address.setText(value.address);                                                                         //地址

        aMap.addMarker(new MarkerOptions()
                .position(new LatLng(value.locationX,value.locationY))
                .icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(),R.mipmap.map_blue_tag)))); //地图
        aMap.moveCamera(CameraUpdateFactory.changeLatLng(new LatLng(value.locationX,value.locationY)));

        tv_contact.setText(value.contact);                                         //联系人

        SpannableStringBuilder stringBuilder = new SpannableStringBuilder(value.contactDetails);  //电话
        TextViewSpan textViewSpan = new TextViewSpan();
        stringBuilder.setSpan(textViewSpan,0,11, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv_phone.setText(stringBuilder);
        tv_phone.setMovementMethod(LinkMovementMethod.getInstance());
        tv_phone.setHighlightColor(getResources().getColor(android.R.color.transparent));

        webView.loadDataWithBaseURL(null,value.description,"text/html","UTF-8",null);  //会议详情
    }

    //下载总结活动pdf
    private void downloadFile() {
        if(value.summary != null){
            loadingView.show();
            RequestCenter.downloadFile(value.summary, new DisposeDownloadListener() {
                @Override
                public void onProgress(int progress) {
                }
                @Override
                public void onSuccess(Object responseObj) {
                    loadingView.dismiss();
                    openPDF((File) responseObj);
                    Log.d("下载文件", "成功");
                }

                @Override
                public void onFailure(Object reasonObj) {
                    loadingView.dismiss();
                    Log.d("下载文件", "失败");
                }
            });
        }else {
            Toast.makeText(this,"暂无活动总结相关信息",Toast.LENGTH_SHORT).show();
        }
    }

    //打开pdf
    private void openPDF(File file) {
        if (file.exists()) {
            Uri path1 = Uri.fromFile(file);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(path1, "application/pdf");
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            try {
                startActivity(intent);
            }
            catch (Exception e) {
                Log.d("打开失败","打开失败");
            }
        }
    }

    //手机号码设置可点击
    private class TextViewSpan extends ClickableSpan {
        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setColor(getResources().getColor(R.color.colorBlue));
            //设置是否有下划线
            ds.setUnderlineText(true);
        }
        @Override
        public void onClick(View widget) {
        }
    }

    //未进行会员认证
    private void dialog_no_approve(){
        AlertDialog.Builder alterDiaglog = new AlertDialog.Builder(ConferenceDetailActivity.this);
        alterDiaglog.setView(R.layout.dialog_is_not_approve_layout);//加载进去
        final AlertDialog dialog = alterDiaglog.create();
        //显示
        dialog.show();
        //取消
        dialog.getWindow().findViewById(R.id.btn_negative_in_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        //前往
        dialog.getWindow().findViewById(R.id.btn_positive_in_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MineMemberApproveActivity.start(ConferenceDetailActivity.this);
                dialog.dismiss();
            }
        });
    }

    //报名成功
    private void dialog_sign_success(){
        AlertDialog.Builder alterDiaglog = new AlertDialog.Builder(ConferenceDetailActivity.this);
        alterDiaglog.setView(R.layout.dialog_is_sign_success_layout);//加载进去
        final AlertDialog dialog = alterDiaglog.create();
        //显示
        dialog.show();
        dialog.getWindow().findViewById(R.id.btn_positive_in_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    //动态申请权限
    public static void verifyStoragePermissions(Activity activity) {
        try {
            //检测是否有写的权限
            int permission = ActivityCompat.checkSelfPermission(activity,
                    "android.permission.WRITE_EXTERNAL_STORAGE");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
