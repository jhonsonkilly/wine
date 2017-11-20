package com.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidyuan.frame.cores.utils.image.FrescoUtils;
import com.androidyuan.frame.cores.widget.FixChildHeightGridView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.model.JingXuanModel;
import com.model.RightClassifyModel;
import com.utils.BaseViewHolder;

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
    public void onBindViewHolder(Holder holder, int position) {
        try {
            holder.hor_text.setText(list.get(position).name.toString());
            holder.hor_price.setText("￥" + list.get(position).price.toString());
            FrescoUtils.displayUrl(holder.hor_img, list.get(position).image);
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

        public Holder(View convertView) {

            super(convertView);
            hor_img = BaseViewHolder.get(convertView, R.id.jingxuan_img);
            hor_text = BaseViewHolder.get(convertView, R.id.jingxuan_text);
            hor_price = BaseViewHolder.get(convertView, R.id.jingxuan_price);


        }
    }
}
