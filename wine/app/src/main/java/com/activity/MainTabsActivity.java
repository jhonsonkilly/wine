package com.activity;

import android.view.View;

import com.androidyuan.frame.base.activity.BaseCommActivity;
import com.widget.TabChooser;

import zjw.wine.R;

/**
 * Created by mac on 2017/10/16.
 */

public class MainTabsActivity extends BaseCommActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initAllWidget() {
        titleArr = getResources().getStringArray(R.array.home_tabs);
        tab_bar = (TabChooser)findViewById(R.id.tab_bar);
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
