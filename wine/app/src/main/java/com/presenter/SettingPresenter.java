package com.presenter;

import android.os.Bundle;
import android.os.Message;
import android.widget.Toast;

import com.Event.AddToCartNumberEvent;
import com.androidyuan.frame.base.activity.BaseApplication;
import com.androidyuan.frame.base.presenter.BaseCommPresenter;
import com.androidyuan.frame.cores.utils.SharedPreferencesUtil;
import com.msg.QuitLoginReqMsg;
import com.msg.QuitLoginResMsg;
import com.otto.OttoBus;

/**
 * <p>Copyright:Copyright(c) 2016</p>
 * <p>Company:上海来伊份电子商务有限公司</p>
 * <p>包名:com.presenter</p>
 * <p>文件名:wine</p>
 * <p>类更新历史信息</p>
 *
 * @todo <a href="mailto:zhoujiawei@laiyifen.com">vernal(周佳伟)</a>
 */
public class SettingPresenter extends BaseCommPresenter {


    private static final int RES_QUIT_MES = 0x1022;

    @Override
    public void initData(Bundle saveInstnce) {

    }

    @Override
    public void handMsg(Message msg) {
        switch (msg.what) {

            case RES_QUIT_MES:


                if (msg.obj != null) {

                    handleResult(msg.obj);
                }
                break;


        }
    }

    public void quitLogin() {
        QuitLoginReqMsg req = new QuitLoginReqMsg();
        QuitLoginResMsg res = new QuitLoginResMsg(RES_QUIT_MES);
        sendHttpGet(req, res);
    }

    public void handleResult(Object res) {

        if (res instanceof QuitLoginResMsg) {
            QuitLoginResMsg msg = (QuitLoginResMsg) res;
            if (msg.getData() != null) {
                //清空配送员
                Toast.makeText(getActivity(), msg.getData().result, Toast.LENGTH_LONG).show();
                SharedPreferencesUtil.saveStringData(BaseApplication.gainContext(), "ut", "");
                SharedPreferencesUtil.saveStringData(BaseApplication.gainContext(), "agent", "N");
                AddToCartNumberEvent event = new AddToCartNumberEvent();
                event.result = 0 + "";
                OttoBus.getInstance().post(event);
                getActivity().finish();

            }
        }


    }
}
