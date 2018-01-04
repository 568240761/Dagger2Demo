package com.ly.mymovie.helper;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.ly.mymovie.R;
import com.ly.mymovie.util.TextUtil;
import com.ly.mymovie.util.ToastUtil;

/**
 * Created by LanYang on 2017/12/26.
 * Email:568240761@qq.com
 */

public class MySwipeRefreshLayout extends SwipeRefreshLayout {
    private OnLoadMoreListener mListener;
    /**
     * 分页加载；开始索引
     */
    private int mStart = 0;
    /**
     * 分页加载；总数
     */
    private int mTotal = 0;
    /**
     * 上拉刷新是否完成
     */
    private boolean isLoadComplete = true;

    /**
     * 数据是否已经加载完成
     */
    private boolean isCompleted = false;

    public void setOnLoadMoreListener(OnLoadMoreListener listener) {
        mListener = listener;
    }

    public void setLoadComplete(boolean loadComplete) {
        isLoadComplete = loadComplete;
    }

    public MySwipeRefreshLayout(@NonNull Context context) {
        this(context, null);
    }

    public MySwipeRefreshLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setColorSchemeResources(R.color.colorPrimary);
        setProgressBackgroundColorSchemeColor(Color.WHITE);
    }

    public void setLoadPage(int start, int total) {
        mStart = start;
        mTotal = total;
    }

    public void resetLoadPage(){
        mStart = 0;
        mTotal = 0;
    }

    public void associatedRecyclerView(RecyclerView recyclerView) {
        if (recyclerView != null) {

            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    if (mListener != null) {
                        int lastPosition = 0;
                        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                        if (layoutManager instanceof LinearLayoutManager) {
                            lastPosition = ((LinearLayoutManager) layoutManager).findLastCompletelyVisibleItemPosition();
                        } else if (layoutManager instanceof GridLayoutManager) {
                            lastPosition = ((GridLayoutManager) layoutManager).findLastCompletelyVisibleItemPosition();
                        }

                        if (lastPosition + 1 == recyclerView.getAdapter().getItemCount() && isLoadComplete) {
                            isLoadComplete = false;
                            if (mStart != 0 && mTotal != 0 && mStart == mTotal) {
                                ToastUtil.makeText(getContext(), TextUtil.getResources(getContext(), R.string.loaded));
                                isLoadComplete = true;
                            } else {
                                mListener.onLoadMore();
                            }
                        }
                    }
                }
            });
        }
    }


    /**
     * 上拉加载的接口回调
     */

    public interface OnLoadMoreListener {
        void onLoadMore();
    }
}
