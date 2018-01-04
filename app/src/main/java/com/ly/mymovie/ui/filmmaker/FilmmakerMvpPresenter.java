package com.ly.mymovie.ui.filmmaker;

import android.support.design.widget.CollapsingToolbarLayout;

import com.ly.mymovie.data.DataManager;
import com.ly.mymovie.data.image.ImageManagerService;
import com.ly.mymovie.data.model.FilmmakerMessage;
import com.ly.mymovie.data.network.Request;
import com.ly.mymovie.ui.base.BaseActivity;
import com.ly.mymovie.ui.base.BasePresenter;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by LanYang on 2017/12/23.
 * Email:568240761@qq.com
 */

public class FilmmakerMvpPresenter extends BasePresenter<FilmmakerMvpView> {
    private final Request mRequest;
    private final ImageManagerService mImageManagerService;
    private final BaseActivity mBaseActivity;

    @Inject
    public FilmmakerMvpPresenter(BaseActivity baseActivity,DataManager dataManager) {
        mBaseActivity = baseActivity;
        mRequest = dataManager.getRequest();
        mImageManagerService = dataManager.getImageManagerService();
    }

    public void loadToolbarLayoutBackground(String url,CollapsingToolbarLayout toolbarLayout){
        mImageManagerService.getBitmap(mBaseActivity, url, toolbarLayout);
    }

    public void loadFilmmakerMessage(String id){
        Observable<FilmmakerMessage> observable = mRequest.getFilmmakerMessage(id);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FilmmakerMessage>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        setDisposable(d);
                    }

                    @Override
                    public void onNext(FilmmakerMessage message) {
                        if(message == null){
                            getMvpView().showError();
                        }else{
                            getMvpView().showFilmmakerMessage(message);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}

