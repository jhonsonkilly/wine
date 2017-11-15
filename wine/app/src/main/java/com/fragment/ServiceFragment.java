package com.fragment;

import android.app.Dialog;
import android.view.View;
import android.widget.TextView;

import com.androidyuan.frame.base.fragment.BaseCommFragment;
import com.presenter.ServicePresenter;

import zjw.wine.R;

/**
 * Created by mac on 2017/10/16.
 */

public class ServiceFragment extends BaseCommFragment<ServicePresenter> {
    @Override
    protected int getLayoutId() {
        return R.layout.frg_service;
    }

    @Override
    protected void initAllWidget(View view) {
        final Dialog dialog = new Dialog(getContext(), R.style.MyDialog);

        dialog.setContentView(R.layout.layout_servicedialog);

        ((TextView)dialog.findViewById(R.id.kefu)).setText("提示");

        dialog.show();
    }

    @Override
    protected void clickView(View v) {

    }
}
