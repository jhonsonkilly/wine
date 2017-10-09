package com.androidyuan.frame.cores.widget.bugfixview;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;

/**
 * Created by wei on 2014/12/30.
 * 修复在ScrollView中ViewPager滚动反弹问题,HorizontalListView滚动不顺畅
 */
public class FixSlowHorizontalScrollView extends HorizontalScrollView {

    OnTouchListener mGestureListener;
    private GestureDetector mGestureDetector;

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @SuppressWarnings("deprecation")
    public FixSlowHorizontalScrollView(Context context, AttributeSet attrs) {

        super(context, attrs);
        mGestureDetector = new GestureDetector(new YScrollDetector());
        setFadingEdgeLength(0);
        //setOverScrollMode(OVER_SCROLL_NEVER);
    }

    //解决滚动问题
    @Override
    protected int computeScrollDeltaToGetChildRectOnScreen(Rect rect) {

        return 0;
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        return super.onInterceptTouchEvent(ev) && mGestureDetector.onTouchEvent(ev);
    }


    class YScrollDetector extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {

            return Math.abs(distanceY) > Math.abs(distanceX);
        }
    }
}
