package com.ly.mymovie.data.model.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by LanYang on 2017/12/16.
 * Email:568240761@qq.com
 */

public class SubjectsBox {
    /**
     * box : 18303000
     * new : false
     * rank : 1
     * subject : {"rating":{"max":10,"average":9.1,"stars":"45","min":0},"genres":["喜剧","动画","冒险"],"title":"寻梦环游记","casts":[{"alt":"https://movie.douban.com/celebrity/1370411/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1489594517.9.webp","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1489594517.9.webp","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1489594517.9.webp"},"name":"安东尼·冈萨雷斯","id":"1370411"},{"alt":"https://movie.douban.com/celebrity/1041009/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1510634028.69.webp","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1510634028.69.webp","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1510634028.69.webp"},"name":"盖尔·加西亚·贝纳尔","id":"1041009"},{"alt":"https://movie.douban.com/celebrity/1036383/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1416762292.89.webp","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1416762292.89.webp","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1416762292.89.webp"},"name":"本杰明·布拉特","id":"1036383"}],"collect_count":270444,"original_title":"Coco","subtype":"movie","directors":[{"alt":"https://movie.douban.com/celebrity/1022678/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p13830.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p13830.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p13830.webp"},"name":"李·昂克里奇","id":"1022678"},{"alt":"https://movie.douban.com/celebrity/1370425/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1497195148.21.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1497195148.21.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1497195148.21.webp"},"name":"阿德里安·莫利纳","id":"1370425"}],"year":"2017","images":{"small":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2503997609.webp","large":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2503997609.webp","medium":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2503997609.webp"},"alt":"https://movie.douban.com/subject/20495023/","id":"20495023"}
     */

    private int box;
    @SerializedName("new")
    private boolean newX;
    private int rank;
    private SubjectsBean subject;

    public int getBox() {
        return box;
    }

    public void setBox(int box) {
        this.box = box;
    }

    public boolean isNewX() {
        return newX;
    }

    public void setNewX(boolean newX) {
        this.newX = newX;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public SubjectsBean getSubject() {
        return subject;
    }

    public void setSubject(SubjectsBean subject) {
        this.subject = subject;
    }
}
