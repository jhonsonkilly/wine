package com.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.androidyuan.frame.cores.utils.image.FrescoUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.model.JingXuanModel;
import com.model.ProductModel;

import java.util.List;

import zjw.wine.R;

/**
 * Created by mac on 2017/11/22.
 */

public class ProductListAdapter extends BaseAdapter {
    Context context;
    List<ProductModel.Result> list;

    SimpleDraweeView img;

    TextView textPrice;

    TextView textView;
    TextView saleText;

    public ProductListAdapter(Context context, List<ProductModel.Result> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
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
                convertView = View.inflate(context, R.layout.item_list, null);
            }

            img = convertView.findViewById(R.id.img_banner);
            textView = convertView.findViewById(R.id.text_1);
            textPrice = convertView.findViewById(R.id.price);
            saleText = convertView.findViewById(R.id.sale);

            FrescoUtils.displayUrl(img, list.get(position).image);
            textView.setText(list.get(position).name);
            textPrice.setText("￥ " + list.get(position).price);
            saleText.setText("销量:  " + list.get(position).salenum);


            return convertView;

        } catch (Exception e) {
            return null;
        }
    }
}
