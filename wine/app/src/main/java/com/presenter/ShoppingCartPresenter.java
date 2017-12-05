package com.presenter;

import android.os.Bundle;
import android.os.Message;

import com.androidyuan.frame.base.presenter.BaseCommPresenter;
import com.iview.ICartListView;
import com.msg.CartListReqMsg;
import com.msg.CartListResMsg;

/**
 * Created by mac on 2017/10/16.
 */

public class ShoppingCartPresenter extends BaseCommPresenter<ICartListView> {

    private static final int RES_CART_MES = 0x1022;

    @Override
    public void initData(Bundle saveInstnce) {

    }

    @Override
    public void handMsg(Message msg) {
        switch (msg.what) {

            case RES_CART_MES:


                if (msg.obj != null) {

                    handleResult(msg.obj);
                }
                break;


        }
    }


    public void getCartList() {
        CartListReqMsg req = new CartListReqMsg();
        CartListResMsg res = new CartListResMsg(RES_CART_MES);
        sendHttpGet(req, res);
    }

    public void handleResult(Object res) {

        if (res instanceof CartListResMsg) {
            CartListResMsg msg = (CartListResMsg) res;
            if (msg.getData() != null) {

                iView.getCartList(msg.getData().result);

            }
        }


    }
}
