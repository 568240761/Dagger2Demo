package com.ly.mymovie.ui.main;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.provider.Settings;

import com.ly.mymovie.annotation.ActivityContext;
import com.ly.mymovie.data.DataManager;
import com.ly.mymovie.data.location.ILocationSuccess;
import com.ly.mymovie.data.location.LocationManagerService;
import com.ly.mymovie.data.model.Message;
import com.ly.mymovie.data.network.Request;
import com.ly.mymovie.helper.MyDialog;
import com.ly.mymovie.ui.base.BaseActivity;
import com.ly.mymovie.ui.base.BasePresenter;
import com.ly.mymovie.util.log.LogUtil;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by LanYang on 2017/12/12.
 * Email:568240761@qq.com
 */
public class HitMvpPresenter extends BasePresenter<HitMvpView> {
    public static final int REQUEST_CODE_LOCATION = 0;
    private final LocationManagerService mLocationUtil;
    private final Request mRequest;
    private final BaseActivity mBaseActivity;
    private final Context mContext;

    @Inject
    public HitMvpPresenter(DataManager dataManager, BaseActivity baseActivity,
                           @ActivityContext Context context) {
        mRequest = dataManager.getRequest();
        mLocationUtil = dataManager.getLocationManagerService();
        mBaseActivity = baseActivity;
        mContext = context;
    }

    public void startLocation() {
        if(!mLocationUtil.isOPen(mContext)){
            MyDialog.showDialog(mBaseActivity, "定位服务未授权，是否授权？", new MyDialog.IPositiveOnClick() {
                @Override
                public void click() {
                    Intent intent = new Intent(
                            Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    mBaseActivity.startActivityForResult(intent,REQUEST_CODE_LOCATION);
                }
            });
            return;
        }
        Location location = mLocationUtil.getBestLocation(mContext);
        if (location == null) {
            mLocationUtil.addLocationListener(mContext,new ILocationSuccess() {
                @Override
                public void success(Location location) {
                    getCity(location);
                    mLocationUtil.removeLocationListener(mContext);
                }
            });
        } else {
            getCity(location);
        }

    }

    private void getCity(Location location) {
        Geocoder gc = new Geocoder(mContext, Locale.getDefault());
        try {
            List<Address> result = gc.getFromLocation(location.getLatitude(),
                    location.getLongitude(), 1);
            if (result.size() == 0) {
                getMvpView().showLocation(null);
            } else {
                Address address = result.get(0);
                String city = address.getSubAdminArea();
                LogUtil.getSingleton().d("当前城市：" + city);
                getMvpView().showLocation(city);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadHitMessage(String city) {
        Observable<Message> observable = mRequest.getHitMessage(city);
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
