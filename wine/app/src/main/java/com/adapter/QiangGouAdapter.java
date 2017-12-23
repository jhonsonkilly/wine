package com.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
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
import com.model.QiangGouModel;
import com.otto.OttoBus;
import com.utils.BaseViewHolder;
import com.utils.Urls;

import java.util.HashMap;
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
    private CountDownTimer timer;


    public QiangGouAdapter(Context context, List<QiangGouModel.QiangGouData> list) {
        this.datalist = list;

        this.context = context;

    }

    @Override
    public QiangGouAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new QiangGouAdapter.Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_qianggou_list, parent, false));
    }

    @Override
    public void onBindViewHolder(final QiangGouAdapter.Holder holder, final int position) {
        try {
            long time = Long.parseLong(datalist.get(position).endTime) - System.currentTimeMillis();

            timer = new CountDownTimer(time, 1000) {

                public void onTick(final long millisUntilFinished) {


                    holder.counttime1.setText(getTimeShort(millisUntilFinished / 1000).split(":")[0]);
                    holder.counttime2.setText(getTimeShort(millisUntilFinished / 1000).split(":")[1]);
                    holder.counttime3.setText(getTimeShort(millisUntilFinished / 1000).split(":")[2]);


                }

                public void onFinish() {
                    holder.counttime1.setText(getTimeShort(0).split(":")[0]);
                    holder.counttime2.setText(getTimeShort(0).split(":")[0]);
                    holder.counttime3.setText(getTimeShort(0).split(":")[0]);

                    timer.cancel();
                    holder.counttime3.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            notifyDataSetChanged();
                        }
                    }, 200);


                }
            };
            timer.start();
            holder.price.setText("￥ " + datalist.get(position).goods.price);
            holder.title.setText(datalist.get(position).goods.name);
            FrescoUtils.displayUrl(holder.hor_img, Urls.getBaseUrl() + "/em/es_pro/" + datalist.get(position).goods.image);
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    AddToCartEvent event = new AddToCartEvent();
                    event.id = datalist.get(position).goods.guid;
                    OttoBus.getInstance().post(event);
                }
            });
            holder.qianggou_rl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!TextUtils.isEmpty(SharedPreferencesUtil.getStringData(context, "ut", ""))) {
                        Intent intent = new Intent(context, WebViewActivity.class);
                        HashMap<String, String> map = new HashMap<>();
                        map.put("productGuid", datalist.get(position).goods.proGuid);
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
        return datalist == null ? 0 : datalist.size();
    }

    static class Holder extends RecyclerView.ViewHolder {


        SimpleDraweeView hor_img;


        TextView title;

        TextView counttime1;

        TextView counttime2;

        TextView counttime3;

        ImageView imageView;

        TextView price;

        RelativeLayout qianggou_rl;

        public Holder(View convertView) {

            super(convertView);
            hor_img = BaseViewHolder.get(convertView, R.id.img);
            title = BaseViewHolder.get(convertView, R.id.title);
            counttime1 = BaseViewHolder.get(convertView, R.id.tx_count_ll1);
            counttime2 = BaseViewHolder.get(convertView, R.id.tx_count_ll2);
            counttime3 = BaseViewHolder.get(convertView, R.id.tx_count_ll3);
            price = BaseViewHolder.get(convertView, R.id.price);
            imageView = BaseViewHolder.get(convertView, R.id.cart);
            qianggou_rl=BaseViewHolder.get(convertView,R.id.qianggou_rl);


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
        if (h >= 10) {
            dateString = dateString.substring(1);
        }

        return dateString;
    }
}
