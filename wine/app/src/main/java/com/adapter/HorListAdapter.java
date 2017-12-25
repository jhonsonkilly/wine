package com.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidyuan.frame.cores.utils.image.FrescoUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.model.HorlistModel;
import com.utils.BaseViewHolder;
import com.utils.Urls;

import java.util.List;

import zjw.wine.R;

/**
 * Created by mac on 2017/11/16.
 */

public class HorListAdapter extends RecyclerView.Adapter<HorListAdapter.Holder> {


    Context context;
    List<HorlistModel.HorData> datalist;

    OnfenleiClickListener onfenleiClickListener;


    public HorListAdapter(Context context, List<HorlistModel.HorData> list) {
        this.datalist = list;

        this.context = context;

    }

    @Override
    public HorListAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HorListAdapter.Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hor_list, parent, false));
    }

    @Override
    public void onBindViewHolder(final HorListAdapter.Holder holder, final int position) {
        try {
            holder.hor_text.setText(datalist.get(position).name.toString());
            FrescoUtils.displayUrl(holder.hor_img, Urls.getBaseUrl() + "/em/es_class/" + datalist.get(position).pic);
            holder.hor_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onfenleiClickListener != null) {
                        onfenleiClickListener.jump(datalist.get(position).guid, position);
                    }
                }
            });
        } catch (Exception e) {

        }

    }

    @Override
    public int getItemCount() {
        return datalist == null ? 0 : datalist.size();
    }

    public void setOnfenleiClickListener(OnfenleiClickListener onfenleiClickListener) {
        this.onfenleiClickListener = onfenleiClickListener;
    }

    public interface OnfenleiClickListener {


        void jump(String id, int pos);


    }

    class Holder extends RecyclerView.ViewHolder {


        SimpleDraweeView hor_img;

        TextView hor_text;

        public Holder(View convertView) {

            super(convertView);
            hor_img = BaseViewHolder.get(convertView, R.id.hor_img);
            hor_text = BaseViewHolder.get(convertView, R.id.hor_text);
        }
    }
}
