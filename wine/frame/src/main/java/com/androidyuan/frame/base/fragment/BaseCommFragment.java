package com.androidyuan.frame.base.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.androidyuan.frame.base.activity.BaseCommActivity;
import com.androidyuan.frame.base.presenter.BaseCommPresenter;
import com.androidyuan.frame.base.protocal.http.HttpTool;
import com.androidyuan.frame.base.protocal.http.RequestMsg;
import com.androidyuan.frame.base.protocal.http.ResponseMsg;
import com.androidyuan.frame.base.view.IBaseCommView;

import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;

//如果不想要顶部标题栏可以采用先添加后gone的方式
//或者继承的子类里面override  findAButton方法

public abstract class BaseCommFragment<P extends BaseCommPresenter> extends Fragment implements IBaseCommView, OnClickListener {

    protected static final String REQ_ERR_TOSET_STR = "呜呜,请求失败了...";
    protected P presenter;
    private Handler mHandler;
    private Activity attachActivity;

    private boolean isFirstShow = true;

    public BaseCommFragment() {

        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {

                switch (msg.what) {
                    default: {
                        if (getActivity() != null) {
                            handlerMsg(msg);
                        }
                    }
                    break;
                }

            }
        };

        generatorPresenter();

        presenter.setIView(this);


    }

    protected void generatorPresenter() {

        try {
            Class<P> cls = ((Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
            Constructor[] constructors = cls.getDeclaredConstructors();
            constructors[0].setAccessible(true);//这句代码 意义不大 仅仅防止构造函数private
            presenter = cls.newInstance();

        }
        catch (java.lang.InstantiationException e) {
            e.printStackTrace();//这个异常通常不会发生 除非你的泛型类型是 Integer Boolean Long 这些
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();  //
        }
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // 基类必须要重写getLayoutId() 请看下面 ||||||||
        View rootView = inflater.inflate(getLayoutId(), container, false);
        //关闭注解
        //ViewUtils.inject(this, rootView);

        presenter.initData(savedInstanceState);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);


        initAllWidget(view);
        firstShow();
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

        return getAttachActivity().getIntent();
    }


    @Override
    public void to(Class<?> cls, Bundle bundles) {

        Intent intent = new Intent(getAttachActivity(), cls);
        intent.putExtras(bundles);
        startActivity(intent);
    }


    @Override
    public void onResume() {

        super.onResume();
        presenter.onResume();

    }

    @Override
    public void onPause() {

        presenter.onPause();
        super.onPause();

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
    abstract protected void initAllWidget(View view);

    protected abstract void clickView(View v);


    //获取这个类的名字的字符串  如:LoginActivity
    public String getClassName() {

        return this.getClass().getSimpleName();
    }


    @Override
    public void hideProgressBar() {

    }


    @Override
    public void showProgressBar() {

    }

    public void clearResource() {

        getAttachActivity().finish();
    }

    @Override
    public void onDestroy() {

        super.onDestroy();

        getHandler().removeCallbacksAndMessages(null);

        presenter.onDestory();
    }

    /**
     * 是否需要打点
     * <p>
     * 只针对view和leave两种。若启用，可以自动为页面打上view和leave两种点。
     * 注意需要同时覆写getPageName。并且最好只用于单一页面的Fragment，
     * 像首页的Activity其中包含了多个Fragment，不建议这样做。
     *
     * @return 是否需要打点，默认不需要。
     */
    public boolean isNeedLogger() {

        return false;
    }

    /**
     * 获取页面名称
     * <p>
     * 若启用了isNeedLogger，那么可以同时返回页面名称，实现自动打view和leave两种点。
     *
     * @return 页面名称
     */
    public String getPageName() {

        return null;
    }

    public void showToast(final String str) {

        attachActivity.runOnUiThread(() -> showToast(str,true) );
    }


    public void showToast(final String str, final boolean centerFlag) {

        ((BaseCommActivity)attachActivity).showToast(str);
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
    public void to(Context context, Class<? extends Activity> cls) {

        Intent intent = new Intent(context, cls);
        context.startActivity(intent);
    }


    public void onReciver(String filter, Intent intent) {

    }


    /**
     * 开始数据请求
     *
     * @param req
     * @param res
     */
    protected void sendHttpPost(RequestMsg req, final ResponseMsg res) {

        HttpTool.getClient().requestPost(req, res, getHandler());
    }


    /**
     * 开始数据请求
     *
     * @param req
     * @param res
     */
    protected void sendHttpGet(RequestMsg req, final ResponseMsg res) {

        HttpTool.getClient().requestGet(req, res, getHandler());
    }

    @Override
    public void onAttach(Activity activity) {

        attachActivity = activity;
        super.onAttach(activity);
    }

    public Activity getAttachActivity() {

        return attachActivity;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {

        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isFirstShow) {
            isFirstShow = false;
        }
    }

    protected void firstShow() {

        presenter.firstShow();
    }

    @Override
    public void sendBroadcastFilter(String filter) {

        LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(new Intent(filter));
    }


}