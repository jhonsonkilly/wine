package com.fragment;

import android.view.View;

import com.androidyuan.frame.base.fragment.BaseCommFragment;
import com.presenter.ShoppingCartPresenter;

import zjw.wine.R;

/**
 * Created by mac on 2017/10/16.
 */

public class ShoppingCartFragment extends BaseCommFragment<ShoppingCartPresenter> {
    @Override
    protected int getLayoutId() {
        return R.layout.frg_shopping_cart;
    }

    @Override
    protected void initAllWidget(View view) {

    }

    @Override
    protected void clickView(View v) {

    }
}
