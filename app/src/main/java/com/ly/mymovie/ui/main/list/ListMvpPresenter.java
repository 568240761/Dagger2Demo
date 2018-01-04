package com.ly.mymovie.ui.main.list;

import com.ly.mymovie.data.DataManager;
import com.ly.mymovie.data.model.Message;
import com.ly.mymovie.data.model.NorthAmericaMessage;
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
 * Created by LanYang on 2017/12/14.
 * Email:568240761@qq.com
 */

public class ListMvpPresenter extends BasePresenter<ListMvpView> {
    private final Request mRequest;

    @Inject
    public ListMvpPresenter(DataManager dataManager) {
        mRequest = dataManager.getRequest();
    }

    public void loadTop250Message(int start) {
        Observable<Message> observable = mRequest.getTop250Message(start, 20);
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

    public void loadNorthAmericaMessage() {
        Observable<NorthAmericaMessage> observable = mRequest.getNorthAmericaMessage();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NorthAmericaMessage>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                        setDisposable(d);
                    }

                    @Override
                    public void onNext(NorthAmericaMessage message) {
                        if (message.getSubjects().isEmpty()) {
                            getMvpView().showErrorView();
                        } else {
                            getMvpView().notifyAdapterBox(message.getSubjects());
                        }
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
