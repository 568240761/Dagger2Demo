package com.ly.mymovie.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

/**
 * Created by LanYang on 2017/12/27.
 * Email:568240761@qq.com
 */

public class ToastUtil {
    private static Toast mToast;

    public static void makeText(@NonNull Context context, String string) {
        if (mToast == null) {
            mToast = Toast.makeText(context, string, Toast.LENGTH_LONG);
        } else {
            mToast.setText(string);
        }
        mToast.show();
    }
}
