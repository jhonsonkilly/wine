package com.msg;

import com.androidyuan.frame.base.protocal.http.ResponseMsg;
import com.model.BannerModel;
import com.model.LeftClassifyModel;

/**
 * Created by mac on 2017/11/14.
 */

public class BannerResMsg extends ResponseMsg<BannerModel> {

    public BannerResMsg(int what) {
        super(what);
    }

}
