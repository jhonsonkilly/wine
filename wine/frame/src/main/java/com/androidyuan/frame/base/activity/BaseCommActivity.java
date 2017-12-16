package com.androidyuan.frame.base.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.androidyuan.frame.base.presenter.BaseCommPresenter;
import com.androidyuan.frame.base.view.IBaseCommView;
import com.androidyuan.frame.cores.boradcast.OnReciverListener;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;

//如果不想要顶部标题栏可以采用先添加后gone的方式
//或者继承的子类里面override  findAButton方法

public abstract class BaseCommActivity<P extends BaseCommPresenter> extends AppCompatActivity implements IBaseCommView, OnClickListener, OnReciverListener {


    protected P presenter;

    //当前activity是否显示
    private boolean isShowThis;
    private Handler mHandler;


    private Dialog mDialog;

    public BaseCommActivity() {

        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {

                if (!isFinishing()) {
                    handlerMsg(msg);
                }
            }
        };

        generatorPresenter();
        if(presenter!=null){
            presenter.setIView(this);
        }

    }

    //修复flyme问题
    public static boolean setMeizuStatusBarDarkIcon(Activity activity, boolean dark) {

        boolean result = false;
        if (activity != null) {
            try {
                WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
                Field darkFlag = WindowManager.LayoutParams.class
                        .getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                Field meizuFlags = WindowManager.LayoutParams.class
                        .getDeclaredField("meizuFlags");
                darkFlag.setAccessible(true);
                meizuFlags.setAccessible(true);
                int bit = darkFlag.getInt(null);
                int value = meizuFlags.getInt(lp);
                if (dark) {
                    value |= bit;
                }
                else {
                    value &= ~bit;
                }
                meizuFlags.setInt(lp, value);
                activity.getWindow().setAttributes(lp);
                result = true;
            }
            catch (Exception e) {
            }
        }
        return result;
    }

    //修复miui问题
    public static boolean setMiuiStatusBarDarkMode(Activity activity, boolean darkmode) {

        Class<? extends Window> clazz = activity.getWindow().getClass();
        try {
            int darkModeFlag = 0;
            Class<?> layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
            darkModeFlag = field.getInt(layoutParams);
            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
            extraFlagField.invoke(activity.getWindow(), darkmode ? darkModeFlag : 0, darkModeFlag);
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    protected void generatorPresenter() {

        try {
            Class<P> cls = ((Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
            Constructor[] constructors = cls.getDeclaredConstructors();
            constructors[0].setAccessible(true);//这句代码 意义不大 仅仅防止构造函数private
            presenter = cls.newInstance();

        }
        catch (InstantiationException e) {
            e.printStackTrace();//这个异常通常不会发生 除非你的泛型类型是 Integer Boolean Long 这些
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();  //
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

       // getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(getLayoutId());
        presenter.initData(savedInstanceState);

        //topbar=(Topbar)findViewById(R.id.topbar); TODO
        initAllWidget();
        presenter.firstShow();


    }

    public P getPresenter() {

        return presenter;
    }

    @TargetApi(19)
    protected void setStatusBarRes(int resid) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Window win = getWindow();
            WindowManager.LayoutParams winParams = win.getAttributes();

            final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            winParams.flags |= bits;

            //winParams.flags &= ~bits;

            win.setAttributes(winParams);

            setMiuiStatusBarDarkMode(this, true);
            setMeizuStatusBarDarkIcon(this, true);
        }
    }


    //这里只需要fragment的布局文件即可  return R.layout.xxxxxx
    protected abstract int getLayoutId();

    //实例化presenter 实例化之后可以做一些其他操作
    //protected abstract void initPresenter();

    @Override
    public Handler getHandler() {

        return mHandler;
    }

    @Override
    public void handlerMsg(Message msg) {
        //handlermsg  没有在new Handler的时候就进行处理 是为了方式presenter 会有新的presenter 带来的地址变更
        presenter.handMsg(msg);
    }

    //获得这个必要的Intent 用于处理一堆的数据
    @Override
    public Intent getViewIntent() {

        return getIntent();
    }

    @Override
    public Activity getActivity() {

        return BaseCommActivity.this;
    }

    @Override
    public void to(Class<?> cls, Bundle bundles) {

        Intent intent = new Intent(this, cls);
        intent.putExtras(bundles);
        startActivity(intent);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);
        presenter.onSaveInstanceState(outState);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {

        isShowThis = hasFocus;//这里才是真正的act显示与隐藏的方法  onPause onStop都不准确的
        if (hasFocus) {
            presenter.onWindowShow();
        }
    }

    @Override
    protected void onResume() {

        super.onResume();
        presenter.onResume();
    }

    @Override
    protected void onPause() {

        super.onPause();
        presenter.onPause();
    }


    // 不要继承这个 继承于clickView
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            default:
                clickView(view);
        }
    }

    //实例化一些控件 包含控件的一些事件绑定的问题
    abstract protected void initAllWidget();


    protected abstract void clickView(View v);


    @Override  //利用重写这个方法设置新页面打开的动画
    public void startActivity(Intent intent) {

        super.startActivity(intent);
    }

    /**
     * 清理资源
     */
    @Override
    public synchronized void clearResource() {

        if (!isFinishing()) {
            finish();
        }
    }


    @Override
    protected void onDestroy() {

        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);

        presenter.onDestory();
    }

    public void showToast(final String str) {

        toast(str, true);
    }

    public void toast(final String str, final boolean centerFlag) {

        if (!TextUtils.isEmpty(str)) {
            runOnUiThread(() -> {
                Toast mToast = Toast.makeText(getApplication(), "" + str, Toast.LENGTH_LONG);
                if (centerFlag) {
                    mToast.setGravity(Gravity.CENTER, 0, 0);
                }
                mToast.show();
            });
        }
    }

    public void toast(final String str, final int duration) {

        if (!TextUtils.isEmpty(str)) {
            runOnUiThread(() ->
                    Toast.makeText(getApplication(), "" + str, duration).show()
            );
        }
    }


    public void to(Class<?> cls) {

        to(cls, new Bundle());
    }

    /**
     * 跳转页面
     *
     * @param context
     * @param cls
     */
    public void to(Context context, Class<?> cls) {

        Intent intent = new Intent(context, cls);
        context.startActivity(intent);
    }

    @Override
    public boolean isVisible() {

        return isShowThis;
    }

    @Override
    public void onBackPressed() {

        hideProgressBar();
        super.onBackPressed();
        clearResource();
    }


    public abstract void setTopTitle(String title);


    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {

        switch (event.getKeyCode()) {
            case KeyEvent.KEYCODE_DPAD_CENTER:
            case KeyEvent.KEYCODE_ENTER:
                return true;
        }
        return super.dispatchKeyEvent(event);
    }


    @Override
    public void hideProgressBar() {

        if (mDialog != null) {
            mDialog.dismiss();
        }
    }


    @Override
    public abstract void showProgressBar();


    //接受可以 但是注册必须要在presenter 中 毕竟presenter 才是真正的处理逻辑的地方
    public void onReciver(String filter, Intent intent) {

    }


    protected <T extends View> T getView(int resId) {

        T view = (T) findViewById(resId);
        if (view == null) {
            throw new IllegalArgumentException("找不到id对应的view");
        }
        return view;
    }

    @Override
    public void sendBroadcastFilter(String filter) {

        LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(new Intent(filter));
    }

}