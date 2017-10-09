package com.androidyuan.frame.cores.widget;
/**
 * Created by wei on 2013/9/12.
 */
/*
*
*FixRowGridView  修复子item高度不同而导致的部分item没有被撑开的问题
* */

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.GridView;

public class FixChildHeightGridView extends GridView {


    private int mHeight = 0;

    public FixChildHeightGridView(Context context) {

        super(context);
    }

    public FixChildHeightGridView(Context context, AttributeSet attrs) {

        super(context, attrs);
    }

    public void setmHeight(int i_hei) {

        mHeight = i_hei;
    }

    //恢复默认  等待进行重新计算
    public void revertDefault() {

        mHeight = 0;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //在这里进行重新计算 做了判断 避免重复的多余的计算
        if (mHeight == 0 && getChildCount() > 0) {
            fixChildHei();
        }


        int expandSpec;
        if (mHeight > 0) {
            expandSpec = MeasureSpec.makeMeasureSpec(
                    mHeight,
                    MeasureSpec.EXACTLY
            );
        }
        else {
            expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        }
        super.onMeasure(widthMeasureSpec, expandSpec);
    }


    private void fixChildHei() {

        final int PRE_GRIDVIEW_HEI = getHeight();

        final int CHILD_COUNT = getChildCount();
        if (CHILD_COUNT > 0) {
            final int NUMCOLUMN = getNumColumns();
            for (int i_index = 0; i_index < CHILD_COUNT / NUMCOLUMN; i_index++) {
                int t_heimax = 0;
                //先取得maxhei
                for (int i_colum = 0; i_colum < NUMCOLUMN; i_colum++) {
                    if (getChildAt(i_index * NUMCOLUMN + i_colum).getMeasuredHeight() > t_heimax) {
                        t_heimax = getChildAt(i_index * NUMCOLUMN + i_colum).getMeasuredHeight();

                    }
                }

                final int MAXHEI = t_heimax;
                //再修正height有问题的 view
                for (int i_colum = 0; i_colum < NUMCOLUMN; i_colum++) {
                    if (getChildAt(i_index * NUMCOLUMN + i_colum).getMeasuredHeight() != MAXHEI) {
                        ViewGroup.LayoutParams layoutParams = getChildAt(i_index * NUMCOLUMN + i_colum).getLayoutParams();
                        layoutParams.height = MAXHEI;
                        getChildAt(i_index * NUMCOLUMN + i_colum).setLayoutParams(layoutParams);
                    }
                }

            }


            //求得gridview自身的高度
            int thishei = getChildAt(0).getMeasuredHeight() * CHILD_COUNT / NUMCOLUMN +
                    getPaddingBottom() +
                    getPaddingTop();
            //计算VerticalSpacing
            if (Build.VERSION.SDK_INT >= 16) {
                thishei += (CHILD_COUNT / NUMCOLUMN - 1) * getVerticalSpacing();
            }


            if (PRE_GRIDVIEW_HEI > thishei) {
                setmHeight(thishei);
                Log.d(getClass().getSimpleName(), "fixCHildHei");
            }

        }
    }
}