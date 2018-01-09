package com.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.Event.AddToCartNumberEvent;
import com.androidyuan.frame.base.activity.BaseCommActivity;
import com.androidyuan.frame.cores.utils.SharedPreferencesUtil;
import com.iview.ILoginView;
import com.model.PersonalModel;
import com.otto.OttoBus;
import com.presenter.LoginPresenter;
import com.widget.CountDownTextView;
import com.widget.ToolBar;

import zjw.wine.R;

/**
 * Created by mac on 2017/10/16.
 */

public class LoginActivity extends BaseCommActivity<LoginPresenter> implements ILoginView {

    ToolBar toolBar;

    EditText ed_phone;

    EditText ed_yan;

    CountDownTextView tx_click;

    @Override
    protected int getLayoutId() {

        return R.layout.layout_login;
    }

    @Override
    protected void initAllWidget() {
        toolBar = (ToolBar) findViewById(R.id.toolbar);
        toolBar.setTitle("登陆");
        ed_phone = (EditText) findViewById(R.id.ed_phone);
        ed_yan = (EditText) findViewById(R.id.ed_yan);
        tx_click = (CountDownTextView) findViewById(R.id.tx_click);
        tx_click.setOnClickListener(this);
        findViewById(R.id.login_button).setOnClickListener(this);
        findViewById(R.id.zhuce_text).setOnClickListener(this);

    }

    @Override
    protected void clickView(View v) {
        if (v.getId() == R.id.tx_click) {
            if (vertifyMes(false)) {
                presenter.getVertifyCode(ed_phone.getText().toString());

            }
        }
        if (v.getId() == R.id.login_button) {
            if (vertifyMes(true)) {


                presenter.login(ed_phone.getText().toString(), ed_yan.getText().toString());
            }

        }
        if (v.getId() == R.id.zhuce_text) {

            startActivity(new Intent(this, RegisterActivity.class));
            finish();
        }
    }

    @Override
    public void setTopTitle(String title) {

    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void showResult(String result) {

        Toast.makeText(this, result, Toast.LENGTH_LONG).show();

        tx_click.startCountDown();

        tx_click.setOnfinishListener(new CountDownTextView.OnFinishListener() {
            @Override
            public void onFinish() {
                if (!TextUtils.isEmpty(ed_yan.getText().toString())) {
                    presenter.delVertifyCode();
                }

            }
        });

    }

    @Override
    public void showLogin(String token) {

        SharedPreferencesUtil.saveStringData(this, "ut", token);
        presenter.getPersonalMes();

    }

    @Override
    public void showPersonal(PersonalModel.PersonalResult model) {
        if (model != null) {

            SharedPreferencesUtil.saveStringData(this, "uid", model.guid);
            SharedPreferencesUtil.saveStringData(this, "img", model.img);
            SharedPreferencesUtil.saveStringData(this, "nick", model.nick);
            AddToCartNumberEvent event = new AddToCartNumberEvent();
            event.result = model.cartItemCount;
            OttoBus.getInstance().post(event);
            finish();


        } else {

            Toast.makeText(this, "个人信息获取失败", Toast.LENGTH_LONG).show();
            finish();

        }

    }


    public boolean vertifyMes(boolean isNeedYan) {

        if (TextUtils.isEmpty(ed_phone.getText().toString())) {

            Toast.makeText(this, "请输入手机号", Toast.LENGTH_LONG).show();
            return false;
        }
        if (ed_phone.getText().toString().length() != 11) {

            Toast.makeText(this, "请输入11位手机号", Toast.LENGTH_LONG).show();
            return false;
        }
        if (isNeedYan) {
            if (TextUtils.isEmpty(ed_yan.getText().toString())) {
                Toast.makeText(this, "请输入验证码", Toast.LENGTH_LONG).show();
                return false;
            }

        }
        return true;
    }


}
