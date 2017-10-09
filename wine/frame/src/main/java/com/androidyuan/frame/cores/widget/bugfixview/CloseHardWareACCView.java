package com.androidyuan.frame.cores.widget.bugfixview;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by wei on 2014/12/30.
 * 关闭硬件加速的View  关闭硬件加速之后就可以画虚线了
 * 这个view暂时用处只是用于画虚线
 */
public class CloseHardWareACCView extends View {

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public CloseHardWareACCView(Context context, AttributeSet attrs) {

        super(context, attrs);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public CloseHardWareACCView(Context context) {

        super(context);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public CloseHardWareACCView(Context context, AttributeSet attrs, int defStyleAttr) {

        super(context, attrs, defStyleAttr);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    }
}
