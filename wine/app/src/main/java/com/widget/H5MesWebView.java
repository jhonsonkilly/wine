package com.widget;

import android.content.Context;
import android.util.AttributeSet;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Copyright:Copyright(c) 2016</p>
 * <p>Company:上海来伊份电子商务有限公司</p>
 * <p>包名:com.widget</p>
 * <p>文件名:wine</p>
 * <p>类更新历史信息</p>
 *
 * @todo <a href="mailto:zhoujiawei@laiyifen.com">vernal(周佳伟)</a>
 */
public class H5MesWebView extends QMWebview {
    public H5MesWebView(Context context) {
        super(context);
    }

    public H5MesWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public H5MesWebView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public String putExParams(HashMap<String, String> extramap) {
        this.map = extramap;
        StringBuilder builder = new StringBuilder();

        for (Map.Entry<String, String> entry : map.entrySet()) {

            builder.append("&" + entry.getKey() + "=");
            builder.append(entry.getValue());
        }
        return builder.toString();
    }





}
