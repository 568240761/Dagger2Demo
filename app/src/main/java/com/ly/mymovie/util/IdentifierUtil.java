package com.ly.mymovie.util;

import android.content.Context;
import android.support.annotation.NonNull;

/**
 * Created by LanYang on 2017/12/24.
 * Email:568240761@qq.com
 * 根据资源名称获取文件ID
 */

public class IdentifierUtil {
    private static final String RES_ID = "id";
    private static final String RES_STRING = "string";
    private static final String RES_DRAWABLE = "drawable";
    private static final String RES_Mipmap = "mipmap";
    private static final String RES_LAYOUT = "layout";
    private static final String RES_STYLE = "style";
    private static final String RES_COLOR = "color";
    private static final String RES_DIMEN = "dimen";
    private static final String RES_ANIM = "anim";
    private static final String RES_MENU = "menu";

    /**
     * 获取资源文件的id
     * @param context
     * @param resName
     * @return
     */
    public static int getId(@NonNull Context context, String resName){
        return getResId(context,resName,RES_ID);
    }

    /**
     * 获取资源文件string的id
     * @param context
     * @param resName
     * @return
     */
    public static int getStringId(@NonNull Context context,String resName){
        return getResId(context,resName,RES_STRING);
    }

    /**
     * 获取资源文件drawable的id
     * @param context
     * @param resName
     * @return
     */
    public static int getDrawableId(@NonNull Context context,String resName){
        return getResId(context,resName,RES_DRAWABLE);
    }

    public static int getMipmapId(Context context,String resName){
        return getResId(context,resName,RES_Mipmap);
    }

    /**
     * 获取资源文件layout的id
     * @param context
     * @param resName
     * @return
     */
    public static int getLayoutId(@NonNull Context context,String resName){
        return getResId(context,resName,RES_LAYOUT);
    }

    /**
     * 获取资源文件style的id
     * @param context
     * @param resName
     * @return
     */
    public static int getStyleId(@NonNull Context context,String resName){
        return getResId(context,resName,RES_STYLE);
    }

    /**
     * 获取资源文件color的id
     * @param context
     * @param resName
     * @return
     */
    public static int getColorId(@NonNull Context context,String resName){
        return getResId(context,resName,RES_COLOR);
    }

    /**
     * 获取资源文件dimen的id
     * @param context
     * @param resName
     * @return
     */
    public static int getDimenId(@NonNull Context context,String resName){
        return getResId(context,resName,RES_DIMEN);
    }

    /**
     * 获取资源文件ainm的id
     * @param context
     * @param resName
     * @return
     */
    public static int getAnimId(@NonNull Context context,String resName){
        return getResId(context,resName,RES_ANIM);
    }

    /**
     * 获取资源文件menu的id
     */
    public static int getMenuId(@NonNull Context context,String resName){
        return getResId(context,resName,RES_MENU);
    }

    /**
     * 获取资源文件ID
     * @param context
     * @param resName
     * @param defType
     * @return
     */
    public static int getResId(@NonNull Context context,String resName,String defType){
        return context.getResources().getIdentifier(resName, defType, context.getPackageName());
    }
}
