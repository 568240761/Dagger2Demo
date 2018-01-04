package com.ly.mymovie.ui.filmmaker;

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
import com.ly.mymovie.data.model.bean.WorksBean;
import com.ly.mymovie.ui.base.BaseActivity;
import com.ly.mymovie.ui.movie.MovieActivity;
import com.ly.mymovie.util.ActionUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by LanYang on 2017/12/23.
 * Email:568240761@qq.com
 */

public class MovieRecyclerViewAdapter extends RecyclerView.Adapter<MovieRecyclerViewAdapter.ViewHolder> {
    private final ImageManagerService mImageManagerService;
    private final BaseActivity mBaseActivity;
    private List<WorksBean> mWorksBeanList;

    public void setWorksBeanList(List<WorksBean> worksBeanList) {
        mWorksBeanList.addAll(worksBeanList);
    }

    @Inject
    public MovieRecyclerViewAdapter(DataManager dataManager, BaseActivity baseActivity) {
        mImageManagerService = dataManager.getImageManagerService();
        mBaseActivity = baseActivity;
        mWorksBeanList = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_movie_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        WorksBean worksBean = mWorksBeanList.get(position);
        final SubjectsBean subjectsBean = worksBean.getSubject();
        mImageManagerService.getBitmap(mBaseActivity,subjectsBean.getImages().getSmall(),holder.mIvImg,R.drawable.img_default);
        holder.mTvName.setText(subjectsBean.getTitle());
        holder.mTvAverage.setText(Double.toString(subjectsBean.getRating().getAverage()));
        holder.mFilmmaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActionUtil.gotoActivity(mBaseActivity,MovieActivity.class,subjectsBean.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mWorksBeanList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_movie_img)
        ImageView mIvImg;
        @BindView(R.id.tv_movie_name)
        TextView mTvName;
        @BindView(R.id.tv_movie_average)
        TextView mTvAverage;
        @BindView(R.id.ll_movie)
        LinearLayout mFilmmaker;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
