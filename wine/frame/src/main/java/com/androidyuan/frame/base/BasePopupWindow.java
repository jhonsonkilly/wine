package com.androidyuan.frame.base;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.LocalBroadcastManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;
import com.androidyuan.frame.cores.boradcast.ListBroadCastReciver;

/**
 * Created by wei on 16-4-6.
 * <p>
 * 一个全屏的PopUpwindow
 * <p>
 * 使用showOnParent显示出来
 * <p>
 * 同时可以重写 showOnParent 做部分控件的　动画
 * 整个popupwindow的动画需要在getAnimStypeId 里配置
 */
public abstract class BasePopupWindow extends PopupWindow implements OnClickListener {

    protected View mConentView;
    protected View mParent;

    ListBroadCastReciver broadCastReciver;

    /**
     * @param context
     * @param parent  这个参数用于 popupwindow 跟view建立连接
     */
    public BasePopupWindow(Context context, View parent) {

        this.mParent = parent;
        init(context);
    }

    protected BasePopupWindow() {

    }

    //demo : return R.layout.popup_new_gift;
    protected abstract int getContentId();

    protected View getContent() {

        return mConentView;
    }

    protected void init(Context context) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mConentView = inflater.inflate(getContentId(), null);

        int h = Resources.getSystem().getDisplayMetrics().heightPixels;
        int w = Resources.getSystem().getDisplayMetrics().widthPixels;

        // 设置SelectPicPopupWindow的View
        this.setContentView(mConentView);

        if (isFullScreen()) {
            setWidth(LayoutParams.MATCH_PARENT);
            setHeight(LayoutParams.MATCH_PARENT);
        }
        else {
            setWidth(LayoutParams.WRAP_CONTENT);
            setHeight(LayoutParams.WRAP_CONTENT);
        }
        this.setFocusable(true);
        this.setOutsideTouchable(true);

        // 刷新状态
        this.update();
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(getBackgroundColor());
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        this.setBackgroundDrawable(dw);
        // mPopupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
        // 设置SelectPicPopupWindow弹出窗体动画效果

        if (getAnimStypeId() > 0) {
            this.setAnimationStyle(getAnimStypeId());
        }

        initView(mConentView);

    }

    protected boolean isHorScreen() {

        return false;
    }

    protected boolean isFullScreen() {

        return true;
    }

    /**
     * 子类通过重写这个方法来完成背景色的配置
     * <p>
     * 可以传递transparent  0x00000000 表示透明背景
     */

    protected int getBackgroundColor() {

        return 0x50000000;
    }


    //demo : R.style.anim_popup_dir;
    protected int getAnimStypeId() {

        return -1;
    }

    protected int getColor(int id) {

        if (getContentView() != null) {
            return getContentView().getResources().getColor(id);
        }
        else {
            return 0x00000000;
        }
    }

    protected Context getContext() {

        return getContentView().getContext();
    }


    protected abstract void initView(View conentView);


    public void show() {

        mParent.post(new Runnable() {
            @Override
            public void run() {

                showAtLocation(mParent, Gravity.CENTER, 0, 0);
            }
        });

    }


    protected Intent createFilterIntent(String filter) {

        return new Intent(filter);
    }

    protected void sendBroadCastFilter(String filter) {

        sendBroadCastIntent(createFilterIntent(filter));
    }

    protected void sendBroadCastIntent(Intent intent) {

        LocalBroadcastManager.getInstance(getContext()).sendBroadcast(intent);
    }


    @Override
    public void dismiss() {

        super.dismiss();

    }
}
