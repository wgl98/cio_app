package com.cio_app.api;

import android.os.Environment;
import android.util.Log;

import com.cio_app.model.conference.conferenceItem.BaseConferenceItem;
import com.cio_app.model.conference.conferenceModel.BaseConferenceModel;
import com.cio_app.model.conference.conferenceRegistered.ConferenceRegisteredModel;
import com.cio_app.model.policy.policyCategory.BasePolicyCategoryModel;
import com.cio_app.model.policy.policyItem.BasePolicyItem;
import com.cio_app.model.policy.policyModel.BasePolicyModel;
import com.cio_app.model.user.UserVerifiedModel;
import com.lib_network.CommonOkHttpClient;
import com.lib_network.listener.DisposeDataHandle;
import com.lib_network.listener.DisposeDataListener;
import com.lib_network.request.CommonRequest;
import com.lib_network.request.RequestParams;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class RequestCenter {

    static class HttpConstants {
        private static final String ROOT_URL = "http://cio.lvdengtech.com/";

        /**
         * 政策服务页面
         */
        private static String POLICY_TYPE = ROOT_URL + "cio/miniapp/policy/category";       //文章类型
        private static String POLICY_ITEM = ROOT_URL + "cio/miniapp/policy/";               //文章详情
        private static String POLICY_LIST = ROOT_URL + "cio/miniapp/policy";                //文章列表

        /**
         * 会议服务页面
         */
        private static String CONFERENCE_LIST = ROOT_URL + "cio/miniapp/event/eventMaster/all";          //会议列表
        private static String CONFERENCE_ITEM = ROOT_URL + "cio/miniapp/event/eventMaster/";             //会议详情
        private static String CONFERENCE_IS_REGISTERED = ROOT_URL + "/cio/miniapp/event/isRegistrated";  //会议是否已经报名
        private static String USER_IS_VERIFIED = ROOT_URL + "/cio/miniapp/event/verify";                 //判断用户是否已认证
        private static String CONFERENCE_REGISTER = ROOT_URL + "/cio/miniapp/event/registration/";      //活动报名

    }

    static class StoragePath {
        private static String CONFERENCE_SUMMARY = Environment.getExternalStorageDirectory()+"/SUMMARY/";
    }

    static class Headers{
        private static RequestParams headers = new RequestParams();
        private static RequestParams getHeaders(){
            headers.put("Authorization","Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlaWQiOjUsInJvbGUiOjUsImV4cCI6OTIyMzM3MjAzNjg1NDc3NSwidXNlcklkIjoxfQ.HaDB_sY3Dst_KE6XMwGIU_7r9V40EdOWb1kQ7UXXamc");
            return headers;
        }
    }

    //获取文件名
    public static String getFileName(String url){
        String [] name = url.split("/");
        return name[name.length-1];
    }

    //根据参数发送所有post请求
    public static void postRequest(String url, RequestParams params, RequestParams headers ,DisposeDataListener listener,
                                  Class<?> clazz) {
        CommonOkHttpClient.post(CommonRequest.
                createPostRequest(url,params,headers), new DisposeDataHandle(listener, clazz));
    }

    //根据参数发送所有post请求
    public static void getRequest(String url, RequestParams params, RequestParams headers ,DisposeDataListener listener,
                                  Class<?> clazz) {
        CommonOkHttpClient.get(CommonRequest.
                createGetRequest(url,params,headers), new DisposeDataHandle(listener, clazz));
    }

    //根据参数发送所有post请求
    public static void getOtherRequest(String url, RequestParams params, RequestParams headers ,DisposeDataListener listener,
                                  Class<?> clazz) {
        CommonOkHttpClient.get(CommonRequest.
                createOtherGetRequest(url, params,headers), new DisposeDataHandle(listener, clazz));
    }
    //根据参数下载文件请求
    public static void downloadFileRequest(String url, RequestParams params, RequestParams headers ,DisposeDataListener listener) {
        CommonOkHttpClient.downloadFile(CommonRequest.
                createGetRequest(url, params,headers), new DisposeDataHandle(listener, StoragePath.CONFERENCE_SUMMARY+getFileName(url)));
    }


    //获取政策服务类型
    public static void requestPolicyType(DisposeDataListener listener) {
        RequestCenter.getRequest(HttpConstants.POLICY_TYPE, null,Headers.getHeaders(), listener,
                BasePolicyCategoryModel.class);
    }

    //获取政策服务列表内容
    public static void requestPolicyList(RequestParams params,DisposeDataListener listener) {
        RequestCenter.getRequest(HttpConstants.POLICY_LIST, params,Headers.getHeaders(), listener,
                BasePolicyModel.class);
    }

    //获取政策服务内容详情
    public static void requestPolicyItem(RequestParams params,DisposeDataListener listener) {
        RequestCenter.getOtherRequest(HttpConstants.POLICY_ITEM, params,Headers.getHeaders(), listener,
                BasePolicyItem.class);
    }

    //获取会议服务列表内容
    public static void requestConferenceList(RequestParams params,DisposeDataListener listener) {
        RequestCenter.getRequest(HttpConstants.CONFERENCE_LIST, params,Headers.getHeaders(), listener,
                BaseConferenceModel.class);
    }

    //获取政策服务内容详情
    public static void requestConferenceItem(RequestParams params,DisposeDataListener listener) {
        RequestCenter.getOtherRequest(HttpConstants.CONFERENCE_ITEM, params,Headers.getHeaders(), listener,
                BaseConferenceItem.class);
    }

    //下载文件内容
    public static void downloadFile(String url,DisposeDataListener listener) {
        RequestCenter.downloadFileRequest(url,null,Headers.getHeaders(), listener);
    }

    //获取会议服务列表内容
    public static void requestConferenceIsRegistered(RequestParams params,DisposeDataListener listener) {
        RequestCenter.getRequest(HttpConstants.CONFERENCE_IS_REGISTERED, params,Headers.getHeaders(), listener,
                ConferenceRegisteredModel.class);
    }
    //获取会议服务列表内容
    public static void requestUserIsVerified(DisposeDataListener listener) {
        RequestCenter.getRequest(HttpConstants.USER_IS_VERIFIED,null,Headers.getHeaders(), listener,
                UserVerifiedModel.class);
    }

    //获取会议服务列表内容
    public static void postConferenceRegisterData(String id,RequestParams params,DisposeDataListener listener) {
        RequestCenter.postRequest(HttpConstants.CONFERENCE_REGISTER+id, params,Headers.getHeaders(), listener,
                ConferenceRegisteredModel.class);
    }
}
