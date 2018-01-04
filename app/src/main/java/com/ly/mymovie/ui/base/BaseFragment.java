package com.ly.mymovie.ui.base;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;

import com.ly.mymovie.util.Constant;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by LanYang on 2017/12/08.
 * Email:568240761@qq.com
 * 所有其他Fragment都必须继承该类
 */

public class BaseFragment extends Fragment{
    private BasePresenter mBasePresenter;
    public BaseActivity parentActivity;
    private Unbinder mUnbinder;

    /**
     * Toolbar是否因滚动而折叠
     */
    protected int mToolbarStatus = -1;

    public void initPresenter(BasePresenter basePresenter) {
        mBasePresenter = basePresenter;
    }

    public void bindView(BaseFragment baseFragment, View view) {
        mUnbinder = ButterKnife.bind(this, view);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        parentActivity = (BaseActivity) getActivity();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser == true) {
            operation();
        }
    }
    /**
     * 需要实现懒加载的fragment页面，重写该方法
     */
    public void operation() {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mBasePresenter != null)
            mBasePresenter.detachView();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden){
            if (mToolbarStatus == Constant.TOOLBAR_NOT_ROLL) {
                mToolbarStatus = parentActivity.disableToolbar();
            } else if(mToolbarStatus == Constant.TOOLBAR_ROLL){
                mToolbarStatus = parentActivity.enableToolbar();
            }
        }
    }
}
