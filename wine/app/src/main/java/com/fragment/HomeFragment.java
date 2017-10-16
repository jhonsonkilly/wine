package com.fragment;

import android.view.View;

import com.androidyuan.frame.base.fragment.BaseCommFragment;
import com.presenter.HomePresenter;

import zjw.wine.R;

/**
 * Created by mac on 2017/10/16.
 */

public class HomeFragment extends BaseCommFragment<HomePresenter> {
    @Override
    protected int getLayoutId() {
        return R.layout.frg_home;
    }

    @Override
    protected void initAllWidget(View view) {

    }

    @Override
    protected void clickView(View v) {

    }
}
