package com.activity;

import android.content.Intent;
import android.view.View;

import com.Event.PayEvent;
import com.Event.ReloadUrlEvent;
import com.androidyuan.frame.base.activity.BaseApplication;
import com.androidyuan.frame.base.activity.BaseCommActivity;
import com.androidyuan.frame.cores.log.CommonLogger;
import com.model.MapWrapper;
import com.otto.OttoBus;
import com.otto.Subscribe;
import com.presenter.WebViewPresenter;
import com.utils.LocationManager;
import com.utils.Urls;
import com.widget.H5InputWebView;
import com.widget.JavaScriptMethods;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.LinkedHashMap;

import zjw.wine.R;

/**
 * <p>Copyright:Copyright(c) 2016</p>
 * <p>Company:上海来伊份电子商务有限公司</p>
 * <p>包名:com.activity</p>
 * <p>文件名:wine</p>
 * <p>类更新历史信息</p>
 *
 * @todo <a href="mailto:zhoujiawei@laiyifen.com">vernal(周佳伟)</a>
 */
public class WebViewActivity extends BaseCommActivity<WebViewPresenter> {

    H5InputWebView qmWebview;
    private String url;
    private HashMap<String, String> parms;

    private MapWrapper mapWrapper;

    private CommonLogger mCommonLogger = CommonLogger.buildLogger(this);

    @Override
    protected int getLayoutId() {
        return R.layout.act_webview;
    }

    @Override
    protected void initAllWidget() {
        qmWebview = (H5InputWebView) findViewById(R.id.webview);
        qmWebview.addJavascriptInterface(new JavaScriptMethods(this, qmWebview), "mobileAPI");
        url = getIntent().getStringExtra("url");

        parms = (HashMap<String, String>) getIntent().getSerializableExtra("parms");

        mapWrapper = (MapWrapper) getIntent().getSerializableExtra("objetParms");


        if (parms != null) {
            qmWebview.putExParams(parms);
        }
        if (mapWrapper != null) {
            qmWebview.putExParams(mapWrapper.getMap());
        }
        qmWebview.loadUrl(url);


        OttoBus.getInstance().register(this);

    }

    @Override
    protected void clickView(View v) {

    }

    @Override
    public void setTopTitle(String title) {

    }

    @Override
    public void showProgressBar() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OttoBus.getInstance().unregister(this);
    }

    //接受参数
    @Subscribe
    public void getResult(PayEvent event) {

        if (event.isParams()) {

            presenter.submitMes(event.map);
        }
        if (event.isResult()) {

            HashMap<String, String> map = new HashMap<>();

            map.put("payResult", event.payResult);
            qmWebview.putExParams(map);
            qmWebview.loadUrl(Urls.getBaseUrl() + "/eshop/shoppingCart/pay.html");

        }
    }

    @Subscribe
    public void reload(ReloadUrlEvent event) {

        new LocationManager(this).setLocationListener(new LocationManager.LocationListener() {
            @Override
            public void onLocationChanged(LocationManager.MapLocation location) {
                if (location != null) {

                    //Toast.makeText(getContext(), location.address, Toast.LENGTH_LONG).show();



                    parms=new LinkedHashMap<>();
                    parms.put("lat", location.y);
                    parms.put("lng", location.x);
                    if (parms != null) {
                        qmWebview.putExParams(parms);
                    }
                    qmWebview.loadUrl(Urls.getBaseUrl() + "/eshop/managerAddress/addAdress.html");


                }
            }
        }).setOnceLocation(true)
                .startLocation(this);





    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        qmWebview.onActivityResult(requestCode, resultCode, data);

    }


}
