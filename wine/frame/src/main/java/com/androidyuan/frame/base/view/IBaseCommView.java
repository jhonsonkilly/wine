package com.androidyuan.frame.base.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

/**
 * Created by wei on 2015/6/18.
 * <p>
 * 再多的言语无法解释这个类
 */
public interface IBaseCommView {

    ////获得一个Frag或者Act的handler  用于消息处理
    Handler getHandler();

    //获得一个Intent  用户获得一些由上一个页面传过来的 一些数据
    Intent getViewIntent();

    //获得这个Context上下文
    Activity getActivity();

    //当前IView是否显示
    boolean isVisible();

    void handlerMsg(Message msg);


    //
    void showToast(String str_msg);


    //让页面跳转并传一堆数据
    void to(Class<?> cls, Bundle bundles);

    void clearResource();

    void hideProgressBar();

    void showProgressBar();

    void sendBroadcastFilter(String filter);

    void onReciver(String filter, Intent intent);


}
