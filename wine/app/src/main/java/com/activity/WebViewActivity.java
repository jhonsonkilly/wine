package com.activity;

import android.view.View;

import com.androidyuan.frame.base.activity.BaseCommActivity;
import com.presenter.WebViewPresenter;
import com.widget.QMWebview;

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

    QMWebview qmWebview;

    @Override
    protected int getLayoutId() {
        return R.layout.act_webview;
    }

    @Override
    protected void initAllWidget() {
        qmWebview = (QMWebview) findViewById(R.id.webview);
        qmWebview.loadUrl("file:///android_asset/store.html");
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


}
