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
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(list.get(position), 0);// 添加页卡


        return list.get(position);
    }
}
