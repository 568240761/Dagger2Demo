package com.ly.mymovie.ui.main.list;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ly.mymovie.R;
import com.ly.mymovie.data.model.Message;
import com.ly.mymovie.data.model.bean.SubjectsBox;
import com.ly.mymovie.helper.LinearSpacingItemDecoration;
import com.ly.mymovie.helper.MySwipeRefreshLayout;
import com.ly.mymovie.ui.base.DaggerFragment;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by LanYang on 2017/12/14.
 * Email:568240761@qq.com
 */

public class NorthAmericaFragment extends DaggerFragment implements ListMvpView {
    @Inject
    ListMvpPresenter mListMvpPresenter;
    @Inject
    NorthAmericaRecyclerViewAdapter mNorthAmericaRecyclerViewAdapter;
    @BindView(R.id.fg_north_america_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.fg_north_america_refresh_layout)
    MySwipeRefreshLayout mRefreshLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentComponent().inject(this);
        mListMvpPresenter.attachView(this);
        initPresenter(mListMvpPresenter);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_north_america, container, false);
        bindView(this,view);

        initView();

        mListMvpPresenter.loadNorthAmericaMessage();

        return view;
    }

    private void initView() {
        mRefreshLayout.setEnabled(false);
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mListMvpPresenter.loadNorthAmericaMessage();
            }
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(parentActivity));
        mRecyclerView.addItemDecoration(new LinearSpacingItemDecoration(getResources().getDimensionPixelSize(R.dimen.adapter_item_decoration), LinearLayoutManager.VERTICAL, true));
        mRecyclerView.setAdapter(mNorthAmericaRecyclerViewAdapter);
    }

    @Override
    public void notifyAdapter(Message message) {

    }

    @Override
    public void notifyAdapterBox(List<SubjectsBox> list) {
        mNorthAmericaRecyclerViewAdapter.clearSubjectsBeanList();
        mNorthAmericaRecyclerViewAdapter.addSubjectsBeanList(list);

        if(mRefreshLayout.isEnabled() == false){
            mRefreshLayout.setEnabled(true);
        }

        if(mRefreshLayout.isRefreshing()==true){
            mRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void showErrorView() {

    }
}
