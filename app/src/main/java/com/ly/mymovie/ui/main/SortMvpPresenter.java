package com.ly.mymovie.ui.main;

import com.google.gson.Gson;
import com.ly.mymovie.data.model.SortMessage;
import com.ly.mymovie.ui.base.BaseActivity;
import com.ly.mymovie.ui.base.BasePresenter;
import com.ly.mymovie.util.AssetsUtil;
import com.ly.mymovie.util.log.LogUtil;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by LanYang on 2017/12/25.
 * Email:568240761@qq.com
 */

public class SortMvpPresenter extends BasePresenter<SortMvpView> {
    private final BaseActivity mBaseActivity;
    @Inject
    public SortMvpPresenter(BaseActivity baseActivity) {
        mBaseActivity = baseActivity;
    }

    public void getFilmContent(){
        Observable<SortMessage> observable = Observable.create(new ObservableOnSubscribe<SortMessage>() {
            @Override
            public void subscribe(ObservableEmitter<SortMessage> e) throws Exception {
                String result = AssetsUtil.openFile(AssetsUtil.FILE_JSON_SORT,mBaseActivity);
                Gson gson = new Gson();
                SortMessage message = gson.fromJson(result, SortMessage.class);
                e.onNext(message);
            }
        });
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SortMessage>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        setDisposable(d);
                    }

                    @Override
                    public void onNext(SortMessage sortMessage) {
                        getMvpView().notifyAdapter(sortMessage.getSortList());
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
