package com.fragment;

import android.view.View;
import android.widget.Toast;

import com.androidyuan.frame.base.fragment.BaseCommFragment;
import com.presenter.MinePresenter;

import zjw.wine.R;


/**
 * Created by mac on 2017/10/16.
 */

public class MineFragment extends BaseCommFragment<MinePresenter> implements View.OnClickListener {
    @Override
    protected int getLayoutId() {
        return R.layout.frg_mine;
    }

    @Override
    protected void initAllWidget(View view) {

        view.findViewById(R.id.img_03).setOnClickListener(this);
        view.findViewById(R.id.circle).setOnClickListener(this);
        view.findViewById(R.id.huiyuan).setOnClickListener(this);
        view.findViewById(R.id.name).setOnClickListener(this);
        view.findViewById(R.id.setting).setOnClickListener(this);

        view.findViewById(R.id.quanbu_dingdan).setOnClickListener(this);
        view.findViewById(R.id.dai_fukuan).setOnClickListener(this);
        view.findViewById(R.id.dai_peisong).setOnClickListener(this);
        view.findViewById(R.id.pei_songzhong).setOnClickListener(this);
        view.findViewById(R.id.dai_pingjia).setOnClickListener(this);
        view.findViewById(R.id.tuikuan).setOnClickListener(this);


        view.findViewById(R.id.mine_jiubi_layout).setOnClickListener(this);
        view.findViewById(R.id.mine_number_layout).setOnClickListener(this);
        view.findViewById(R.id.mine_jiubi).setOnClickListener(this);
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
                break;
            //消息栏
            case R.id.img_03:
                break;
            //设置栏
            case R.id.setting:
                break;
            //会员积分栏
            case R.id.huiyuan:
                break;


            //订单详情页
            case R.id.quanbu_dingdan:
                break;
            case R.id.dai_fukuan:
                break;
            case R.id.dai_peisong:
                break;
            case R.id.pei_songzhong:
                break;
            case R.id.dai_pingjia:
                break;
            case R.id.tuikuan:
                break;

            //酒币
            case R.id.mine_jiubi_layout:
                break;
            //优惠券
            case R.id.mine_number_layout:
                break;


            //订单详情页
            case R.id.mine_dizhi:
                break;
            case R.id.mine_pingjia:
                break;
            case R.id.mine_shoucang:
                break;
            case R.id.mine_yaoqing:
                break;
            case R.id.liulan_history:
                break;

            case R.id.mine_tiezi:
                break;
            case R.id.mine_huifu:
                break;
            case R.id.huifu_mine:
                break;

            case R.id.door:
                Toast.makeText(getContext(), "haha", Toast.LENGTH_LONG).show();
                break;


        }
    }
}
