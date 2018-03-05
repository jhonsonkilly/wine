package com.msg;

import com.androidyuan.frame.base.protocal.http.RequestMsg;
import com.utils.Urls;

import java.util.Map;

/**
 * Created by mac on 18/3/5.
 */

public class WXPayReqMsg extends RequestMsg {

    @Override
    public String getUrl() {
        return Urls.m_WxPayMes;
    }

    public WXPayReqMsg(Map<String, String> map) {

        super();


        params.putAll(map);

    }
}
