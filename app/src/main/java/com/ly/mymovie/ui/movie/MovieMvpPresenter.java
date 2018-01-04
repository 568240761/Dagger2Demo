package com.ly.mymovie.ui.movie;

import android.support.design.widget.CollapsingToolbarLayout;

import com.ly.mymovie.data.DataManager;
import com.ly.mymovie.data.image.ImageManagerService;
import com.ly.mymovie.data.model.MovieMessage;
import com.ly.mymovie.data.network.Request;
import com.ly.mymovie.ui.base.BaseActivity;
import com.ly.mymovie.ui.base.BasePresenter;
import com.ly.mymovie.util.log.LogUtil;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by LanYang on 2017/12/16.
 * Email:568240761@qq.com
 */

public class MovieMvpPresenter extends BasePresenter<MovieMvpView> {
    private final Request mRequest;
    private final ImageManagerService mImageManagerService;
    private final BaseActivity mBaseActivity;

    @Inject
    public MovieMvpPresenter(BaseActivity baseActivity, DataManager dataManager){
        mBaseActivity = baseActivity;
        mRequest = dataManager.getRequest();
        mImageManagerService = dataManager.getImageManagerService();
    }

    public void loadToolbarLayoutBackground(String url,CollapsingToolbarLayout toolbarLayout){
        mImageManagerService.getBitmap(mBaseActivity, url, toolbarLayout);
    }

    public void loadMovieMessage(String id){
        Observable<MovieMessage> observable = mRequest.getMovieMessage(id);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MovieMessage>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        setDisposable(d);
                    }

                    @Override
                    public void onNext(MovieMessage movieBean) {
                        if(movieBean == null){
                            getMvpView().showError();
                        }else{
                            getMvpView().showMovieMessage(movieBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.getSingleton().e(null,e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
