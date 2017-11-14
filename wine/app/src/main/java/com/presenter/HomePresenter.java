package com.presenter;

import android.os.Bundle;
import android.os.Message;

import com.androidyuan.frame.base.presenter.BaseCommPresenter;
import com.iview.IHomeView;
import com.iview.ILeftClassifyView;
import com.msg.BannerReqMsq;
import com.msg.BannerResMsg;
import com.msg.HorListReqMsg;
import com.msg.HorListResMsg;
import com.msg.LeftClassifyReqMsg;
import com.msg.LeftClassifyResMsg;
import com.msg.RightClassifyResMsg;

/**
 * Created by mac on 2017/10/16.
 */

public class HomePresenter extends BaseCommPresenter<IHomeView> {


    private static final int RES_BANNER_MES = 0x1022;

    private static final int RES_HORLIST_MES = 0x1023;

    @Override
    public void initData(Bundle saveInstnce) {
        getList();

    }

    @Override
    public void handMsg(Message msg) {
        switch (msg.what) {

            case RES_BANNER_MES:
                if (msg.obj != null) {

                    handleResult((BannerResMsg) msg.obj);
                }
                break;
            case RES_HORLIST_MES:
                if (msg.obj != null) {

                    handleHorResult((HorListResMsg) msg.obj);
                }
                break;


        }
    }

    public void getList() {
        BannerReqMsq req = new BannerReqMsq();
        BannerResMsg res = new BannerResMsg(RES_BANNER_MES);
        sendHttpGet(req, res);

        HorListReqMsg req1 = new HorListReqMsg();
        HorListResMsg res2 = new HorListResMsg(RES_HORLIST_MES);
        sendHttpGet(req1, res2);
    }

    public void handleResult(BannerResMsg res) {


        if (res.isSuc()) {
            if (res.getData() != null) {

                iView.showBannerList(res.getData().result);

            }
        }
    }

    public void handleHorResult(HorListResMsg res) {


        if (res.isSuc()) {
            if (res.getData() != null) {

                iView.showHorList(res.getData().result);

            }
        }
    }
}
