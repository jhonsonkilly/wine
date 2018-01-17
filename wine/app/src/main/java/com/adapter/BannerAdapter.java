package com.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import com.activity.LoginActivity;
import com.activity.WebViewActivity;
import com.androidyuan.frame.cores.utils.SharedPreferencesUtil;
import com.model.BannerModel;
import com.model.MapWrapper;
import com.utils.Urls;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by mac on 2017/11/14.
 */

public class BannerAdapter extends PagerAdapter {

    private List<View> list = new ArrayList<>();

    public BannerAdapter(List<View> list) {
        this.list = list;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    public Object instantiateItem(ViewGroup container, int position) {



        if (list != null && list.size() != 0) {
            position = position % list.size();
        }
        ViewGroup parent = (ViewGroup) list.get(position).getParent();

        if (parent != null) {

            parent.removeAllViews();

        }
        container.addView(list.get(position));// 添加页卡
        return list.get(position);
    }
}
