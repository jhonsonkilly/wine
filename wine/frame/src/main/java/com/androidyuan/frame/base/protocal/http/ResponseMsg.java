package com.androidyuan.frame.base.protocal.http;

import android.text.TextUtils;

import com.alibaba.fastjson.JSONObject;
import com.androidyuan.frame.cores.log.CommonLogger;
import com.androidyuan.frame.cores.utils.FastJSONHelper;

import java.lang.reflect.ParameterizedType;

/**
 * 这个类会的赋值 会通过JsonHelper 把json字符串转成这个类所以需要谨慎加减字段
 * <p>
 * T是任意类型的对象  用于json中res字段直接转T类型的对象  T类型的对象建议放在 com.base.protocal.object包下面
 */
public abstract class ResponseMsg<T> {


    protected T data;

    protected int resmsgWhat = 0;
    protected int result = 0; // 状态位
    protected JSONObject fastjsonObject;
    private String msg = ""; // 消息名称
    //
    private String response;

    public ResponseMsg(int what) {

        resmsgWhat = what;
    }

    public ResponseMsg() {

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

            if (fastjsonObject.containsKey("message")) {
                msg = fastjsonObject.getString("message");
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

        if (fastjsonObject != null && fastjsonObject.containsKey("result")) {
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

        return result == 200;
    }


    //一堆 get set 方法
    public int getResult() {

        return result;
    }

    public void setResult(int result) {

        this.result = result;
    }

    public String getMsg() {

        return msg;
    }

    public void setMsg(String msg) {

        this.msg = msg;
    }


}
