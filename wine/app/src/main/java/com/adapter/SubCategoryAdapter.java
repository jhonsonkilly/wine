package com.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidyuan.frame.cores.widget.FixChildHeightGridView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.model.RightClassifyModel;
import com.utils.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import zjw.wine.R;

/**
 * Created by mac on 2017/10/31.
 */

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.Holder> {

    List<RightClassifyModel.Data> datalist;

    List<RightClassifyModel.Data.Products> list=new ArrayList<>();
    Context context;

    public SubCategoryAdapter(List<RightClassifyModel.Data> list, Context context) {

        this.datalist = list;

        this.context = context;
    }

    @Override
    public SubCategoryAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SubCategoryAdapter.Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_right_list, parent, false));
    }

    @Override
    public void onBindViewHolder(SubCategoryAdapter.Holder holder, int position) {
        try {
            //FrescoUtils.displayUrl(holder.img_1, "http://cdn.oudianyun.com/1485064331959_35.97432246863733_7eef16b3-ab2a-4274-866c-f59b5de83f22.jpg");
            holder.mTextView.setText(datalist.get(position).name);
            ProductAdapter adapter = new ProductAdapter(context, datalist.get(position).products);
            holder.mFixChildHeightGridView.setAdapter(adapter);

        } catch (Exception e) {

        }

    }

    @Override
    public int getItemCount() {
        return datalist == null ? 0 : datalist.size();
    }

    class Holder extends RecyclerView.ViewHolder {


        FixChildHeightGridView mFixChildHeightGridView;

        TextView mTextView;
        private final SimpleDraweeView img_1;

        public Holder(View convertView) {

            super(convertView);
            mFixChildHeightGridView = BaseViewHolder.get(convertView, R.id.gridview);
            mTextView = BaseViewHolder.get(convertView, R.id.textview_1);
            img_1 = BaseViewHolder.get(convertView, R.id.img_1);


        }
    }
}
