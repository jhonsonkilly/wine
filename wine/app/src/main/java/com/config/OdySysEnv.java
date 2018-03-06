package com.config;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.hardware.Camera;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

import com.androidyuan.frame.base.activity.BaseApplication;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import zjw.wine.R;

/**
 * 运行环境信息
 * @author lxs
 * @version 1.0
 */
public final class OdySysEnv {

	/***Log输出标识**/
	private static final String TAG = OdySysEnv.class.getSimpleName();
	
	/***屏幕显示材质**/
	private static final DisplayMetrics mDisplayMetrics = new DisplayMetrics();
	
	/**上下文**/
	private static final Context context = BaseApplication.gainContext();
	
	/**操作系统名称(GT-I9100G)***/
	public static final String MODEL_NUMBER = Build.MODEL;
	
	/**操作系统名称(I9100G)***/
	public static final String DISPLAY_NAME = Build.DISPLAY;
	
	/**操作系统版本(4.4)***/
	public static final String OS_VERSION = Build.VERSION.RELEASE;;
	
	/**应用程序版本***/
	public static final String APP_VERSION = getVersion();
	
	/***屏幕宽度**/
	public static final int SCREEN_WIDTH = getDisplayMetrics().widthPixels;
	
	/***屏幕高度**/
	public static final int SCREEN_HEIGHT = getDisplayMetrics().heightPixels;
	

	/***Activity之间数据传输数据对象Key**/
	public static final String ACTIVITY_DTO_KEY = "ACTIVITY_DTO_KEY";
	
	/**获取系统显示材质***/
	public static DisplayMetrics getDisplayMetrics(){
		  WindowManager windowMgr = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
		  windowMgr.getDefaultDisplay().getMetrics(mDisplayMetrics);
		  return mDisplayMetrics;
	}
	
	/**获取摄像头支持的分辨率***/
	public static List<Camera.Size> getSupportedPreviewSizes(Camera camera){
        Camera.Parameters parameters = camera.getParameters();
        List<Camera.Size> sizeList = parameters.getSupportedPreviewSizes();
        return sizeList;
	}

	/**
	 * 获取应用程序版本（versionName）
	 * @return 当前应用的版本号
	 */
	public static String getVersionCode() {
		PackageManager manager = context.getPackageManager();
		PackageInfo info = null;
		try {
			info = manager.getPackageInfo(context.getPackageName(), 0);
		} catch (NameNotFoundException e) {
			Log.e(TAG, context.getString(R.string.get_app_version_fail_)+e.getMessage());
			return "";
		}

		return info.versionCode + "";
	}

	/**
	 * 获取应用程序版本（versionName）
	 * @return 当前应用的版本号
	 */
	public static String getVersion() {
		PackageManager manager = context.getPackageManager();
		PackageInfo info = null;
		try {
			info = manager.getPackageInfo(context.getPackageName(), 0);
		} catch (NameNotFoundException e) {
			Log.e(TAG, context.getString(R.string.get_app_version_fail_)+e.getMessage());
			return "";
		}
		
		return info.versionName;
	}
	
	/**
	 * 获取系统内核版本
	 * @return
	 */
	public static String getKernelVersion(){
		String strVersion= "";
		FileReader mFileReader = null;
		BufferedReader mBufferedReader = null;
		try {
			mFileReader = new FileReader("/proc/version");
			mBufferedReader = new BufferedReader(mFileReader, 8192);
			String str2 = mBufferedReader.readLine();
			strVersion = str2.split("\\s+")[2];//KernelVersion
			
		} catch (Exception e) {
			Log.e(TAG, context.getString(R.string.get_system_core_fail_)+e.getMessage());
		}finally{
			try {
				mBufferedReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return strVersion;
	}
	
	
	/***
	 * 获取MAC地址
	 * @return
	 */
	public static String getMacAddress(){
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        if(wifiInfo.getMacAddress()!=null){
        	return wifiInfo.getMacAddress();
		} else {
			return "";
		}
	}
	
	/**
	 * 获取运行时间
	 * @return 运行时间(单位/s)
	 */
	public static long getRunTimes() {
		long ut = SystemClock.elapsedRealtime() / 1000;
		if (ut == 0) {
			ut = 1;
		}
		return ut;
	}

	/**
	 * 按钮连续点击（500毫秒内不能同时起效）
	 */
	private static long lastClickTime;
	public static boolean isFastDoubleClick() {
		long time = System.currentTimeMillis();
		if ( time - lastClickTime < 500) {
			return true;
		}
		lastClickTime = time;
		return false;
	}

	/**
	 * 检查apk是否已经安装
	 * @param context
	 * @param packageName
	 */
	public static boolean isAppInstalled(Context context, String packageName) {
		boolean installed = false;
		PackageManager pm = context.getPackageManager();
		try{
			ApplicationInfo info = pm.getApplicationInfo(packageName, 0);
			if(info != null) installed = true;
		}catch(NameNotFoundException e){
			e.printStackTrace();
		}
		return installed;
	}

	/**
	 * Checks if there is enough Space on phone self
	 *
	 */
	public static boolean enoughSpaceOnPhone(long updateSize) {
		return getRealSizeOnPhone() > updateSize;
	}

	/**
	 * get the space is left over on phone self
	 */
	public static long getRealSizeOnPhone() {
		File path = Environment.getDataDirectory();
		StatFs stat = new StatFs(path.getPath());
		long blockSize = stat.getBlockSize();
		long availableBlocks = stat.getAvailableBlocks();
		long realSize = blockSize * availableBlocks;
		return realSize;
	}

	/**
	 * Checks if there is enough Space on SDCard
	 *
	 * @param updateSize
	 *            Size to Check
	 * @return True if the Update will fit on SDCard, false if not enough space on SDCard Will also return false, if the SDCard is
	 *         not mounted as read/write
	 */
	public static boolean enoughSpaceOnSdCard(long updateSize) {
		String status = Environment.getExternalStorageState();
		if (!status.equals(Environment.MEDIA_MOUNTED))
			return false;
		return (updateSize < getRealSizeOnSdcard());
	}

	/**
	 * get the space is left over on sdcard
	 */
	public static long getRealSizeOnSdcard() {
		File path = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
		StatFs stat = new StatFs(path.getPath());
		long blockSize = stat.getBlockSize();
		long availableBlocks = stat.getAvailableBlocks();
		return availableBlocks * blockSize;
	}


}
