package com.ly.mymovie.ui.main;

import com.ly.mymovie.data.model.Message;
import com.ly.mymovie.ui.base.MvpView;

/**
 * Created by LanYang on 2017/12/12.
 * Email:568240761@qq.com
 */

public interface ComingMvpView extends MvpView {
    void notifyAdapter(Message message);
    void showErrorView();
}
