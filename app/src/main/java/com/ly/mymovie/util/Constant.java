package com.ly.mymovie.util;

import android.Manifest;
import android.support.annotation.IntDef;

/**
 * Created by LanYang on 2017/12/30.
 * Email:568240761@qq.com
 * 常量类
 */

public class Constant {
    public static final int REQUEST_CODE_PERMISSION_LOCATION = 0;
    public static final String[] PERMISSION_LOCATION = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};

    @IntDef(REQUEST_CODE_PERMISSION_LOCATION)
    public @interface PermissionRequestCode {
    }

    /**
     * 滚动折叠
     */
    public static final int TOOLBAR_ROLL = 0;
    /**
     * 滚动也不折叠
     */
    public static final int TOOLBAR_NOT_ROLL = TOOLBAR_ROLL + 1;
}
