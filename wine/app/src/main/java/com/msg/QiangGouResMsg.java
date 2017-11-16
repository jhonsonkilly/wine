package com.msg;

import com.androidyuan.frame.base.protocal.http.ResponseMsg;
import com.model.QiangGouModel;

/**
 * Created by mac on 2017/11/16.
 */

public class QiangGouResMsg extends ResponseMsg<QiangGouModel> {


    public QiangGouResMsg(int what) {
        super(what);
    }
}
