package com.androidyuan.frame.cores.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

/**
 * @author Chris
 * @time 16/3/23 下午3:28
 * @function  SP辅助类
 */
public class SharedPreferencesHelper {
    public static final String PREFERENCE_NAME = "QuanMing_PREFERENCES";
    private static SharedPreferencesHelper instance;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    public static SharedPreferencesHelper getInstance(Context context) {
        if (instance == null&&context.getApplicationContext()!=null)
            instance = new SharedPreferencesHelper(
                    context.getApplicationContext());
        return instance;
    }



    public SharedPreferencesHelper(Context context) {
        sp = context.getSharedPreferences(PREFERENCE_NAME, 2);
        editor = sp.edit();
    }
    public long getLongValue(String key) {
        if (key != null && !key.equals("")) {
            return sp.getLong(key, 0);
        }
        return 0;
    }

    public String getStringValue(String key) {
        if (key != null && !key.equals("")) {
            return sp.getString(key, null);
        }
        return null;
    }

    public String getStringValue(String key, String def) {
        if (key != null && !key.equals("")) {
            return sp.getString(key, null);
        }
        return def;
    }

    public int getIntValue(String key) {
        if (key != null && !key.equals("")) {
            return sp.getInt(key, 0);
        }
        return 0;
    }

    public boolean getBooleanValue(String key) {
        if (key != null && !key.equals("")) {
            return sp.getBoolean(key, true);
        }
        return true;
    }

    public boolean getBooleanValue(String key, boolean value) {
        if (key != null && !key.equals("")) {
            return sp.getBoolean(key, value);
        }
        return value;
    }

    public boolean getPlayerBooleanValue(String key) {
        if (key != null && !key.equals("")) {
            return sp.getBoolean(key, true);
        }
        return true;
    }

    public boolean getOnPlayingBooleanValue(String key) {
        if (key != null && !key.equals("")) {
            return sp.getBoolean(key, false);
        }
        return false;
    }

    public float getFloatValue(String key) {
        if (key != null && !key.equals("")) {
            return sp.getFloat(key, 0);
        }
        return 0;
    }

    public void putStringValue(String key, String value) {
        if (key != null && !key.equals("")) {
            editor = sp.edit();
            editor.putString(key, value);
            commitOrApply(editor);
        }
    }

    public void putIntValue(String key, int value) {
        if (key != null && !key.equals("")) {
            editor = sp.edit();
            editor.putInt(key, value);
            commitOrApply(editor);
        }
    }

    public void putBooleanValue(String key, boolean value) {
        if (key != null && !key.equals("")) {
            editor = sp.edit();
            editor.putBoolean(key, value);
            commitOrApply(editor);
        }
    }

    public void putLongValue(String key, long value) {
        if (key != null && !key.equals("")) {
            editor = sp.edit();
            editor.putLong(key, value);
            commitOrApply(editor);
        }
    }

    public void putFloatValue(String key, Float value) {
        if (key != null && !key.equals("")) {
            editor = sp.edit();
            editor.putFloat(key, value);
            commitOrApply(editor);
        }
    }

    public void remove(String key) {
        if (key != null && !key.equals("")) {
            editor = sp.edit();
            editor.remove(key);
            commitOrApply(editor);
        }
    }
    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    private void commitOrApply(SharedPreferences.Editor editor) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.GINGERBREAD) {
            editor.commit();
        } else {
            editor.apply();
        }
    }

    public void clearAll() {
        editor.clear();
        commitOrApply(editor);
    }
}
