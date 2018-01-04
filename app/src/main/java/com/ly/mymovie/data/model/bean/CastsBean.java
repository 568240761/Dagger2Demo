package com.ly.mymovie.data.model.bean;

/**
 * Created by LanYang on 2017/12/12.
 * Email:568240761@qq.com
 * 演员实体类
 */

public class CastsBean {
    /**
     * alt : https://movie.douban.com/celebrity/1370411/
     * avatars : {"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1489594517.9.webp","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1489594517.9.webp","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1489594517.9.webp"}
     * name : 安东尼·冈萨雷斯
     * id : 1370411
     */

    private String alt;
    private AvatarsBean avatars;
    private String name;
    private String id;

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public AvatarsBean getAvatars() {
        return avatars;
    }

    public void setAvatars(AvatarsBean avatars) {
        this.avatars = avatars;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return this.name == null ? "" : this.name;
    }
}
