package com.presenter;

import android.os.Bundle;
import android.os.Message;

import com.androidyuan.frame.base.presenter.BaseCommPresenter;
import com.iview.ILeftClassifyView;
import com.model.LeftClassifyModel;
import com.msg.LeftClassifyReqMsg;
import com.msg.LeftClassifyResMsg;

import java.util.List;

/**
 * Created by mac on 2017/10/16.
 */

public class ClassifyPresenter extends BaseCommPresenter<ILeftClassifyView> {


    private static final int RES_LEFT_MES = 0x1022;

    @Override
    public void initData(Bundle saveInstnce) {

    }

    @Override
    public void handMsg(Message msg) {

        switch (msg.what) {

            case RES_LEFT_MES:
                if (msg.obj != null && msg.obj instanceof LeftClassifyModel) {

                    handleResult((LeftClassifyResMsg) msg.obj);
                }
                break;

        }
    }

    public void getLeftMes() {
        LeftClassifyReqMsg req = new LeftClassifyReqMsg();
        LeftClassifyResMsg res = new LeftClassifyResMsg(RES_LEFT_MES);
        sendHttpGet(req, res);
    }

    public void handleResult(LeftClassifyResMsg res) {


        if (res.isSuc()) {
            if (res.getData() != null) {

                iView.showData(res.getData().result);

            }
        }
    }
}


