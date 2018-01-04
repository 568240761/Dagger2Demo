package com.ly.mymovie.data.model.bean;

/**
 * Created by LanYang on 2017/12/12.
 * Email:568240761@qq.com
 * 电影图片地址的实体类
 */

public class ImagesBean {
    /**
     * small : https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2503997609.webp
     * large : https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2503997609.webp
     * medium : https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2503997609.webp
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
