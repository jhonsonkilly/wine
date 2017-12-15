package com.presenter;

import android.os.Bundle;
import android.os.Message;

import com.androidyuan.frame.base.presenter.BaseCommPresenter;
import com.iview.ICartListView;
import com.msg.AddtoCartReqMsg;
import com.msg.AddtoCartResMsg;
import com.msg.CartListReqMsg;
import com.msg.CartListResMsg;
import com.msg.DelateReqMsg;
import com.msg.DelateResMsg;

/**
 * Created by mac on 2017/10/16.
 */

public class ShoppingCartPresenter extends BaseCommPresenter<ICartListView> {

    private static final int RES_CART_MES = 0x1022;

    private static final int RES_ADDTOCART_MES = 0x1027;

    private static final int RES_DELATE_MES = 0x1028;


    int type;

    @Override
    public void initData(Bundle saveInstnce) {

    }

    @Override
    public void handMsg(Message msg) {
        switch (msg.what) {

            case RES_CART_MES:

            case RES_ADDTOCART_MES:
            case RES_DELATE_MES:
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


    public void addToCart(String id,String num,int type){
        this.type=type;
        AddtoCartReqMsg req = new AddtoCartReqMsg(id, num);
        AddtoCartResMsg res = new AddtoCartResMsg(RES_ADDTOCART_MES);
        sendHttpPostJson(req, res);
    }

    public void delateCart(String id,int type){
        this.type=type;
        DelateReqMsg req = new DelateReqMsg(id);
        DelateResMsg res = new DelateResMsg(RES_DELATE_MES);
        sendHttpGet(req, res);
    }

    public void handleResult(Object res) {

        if (res instanceof CartListResMsg) {
            CartListResMsg msg = (CartListResMsg) res;
            if (msg.getData() != null) {

                iView.getCartList(msg.getData().result);

            }
        }

        if (res instanceof AddtoCartResMsg) {
            AddtoCartResMsg msg = (AddtoCartResMsg) res;
            if(msg.isSuc()){
                iView.showMes(msg.getMsg(),type);
            }

        }
        if (res instanceof DelateResMsg) {
            DelateResMsg msg = (DelateResMsg) res;
            if(msg.isSuc()){
                iView.delateGoods(type);
            }

        }


    }
}
