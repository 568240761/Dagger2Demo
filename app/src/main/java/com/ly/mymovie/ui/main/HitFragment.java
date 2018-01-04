package com.ly.mymovie.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ly.mymovie.R;
import com.ly.mymovie.data.model.Message;
import com.ly.mymovie.data.model.bean.SubjectsBean;
import com.ly.mymovie.helper.GridSpacingItemDecoration;
import com.ly.mymovie.helper.MySwipeRefreshLayout;
import com.ly.mymovie.ui.adapter.MovieRecyclerViewAdapter;
import com.ly.mymovie.ui.base.DaggerFragment;
import com.ly.mymovie.util.Constant;
import com.ly.mymovie.util.PermissionUtil;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by LanYang on 2017/12/08.
 * Email:568240761@qq.com
 */

public class HitFragment extends DaggerFragment implements HitMvpView {
    @Inject
    HitMvpPresenter mHitMvpPresenter;
    @Inject
    MovieRecyclerViewAdapter mMovieListAdapter;

    @BindView(R.id.fg_hit_recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.hit_refresh_layout)
    MySwipeRefreshLayout mRefreshLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentComponent().inject(this);
        mHitMvpPresenter.attachView(this);
        initPresenter(mHitMvpPresenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hit, container, false);
        bindView(this, view);
        mToolbarStatus = parentActivity.disableToolbar();

        initView();

        initPermission();

        return view;
    }

    private void initPermission() {
        if (!PermissionUtil.checkSelfPermission(parentActivity, Constant.PERMISSION_LOCATION)) {
            mHitMvpPresenter.startLocation();
        } else {
            PermissionUtil.checkSelfPermission(parentActivity, Constant.PERMISSION_LOCATION, "定位服务未授权，是否授权？", Constant.REQUEST_CODE_PERMISSION_LOCATION);
        }
    }

    private void initView() {
        mRefreshLayout.setEnabled(false);
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mHitMvpPresenter.startLocation();
            }
        });

        mRecyclerView.setLayoutManager(new GridLayoutManager(parentActivity, 2, GridLayoutManager.VERTICAL, false));
        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(2, getResources().getDimensionPixelSize(R.dimen.adapter_item_decoration), true));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mMovieListAdapter);
    }

    @Override
    public void notifyAdapter(Message message) {
        mToolbarStatus = parentActivity.enableToolbar();

        mMovieListAdapter.clearSubjectSBeanList();
        List<SubjectsBean> list = message.getSubjects();
        mMovieListAdapter.addSubjectsBeanList(list);

        if (mRefreshLayout.isEnabled() == false) {
            mRefreshLayout.setEnabled(true);
        }

        if (mRefreshLayout.isRefreshing() == true) {
            mRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void showLocation(String city) {
        ((MainActivity) parentActivity).showLocation(city);
        if (!TextUtils.isEmpty(city)) {
            mHitMvpPresenter.loadHitMessage(city.substring(0, city.length() - 1));
        }
    }

    @Override
    public void showErrorView() {

    }
}
