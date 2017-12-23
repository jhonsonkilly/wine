package com.androidyuan.frame.cores.utils.image;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.Keep;
import android.text.TextUtils;
import android.util.Log;
import android.view.ViewGroup;

import com.androidyuan.frame.cores.FrameApplication;
import com.facebook.cache.common.CacheKey;
import com.facebook.common.executors.CallerThreadExecutor;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.cache.DefaultCacheKeyFactory;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.core.ImagePipelineFactory;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import java.io.File;

/**
 * Created by wei on 16-3-24.
 * <p/>
 * 配置freco的方法 Fresco.initialize(this, FrescoUtils.getImagePipelineConfig(FrameApplication.getApp()));
 * <p/>
 * 配置之后就可以尽情地使用了
 * <p/>
 * //旧的方案
 * ImagePipelineConfig config= ImagePipelineConfig.newBuilder(this)
 * .setBitmapsConfig(Bitmap.Config.RGB_565).build();
 */
@Keep
public class FrescoUtils {

    // 的在线图片尾部追加 这个可以 实现修圆
    private static final String GIF2JPGEND = "imageView2/format/jpg";

    private static ImagePipelineConfig sImagePipelineConfig;

    public static void initFresco(Context context) {

        Fresco.initialize(context, getImagePipelineConfig(context));
    }

    private static ImagePipelineConfig getImagePipelineConfig(Context context) {

        if (sImagePipelineConfig == null) {
            sImagePipelineConfig = configureCaches(context);
        }
        return sImagePipelineConfig;
    }


    private static ImagePipelineConfig configureCaches(Context context) {

        //配置了能够对PNG,JPG进行缩放,原来只能对JPG
        ImagePipelineConfig.Builder configBuilder = ImagePipelineConfig.newBuilder(context)
                .setDownsampleEnabled(true)
                .setBitmapsConfig(Bitmap.Config.RGB_565)
                .setDownsampleEnabled(true);
        //.setBitmapMemoryCacheParamsSupplier(mSupplierMemoryCacheParams);

        return configBuilder.build();

        //fresco的DefaultEncodedMemoryCacheParamsSupplier类里面配置了最大 是maxsize/4
        // 但是源码里写了 低于 16MB的情况也会按照3M
        //如果考虑的更细致 这里可以强制配置 1/8  调用setBitmapMemoryCacheParamsSupplier() 配置一下
    }


    /**
     * 设置加载中的图
     *
     * @param view
     * @param resId
     */
    public static void setLodingImgRes(SimpleDraweeView view, int resId) {

        view.getHierarchy().setFadeDuration(0);
        view.getHierarchy().setPlaceholderImage(resId);
    }

    /**
     * 设置出错默认图
     *
     * @param view
     * @param resId
     */
    public static void setFaildImgRes(SimpleDraweeView view, int resId) {

        view.getHierarchy().setFadeDuration(0);
        view.getHierarchy().setFailureImage(view.getResources().getDrawable(resId));
    }


    /**
     * 设置加载中的图和出错的图一致  一次代替上面两个方法
     *
     * @param view
     * @param resId
     */
    public static void setDefaultImgRes(SimpleDraweeView view, int resId) {

        view.getHierarchy().setFadeDuration(0);
        view.getHierarchy().setPlaceholderImage(resId);
        view.getHierarchy().setFailureImage(view.getResources().getDrawable(resId));
    }

    /**
     * 给imgview实现圆形功能 但是不带包边
     *
     * @param view
     */
    public static void setViewRound(SimpleDraweeView view) {

        RoundingParams roundingParams = new RoundingParams();

        roundingParams.setRoundAsCircle(true);
        roundingParams.setRoundingMethod(RoundingParams.RoundingMethod.BITMAP_ONLY);
        view.getHierarchy().setRoundingParams(roundingParams);
    }

