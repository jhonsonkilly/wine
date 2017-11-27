package com.msg;

import com.androidyuan.frame.base.protocal.http.ResponseMsg;
import com.model.AddToCartModel;
import com.model.BannerModel;

/**
 * Created by mac on 2017/11/27.
 */

public class AddtoCartResMsg extends ResponseMsg<AddToCartModel> {
    public AddtoCartResMsg(int what) {
        super(what);
    }
}
