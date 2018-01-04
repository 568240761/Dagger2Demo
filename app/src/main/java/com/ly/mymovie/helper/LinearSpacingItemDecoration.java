package com.ly.mymovie.helper;

import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by LanYang on 2017/12/13.
 * Email:568240761@qq.com
 * 设置设置RecyclerView LinearLayoutManager中的Item间隔
 */

public class LinearSpacingItemDecoration extends RecyclerView.ItemDecoration {

    private int space;
    private int orientation;
    //是否需要设置边界空间
    private boolean isBorder;

    public LinearSpacingItemDecoration(int space, int orientation,boolean isBorder) {
        this.space = space;
        this.orientation = orientation;
        this.isBorder = isBorder;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (this.orientation == LinearLayoutManager.VERTICAL) {
            outRect.left = space;
            outRect.right = space;
            outRect.bottom = space;

            if (parent.getChildLayoutPosition(view) == 0) {
                outRect.top = space;
            } else {
                outRect.top = 0;
            }
        } else {
            if (isBorder) {
                outRect.top = space;
                outRect.bottom = space;
                outRect.right = space;

                if (parent.getChildLayoutPosition(view) == 0) {
                    outRect.left = space;
                } else {
                    outRect.left = 0;
                }
            } else {
                outRect.top = 0;
                outRect.bottom = 0;
                outRect.right = 0;

                if (parent.getChildLayoutPosition(view) == 0) {
                    outRect.left = 0;
                } else {
                    outRect.left = space;
                }
            }
        }
    }
}
