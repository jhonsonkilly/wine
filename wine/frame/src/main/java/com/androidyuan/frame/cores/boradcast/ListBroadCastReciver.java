package com.androidyuan.frame.cores.boradcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;

/**
 * Created by wei on 16-3-8.
 * <p>
 * !!!! 记得 调用unRegist方法  否则后果很严重
 */
public class ListBroadCastReciver extends BroadcastReceiver {


    IntentFilter filter;
    OnReciverListener onReciverListener;
    LocalBroadcastManager localBroadcastManager;

    public ListBroadCastReciver() {

    }

    public ListBroadCastReciver(Context con, OnReciverListener ll) {

        onReciverListener = ll;
        filter = new IntentFilter();
        localBroadcastManager = LocalBroadcastManager.getInstance(con);
    }

    public static ListBroadCastReciver registerContext(Context con, OnReciverListener ll) {

        ListBroadCastReciver reciver = new ListBroadCastReciver(con, ll);


        return reciver;
    }

    public void putFilter(String action) {

        filter.addAction(action);
    }

    public void commit() {

        localBroadcastManager.registerReceiver(this, filter);
    }

    public void unRegister() {

        localBroadcastManager.unregisterReceiver(this);
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        onReciverListener.onReciver(intent.getAction(), intent);
    }
}
