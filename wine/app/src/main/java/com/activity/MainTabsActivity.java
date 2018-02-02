package com.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.Event.AddToCartNumberEvent;
import com.Event.GoHomeEvent;
import com.Event.GoShoppingCartEvent;
import com.androidyuan.frame.base.activity.BaseCommActivity;
import com.androidyuan.frame.cores.utils.SharedPreferencesUtil;
import com.fragment.ClassifyFragment;
import com.fragment.HomeFragment;
import com.fragment.MineFragment;
import com.fragment.ServiceFragment;
import com.fragment.ShoppingCartFragment;
import com.iview.IMainTabsView;
import com.model.PersonalModel;
import com.otto.OttoBus;
import com.otto.Subscribe;
import com.presenter.MainTabsPresenter;
import com.utils.Urls;
import com.viewpagerindicator.TabPageIndicator;
import com.widget.TabChooser;
import com.widget.TabChooserBean;
import com.widget.TabSelectListener;

import java.util.ArrayList;
import java.util.List;

import zjw.wine.R;


/**
 * Created by mac on 2017/10/16.
 */

public class MainTabsActivity extends BaseCommActivity<MainTabsPresenter> implements IMainTabsView {


    private String[] titleArr;
    private TabChooser tab_bar;
    private List<TabChooserBean> list = new ArrayList<>();

    private Fragment newFragment;
    private Fragment oldFragment;

    private HomeFragment fragmentHome;
    private ClassifyFragment fragmentClass;
    private ServiceFragment fragmentDiscover;

    private MineFragment fragmentMy;
    private ShoppingCartFragment fragmentShoppingCart;

    protected boolean bActive = true;



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
        //请求购物车数量

        OttoBus.getInstance().register(this);

        if (!TextUtils.isEmpty(SharedPreferencesUtil.getStringData(this, "ut", ""))) {

            presenter.getPersonalMes();
        }


    }

    @Override
    public void setData(PersonalModel.PersonalResult model) {
        if(model!=null){
            AddToCartNumberEvent event = new AddToCartNumberEvent();
            event.result = model.cartItemCount;
            OttoBus.getInstance().post(event);
        }

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
                //this.startActivity(new Intent(this, LoginActivity.class));

                break;
            case R.id.text_cancel:
                if (dialog != null) {
                    dialog.dismiss();
                    dialog = null;
                }
                break;
            case R.id.phone:
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + view.getText().toString()));
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


    public void switchFragment(String id, int pos) {
        tab_bar.setCurrentItem(1);
        oldFragment = newFragment;
        switchFragment(oldFragment, fragmentClass);

        newFragment = fragmentClass;
        fragmentClass.getSwitchClick(id, pos);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OttoBus.getInstance().unregister(this);
    }


    @Subscribe
    public void goHome(GoHomeEvent event) {
        tab_bar.setCurrentItem(0);
        oldFragment = newFragment;
        switchFragment(oldFragment, fragmentHome);
        newFragment = fragmentHome;

    }

    @Subscribe
    public void getNumber(AddToCartNumberEvent event) {
        if(!TextUtils.isEmpty(event.result)){
            tab_bar.showDot(3, true, Integer.parseInt(event.result));
        }

    }

    @Subscribe
    public void backToShopCart(GoShoppingCartEvent event) {
        tab_bar.setCurrentItem(3);
        oldFragment = newFragment;
        switchFragment(oldFragment, fragmentShoppingCart);
        newFragment = fragmentShoppingCart;

    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        exitApp();
    }

    long[] mHits = new long[2];

    //定义一个所需的数组
    private void exitApp() {
//         数组向左移位操作
        System.arraycopy(mHits, 1, mHits, 0, mHits.length - 1);
        mHits[mHits.length - 1] = SystemClock.uptimeMillis();
        if (mHits[0] >= (SystemClock.uptimeMillis() - 2000)) {// 2000代表设定的间隔时间
            finish();
        } else {
            Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {

        if (event.getKeyCode() == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                /*隐藏软键盘*/


            InputMethodManager inputMethodManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (inputMethodManager.isActive()) {
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }
            fragmentHome.getSearchText();


            // 上传代码
            return true;
        }
        return super.dispatchKeyEvent(event);
    }


}
