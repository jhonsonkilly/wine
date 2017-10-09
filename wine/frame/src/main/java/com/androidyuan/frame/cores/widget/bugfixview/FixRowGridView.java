package com.androidyuan.frame.cores.widget.bugfixview;
/*
bugfixview 这个包里面的View专门用来修复android自身带的bug
*/


/**
 * Created by wei on 2013/9/3.
 */
/*
*
*FixRowGridView  修复 因为 ListView内嵌的时候 不能显示多行的问题
* */

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

public class FixRowGridView extends GridView {

    public FixRowGridView(Context context) {

        super(context);
    }

    public FixRowGridView(Context context, AttributeSet attrs) {

        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}