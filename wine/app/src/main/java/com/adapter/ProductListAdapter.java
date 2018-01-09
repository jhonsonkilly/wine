package com.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.Event.AddToCartEvent;
import com.activity.LoginActivity;
import com.activity.WebViewActivity;
import com.androidyuan.frame.cores.utils.SharedPreferencesUtil;
import com.androidyuan.frame.cores.utils.image.FrescoUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.model.MapWrapper;
import com.model.ProductModel;
import com.otto.OttoBus;
import com.utils.Urls;

import java.util.LinkedHashMap;
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
    private RelativeLayout pro_rl;


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
            textPrice = (TextView) convertView.findViewById(R.id.price);
            saleText = (TextView) convertView.findViewById(R.id.sale);
            addImg = (ImageView) convertView.findViewById(R.id.cart);
            pro_rl = convertView.findViewById(R.id.pro_rl);
            addImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    AddToCartEvent event = new AddToCartEvent();
                    event.id = list.get(position).guid;
                    OttoBus.getInstance().post(event);
                }
            });

            FrescoUtils.displayUrl(img, Urls.getBaseUrl() + "/em/es_pro/" + list.get(position).image);
            textView.setText(list.get(position).name);
            textPrice.setText("￥ " + list.get(position).price);
            if (TextUtils.isEmpty(list.get(position).salenum)) {
                saleText.setText("销量:  " + 0);
            } else {
                saleText.setText("销量:  " + list.get(position).salenum);
            }

            pro_rl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!TextUtils.isEmpty(SharedPreferencesUtil.getStringData(context, "ut", ""))) {
                        Intent intent = new Intent(context, WebViewActivity.class);
                        LinkedHashMap<String, String> map = new LinkedHashMap<>();
                        map.put("productGuid", list.get(position).proGuid);
                        map.put("name", list.get(position).name);
                        map.put("cost", list.get(position).price);
                        map.put("goodGuid", list.get(position).guid);
                        intent.putExtra("objetParms", new MapWrapper().setMap(map));
                        intent.putExtra("url", Urls.getBaseUrl() + "/eshop/commodity/commodity.html");
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
