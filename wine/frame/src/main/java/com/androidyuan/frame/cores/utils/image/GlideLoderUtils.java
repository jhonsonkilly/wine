package com.androidyuan.frame.cores.utils.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextUtils;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;


/**
 * Created by weizongwei on 15-11-24.
 */
public class GlideLoderUtils {

    public static void display(ImageView img, String url) {

        if (!TextUtils.isEmpty(url)) {
            Glide.with(img.getContext())
                    .load(url + "")
                    .into(img);
        }
    }

    public static void displayDrawId(ImageView img, int strid) {

        Glide.with(img.getContext())
                .load(strid)
                .into(img);
    }

    //由于glide默认使用了 fade动画 所以再listview加载的时候  不能执行这个动画
    public static void displayOnList(ImageView img, String url) {

        Glide.with(img.getContext())
                .load(url + "")
                .crossFade()
                .into(img);
    }


    //展示centerCrop
    public static void displayCenterCrop(ImageView img, String url) {

        if (!TextUtils.isEmpty(url)) {
            Glide.with(img.getContext())
                    .load(url + "")
                    .centerCrop()
                    .into(img);
        }
    }

    public static void displayRound(ImageView img, String url) {

        if (!TextUtils.isEmpty(url)) {
            RequestManager glideRequest;
            glideRequest = Glide.with(img.getContext());

            Context con = img.getContext();
            glideRequest.load(url).transform(
                    new GlideRoundTransform(img.getContext())
            ).into(img);
        }
    }


    //原型图片   必要的transformation
    public static class GlideRoundTransform extends BitmapTransformation {

        public GlideRoundTransform(Context context) {

            super(context);
        }

        @Override
        protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {

            return circleCrop(pool, toTransform);
        }

        private Bitmap circleCrop(BitmapPool pool, Bitmap source) {

            if (source == null) return null;

            int size = Math.min(source.getWidth(), source.getHeight());
            int x = (source.getWidth() - size) / 2;
            int y = (source.getHeight() - size) / 2;

            // TODO this could be acquired from the pool too
            Bitmap squared = Bitmap.createBitmap(source, x, y, size, size);

            Bitmap result = pool.get(size, size, Bitmap.Config.ARGB_8888);
            if (result == null) {
                result = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
            }

            Canvas canvas = new Canvas(result);
            Paint paint = new Paint();
            paint.setShader(new BitmapShader(squared, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
            paint.setAntiAlias(true);
            float r = size / 2f;
            canvas.drawCircle(r, r, r, paint);
            return result;
        }

        @Override
        public String getId() {

            return getClass().getName();
        }
    }
}
