package com.ly.mymovie.util;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;

import com.ly.mymovie.helper.MyDialog;

/**
 * Created by LanYang on 2017/12/30.
 * Email:568240761@qq.com
 * 权限工具
 */

public class PermissionUtil {

    public static boolean checkSelfPermission(@NonNull Activity activity, String[] permissions) {
        if (Build.VERSION.SDK_INT < 23)
            return false;
        boolean isGranted = false;
        for (String permission : permissions) {
            boolean flag = ActivityCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED;
            isGranted = isGranted || flag;
        }
        return isGranted;
    }

    public static void checkSelfPermission(@NonNull final Activity activity, final String[] permissions, String message,@Constant.PermissionRequestCode final int requestCode) {
        if (Build.VERSION.SDK_INT < 23)
            return;
        boolean isGranted = false;
        for (String permission : permissions) {
            boolean flag = ActivityCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED;
            isGranted = isGranted || flag;
        }
        if (isGranted) {
            isGranted = false;
            for (String permission : permissions) {
                boolean flag = ActivityCompat.shouldShowRequestPermissionRationale(activity, permission);
                isGranted = isGranted || flag;
            }
            if (isGranted) {
                MyDialog.showDialog(activity, message, new MyDialog.IPositiveOnClick() {
                    @Override
                    public void click() {
                        requestPermissions(activity, permissions, requestCode);
                    }
                });
            } else {
                requestPermissions(activity, permissions, requestCode);
            }
        }
    }

    private static void requestPermissions(@NonNull Activity activity, String[] permissions, int requestCode) {
        ActivityCompat.requestPermissions(activity, permissions, requestCode);
    }

    public static boolean verifyPermissions(int[] grantResults) {
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }
}
