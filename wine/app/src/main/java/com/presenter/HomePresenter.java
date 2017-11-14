package com.presenter;

import android.os.Bundle;
import android.os.Message;

import com.androidyuan.frame.base.presenter.BaseCommPresenter;
import com.iview.IHomeView;
import com.iview.ILeftClassifyView;
import com.msg.BannerReqMsq;
import com.msg.BannerResMsg;
import com.msg.LeftClassifyReqMsg;
import com.msg.LeftClassifyResMsg;
import com.msg.RightClassifyResMsg;

/**
 * Created by mac on 2017/10/16.
 */

public class HomePresenter extends BaseCommPresenter<IHomeView> {


    private static final int RES_BANNER_MES = 0x1022;

    @Override
    public void initData(Bundle saveInstnce) {
        getBannerList();
    }

    @Override
    public void handMsg(Message msg) {
        switch (msg.what) {

            case RES_BANNER_MES:
                if (msg.obj != null) {

                    handleResult((BannerResMsg) msg.obj);
                }
                break;


        }
    }

    public void getBannerList() {
        BannerReqMsq req = new BannerReqMsq();
        BannerResMsg res = new BannerResMsg(RES_BANNER_MES);
        sendHttpGet(req, res);
    }

    public void handleResult(BannerResMsg res) {


        if (res.isSuc()) {
            if (res.getData() != null) {

                iView.showBannerList(res.getData().result);

            }
        }
    }
}
