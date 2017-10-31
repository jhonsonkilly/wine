package com.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.model.LeftClassifyModel;
import com.model.RightClassifyModel;
import com.utils.BaseViewHolder;

import java.util.List;

import zjw.wine.R;

/**
 * Created by mac on 2017/10/31.
 */

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.Holder> {

    List<RightClassifyModel.Data> datalist;

    public SubCategoryAdapter(List<RightClassifyModel.Data> list) {

        this.datalist = list;
    }
    @Override
    public SubCategoryAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SubCategoryAdapter.Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_left_list, parent, false));
    }

    @Override
    public void onBindViewHolder(SubCategoryAdapter.Holder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return datalist == null ? 0 : datalist.size();
    }

    class Holder extends RecyclerView.ViewHolder {


        TextView category_name;

        RelativeLayout rel;

        public Holder(View convertView) {

            super(convertView);
            category_name = BaseViewHolder.get(convertView, R.id.category_name);
            rel=BaseViewHolder.get(convertView,R.id.root);


        }
    }
}