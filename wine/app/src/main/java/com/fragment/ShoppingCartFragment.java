package com.fragment;

import android.view.View;
import android.widget.TextView;

import com.Event.GoHomeEvent;
import com.androidyuan.frame.base.fragment.BaseCommFragment;
import com.otto.OttoBus;
import com.presenter.ShoppingCartPresenter;
import com.widget.ToolBar;

import zjw.wine.R;

/**
 * Created by mac on 2017/10/16.
 */

public class ShoppingCartFragment extends BaseCommFragment<ShoppingCartPresenter> {

    private ToolBar toolBar;

    TextView text_go_home;

    @Override
    protected int getLayoutId() {
        return R.layout.frg_shopping_cart;
    }

    @Override
    protected void initAllWidget(View view) {
        toolBar = view.findViewById(R.id.toolbar);
        text_go_home = view.findViewById(R.id.text_go_home);

        text_go_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                OttoBus.getInstance().post(new GoHomeEvent());
            }
        });

        toolBar.setTitle("购物车");
        toolBar.hideBack();

        toolBar.showShopCart().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    @Override
    protected void clickView(View v) {

    }
}
