package com.config;

import com.androidyuan.frame.base.activity.BaseApplication;
import com.utils.Md5Utils;

/**
 * Created by mac on 18/1/13.
 */

public class UrlConfig {


    public static final boolean isDebug = BaseApplication.isDebug;

    //如下几个地方无法在服务端配置
    private static final String TEST_INTERFACE_CONFIGURE_URL = "http://tz.tensdo.com";
    private static final String ONLINE_INTERFACE_CONFIGURE_URL = "http://tz.tensdo.com";

    private static final String HTTP_SIGN_SOURCE = "gm8Pyx3sbuCdqsspYylv3rhh9Bt40vn7";

    public static String getInterfaceConfigureUrl() {
        if (isDebug) {
            return TEST_INTERFACE_CONFIGURE_URL;
        } else {
            return ONLINE_INTERFACE_CONFIGURE_URL;
        }
    }


    /**
     * @author Chris
     * @time 18/1/13 上午2:26
     * @function
     */
    private static String buildAuthParams(String key) {

        StringBuilder sb = new StringBuilder();
        sb.append(LoginHelper.token());
        sb.append(key);
        sb.append(HTTP_SIGN_SOURCE);
        sb.append(LoginHelper.getClient());
        String authString = Md5Utils.md5ForString(sb.toString());
        return authString;
    }

    private static String buildAllParams() {
        String key = System.currentTimeMillis() + "";
        StringBuilder sb = new StringBuilder();
        sb.append("?sign=" + buildAuthParams(key));
        sb.append("&access_token=" + LoginHelper.token());
        sb.append("&timestamp=" + key);
        sb.append("&client=" + LoginHelper.getClient());
        return sb.toString();
    }

    //短信接口
    public static String sendSmsUrl(String phone) {
        StringBuilder sb = new StringBuilder(buildAllParams());
        sb.append("&phone=" + phone);
        String uriString = getInterfaceConfigureUrl() + "/api/send_reg_code" + sb.toString();
        return uriString;
    }
    //注册接口
    public static String registUrl() {
        StringBuilder sb = new StringBuilder(buildAllParams());
        String uriString = getInterfaceConfigureUrl() + "/api/register" + sb.toString();
        return uriString;
    }
    //退出登录接口
    public static String cancelLoginUrl() {
        StringBuilder sb = new StringBuilder(buildAllParams());
        String uriString = getInterfaceConfigureUrl() + "/api/logout" + sb.toString();
        return uriString;
    }
    //首页
    public static String homePageUrl() {
        StringBuilder sb = new StringBuilder(buildAllParams());
        String uriString = getInterfaceConfigureUrl() + "/api/index" + sb.toString();
        return uriString;
    }
    public static String LoginUrl() {
        StringBuilder sb = new StringBuilder(buildAllParams());
        String uriString = getInterfaceConfigureUrl() + "/api/login" + sb.toString();
        return uriString;
    }
}
