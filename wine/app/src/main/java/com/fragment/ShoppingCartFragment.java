package com.fragment;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.Event.GoHomeEvent;
import com.activity.LoginActivity;
import com.activity.WebViewActivity;
import com.adapter.ShoppingCartAdapter;
import com.androidyuan.frame.base.fragment.BaseCommFragment;
import com.androidyuan.frame.cores.utils.SharedPreferencesUtil;
import com.androidyuan.frame.cores.widget.FixHeightListView;
import com.iview.ICartListView;
import com.model.ShoppingListModel;
import com.otto.OttoBus;
import com.presenter.ShoppingCartPresenter;
import com.utils.Urls;
import com.widget.ToolBar;

import java.util.List;

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

    List<ShoppingListModel.ShoppingResult> shoppingCartBeanList;

    private double totalPrice = 0.00;// 购买的商品总价


    //总额
    TextView tvShowPrice;

    Button showButton;
    private LinearLayout empty_cart;


    @Override
    protected int getLayoutId() {
        return R.layout.frg_shopping_cart;
    }

    @Override
    protected void initAllWidget(View view) {
        toolBar = view.findViewById(R.id.toolbar);
        text_go_home = view.findViewById(R.id.text_go_home);

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

            }
        });
        listView = view.findViewById(R.id.cart_list);
        empty_cart = view.findViewById(R.id.empty_cart);
        ckAll = view.findViewById(R.id.ck_all);
        ckAll.setOnClickListener(this);
        tvShowPrice = view.findViewById(R.id.tv_show_price_tx);
        showButton = view.findViewById(R.id.tv_settlement);
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
        if (!TextUtils.isEmpty(SharedPreferencesUtil.getStringData(getContext(), "ut", ""))) {
            empty_cart.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
            presenter.getCartList();
        } else {
            empty_cart.setVisibility(View.VISIBLE);
            listView.setVisibility(View.GONE);

        }

    }

    @Override
    public void getCartList(List<ShoppingListModel.ShoppingResult> list) {
        this.shoppingCartBeanList = list;

        shoppingCartAdapter = new ShoppingCartAdapter(getContext());
        shoppingCartAdapter.setCheckInterface(this);
        shoppingCartAdapter.setModifyCountInterface(this);
        listView.setAdapter(shoppingCartAdapter);
        shoppingCartAdapter.setShoppingCartBeanList(list);
    }

    @Override
    public void checkGroup(int position, boolean isChecked) {
        shoppingCartBeanList.get(position).setChoose(isChecked);
        if (isAllCheck())
            ckAll.setChecked(true);
        else
            ckAll.setChecked(false);
        shoppingCartAdapter.notifyDataSetChanged();
        statistics();
    }

    private boolean isAllCheck() {

        for (ShoppingListModel.ShoppingResult group : shoppingCartBeanList) {
            if (!group.isChoose())
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
                    totalPrice += Double.parseDouble(shoppingCartBean.price) * shoppingCartBean.quantity;
                }
            }
            tvShowPrice.setText("￥" + totalPrice);
        } catch (Exception e) {

        }

    }

    @Override
    public void doIncrease(int position, View showCountView, boolean isChecked) {
        ShoppingListModel.ShoppingResult shoppingCartBean = shoppingCartBeanList.get(position);
        int currentCount = shoppingCartBean.getQuantity();
        currentCount++;
        shoppingCartBean.setQuantity(currentCount);
        ((TextView) showCountView).setText(currentCount + "");
        shoppingCartAdapter.notifyDataSetChanged();
        statistics();
    }

    @Override
    public void doDecrease(int position, View showCountView, boolean isChecked) {
        ShoppingListModel.ShoppingResult shoppingCartBean = shoppingCartBeanList.get(position);
        int currentCount = shoppingCartBean.getQuantity();
        if (currentCount == 1) {
            return;
        }
        currentCount--;
        shoppingCartBean.setQuantity(currentCount);
        ((TextView) showCountView).setText(currentCount + "");
        shoppingCartAdapter.notifyDataSetChanged();
        statistics();
    }

    @Override
    public void childDelete(int position) {
        shoppingCartBeanList.remove(position);
        shoppingCartAdapter.notifyDataSetChanged();
        statistics();
    }


    private void lementOnder() {
        //选中的需要提交的商品清单
        for (ShoppingListModel.ShoppingResult bean : shoppingCartBeanList) {
            boolean choosed = bean.isChoose();
            if (choosed) {


            }
        }
        Toast.makeText(getContext(), "总价：" + totalPrice, Toast.LENGTH_LONG).show();
        if (!TextUtils.isEmpty(SharedPreferencesUtil.getStringData(getContext(), "ut", ""))) {

            Intent intent = new Intent(getContext(), WebViewActivity.class);
            intent.putExtra("url", Urls.getBaseUrl() + "/eshop/shoppingCart/Confirm-order.html");
            startActivity(intent);
        } else {
            getContext().startActivity(new Intent(getContext(), LoginActivity.class));
        }


    }


}
