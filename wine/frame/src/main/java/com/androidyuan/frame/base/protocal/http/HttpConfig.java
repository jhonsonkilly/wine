package com.androidyuan.frame.base.protocal.http;


import com.androidyuan.frame.cores.FrameApplication;

/**
 * Created by wei on 2015/6/19.
 */
public class HttpConfig {

    //超时时间
    static final int TIMEOU_MS = 5 * 1000;
    static final int RETRY_COUT = 2;

    //基础api,V1
    private static String BASE_URL = FrameApplication.getApp().getHostUrl();

    public static void setHostUrl(String str) {

        BASE_URL = str;
    }

    public static String getBaseUrl() {

        return BASE_URL;
    }

}
