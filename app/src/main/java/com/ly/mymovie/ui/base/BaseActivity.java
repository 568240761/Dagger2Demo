package com.ly.mymovie.ui.base;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.ly.mymovie.R;
import com.ly.mymovie.helper.AppBarLayoutChangedListener;
import com.ly.mymovie.util.ActionUtil;
import com.ly.mymovie.util.Constant;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;
import butterknife.Unbinder;

/**
 * Created by LanYang on 2017/12/07.
 * Email:568240761@qq.com
 * 应用程序中的所有其他Activity都必须继承该类。
 */

public class BaseActivity extends AppCompatActivity {
    private BasePresenter mBasePresenter;
    private Unbinder mUnbinder;
    private int mScrollFlag;

    /**************************************toolbar************************************/
    @Nullable
    @BindView(R.id.toolbar)
    public Toolbar mToolbar;
    @Nullable
    @BindView(R.id.toolbar_title)
    public TextView mToolbarTitle;
    @Nullable
    @BindView(R.id.toolbar_layout)
    public CollapsingToolbarLayout mToolbarLayout;
    @Nullable
    @BindView(R.id.app_bar)
    public AppBarLayout mAppBar;

    @Optional
    @OnClick(R.id.toolbar_back)
    public void viewClicked(View view) {
        finishActivity();
    }

    /**
     * AppBarLayout折叠与展开的监听
     */
    public void addOnOffsetChangedListener() {
        mAppBar.addOnOffsetChangedListener(new AppBarLayoutChangedListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, int state) {
                if (state == AppBarLayoutChangedListener.EXPANDED) {
                    mToolbarTitle.setVisibility(View.INVISIBLE);
                } else if (state == AppBarLayoutChangedListener.COLLAPSED) {
                    mToolbarTitle.setVisibility(View.VISIBLE);
                } else {
                    mToolbarTitle.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    /********************************************************************************/

    public void bindView(BaseActivity baseActivity) {
        mUnbinder = ButterKnife.bind(baseActivity);
    }

    /**
     * 滚动折叠
     */
    public int enableToolbar() {
        AppBarLayout.LayoutParams params =
                (AppBarLayout.LayoutParams) mToolbar.getLayoutParams();
        if (params.getScrollFlags() == 0) {
            params.setScrollFlags(mScrollFlag);
            mToolbar.setLayoutParams(params);
        }
        return Constant.TOOLBAR_ROLL;
    }

    /**
     * 滚动也不折叠
     */
    public int disableToolbar() {
        AppBarLayout.LayoutParams params =
                (AppBarLayout.LayoutParams) mToolbar.getLayoutParams();
        if (params.getScrollFlags() != 0) {
            mScrollFlag = params.getScrollFlags();
            //AppBarLayout下的toolbar就不会随着滚动条折叠
            params.setScrollFlags(0);
            mToolbar.setLayoutParams(params);
        }
        return Constant.TOOLBAR_NOT_ROLL;
    }

    public void initPresenter(BasePresenter basePresenter) {
        mBasePresenter = basePresenter;
    }

    @Override
    protected void onDestroy() {
        if (mBasePresenter != null) {
            mBasePresenter.detachView();
        }
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
        super.onDestroy();
    }

    public String getDataFromActivity() {
        Intent intent = getIntent();
        String data = intent.getStringExtra(ActionUtil.INTENT_STRING);
        return data == null ? "" : data;
    }

    public void finishActivity() {
        finish();
    }

}
