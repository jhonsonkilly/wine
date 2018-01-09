package com.fragment;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.Event.AddToCartNumberEvent;
import com.activity.LoginActivity;
import com.activity.SettingActivity;
import com.activity.WebViewActivity;
import com.androidyuan.frame.base.fragment.BaseCommFragment;
import com.androidyuan.frame.cores.utils.SharedPreferencesUtil;
import com.androidyuan.frame.cores.utils.image.FrescoUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.iview.IMineView;
import com.model.PersonalModel;
import com.otto.OttoBus;
import com.presenter.MinePresenter;
import com.utils.Urls;

import java.util.HashMap;

import zjw.wine.R;


/**
 * Created by mac on 2017/10/16.
 */

public class MineFragment extends BaseCommFragment<MinePresenter> implements View.OnClickListener, IMineView {

    private SimpleDraweeView circle, circle_small;
    private TextView name_text;
    private SimpleDraweeView mHunyuanImg;
    private TextView mine_juibi;
    private TextView text_exp;

    @Override
    protected int getLayoutId() {
        return R.layout.frg_mine;
    }

    @Override
    protected void initAllWidget(View view) {

        view.findViewById(R.id.img_03).setOnClickListener(this);
        circle = (SimpleDraweeView) view.findViewById(R.id.circle);
        circle_small = view.findViewById(R.id.circle_small);
        circle.setOnClickListener(this);
        circle_small.setOnClickListener(this);
        mHunyuanImg = view.findViewById(R.id.huiyuan);
        mHunyuanImg.setOnClickListener(this);
        text_exp = view.findViewById(R.id.text_exp);
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

                    Intent intent = new Intent(getContext(), WebViewActivity.class);
                    intent.putExtra("url", Urls.getBaseUrl() + "/eshop/myInvited/wdyq.html");
                    startActivity(intent);
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

            circle_small.setVisibility(View.GONE);
            text_exp.setVisibility(View.GONE);
        } else {

            mHunyuanImg.setVisibility(View.VISIBLE);
            name_text.setVisibility(View.VISIBLE);
            circle_small.setVisibility(View.VISIBLE);
            text_exp.setVisibility(View.VISIBLE);
            presenter.getPersonalMes();

        }


    }


    @Override
    public void setData(PersonalModel.PersonalResult model) {
        if (model != null) {


            if (TextUtils.isEmpty(model.img)) {
                FrescoUtils.displayUrl(circle, "res:///" + R.mipmap.login);
            } else {
                FrescoUtils.displayUrl(circle, Urls.getBaseUrl() + "/nmw/thumb/" + model.img);
            }

            if (TextUtils.isEmpty(model.nick)) {
                name_text.setText("a" + model.phone);
            } else {
                name_text.setText(model.nick);
            }

            if (TextUtils.isEmpty(model.wine)) {
                mine_juibi.setText("0");
            } else {
                mine_juibi.setText(model.wine);
            }

            if (model.isMale()) {
                FrescoUtils.displayUrl(circle_small, "res:///" + R.mipmap.nan);
            } else {
                FrescoUtils.displayUrl(circle_small, "res:///" + R.mipmap.nv);
            }


            FrescoUtils.displayUrl(mHunyuanImg, Urls.getBaseUrl() + "/em/es_grade/" + model.gradeInfo.levelIcon);

            text_exp.setText(model.evalue + "/" + (model.gradeInfo.right - model.gradeInfo.left) + "");


        }

    }
}
