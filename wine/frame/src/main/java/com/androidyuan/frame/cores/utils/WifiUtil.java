package com.androidyuan.frame.cores.utils;

/**
 * Created by weizongwei on 15-11-25.
 */

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

/**
 * @author zm
 *         <p/>
 *         wifi相关的获取，mac、wifi名称、是否连接wifi
 */
public class WifiUtil {

    // 测试获取mac地址
    public static String getMacAddress(Context context) {
        // 获取mac地址：
        WifiManager wifi = (WifiManager) context
                .getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        return info.getMacAddress();
    }

    // 获取wifi名称
    public static String getWifiSsid(Context context) {

        String wifi_name = "";
        try {
            WifiManager wifiMgr = (WifiManager) context
                    .getSystemService(Context.WIFI_SERVICE);
            // int wifiState = wifiMgr.getWifiState();
            WifiInfo info = wifiMgr.getConnectionInfo();
            wifi_name = info != null ? info.getSSID() : "";
            wifi_name = wifi_name.replace("\"", "");
        }
        catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            wifi_name = "";
        }

        return wifi_name;
    }

    // 判断当前是否使用的是 WIFI网络 ，是否成功连接
    public static boolean isWifiActive(Context context) {

        Context mContext = context.getApplicationContext();
        ConnectivityManager connectivity = (ConnectivityManager) mContext
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] info;
        if (connectivity != null) {
            info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getTypeName().equals("WIFI")
                            && info[i].isConnected()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * @param context wifi是否有效
     */
    public static boolean isWifiAvailable(Context context) {

        ConnectivityManager conMan = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        State wifi = conMan.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                .getState();
        return State.CONNECTED == wifi;
    }

    // 判断wifi是否开启
    public static boolean isOpen(Context context) {

        try {
            WifiManager wm = (WifiManager) context
                    .getSystemService(Context.WIFI_SERVICE);
            return wm.isWifiEnabled();
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }


    //是否网络联通
    public static boolean isNetworkAvailable(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm == null) {
        }
        else {
            //如果仅仅是用来判断网络连接
            //则可以使用 cm.getActiveNetworkInfo().isAvailable();
            NetworkInfo[] info = cm.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}