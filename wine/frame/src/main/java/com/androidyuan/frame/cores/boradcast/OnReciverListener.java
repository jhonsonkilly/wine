package com.androidyuan.frame.cores.boradcast;

import android.content.Intent;

/**
 * Created by wei on 16-3-8.
 */
public interface OnReciverListener {

    void onReciver(String filter, Intent intent);
}
