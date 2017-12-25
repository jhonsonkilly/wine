package com.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import com.activity.WebViewActivity;
import com.adapter.ParentCategoryAdapter;
import com.adapter.SubCategoryAdapter;
import com.androidyuan.frame.base.fragment.BaseCommFragment;
import com.iview.ILeftClassifyView;
import com.model.LeftClassifyModel;
import com.model.RightClassifyModel;
import com.presenter.ClassifyPresenter;
import com.utils.Urls;

import java.util.HashMap;
import java.util.List;

import zjw.wine.R;

/**
 * Created by mac on 2017/10/16.
 */

public class ClassifyFragment extends BaseCommFragment<ClassifyPresenter> implements ILeftClassifyView, ParentCategoryAdapter.OnLeftClickListener {

    private RecyclerView recyclerView;
    private RecyclerView mRightRecycleView;
    private ParentCategoryAdapter mParentCategoryAdapter;



    private SubCategoryAdapter mSubCategoryAdapter;
    EditText search_product;

    String id;
    int pos;

    private static final int CLASSIFY = 2;


    @Override
    protected int getLayoutId() {
        return R.layout.frg_classify;
    }

    @Override
    protected void initAllWidget(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.parentRv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRightRecycleView = (RecyclerView) view.findViewById(R.id.subRv);
        mRightRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        search_product = view.findViewById(R.id.search_product);


    }

    @Override
    protected void clickView(View v) {

    }

    @Override
    public void showData(List<LeftClassifyModel.Data> list, boolean isHorList) {

        mParentCategoryAdapter = new ParentCategoryAdapter(list, getContext());
        recyclerView.setAdapter(mParentCategoryAdapter);
        mParentCategoryAdapter.setOnLeftClickListener(this);
        if (isHorList) {
            mParentCategoryAdapter.getClickPos(pos);
            presenter.getRightMes(id);
        }
    }

    @Override
    public void showRightData(List<RightClassifyModel.Data> list) {


        mSubCategoryAdapter = new SubCategoryAdapter(list, getContext());
        mRightRecycleView.setAdapter(mSubCategoryAdapter);
    }

    public void getSearchText() {
        Intent intent = new Intent(getContext(), WebViewActivity.class);
        HashMap<String, String> map = new HashMap<>();

        map.put("productGuid", "noParam");

        map.put("productName", search_product.getText().toString());

        intent.putExtra("parms", map);
        intent.putExtra("url", Urls.getBaseUrl() + "/eshop/classification/neiye.html");
        this.startActivity(intent);
    }
    @Override
    public void leftClick(String id) {


        presenter.getRightMes(id);
    }

    public void getSwitchClick(String id, int pos) {
        presenter.getLeftMes(true);
        this.id = id;
        this.pos = pos;

    }
}
