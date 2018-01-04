package com.ly.mymovie.ui.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ly.mymovie.R;
import com.ly.mymovie.data.model.bean.SortBean;
import com.ly.mymovie.helper.GridSpacingItemDecoration;
import com.ly.mymovie.ui.base.DaggerFragment;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by LanYang on 2017/12/24.
 * Email:568240761@qq.com
 */

public class SortFragment extends DaggerFragment implements SortMvpView {
    @Inject
    SortFragmentAdapter mSortFragmentAdapter;
    @Inject
    SortMvpPresenter mSortMvpPresenter;

    @BindView(R.id.fg_sort_recycler_view)
    RecyclerView mRecyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentComponent().inject(this);
        mSortMvpPresenter.attachView(this);
        initPresenter(mSortMvpPresenter);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sort, container, false);
        bindView(this, view);

        mToolbarStatus = parentActivity.enableToolbar();

        initRecyclerView();

        mSortMvpPresenter.getFilmContent();

        return view;
    }

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new GridLayoutManager(parentActivity, 2, GridLayoutManager.VERTICAL, false));
        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(2, getResources().getDimensionPixelSize(R.dimen.adapter_item_decoration), true));
        mRecyclerView.setAdapter(mSortFragmentAdapter);
    }

    @Override
    public void notifyAdapter(List<SortBean> sortBeanList) {
        mSortFragmentAdapter.setSortBeanList(sortBeanList);
        mSortFragmentAdapter.notifyDataSetChanged();
    }
}
