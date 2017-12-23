package com.androidyuan.frame.base.protocal.http;

import android.os.Handler;
import android.os.Message;
import android.support.annotation.Keep;

import com.androidyuan.frame.base.activity.WineApplication;
import com.androidyuan.frame.cores.log.CommonLogger;
import com.androidyuan.frame.cores.utils.SharedPreferencesUtil;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.androidyuan.frame.base.protocal.http.HttpConfig.RETRY_COUT;
import static com.androidyuan.frame.base.protocal.http.HttpConfig.TIMEOU_MS;

/**
 * Created by wei on 2015/6/19.
 * <p>
 * <p>
 * =====使用OkHttp的数据请求,只不过重新封装了一层===
 * <p>
 * 1.HEAD
 * 2.GET
 * 3.POST(body)
 * 4.POST(form)
 */
@Keep
public class HttpTool {

    private static HttpTool sInstance;
    private final OkHttpClient mClient = new OkHttpClient.Builder()
            .addInterceptor(new RetryIntercepter(RETRY_COUT))//重试次数
            .readTimeout(TIMEOU_MS, TimeUnit.MILLISECONDS)//超时时间
            .build();
    private CommonLogger mCommonLogger = CommonLogger.buildLogger(this);

    public static HttpTool getClient() {

        if (sInstance == null) {
            sInstance = new HttpTool();
        }
        return sInstance;
    }

    /**
     * 不带参数的Http HEAD请求
     */
    public void requestHEAD(String url) {

        Request request = new Request.Builder()
                .url(url)
                .head()
                .build();

        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }


    /**
     * 一个get请求  完全面向对象的实现方式 req res 都封装好的
     *
     * @param req req里面千万别带含有File类型的value  get请求无法提交bitmap  除非你特么转byte[]转base64
     * @param res
     */
    public void requestGet(final RequestMsg req, final ResponseMsg res, final Handler handler) {
        String mUrl = req.getUrl();
        //添加token
        if (req.getUrl().contains("?")) {
            mUrl += "&";
        } else {
            mUrl += "?";
        }
        mUrl += "TOKEN=" + SharedPreferencesUtil.getStringData(WineApplication.gainContext(), "ut", "");

        Request request = new Request.Builder()
                .url(mUrl)
                .build();

        mCommonLogger.info(mUrl);
        mClient.newCall(request).enqueue(new ReponseResolver(res, handler));

    }


    /**
     * 一个post请求  完全面向对象的实现方式 req res 都封装好的
     *
     * @param req     req不可含有File类型的Value
     * @param res
     * @param handler 没有设置超时时间 默认情况下  有一个超时时间的  不用去管
     */
    public void requestPostJson(final RequestMsg req, final ResponseMsg res, final Handler handler) {

        FormBody.Builder builder = new FormBody.Builder();

        if (req.params.size() != 0) {
            for (Map.Entry<String, Object> entry : req.params.entrySet()) {

                builder.add(entry.getKey(), entry.getValue().toString());

            }
        }
        RequestBody body = builder.build();

        Request request = new Request
                .Builder()
                .url(req.getRequestUrl())
                .post(body).build();

        /*MediaType JSON_TYPE = MediaType.parse("application/json; charset=utf-8");

        RequestBody body = RequestBody.create(JSON_TYPE, req.getJsonBody());

        Request request = new FormBody.Builder()
                .url(req.getRequestUrl())
                .post(body)
                .build();*/

        mCommonLogger.d(req.getRequestUrl());

        mCommonLogger.info("body:" + req.getJsonBody());
        mClient.newCall(request).enqueue(new ReponseResolver(res, handler));
    }


    /**
     * ======这个方法可以提交file到服务器=====
     * <p>
     * 一个post请求  完全面向对象的实现方式 req res 都封装好的
     *
     * @param req
     * @param res
     * @param handler
     */
    public void requestPost(final RequestMsg req, final ResponseMsg res, final Handler handler) {


        MultipartBody.Builder formBody = new MultipartBody.Builder();

        for (Map.Entry<String, Object> entry : req.getParamEntry()) {

            if (entry.getValue() instanceof String) {
                formBody.addFormDataPart(entry.getKey(), (String) entry.getValue());
            } else if (entry.getValue() instanceof File) {
                formBody.addFormDataPart(entry.getKey(), ((File) entry.getValue()).getAbsolutePath(), generatorImgBody((File) entry.getValue()));
            }
        }

        Request request = new Request.Builder()
                .url(req.getRequestUrl())
                .post(formBody.build())
                .build();

        mCommonLogger.d(req.getRequestUrl());
        mClient.newCall(request).enqueue(new ReponseResolver(res, handler));

    }

    private RequestBody generatorImgBody(File file) {

        return RequestBody.create(MediaType.parse("image/png"), file);
    }

    private class ReponseResolver implements Callback {

        ResponseMsg mResponseMsg;
        Handler mHandler;

        ReponseResolver(ResponseMsg res, Handler handler) {

            mResponseMsg = res;
            mHandler = handler;
        }

        @Override
        public void onFailure(Call call, IOException e) {

            mCommonLogger.e(e);
            sendHandMsg();
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {

            String body = response.body().string();//不能调用两次
            mCommonLogger.d("response:" + body);
            mResponseMsg.setResponse(body);//maybe need sync
            mResponseMsg.setResult(response.code());//TODO
            sendHandMsg();

        }

        public void sendHandMsg() {

            Message msg = Message.obtain();
            msg.what = mResponseMsg.getMsgWhat();
            msg.obj = mResponseMsg;
            mHandler.sendMessage(msg);
        }
    }

}
