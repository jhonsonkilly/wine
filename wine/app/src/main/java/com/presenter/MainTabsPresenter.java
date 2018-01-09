package com.presenter;

import android.os.Bundle;
import android.os.Message;

import com.androidyuan.frame.base.presenter.BaseCommPresenter;
import com.iview.IMainTabsView;
import com.msg.PersonalReqMsg;
import com.msg.PersonalResMsg;

/**
 * Created by mac on 2017/10/16.
 */

public class MainTabsPresenter extends BaseCommPresenter<IMainTabsView> {

    private static final int RES_PERSONAL_MES = 0x1025;
    @Override
    public void initData(Bundle saveInstnce) {

    }

    @Override
    public void handMsg(Message msg) {
        switch (msg.what) {


            case RES_PERSONAL_MES:
                if (msg.obj != null) {

                    handleResult(msg.obj);
                }
                break;


        }
    }

    public void getPersonalMes() {
        PersonalReqMsg req = new PersonalReqMsg();
        PersonalResMsg res = new PersonalResMsg(RES_PERSONAL_MES);
        sendHttpGet(req, res);
    }

    public void handleResult(Object res) {





        if (res instanceof PersonalResMsg) {
            PersonalResMsg msg = (PersonalResMsg) res;
            if (msg.isSuc()) {

                iView.setData(msg.getData().result);

            } else {

                iView.setData(null);
            }
        }


    }
}
