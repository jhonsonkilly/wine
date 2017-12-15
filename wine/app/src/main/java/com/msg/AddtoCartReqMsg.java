package com.msg;

import com.androidyuan.frame.base.protocal.http.RequestMsg;
import com.utils.Urls;

/**
 * Created by mac on 2017/11/27.
 */

public class AddtoCartReqMsg extends RequestMsg {
    @Override
    public String getUrl() {
        return Urls.m_addtocartlist;
    }

    public AddtoCartReqMsg(String id, String count) {
        super();
        params.put("goodGuid", id);
        params.put("quantity", count);

    }
}
