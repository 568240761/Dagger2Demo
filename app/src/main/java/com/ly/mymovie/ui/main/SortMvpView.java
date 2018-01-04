package com.ly.mymovie.ui.main;

import com.ly.mymovie.data.model.bean.SortBean;
import com.ly.mymovie.ui.base.MvpView;

import java.util.List;

/**
 * Created by LanYang on 2017/12/25.
 * Email:568240761@qq.com
 */

public interface SortMvpView extends MvpView {
    void notifyAdapter(List<SortBean> sortBeanList);
}
