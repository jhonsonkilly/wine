package com.androidyuan.frame.base.activity;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by mac on 2017/10/16.
 */

public class WineApplication extends Application {

    private static Context instance;

    public static final boolean isDebug = true;


    public static Context gainContext() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        Fresco.initialize(this);
        //设置假ut测试
        //SharedPreferencesUtil.saveStringData(this, "ut", "abc");



    }


}
