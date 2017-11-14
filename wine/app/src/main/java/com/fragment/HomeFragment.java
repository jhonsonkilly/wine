package com.fragment;

import android.support.v4.view.ViewPager;
import android.view.View;

import com.adapter.BannerAdapter;
import com.androidyuan.frame.base.fragment.BaseCommFragment;
import com.androidyuan.frame.cores.utils.image.FrescoUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.iview.IHomeView;
import com.model.BannerModel;
import com.model.HorlistModel;
import com.presenter.HomePresenter;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;

import zjw.wine.R;

/**
 * Created by mac on 2017/10/16.
 */

public class HomeFragment extends BaseCommFragment<HomePresenter> implements View.OnClickListener, IHomeView {

    private ViewPager pager;

    List<View> mlist = new ArrayList<>();


    private BannerAdapter adapter;
    private CirclePageIndicator indicator;

    @Override
    protected int getLayoutId() {
        return R.layout.frg_home;
    }

    @Override
    protected void initAllWidget(View view) {
        view.findViewById(R.id.img_dignwei).setOnClickListener(this);
        view.findViewById(R.id.img_scan).setOnClickListener(this);
        pager = view.findViewById(R.id.viewPager);
        indicator = view.findViewById(R.id.indicator);
    }

    @Override
    protected void clickView(View v) {
        switch (v.getId()) {
            case R.id.img_dignwei:
                break;
            case R.id.img_scan:
                break;
        }
    }

    @Override
    public void showBannerList(List<BannerModel.BannerData> list) {

        for (int i = 0; i < list.size(); i++) {
            View view = View.inflate(getContext(), R.layout.banner_item, null);
            SimpleDraweeView img1 = view.findViewById(R.id.img_1);
            FrescoUtils.displayUrl(img1, "http://cdn.oudianyun.com/lyf/prod/back-cms/1510578072575_1644_101.jpg@base@tag=imgScale&q=80");
            mlist.add(view);
        }
        adapter = new BannerAdapter(mlist);

        pager.setAdapter(adapter);
        indicator.setViewPager(pager);
    }

    @Override
    public void showHorList(List<HorlistModel.HorData> list) {

    }
}