    /**
     * 给imgview实现圆形功能 并带有包边
     *
     * @param view     SimpleDraweeView
     * @param borderDP border跨度
     * @param colRes   颜色资源
     */
    public static void setViewRound(SimpleDraweeView view, float borderDP, int colRes) {

        float border_width = dip2px(borderDP);
        RoundingParams roundingParams = new RoundingParams();
        roundingParams.setRoundAsCircle(true);
        roundingParams.setBorder(colRes, border_width);
        roundingParams.setPadding(border_width);//不设置内边距 不会显示出边框

        //BITMAP_ONLY 是采用BitmapShader的方式 会造成内存抖动 注意  大图注意
        roundingParams.setRoundingMethod(RoundingParams.RoundingMethod.BITMAP_ONLY);
        view.getHierarchy().setRoundingParams(roundingParams);
        view.setAspectRatio(16.0f / 9);
    }

    /**
     * 关掉 view设置bitmap时的动画
     *
     * @param view
     */
    public static void closeAnim(SimpleDraweeView view) {

        view.getHierarchy().setFadeDuration(0);
    }


    /**
     * 关掉 view设置bitmap时的动画
     *
     * @param view
     */
    public static void setAspectRatio(SimpleDraweeView view, float ratio) {

        view.setAspectRatio(ratio);
    }


    /**
     * 给imgview实现圆形功能
     *
     * @param view
     */
    public static void setViewCorner(SimpleDraweeView view, int color) {

        RoundingParams cornerParams = RoundingParams.fromCornersRadius(7f);
        cornerParams.setOverlayColor(color);

        //BITMAP_ONLY 是采用BitmapShader的方式 会造成内存抖动 注意!!!  大图注意!!!
        cornerParams.setRoundingMethod(RoundingParams.RoundingMethod.BITMAP_ONLY);
        cornerParams.setCornersRadius(dip2px(9));
        view.getHierarchy().setRoundingParams(cornerParams);
    }

    public static float dip2px(float dipValue) {

        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return (dipValue * scale + 0.5f);
    }


    public static void displayUrl(SimpleDraweeView view, String url) {

        if (TextUtils.isEmpty(url)) {
            view.setImageURI(Uri.parse(""));
        } else {
              Log.i("FrescoUtils", url);
            view.setImageURI(Uri.parse(url));
        }
    }

    public static void displayUrlFromRes(SimpleDraweeView view, int res) {

        if (res == 0) {
            return;
        }
        view.setImageResource(res);
    }

    public static void displayLocalUrl(SimpleDraweeView view, String url) {

        if (TextUtils.isEmpty(url)) {
            view.setImageURI((Uri.parse("")));
        } else {
            view.setImageURI(Uri.fromFile(new File(url)));
        }
    }


    //让fresco主动把图片缓存下来
    public static void gotoCacheImg(String url) {

        if (TextUtils.isEmpty(url))
            return;


        ImageRequest imageRequest = ImageRequestBuilder
                .newBuilderWithSource(Uri.parse(url))
                //.setPostprocessor(postprocessor)
                .build();

        Fresco.getImagePipeline().fetchDecodedImage(imageRequest, null);
    }


    public static void fetchToBitmapCache(String url) {

        if (!TextUtils.isEmpty(url)) {
            ImageRequest imageRequest = ImageRequest.fromUri(url);
            Fresco.getImagePipeline().prefetchToBitmapCache(imageRequest, null);
        }
    }

    public static void loadUrlBySync(SimpleDraweeView view, String url) {

        if (!TextUtils.isEmpty(url)) {
            //Fresco.getImagePipeline().evictFromCache(Uri.parse(url));
            displayUrl(view, url);
        }
    }

    /**
     * 展示在线地址的头像 自动把服务器的gif转成jpg下载下来
     *
     * @param imageView
     * @param url
     */
    public static void displayAvatar(final SimpleDraweeView imageView, String url) {

        if (TextUtils.isEmpty(url)) {
            imageView.setImageURI(Uri.parse(""));
        } else {
            if (!url.contains("?")) {
                url += "?";
            }
            imageView.setImageURI(Uri.parse(url + GIF2JPGEND));
        }
    }

