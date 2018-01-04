package com.ly.mymovie.injection.module;

import android.app.Application;
import android.content.Context;

import com.ly.mymovie.data.network.NetworkManagerService;
import com.ly.mymovie.data.network.Request;
import com.ly.mymovie.annotation.ApplicationContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by LanYang on 2017/12/07.
 * Email:568240761@qq.com
 */
@Module
public class ApplicationModule {

    protected final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    @Singleton
    Request provideRequest() {
        return new NetworkManagerService().createRetrofit();
    }
}
