package com.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.utils.UiUtils;

import java.util.ArrayList;
import java.util.List;

import zjw.wine.R;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * Created by meijunqiang on 2017/6/22.
 * 描述:商家店铺首页
 */
public class CommonTabChooser extends LinearLayout {
    private int mMaxTabWidth;
    private int mSelectedTabIndex;
    private LinearLayout mTabLayout;
    private List<TabChooserBean> tabList = new ArrayList<>();
    private final String EMPTY_TITLE = "";
    private int colorId;
    private int topViewSize;

    public void setStatus(int mSelectedTabIndex){
        this.mSelectedTabIndex = mSelectedTabIndex;
    }

    public CommonTabChooser(Context context) {
        super(context);
        initView();
    }

    public CommonTabChooser(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public void setTabList(List<TabChooserBean> tabList) {
        this.tabList = tabList;
        notifyDataSetChanged();
    }

    public void setTabList(List<TabChooserBean> tabList, int topViewSize) {
        this.tabList = tabList;
        this.topViewSize = topViewSize;
        notifyDataSetChanged();
    }

    public void initView() {
        mTabLayout = new LinearLayout(getContext());
        addView(mTabLayout, new ViewGroup.LayoutParams(WRAP_CONTENT, MATCH_PARENT));
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }

    public void setBackGround(int color) {
        mTabLayout.setBackgroundColor(color);
    }

    private void addTab(CharSequence text, int index) {
        TabLayout layout = new TabLayout(getContext());
        layout.index = index;
        if (!TextUtils.isEmpty(tabList.get(index).tabTitle)) {
            layout.tv_tab_title.setText(tabList.get(index).tabTitle);
            layout.tv_tab_title.setVisibility(View.VISIBLE);
            layout.iv_tab.setVisibility(View.GONE);
        } else {
            layout.iv_tab.setImageResource(tabList.get(index).imagesrc);
            layout.tv_tab_title.setVisibility(View.GONE);
            layout.iv_tab.setVisibility(View.VISIBLE);
        }
        layout.tv_tab.setText(tabList.get(index).tabcontent);
        layout.tv_tab.setGravity(Gravity.CENTER);
        layout.setOnClickListener(mTabClickListener);
        LayoutParams params = new LayoutParams(0, LayoutParams.MATCH_PARENT, 1);
        mTabLayout.addView(layout, params);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        final boolean lockedExpanded = widthMode == MeasureSpec.EXACTLY;
        final int childCount = mTabLayout.getChildCount();
        if (childCount > 1 && (widthMode == MeasureSpec.EXACTLY || widthMode == MeasureSpec.AT_MOST)) {
            if (childCount > 2) {
                mMaxTabWidth = (int) (MeasureSpec.getSize(widthMeasureSpec) * 0.4f);
            } else {
                mMaxTabWidth = MeasureSpec.getSize(widthMeasureSpec) / 2;
            }
        } else {
            mMaxTabWidth = -1;
        }

        final int oldWidth = getMeasuredWidth();
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        final int newWidth = getMeasuredWidth();

        if (lockedExpanded && oldWidth != newWidth) {
            setCurrentItem(mSelectedTabIndex);
        }
    }

    public void showDot(int index, boolean show, int count) {
        TabLayout layout = (TabLayout) mTabLayout.getChildAt(index);
        layout.tv_msg.setVisibility(show ? View.VISIBLE : View.GONE);
        UiUtils.setCareNum(count, 0, layout.tv_msg);
    }

    public void setOneTab(int index, String title){
        TabLayout layout = (TabLayout) mTabLayout.getChildAt(index);
        layout.tv_tab_title.setText(title);
    }

    private class TabLayout extends RelativeLayout {

        private int index;
        public TextView tv_tab;
        public ImageView iv_tab;
        public TextView tv_msg;
        public TextView tv_tab_title;

        public TabLayout(Context context) {
            super(context);
            initView(context);
        }

        public int getIndex() {
            return index;
        }

        void initView(Context context) {
            RelativeLayout layout = (RelativeLayout) LayoutInflater.from(context).inflate(R.layout.common_tab_layout, null);
            tv_tab = (TextView) layout.findViewById(R.id.tv_tab);
            tv_tab_title = (TextView) layout.findViewById(R.id.tv_tab_title);
            iv_tab = (ImageView) layout.findViewById(R.id.iv_tab);
            if (topViewSize>0) {
                ViewGroup.LayoutParams layoutParams = tv_tab_title.getLayoutParams();
                layoutParams.height = topViewSize;
                ViewGroup.LayoutParams ivLayoutParams = iv_tab.getLayoutParams();
                ivLayoutParams.height = topViewSize;
//                tv_tab_title.setLayoutParams(layoutParams);
//                iv_tab.setLayoutParams(ivLayoutParams);
            }
            tv_msg = (TextView) layout.findViewById(R.id.tv_msg);
            if (colorId != 0) {
                tv_tab.setTextColor(getResources().getColorStateList(colorId));
            }
            addView(layout);
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            if (mMaxTabWidth > 0 && getMeasuredWidth() > mMaxTabWidth) {
                super.onMeasure(MeasureSpec.makeMeasureSpec(mMaxTabWidth, MeasureSpec.EXACTLY),
                        heightMeasureSpec);
            }
        }
    }

    private final OnClickListener mTabClickListener = new OnClickListener() {
        public void onClick(View view) {
            TabLayout tabLayout = (TabLayout) view;
            if (tabLayout.getIndex() != mSelectedTabIndex) {
                mSelectedTabIndex = tabLayout.getIndex();
                setCurrentItem(mSelectedTabIndex);
                if (listener != null) {
                    listener.select(mSelectedTabIndex);
                }
            }
        }
    };

    public void setCurrentItem(int position) {
        final int tabCount = mTabLayout.getChildCount();
        mSelectedTabIndex = position;
        for (int i = 0; i < tabCount; i++) {
            final View child = mTabLayout.getChildAt(i);
            final boolean isSelected = (i == position);
            child.setSelected(isSelected);
        }
    }

    public void notifyDataSetChanged() {
        final int count = tabList.size();
        for (int i = 0; i < count; i++) {
            CharSequence title = tabList.get(i).tabcontent;
            if (title == null) {
                title = EMPTY_TITLE;
            }
            addTab(title, i);
        }
        if (mSelectedTabIndex > count) {
            mSelectedTabIndex = count - 1;
        }
        setCurrentItem(mSelectedTabIndex);
        requestLayout();
    }

    private TabSelectListener listener;

    public void setTabSelectListener(TabSelectListener listener) {
        this.listener = listener;
    }


}
