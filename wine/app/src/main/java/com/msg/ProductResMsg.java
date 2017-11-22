package com.msg;

import com.androidyuan.frame.base.protocal.http.ResponseMsg;
import com.model.LeftClassifyModel;
import com.model.ProductModel;

/**
 * Created by mac on 2017/11/22.
 */

public class ProductResMsg extends ResponseMsg<ProductModel> {


    public ProductResMsg(int what) {
        super(what);
    }
}
