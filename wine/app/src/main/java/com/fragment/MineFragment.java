package com.fragment;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.activity.LoginActivity;
import com.activity.SettingActivity;
import com.activity.WebViewActivity;
import com.androidyuan.frame.base.fragment.BaseCommFragment;
import com.androidyuan.frame.cores.utils.SharedPreferencesUtil;
import com.androidyuan.frame.cores.utils.image.FrescoUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.iview.IMineView;
import com.model.PersonalModel;
import com.presenter.MinePresenter;
import com.utils.Urls;

import java.util.HashMap;

import zjw.wine.R;


/**
 * Created by mac on 2017/10/16.
 */

public class MineFragment extends BaseCommFragment<MinePresenter> implements View.OnClickListener, IMineView {

    private SimpleDraweeView circle;
    private TextView name_text;
    private ImageView mHunyuanImg;
    private TextView mine_juibi;

    @Override
    protected int getLayoutId() {
        return R.layout.frg_mine;
    }

    @Override
    protected void initAllWidget(View view) {

        view.findViewById(R.id.img_03).setOnClickListener(this);
        circle = (SimpleDraweeView) view.findViewById(R.id.circle);
        circle.setOnClickListener(this);
        mHunyuanImg = (ImageView) view.findViewById(R.id.huiyuan);
        mHunyuanImg.setOnClickListener(this);
        name_text = (TextView) view.findViewById(R.id.name);
        name_text.setOnClickListener(this);
        view.findViewById(R.id.setting).setOnClickListener(this);

        view.findViewById(R.id.quanbu_dingdan).setOnClickListener(this);
        view.findViewById(R.id.dai_fukuan).setOnClickListener(this);
        view.findViewById(R.id.dai_peisong).setOnClickListener(this);
        view.findViewById(R.id.pei_songzhong).setOnClickListener(this);
        view.findViewById(R.id.dai_pingjia).setOnClickListener(this);
        view.findViewById(R.id.tuikuan).setOnClickListener(this);


        view.findViewById(R.id.mine_jiubi_layout).setOnClickListener(this);
        view.findViewById(R.id.mine_number_layout).setOnClickListener(this);
        mine_juibi = (TextView) view.findViewById(R.id.mine_jiubi);
        view.findViewById(R.id.mine_number).setOnClickListener(this);


        view.findViewById(R.id.mine_dizhi).setOnClickListener(this);
        view.findViewById(R.id.mine_pingjia).setOnClickListener(this);
        view.findViewById(R.id.mine_shoucang).setOnClickListener(this);
        view.findViewById(R.id.mine_yaoqing).setOnClickListener(this);
        view.findViewById(R.id.liulan_history).setOnClickListener(this);


        view.findViewById(R.id.mine_tiezi).setOnClickListener(this);
        view.findViewById(R.id.mine_huifu).setOnClickListener(this);
        view.findViewById(R.id.huifu_mine).setOnClickListener(this);

        view.findViewById(R.id.door).setOnClickListener(this);


    }

