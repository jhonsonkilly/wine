package com.utils;

import com.androidyuan.frame.base.activity.WineApplication;

/**
 * Created by mac on 2017/10/29.
 */

public class Urls {
    public static final boolean isDebug = WineApplication.isDebug;

    //如下几个地方无法在服务端配置
    private static final String TEST_INTERFACE_CONFIGURE_URL = "http://47.93.18.21";
    private static final String ONLINE_INTERFACE_CONFIGURE_URL = "";

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
    //右侧列表接口
    public static final String m_rightlist = getBaseUrl() + "/eshop/app/goods/getProductClassByParent";

    public static final String m_homebannerlist = getBaseUrl() + "/eshop/app/goods/getSlidShowList";

    public static final String m_horlist = getBaseUrl() + "/eshop/app/goods/getFirstGradeGoodsClass";

    public static final String m_qianggoulist = getBaseUrl() + "/eshop/app/goods/getTimeBuyGoodsList";

    public static final String m_jingxuanlist = getBaseUrl() + "/eshop/app/goods/get919ChoiceGoodsList";

    public static final String m_productlist = getBaseUrl() + "/eshop/app/goods/get919ProductList";

    public static final String m_addtocartlist = getBaseUrl() + "/eshop/app/purchase/goodToCart";

    public static final String m_getVertifyCode = getBaseUrl() + "/eshop/app/user/sendVerifyCode";

    public static final String m_login = getBaseUrl() + "/eshop/app/user/login";

    public static final String m_register = getBaseUrl() + "/eshop/app/user/register";

    public static final String m_logout = getBaseUrl() + "/eshop/app/user/logout";

    public static final String m_delVertifyCode = getBaseUrl() + "/eshop/app/user/delVerifyCode";

    public static final String m_cartList = getBaseUrl() + "/eshop/app/purchase/getGoodsFromCart";

    public static final String m_personalList = getBaseUrl() + "/eshop/app/member/getMemberInfo";




}
