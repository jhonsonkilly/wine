package com.androidyuan.frame.cores.widget.bugfixview;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by wei on 2014/12/30.
 * <p>
 * 解决ViewPager在ScrollView下的 滚动反弹问题
 */
public class FixReBackViewPager extends ViewPager {

    private float xDistance, yDistance, xLast, yLast;

    public FixReBackViewPager(Context context) {

        super(context);
    }

    public FixReBackViewPager(Context context, AttributeSet attrs) {

        super(context, attrs);
    }

    /**
     * 以下这一段是 viewpager滑动有反弹卡顿 所以加这个就不会这样了
     */

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                xDistance = yDistance = 0f;
                xLast = ev.getX();
                yLast = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                final float curX = ev.getX();
                final float curY = ev.getY();

                xDistance += Math.abs(curX - xLast);
                yDistance += Math.abs(curY - yLast);
                xLast = curX;
                yLast = curY;

                if (xDistance > yDistance) {
                    return false;
                }
        }

        return super.onInterceptTouchEvent(ev);
    }
}
