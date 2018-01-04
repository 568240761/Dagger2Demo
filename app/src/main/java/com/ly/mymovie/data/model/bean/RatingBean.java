package com.ly.mymovie.data.model.bean;

/**
 * Created by LanYang on 2017/12/12.
 * Email:568240761@qq.com
 * 电影的评价实体类
 */

public class RatingBean {
    /**
     * max : 10
     * average : 9.1
     * stars : 45
     * min : 0
     */

    private int max;
    private double average;
    private String stars;
    private int min;

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }
}
