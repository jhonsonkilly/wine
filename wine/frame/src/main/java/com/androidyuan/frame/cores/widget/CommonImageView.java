package com.androidyuan.frame.cores.widget;

import android.content.Context;
import android.util.AttributeSet;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by wei on 17-6-13.
 * <p>
 * 通用ImageView
 */

public class CommonImageView extends SimpleDraweeView {


    public CommonImageView(Context context, GenericDraweeHierarchy hierarchy) {

        super(context, hierarchy);
    }

    public CommonImageView(Context context) {

        super(context);
    }

    public CommonImageView(Context context, AttributeSet attrs) {

        super(context, attrs);
    }

    public CommonImageView(Context context, AttributeSet attrs, int defStyle) {

        super(context, attrs, defStyle);
    }

    public CommonImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {

        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
