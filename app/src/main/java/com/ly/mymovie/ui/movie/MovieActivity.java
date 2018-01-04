package com.ly.mymovie.ui.movie;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.ly.mymovie.R;
import com.ly.mymovie.data.model.ImageMessage;
import com.ly.mymovie.data.model.MovieMessage;
import com.ly.mymovie.helper.JustifyTextView;
import com.ly.mymovie.helper.LinearSpacingItemDecoration;
import com.ly.mymovie.ui.base.DaggerActivity;
import com.ly.mymovie.util.SpannableStringUtil;
import com.ly.mymovie.util.TextUtil;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class MovieActivity extends DaggerActivity implements MovieMvpView {

    @Inject
    MovieMvpPresenter mMovieMvpPresenter;
    @Inject
    FilmmakerRecyclerViewAdapter mFilmmakerRecyclerViewAdapter;

    @BindView(R.id.movie_tv_name)
    TextView mTvName;
    @BindView(R.id.movie_tv_average)
    TextView mTvAverage;
    @BindView(R.id.movie_tv_other_name)
    TextView mTvOtherName;
    @BindView(R.id.movie_tv_year)
    TextView mTvYear;
    @BindView(R.id.movie_tv_countries)
    TextView mTvCountries;
    @BindView(R.id.movie_tv_type)
    TextView mTvType;
    @BindView(R.id.movie_tv_director)
    TextView mTvDirector;
    @BindView(R.id.movie_tv_cast)
    TextView mTvCast;
    @BindView(R.id.movie_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.movie_tv_plot)
    JustifyTextView mTvPlot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        bindView(this);
        mToolbarLayout.setBackgroundResource(R.drawable.img_default);

        activityComponent().inject(this);
        mMovieMvpPresenter.attachView(this);
        initPresenter(mMovieMvpPresenter);

        initView();

        mMovieMvpPresenter.loadMovieMessage(getDataFromActivity());
    }

    private void initView() {
        addOnOffsetChangedListener();

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mRecyclerView.addItemDecoration(new LinearSpacingItemDecoration(getResources().getDimensionPixelSize(R.dimen.margin_right_left), LinearLayoutManager.HORIZONTAL,false));
        mRecyclerView.setAdapter(mFilmmakerRecyclerViewAdapter);
    }

    @Override
    public void showError() {

    }

    @Override
    public void showMovieMessage(MovieMessage movieBean) {
        mMovieMvpPresenter.loadToolbarLayoutBackground(movieBean.getImages().getLarge(), mToolbarLayout);

        mTvName.setText(movieBean.getTitle());
        mToolbarTitle.setText(movieBean.getTitle());
        mTvAverage.setText(Double.toString(movieBean.getRating().getAverage()));
        mTvOtherName.setText(TextUtil.getResources(this,R.string.otherName) + TextUtil.isEmpty(TextUtil.splicing(movieBean.getAka()),this));
        mTvYear.setText(TextUtil.getResources(this,R.string.year) + TextUtil.isEmpty(movieBean.getYear(),this));
        mTvCountries.setText(TextUtil.getResources(this,R.string.countries) + TextUtil.isEmpty(TextUtil.splicing(movieBean.getCountries()),this));
        SpannableStringUtil.setClickText(R.string.type,R.color.font_link,movieBean.getGenres(),mTvType);
        SpannableStringUtil.setClickText(R.string.director,R.color.font_link,movieBean.getDirectors(),mTvDirector);
        SpannableStringUtil.setClickText(R.string.cast,R.color.font_link,movieBean.getCasts(),mTvCast);

        List<ImageMessage> list = TextUtil.spliceList(movieBean.getDirectors(), movieBean.getCasts());

        mFilmmakerRecyclerViewAdapter.setImageMessageList(list);
        mFilmmakerRecyclerViewAdapter.notifyDataSetChanged();

        mTvPlot.setText(TextUtil.isEmpty(TextUtil.formatPlot(movieBean.getSummary()),this),list,R.color.font_link);
    }
}
