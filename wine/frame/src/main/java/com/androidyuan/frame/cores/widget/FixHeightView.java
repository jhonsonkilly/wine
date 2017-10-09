package com.androidyuan.frame.cores.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by wei on 2015/6/29.
 */
public class FixHeightView extends LinearLayout {

    //宽高比   宽度/高度
    private static final double WH_RATIO = 2.092;

    public FixHeightView(Context context) {

        super(context);
    }

    public FixHeightView(Context context, AttributeSet attrs) {

        super(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public FixHeightView(Context context, AttributeSet attrs, int defStyle) {

        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        // 父容器传过来的宽度方向上的模式
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        // 父容器传过来的高度方向上的模式
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        // 父容器传过来的宽度的值
        int width = MeasureSpec.getSize(widthMeasureSpec) - getPaddingLeft()
                - getPaddingRight();
        // 父容器传过来的高度的值
        int height = MeasureSpec.getSize(heightMeasureSpec) - getPaddingBottom()
                - getPaddingTop();

        height = (int) (width / WH_RATIO + 0.5f);
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(
                height,
                MeasureSpec.EXACTLY
        );

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

}
