package com.androidyuan.frame.cores.utils;

/**
 * Created by weizongwei on 15-11-25.
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.List;


/**
 * Created by weizongwei on 2014/8/8.
 * <p/>
 * JSON帮助类  这个可以做一个实例研究代码，也可以作为一个工具类 去调用。
 * FastJson是一个Json处理工具包，包括“序列化”和“反序列化”两部分,Fastjson是一个Java语言编写的高性能功能完善的JSON库。Fastjson支持java bean的直接序列化。
 * 你可以使用com.alibaba.fastjson.JSON这个类进行序列化和反序列化。fastjson采用独创的算法，将parse的速度提升到极致，超过所有json库。
 * <p/>
 * FastJson速度最快，fastjson具有极快的性能，超越任其他的Java Json parser。
 * FastJson功能强大，完全支持Java Bean、集合、Map、日期、Enum，支持范型，支持自省；无依赖。
 * Fastjson API入口类是com.alibaba.fastjson.JSON，常用的序列化操作都可以在JSON类上的静态方法直接完成。
 * 使用Fastjson首先在官网下载,然后应用到自己的项目中
 * <p/>
 * 在需要解析JSON的Response中,需要导入如下四个包。
 * import  com.alibaba.fastjson.JSON;
 * import com.alibaba.fastjson.JSONObject;
 * import com.alibaba.fastjson.JSONArray;
 * import com.alibaba.fastjson.JSONException;
 * <p/>
 * <p/>
 * 一.首先概述一下Fastjson中的经常调用的方法：  避免使用org.apche.json的包。
 * <p/>
 * 1 public static final Object parse(String text); // 把JSON文本parse为JSONObject或者JSONArray
 * 2 public static final JSONObject parseObject(String text)； // 把JSON文本parse成JSONObject
 * 3 public static final  T parseObject(String text, Class clazz); // 把JSON文本parse为JavaBean
 * 4 public static final JSONArray parseArray(String text); // 把JSON文本parse成JSONArray
 * 5 public static final  List parseArray(String text, Class clazz); //把JSON文本parse成JavaBean集合
 * 6 public static final String toJSONString(Object object); // 将JavaBean序列化为JSON文本
 * 7 public static final String toJSONString(Object object, boolean prettyFormat); // 将JavaBean序列化为带格式的JSON文本
 * 8 public static final Object toJSON(Object javaObject); 将JavaBean转换为JSONObject或者JSONArray。
 */

public class FastJSONHelper {
    //第一部分  javabean转JSON  或者jsonstr

    /**
     * 将一个 Object 或者List对象转化为JSONObject或者JSONArray
     *
     * @param ObjOrList Object 或者List对象
     * @return
     */
    public static Object toJSON(Object ObjOrList) {

        Object obj = null;

        try {
            obj = JSON.toJSON(ObjOrList);

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return obj;
    }

    /**
     * 将一个 Object 或者List对象转化为JSOObject或者JSONArray
     *
     * @param ObjOrList Object 或者List对象 或者hashmap 但是如果是set  就会有问题
     * @return
     */
    public static String toJSONStr(Object ObjOrList) {

        String jsonstr = "";

        try {
            jsonstr = JSON.toJSONString(ObjOrList);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return jsonstr;
    }


    //第二部分字符串转  obj list

    /**
     * 字符串转obj
     *
     * @param jsonstr
     * @param clazz
     * @return
     */
    public static Object parseToObject(String jsonstr, Class<?> clazz) {

        Object parseObj = null;
        try {
            parseObj = JSON.parseObject(jsonstr, clazz);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return parseObj;
    }

    /**
     * 字符串转list
     *
     * @param jsonstr
     * @param clazz
     * @return
     */
    public static List<?> parseToList(String jsonstr, Class<?> clazz) {

        List<?> parseObj = null;
        try {
            parseObj = JSON.parseArray(jsonstr, clazz);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return parseObj;
    }


    //第三部分  字符串转JSONObj  或者JSONArray

    /**
     * 字符串转jsonobj
     *
     * @param jsonstr
     * @return
     */
    public static JSONObject parseToJSONObejct(String jsonstr) {

        JSONObject parseObj = null;
        try {
            parseObj = JSON.parseObject(jsonstr);

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return parseObj;
    }

    /**
     * 字符串转list
     *
     * @param jsonstr
     * @return
     */
    public static JSONArray parseToJSONArray(String jsonstr) {

        JSONArray parseObj = null;
        try {
            parseObj = JSON.parseArray(jsonstr);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return parseObj;
    }


    //第四部分 com.alibaba包下 JSONObj 或者JSONArr 转 javabean或者 java array

    /**
     * @param jsonObj fastjson下的jsonObj
     * @param obj
     * @return
     */
    public static Object parseToObject(JSONObject jsonObj, Class<?> obj) {

        Object parseObj = null;
        try {
            parseObj = JSON.parseObject(jsonObj + "", obj);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return parseObj;
    }

    /**
     * @param jsonArr fastjson下的jsonArr
     * @param obj
     * @return
     */
    public static List<?> parseToList(JSONArray jsonArr, Class<?> obj) {

        List list = null;

        try {
            list = JSON.parseArray(jsonArr + "", obj);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    //第五部分 将android系统下的JSONObj 或者JSONArr 转 javabean或者 javaarr
    //第五部分看似没用不过想想特么的 用的人偶尔还是会用到系统的JSON对象  所以决定加下面这两个方法

    /**
     * @param jsonObj android系统下的JSONObj
     * @param obj
     * @return
     */
    public static Object parseToObject(org.json.JSONObject jsonObj, Class<?> obj) {

        Object parseObj = null;
        try {
            parseObj = JSON.parseObject(jsonObj.toString(), obj);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return parseObj;
    }

    /**
     * @param jsonArr android系统下的JSONArr
     * @param obj
     * @return
     */
    public static List<?> parseToList(org.json.JSONArray jsonArr, Class<?> obj) {

        List list = null;
        try {
            list = JSON.parseArray(jsonArr.toString(), obj);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

}