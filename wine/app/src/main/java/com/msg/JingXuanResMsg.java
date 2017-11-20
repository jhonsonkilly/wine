package com.msg;

import com.androidyuan.frame.base.protocal.http.ResponseMsg;
import com.model.JingXuanModel;

/**
 * Created by mac on 2017/11/20.
 */

public class JingXuanResMsg extends ResponseMsg<JingXuanModel> {
    public JingXuanResMsg(int what) {
        super(what);
    }
}