    @Override
    protected void clickView(View v) {
        switch (v.getId()) {


            //修改信息
            case R.id.circle:
            case R.id.name:
                if (!TextUtils.isEmpty(SharedPreferencesUtil.getStringData(getContext(), "ut", ""))) {

                    Intent intent = new Intent(getContext(), WebViewActivity.class);
                    intent.putExtra("url", Urls.getBaseUrl() + "/eshop/userInfo/data.html");
                    startActivity(intent);
                } else {
                    getContext().startActivity(new Intent(getContext(), LoginActivity.class));
                }
                break;
            //消息栏
            case R.id.img_03:
                if (!TextUtils.isEmpty(SharedPreferencesUtil.getStringData(getContext(), "ut", ""))) {

                } else {
                    getContext().startActivity(new Intent(getContext(), LoginActivity.class));
                }
                break;
            //设置栏
            case R.id.setting:
                getContext().startActivity(new Intent(getContext(), SettingActivity.class));
                break;
            //会员积分栏
            case R.id.huiyuan:
                if (!TextUtils.isEmpty(SharedPreferencesUtil.getStringData(getContext(), "ut", ""))) {

                } else {
                    getContext().startActivity(new Intent(getContext(), LoginActivity.class));
                }
                break;


            //订单详情页
            case R.id.quanbu_dingdan:
                if (!TextUtils.isEmpty(SharedPreferencesUtil.getStringData(getContext(), "ut", ""))) {
                    Intent intent = new Intent(getContext(), WebViewActivity.class);
                    HashMap<String, String> map = new HashMap<>();
                    map.put("item", "orderAll");
                    intent.putExtra("parms", map);
                    intent.putExtra("url", Urls.getBaseUrl() + "/eshop/myOrder/order.html");
                    startActivity(intent);
                } else {
                    getContext().startActivity(new Intent(getContext(), LoginActivity.class));
                }
                break;
            case R.id.dai_fukuan:
                if (!TextUtils.isEmpty(SharedPreferencesUtil.getStringData(getContext(), "ut", ""))) {
                    Intent intent = new Intent(getContext(), WebViewActivity.class);
                    HashMap<String, String> map = new HashMap<>();
                    map.put("item", "waitpay");
                    intent.putExtra("parms", map);
                    intent.putExtra("url", Urls.getBaseUrl() + "/eshop/myOrder/order.html");
                    startActivity(intent);
                } else {
                    getContext().startActivity(new Intent(getContext(), LoginActivity.class));

                }
                break;
            case R.id.dai_peisong:
                if (!TextUtils.isEmpty(SharedPreferencesUtil.getStringData(getContext(), "ut", ""))) {
                    Intent intent = new Intent(getContext(), WebViewActivity.class);
                    HashMap<String, String> map = new HashMap<>();
                    map.put("item", "waitsend");
                    intent.putExtra("parms", map);
                    intent.putExtra("url", Urls.getBaseUrl() + "/eshop/myOrder/order.html");
                    startActivity(intent);
                } else {
                    getContext().startActivity(new Intent(getContext(), LoginActivity.class));
                }
                break;
            case R.id.pei_songzhong:
                if (!TextUtils.isEmpty(SharedPreferencesUtil.getStringData(getContext(), "ut", ""))) {
                    Intent intent = new Intent(getContext(), WebViewActivity.class);
                    HashMap<String, String> map = new HashMap<>();

                    map.put("item", "sending");
                    intent.putExtra("parms", map);
                    intent.putExtra("url", Urls.getBaseUrl() + "/eshop/myOrder/order.html");
                    startActivity(intent);
                } else {
                    getContext().startActivity(new Intent(getContext(), LoginActivity.class));
                }
                break;
            case R.id.dai_pingjia:
                if (!TextUtils.isEmpty(SharedPreferencesUtil.getStringData(getContext(), "ut", ""))) {
                    Intent intent = new Intent(getContext(), WebViewActivity.class);
                    HashMap<String, String> map = new HashMap<>();

                    map.put("item", "waitpingjia");
                    intent.putExtra("parms", map);
                    intent.putExtra("url", Urls.getBaseUrl() + "/eshop/myOrder/order.html");
                    startActivity(intent);
                } else {
                    getContext().startActivity(new Intent(getContext(), LoginActivity.class));
                }
                break;
            case R.id.tuikuan:
                if (!TextUtils.isEmpty(SharedPreferencesUtil.getStringData(getContext(), "ut", ""))) {
                    Intent intent = new Intent(getContext(), WebViewActivity.class);

                    intent.putExtra("url", Urls.getBaseUrl() + "/eshop/myOrder/refund.html");
                    startActivity(intent);
                } else {
                    getContext().startActivity(new Intent(getContext(), LoginActivity.class));
                }
                break;

            //酒币
            case R.id.mine_jiubi_layout:
                if (!TextUtils.isEmpty(SharedPreferencesUtil.getStringData(getContext(), "ut", ""))) {
                    Intent intent = new Intent(getContext(), WebViewActivity.class);
                    intent.putExtra("url", Urls.getBaseUrl() + "/eshop/myJiubiAndCoupon/jiubi.html");
                    startActivity(intent);
                } else {
                    getContext().startActivity(new Intent(getContext(), LoginActivity.class));
                }
                break;
            //优惠券
            case R.id.mine_number_layout:
                if (!TextUtils.isEmpty(SharedPreferencesUtil.getStringData(getContext(), "ut", ""))) {

                } else {
                    getContext().startActivity(new Intent(getContext(), LoginActivity.class));
                }
                break;


            //订单详情页
            case R.id.mine_dizhi:
                if (!TextUtils.isEmpty(SharedPreferencesUtil.getStringData(getContext(), "ut", ""))) {

                    Intent intent = new Intent(getContext(), WebViewActivity.class);
                    intent.putExtra("url", Urls.getBaseUrl() + "/eshop/managerAddress/adress.html");
                    startActivity(intent);

                } else {
                    getContext().startActivity(new Intent(getContext(), LoginActivity.class));
                }
                break;
            case R.id.mine_pingjia:
                if (!TextUtils.isEmpty(SharedPreferencesUtil.getStringData(getContext(), "ut", ""))) {
                    Intent intent = new Intent(getContext(), WebViewActivity.class);
                    intent.putExtra("url", Urls.getBaseUrl() + "/eshop/myEvaluates/evaluates-list.html");
                    startActivity(intent);
                } else {
                    getContext().startActivity(new Intent(getContext(), LoginActivity.class));
                }
                break;
            case R.id.mine_shoucang:
                if (!TextUtils.isEmpty(SharedPreferencesUtil.getStringData(getContext(), "ut", ""))) {
                    Intent intent = new Intent(getContext(), WebViewActivity.class);
                    intent.putExtra("url", Urls.getBaseUrl() + "/eshop/myCollection/collectionShop.html");
                    startActivity(intent);
                } else {
                    getContext().startActivity(new Intent(getContext(), LoginActivity.class));
                }
                break;
            case R.id.mine_yaoqing:
                if (!TextUtils.isEmpty(SharedPreferencesUtil.getStringData(getContext(), "ut", ""))) {

                } else {
                    getContext().startActivity(new Intent(getContext(), LoginActivity.class));
                }
                break;
            case R.id.liulan_history:


                if (!TextUtils.isEmpty(SharedPreferencesUtil.getStringData(getContext(), "ut", ""))) {
                    Intent intent = new Intent(getContext(), WebViewActivity.class);
                    intent.putExtra("url", Urls.getBaseUrl() + "/eshop/myBrowse/browse.html");
                    startActivity(intent);
                } else {
                    getContext().startActivity(new Intent(getContext(), LoginActivity.class));
                }
                break;

            case R.id.mine_tiezi:
                if (!TextUtils.isEmpty(SharedPreferencesUtil.getStringData(getContext(), "ut", ""))) {
                    Intent intent = new Intent(getContext(), WebViewActivity.class);
                    intent.putExtra("url", Urls.getBaseUrl() + "/eshop/myPost/wdtz.html");
                    startActivity(intent);
                } else {
                    getContext().startActivity(new Intent(getContext(), LoginActivity.class));
                }
                break;
            case R.id.mine_huifu:
                if (!TextUtils.isEmpty(SharedPreferencesUtil.getStringData(getContext(), "ut", ""))) {


                    Intent intent = new Intent(getContext(), WebViewActivity.class);
                    intent.putExtra("url", Urls.getBaseUrl() + "/eshop/myPost/wdhf.html");
                    startActivity(intent);
                } else {
                    getContext().startActivity(new Intent(getContext(), LoginActivity.class));
                }
                break;
            case R.id.huifu_mine:
                if (!TextUtils.isEmpty(SharedPreferencesUtil.getStringData(getContext(), "ut", ""))) {

                    Intent intent = new Intent(getContext(), WebViewActivity.class);
                    intent.putExtra("url", Urls.getBaseUrl() + "/eshop/myPost/hfwd.html");
                    startActivity(intent);

                } else {
                    getContext().startActivity(new Intent(getContext(), LoginActivity.class));
                }
                break;

            case R.id.door:
                if (!TextUtils.isEmpty(SharedPreferencesUtil.getStringData(getContext(), "ut", ""))) {
                    Intent intent = new Intent(getContext(), WebViewActivity.class);
                    intent.putExtra("url", Urls.getBaseUrl() + "/eshop/919Store/store.html");
                    startActivity(intent);

                } else {
                    getContext().startActivity(new Intent(getContext(), LoginActivity.class));
                }
                break;


        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (TextUtils.isEmpty(SharedPreferencesUtil.getStringData(getContext(), "ut", ""))) {

            FrescoUtils.displayUrl(circle, "res:///" + R.mipmap.nologin);
            mHunyuanImg.setVisibility(View.GONE);
            name_text.setVisibility(View.GONE);

        } else {

            mHunyuanImg.setVisibility(View.VISIBLE);
            name_text.setVisibility(View.VISIBLE);
            presenter.getPersonalMes();
        }


    }


    @Override
    public void setData(PersonalModel.PersonalResult model) {
        if (model != null) {
            if (TextUtils.isEmpty(model.img)) {
                FrescoUtils.displayUrl(circle, "res:///" + R.mipmap.login);
            } else {
                FrescoUtils.displayUrl(circle, Urls.getBaseUrl() + "/em/es_myself/" + model.img);
            }

            if (TextUtils.isEmpty(model.nick)) {
                name_text.setText("a" + model.phone);
            } else {
                name_text.setText(model.nick);
            }

            mine_juibi.setText(model.wine);
            if (model.grade >= 0 && model.grade <= 499) {
                mHunyuanImg.setImageDrawable(this.getResources().getDrawable(R.mipmap.jf_03));
            } else if (model.grade >= 500 && model.grade <= 999) {
                mHunyuanImg.setImageDrawable(this.getResources().getDrawable(R.mipmap.jf_06));
            } else if (model.grade >= 1000 && model.grade <= 4999) {
                mHunyuanImg.setImageDrawable(this.getResources().getDrawable(R.mipmap.jf_08));
            } else if (model.grade >= 5000 && model.grade <= 9999) {
                mHunyuanImg.setImageDrawable(this.getResources().getDrawable(R.mipmap.jf_10));
            } else if (model.grade >= 10000 && model.grade <= 49999) {
                mHunyuanImg.setImageDrawable(this.getResources().getDrawable(R.mipmap.jf_12));
            } else if (model.grade >= 50000) {
                mHunyuanImg.setImageDrawable(this.getResources().getDrawable(R.mipmap.jf_14));
            }
        }

    }
}
