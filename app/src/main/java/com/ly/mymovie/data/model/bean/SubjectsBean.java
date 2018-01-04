package com.ly.mymovie.data.model.bean;

import java.util.List;

/**
 * Created by LanYang on 2017/12/12.
 * Email:568240761@qq.com
 * 关于电影信息的实体类
 */

public class SubjectsBean {
    /**
     * rating : {"max":10,"average":9.1,"stars":"45","min":0}
     * genres : ["喜剧","动画","冒险"]
     * title : 寻梦环游记
     * casts : [{"alt":"https://movie.douban.com/celebrity/1370411/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1489594517.9.webp","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1489594517.9.webp","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1489594517.9.webp"},"name":"安东尼·冈萨雷斯","id":"1370411"},{"alt":"https://movie.douban.com/celebrity/1041009/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1510634028.69.webp","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1510634028.69.webp","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1510634028.69.webp"},"name":"盖尔·加西亚·贝纳尔","id":"1041009"},{"alt":"https://movie.douban.com/celebrity/1036383/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1416762292.89.webp","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1416762292.89.webp","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1416762292.89.webp"},"name":"本杰明·布拉特","id":"1036383"}]
     * collect_count : 253459
     * original_title : Coco
     * subtype : movie
     * directors : [{"alt":"https://movie.douban.com/celebrity/1022678/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p13830.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p13830.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p13830.webp"},"name":"李·昂克里奇","id":"1022678"},{"alt":"https://movie.douban.com/celebrity/1370425/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1497195148.21.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1497195148.21.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1497195148.21.webp"},"name":"阿德里安·莫利纳","id":"1370425"}]
     * year : 2017
     * images : {"small":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2503997609.webp","large":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2503997609.webp","medium":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2503997609.webp"}
     * alt : https://movie.douban.com/subject/20495023/
     * id : 20495023
     */

    private RatingBean rating;
    private String title;
    private int collect_count;
    private String original_title;
    private String subtype;
    private String year;
    private ImagesBean images;
    private String alt;
    private String id;
    private List<String> genres;
    private List<CastsBean> casts;
    private List<DirectorsBean> directors;

    public RatingBean getRating() {
        return rating;
    }

    public void setRating(RatingBean rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCollect_count() {
        return collect_count;
    }

    public void setCollect_count(int collect_count) {
        this.collect_count = collect_count;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public ImagesBean getImages() {
        return images;
    }

    public void setImages(ImagesBean images) {
        this.images = images;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public List<CastsBean> getCasts() {
        return casts;
    }

    public void setCasts(List<CastsBean> casts) {
        this.casts = casts;
    }

    public List<DirectorsBean> getDirectors() {
        return directors;
    }

    public void setDirectors(List<DirectorsBean> directors) {
        this.directors = directors;
    }
}
