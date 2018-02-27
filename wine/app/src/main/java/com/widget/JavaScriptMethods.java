package com.widget;

import android.content.Context;
import android.os.Handler;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

/**
 * Created by mac on 2018/2/27.
 */

public class JavaScriptMethods {

    private Context context;
    private WebView mWebView;
    private Handler mHandler = new Handler();
    public JavaScriptMethods(Context context, WebView webView) {
        this.context = context;
        this.mWebView = webView;
    }

    @JavascriptInterface //android4.2之后，如果不加上该注解，js无法调用android方法（安全）
    public void takeShareParams(final String json) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, json, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
