package com.ly.mymovie.ui.search;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.ly.mymovie.R;
import com.ly.mymovie.data.model.Message;
import com.ly.mymovie.data.model.bean.SubjectsBean;
import com.ly.mymovie.helper.GridSpacingItemDecoration;
import com.ly.mymovie.helper.MySwipeRefreshLayout;
import com.ly.mymovie.ui.adapter.MovieRecyclerViewAdapter;
import com.ly.mymovie.ui.base.DaggerActivity;
import com.ly.mymovie.util.TextUtil;
import com.ly.mymovie.util.ToastUtil;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by LanYang on 2017/12/24.
 * Email:568240761@qq.com
 */

public class SearchActivity extends DaggerActivity implements SearchMvpView {
    @Inject
    SearchMvpPresenter mSearchMvpPresenter;
    @Inject
    MovieRecyclerViewAdapter mMovieRecyclerViewAdapter;
    @BindView(R.id.toolbar_edit)
    EditText mEdit;
    @BindView(R.id.search_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.search_refresh_layout)
    MySwipeRefreshLayout mRefreshLayout;

    private int mStart = 0;

    private InputMethodManager mInputMethodManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        bindView(this);

        activityComponent().inject(this);
        mSearchMvpPresenter.attachView(this);
        initPresenter(mSearchMvpPresenter);

        mInputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        disableToolbar();

        initView();
    }

    private void initView() {
        mRefreshLayout.setEnabled(false);
        mRefreshLayout.associatedRecyclerView(mRecyclerView);
        mRefreshLayout.setOnLoadMoreListener(new MySwipeRefreshLayout.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                load(mStart);
            }
        });

        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false));
        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(2, getResources().getDimensionPixelSize(R.dimen.adapter_item_decoration), true));
        mRecyclerView.setAdapter(mMovieRecyclerViewAdapter);

        mEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    hideKeyboard();
                    search();
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void showErrorView() {
        mRefreshLayout.setLoadComplete(true);
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

    @OnClick(R.id.toolbar_click)
    public void onViewClicked() {
        hideKeyboard();
        search();
    }

    private void hideKeyboard() {
        if (mInputMethodManager.isActive(mEdit)) {
            mInputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    private void search() {
        mRefreshLayout.resetLoadPage();
        mMovieRecyclerViewAdapter.clearSubjectSBeanList();
        load(0);
    }

    private void load(int start) {
        String str = mEdit.getText().toString().trim();
        if(str.length()==0){
            ToastUtil.makeText(this, TextUtil.getResources(this,R.string.search_tag));
            return;
        }
        mSearchMvpPresenter.load(str, start);
    }
}
