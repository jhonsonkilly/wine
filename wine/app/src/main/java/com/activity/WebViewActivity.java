package com.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.View;

import com.androidyuan.frame.base.activity.BaseCommActivity;
import com.presenter.WebViewPresenter;
import com.widget.H5InputWebView;
import com.widget.QMWebview;

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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == H5InputWebView.FILECHOOSER_RESULTCODE) {
            if (null == qmWebview.mUploadMessage && null == qmWebview.mUploadCallbackAboveL) return;
            Uri result = data == null || resultCode != RESULT_OK ? null : data.getData();
            if (qmWebview.mUploadCallbackAboveL != null) {
                onActivityResultAboveL(requestCode, resultCode, data);
            } else if (qmWebview.mUploadMessage != null) {
                Log.e("result", result + "");
                if (result == null) {
//	            		mUploadMessage.onReceiveValue(imageUri);
                    qmWebview.mUploadMessage.onReceiveValue(qmWebview.imageUri);
                    qmWebview.mUploadMessage = null;

                    Log.e("imageUri", qmWebview.imageUri + "");
                } else {
                    qmWebview.mUploadMessage.onReceiveValue(result);
                    qmWebview.mUploadMessage = null;
                }


            }
        }
    }

    @SuppressWarnings("null")
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void onActivityResultAboveL(int requestCode, int resultCode, Intent data) {
        if (requestCode != H5InputWebView.FILECHOOSER_RESULTCODE
                || qmWebview.mUploadCallbackAboveL == null) {
            return;
        }

        Uri[] results = null;
        if (resultCode == Activity.RESULT_OK) {
            if (data == null) {
                results = new Uri[]{qmWebview.imageUri};
            } else {
                String dataString = data.getDataString();
                ClipData clipData = data.getClipData();

                if (clipData != null) {
                    results = new Uri[clipData.getItemCount()];
                    for (int i = 0; i < clipData.getItemCount(); i++) {
                        ClipData.Item item = clipData.getItemAt(i);
                        results[i] = item.getUri();
                    }
                }

                if (dataString != null)
                    results = new Uri[]{Uri.parse(dataString)};
            }
        }
        if (results != null) {
            qmWebview.mUploadCallbackAboveL.onReceiveValue(results);
            qmWebview.mUploadCallbackAboveL = null;
        } else {
            results = new Uri[]{qmWebview.imageUri};
            qmWebview.mUploadCallbackAboveL.onReceiveValue(results);
            qmWebview.mUploadCallbackAboveL = null;
        }

        return;
    }


}
