package com.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.View;

import com.Event.PayEvent;
import com.androidyuan.frame.base.activity.BaseCommActivity;
import com.androidyuan.frame.cores.log.CommonLogger;
import com.otto.OttoBus;
import com.otto.Subscribe;
import com.presenter.WebViewPresenter;
import com.utils.Urls;
import com.widget.H5InputWebView;

import java.util.HashMap;

import zjw.wine.R;

/**
 * <p>Copyright:Copyright(c) 2016</p>
 * <p>Company:上海来伊份电子商务有限公司</p>
 * <p>包名:com.activity</p>
 * <p>文件名:wine</p>
 * <p>类更新历史信息</p>
 *
 * @todo <a href="mailto:zhoujiawei@laiyifen.com">vernal(周佳伟)</a>
 */
public class WebViewActivity extends BaseCommActivity<WebViewPresenter> {

    H5InputWebView qmWebview;
    private String url;
    private HashMap<String, String> parms;

    private CommonLogger mCommonLogger = CommonLogger.buildLogger(this);

    @Override
    protected int getLayoutId() {
        return R.layout.act_webview;
    }

    @Override
    protected void initAllWidget() {
        qmWebview = (H5InputWebView) findViewById(R.id.webview);
        url = getIntent().getStringExtra("url");
        parms = (HashMap<String, String>) getIntent().getSerializableExtra("parms");


        if (parms != null) {
            qmWebview.putExParams(parms);
        }
        qmWebview.loadUrl(url);


        OttoBus.getInstance().register(this);

    }

    @Override
    protected void clickView(View v) {

    }

    @Override
    public void setTopTitle(String title) {

    }

    @Override
    public void showProgressBar() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OttoBus.getInstance().unregister(this);
    }

    //接受参数
    @Subscribe
    public void getResult(PayEvent event) {

        if (event.isParams()) {

            presenter.submitMes(event.map);
        }
        if (event.isResult()) {

            HashMap<String, String> map = new HashMap<>();

            map.put("payResult", event.payResult);
            qmWebview.putExParams(map);
            qmWebview.loadUrl(Urls.getBaseUrl() + "/eshop/shoppingCart/pay.html");

        }


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        qmWebview.onActivityResult(requestCode, resultCode, data);

    }


}
