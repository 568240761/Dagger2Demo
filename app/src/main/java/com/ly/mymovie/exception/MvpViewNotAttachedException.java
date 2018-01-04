package com.ly.mymovie.exception;

/**
 * Created by LanYang on 2017/12/07.
 * Email:568240761@qq.com
 * Presenter未绑定View时，抛出该异常
 */

public class MvpViewNotAttachedException extends RuntimeException{
    public MvpViewNotAttachedException() {
        super("在处理数据之前，先调用Presenter.attachView(MvpView view)方法");
    }
}
