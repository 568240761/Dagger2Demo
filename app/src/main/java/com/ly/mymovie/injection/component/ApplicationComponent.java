package com.ly.mymovie.injection.component;

import com.ly.mymovie.data.DataManager;
import com.ly.mymovie.data.network.Request;
import com.ly.mymovie.injection.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by LanYang on 2017/12/07.
 * Email:568240761@qq.com
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    DataManager dataManager();

    Request request();
}
