package com.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.tbruyelle.rxpermissions.RxPermissions;

import rx.functions.Action1;

/**
 * Created by Administrator on 2016/12/7.
 */

public class LocationManager {
    protected static final Object monitor = new Object();
    private LocationListener listener;

    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;

    public AMapLocationClientOption mLocationOption = null;
    //声明定位回调监听器
    public AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if (listener != null) {
                MapLocation location = new MapLocation();
                location.province = aMapLocation.getProvince();
                location.city = aMapLocation.getCity();
                location.district = aMapLocation.getDistrict();
                location.address=aMapLocation.getAddress();
                location.lan = aMapLocation.getLatitude();
                location.lon = aMapLocation.getLongitude();
                listener.onLocationChanged(location);
            }
        }
    };

    public LocationManager(Context context) {
        mLocationOption = new AMapLocationClientOption();
        //初始化定位
        mLocationClient = new AMapLocationClient(context);
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
    }




    public LocationManager setOnceLocation(boolean isOnce) {
        mLocationOption.setOnceLocation(isOnce);
        return this;
    }

    public void startLocation(final Activity activity) {
        RxPermissions rxPermissions = new RxPermissions(activity);
        rxPermissions.request(
                //mTODO:meiyizhi 定位需要的权限
                Manifest.permission.ACCESS_FINE_LOCATION,//位置
                Manifest.permission.ACCESS_COARSE_LOCATION)
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean granted) {
                        if (granted) {
                            //给定位客户端对象设置定位参数
                            mLocationClient.setLocationOption(mLocationOption);
                            //启动定位
                            mLocationClient.startLocation();
                        } else {

                            Toast.makeText(activity,"为了更好的使用体验，请开启定位权限!",Toast.LENGTH_LONG).show();
                        }
                    }
                });




    }

    public void stopLocation() {
        mLocationClient.unRegisterLocationListener(mLocationListener);
        mLocationClient.stopLocation();
        mLocationClient.onDestroy();
    }

    public LocationManager setLocationListener(LocationListener listener) {
        this.listener = listener;
        return this;
    }

    public interface LocationListener {
        void onLocationChanged(MapLocation location);
    }

    public class MapLocation {
        public String province;
        public String city;
        public String district;
        public double lan;
        public double lon;
        public String address;
    }
}
