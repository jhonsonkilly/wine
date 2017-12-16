package com.utils;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>Copyright:Copyright(c) 2016</p>
 * <p>Company:上海来伊份电子商务有限公司</p>
 * <p>包名:com.utils</p>
 * <p>文件名:wine</p>
 * <p>类更新历史信息</p>
 *
 * @todo <a href="mailto:zhoujiawei@laiyifen.com">vernal(周佳伟)</a>
 */
public class ParamsUtils {


    //获取健值对
    public static Map<String, String> getParameter(String url, String parameter) {
        try {
            final String charset = "utf-8";
            url = URLDecoder.decode(url, charset);
            if (url.indexOf('?') != -1) {
                final String contents = url.substring(url.indexOf('?') + 1);
                HashMap<String, String> map = new HashMap<String, String>();
                String[] keyValues = contents.split("&");
                for (int i = 0; i < keyValues.length; i++) {
                    String key = keyValues[i].substring(0, keyValues[i].indexOf("="));
                    String value = keyValues[i].substring(keyValues[i].indexOf("=") + 1);

                    if (key.equals(parameter)) {
                        if (value == null || "".equals(value.trim())) {
                            return null;
                        }
                        map.put(key, value);
                        return map;
                    }

                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    //获得值
    public static String getParameterValue(String url, String parameter) {
        try {
            final String charset = "utf-8";
            url = URLDecoder.decode(url, charset);
            if (url.indexOf('?') != -1) {
                final String contents = url.substring(url.indexOf('?') + 1);
                HashMap<String, String> map = new HashMap<String, String>();
                String[] keyValues = contents.split("&");
                for (int i = 0; i < keyValues.length; i++) {
                    String key = keyValues[i].substring(0, keyValues[i].indexOf("="));
                    String value = keyValues[i].substring(keyValues[i].indexOf("=") + 1);

                    if (key.equals(parameter)) {
                        if (value == null || "".equals(value.trim())) {
                            return null;
                        }

                        return value;
                    }

                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
