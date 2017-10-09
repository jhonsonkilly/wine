package com.androidyuan.frame.base.protocal.http;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * Created by wei on 17-6-7.
 * <p>
 * 自定义重试次数拦截器
 */

class RetryIntercepter implements Interceptor {


    private int maxRetry;//最大重试次数
    private int retryNum = 2;//假如设置为3次重试的话，则最大可能请求4次（默认1次+3次重试）

    RetryIntercepter(int maxRetry) {

        this.maxRetry = maxRetry;
    }


    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        Response response = chain.proceed(request);
        while (!response.isSuccessful() && retryNum < maxRetry) {
            retryNum++;
            response = chain.proceed(request);
        }
        return response;
    }
}
