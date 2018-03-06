package com.activity;

import android.app.Application;
import android.content.Context;

import com.androidyuan.frame.base.activity.BaseApplication;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;


/**
 * Created by mac on 2017/10/16.
 */

public class WineApplication extends BaseApplication {



    @Override
    public void onCreate() {
        super.onCreate();




        PlatformConfig.setWeixin(" wx1ab2725d03ce8cc6 ","8bdc4b5ce605bd90ba78b794d9245402");

        UMShareAPI.get(this);




    }


}
