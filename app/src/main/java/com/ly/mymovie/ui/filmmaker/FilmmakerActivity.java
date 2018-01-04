package com.ly.mymovie.ui.filmmaker;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.ly.mymovie.R;
import com.ly.mymovie.data.model.FilmmakerMessage;
import com.ly.mymovie.data.model.bean.WorksBean;
import com.ly.mymovie.helper.GridSpacingItemDecoration;
import com.ly.mymovie.ui.base.DaggerActivity;
import com.ly.mymovie.util.TextUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by LanYang on 2017/12/23.
 * Email:568240761@qq.com
 */

public class FilmmakerActivity extends DaggerActivity implements FilmmakerMvpView {
    @Inject
    FilmmakerMvpPresenter mFilmmakerMvpPresenter;
    @Inject
    MovieRecyclerViewAdapter mMovieRecyclerViewAdapter;

    @BindView(R.id.filmmaker_tv_name)
    TextView mTvName;
    @BindView(R.id.filmmaker_tv_foreign_name)
    TextView mTvForeignName;
    @BindView(R.id.filmmaker_tv_gender)
    TextView mTvGender;
    @BindView(R.id.filmmaker_tv_born_place)
    TextView mTvBornPlace;
    @BindView(R.id.filmmaker_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.filmmaker_tv_work)
    TextView mTvWork;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filmmaker);
        bindView(this);

        activityComponent().inject(this);
        mFilmmakerMvpPresenter.attachView(this);
        initPresenter(mFilmmakerMvpPresenter);

        initView();

        mFilmmakerMvpPresenter.loadFilmmakerMessage(getDataFromActivity());
    }

    private void initView() {
        addOnOffsetChangedListener();

        mRecyclerView.setLayoutManager(new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false));
        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(2,getResources().getDimensionPixelSize(R.dimen.adapter_item_decoration),true));
        mRecyclerView.setAdapter(mMovieRecyclerViewAdapter);
    }

    @Override
    public void showError() {

    }

    @Override
    public void showFilmmakerMessage(FilmmakerMessage message) {
        mFilmmakerMvpPresenter.loadToolbarLayoutBackground(message.getAvatars().getLarge(), mToolbarLayout);

        mToolbarTitle.setText(message.getName());
        mTvName.setText(message.getName());
        mTvForeignName.setText(TextUtil.getResources(this, R.string.foreign_name) + TextUtil.isEmpty(message.getName_en(),this));
        mTvGender.setText(TextUtil.getResources(this, R.string.gender) + TextUtil.isEmpty(message.getGender(),this));
        mTvBornPlace.setText(TextUtil.getResources(this, R.string.born_place) + TextUtil.isEmpty(message.getBorn_place(),this));
        mTvWork.setText(TextUtil.getResources(this,R.string.work)+TextUtil.isEmpty(TextUtil.splicing(getRoles(message)),this));
        mMovieRecyclerViewAdapter.setWorksBeanList(message.getWorks());
        mMovieRecyclerViewAdapter.notifyDataSetChanged();
    }

    private Set<String> getRoles(FilmmakerMessage message){
        Set<String> roleSet = new HashSet<String>();
        List<WorksBean> worksBeanList = message.getWorks();
        for(WorksBean worksBean : worksBeanList){
            for(String role : worksBean.getRoles()){
                roleSet.add(role);
            }
        }
        return roleSet;
    }
}
