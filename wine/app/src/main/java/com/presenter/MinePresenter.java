package com.presenter;

import android.os.Bundle;
import android.os.Message;

import com.androidyuan.frame.base.presenter.BaseCommPresenter;
import com.iview.IMineView;
import com.msg.PersonalReqMsg;
import com.msg.PersonalResMsg;

/**
 * Created by mac on 2017/10/16.
 */

public class MinePresenter extends BaseCommPresenter<IMineView> {

    private static final int RES_PERSONAL_MES = 0x1025;
    @Override
    public void initData(Bundle saveInstnce) {

    }

    @Override
    public void handMsg(Message msg) {

    }

    public void getPersonalMes() {
        PersonalReqMsg req = new PersonalReqMsg();
        PersonalResMsg res = new PersonalResMsg(RES_PERSONAL_MES);
        sendHttpGet(req, res);
    }
}
