package com.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.model.LeftClassifyModel;
import com.utils.BaseViewHolder;

import java.util.List;

import zjw.wine.R;

/**
 * Created by mac on 2017/10/29.
 */

public class ParentCategoryAdapter extends RecyclerView.Adapter<ParentCategoryAdapter.Holder> {



    List<LeftClassifyModel.Data> datalist;

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_left_list, parent, false));
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.category_name.setText(datalist.get(position).name.toString());
    }



    @Override
    public int getItemCount() {
        return datalist == null ? 0 : datalist.size();
    }

    public ParentCategoryAdapter(List<LeftClassifyModel.Data> list) {

        this.datalist = list;
    }

    class Holder extends RecyclerView.ViewHolder {


        TextView category_name;



        public Holder(View convertView) {

            super(convertView);
            category_name = BaseViewHolder.get(convertView, R.id.category_name);

        }
    }
}
