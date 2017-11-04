package com.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.model.LeftClassifyModel;
import com.utils.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import zjw.wine.R;

/**
 * Created by mac on 2017/10/29.
 */

public class ParentCategoryAdapter extends RecyclerView.Adapter<ParentCategoryAdapter.Holder> {


    List<LeftClassifyModel.Data> datalist;

    OnLeftClickListener onLeftClickListener;

    Context context;

    private List<Boolean> isClicks;


    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_left_list, parent, false));
    }

    @Override
    public void onBindViewHolder(final Holder holder, final int position) {
        holder.setIsRecyclable(false);
        holder.category_name.setText(datalist.get(position).name.toString());



        for (int i = 0; i < isClicks.size(); i++) {
            if (isClicks.get(i)) {
                onLeftClickListener.leftClick(datalist.get(i).guid);
            }
        }

        if (isClicks.get(position)) {
            holder.category_name.setTextColor(context.getResources().getColor(R.color.theme_color));
            holder.rel.setBackgroundColor(context.getResources().getColor(R.color.white));
        } else {
            holder.category_name.setTextColor(context.getResources().getColor(R.color.main_title_color));
            holder.rel.setBackgroundColor(context.getResources().getColor(R.color.background_color));
        }


        holder.rel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onLeftClickListener != null) {
                    onLeftClickListener.leftClick(datalist.get(position).guid);

                    for (int i = 0; i < isClicks.size(); i++) {
                        isClicks.set(i, false);
                    }
                    isClicks.set(position, true);
                    notifyDataSetChanged();
                }

            }
        });
    }


    @Override
    public int getItemCount() {
        return datalist == null ? 0 : datalist.size();
    }

    public ParentCategoryAdapter(List<LeftClassifyModel.Data> list, Context context) {

        this.datalist = list;

        this.context = context;

        isClicks = new ArrayList<>();
        for (int i = 0; i < datalist.size(); i++) {
            if (i == 0) {
                isClicks.add(true);
            } else {
                isClicks.add(false);
            }

        }

    }

    class Holder extends RecyclerView.ViewHolder {


        TextView category_name;

        RelativeLayout rel;

        public Holder(View convertView) {

            super(convertView);
            category_name = BaseViewHolder.get(convertView, R.id.category_name);
            rel = BaseViewHolder.get(convertView, R.id.root);


        }
    }

    public interface OnLeftClickListener {


        void leftClick(String id);


    }


    public void setOnLeftClickListener(OnLeftClickListener onLeftClickListener) {
        this.onLeftClickListener = onLeftClickListener;
    }
}
