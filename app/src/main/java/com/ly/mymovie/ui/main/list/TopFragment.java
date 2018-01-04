package com.ly.mymovie.ui.main.list;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ly.mymovie.R;
import com.ly.mymovie.data.model.Message;
import com.ly.mymovie.data.model.bean.SubjectsBean;
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

public class TopFragment extends DaggerFragment implements ListMvpView {
    @Inject
    ListMvpPresenter mListMvpPresenter;
    @Inject
    TopRecyclerViewAdapter mTopRecyclerViewAdapter;
    @BindView(R.id.fg_top_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.top_refresh_layout)
    MySwipeRefreshLayout mRefreshLayout;

    private int mStart = 0;

    /**
     * 是否首次展示页面
     */
    private boolean isFirst = true;

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
        View view = inflater.inflate(R.layout.fragment_top250, container, false);
        bindView(this, view);

        initView();

        return view;
    }

    private void initView() {
        mRefreshLayout.setEnabled(false);
        mRefreshLayout.associatedRecyclerView(mRecyclerView);
        mRefreshLayout.setOnLoadMoreListener(new MySwipeRefreshLayout.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                mListMvpPresenter.loadTop250Message(mStart);
            }
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(parentActivity));
        mRecyclerView.addItemDecoration(new LinearSpacingItemDecoration(getResources().getDimensionPixelSize(R.dimen.adapter_item_decoration), LinearLayoutManager.VERTICAL, true));
        mRecyclerView.setAdapter(mTopRecyclerViewAdapter);
    }

    @Override
    public void notifyAdapter(Message message) {
        List<SubjectsBean> list = message.getSubjects();
        mTopRecyclerViewAdapter.addSubjectsBeanList(list);

        mStart += list.size();
        mRefreshLayout.setLoadPage(mStart, message.getTotal());
        mRefreshLayout.setLoadComplete(true);
    }

    @Override
    public void notifyAdapterBox(List<SubjectsBox> list) {

    }

    @Override
    public void showErrorView() {

    }

    @Override
    public void operation() {
        if (isFirst) {
            isFirst = false;
            mListMvpPresenter.loadTop250Message(mStart);
        }
    }
}
