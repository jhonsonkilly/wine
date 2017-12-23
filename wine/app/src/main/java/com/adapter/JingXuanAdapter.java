package com.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.androidyuan.frame.cores.utils.image.FrescoUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.model.JingXuanModel;
import com.utils.Urls;

import java.util.List;

import zjw.wine.R;

/**
 * Created by mac on 2017/11/20.
 */

public class JingXuanAdapter extends BaseAdapter {

    Context context;
    List<JingXuanModel.Data> list;

    SimpleDraweeView img;

    RecyclerView recycler;

    public JingXuanAdapter(Context context, List<JingXuanModel.Data> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        try {
            if (convertView == null) {
                convertView = View.inflate(context, R.layout.item_listview, null);
            }

            img = (SimpleDraweeView) convertView.findViewById(R.id.img_banner);
            recycler = (RecyclerView) convertView.findViewById(R.id.img_horRecycle2);

            FrescoUtils.displayUrl(img, Urls.getBaseUrl()+"/em/es_choice/"+list.get(position).img);


            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            recycler.setLayoutManager(linearLayoutManager);

            JingXuanItemAdapter adapter = new JingXuanItemAdapter(list.get(position).goods,context);
            recycler.setAdapter(adapter);
            return convertView;

        } catch (Exception e) {
            return null;
        }
    }
}
