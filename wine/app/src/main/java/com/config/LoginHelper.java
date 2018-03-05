package com.config;

import com.androidyuan.frame.base.activity.WineApplication;
import com.androidyuan.frame.cores.utils.SharedPreferencesUtil;
import com.model.UserModel;

/**
 * Created by mac on 18/1/13.
 */

public class LoginHelper {

    public static boolean isLogin() {
        if (SharedPreferencesUtil.getStringData(WineApplication.gainContext(), "token", "") != null) {
            return true;
        }
        return false;
    }



    //验证参数
    public static String getClient() {
        return SharedPreferencesUtil.getStringData(WineApplication.gainContext(), "client", "");
    }

    //验证参数
    public  static String token() {
        return SharedPreferencesUtil.getStringData(WineApplication.gainContext(), "token", "");
    }

    public static void setLoginMes(UserModel mes) {

        SharedPreferencesUtil.saveStringData(WineApplication.gainContext(), "client", mes.getClient_id());
        SharedPreferencesUtil.saveStringData(WineApplication.gainContext(), "token", mes.getAccess_token());
    }
}
