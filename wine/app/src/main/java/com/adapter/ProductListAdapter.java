package com.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.Event.AddToCartEvent;
import com.androidyuan.frame.cores.utils.image.FrescoUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.model.ProductModel;
import com.otto.OttoBus;

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

    ImageView addImg;



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
    public View getView(final int position, View convertView, ViewGroup parent) {
        try {
            if (convertView == null) {
                convertView = View.inflate(context, R.layout.item_list, null);
            }

            img = (SimpleDraweeView) convertView.findViewById(R.id.img_banner);
            textView = (TextView) convertView.findViewById(R.id.text_1);
            textPrice = (TextView)convertView.findViewById(R.id.price);
            saleText = (TextView)convertView.findViewById(R.id.sale);
            addImg = (ImageView) convertView.findViewById(R.id.cart);
            addImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    AddToCartEvent event = new AddToCartEvent();
                    event.id = list.get(position).guid;
                    OttoBus.getInstance().post(event);
                }
            });

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
