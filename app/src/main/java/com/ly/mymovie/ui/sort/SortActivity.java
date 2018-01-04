package com.ly.mymovie.ui.sort;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ly.mymovie.R;
import com.ly.mymovie.data.model.Message;
import com.ly.mymovie.data.model.bean.SubjectsBean;
import com.ly.mymovie.helper.GridSpacingItemDecoration;
import com.ly.mymovie.helper.MySwipeRefreshLayout;
import com.ly.mymovie.ui.adapter.MovieRecyclerViewAdapter;
import com.ly.mymovie.ui.base.DaggerActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by LanYang on 2017/12/24.
 * Email:568240761@qq.com
 */

public class SortActivity extends DaggerActivity implements SortMvpView {
    @Inject
    MovieRecyclerViewAdapter mMovieRecyclerViewAdapter;
    @Inject
    SortMvpPresenter mSortMvpPresenter;

    @BindView(R.id.sort_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.sort_refresh_layout)
    MySwipeRefreshLayout mRefreshLayout;

    private int mStart = 0;

    private String mTag;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort);
        bindView(this);

        activityComponent().inject(this);
        mSortMvpPresenter.attachView(this);
        initPresenter(mSortMvpPresenter);

        disableToolbar();
        initView();

        mTag = getDataFromActivity();
        mToolbarTitle.setText(mTag);
        mSortMvpPresenter.loadSortMessage(mTag, mStart);
    }

    private void initView() {
        mRefreshLayout.setEnabled(false);
        mRefreshLayout.associatedRecyclerView(mRecyclerView);
        mRefreshLayout.setOnLoadMoreListener(new MySwipeRefreshLayout.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                mSortMvpPresenter.loadSortMessage(mTag, mStart);
            }
        });

        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false));
        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(2, getResources().getDimensionPixelSize(R.dimen.adapter_item_decoration), true));
        mRecyclerView.setAdapter(mMovieRecyclerViewAdapter);
    }

    @Override
    public void showErrorView() {

    }

    @Override
    public void notifyAdapter(Message message) {
        enableToolbar();
        List<SubjectsBean> subjectsBeanList = message.getSubjects();
        mMovieRecyclerViewAdapter.addSubjectsBeanList(subjectsBeanList);

        mStart += subjectsBeanList.size();
        mRefreshLayout.setLoadPage(mStart, message.getTotal());
        mRefreshLayout.setLoadComplete(true);
    }
}
