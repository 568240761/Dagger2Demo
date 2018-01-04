package com.ly.mymovie;

import android.app.Application;
import android.content.Context;

import com.ly.mymovie.injection.component.ApplicationComponent;
import com.ly.mymovie.injection.component.DaggerApplicationComponent;
import com.ly.mymovie.injection.module.ApplicationModule;

/**
 * Created by LanYang on 2017/11/27.
 * Email:568240761@qq.com
 */

public class MyMovieApplication extends Application {
    private ApplicationComponent mApplicationComponent;

    public static MyMovieApplication get(Context context){
        return (MyMovieApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public ApplicationComponent getApplicationComponent(){
        if(mApplicationComponent == null){
            mApplicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
        }
        return mApplicationComponent;
    }
}
