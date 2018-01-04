package com.ly.mymovie.data;

import com.ly.mymovie.data.image.ImageManagerService;
import com.ly.mymovie.data.location.LocationManagerService;
import com.ly.mymovie.data.network.Request;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by LanYang on 2017/12/12.
 * Email:568240761@qq.com
 */
@Singleton
public class DataManager {
    private final Request mRequest;
    private final ImageManagerService mImageManagerService;
    private final LocationManagerService mLocationManagerService;

    public Request getRequest() {
        return mRequest;
    }

    public ImageManagerService getImageManagerService() {
        return mImageManagerService;
    }

    public LocationManagerService getLocationManagerService() {
        return mLocationManagerService;
    }

    @Inject
    public DataManager(Request request, ImageManagerService imageManagerService, LocationManagerService locationManagerService) {
        mRequest = request;
        mImageManagerService = imageManagerService;
        mLocationManagerService = locationManagerService;
    }

}
