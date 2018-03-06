package com.widget;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

import com.activity.WineApplication;
import com.model.UserModel;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import org.json.JSONObject;

import zjw.wine.R;

/**
 * Created by mac on 2018/2/27.
 */

public class JavaScriptMethods {

    private Context context;
    private WebView mWebView;
    private Handler mHandler = new Handler();

    public JavaScriptMethods(Context context, WebView webView) {
        this.context = context;
        this.mWebView = webView;
    }

    @JavascriptInterface //android4.2之后，如果不加上该注解，js无法调用android方法（安全）
    public void takeShareParams(final String json) {


        try {
            String img = null;
            String title = null;
            String content = null;
            String url = null;
            JSONObject jsonObject = new JSONObject(json);
            img = jsonObject.optString("img");
            title = jsonObject.optString("title");
            content = jsonObject.optString("content");
            url = jsonObject.optString("url");
            Activity act = (Activity) context;
            ShareAction action = new ShareAction(act);

            UMWeb web = new UMWeb(url);
            web.setTitle(title);//标题
            web.setThumb(new UMImage(context, img));  //缩略图
            web.setDescription(content);//描述
            action.setDisplayList(SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE)
                    .withMedia(web)
                    .setCallback(umShareListener)
                    .share();
            action.open();

        } catch (Exception e) {

        }


    }

    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {

        }

        @Override
        public void onResult(SHARE_MEDIA platform) {
            Log.d("plat", "platform" + platform);
            Toast.makeText(context, "分享成功", Toast.LENGTH_LONG).show();


        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(context, "分享失败", Toast.LENGTH_LONG).show();

        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(context, "分享取消", Toast.LENGTH_LONG).show();
        }
    };
}
