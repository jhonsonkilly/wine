package com.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import zjw.wine.R;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;


import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Pattern;

/**
 * Created by lxs on 2016/5/11.
 */
public class UiUtils {

    /**
     * @param activity
     * @param title
     * @see [自定义标题栏]
     */
    public static RelativeLayout getTitleBar(final Activity activity, String title) {
        activity.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        activity.setContentView(R.layout.layout_title);
        activity.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
                R.layout.layout_title);
        RelativeLayout layout_title = (RelativeLayout) activity.findViewById(R.id.layout_title);
        TextView textView = (TextView) activity.findViewById(R.id.tv_title);
        textView.setText(title);
        ImageView back = (ImageView) activity.findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                activity.finish();
            }
        });
        return layout_title;
    }

    /**
     * double格式化(保留两位小数___带¥符号)
     *
     * @param dou
     * @return
     */
    public static String getMoneyDouble(double dou) {
        return "¥" + getDoubleForDouble(dou);
    }

    /**
     * double格式化(保留两位小数)
     *
     * @param dou
     * @return
     */
    public static String getDoubleForDouble(double dou) {
        DecimalFormat formatter = new DecimalFormat();
        formatter.applyPattern("#0.00");//格式代码，".00"代表保留2位小数，是0的输出0
        return formatter.format(dou);
    }

    public static String getDoubleForInteger(double dou) {
        DecimalFormat formatter = new DecimalFormat();
        formatter.applyPattern("#0");//格式代码，".00"代表保留2位小数，是0的输出0
        return formatter.format(dou);
    }

    public static String getDoubleForDouble(String money) {
        if (!TextUtils.isEmpty(money)) {
            DecimalFormat formatter = new DecimalFormat();
            formatter.applyPattern("#0.00");
            return formatter.format(new BigDecimal(money));
        } else {
            return "0.00";
        }
    }

    public static boolean isIdentityCard(String card) {
        if (isEmpty(card)) {
            return false;
        } else if (card.length() == 18) {
            Pattern pattern = Pattern.compile("^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$");
            return pattern.matcher(card).matches();
        } else if (card.length() == 15) {
            Pattern pattern = Pattern.compile("^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$");
            return pattern.matcher(card).matches();
        }
        return false;
    }

    public static boolean isEmpty(String input) {
        if (input == null || "".equals(input) || "null".equals(input))
            return true;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }

    /**
     * 毫秒数转化时间格式
     *
     * @param args
     * @return
     */
    public static String getTiem(Long args) {
        Date dat = new Date(args);
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(dat);
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy.MM.dd");
        String sb = format.format(gc.getTime());
        System.out.println(sb);
        return sb;
    }

    /**
     * 字符大小 "¥"符号跟小数点后两位缩小
     * @param context
     * @param dou
     * @return
     */


    /**
     * 字符大小 "¥"符号跟小数点后两位缩小
     * @param context
     * @param content
     * @return
     */
//    public static SpannableString getGiftName(Context context, String content) {
//        SpannableString styledText = new SpannableString(content);
//        styledText.setSpan(new TextAppearanceSpan(context, R.style.text_gift_name), content.indexOf("//[") + 1, content.indexOf("//]") + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        styledText.setSpan(new TextAppearanceSpan(context, R.style.text_moeny_sum), content.indexOf("//]") + 2, content.length() - 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        return styledText;
//    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     *
     * @param context
     * @param dpValue
     * @return
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    /**
     * 设置文本图片混合
     * @param tv_show
     * @param context
     * @param url
     * @param content
     */


    /**
     * 设置文本图片混合
     *
     * @param tv_show
     * @param context
     * @param url
     * @param content
     */


    /**
     * 设置文本图片混合
     *
     * @param tv_show
     * @param context
     * @param res
     * @param content
     */


    /**
     * 设置文本图片混合
     *
     * @param tv_show
     * @param context
     * @param res
     */


    /**
     * 文本混合
     *
     * @param context
     * @param text
     * @return
     */


    /**
     * 设置消息类数量角标
     *
     * @param number
     * @param view
     */
    public static void setCareNum(long number, int type, TextView view) {
        if (null == view) {
            return;
        }
        view.setBackgroundResource(R.drawable.radius_theme_7);
        view.setTextSize(PxUtils.pxTodip(26));

        view.setGravity(Gravity.CENTER);
        view.setPadding(PxUtils.dipTopx(2), 0, PxUtils.dipTopx(2), 0);
        view.setMinWidth(PxUtils.dipTopx(16));
        ViewGroup.LayoutParams para = view.getLayoutParams();
        para.height = PxUtils.dipTopx(16);
        if (number > 0) {
            if (number > 99) {
                view.setText(R.string.othermore);
                para.width = PxUtils.dipTopx(20);
            } else {
                view.setText(number + "");
                if (number > 9) {
                    para.width = PxUtils.dipTopx(16);
                    if (type == 1) {//消息类最大为9+
                        view.setText("9+");
                    }
                } else {
                    para.width = PxUtils.dipTopx(16);
                }
            }
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }
        view.setLayoutParams(para);
    }
}
