package com.msg;

import com.androidyuan.frame.base.protocal.http.RequestMsg;
import com.utils.Urls;

/**
 * Created by mac on 2017/11/22.
 */

public class ProductReqMsg extends RequestMsg {
    @Override
    public String getUrl() {
        return Urls.m_productlist;
    }
}
