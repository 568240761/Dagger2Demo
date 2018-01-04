package com.ly.mymovie.data.location;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

/**
 * Created by LanYang on 2017/12/29.
 * Email:568240761@qq.com
 */

public class MyLocationListener implements LocationListener {

    /**
     * 定位改变监听
     *
     * @param location
     */
    @Override
    public void onLocationChanged(Location location) {

    }

    /**
     * 定位状态监听
     *
     * @param provider
     * @param status
     * @param extras
     */
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    /**
     * 定位状态可用监听
     *
     * @param provider
     */
    @Override
    public void onProviderEnabled(String provider) {
    }

    /**
     * 定位状态不可用监听
     *
     * @param provider
     */
    @Override
    public void onProviderDisabled(String provider) {
    }
}
