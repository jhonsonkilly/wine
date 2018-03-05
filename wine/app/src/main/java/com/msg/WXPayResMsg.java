package com.msg;

import com.androidyuan.frame.base.protocal.http.ResponseMsg;
import com.model.AliPayModel;

/**
 * Created by mac on 18/3/5.
 */

public class WXPayResMsg extends ResponseMsg<AliPayModel> {
    public WXPayResMsg(int what) {
        super(what);
    }
}
