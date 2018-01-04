package com.ly.mymovie.data.model.bean;

/**
 * Created by LanYang on 2017/12/12.
 * Email:568240761@qq.com
 * 导演实体类
 */

public class DirectorsBean {
    /**
     * alt : https://movie.douban.com/celebrity/1022678/
     * avatars : {"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p13830.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p13830.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p13830.webp"}
     * name : 李·昂克里奇
     * id : 1022678
     */

    private String alt;
    private AvatarsBeanX avatars;
    private String name;
    private String id;

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public AvatarsBeanX getAvatars() {
        return avatars;
    }

    public void setAvatars(AvatarsBeanX avatars) {
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
