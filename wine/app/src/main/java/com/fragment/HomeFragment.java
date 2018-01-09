package com.fragment;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.Event.AddToCartEvent;
import com.activity.LoginActivity;
import com.activity.MainTabsActivity;
import com.activity.SweepActivity;
import com.activity.WebViewActivity;
import com.adapter.BannerAdapter;
import com.adapter.HorListAdapter;
import com.adapter.JingXuanAdapter;
import com.adapter.ProductListAdapter;
import com.adapter.QiangGouAdapter;
import com.androidyuan.frame.base.fragment.BaseCommFragment;
import com.androidyuan.frame.cores.utils.SharedPreferencesUtil;
import com.androidyuan.frame.cores.utils.image.FrescoUtils;
import com.androidyuan.frame.cores.widget.bugfixview.FixReBackViewPager;
import com.facebook.drawee.view.SimpleDraweeView;
import com.iview.IHomeView;
import com.model.BannerModel;
import com.model.HorlistModel;
import com.model.JingXuanModel;
import com.model.MapWrapper;
import com.model.ProductModel;
import com.model.QiangGouModel;
import com.otto.OttoBus;
import com.otto.Subscribe;
import com.presenter.HomePresenter;
import com.tbruyelle.rxpermissions.RxPermissions;
import com.utils.LocationManager;
import com.utils.Urls;
import com.viewpagerindicator.CirclePageIndicator;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
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

    RecyclerView recyclerView;

    HorListAdapter horListAdapter;
    private JingXuanAdapter jingXuanAdapter;
    private EditText search_product;
    private static final int HOME = 1;


    private Handler mHandler = new Handler();
    private static final int TIME = 2500;
    private int itemPosition;

    @Override
    protected int getLayoutId() {
        return R.layout.frg_home;
    }

    @Override
    protected void initAllWidget(View view) {
        view.findViewById(R.id.img_dignwei).setOnClickListener(this);
        view.findViewById(R.id.img_scan).setOnClickListener(this);
        pager = (FixReBackViewPager) view.findViewById(R.id.viewPager);
        indicator = (CirclePageIndicator) view.findViewById(R.id.indicator);

        horRecycle = (RecyclerView) view.findViewById(R.id.hor_recycle);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        horRecycle.setLayoutManager(linearLayoutManager);


        listView = (ListView) view.findViewById(R.id.jingxuan_list);

        productListView = (ListView) view.findViewById(R.id.product_list);

        recyclerView = (RecyclerView) view.findViewById(R.id.qianggou_recycle);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getContext());
        linearLayoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager2);

        locationManager = new LocationManager(getContext());

        view.findViewById(R.id.img_1).setOnClickListener(this);
        view.findViewById(R.id.img_2).setOnClickListener(this);
        view.findViewById(R.id.img_3).setOnClickListener(this);
        view.findViewById(R.id.img_4).setOnClickListener(this);
        view.findViewById(R.id.huodong_rl).setOnClickListener(this);
        search_product = (EditText) view.findViewById(R.id.search_product);

        search_product.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // 这两个条件必须同时成立，如果仅仅用了enter判断，就会执行两次
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    // 执行发送消息等操作
                    event.setSource(HOME);
                    return true;
                }
                return false;
            }
        });
        OttoBus.getInstance().register(this);


    }

    @Override
    protected void clickView(View v) {
        switch (v.getId()) {
            case R.id.img_dignwei:

                if (!TextUtils.isEmpty(SharedPreferencesUtil.getStringData(getContext(), "ut", ""))) {
                    locationManager.setLocationListener(new LocationManager.LocationListener() {
                        @Override
                        public void onLocationChanged(LocationManager.MapLocation location) {
                            if (location != null) {

                                //Toast.makeText(getContext(), location.address, Toast.LENGTH_LONG).show();
                                if (!TextUtils.isEmpty(SharedPreferencesUtil.getStringData(getContext(), "ut", ""))) {
                                    Intent intent = new Intent(getContext(), WebViewActivity.class);
                                    LinkedHashMap<String, String> map = new LinkedHashMap<>();
                                    map.put("currentLocation", URLEncoder.encode(location.address));
                                    map.put("coordinate", location.x + "-" + location.y);
                                    intent.putExtra("objetParms", new MapWrapper().setMap(map));
                                    intent.putExtra("url", Urls.getBaseUrl() + "/eshop/managerAddress/changeAdress.html");
                                    startActivity(intent);
                                } else {
                                    getContext().startActivity(new Intent(getContext(), LoginActivity.class));
                                }
                            }
                        }
                    }).setOnceLocation(true)
                            .startLocation(getActivity());
                } else {
                    getContext().startActivity(new Intent(getContext(), LoginActivity.class));
                }

                break;
            case R.id.img_scan:


                RxPermissions rxPermissions = new RxPermissions(getActivity());
                rxPermissions.request(
                        //mTODO:meiyizhi 定位需要的权限
                        android.Manifest.permission.CAMERA, android.Manifest.permission.READ_PHONE_STATE)
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
            //带跑腿
            case R.id.img_1:
                if (!TextUtils.isEmpty(SharedPreferencesUtil.getStringData(getContext(), "ut", ""))) {
                    Intent intent = new Intent(getContext(), WebViewActivity.class);
                    intent.putExtra("url", Urls.getBaseUrl() + "/eshop/run/Run.html");
                    startActivity(intent);
                } else {
                    getContext().startActivity(new Intent(getContext(), LoginActivity.class));
                }
                break;
            case R.id.img_2:

                if (!TextUtils.isEmpty(SharedPreferencesUtil.getStringData(getContext(), "ut", ""))) {
                    Intent intent = new Intent(getContext(), WebViewActivity.class);
                    intent.putExtra("url", Urls.getBaseUrl() + "/eshop/919Selling/Selling.html");
                    startActivity(intent);
                } else {
                    getContext().startActivity(new Intent(getContext(), LoginActivity.class));
                }
                break;
            case R.id.img_3:
                if (!TextUtils.isEmpty(SharedPreferencesUtil.getStringData(getContext(), "ut", ""))) {
                    Intent intent = new Intent(getContext(), WebViewActivity.class);
                    intent.putExtra("url", Urls.getBaseUrl() + "/eshop/personalTailor/private.html");
                    startActivity(intent);
                } else {
                    getContext().startActivity(new Intent(getContext(), LoginActivity.class));
                }
                break;
            case R.id.img_4:
                if (!TextUtils.isEmpty(SharedPreferencesUtil.getStringData(getContext(), "ut", ""))) {
                    Intent intent = new Intent(getContext(), WebViewActivity.class);
                    intent.putExtra("url", Urls.getBaseUrl() + "/eshop/memberCommunity/Community.html");
                    startActivity(intent);
                } else {
                    getContext().startActivity(new Intent(getContext(), LoginActivity.class));
                }
                break;

            case R.id.huodong_rl:
                if (!TextUtils.isEmpty(SharedPreferencesUtil.getStringData(getContext(), "ut", ""))) {
                    Intent intent = new Intent(getContext(), WebViewActivity.class);
                    intent.putExtra("url", Urls.getBaseUrl() + "/eshop/hot/hot.html");
                    startActivity(intent);
                } else {
                    getContext().startActivity(new Intent(getContext(), LoginActivity.class));
                }
                break;
        }
    }

    @Override
    public void showBannerList(List<BannerModel.BannerData> list) {

        for (int i = 0; i < list.size(); i++) {
            View view = View.inflate(getContext(), R.layout.banner_item, null);
            SimpleDraweeView img1 = (SimpleDraweeView) view.findViewById(R.id.img_1);
            FrescoUtils.displayUrl(img1, Urls.getBaseUrl() + "/em/es_carousel/" + list.get(i).img);
            mlist.add(view);
        }

        adapter = new BannerAdapter(mlist);

        pager.setAdapter(adapter);

        indicator.setViewPager(pager);

        mHandler.postDelayed(runnableForViewPager, TIME);

    }

    Runnable runnableForViewPager = new Runnable() {
        @Override
        public void run() {
            try {
                itemPosition++;
                mHandler.postDelayed(this, TIME);
                pager.setCurrentItem(itemPosition % mlist.size());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    @Override
    public void showHorList(List<HorlistModel.HorData> list) {
        horListAdapter = new HorListAdapter(getContext(), list);
        horRecycle.setAdapter(horListAdapter);
        horListAdapter.setOnfenleiClickListener(new HorListAdapter.OnfenleiClickListener() {
            @Override
            public void jump(String id, int pos) {

                MainTabsActivity activity = (MainTabsActivity) getContext();
                activity.switchFragment(id, pos);
            }
        });

    }

    @Override
    public void showQiangGouList(List<QiangGouModel.QiangGouData> list) {
        recyclerView.setAdapter(new QiangGouAdapter(getContext(), list));
    }


    @Override
    public void showJingXuanList(List<JingXuanModel.Data> list) {
        jingXuanAdapter = new JingXuanAdapter(getContext(), list);
        listView.setAdapter(jingXuanAdapter);

    }


    @Override
    public void showMes(String mes) {
        Toast.makeText(getContext(), mes, Toast.LENGTH_LONG).show();


    }


    @Override
    public void showProductList(List<ProductModel.Result> list) {
        productAdapter = new ProductListAdapter(getContext(), list);
        productListView.setAdapter(productAdapter);


    }

    @Subscribe
    public void setContent(AddToCartEvent event) {
        if (!TextUtils.isEmpty(SharedPreferencesUtil.getStringData(getContext(), "ut", ""))) {
            presenter.addtoCart(event.id, "1");
        } else {
            getContext().startActivity(new Intent(getContext(), LoginActivity.class));
        }
    }

    public void getSearchText() {
        Intent intent = new Intent(getContext(), WebViewActivity.class);
        LinkedHashMap<String, String> map = new LinkedHashMap<>();

        map.put("productGuid", "noParam");

        map.put("productName", search_product.getText().toString());

        intent.putExtra("objetParms", new MapWrapper().setMap(map));
        intent.putExtra("url", Urls.getBaseUrl() + "/eshop/classification/neiye.html");
        this.startActivity(intent);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        OttoBus.getInstance().unregister(this);
        if (locationManager != null) {
            locationManager.stopLocation();
        }
    }
}