    private static void updateViewSize(final SimpleDraweeView draweeView, ImageInfo imageInfo) {

        if (imageInfo != null) {


            int minw = 0;
            int minh = 0;

            if (Build.VERSION.SDK_INT >= 16) {
                minh = draweeView.getMinimumHeight();
                minw = draweeView.getMinimumWidth();
            }
            draweeView.getLayoutParams().width = imageInfo.getWidth();
            if (imageInfo.getWidth() < minw) {//如果图片太小的情况下 则根据mindWidth进行设置 保证不要被设置为很小
                draweeView.getLayoutParams().width = minh;
            } else if (imageInfo.getWidth() > Resources.getSystem().getDisplayMetrics().widthPixels) {//如果图片的宽度远远大于屏幕宽度 则: 不管他 他自己会充满父控件
            }

            draweeView.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;
            draweeView.setAspectRatio((float) imageInfo.getWidth() / imageInfo.getHeight());
        }
    }


    /**
     * 根据下载好的图片的宽度高度 自动修改宽高比
     * <p/>
     * 这里要求控件宽度高度为wrap_content 理论上来说不推荐这么用,因为这样子fresco就无法对bitmap进行缩放来节省内存了
     * 如果不是某些特殊需求,比如 使用的是重写的SimpleDraweeView
     * 我们还是 推荐使用 AutoRatioDraweeView  毕竟这个方法不支持图片载入出错的之后的东西 功能不全
     */
    public static void displayAutoRatioFromURL(final SimpleDraweeView draweeView, String url) {

        if (TextUtils.isEmpty(url)) {
            url = "";
        }

        ControllerListener listener = new BaseControllerListener<ImageInfo>() {
            @Override
            public void onIntermediateImageSet(String id, ImageInfo imageInfo) {

                super.onIntermediateImageSet(id, imageInfo);
                updateViewSize(draweeView, imageInfo);
            }

            @Override
            public void onFinalImageSet(String id, ImageInfo imageInfo, Animatable animatable) {

                super.onFinalImageSet(id, imageInfo, animatable);
                updateViewSize(draweeView, imageInfo);
            }

        };

        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(Uri.parse(url))
                .setControllerListener(listener)
                .build();
        draweeView.setController(controller);
    }

    /**
     * fresco载入图片的监听的方法
     *
     * @param view
     * @param url
     * @param listener
     */
    public static void displayUrlAndListen(SimpleDraweeView view, String url, final ImageLoadListener listener) {

        ControllerListener controllerListener = new BaseControllerListener<ImageInfo>() {
            @Override
            public void onFailure(String id, Throwable throwable) {

                super.onFailure(id, throwable);
                if (listener != null) {
                    listener.onError();
                }
            }

            @Override
            public void onFinalImageSet(String id, ImageInfo imageInfo, Animatable animatable) {

                super.onFinalImageSet(id, imageInfo, animatable);
                if (listener != null) {
                    listener.onSucceed();
                }
            }
        };

        Uri uri;
        if (TextUtils.isEmpty(url)) {
            uri = Uri.parse("");
        } else {
            uri = Uri.parse(url);
        }

        DraweeController controller
                =
                Fresco.newDraweeControllerBuilder()
                        .setControllerListener(controllerListener)
                        .setUri(uri)
                        .build();
        view.setController(controller);
    }

    /**
     * 主动load一个bitmap 通过callback得到
     *
     * @param url    图片地址
     * @param listen callback
     */
    public static void loadBitmapAndRemoveMemory(final String url, final BitmapLoadCallback listen) {

        Uri uri;
        if (TextUtils.isEmpty(url)) {
            uri = Uri.parse("");
        } else {
            uri = Uri.parse(url);
        }

        ImageRequest imageRequest = ImageRequestBuilder
                .newBuilderWithSource(uri)
                .setProgressiveRenderingEnabled(true)
                .build();

        ImagePipeline imagePipeline = Fresco.getImagePipeline();
        DataSource<CloseableReference<CloseableImage>> dataSource
                =
                imagePipeline.fetchDecodedImage(imageRequest, FrameApplication.getApp());

        BaseBitmapDataSubscriber subscriber = new BaseBitmapDataSubscriber() {
            @Override
            public void onNewResultImpl(Bitmap bitmap) {

                Bitmap newbit = null;
                try {
                    if (bitmap != null) {
                        newbit = bitmap.copy(bitmap.getConfig(), true);//fresco很容易给你释放掉，所以copy一份出来
                        FrescoUtils.removeImgFromMemory(url);//让fresco从ram中移除掉这张图
                    }
                } catch (Exception ex) {//OOM异常
                }

                if (listen != null && newbit != null && newbit.getByteCount() > 0) {
                    listen.onSucceed(newbit);
                }
            }

            @Override
            public void onFailureImpl(DataSource dataSource) {

                if (listen != null) {
                    listen.onError();
                }
            }
        };

        dataSource.subscribe(subscriber, CallerThreadExecutor.getInstance());
    }

