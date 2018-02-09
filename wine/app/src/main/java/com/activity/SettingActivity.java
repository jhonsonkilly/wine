package com.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.androidyuan.frame.base.activity.BaseCommActivity;
import com.androidyuan.frame.cores.utils.SharedPreferencesUtil;
import com.presenter.SettingPresenter;
import com.utils.Urls;
import com.widget.ToolBar;

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
public class SettingActivity extends BaseCommActivity<SettingPresenter> {


    ToolBar toolBar;
    private Button button;


    @Override
    protected int getLayoutId() {
        return R.layout.act_setting;
    }

    @Override
    protected void initAllWidget() {
        toolBar = (ToolBar) findViewById(R.id.toolbar);
        toolBar.setTitle("设置");
        findViewById(R.id.lin_ll).setOnClickListener(this);
        button = (Button) findViewById(R.id.cancel_login);
        button.setOnClickListener(this);
        if (!TextUtils.isEmpty(SharedPreferencesUtil.getStringData(this, "ut", ""))) {
            button.setVisibility(View.VISIBLE);
        } else {
            button.setVisibility(View.GONE);
        }


    }

    @Override
    protected void clickView(View v) {
        if (v.getId() == R.id.lin_ll) {

            Intent intent = new Intent(this, WebViewActivity.class);
            intent.putExtra("url", Urls.getBaseUrl() + "/eshop/setup/about.html");
            startActivity(intent);

        }
        if (v.getId() == R.id.cancel_login) {
            presenter.quitLogin();
        }
    }

    @Override
    public void setTopTitle(String title) {

    }

    @Override
    public void showProgressBar() {

    }
}
