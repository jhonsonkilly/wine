package com.adapter;


import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.activity.LoginActivity;
import com.activity.WebViewActivity;
import com.androidyuan.frame.cores.utils.SharedPreferencesUtil;
import com.androidyuan.frame.cores.utils.image.FrescoUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.model.MapWrapper;
import com.model.RightClassifyModel;
import com.utils.Urls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
        if (list != null) {
            return list.size();
        } else {
            return 0;
        }

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
    public View getView(final int position, View convertView, ViewGroup parent) {
        try {
            if (convertView == null) {
                convertView = View.inflate(context, R.layout.item_gridview, null);
            }

            img = (SimpleDraweeView) convertView.findViewById(R.id.img_1);
            textView = (TextView) convertView.findViewById(R.id.text_1);

            FrescoUtils.displayUrl(img, Urls.getBaseUrl() + "/em/es_class/" + list.get(position).pic);
            textView.setText(list.get(position).name);
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!TextUtils.isEmpty(SharedPreferencesUtil.getStringData(context, "ut", ""))) {
                        Intent intent = new Intent(context, WebViewActivity.class);
                        LinkedHashMap<String, String> map = new LinkedHashMap<>();

                        map.put("productGuid", list.get(position).guid);

                        map.put("productName", "noParam");

                        intent.putExtra("objetParms", new MapWrapper().setMap(map));
                        intent.putExtra("url", Urls.getBaseUrl() + "/eshop/classification/neiye.html");
                        context.startActivity(intent);
                    } else {
                        context.startActivity(new Intent(context, LoginActivity.class));
                    }
                }
            });
            return convertView;

        } catch (Exception e) {
            return null;
        }


    }
}
