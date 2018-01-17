package com.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.Event.AddToCartEvent;
import com.Event.AddToCartNumberEvent;
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
import com.banner.BannerBaseAdapter;
import com.banner.BannerView;
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
import com.viewpagerindicator.TabPageIndicator;

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

    private ViewPager pager;

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
    private BannerView bannerView;
    private LinearLayout ll;
    private ArrayList<View> indicationList;

    @Override
    protected int getLayoutId() {
        return R.layout.frg_home;
    }

    @Override
    protected void initAllWidget(View view) {
        view.findViewById(R.id.img_dignwei).setOnClickListener(this);
        view.findViewById(R.id.img_scan).setOnClickListener(this);

        bannerView = (BannerView) view.findViewById(R.id.bannerView);

        ll = view.findViewById(R.id.corn_ll);


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

    private void bannerPointLight(int currentPoint) {
        for (int i = 0; i < indicationList.size(); i++) {
            if (currentPoint == i) {
                indicationList.get(i).setBackgroundResource(R.drawable.home_top_ic_point_on);
            } else {
                indicationList.get(i).setBackgroundResource(R.drawable.home_top_ic_point_off);
            }
        }
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
                                    map.put("lng", location.x);
                                    map.put("lat", location.y);
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
            //带跑腿
            case R.id.img_1:
                if (!TextUtils.isEmpty(SharedPreferencesUtil.getStringData(getContext(), "ut", ""))) {
                    Intent intent = new Intent(getContext(), WebViewActivity.class);
                    intent.putExtra("url", Urls.getBaseUrl() + "/eshop/run/Run.html");
                    startActivity(intent);
                } else {
                    getContext().startActivity(new Intent(getContext(), LoginActivity.class));
                }
                //presenter.getHomePage();
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

    BannerAdapter mAdapter;

    @Override
    public void showBannerList(final List<BannerModel.BannerData> list) {


        bannerView.setAdapter(mAdapter = new BannerAdapter(getContext()));
        mAdapter.setOnPageTouchListener(new BannerBaseAdapter.OnPageTouchListener<BannerModel.BannerData>() {
            @Override
            public void onPageClick(int position, BannerModel.BannerData bannerBean) {
                // 页面点击
                if(!TextUtils.isEmpty(bannerBean.linkUrl)){
                    if(bannerBean.linkUrl.startsWith("http")){
                        Intent intent = new Intent(getContext(), WebViewActivity.class);
                        intent.putExtra("url", bannerBean.linkUrl);
                        getContext().startActivity(intent);
                    }else{

                        if (!TextUtils.isEmpty(SharedPreferencesUtil.getStringData(getContext(), "ut", ""))) {
                            Intent intent = new Intent(getContext(), WebViewActivity.class);
                            LinkedHashMap<String, String> map = new LinkedHashMap<>();

                            map.put("productGuid", bannerBean.linkUrl);


                            intent.putExtra("objetParms", new MapWrapper().setMap(map));
                            intent.putExtra("url", Urls.getBaseUrl() + "/eshop/commodity/commodity.html");
                            getContext().startActivity(intent);
                        } else {
                            getContext().startActivity(new Intent(getContext(), LoginActivity.class));

                        }

                    }
                }
            }

            @Override
            public void onPageDown() {

                bannerView.stopAutoScroll();
            }

            @Override
            public void onPageUp() {

                bannerView.startAutoScroll();
            }
        });
        mAdapter.setData(list);

        indicationList = new ArrayList<View>();
        for (int i = 0; i < list.size(); i++) {

            ImageView iv2 = new ImageView(getContext());
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(12, 0, 0, 0);
            iv2.setLayoutParams(lp);
            if (i == 0) {
                iv2.setBackgroundResource(R.drawable.home_top_ic_point_on);
            } else {
                iv2.setBackgroundResource(R.drawable.home_top_ic_point_off);
            }
            indicationList.add(iv2);
            //添加到圆点布局
            ll.addView(iv2);
        }

        bannerView.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                bannerPointLight(position % indicationList.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



    }



    private class BannerAdapter extends BannerBaseAdapter<BannerModel.BannerData> {

        @Override
        protected void convert(View convertView, BannerModel.BannerData data) {
            setImageUrl(R.id.img_1, Urls.getBaseUrl() + "/em/es_carousel/" + data.img);

        }

        public BannerAdapter(Context context) {
            super(context);
        }

        @Override
        protected int getLayoutResID() {
            return R.layout.banner_item;
        }


    }

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
    public void showProductList(List<ProductModel.Result> list) {
        productAdapter = new ProductListAdapter(getContext(), list);
        productListView.setAdapter(productAdapter);


    }

    @Override
    public void showMes(String mes, String number) {
        Toast.makeText(getContext(), mes, Toast.LENGTH_LONG).show();
        AddToCartNumberEvent event = new AddToCartNumberEvent();
        event.result = number;
        OttoBus.getInstance().post(event);
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