    /**
     * 主动load一个bitmap 通过callback得到,不用缩放传0,0就可以了
     *
     * @param url    图片地址
     * @param listen callback
     */
    public static void loadBitmap(final String url, final BitmapLoadCallback listen) {

        Uri uri;
        if (TextUtils.isEmpty(url)) {
            uri = Uri.parse("");
        } else {
            uri = Uri.parse(url);
        }

        ImageRequest imageRequest = ImageRequestBuilder
                .newBuilderWithSource(uri)
                .setProgressiveRenderingEnabled(true)
                .build();

        ImagePipeline imagePipeline = Fresco.getImagePipeline();
        DataSource<CloseableReference<CloseableImage>> dataSource
                =
                imagePipeline.fetchDecodedImage(imageRequest, FrameApplication.getApp());

        BaseBitmapDataSubscriber subscriber = new BaseBitmapDataSubscriber() {
            @Override
            public void onNewResultImpl(Bitmap bitmap) {

                Bitmap newbit = null;

                try {
                    if (bitmap != null) {
                        newbit = bitmap.copy(bitmap.getConfig(), true);//fresco很容易给你释放掉，所以copy一份出来
                        //FrescoUtils.removeImgFromMemory(url);//让fresco从ram中移除掉这张图
                        Log.i("+++++++++++", newbit.getByteCount() + "");

                    }
                } catch (Exception ex) {//OOM异常
                }

                if (listen != null && newbit != null && newbit.getByteCount() > 0) {
                    listen.onSucceed(newbit);
                }
            }

            @Override
            public void onFailureImpl(DataSource dataSource) {

                if (listen != null) {
                    listen.onError();
                }
            }
        };

        dataSource.subscribe(subscriber, CallerThreadExecutor.getInstance());
    }

    /**
     * @author Chris
     * @time 16/6/14 下午3:22
     * @function 低版本(API16)会报库异常, 待解决, , 1.1版本没有问题了
     */
    public static void loadreSizeBitmap(final String url, final BitmapLoadCallback listen, final int width, final int height) {

        Uri uri;
        if (TextUtils.isEmpty(url)) {
            uri = Uri.parse("");
        } else {
            uri = Uri.parse(url);
        }

        ImageRequest imageRequest = ImageRequestBuilder
                .newBuilderWithSource(uri)

                .setResizeOptions(new ResizeOptions(width, height))

                .build();


        ImagePipeline imagePipeline = Fresco.getImagePipeline();
        DataSource<CloseableReference<CloseableImage>> dataSource
                =
                imagePipeline.fetchDecodedImage(imageRequest, FrameApplication.getApp());

        BaseBitmapDataSubscriber subscriber = new BaseBitmapDataSubscriber() {
            @Override
            public void onNewResultImpl(Bitmap bitmap) {

                Bitmap newbit = null;
                Bitmap newSizeBit = null;

                try {
                    if (bitmap != null) {
                        newbit = bitmap.copy(bitmap.getConfig(), true);//fresco很容易给你释放掉，所以copy一份出来
                        //FrescoUtils.removeImgFromMemory(url);//让fresco从ram中移除掉这张图
                        //newSizeBit=BitmapUtils.getChangeSizeBitmap(newbit,width,height);
                        Log.i("+++++++++++", newbit.getByteCount() + "");

                    }
                } catch (Exception ex) {//OOM异常
                }

                if (listen != null && newbit != null && newbit.getByteCount() > 0) {
                    listen.onSucceed(newbit);
                }
            }

            @Override
            public void onFailureImpl(DataSource dataSource) {

                if (listen != null) {
                    listen.onError();
                }
            }
        };

        dataSource.subscribe(subscriber, CallerThreadExecutor.getInstance());
    }

