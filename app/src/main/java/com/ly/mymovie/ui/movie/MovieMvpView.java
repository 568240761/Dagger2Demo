package com.ly.mymovie.ui.movie;

import com.ly.mymovie.data.model.MovieMessage;
import com.ly.mymovie.ui.base.MvpView;

/**
 * Created by LanYang on 2017/12/16.
 * Email:568240761@qq.com
 */

public interface MovieMvpView extends MvpView {
    void showError();
    void showMovieMessage(MovieMessage movieBean);
}
