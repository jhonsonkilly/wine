package com.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

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
public class WebViewActivity extends Activity {


    QMWebview qmWebview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_webview);
        qmWebview= findViewById(R.id.webview);
        qmWebview.loadUrl("file:///android_asset/store.html");

    }
}
