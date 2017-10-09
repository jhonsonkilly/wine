package com.androidyuan.frame.cores;

import android.app.Application;
import com.androidyuan.frame.cores.log.CommonLogger;
import com.androidyuan.frame.cores.utils.image.FrescoUtils;

/**
 * Created by wei on 17-6-7.
 */

public abstract class FrameApplication extends Application {

    private static FrameApplication sApp;

    public FrameApplication() {

        super();
        sApp = this;
    }

    public static FrameApplication getApp() {

        return sApp;
    }

    @Override
    public void onCreate() {

        super.onCreate();
        CommonLogger.initDebugConfig(this);
        GlobalDirConfig.init(this);

        FrescoUtils.initFresco(this);
    }

    public abstract String getHostUrl();

    public abstract String getUserid();
}
