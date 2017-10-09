package com.androidyuan.frame.cores.log;

import android.content.Context;
import android.util.Log;

import com.androidyuan.frame.cores.FrameApplication;

import java.lang.reflect.Field;

/**
 * Created by wei on 16-2-20
 */

public class CommonLogger {


    public static boolean sIsOpen = false;
    private String TAG = " " + this.getClass().getSimpleName();

    public CommonLogger() {

    }

    public CommonLogger(String s) {

        TAG = s;
    }

    /**
     * 自动取主工程的 BuildConfig
     */
    public static void init(Context context) {

        Class cls = null;
        try {
            //动态获取BuildConfig
            new CommonLogger()
                    .d(context.getApplicationContext().getPackageName() + ".BuildConfig");
            cls = getBuildConfig(context.getApplicationContext().getPackageName() + ".BuildConfig");
            if (cls == null) return;

            Field field = cls.getField("DEBUG");
            boolean isDebug = field.getBoolean(null);
            CommonLogger.initLogger(isDebug);
        }
        catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private static void initLogger(boolean d) {

        sIsOpen = d;
        new CommonLogger().d(" isDebug = " + d);
    }

    public static CommonLogger buildLogger(Object obj) {

        return new CommonLogger(" " + obj.getClass().getSimpleName());
    }

    public static CommonLogger buildLogger(String s) {

        return new CommonLogger(s);
    }


    private static Class getBuildConfig(String clsname) {

        Class cls = null;
        try {
            cls = Class.forName(clsname);
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return cls;
    }

    public static void initDebugConfig(FrameApplication frameApplication) {

        init(frameApplication);
    }

    public void d(String s) {

        if (sIsOpen) {
            Log.d(TAG, s + "");
        }
    }

    public void w(String s) {

        Log.w(TAG, s + "");
    }

    public void e(String s) {

        Log.e(TAG, s + "");
    }

    public void e(Throwable throwable) {

        Log.wtf(TAG, throwable);
    }

    public void info(String s) {

        Log.i(TAG, s);
    }

    public void warning(String s) {

        Log.w(TAG, s);
    }
}
