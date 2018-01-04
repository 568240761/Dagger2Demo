package com.ly.mymovie.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StringDef;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by LanYang on 2017/12/24.
 * Email:568240761@qq.com
 * 资源文件夹asset管理工具
 */

public class AssetsUtil {
    public final static String FILE_JSON_SORT = "sort.json";
    @StringDef(FILE_JSON_SORT)
    public @interface AssetsFile{}

    public static String openFile(@AssetsFile String file, @NonNull Context context){
        String result = null;
        try {
            InputStream is = context.getAssets().open(file);
            int length = is.available();
            byte[]  buffer = new byte[length];
            is.read(buffer);
            result = new String(buffer, "utf8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
