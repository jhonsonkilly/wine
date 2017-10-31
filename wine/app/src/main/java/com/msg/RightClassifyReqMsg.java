package com.msg;

import com.androidyuan.frame.base.protocal.http.RequestMsg;
import com.utils.Urls;

/**
 * Created by mac on 2017/10/29.
 */

public class RightClassifyReqMsg extends RequestMsg {

    String guid;

    public RightClassifyReqMsg(String id) {
        guid = id;
    }

    @Override
    public String getUrl() {
        return Urls.m_rightlist + "?parent=" + guid;
    }

}
