package com.ly.mymovie.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import com.ly.mymovie.R;
import com.ly.mymovie.data.model.ImageMessage;
import com.ly.mymovie.data.model.bean.AvatarsBean;
import com.ly.mymovie.data.model.bean.AvatarsBeanX;
import com.ly.mymovie.data.model.bean.CastsBean;
import com.ly.mymovie.data.model.bean.DirectorsBean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by LanYang on 2017/12/13.
 * Email:568240761@qq.com
 */

public class TextUtil {

    /**
     * 从资源文件中获取字符串
     *
     * @param context
     * @param id
     * @return
     */
    public static String getResources(@NonNull Context context, @StringRes int id) {
        return context.getResources().getString(id);
    }
    /**
     * 将多个字符串连接起来，以空格隔开
     *
     * @param list
     * @return
     */
    public static String splicing(List<? extends Object> list) {

        if (list == null)
            return "";
        StringBuffer stringBuffer = new StringBuffer();
        for (Object object : list) {
            stringBuffer.append(object.toString()).append(" ");
        }
        return stringBuffer.toString();
    }

    public static String splicing(Set<? extends Object> set) {
        if (set == null)
            return "";
        StringBuffer stringBuffer = new StringBuffer();
        Iterator it = set.iterator();
        while(it.hasNext()){
            Object object = it.next();
            stringBuffer.append(object.toString()).append(" ");
        }
        return stringBuffer.toString();
    }

    /**
     * 格式化金额，以万为单位
     *
     * @param money
     * @return
     */
    public static String formatMoney(int money) {
        if (money == 0) {
            return "";
        } else {
            String str = Integer.toString(money);
            int length = str.length();
            if (length <= 4) {
                return str;
            } else {
                return str.substring(0, length - 4) + "万";
            }
        }
    }

    public static String formatPlot(String content){
        content = content.replace("©豆瓣","");
        return content;
    }

    public static String isEmpty(String str,@NonNull Context context){
        if(str.isEmpty()){
            return context.getResources().getString(R.string.nothing);
        }
        return str;
    }

    public static List<ImageMessage> spliceList(List<DirectorsBean> directorsBeanList, List<CastsBean> castsBeanList) {
        List<ImageMessage> list = new ArrayList<>();
        List<String> nameList = new ArrayList<>();

        for (DirectorsBean directorsBean : directorsBeanList) {
            ImageMessage imageMessage = new ImageMessage();
            imageMessage.setName(directorsBean.getName());
            nameList.add(directorsBean.getName());
            AvatarsBeanX avatarsBeanX = directorsBean.getAvatars();
            imageMessage.setUrl(avatarsBeanX == null?"":avatarsBeanX.getSmall());
            imageMessage.setId(directorsBean.getId());
            list.add(imageMessage);
        }

        for (CastsBean castsBean : castsBeanList) {
            if (!nameList.contains(castsBean.getName())) {
                ImageMessage imageMessage = new ImageMessage();
                imageMessage.setName(castsBean.getName());
                AvatarsBean avatarsBean = castsBean.getAvatars();
                imageMessage.setUrl(avatarsBean==null?"":avatarsBean.getSmall());
                imageMessage.setId(castsBean.getId());
                list.add(imageMessage);
            }
        }
        return list;
    }
}
