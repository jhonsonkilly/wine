package com.androidyuan.frame.cores.utils;

import java.text.DecimalFormat;

/**
 * Created by weizongwei on 15-12-16.
 */
public class FormatUtils {


    //doulue 转String  保留两位小树
    public static final String getDoubleFooter(double d1) {

        d1 += 0.0;
        DecimalFormat df = new DecimalFormat("######0.00");
        String str = "" + df.format(d1);
        return str;
    }
}
