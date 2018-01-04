package com.ly.mymovie.ui.adapter;

import android.support.annotation.IntDef;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ly.mymovie.R;
import com.ly.mymovie.data.DataManager;
import com.ly.mymovie.data.image.ImageManagerService;
import com.ly.mymovie.data.model.bean.SubjectsBean;
import com.ly.mymovie.ui.base.BaseActivity;
import com.ly.mymovie.ui.movie.MovieActivity;
import com.ly.mymovie.util.ActionUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by LanYang on 2017/12/12.
 * Email:568240761@qq.com
 * 电影列表适配器
 */
public class MovieRecyclerViewAdapter extends RecyclerView.Adapter<MovieRecyclerViewAdapter.MovieListViewHolder> {
    public final static int TYPE_COMING = 1;
    @IntDef(TYPE_COMING)
    public @interface MovieViewType{};

    private final BaseActivity mBaseActivity;
    private final ImageManagerService mImageManagerService;

    private List<SubjectsBean> mSubjectsBeanList;
    /*0-正在热映(默认)；1-即将上映*/
    private int type;

    public void addSubjectsBeanList(List<SubjectsBean> subjectsBeanList) {
        mSubjectsBeanList.addAll(subjectsBeanList);
        notifyItemRangeChanged(mSubjectsBeanList.size() - subjectsBeanList.size(), subjectsBeanList.size());
    }

    public void clearSubjectSBeanList() {
        mSubjectsBeanList.clear();
        notifyDataSetChanged();
    }

    public void setType(@MovieViewType int type) {
        this.type = type;
    }

    @Inject
    public MovieRecyclerViewAdapter(BaseActivity baseActivity, DataManager dataManager) {
        mBaseActivity = baseActivity;
        mImageManagerService = dataManager.getImageManagerService();
        mSubjectsBeanList = new ArrayList<SubjectsBean>();
    }

    @Override
    public MovieListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_movie_item, parent, false);
        return new MovieListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MovieListViewHolder holder, int position) {
        final SubjectsBean subjectsBean = mSubjectsBeanList.get(position);
        holder.mTvMovieName.setText(subjectsBean.getTitle());
        if (type == 0) {
            holder.mTvMovieAverage.setText(Double.toString(subjectsBean.getRating().getAverage()));
        } else if (type == 1) {
            List<String> genresList = subjectsBean.getGenres();
            holder.mTvMovieAverage.setText(genresList == null || genresList.size() == 0 ? "" : genresList.get(0));
        }
        mImageManagerService.getBitmap(mBaseActivity, subjectsBean.getImages().getSmall(), holder.mIvMovieImg,R.drawable.img_default);
        holder.ll_movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActionUtil.gotoActivity(mBaseActivity, MovieActivity.class, subjectsBean.getId());
            }
        });
        holder.startAnimator();
    }

    @Override
    public int getItemCount() {
        return mSubjectsBeanList.size();
    }


    class MovieListViewHolder extends AnimatorViewHolder {

        @BindView(R.id.iv_movie_img)
        ImageView mIvMovieImg;
        @BindView(R.id.tv_movie_name)
        TextView mTvMovieName;
        @BindView(R.id.tv_movie_average)
        TextView mTvMovieAverage;
        @BindView(R.id.ll_movie)
        LinearLayout ll_movie;

        public MovieListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
