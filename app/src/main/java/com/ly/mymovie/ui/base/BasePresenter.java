package com.ly.mymovie.ui.base;

import com.ly.mymovie.exception.MvpViewNotAttachedException;
import com.ly.mymovie.util.RxJavaUtil;

import io.reactivex.disposables.Disposable;

/**
 * Created by LanYang on 2017/12/07.
 * Email:568240761@qq.com
 * 该类实现了{@link MvpPresenter};子类能通过调用getMvpView()获得mMvpView
 */
public class BasePresenter<T extends MvpView> implements MvpPresenter<T> {

    private T mMvpView;
    //取消订阅；释放资源
    private Disposable mDisposable;

    public void setDisposable(Disposable disposable) {
        mDisposable = disposable;
    }

    @Override
    public void attachView(T mvpView) {
        mMvpView = mvpView;
    }

    @Override
    public void detachView() {
        mMvpView = null;
        RxJavaUtil.dispose(mDisposable);
    }

    public boolean isViewAttached() {
        return mMvpView != null;
    }

    public T getMvpView() {
        if (!isViewAttached()) {
            throw new MvpViewNotAttachedException();
        }
        return mMvpView;
    }
}

