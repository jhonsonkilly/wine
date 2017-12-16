package com.widget;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidyuan.frame.base.activity.BaseCommActivity;

import zjw.wine.R;

/**
 * <p>Copyright:Copyright(c) 2016</p>
 * <p>Company:上海来伊份电子商务有限公司</p>
 * <p>包名:com.widget</p>
 * <p>文件名:wine</p>
 * <p>类更新历史信息</p>
 *
 * @todo <a href="mailto:zhoujiawei@laiyifen.com">vernal(周佳伟)</a>
 */
public class ToolBar extends FrameLayout implements View.OnClickListener {
    Context context;
    private TextView title;
    private ImageView back_img;
    private ImageView shop_cart;

    public ToolBar(@NonNull Context context) {
        super(context);
        initView(context);
    }

    public ToolBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ToolBar(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public void initView(Context context) {
        this.context = context;
        View view = View.inflate(context, R.layout.toolbar_view, null);
        title = (TextView) view.findViewById(R.id.title);
        back_img = (ImageView)view.findViewById(R.id.back);
        back_img.setOnClickListener(this);
        shop_cart = (ImageView)view.findViewById(R.id.shopCart);
        addView(view);
    }

    public void setTitle(String content) {
        title.setText(content);
    }

    public void hideBack() {
        back_img.setVisibility(GONE);
    }

    public ImageView showShopCart() {
        shop_cart.setVisibility(VISIBLE);
        return shop_cart;
    }


    @Override
    public void onClick(View view) {
        BaseCommActivity activity = (BaseCommActivity) context;
        activity.finish();
    }
}
