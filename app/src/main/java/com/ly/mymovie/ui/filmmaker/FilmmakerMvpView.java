package com.ly.mymovie.ui.filmmaker;

import com.ly.mymovie.data.model.FilmmakerMessage;
import com.ly.mymovie.ui.base.MvpView;

/**
 * Created by LanYang on 2017/12/23.
 * Email:568240761@qq.com
 */

public interface FilmmakerMvpView extends MvpView {
    void showError();
    void showFilmmakerMessage(FilmmakerMessage message);
}
