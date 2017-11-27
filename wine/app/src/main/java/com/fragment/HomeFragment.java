package com.fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.activity.SweepActivity;
import com.adapter.BannerAdapter;
import com.adapter.HorListAdapter;
import com.adapter.JingXuanAdapter;
import com.adapter.ProductAdapter;
import com.adapter.ProductListAdapter;
import com.androidyuan.frame.base.fragment.BaseCommFragment;
import com.androidyuan.frame.cores.utils.image.FrescoUtils;
import com.androidyuan.frame.cores.widget.bugfixview.FixReBackViewPager;
import com.facebook.drawee.view.SimpleDraweeView;
import com.iview.IHomeView;
import com.model.BannerModel;
import com.model.HorlistModel;
import com.model.JingXuanModel;
import com.model.ProductModel;
import com.model.QiangGouModel;
import com.presenter.HomePresenter;
import com.tbruyelle.rxpermissions.RxPermissions;
import com.utils.LocationManager;
import com.viewpagerindicator.CirclePageIndicator;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import rx.functions.Action1;
import zjw.wine.R;

/**
 * Created by mac on 2017/10/16.
 */

public class HomeFragment extends BaseCommFragment<HomePresenter> implements View.OnClickListener, IHomeView {

    private FixReBackViewPager pager;

    List<View> mlist = new ArrayList<>();


    private BannerAdapter adapter;
    private CirclePageIndicator indicator;

    LocationManager locationManager;
    private RecyclerView horRecycle;


    private ListView listView;

    private ListView productListView;
    private ProductListAdapter productAdapter;


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

        horRecycle = view.findViewById(R.id.hor_recycle);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        horRecycle.setLayoutManager(linearLayoutManager);


        listView = view.findViewById(R.id.jingxuan_list);

        productListView = view.findViewById(R.id.product_list);

        locationManager = new LocationManager(getContext());

    }

    @Override
    protected void clickView(View v) {
        switch (v.getId()) {
            case R.id.img_dignwei:
                locationManager.setLocationListener(new LocationManager.LocationListener() {
                    @Override
                    public void onLocationChanged(LocationManager.MapLocation location) {
                        if (location != null) {

                            Toast.makeText(getContext(), location.address, Toast.LENGTH_LONG).show();
                        }
                    }
                }).setOnceLocation(true)
                        .startLocation(getActivity());
                break;
            case R.id.img_scan:


                RxPermissions rxPermissions = new RxPermissions(getActivity());
                rxPermissions.request(
                        //mTODO:meiyizhi 定位需要的权限
                        android.Manifest.permission.CAMERA)
                        .subscribe(new Action1<Boolean>() {
                            @Override
                            public void call(Boolean granted) {
                                if (granted) {
                                    startActivity(new Intent(getContext(), SweepActivity.class));
                                } else {

                                    Toast.makeText(getContext(), "为了更好的使用体验，请开启相机使用权限!", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
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
        horRecycle.setAdapter(new HorListAdapter(getContext(), list));
    }

    @Override
    public void showQiangGouList(List<QiangGouModel.QiangGouData> list) {

    }

    @Override
    public void showJingXuanList(List<JingXuanModel.Data> list) {
        listView.setAdapter(new JingXuanAdapter(getContext(), list));
    }

    @Override
    public void showMes(String mes) {
        Toast.makeText(getContext(), mes, Toast.LENGTH_LONG).show();
    }


    @Override
    public void showProductList(List<ProductModel.Result> list) {
        productAdapter = new ProductListAdapter(getContext(), list);
        productListView.setAdapter(productAdapter);
        productAdapter.setOnAddCartClickListener(new ProductListAdapter.OnAddCartClickListener() {
            @Override
            public void addCart(String id) {
                presenter.addtoCart(id, "1");
            }
        });

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (locationManager != null) {
            locationManager.stopLocation();
        }
    }
}
