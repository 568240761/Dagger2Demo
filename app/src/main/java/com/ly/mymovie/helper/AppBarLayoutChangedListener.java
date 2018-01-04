package com.ly.mymovie.helper;

import android.support.design.widget.AppBarLayout;

/**
 * Created by LanYang on 2017/12/18.
 * Email:568240761@qq.com
 * 监听CollapsingToolbarLayout的展开与折叠状态
 */

public abstract class AppBarLayoutChangedListener implements AppBarLayout.OnOffsetChangedListener {
    /**
     * 展开状态
     */
    public final static int EXPANDED = 0;
    /**
     * 折叠状态
     */
    public final static int COLLAPSED = EXPANDED + 1;
    /**
     * 中间状态
     */
    public final static int IDLE = COLLAPSED + 1;

    private int  mCurrentState = IDLE;
    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        if (verticalOffset == 0) {
            if (mCurrentState != EXPANDED) {
                onStateChanged(appBarLayout, EXPANDED);
            }
            mCurrentState = EXPANDED;
        } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
            if (mCurrentState != COLLAPSED) {
                onStateChanged(appBarLayout, COLLAPSED);
            }
            mCurrentState = COLLAPSED;
        } else {
            if (mCurrentState != IDLE) {
                onStateChanged(appBarLayout, IDLE);
            }
            mCurrentState = IDLE;
        }
    }

    public abstract void onStateChanged(AppBarLayout appBarLayout, int state);
}
