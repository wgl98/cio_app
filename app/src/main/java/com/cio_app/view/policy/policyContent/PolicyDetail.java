package com.cio_app.view.policy.policyContent;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

import com.cio_app.R;
import com.e.lib_common_ui.base.BaseActivity;

public class PolicyDetail extends BaseActivity {

    private WebView webView ;

    public static void start(Context context,String str) {
        Intent intent = new Intent(context, PolicyDetail.class);
        intent.putExtra("data",str);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_policy_detail);
        String data = getIntent().getStringExtra("data");
        webView = new WebView(this);
        webView = findViewById(R.id.webview);
        webView.loadDataWithBaseURL(null,data,"text/html","UTF-8",null);
    }
}
