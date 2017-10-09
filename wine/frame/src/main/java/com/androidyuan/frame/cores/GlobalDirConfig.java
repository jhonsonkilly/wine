package com.androidyuan.frame.cores;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by weizongwei on 15-11-24.
 * <p>
 * 优点：
 * 1. 优先使用 外置存储  减少android  4.x 版本的内置存储空间浪费;
 * 2. android 6.+  版本免去权限申请。
 * 3. 规范项目 文件存取的路径。
 * 4. 三重保证  img目录下的剪切图片防止系统media服务扫描 存到相册中。
 * 5. 使用的时候 存文件不需要先mkdir 了  因为app启动时都已经给你创建好了。
 */

public class GlobalDirConfig {

    private static final String PROJECTNAME = "androidyuan";
    private static GlobalDirConfig globalInstance;// 唯一配置实例
    public String mSdPath;
    public String mRootPath; //项目杂七杂八文件 根目录
    public String mDataPath;// 用户存放数据等到私有目录下
    public String mImagePath; // 图像目录
    public String mCachePath; // 缓存目录
    public String mLogPath; // 日志目录
    public String mDatabasePath;// 数据库目录
    public String dataFileName = PROJECTNAME + ".db"; // 数据库文件名称

    /**
     * 获取全局唯一实例
     */
    public static GlobalDirConfig getInstance() {

        if (globalInstance == null) {
            globalInstance = new GlobalDirConfig();
        }
        return globalInstance;
    }

    public static void init(Context context) {

        getInstance().initThis(context);
    }

    /**
     * 删除文件或目录
     *
     * @param path
     * @return
     */
    public static boolean DeleteFile(String path) {

        File f = new File(path);
        boolean ret = true;

        if (!f.exists()) {
            ret = false;
        }
        else if (f.exists()) {
            if (f.isDirectory()) {
                File[] files = f.listFiles();
                for (int i = 0; i < files.length; i++) {
                    if (!DeleteFile(files[i].getPath())) {
                        ret = false;
                    }
                }
            }
            else if (!f.delete()) {
                ret = false;
            }
        }
        return ret;
    }

    /**
     * 加载配置信息
     */
    public void initThis(Context context) {
        // 初始化文件目录
        String fileSeparator = System.getProperty("file.separator");

        if (existExternalStorage() && context.getExternalFilesDir(null) != null) {//如果存在 外置存储 则优先采用 外置存储 ，略过外置空间不被共享的小概率事件
            mSdPath = Environment.getExternalStorageDirectory().getAbsolutePath();
            mRootPath = context.getExternalFilesDir(null).getAbsolutePath();
        }
        else {//没办法  只能使用私有目录   否则 app更新 文件下载  图片剪切实在没地方存了
            mSdPath = mRootPath = context.getFilesDir().getAbsolutePath();
        }
        //普通数据
        mDataPath = mRootPath + fileSeparator + "data" + fileSeparator;
        //数据库
        mDatabasePath = mRootPath + fileSeparator + "db" + fileSeparator;
        //图片  linxu 下.开头文件 或者文件夹 都会主动被隐藏 android  请使用RE文件管理器 打开才能看到隐藏目录
        mImagePath = mRootPath + fileSeparator + ".image" + fileSeparator;
        //缓存
        mCachePath = mRootPath + fileSeparator + "cache" + fileSeparator;
        //日志
        mLogPath = mRootPath + fileSeparator + ".log" + fileSeparator;
        //初始化数据库，数据，图片文件夹,日志文件夹
        mkAllDirectory();
        //初始化缓存文件夹
        cleanAndReMkCacheDir();
        return;
    }

    /**
     * 检查并创建私有文件夹
     */
    private void mkAllDirectory() {
        // 创建私有文件夹
        String[] path = {mRootPath, mDatabasePath, mDataPath, mImagePath, mCachePath, mLogPath};
        //这里创建目录要先创建root 再创建子目录  否则创建失败
        for (String x : path) {
            File fi = new File(x);
            if (!fi.exists()) {
                fi.mkdir();
            }
        }
    }

    /**
     * 每次启动需要删除的旧文件夹
     */
    private void cleanAndReMkCacheDir() {

        if ((!TextUtils.isEmpty(mSdPath) && new File(mSdPath).canRead())) {

            String[] path = {mCachePath, mImagePath};
            for (String x : path) {
                File fi = new File(x);
                if (!fi.exists()) {
                    fi.mkdir();
                }
                else {
                    DeleteFile(x);
                    fi.mkdir();
                }
            }
            // 禁止系统Media搜索程序目录;
            String nomediapath = mImagePath + ".nomedia";
            File nomedia = new File(nomediapath);
            try {
                if (!nomedia.exists())
                    nomedia.createNewFile();
            }
            catch (IOException e) {
            }
        }
    }

    /**
     * 程序退出清空缓存数据
     */
    public void clearCacheFile() {

        DeleteFile(mCachePath);
        DeleteFile(mImagePath);
    }

    /**
     * 是否存在外置存储空间
     *
     * @return
     */
    private boolean existExternalStorage() {

        return Environment.getExternalStorageState()
                .equals(Environment.MEDIA_MOUNTED); //判断sd卡是否存在
    }


}
