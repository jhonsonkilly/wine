package com.msg;

import com.androidyuan.frame.base.protocal.http.RequestMsg;
import com.androidyuan.frame.base.protocal.http.ResponseMsg;
import com.config.UrlConfig;
import com.model.DelYanZhenModel;

/**
 * Created by mac on 18/1/13.
 */

public class HomePageReqMsg extends RequestMsg {
    @Override
    public String getUrl() {
        return UrlConfig.homePageUrl();
    }
}
