package com.ly.mymovie.ui.base;

import android.content.Context;

import com.ly.mymovie.MyMovieApplication;
import com.ly.mymovie.injection.component.DaggerFragmentComponent;
import com.ly.mymovie.injection.component.FragmentComponent;
import com.ly.mymovie.injection.module.FragmentModule;

/**
 * Created by LanYang on 2018/01/03.
 * Email:568240761@qq.com
 * 需要依赖注入的Fragment必须继承DaggerFragment;不需要则继承BaseFragment
 */

public class DaggerFragment extends BaseFragment {
    private FragmentComponent mFragmentComponent;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mFragmentComponent = DaggerFragmentComponent.builder()
                .applicationComponent(MyMovieApplication.get(context).getApplicationComponent())
                .fragmentModule(new FragmentModule(this))
                .build();
    }

    public FragmentComponent fragmentComponent(){
        return mFragmentComponent;
    }
}
