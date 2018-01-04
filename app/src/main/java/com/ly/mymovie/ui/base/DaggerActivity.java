package com.ly.mymovie.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.ly.mymovie.MyMovieApplication;
import com.ly.mymovie.injection.component.ActivityComponent;
import com.ly.mymovie.injection.component.DaggerActivityComponent;
import com.ly.mymovie.injection.module.ActivityModule;

/**
 * Created by LanYang on 2018/01/03.
 * Email:568240761@qq.com
 * 需要依赖注入的Activity必须继承DaggerActivity;不需要则继承BaseActivity
 */

public class DaggerActivity extends BaseActivity {
    private ActivityComponent mActivityComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .applicationComponent(MyMovieApplication.get(this).getApplicationComponent())
                .build();
    }

    public ActivityComponent activityComponent() {
        return mActivityComponent;
    }
}
