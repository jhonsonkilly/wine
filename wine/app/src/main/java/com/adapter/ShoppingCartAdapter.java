package com.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.activity.WebViewActivity;
import com.androidyuan.frame.cores.utils.image.FrescoUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.model.MapWrapper;
import com.model.ShoppingListModel;
import com.utils.Urls;

import java.text.DecimalFormat;
import java.util.LinkedHashMap;
import java.util.List;

import zjw.wine.R;

/**
 * Created by AYD on 2016/11/21.
 * <p/>
 * 购物车Adapter
 */
public class ShoppingCartAdapter extends BaseAdapter {


    private List<ShoppingListModel.ShoppingResult> shoppingCartBeanList;
    private CheckInterface checkInterface;
    private ModifyCountInterface modifyCountInterface;
    private Context context;

    public ShoppingCartAdapter(Context context) {
        this.context = context;
    }

    public void setShoppingCartBeanList(List<ShoppingListModel.ShoppingResult> shoppingCartBeanList) {
        this.shoppingCartBeanList = shoppingCartBeanList;
        notifyDataSetChanged();
    }

    /**
     * 单选接口
     *
     * @param checkInterface
     */
    public void setCheckInterface(CheckInterface checkInterface) {
        this.checkInterface = checkInterface;
    }

    /**
     * 改变商品数量接口
     *
     * @param modifyCountInterface
     */
    public void setModifyCountInterface(ModifyCountInterface modifyCountInterface) {
        this.modifyCountInterface = modifyCountInterface;
    }

    @Override
    public int getCount() {
        return shoppingCartBeanList == null ? 0 : shoppingCartBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return shoppingCartBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    /**
     * 是否显示可编辑
     *
     * @param flag
     */
    public void isShow(boolean flag) {

        notifyDataSetChanged();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_shopping_cart_layout, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final ShoppingListModel.ShoppingResult shoppingCartBean = shoppingCartBeanList.get(position);
        boolean choosed = shoppingCartBean.isChoose();
        if (choosed) {
            holder.ckOneChose.setChecked(true);
        } else {
            holder.ckOneChose.setChecked(false);
        }
        if (!TextUtils.isEmpty(shoppingCartBean.price)) {
            double totalPrice = Double.parseDouble(shoppingCartBean.price) * shoppingCartBean.quantity;
            DecimalFormat df = new java.text.DecimalFormat("#0.00");
            holder.totalTx.setText(df.format(totalPrice) + "元");
        }


        holder.tvCommodityName.setText(shoppingCartBean.goodName);
        holder.tvCommodityPrice.setText(shoppingCartBean.price + "");

        holder.tvCommodityShowNum.setText(shoppingCartBean.quantity + "");
        //ImageLoader.getInstance().displayImage(shoppingCartBean.getImageUrl(),holder.ivShowPic);
        FrescoUtils.displayUrl(holder.ivShowPic, Urls.getBaseUrl() + "/em/es_pro/" + shoppingCartBean.image);
        //单选框按钮
        holder.ckOneChose.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        shoppingCartBean.setChoose(((CheckBox) v).isChecked());
                        checkInterface.checkGroup(position, ((CheckBox) v).isChecked());//向外暴露接口
                    }
                }
        );
        //增加按钮
        holder.ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyCountInterface.doIncrease(position, holder.tvCommodityShowNum, holder.ckOneChose.isChecked());//暴露增加接口
            }
        });
        //删减按钮
        holder.ivSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyCountInterface.doDecrease(position, holder.tvCommodityShowNum, holder.ckOneChose.isChecked());//暴露删减接口
            }
        });
        //删除弹窗
        holder.tvCommodityDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alert = new AlertDialog.Builder(context).create();
                alert.setTitle("操作提示");
                alert.setMessage("您确定要将这些商品从购物车中移除吗？");
                alert.setButton(DialogInterface.BUTTON_NEGATIVE, "取消",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                return;
                            }
                        });
                alert.setButton(DialogInterface.BUTTON_POSITIVE, "确定",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                modifyCountInterface.childDelete(position);//删除 目前只是从item中移除

                            }
                        });
                alert.show();
            }
        });
        //判断是否在编辑状态下

        holder.tvCommodityName.setVisibility(View.VISIBLE);

        holder.tvCommodityDelete.setVisibility(View.GONE);

        holder.ll_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, WebViewActivity.class);
                LinkedHashMap<String, String> map = new LinkedHashMap<>();

                map.put("productGuid", shoppingCartBean.productGuid);
                map.put("name", shoppingCartBean.goodName);
                map.put("cost", shoppingCartBean.price);
                map.put("goodGuid", shoppingCartBean.goodGuid);

                intent.putExtra("objetParms", new MapWrapper().setMap(map));
                intent.putExtra("url", Urls.getBaseUrl() + "/eshop/commodity/commodity.html");
                context.startActivity(intent);
            }
        });


        return convertView;
    }

    //初始化控件
    class ViewHolder {
        ImageView tvCommodityDelete;
        SimpleDraweeView ivShowPic;
        TextView tvCommodityName, tvCommodityPrice, tvCommodityShowNum, ivSub, ivAdd, totalTx;
        CheckBox ckOneChose;
        LinearLayout rlEdit;
        LinearLayout ll_layout;

        public ViewHolder(View itemView) {
            ckOneChose = (CheckBox) itemView.findViewById(R.id.ck_chose);
            ivShowPic = (SimpleDraweeView) itemView.findViewById(R.id.iv_show_pic);
            ivSub = (TextView) itemView.findViewById(R.id.iv_sub);
            ivAdd = (TextView) itemView.findViewById(R.id.iv_add);
            tvCommodityName = (TextView) itemView.findViewById(R.id.tv_commodity_name);

            tvCommodityPrice = (TextView) itemView.findViewById(R.id.tv_commodity_price);

            tvCommodityShowNum = (TextView) itemView.findViewById(R.id.tv_commodity_show_num);
            tvCommodityDelete = (ImageView) itemView.findViewById(R.id.tv_commodity_delete);
            rlEdit = (LinearLayout) itemView.findViewById(R.id.rl_edit);
            totalTx = (TextView) itemView.findViewById(R.id.total_tx);
            ll_layout=itemView.findViewById(R.id.ll_item);


        }
    }

    /**
     * 复选框接口
     */
    public interface CheckInterface {
        /**
         * 组选框状态改变触发的事件
         *
         * @param position  元素位置
         * @param isChecked 元素选中与否
         */
        void checkGroup(int position, boolean isChecked);
    }


    /**
     * 改变数量的接口
     */
    public interface ModifyCountInterface {
        /**
         * 增加操作
         *
         * @param position      元素位置
         * @param showCountView 用于展示变化后数量的View
         * @param isChecked     子元素选中与否
         */
        void doIncrease(int position, View showCountView, boolean isChecked);

        /**
         * 删减操作
         *
         * @param position      元素位置
         * @param showCountView 用于展示变化后数量的View
         * @param isChecked     子元素选中与否
         */
        void doDecrease(int position, View showCountView, boolean isChecked);

        /**
         * 删除子item
         *
         * @param position
         */
        void childDelete(int position);
    }
}
