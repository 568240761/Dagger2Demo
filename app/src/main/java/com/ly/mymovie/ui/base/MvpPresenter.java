package com.ly.mymovie.ui.base;

/**
 * Created by LanYang on 2017/12/07.
 * Email:568240761@qq.com
 * 任何扮演逻辑处理的类必须实现该接口或继承{@link BasePresenter}
 */
public interface MvpPresenter<V extends MvpView> {

    void attachView(V mvpView);

    void detachView();
}
