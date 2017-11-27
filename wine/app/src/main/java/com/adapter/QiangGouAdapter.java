package com.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidyuan.frame.cores.utils.image.FrescoUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.model.QiangGouModel;
import com.utils.BaseViewHolder;

import java.util.List;

import zjw.wine.R;

/**
 * <p>Copyright:Copyright(c) 2016</p>
 * <p>Company:上海来伊份电子商务有限公司</p>
 * <p>包名:com.adapter</p>
 * <p>文件名:wine</p>
 * <p>类更新历史信息</p>
 *
 * @todo <a href="mailto:zhoujiawei@laiyifen.com">vernal(周佳伟)</a>
 */
public class QiangGouAdapter extends RecyclerView.Adapter<QiangGouAdapter.Holder> {


    Context context;
    List<QiangGouModel.QiangGouData> datalist;


    public QiangGouAdapter(Context context, List<QiangGouModel.QiangGouData> list) {
        this.datalist = list;

        this.context = context;

    }

    @Override
    public QiangGouAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new QiangGouAdapter.Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_qianggou_list, parent, false));
    }

    @Override
    public void onBindViewHolder(QiangGouAdapter.Holder holder, int position) {
        try {
            holder.price.setText("￥ " + datalist.get(position).goods.price);
            holder.title.setText(datalist.get(position).goods.name);
            FrescoUtils.displayUrl(holder.hor_img, datalist.get(position).goods.image);
        } catch (Exception e) {

        }

    }

    @Override
    public int getItemCount() {
        return datalist == null ? 0 : datalist.size();
    }

    class Holder extends RecyclerView.ViewHolder {


        SimpleDraweeView hor_img;

        TextView hor_text;

        TextView title;

        TextView counttime;

        ImageView imageView;

        TextView price;

        public Holder(View convertView) {

            super(convertView);
            hor_img = BaseViewHolder.get(convertView, R.id.img);
            title = BaseViewHolder.get(convertView, R.id.title);
            counttime = BaseViewHolder.get(convertView, R.id.counttime);
            price = BaseViewHolder.get(convertView, R.id.price);
            imageView = BaseViewHolder.get(convertView, R.id.cart);


        }
    }
}
