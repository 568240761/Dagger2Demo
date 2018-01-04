package com.ly.mymovie.ui.sort;

import com.ly.mymovie.data.model.Message;
import com.ly.mymovie.ui.base.MvpView;

/**
 * Created by LanYang on 2017/12/24.
 * Email:568240761@qq.com
 */

public interface SortMvpView extends MvpView {
    void showErrorView();
    void notifyAdapter(Message message);
}
