package com.presenter;

import android.os.Bundle;
import android.os.Message;

import com.activity.LoginActivity;
import com.androidyuan.frame.base.presenter.BaseCommPresenter;
import com.iview.ILoginView;
import com.msg.DelYanZhenReqMsg;
import com.msg.DelYanZhenResMsg;
import com.msg.LoginReqMsg;
import com.msg.LoginResMsg;
import com.msg.PersonalReqMsg;
import com.msg.PersonalResMsg;
import com.msg.YanZhenReqMsg;
import com.msg.YanZhenResMsg;

/**
 * <p>Copyright:Copyright(c) 2016</p>
 * <p>Company:上海来伊份电子商务有限公司</p>
 * <p>包名:com.presenter</p>
 * <p>文件名:wine</p>
 * <p>类更新历史信息</p>
 *
 * @todo <a href="mailto:zhoujiawei@laiyifen.com">vernal(周佳伟)</a>
 */
public class LoginPresenter extends BaseCommPresenter<ILoginView> {

    String phone;

    private static final int RES_YANZHEN_MES = 0x1022;

    private static final int RES_DEL_MES = 0x1023;

    private static final int RES_LOGIN_MES = 0x1024;

    private static final int RES_PERSONAL_MES = 0x1025;

    @Override
    public void initData(Bundle saveInstnce) {

    }

    @Override
    public void handMsg(Message msg) {
        switch (msg.what) {

            case RES_YANZHEN_MES:
            case RES_DEL_MES:
            case RES_LOGIN_MES:
            case RES_PERSONAL_MES:
                if (msg.obj != null) {

                    handleResult(msg.obj);
                }
                break;


        }
    }

    public void getVertifyCode(String phone) {
        this.phone = phone;
        YanZhenReqMsg req = new YanZhenReqMsg(phone);
        YanZhenResMsg res = new YanZhenResMsg(RES_YANZHEN_MES);
        sendHttpGet(req, res);
    }

    public void delVertifyCode() {
        DelYanZhenReqMsg req = new DelYanZhenReqMsg(phone);
        DelYanZhenResMsg res = new DelYanZhenResMsg(RES_DEL_MES);
        sendHttpGet(req, res);
    }

    public void login(String phone, String yan) {
        LoginReqMsg req = new LoginReqMsg(phone, yan);
        LoginResMsg res = new LoginResMsg(RES_LOGIN_MES);
        sendHttpPostJson(req, res);
    }

    public void getPersonalMes(String id) {
        PersonalReqMsg req = new PersonalReqMsg();
        PersonalResMsg res = new PersonalResMsg(RES_PERSONAL_MES);
        sendHttpGet(req, res);
    }

    public void handleResult(Object res) {

        if (res instanceof YanZhenResMsg) {
            YanZhenResMsg msg = (YanZhenResMsg) res;
            if (msg.getData() != null) {

                iView.showResult(msg.getData().result);

            }
        }

        if (res instanceof DelYanZhenResMsg) {
            DelYanZhenResMsg msg = (DelYanZhenResMsg) res;
            if (msg.isSuc()) {

            }
        }

        if (res instanceof PersonalResMsg) {
            PersonalResMsg msg = (PersonalResMsg) res;
            if (msg.isSuc()) {

                iView.showPersonal(msg.getData().result);

            } else {

                iView.showPersonal(null);
            }
        }

        if (res instanceof LoginResMsg) {
            LoginResMsg msg = (LoginResMsg) res;
            if (msg.isSuc()) {
                if (msg.getData() != null) {

                    iView.showLogin(msg.getData().result.token);

                } else {
                    LoginActivity act = (LoginActivity) getActivity();
                    act.showToast(msg.getMsg());
                }
            }
        }
    }
}
