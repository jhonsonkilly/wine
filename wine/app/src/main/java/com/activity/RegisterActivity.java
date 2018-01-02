package com.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.androidyuan.frame.base.activity.BaseCommActivity;
import com.androidyuan.frame.cores.utils.SharedPreferencesUtil;
import com.iview.IRegistView;
import com.model.PersonalModel;
import com.presenter.RegistPresenter;
import com.widget.CountDownTextView;
import com.widget.ToolBar;

import zjw.wine.R;

/**
 * Created by mac on 2017/10/16.
 */

public class RegisterActivity extends BaseCommActivity<RegistPresenter> implements IRegistView {

    ToolBar toolBar;


    EditText ed_phone;

    EditText ed_yan;

    CountDownTextView tx_click;
    private EditText ed_yao;

    @Override
    protected int getLayoutId() {
        return R.layout.layout_regist;
    }

    @Override
    protected void initAllWidget() {
        toolBar = (ToolBar) findViewById(R.id.toolbar);
        toolBar.setTitle("注册");
        ed_phone = (EditText) findViewById(R.id.ed_phone);
        ed_yan = (EditText) findViewById(R.id.ed_yan);
        tx_click = (CountDownTextView) findViewById(R.id.tx_click);
        tx_click.setOnClickListener(this);
        ed_yao = (EditText) findViewById(R.id.ed_yao);
        findViewById(R.id.regist_button).setOnClickListener(this);


    }

    @Override
    protected void clickView(View v) {
        if (v.getId() == R.id.tx_click) {
            if (vertifyMes(false)) {
                presenter.getVertifyCode(ed_phone.getText().toString());

            }


        }
        if (v.getId() == R.id.regist_button) {
            if (vertifyMes(true)) {

                presenter.regist(ed_phone.getText().toString(), ed_yan.getText().toString(), ed_yao.getText().toString());
            }

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

                presenter.delVertifyCode();
            }
        });

    }

    @Override
    public void showRegist(String token) {
        // Toast.makeText(this, mes, Toast.LENGTH_LONG).show();
        // startActivity(new Intent(this, LoginActivity.class));
        SharedPreferencesUtil.saveStringData(this, "ut", token);
        presenter.getPersonalMes();

    }

    @Override
    public void showPersonal(PersonalModel.PersonalResult model) {
        if (model != null) {

            SharedPreferencesUtil.saveStringData(this, "uid", model.guid);
            SharedPreferencesUtil.saveStringData(this, "img", model.img);
            SharedPreferencesUtil.saveStringData(this, "nick", model.nick);
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
