package com.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.androidyuan.frame.base.activity.BaseCommActivity;
import com.config.Constants;
import com.fragment.ClassifyFragment;
import com.fragment.HomeFragment;
import com.fragment.MineFragment;
import com.fragment.ServiceFragment;
import com.fragment.ShoppingCartFragment;
import com.presenter.MainTabsPresenter;
import com.widget.TabChooser;
import com.widget.TabChooserBean;
import com.widget.TabSelectListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import zjw.wine.R;


/**
 * Created by mac on 2017/10/16.
 */

public class MainTabsActivity extends BaseCommActivity<MainTabsPresenter> {

    public static final int HOME_FRAGMENT = 0;
    public static final int CLASS_FRAGMENT = 1;
    public static final int LIVE_FRAGMETN = 2;
    public static final int SHOPCART_FRAGMENT = 3;
    public static final int MY_FRAGMENT = 4;

    private String[] titleArr;
    private TabChooser tab_bar;
    private List<TabChooserBean> list = new ArrayList<>();

    private Fragment newFragment;
    private Fragment oldFragment;

    private HomeFragment fragmentHome;
    private ClassifyFragment fragmentClass;
    private ServiceFragment fragmentDiscover;
    //    private LiveListFragment liveFragment;
    private MineFragment fragmentMy;
    private ShoppingCartFragment fragmentShoppingCart;

    protected boolean bActive = true;

    private List<Fragment> fragments = new ArrayList<>();

    private int[] imgArr = new int[]{R.drawable.select_home, R.drawable.select_classification, R.drawable.select_discover, R.drawable.select_buycart, R.drawable.select_my};
    private Dialog dialog;
    private TextView view;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_tabs_main;
    }

    @Override
    protected void initAllWidget() {
        titleArr = getResources().getStringArray(R.array.home_tabs);
        tab_bar = (TabChooser) findViewById(R.id.tab_bar);
        tab_bar.setTabSelectListener(new TabSelectListener() {
            @Override
            public void select(int position) {
                switch (position) {
                    case 0:
                        oldFragment = newFragment;
                        switchFragment(oldFragment, fragmentHome);
                        newFragment = fragmentHome;

                        break;
                    case 1:
                        oldFragment = newFragment;
                        switchFragment(oldFragment, fragmentClass);
                        newFragment = fragmentClass;

                        break;
                    case 2:
                        break;
                    case 3:
                        oldFragment = newFragment;
                        switchFragment(oldFragment, fragmentShoppingCart);
                        newFragment = fragmentShoppingCart;

                        break;
                    case 4:
                        oldFragment = newFragment;
                        switchFragment(oldFragment, fragmentMy);
                        newFragment = fragmentMy;
                        //StatusBarCompat.setStatusBarColor(MainActivity.this, Color.parseColor("#FFB300"), false);
                        break;
                    default:
                        break;
                }
            }
        });
        fragmentHome = new HomeFragment();
        fragmentClass = new ClassifyFragment();
//        liveFragment = new LiveListFragment();
        fragmentDiscover = new ServiceFragment();

//
        fragmentMy = new MineFragment();

//        fragmentMy.setUrl(OdyApplication.H5URL + "/test.html");
        fragmentShoppingCart = new ShoppingCartFragment();
        fragments.add(fragmentHome);
        fragments.add(fragmentClass);
//        fragments.add(liveFragment);
        fragments.add(fragmentShoppingCart);
        fragments.add(fragmentMy);
        for (int i = 0; i < titleArr.length; i++) {
            TabChooserBean bean = new TabChooserBean();
            bean.imagesrc = imgArr[i];
            bean.tabcontent = titleArr[i];
            list.add(bean);
        }
        newFragment = fragmentHome;
        addFragment(newFragment);
        tab_bar.setTabList(list);
        findViewById(R.id.img_kefu).setOnClickListener(this);


    }

    public void switchFragment(Fragment from, Fragment to) {
        if (!bActive) return;
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (to.isAdded() || fragmentManager.getFragments().contains(to)) {
            // 隐藏当前的fragment，显示下一个
            fragmentManager.beginTransaction().hide(from).show(to).commitAllowingStateLoss();
        } else {
            // 隐藏当前的fragment，add下一个到Activity中
            fragmentManager.beginTransaction().hide(from).add(R.id.centerlayout, to).commitAllowingStateLoss();
        }
    }

    public void addFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (!fragment.isAdded()) {
            fragmentManager.beginTransaction().add(R.id.centerlayout, fragment).commitAllowingStateLoss();
        }
    }


    @Override
    protected void clickView(View v) {
        switch (v.getId()) {
            case R.id.img_kefu:
                if (dialog == null) {
                    dialog = new Dialog(this, R.style.MyDialog);

                    dialog.setContentView(R.layout.layout_servicedialog);

                    ((TextView) dialog.findViewById(R.id.text_cancel)).setOnClickListener(this);
                    view = (TextView) dialog.findViewById(R.id.phone);
                    view.setOnClickListener(this);

                    dialog.show();
                } else {
                    dialog.show();
                }

                break;
            case R.id.text_cancel:
                if (dialog != null) {
                    dialog.dismiss();
                    dialog = null;
                }
                break;
            case R.id.phone:
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+view.getText().toString()));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                dialog.dismiss();
                break;
        }
    }

    @Override
    public void setTopTitle(String title) {

    }

    @Override
    public void showProgressBar() {

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        int chooseItem = intent.getIntExtra(Constants.GO_MAIN, 0);
        setFragment(chooseItem);


    }

    public void setFragment(int code) {
        switch (code) {
            case HOME_FRAGMENT:
                tab_bar.setCurrentItem(0);
                oldFragment = newFragment;
                switchFragment(oldFragment, fragmentHome);
                newFragment = fragmentHome;
                break;
            case CLASS_FRAGMENT:
                tab_bar.setCurrentItem(1);
                oldFragment = newFragment;
                switchFragment(oldFragment, fragmentClass);
                newFragment = fragmentClass;
                break;
            case LIVE_FRAGMETN:
                tab_bar.setCurrentItem(2);
                oldFragment = newFragment;
                switchFragment(oldFragment, fragmentDiscover);
                newFragment = fragmentDiscover;
                break;
            case SHOPCART_FRAGMENT:
                tab_bar.setCurrentItem(3);
                oldFragment = newFragment;
                switchFragment(oldFragment, fragmentShoppingCart);
                newFragment = fragmentShoppingCart;
                break;
            case MY_FRAGMENT:
                tab_bar.setCurrentItem(4);
                oldFragment = newFragment;
                switchFragment(oldFragment, fragmentMy);
                newFragment = fragmentMy;
                break;
            default:
                break;
        }
    }
}
