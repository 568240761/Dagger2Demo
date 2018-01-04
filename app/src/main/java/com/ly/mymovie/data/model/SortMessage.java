package com.ly.mymovie.data.model;

import com.ly.mymovie.data.model.bean.SortBean;

import java.util.List;

/**
 * Created by LanYang on 2017/12/24.
 * Email:568240761@qq.com
 */

public class SortMessage {
   private List<SortBean> sort;

    public List<SortBean> getSortList() {
        return sort;
    }

    public void setSortList(List<SortBean> sortList) {
        this.sort = sortList;
    }
}
