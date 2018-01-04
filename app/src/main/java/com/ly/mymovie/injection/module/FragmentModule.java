package com.ly.mymovie.injection.module;

import android.content.Context;

import com.ly.mymovie.annotation.ActivityContext;
import com.ly.mymovie.ui.base.BaseActivity;
import com.ly.mymovie.ui.base.BaseFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by LanYang on 2018/01/03.
 * Email:568240761@qq.com
 */
@Module
public class FragmentModule {
    private BaseFragment mBaseFragment;

    public FragmentModule(BaseFragment baseFragment) {
        mBaseFragment = baseFragment;
    }

    @Provides
    BaseActivity provideActivity() {
        return mBaseFragment.parentActivity;
    }

    @Provides
    BaseFragment provideFragment(){
        return mBaseFragment;
    }

    @Provides
    @ActivityContext
    Context providesContext() {
        return mBaseFragment.parentActivity;
    }
}
