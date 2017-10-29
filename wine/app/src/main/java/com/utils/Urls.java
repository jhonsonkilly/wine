package com.utils;

import com.activity.WineApplication;

/**
 * Created by mac on 2017/10/29.
 */

public class Urls {
    public static final boolean isDebug = WineApplication.isDebug;

    //如下几个地方无法在服务端配置
    private static final String TEST_INTERFACE_CONFIGURE_URL = "http://test.quanmin.tv:8301/udata/getdata";
    private static final String ONLINE_INTERFACE_CONFIGURE_URL = "47.93.18.21";

    public static String getInterfaceConfigureUrl() {
        if (isDebug) {
            return TEST_INTERFACE_CONFIGURE_URL;
        } else {
            return ONLINE_INTERFACE_CONFIGURE_URL;
        }
    }


    public static String getBaseUrl() {
        return getInterfaceConfigureUrl();
    }
    //左侧列表接口
    public static final String m_leftlist = getBaseUrl() + "/eshop/app/goods/getFirstGradeGoodsClass";

}
