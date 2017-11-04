package com.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.SimpleOnItemTouchListener;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.adapter.ParentCategoryAdapter;
import com.adapter.SubCategoryAdapter;
import com.androidyuan.frame.base.fragment.BaseCommFragment;
import com.iview.ILeftClassifyView;
import com.model.LeftClassifyModel;
import com.model.RightClassifyModel;
import com.msg.LeftClassifyReqMsg;
import com.msg.LeftClassifyResMsg;
import com.presenter.ClassifyPresenter;

import java.util.ArrayList;
import java.util.List;

import zjw.wine.R;

/**
 * Created by mac on 2017/10/16.
 */

public class ClassifyFragment extends BaseCommFragment<ClassifyPresenter> implements ILeftClassifyView, ParentCategoryAdapter.OnLeftClickListener{

    private RecyclerView recyclerView;
    private RecyclerView mRightRecycleView;
    private ParentCategoryAdapter mParentCategoryAdapter;
    private SubCategoryAdapter mSubCategoryAdapter;


    @Override
    protected int getLayoutId() {
        return R.layout.frg_classify;
    }

    @Override
    protected void initAllWidget(View view) {
        recyclerView = view.findViewById(R.id.parentRv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRightRecycleView = view.findViewById(R.id.subRv);
        mRightRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));



    }

    @Override
    protected void clickView(View v) {

    }

    @Override
    public void showData(List<LeftClassifyModel.Data> list) {
        mParentCategoryAdapter = new ParentCategoryAdapter(list,getContext());
        recyclerView.setAdapter(mParentCategoryAdapter);
        mParentCategoryAdapter.setOnLeftClickListener(this);
    }

    @Override
    public void showRightData(List<RightClassifyModel.Data> list) {


        mSubCategoryAdapter = new SubCategoryAdapter(list,getContext());
        mRightRecycleView.setAdapter(mSubCategoryAdapter);
    }



    @Override
    public void leftClick(String id) {


        presenter.getRightMes(id);
    }
}
