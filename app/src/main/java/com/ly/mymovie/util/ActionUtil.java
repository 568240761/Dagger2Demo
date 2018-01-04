package com.ly.mymovie.util;

import android.content.Context;
import android.content.Intent;

import com.ly.mymovie.annotation.ActivityContext;

/**
 * Created by LanYang on 2017/12/16.
 * Email:568240761@qq.com
 * 页面跳转
 */

public class ActionUtil {
    public final static String INTENT_STRING = "msg_string";

    public static void gotoActivity(@ActivityContext Context context, Class object, String msg) {
        Intent intent = new Intent(context, object);
        if (msg != null) {
            intent.putExtra(INTENT_STRING, msg);
        }
        context.startActivity(intent);
    }
}
