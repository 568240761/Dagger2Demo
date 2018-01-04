package com.ly.mymovie.injection.component;

import com.ly.mymovie.annotation.ActivityScope;
import com.ly.mymovie.injection.module.ActivityModule;
import com.ly.mymovie.ui.filmmaker.FilmmakerActivity;
import com.ly.mymovie.ui.movie.MovieActivity;
import com.ly.mymovie.ui.search.SearchActivity;
import com.ly.mymovie.ui.sort.SortActivity;

import dagger.Component;

/**
 * Created by LanYang on 2017/12/07.
 * Email:568240761@qq.com
 * 该组件向应用程序中的所有Activity、Fragment注入依赖关系
 */
@ActivityScope
@Component(dependencies = ApplicationComponent.class,modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(MovieActivity movieActivity);
    void inject(FilmmakerActivity filmmakerActivity);
    void inject(SortActivity sortActivity);
    void inject(SearchActivity searchActivity);

//    void inject(HitFragment hitFragment);
//    void inject(ComingFragment comingFragment);
//    void inject(SortFragment sortFragment);
//    void inject(TopFragment topFragment);
//    void inject(NorthAmericaFragment northAmericaFragment);
}
