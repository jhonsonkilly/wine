package com.fragment;

import android.util.Log;
import android.view.View;

import com.androidyuan.frame.base.fragment.BaseCommFragment;
import com.iview.ILeftClassifyView;
import com.model.LeftClassifyModel;
import com.msg.LeftClassifyReqMsg;
import com.msg.LeftClassifyResMsg;
import com.presenter.ClassifyPresenter;

import java.util.List;

import zjw.wine.R;

/**
 * Created by mac on 2017/10/16.
 */

public class ClassifyFragment extends BaseCommFragment<ClassifyPresenter> implements ILeftClassifyView{
    @Override
    protected int getLayoutId() {
        return R.layout.frg_classify;
    }

    @Override
    protected void initAllWidget(View view) {
        presenter.getLeftMes();
    }

    @Override
    protected void clickView(View v) {

    }

    @Override
    public void showData(List<LeftClassifyModel.Data> list) {
        Log.i("haha","1");
    }
}
