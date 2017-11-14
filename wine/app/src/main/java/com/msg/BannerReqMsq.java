package com.msg;

import com.androidyuan.frame.base.protocal.http.RequestMsg;
import com.utils.Urls;

/**
 * Created by mac on 2017/11/14.
 */

public class BannerReqMsq extends RequestMsg {
    @Override
    public String getUrl() {
        return Urls.m_homebannerlist;
    }
}
