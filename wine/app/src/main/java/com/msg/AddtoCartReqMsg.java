package com.msg;

import com.androidyuan.frame.base.protocal.http.RequestMsg;
import com.utils.Urls;

import java.util.HashMap;
import java.util.Map;

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
        Map<String, String> map = new HashMap<>();

        map.put("goodGuid", id);
        map.put("quantity", count);
        put("p", map);
    }
}
