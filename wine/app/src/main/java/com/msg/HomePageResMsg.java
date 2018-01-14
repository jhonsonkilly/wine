package com.msg;

import com.androidyuan.frame.base.protocal.http.LiveResponseMsg;
import com.androidyuan.frame.base.protocal.http.ResponseMsg;
import com.model.DelYanZhenModel;
import com.model.HomePageModel;

/**
 * Created by mac on 18/1/13.
 */

public class HomePageResMsg extends LiveResponseMsg<HomePageModel> {


    public HomePageResMsg(int what) {
        super(what);
    }
}
