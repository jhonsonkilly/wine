package com.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.adapter.ParentCategoryAdapter;
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

public class ClassifyFragment extends BaseCommFragment<ClassifyPresenter> implements ILeftClassifyView {

    private RecyclerView recyclerView;

    @Override
    protected int getLayoutId() {
        return R.layout.frg_classify;
    }

    @Override
    protected void initAllWidget(View view) {
        recyclerView = view.findViewById(R.id.parentRv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


    }

    @Override
    protected void clickView(View v) {

    }

    @Override
    public void showData(List<LeftClassifyModel.Data> list) {

        recyclerView.setAdapter(new ParentCategoryAdapter(list));
    }
}
