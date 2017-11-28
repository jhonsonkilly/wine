package com.presenter;

import android.os.Bundle;
import android.os.Message;

import com.androidyuan.frame.base.presenter.BaseCommPresenter;
import com.msg.DelYanZhenReqMsg;
import com.msg.DelYanZhenResMsg;
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
public class RegistPresenter extends BaseCommPresenter {

    String phone;

    private static final int RES_DEL_MES = 0x1221;


    private static final int RES_YANZHEN_MES = 0x1223;

    @Override
    public void initData(Bundle saveInstnce) {

    }

    @Override
    public void handMsg(Message msg) {

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

    public void reigst(String phone,String code) {

    }
}
