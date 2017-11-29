package com.adapter;

import android.content.Context;
import android.os.CountDownTimer;
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

            timer = new CountDownTimer(countTime, 1000) {

                public void onTick(final long millisUntilFinished) {

                    time = millisUntilFinished;

                    // holder12.tx_count.setText(getTimeShort(millisUntilFinished / 1000));
                    holder12.tx_count_ll1.setText(getTimeShort(millisUntilFinished / 1000).split(":")[0]);
                    holder12.tx_count_ll2.setText(getTimeShort(millisUntilFinished / 1000).split(":")[1]);
                    holder12.tx_count_ll3.setText(getTimeShort(millisUntilFinished / 1000).split(":")[2]);



                }

                public void onFinish() {
                    holder12.tx_count_ll1.setText(getTimeShort(0).split(":")[0]);
                    holder12.tx_count_ll2.setText(getTimeShort(0).split(":")[0]);
                    holder12.tx_count_ll3.setText(getTimeShort(0).split(":")[0]);
                    timer = null;
                    time = 1;
                    holder12.tx_count_ll1.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (onRetryListener != null) {
                                onRetryListener.retryCountTime();
                            }
                        }
                    }, 200);

                }
            };
            timer.start();

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

    public String getTimeShort(long i) {

        long h = i / 3600;
        long m = (i % 3600) / 60;
        long s = (i % 3600) % 60;


        String dateString = "0" + h + ":" + m + ":" + s;

        if (m < 10) {
            dateString = "0" + h + ":" + "0" + m + ":" + s;
        }
        if (s < 10) {
            dateString = "0" + h + ":" + m + ":" + "0" + s;
        }
        if (m < 10 && s < 10) {

            dateString = "0" + h + ":" + "0" + m + ":" + "0" + s;
        }

        return dateString;
    }
}
