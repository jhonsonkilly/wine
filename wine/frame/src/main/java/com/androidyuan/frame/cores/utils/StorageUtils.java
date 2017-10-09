package com.androidyuan.frame.cores.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;

import java.io.File;

import static android.os.Environment.MEDIA_MOUNTED;

/**
 * 获取存储路径
 * <p>
 * Created by wei on 2016/10/28.
 */

public class StorageUtils {

    private static final String EXTERNAL_STORAGE_PERMISSION =
            "android.permission.WRITE_EXTERNAL_STORAGE";

    private StorageUtils() {

    }

    public static File getCacheDirectory(Context context) {

        return getCacheDirectory(context, true);
    }

    public static File getCacheDirectory(Context context, boolean preferExternal) {

        File appCacheDir = null;
        String externalStorageState;
        try {
            externalStorageState = Environment.getExternalStorageState();
        }
        catch (NullPointerException e) {
            externalStorageState = "";
        }
        catch (IncompatibleClassChangeError e) {
            externalStorageState = "";
        }
        if (preferExternal && MEDIA_MOUNTED.equals(externalStorageState)
                && hasExternalStoragePermission(context)) {
            appCacheDir = getExternalCacheDir(context);
        }
        if (appCacheDir == null) {
            appCacheDir = context.getCacheDir();
        }
        if (appCacheDir == null) {
            String cacheDirPath = "/data/data/" + context.getPackageName() + "/cache/";
            appCacheDir = new File(cacheDirPath);
        }
        return appCacheDir;
    }

    public static File getFilesDirectory(Context context) {

        return getFilesDirectory(context, true);
    }

    public static File getFilesDirectory(Context context, boolean preferExternal) {

        File appFilesDir = null;
        String externalStorageState;
        try {
            externalStorageState = Environment.getExternalStorageState();
        }
        catch (NullPointerException e) {
            externalStorageState = "";
        }
        catch (IncompatibleClassChangeError e) {
            externalStorageState = "";
        }
        if (preferExternal && MEDIA_MOUNTED.equals(externalStorageState)
                && hasExternalStoragePermission(context)) {
            appFilesDir = context.getExternalFilesDir(null);
        }
        if (appFilesDir == null) {
            appFilesDir = context.getFilesDir();
        }
        if (appFilesDir == null) {
            String cacheDirPath = "/data/data/" + context.getPackageName() + "/files/";
            appFilesDir = new File(cacheDirPath);
        }
        return appFilesDir;
    }

    private static File getExternalCacheDir(Context context) {

        File dataDir = new File(
                new File(Environment.getExternalStorageDirectory(), "Android"),
                "data"
        );
        File appCacheDir = new File(new File(dataDir, context.getPackageName()), "cache");
        if (!appCacheDir.exists()) {
            if (!appCacheDir.mkdirs()) {
                return null;
            }
        }
        return appCacheDir;
    }

    private static boolean hasExternalStoragePermission(Context context) {

        int perm = context.checkCallingOrSelfPermission(EXTERNAL_STORAGE_PERMISSION);
        return perm == PackageManager.PERMISSION_GRANTED;
    }
}
