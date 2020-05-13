package com.cio_app.api;

import android.util.Log;

import com.cio_app.model.policy.policyCategory.BasePolicyCategoryModel;
import com.cio_app.model.policy.policyItem.BasePolicyItem;
import com.cio_app.model.policy.policyModel.BasePolicyModel;
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
        private static String POLICY_ITEM = ROOT_URL + "cio/miniapp/policy/";                //文章详情
        private static String POLICY_LIST = ROOT_URL + "cio/miniapp/policy";                //文章列表
    }

    static class Headers{
        private static RequestParams headers = new RequestParams();
        private static RequestParams getHeaders(){
            headers.put("Authorization","Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlaWQiOjUsInJvbGUiOjUsImV4cCI6OTIyMzM3MjAzNjg1NDc3NSwidXNlcklkIjoxfQ.HaDB_sY3Dst_KE6XMwGIU_7r9V40EdOWb1kQ7UXXamc");
            return headers;
        }
    }

    //根据参数发送所有post请求
    public static void getRequest(String url, RequestParams params, RequestParams headers ,DisposeDataListener listener,
                                  Class<?> clazz) {
        CommonOkHttpClient.get(CommonRequest.
                createGetRequest(url, params,headers), new DisposeDataHandle(listener, clazz));
    }

    //根据参数发送所有post请求
    public static void getOtherRequest(String url, RequestParams params, RequestParams headers ,DisposeDataListener listener,
                                  Class<?> clazz) {
        CommonOkHttpClient.get(CommonRequest.
                createOtherGetRequest(url, params,headers), new DisposeDataHandle(listener, clazz));
    }

    public static void requestPolicyType(DisposeDataListener listener) {
        RequestCenter.getRequest(HttpConstants.POLICY_TYPE, null,Headers.getHeaders(), listener,
                BasePolicyCategoryModel.class);
    }

    public static void requestPolicyList(RequestParams params,DisposeDataListener listener) {
        RequestCenter.getRequest(HttpConstants.POLICY_LIST, params,Headers.getHeaders(), listener,
                BasePolicyModel.class);
    }

    public static void requestPolicyItem(RequestParams params,DisposeDataListener listener) {
        RequestCenter.getOtherRequest(HttpConstants.POLICY_ITEM, params,Headers.getHeaders(), listener,
                BasePolicyItem.class);
    }


   /* public static void requestPolicyType(String address,okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).addHeader("Authorization","Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlaWQiOjUsInJvbGUiOjUsImV4cCI6OTIyMzM3MjAzNjg1NDc3NSwidXNlcklkIjoxfQ.HaDB_sY3Dst_KE6XMwGIU_7r9V40EdOWb1kQ7UXXamc").build();
        client.newCall(request).enqueue(callback);
        Log.d("请求",request.toString());
    }*/


}
