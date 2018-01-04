package com.ly.mymovie.ui.search;

import com.ly.mymovie.data.DataManager;
import com.ly.mymovie.data.model.Message;
import com.ly.mymovie.data.network.Request;
import com.ly.mymovie.ui.base.BasePresenter;
import com.ly.mymovie.util.log.LogUtil;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by LanYang on 2017/12/24.
 * Email:568240761@qq.com
 */

public class SearchMvpPresenter extends BasePresenter<SearchMvpView> {
    private final Request mRequest;

    @Inject
    public SearchMvpPresenter(DataManager dataManager) {
        mRequest = dataManager.getRequest();
    }

    public void load(String q, int start) {
        Observable<Message> observable = mRequest.getSearchMessage(q, start);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Message>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        setDisposable(d);
                    }

                    @Override
                    public void onNext(Message message) {

                        getMvpView().notifyAdapter(message);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.getSingleton().e(null, e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
