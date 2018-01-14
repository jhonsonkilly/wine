package com.androidyuan.frame.base.protocal.http;

import android.text.TextUtils;

import com.alibaba.fastjson.JSONObject;
import com.androidyuan.frame.cores.log.CommonLogger;
import com.androidyuan.frame.cores.utils.FastJSONHelper;

import java.lang.reflect.ParameterizedType;

/**
 * @author Chris
 * @time 18/1/13 上午3:29
 * @function 特殊情况处理
 */

public abstract class LiveResponseMsg<T> extends ResponseMsg {


    protected T data;

    protected int resmsgWhat = 0;
    protected int result = 0; // 状态位
    protected JSONObject fastjsonObject;
    private String msg = ""; // 错误原因
    //
    private boolean isSuc = true; // 默认是true
    private String response;


    public LiveResponseMsg(int what) {

        super();
        resmsgWhat = what;
    }

    public int getMsgWhat() {

        return resmsgWhat;
    }

    public String getResponse() {

        return response;
    }

    public void setResponse(String response) {

        CommonLogger.buildLogger(getClass().getSimpleName()).d("response:" + response);
        this.response = response;

        try {
            fastjsonObject = JSONObject.parseObject(response);
        } catch (Exception e) {

        }

        if (fastjsonObject != null) {

            if (fastjsonObject.containsKey("result")) {
                isSuc = fastjsonObject.getBoolean("result");
                msg = fastjsonObject.getString("msg");
            }
        }


        try {
            if (!TextUtils.isEmpty(response)) {//此处不判断 是否解析成功 让子类可以手动重写convertData
                convertData();
            }
        } catch (Exception ex) {
        }
    }


    /**
     * 这个方法在子类中重写 进行json解析判断
     * 由于json解析是个耗时操作 所以 这个方法尽量不要执行多次
     */
    public T convertData() {

        if (fastjsonObject != null) {
            Class<T> cls = ((Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
            return data = (T) FastJSONHelper.parseToObject(fastjsonObject, cls);
        }
        return null;
    }


    public T getData() {

        return data;
    }

    //判断result的状态位是否等于 配置好的error lsit
    public boolean isError() {

        return !isSuc();
    }


    //这个可以重写有时候会有多种状态 都代表成功
    public boolean isSuc() {

        return isSuc;
    }

    public String getMsg() {

        return msg;
    }

    public void setMsg(String msg) {

        this.msg = msg;
    }
}
