package com.presenter;

import android.os.Bundle;
import android.os.Message;
import android.widget.Toast;

import com.androidyuan.frame.base.presenter.BaseCommPresenter;
import com.iview.IHomeView;
import com.msg.AddtoCartReqMsg;
import com.msg.AddtoCartResMsg;
import com.msg.BannerReqMsg;
import com.msg.BannerResMsg;
import com.msg.HomePageReqMsg;
import com.msg.HomePageResMsg;
import com.msg.HorListReqMsg;
import com.msg.HorListResMsg;
import com.msg.JingXuanReqMsg;
import com.msg.JingXuanResMsg;
import com.msg.ProductReqMsg;
import com.msg.ProductResMsg;
import com.msg.QiangGouReqMsg;
import com.msg.QiangGouResMsg;
import com.msg.RegistReqMsg;
import com.msg.RegistResMsg;

/**
 * Created by mac on 2017/10/16.
 */


public class HomePresenter extends BaseCommPresenter<IHomeView> {


    private static final int RES_BANNER_MES = 0x1022;

    private static final int RES_HORLIST_MES = 0x1023;

    private static final int RES_QIANGGOU_MES = 0x1024;

    private static final int RES_JINGXUAN_MES = 0x1025;

    private static final int RES_PRODUCT_MES = 0x1026;

    private static final int RES_ADDTOCART_MES = 0x1027;

    private static final int RES_HOME_MES = 5;

    @Override
    public void initData(Bundle saveInstnce) {


    }

    @Override
    public void handMsg(Message msg) {
        switch (msg.what) {

            case RES_BANNER_MES:
            case RES_HORLIST_MES:
            case RES_QIANGGOU_MES:
            case RES_JINGXUAN_MES:
            case RES_PRODUCT_MES:
            case RES_ADDTOCART_MES:
            case RES_HOME_MES:

                if (msg.obj != null) {

                    handleResult(msg.obj);
                }
                break;


        }
    }

    public void addtoCart(String id, String number) {
        AddtoCartReqMsg req = new AddtoCartReqMsg(id, number);
        AddtoCartResMsg res = new AddtoCartResMsg(RES_ADDTOCART_MES);
        sendHttpPostJson(req, res);
    }



    public void getList() {

        BannerReqMsg req = new BannerReqMsg();
        BannerResMsg res = new BannerResMsg(RES_BANNER_MES);
        sendHttpGet(req, res);

        HorListReqMsg req1 = new HorListReqMsg();
        HorListResMsg res2 = new HorListResMsg(RES_HORLIST_MES);
        sendHttpGet(req1, res2);

        QiangGouReqMsg req3 = new QiangGouReqMsg();
        QiangGouResMsg res4 = new QiangGouResMsg(RES_QIANGGOU_MES);
        sendHttpGet(req3, res4);

        JingXuanReqMsg req5 = new JingXuanReqMsg();
        JingXuanResMsg res6 = new JingXuanResMsg(RES_JINGXUAN_MES);
        sendHttpGet(req5, res6);

        ProductReqMsg req7 = new ProductReqMsg();
        ProductResMsg res8 = new ProductResMsg(RES_PRODUCT_MES);
        sendHttpGet(req7, res8);


    }

    public void handleResult(Object res) {

        if (res instanceof BannerResMsg) {
            BannerResMsg msg = (BannerResMsg) res;
            if (msg.getData() != null) {

                iView.showBannerList(msg.getData().result);

            }
        }
        if (res instanceof HorListResMsg) {
            HorListResMsg msg = (HorListResMsg) res;
            if (msg.getData() != null) {

                iView.showHorList(msg.getData().result);

            }
        }
        if (res instanceof QiangGouResMsg) {
            QiangGouResMsg msg = (QiangGouResMsg) res;
            if (msg.getData() != null) {

                iView.showQiangGouList(msg.getData().result);

            }
        }
        if (res instanceof JingXuanResMsg) {
            JingXuanResMsg msg = (JingXuanResMsg) res;
            if (msg.getData() != null) {

                iView.showJingXuanList(msg.getData().result);

            }
        }
        if (res instanceof ProductResMsg) {
            ProductResMsg msg = (ProductResMsg) res;
            if (msg.getData() != null) {

                iView.showProductList(msg.getData().result);

            }
        }

        if (res instanceof AddtoCartResMsg) {
            AddtoCartResMsg msg = (AddtoCartResMsg) res;
            if (msg.isSuc()) {
                iView.showMes(msg.getMsg(), msg.getData().result);
            }


        }

        if (res instanceof QiangGouResMsg) {
            QiangGouResMsg msg = (QiangGouResMsg) res;
            if (msg.getData() != null) {

                iView.showQiangGouList(msg.getData().result);

            }
        }

        if (res instanceof HomePageResMsg) {
            HomePageResMsg msg = (HomePageResMsg) res;
            if (msg.getData() != null) {
                if (msg.isSuc()) {
                    Toast.makeText(iView.getActivity(), msg.getData().rotate.get(1), Toast.LENGTH_LONG).show();
                } else {

                    Toast.makeText(iView.getActivity(), msg.getMsg(), Toast.LENGTH_LONG).show();
                }


            }
        }


    }


}
