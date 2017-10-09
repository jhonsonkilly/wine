package com.androidyuan.frame.base.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;
import java.util.Objects;

/**
 * Created by weizongwei on 15-8-27.
 */
public class BasePresenterAdapter extends BaseAdapter {

    Context context;
    List<Objects> datalist;

    public BasePresenterAdapter(Context context, List<Objects> list) {

        this.context = context;
        datalist = list;
    }


    @Override
    public int getCount() {

        return datalist == null ? 0 : datalist.size();
    }

    @Override
    public Object getItem(int i) {

        return datalist.get(i);
    }

    @Override
    public long getItemId(int i) {

        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        return null;
    }
}
