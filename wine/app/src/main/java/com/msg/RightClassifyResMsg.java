package com.msg;

import com.androidyuan.frame.base.protocal.http.ResponseMsg;
import com.model.LeftClassifyModel;
import com.model.RightClassifyModel;

/**
 * Created by mac on 2017/10/29.
 */

public class RightClassifyResMsg extends ResponseMsg<RightClassifyModel> {


    public RightClassifyResMsg(int what) {
        super(what);
    }
}
