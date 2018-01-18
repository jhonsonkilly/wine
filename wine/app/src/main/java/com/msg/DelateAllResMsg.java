package com.msg;

import com.androidyuan.frame.base.protocal.http.ResponseMsg;
import com.model.DelateCartModel;

/**
 * Created by mac on 2018/1/18.
 */

public class DelateAllResMsg extends ResponseMsg<DelateCartModel> {

    public DelateAllResMsg(int what) {
        super(what);
    }
}
