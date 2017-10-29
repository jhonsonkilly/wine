package com.activity;

import android.app.Application;
import android.content.Context;

/**
 * Created by mac on 2017/10/16.
 */

public class WineApplication extends Application {

    private static Context instance;

    public static final boolean isDebug = false;


    public static Context gainContext() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

    }
}
