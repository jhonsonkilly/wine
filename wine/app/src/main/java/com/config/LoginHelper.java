package com.config;

import com.androidyuan.frame.base.activity.BaseApplication;
import com.androidyuan.frame.cores.utils.SharedPreferencesUtil;
import com.model.UserModel;

/**
 * Created by mac on 18/1/13.
 */

public class LoginHelper {

    public static boolean isLogin() {
        if (SharedPreferencesUtil.getStringData(BaseApplication.gainContext(), "token", "") != null) {
            return true;
        }
        return false;
    }



    //验证参数
    public static String getClient() {
        return SharedPreferencesUtil.getStringData(BaseApplication.gainContext(), "client", "");
    }

    //验证参数
    public  static String token() {
        return SharedPreferencesUtil.getStringData(BaseApplication.gainContext(), "token", "");
    }

    public static void setLoginMes(UserModel mes) {

        SharedPreferencesUtil.saveStringData(BaseApplication.gainContext(), "client", mes.getClient_id());
        SharedPreferencesUtil.saveStringData(BaseApplication.gainContext(), "token", mes.getAccess_token());
    }
}
