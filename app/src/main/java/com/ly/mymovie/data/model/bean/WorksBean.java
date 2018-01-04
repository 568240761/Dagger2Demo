package com.ly.mymovie.data.model.bean;

import java.util.List;

/**
 * Created by LanYang on 2017/12/23.
 * Email:568240761@qq.com
 */

public class WorksBean {
    /**
     * roles : ["演员"]
     * subject : {"rating":{"max":10,"average":9.1,"stars":"45","min":0},"genres":["剧情"],"title":"危险心灵","casts":[{"alt":null,"avatars":null,"name":"黄河","id":null},{"alt":null,"avatars":null,"name":"桂纶镁","id":null},{"alt":"https://movie.douban.com/celebrity/1316681/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p38274.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p38274.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p38274.webp"},"name":"李烈","id":"1316681"}],"collect_count":1935,"original_title":"危險心靈","subtype":"tv","directors":[{"alt":"https://movie.douban.com/celebrity/1160765/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p17842.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p17842.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p17842.webp"},"name":"易智言","id":"1160765"},{"alt":"https://movie.douban.com/celebrity/1319263/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p46338.webp","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p46338.webp","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p46338.webp"},"name":"杨雅喆","id":"1319263"}],"year":"2006","images":{"small":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2283338286.webp","large":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2283338286.webp","medium":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2283338286.webp"},"alt":"https://movie.douban.com/subject/3018513/","id":"3018513"}
     */
    private SubjectsBean subject;
    private List<String> roles;

    public SubjectsBean getSubject() {
        return subject;
    }

    public void setSubject(SubjectsBean subject) {
        this.subject = subject;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
