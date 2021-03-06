package com.androidyuan.frame.cores.widget.animationview;

import android.animation.LayoutTransition;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * 带动画的view  调用AddView时触发动画
 */
public class AnimLinearLayout extends LinearLayout {

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public AnimLinearLayout(Context context, AttributeSet attrs) {

        super(context, attrs);
        LayoutTransition transition = new LayoutTransition();
        this.setLayoutTransition(transition);
    }

}