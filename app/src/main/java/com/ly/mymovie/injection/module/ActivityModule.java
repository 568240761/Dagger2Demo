package com.ly.mymovie.injection.module;

import android.content.Context;

import com.ly.mymovie.annotation.ActivityContext;
import com.ly.mymovie.ui.base.BaseActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by LanYang on 2017/12/07.
 * Email:568240761@qq.com
 */
@Module
public class ActivityModule {

    private BaseActivity mActivity;

    public ActivityModule(BaseActivity activity) {
        mActivity = activity;
    }

    @Provides
    BaseActivity provideActivity() {
        return mActivity;
    }

    @Provides
    @ActivityContext
    Context providesContext() {
        return mActivity;
    }
}
