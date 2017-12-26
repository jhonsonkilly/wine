package com.presenter;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.widget.Toast;

import com.Event.PayEvent;
import com.alipay.PayResult;
import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.app.PayTask;
import com.androidyuan.frame.base.presenter.BaseCommPresenter;
import com.androidyuan.frame.cores.FrameApplication;
import com.msg.AliPayReqMsg;
import com.msg.AliPayResMsg;
import com.otto.OttoBus;

import java.util.Map;

/**
 * <p>Copyright:Copyright(c) 2016</p>
 * <p>Company:上海来伊份电子商务有限公司</p>
 * <p>包名:com.presenter</p>
 * <p>文件名:wine</p>
 * <p>类更新历史信息</p>
 *
 * @todo <a href="mailto:zhoujiawei@laiyifen.com">vernal(周佳伟)</a>
 */
public class WebViewPresenter extends BaseCommPresenter {


    private static final int RES_ALIPAY = 0x1027;

    private static final int RES_WXPAY = 0x1028;


    private static final String ALIPAY_SUC = "9000";//	订单支付成功
    private static final String ALIPAY_PROG = "8000";//	正在处理中
    private static final String ALIPAY_PAY_ERR = "4000";//	订单支付失败
    private static final String ALIPAY_CACEL = "6001";//	用户中途取消
    private static final String ALIPAY_CON_ERR = "6002";//	网络连接出错

    @Override
    public void initData(Bundle saveInstnce) {

    }

    @Override
    public void handMsg(Message msg) {
        switch (msg.what) {

            case RES_ALIPAY:

            case RES_WXPAY:
                if (msg.obj != null) {

                    handleResult(msg.obj);
                }
                break;


        }
    }

    public void submitMes(Map<String, String> map) {
        if (map.size() == 4) {
            AliPayReqMsg req = new AliPayReqMsg(map);
            AliPayResMsg res = new AliPayResMsg(RES_ALIPAY);
            sendHttpPostJson(req, res);
        } else {

        }
    }

    public void handleResult(Object res) {

        if (res instanceof AliPayResMsg) {
            AliPayResMsg msg = (AliPayResMsg) res;
            if (msg.getData() != null) {

                callAliPay(msg.getData().result);

            }
        }
    }

    public void callAliPay(final String response) {

        //EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);

        AsyncTask<Void, Void, String> task = new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                // 构造PayTask 对象
                PayTask alipay = new PayTask(iView.getActivity());
                // 调用支付接口，获取支付结果
                return alipay.pay(response, true);
            }

            @Override
            protected void onPostExecute(String payresult) {

                super.onPostExecute(payresult);
                if (payresult != null) {

                    PayResult payResult = new PayResult(payresult);
                    /**
                     * 同步返回的结果必须放置到服务端进行验证（验证的规则请看https://doc.open.alipay.com/doc2/
                     * detail.htm?spm=0.0.0.0.xdvAU6&treeId=59&articleId=103665&
                     * docType=1) 建议商户依赖异步通知
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息

                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    if (TextUtils.equals(resultStatus, ALIPAY_SUC)) {
                        //Toast.makeText(TheWayRechargeActivity.this, "支付成功", Toast.LENGTH_SHORT).show();

                        PayEvent event = new PayEvent();
                        event.payResult = "success";
                        event.type = PayEvent.PayType.RESULT;
                        OttoBus.getInstance().post(event);
                        //http://47.93.18.21/eshop/shoppingCart/pay.html?member=xxx&userName=xxx&userImage=xxx&token=xxx&payResult=xxxx

                    } else {
                        // 判断resultStatus 为非"9000"则代表可能支付失败
                        // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, ALIPAY_PROG)) {
                            Toast.makeText(FrameApplication.getApp(), "支付结果确认中", Toast.LENGTH_SHORT).show();

                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            if (TextUtils.equals(resultStatus, ALIPAY_PAY_ERR)) {
                                Toast.makeText(FrameApplication.getApp(), "支付失败", Toast.LENGTH_SHORT).show();
                                PayEvent event = new PayEvent();
                                event.payResult = "error";
                                event.type = PayEvent.PayType.RESULT;
                                OttoBus.getInstance().post(event);
                            } else if (TextUtils.equals(resultStatus, ALIPAY_CON_ERR)) {
                                Toast.makeText(FrameApplication.getApp(), "网络连接出错", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }
                }

            }
        };
        task.execute();

    }
}
