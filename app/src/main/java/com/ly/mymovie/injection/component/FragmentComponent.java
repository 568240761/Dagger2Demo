package com.ly.mymovie.injection.component;

import com.ly.mymovie.annotation.FragmentScope;
import com.ly.mymovie.injection.module.FragmentModule;
import com.ly.mymovie.ui.main.ComingFragment;
import com.ly.mymovie.ui.main.HitFragment;
import com.ly.mymovie.ui.main.SortFragment;
import com.ly.mymovie.ui.main.list.NorthAmericaFragment;
import com.ly.mymovie.ui.main.list.TopFragment;

import dagger.Component;

/**
 * Created by LanYang on 2018/01/03.
 * Email:568240761@qq.com
 */
@FragmentScope
@Component(dependencies = ApplicationComponent.class,modules = FragmentModule.class)
public interface FragmentComponent {
    void inject(HitFragment hitFragment);
    void inject(ComingFragment comingFragment);
    void inject(SortFragment sortFragment);
    void inject(TopFragment topFragment);
    void inject(NorthAmericaFragment northAmericaFragment);
}
