package com.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.Event.AddToCartEvent;
import com.activity.LoginActivity;
import com.activity.WebViewActivity;
import com.androidyuan.frame.cores.utils.SharedPreferencesUtil;
import com.androidyuan.frame.cores.utils.image.FrescoUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.model.JingXuanModel;
import com.otto.OttoBus;
import com.utils.BaseViewHolder;
import com.utils.Urls;

import java.util.HashMap;
import java.util.List;

import zjw.wine.R;

/**
 * Created by mac on 2017/11/20.
 */

public class JingXuanItemAdapter extends RecyclerView.Adapter<JingXuanItemAdapter.Holder> {

    List<JingXuanModel.Data.Goods> list;

    Context context;



    public JingXuanItemAdapter(List<JingXuanModel.Data.Goods> list, Context context) {

        this.list = list;

        this.context = context;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new JingXuanItemAdapter.Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_jingxuan_list, parent, false));
    }

    @Override
    public void onBindViewHolder(Holder holder, final int position) {
        try {
            holder.hor_text.setText(list.get(position).name.toString());
            holder.hor_price.setText("￥" + list.get(position).price.toString());
            FrescoUtils.displayUrl(holder.hor_img, Urls.getBaseUrl()+"/em/es_pro/"+list.get(position).image);
            holder.add_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    AddToCartEvent event = new AddToCartEvent();
                    event.id=list.get(position).guid;
                    OttoBus.getInstance().post(event);
                }
            });

            holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!TextUtils.isEmpty(SharedPreferencesUtil.getStringData(context, "ut", ""))) {
                        Intent intent = new Intent(context, WebViewActivity.class);
                        HashMap<String, String> map = new HashMap<>();
                        map.put("productGuid", list.get(position).proGuid);
                        map.put("cost", list.get(position).price);
                        map.put("goodGuid", list.get(position).guid);
                        intent.putExtra("parms", map);
                        intent.putExtra("url", Urls.getBaseUrl() + "/eshop/commodity/commodity.html");
                        context.startActivity(intent);
                    } else {
                        context.startActivity(new Intent(context, LoginActivity.class));

                    }
                }
            });


        } catch (Exception e) {

        }
    }


    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }


    class Holder extends RecyclerView.ViewHolder {

        SimpleDraweeView hor_img;

        TextView hor_text;

        TextView hor_price;

        ImageView add_img;

        RelativeLayout relativeLayout;

        public Holder(View convertView) {

            super(convertView);
            hor_img = BaseViewHolder.get(convertView, R.id.jingxuan_img);
            hor_text = BaseViewHolder.get(convertView, R.id.jingxuan_text);
            hor_price = BaseViewHolder.get(convertView, R.id.jingxuan_price);
            add_img = BaseViewHolder.get(convertView, R.id.add_img);
            relativeLayout=BaseViewHolder.get(convertView,R.id.lin_ll);


        }
    }


}
