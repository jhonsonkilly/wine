package com.msg;

import com.androidyuan.frame.base.protocal.http.RequestMsg;
import com.utils.Urls;

/**
 * Created by mac on 2018/1/18.
 */

public class DelateAllReqMsg extends RequestMsg {


    public DelateAllReqMsg(String id) {
        super();
        params.put("goodGuids", id);


    }
    @Override
    public String getUrl() {
        return Urls.m_delateAllGoods;
    }
}
