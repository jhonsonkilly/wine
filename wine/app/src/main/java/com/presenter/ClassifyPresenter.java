package com.presenter;

import android.os.Bundle;
import android.os.Message;

import com.androidyuan.frame.base.presenter.BaseCommPresenter;
import com.iview.ILeftClassifyView;
import com.model.LeftClassifyModel;
import com.msg.LeftClassifyReqMsg;
import com.msg.LeftClassifyResMsg;
import com.msg.RightClassifyReqMsg;
import com.msg.RightClassifyResMsg;

import java.util.List;

/**
 * Created by mac on 2017/10/16.
 */

public class ClassifyPresenter extends BaseCommPresenter<ILeftClassifyView> {


    private static final int RES_LEFT_MES = 0x1022;

    private static final int RES_RIGHT_MES = 0x1023;

    @Override
    public void initData(Bundle saveInstnce) {
        getLeftMes();
    }

    @Override
    public void handMsg(Message msg) {

        switch (msg.what) {

            case RES_LEFT_MES:
                if (msg.obj != null) {

                    handleResult((LeftClassifyResMsg) msg.obj);
                }
                break;
            case RES_RIGHT_MES:
                if (msg.obj != null) {

                    handleRightResult((RightClassifyResMsg) msg.obj);
                }
                break;

        }
    }

    public void getLeftMes() {
        LeftClassifyReqMsg req = new LeftClassifyReqMsg();
        LeftClassifyResMsg res = new LeftClassifyResMsg(RES_LEFT_MES);
        sendHttpGet(req, res);
    }

    public void getRightMes(String id) {
        RightClassifyReqMsg req = new RightClassifyReqMsg(id);
        RightClassifyResMsg res = new RightClassifyResMsg(RES_LEFT_MES);
        sendHttpGet(req, res);
    }

    public void handleResult(LeftClassifyResMsg res) {


        if (res.isSuc()) {
            if (res.getData() != null) {

                iView.showData(res.getData().result);

            }
        }
    }

    public void handleRightResult(RightClassifyResMsg res) {


        if (res.isSuc()) {
            if (res.getData() != null) {

                iView.showRightData(res.getData().result);

            }
        }
    }
}


