package com.androidyuan.frame.base.adapter;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by weizongwei on 14-8-27.
 */
public interface IAdapterInterFace<T> {

    View getView(int i, View view, ViewGroup viewGroup);
}
