package com.ly.mymovie.data.model.bean;

/**
 * Created by LanYang on 2017/12/12.
 * Email:568240761@qq.com
 * 导演图片地址的实体类
 */

public class AvatarsBeanX {
    /**
     * small : https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p13830.webp
     * large : https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p13830.webp
     * medium : https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p13830.webp
     */

    private String small;
    private String large;
    private String medium;

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }
}