    public static void loadBitmapNotCopy(final String url, final BitmapLoadCallback listen) {

        Uri uri;
        if (TextUtils.isEmpty(url)) {
            uri = Uri.parse("");
        } else {
            uri = Uri.parse(url);
        }

        ImageRequest imageRequest = ImageRequestBuilder
                .newBuilderWithSource(uri)
                .build();

        ImagePipeline imagePipeline = Fresco.getImagePipeline();
        DataSource<CloseableReference<CloseableImage>> dataSource
                =
                imagePipeline.fetchDecodedImage(imageRequest, FrameApplication.getApp());

        BaseBitmapDataSubscriber subscriber = new BaseBitmapDataSubscriber() {
            @Override
            public void onNewResultImpl(Bitmap bitmap) {


                if (listen != null && bitmap != null && bitmap.getByteCount() > 0) {
                    listen.onSucceed(bitmap);
                }
            }

            @Override
            public void onFailureImpl(DataSource dataSource) {

                if (listen != null) {
                    listen.onError();
                }
            }
        };

        dataSource.subscribe(subscriber, CallerThreadExecutor.getInstance());
    }

    /**
     * 判断某个 url的图片是否在本地中
     *
     * @param url
     * @return true：in  false：not in
     */
    public static boolean isInDisk(String url) {

        if (TextUtils.isEmpty(url)) {
            return false;
        }
        Uri uri = Uri.parse(url);
        CacheKey cacheKey = DefaultCacheKeyFactory.getInstance().getEncodedCacheKey(ImageRequest.fromUri(uri), null);
        return ImagePipelineFactory.getInstance().getMainBufferedDiskCache().contains(cacheKey).getResult() != null
                ||
                ImagePipelineFactory.getInstance().getSmallImageFileCache().hasKey(cacheKey);
    }

    /**
     * 判断某个 url的图片是否在内存中
     *
     * @param url
     * @return true：in  false：not in
     */
    public static boolean isInMemory(String url) {

        if (TextUtils.isEmpty(url)) {
            return false;
        }
        ImagePipeline imagePipeline = Fresco.getImagePipeline();

        boolean inMemoryCache = false;
        try {
            Uri uri = Uri.parse(url);
            inMemoryCache = imagePipeline.isInBitmapMemoryCache(uri);
        } catch (Exception ex) {
        }
        return inMemoryCache;
    }

    /**
     * 把大图从内存中主动清除掉
     * 小图不要做这个工作 没有意义 不能节省多少内存
     *
     * @param url
     */
    public static void removeImgFromMemory(String url) {

        if (TextUtils.isEmpty(url)) {
            return;
        } else {

            try {
                if (isInMemory(url)) {
                    Uri uri = Uri.parse(url);
                    ImagePipeline imagePipeline = Fresco.getImagePipeline();
                    imagePipeline.evictFromMemoryCache(uri);
                }
            } catch (Exception ex) {
            }
        }
    }


    public static void removeImgFromCache(String url) {

        if (TextUtils.isEmpty(url)) {
            return;
        } else {

            try {
                Uri uri = Uri.parse(url);
                ImagePipeline imagePipeline = Fresco.getImagePipeline();
                imagePipeline.evictFromMemoryCache(uri);
                imagePipeline.evictFromDiskCache(uri);
            } catch (Exception ex) {
            }
        }
    }


    //清除本地的所有的缓存 包括内存缓存
    public static void clearDataCache() {

        ImagePipeline imagePipeline = Fresco.getImagePipeline();
        imagePipeline.clearMemoryCaches();
        imagePipeline.clearDiskCaches();
        imagePipeline.clearCaches();

    }


    public interface ImageLoadListener {

        void onSucceed();

        void onError();

    }

    public interface BitmapLoadCallback {

        void onSucceed(Bitmap bitmap);

        void onError();
    }

}
