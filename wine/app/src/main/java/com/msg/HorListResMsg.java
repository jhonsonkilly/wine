package com.msg;

import com.androidyuan.frame.base.protocal.http.ResponseMsg;
import com.model.BannerModel;
import com.model.HorlistModel;

/**
 * Created by mac on 2017/11/14.
 */

public class HorListResMsg extends ResponseMsg<HorlistModel> {


    public HorListResMsg(int what) {
        super(what);
    }
}
