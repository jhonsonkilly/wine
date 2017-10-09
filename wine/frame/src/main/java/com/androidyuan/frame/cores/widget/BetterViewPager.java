package com.androidyuan.frame.cores.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

/**
 * Created by weizongwei on 15-10-9.
 */
public class BetterViewPager extends ViewPager {

    protected OnPageChangeListener listener;

    public BetterViewPager(Context context) {

        super(context);
    }


    public BetterViewPager(Context context, AttributeSet attrs) {

        super(context, attrs);
    }

    @Override
    public void setCurrentItem(int item) {

        boolean invokeMeLater = false;

        if (super.getCurrentItem() == 0 && item == 0)
            invokeMeLater = true;

        super.setCurrentItem(item);

        if (invokeMeLater && listener != null)
            listener.onPageSelected(item);
    }
}
