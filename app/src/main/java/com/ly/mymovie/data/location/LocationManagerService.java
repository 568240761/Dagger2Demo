package com.ly.mymovie.data.location;

import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;

import com.ly.mymovie.util.Constant;
import com.ly.mymovie.util.log.LogUtil;
import com.ly.mymovie.util.PermissionUtil;

import javax.inject.Inject;

/**
 * Created by LanYang on 2017/12/28.
 * Email:568240761@qq.com
 */

public class LocationManagerService {
    private static final long REFRESH_TIME = 5000L;
    private static final float DISTANCE = 0.0f;
    private String mProvider;
    private MyLocationListener mLocationListener;

    @Inject
    public LocationManagerService() {
    }

    private LocationManager getLocationManager(Context context){
        return (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    }

    /**
     * 判断GPS是否开启，GPS或者AGPS开启一个就认为是开启的
     *
     * @return true 表示开启
     */
    public boolean isOPen(Context context) {
        LocationManager locationManager = getLocationManager(context);
        boolean gps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean network = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        if (gps || network) {
            return true;
        }
        return false;
    }

    /**
     * GPS定位方式
     */
    private Location getGPSLocation(Context context) {

        if(PermissionUtil.checkSelfPermission((Activity) context, Constant.PERMISSION_LOCATION)){
            return null;
        }

        mProvider = LocationManager.GPS_PROVIDER;
        //获取最后的GPS定位信息，如果是第一次打开，一般会拿不到定位信息，一般可以请求监听，在有效的时间范围可以获取定位信息
        Location location = getLocationManager(context).getLastKnownLocation(mProvider);

        return location;
    }

    /**
     * network获取定位方式
     */
    private Location getNetWorkLocation(Context context) {

        if(PermissionUtil.checkSelfPermission((Activity) context, Constant.PERMISSION_LOCATION)){
            return null;
        }

        mProvider = LocationManager.NETWORK_PROVIDER;
        //获取最后的network定位信息
        Location location = getLocationManager(context).getLastKnownLocation(mProvider);

        return location;
    }

    /**
     * 获取最好的定位方式
     */
    public Location getBestLocation(Context context) {
        Criteria criteria = new Criteria();
        //设置经纬度的精准度
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        //设置不需要获取海拔方向数据
        criteria.setAltitudeRequired(false);
        //设置是否需要获得方向信息
        criteria.setBearingRequired(false);
        //设置允许产生资费,如流量
        criteria.setCostAllowed(true);

        LocationManager locationManager = getLocationManager(context);

        mProvider = locationManager.getBestProvider(criteria, true);
        String log = mProvider == null?"null":mProvider.toString();
        LogUtil.getSingleton().d("定位方式:"+log);


        if(PermissionUtil.checkSelfPermission((Activity) context, Constant.PERMISSION_LOCATION)){
            return null;
        }

        Location location = locationManager.getLastKnownLocation(mProvider);

        return location;
    }

    public void addLocationListener(Context context,final ILocationSuccess success) {
        if(PermissionUtil.checkSelfPermission((Activity) context, Constant.PERMISSION_LOCATION)){
            return;
        }
        mLocationListener = new MyLocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                super.onLocationChanged(location);
                success.success(location);
            }
        };
        getLocationManager(context).requestLocationUpdates(mProvider, REFRESH_TIME, DISTANCE, mLocationListener);
    }

    public void removeLocationListener(Context context) {
        if (mLocationListener != null) {
            getLocationManager(context).removeUpdates(mLocationListener);
            mLocationListener = null;
        }
    }
}
