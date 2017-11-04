package com.adapter;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.androidyuan.frame.cores.utils.image.FrescoUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.model.RightClassifyModel;

import java.util.ArrayList;
import java.util.List;

import zjw.wine.R;

/**
 * Created by mac on 2017/11/4.
 */

public class ProductAdapter extends BaseAdapter {

    List<RightClassifyModel.Data.Products> list = new ArrayList<>();
    Context context;

    SimpleDraweeView img;

    TextView textView;

    public ProductAdapter(Context context, List<RightClassifyModel.Data.Products> list) {
        this.list = list;
        this.context = context;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        try {
            if (convertView == null) {
                convertView = View.inflate(context, R.layout.item_gridview, null);
            }

            img = convertView.findViewById(R.id.img_1);
            textView = (TextView) convertView.findViewById(R.id.text_1);

            FrescoUtils.displayUrl(img, list.get(position).image);
            textView.setText(list.get(position).name);
            return convertView;

        } catch (Exception e) {
            return null;
        }


    }
}
