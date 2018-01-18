package com.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.Event.AddToCartNumberEvent;
import com.Event.GoHomeEvent;
import com.activity.LoginActivity;
import com.activity.WebViewActivity;
import com.adapter.ShoppingCartAdapter;
import com.androidyuan.frame.base.fragment.BaseCommFragment;
import com.androidyuan.frame.cores.utils.FastJSONHelper;
import com.androidyuan.frame.cores.utils.SharedPreferencesUtil;
import com.androidyuan.frame.cores.widget.FixHeightListView;
import com.iview.ICartListView;
import com.model.ShoppingListModel;
import com.otto.OttoBus;
import com.presenter.ShoppingCartPresenter;
import com.utils.Urls;
import com.widget.ToolBar;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import zjw.wine.R;

/**
 * Created by mac on 2017/10/16.
 */

public class ShoppingCartFragment extends BaseCommFragment<ShoppingCartPresenter> implements ICartListView
        , ShoppingCartAdapter.CheckInterface, ShoppingCartAdapter.ModifyCountInterface {

    private ToolBar toolBar;

    TextView text_go_home;

    CheckBox ckAll;
    private FixHeightListView listView;

    ShoppingCartAdapter shoppingCartAdapter;

    List<ShoppingListModel.ShoppingResult> shoppingCartBeanList = new ArrayList<>();

    private double totalPrice = 0.00;// 购买的商品总价


    //总额
    TextView tvShowPrice;

    Button showButton;
    private LinearLayout empty_cart;
    private int currentCount;

    View showCountView;


    private static final int TYPE1 = 1;
    private static final int TYPE2 = 2;
    private static final int TYPE3 = 3;

    private ShoppingListModel.ShoppingResult selectBean;

    private ShoppingListModel.ShoppingResult unselectBean;


    @Override
    protected int getLayoutId() {
        return R.layout.frg_shopping_cart;
    }

    @Override
    protected void initAllWidget(View view) {
        toolBar = (ToolBar) view.findViewById(R.id.toolbar);
        text_go_home = (TextView) view.findViewById(R.id.text_go_home);

        text_go_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                OttoBus.getInstance().post(new GoHomeEvent());
            }
        });

        toolBar.setTitle("购物车");
        toolBar.hideBack();

        toolBar.showShopCart().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog alert = new AlertDialog.Builder(getContext()).create();
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
                                StringBuilder builder = new StringBuilder();
                                for (ShoppingListModel.ShoppingResult result : shoppingCartBeanList) {
                                    if (result.isChoose()) {
                                        if (builder.length() > 0) {
                                            builder.append(",").append(result.goodGuid);
                                        } else {
                                            builder.append(result.goodGuid);
                                        }


                                    }
                                }
                                presenter.delateAllCart(builder.toString());


                            }
                        });
                alert.show();
            }
        });
        listView = (FixHeightListView) view.findViewById(R.id.cart_list);
        empty_cart = (LinearLayout) view.findViewById(R.id.empty_cart);
        ckAll = (CheckBox) view.findViewById(R.id.ck_all);
        ckAll.setOnClickListener(this);


        tvShowPrice = (TextView) view.findViewById(R.id.tv_show_price_tx);
        showButton = (Button) view.findViewById(R.id.tv_settlement);
        showButton.setOnClickListener(this);

    }

    @Override
    protected void clickView(View v) {
        switch (v.getId()) {
            case R.id.ck_all:
                if (shoppingCartBeanList.size() != 0) {
                    if (ckAll.isChecked()) {
                        for (int i = 0; i < shoppingCartBeanList.size(); i++) {
                            shoppingCartBeanList.get(i).setChoose(true);
                        }
                        shoppingCartAdapter.notifyDataSetChanged();
                    } else {
                        for (int i = 0; i < shoppingCartBeanList.size(); i++) {
                            shoppingCartBeanList.get(i).setChoose(false);
                        }
                        shoppingCartAdapter.notifyDataSetChanged();
                    }
                    statistics();
                }
                break;
            case R.id.tv_settlement:
                lementOnder();
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (TextUtils.isEmpty(SharedPreferencesUtil.getStringData(getContext(), "ut", ""))) {
            empty_cart.setVisibility(View.VISIBLE);
            listView.setVisibility(View.GONE);

        } else {

            empty_cart.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
            presenter.getCartList();
        }

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden) {
            if (TextUtils.isEmpty(SharedPreferencesUtil.getStringData(getContext(), "ut", ""))) {
                empty_cart.setVisibility(View.VISIBLE);
                listView.setVisibility(View.GONE);

            } else {

                empty_cart.setVisibility(View.GONE);
                listView.setVisibility(View.VISIBLE);
                presenter.getCartList();
            }
        }

    }

    @Override
    public void getCartList(List<ShoppingListModel.ShoppingResult> list) {
        this.shoppingCartBeanList = list;
        if (shoppingCartBeanList.size() == 0) {
            empty_cart.setVisibility(View.VISIBLE);
            listView.setVisibility(View.GONE);

        } else {
            if (isAllCheck()) {
                ckAll.setChecked(true);
            } else {
                ckAll.setChecked(false);
            }
            statistics();
            if (shoppingCartAdapter == null) {
                shoppingCartAdapter = new ShoppingCartAdapter(getContext());
                shoppingCartAdapter.setCheckInterface(this);
                shoppingCartAdapter.setModifyCountInterface(this);
                listView.setAdapter(shoppingCartAdapter);
                shoppingCartAdapter.setShoppingCartBeanList(list);
            } else {
                shoppingCartAdapter.setShoppingCartBeanList(list);
            }


        }
        AddToCartNumberEvent event = new AddToCartNumberEvent();
        event.result = list.size() + "";
        OttoBus.getInstance().post(event);


    }

    @Override
    public void showMes(String id, int type) {

        //增加减少
        if (type == TYPE1) {
            Toast.makeText(getContext(), id, Toast.LENGTH_LONG).show();
            ((TextView) showCountView).setText(currentCount + "");
            shoppingCartAdapter.notifyDataSetChanged();
            statistics();
        }
        //单个添加
        if (type == TYPE2) {
            Toast.makeText(getContext(), id, Toast.LENGTH_LONG).show();
            shoppingCartAdapter.notifyDataSetChanged();
            statistics();
        }
        //全选
        if (type == TYPE3) {

            selectBean.setChoose(true);
            shoppingCartAdapter.notifyDataSetChanged();
            statistics();
        }


    }


    @Override
    public void delateAllGoods(String number) {

        Iterator<ShoppingListModel.ShoppingResult> it = shoppingCartBeanList.iterator();

        while (it.hasNext()) {

            ShoppingListModel.ShoppingResult x = it.next();
            if (x.isChoose()) {

                it.remove();

            }
        }
        if (shoppingCartBeanList.size() == 0) {

            empty_cart.setVisibility(View.VISIBLE);
            listView.setVisibility(View.GONE);


        } else {
            shoppingCartAdapter.notifyDataSetChanged();
        }

        if (isAllCheck()) {
            ckAll.setChecked(true);
        } else {
            ckAll.setChecked(false);
        }
        statistics();

        Toast.makeText(getContext(), "删除成功", Toast.LENGTH_LONG).show();

        AddToCartNumberEvent event = new AddToCartNumberEvent();
        event.result = number;
        OttoBus.getInstance().post(event);
    }


    @Override
    public void checkGroup(int position, boolean isChecked) {
        shoppingCartBeanList.get(position).setChoose(isChecked);

        if (isAllCheck())
            ckAll.setChecked(true);
        else
            ckAll.setChecked(false);


        statistics();


    }

    private boolean isAllCheck() {

        if (shoppingCartBeanList.size() == 0) {
            return false;
        }
        for (ShoppingListModel.ShoppingResult group : shoppingCartBeanList) {
            if (!group.isChoose())
                return false;
        }
        return true;
    }

    private boolean isAllunCheck() {

        for (ShoppingListModel.ShoppingResult group : shoppingCartBeanList) {
            if (group.isChoose())
                return false;
        }
        return true;
    }

    public void statistics() {
        try {
            totalPrice = 0.00;
            for (int i = 0; i < shoppingCartBeanList.size(); i++) {
                ShoppingListModel.ShoppingResult shoppingCartBean = shoppingCartBeanList.get(i);
                if (shoppingCartBean.isChoose()) {

                    if (!TextUtils.isEmpty(shoppingCartBean.price)) {
                        totalPrice += Double.parseDouble(shoppingCartBean.price) * shoppingCartBean.quantity;
                    }

                }
            }
            DecimalFormat df = new java.text.DecimalFormat("#0.00");
            tvShowPrice.setText("￥" + df.format(totalPrice));
        } catch (Exception e) {

        }

    }

    @Override
    public void doIncrease(int position, View showCountView, boolean isChecked) {
        this.showCountView = showCountView;
        ShoppingListModel.ShoppingResult shoppingCartBean = shoppingCartBeanList.get(position);
        currentCount = shoppingCartBean.getQuantity();
        currentCount++;
        shoppingCartBean.setQuantity(currentCount);
        presenter.addToCart(shoppingCartBean.goodGuid, "1", TYPE1);

    }

    @Override
    public void doDecrease(int position, View showCountView, boolean isChecked) {
        this.showCountView = showCountView;
        ShoppingListModel.ShoppingResult shoppingCartBean = shoppingCartBeanList.get(position);
        int currentCount = shoppingCartBean.getQuantity();
        if (currentCount == 1) {
            return;
        }
        currentCount--;
        shoppingCartBean.setQuantity(currentCount);

        presenter.addToCart(shoppingCartBean.goodGuid, -1 + "", TYPE1);

    }

    @Override
    public void childDelete(int position) {
        shoppingCartBeanList.remove(position);
        shoppingCartAdapter.notifyDataSetChanged();
        statistics();
    }


    private void lementOnder() {
        //选中的需要提交的商品清单


        ArrayList<Map<String, Object>> list = new ArrayList<>();

        if (shoppingCartBeanList.size() == 0) {
            Toast.makeText(getContext(), "请添加商品至购物车", Toast.LENGTH_LONG).show();
            return;
        }
        if (isAllunCheck()) {
            Toast.makeText(getContext(), "请选择一件商品", Toast.LENGTH_LONG).show();
            return;
        }

        Toast.makeText(getContext(), "总价：" + totalPrice, Toast.LENGTH_LONG).show();


        if (!TextUtils.isEmpty(SharedPreferencesUtil.getStringData(getContext(), "ut", ""))) {
            for (ShoppingListModel.ShoppingResult result : shoppingCartBeanList) {

                Map<String, Object> models = new HashMap<>();
                models.put("id", result.id);
                models.put("selected", result.isChoose == true ? 1 : 0);
                list.add(models);

            }

            try {
                Intent intent = new Intent(getContext(), WebViewActivity.class);

                HashMap<String, String> map = new HashMap<>();
                map.put("cartItems", FastJSONHelper.toJSONStr(list));
                intent.putExtra("parms", map);

                intent.putExtra("url", Urls.getBaseUrl() + "/eshop/shoppingCart/Confirm-order.html");

                startActivity(intent);

            } catch (Exception e) {

            }

        } else {
            getContext().startActivity(new Intent(getContext(), LoginActivity.class));
        }


    }


}
