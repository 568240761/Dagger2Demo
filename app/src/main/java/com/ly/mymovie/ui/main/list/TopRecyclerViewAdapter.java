package com.ly.mymovie.ui.main.list;

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
import com.ly.mymovie.ui.adapter.AnimatorViewHolder;
import com.ly.mymovie.ui.base.BaseActivity;
import com.ly.mymovie.ui.movie.MovieActivity;
import com.ly.mymovie.util.ActionUtil;
import com.ly.mymovie.util.SpannableStringUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by LanYang on 2017/12/14.
 * Email:568240761@qq.com
 */

public class TopRecyclerViewAdapter extends RecyclerView.Adapter<TopRecyclerViewAdapter.ViewHolder> {

    private BaseActivity mBaseActivity;
    private List<SubjectsBean> mSubjectsBeanList;
    private final ImageManagerService mImageManagerService;


    public void addSubjectsBeanList(List<SubjectsBean> subjectsBeanList) {
        int count = subjectsBeanList.size();
        mSubjectsBeanList.addAll(subjectsBeanList);
        notifyItemRangeChanged(mSubjectsBeanList.size() - count, count);
    }

    @Inject
    public TopRecyclerViewAdapter(BaseActivity baseActivity, DataManager dataManager) {
        mBaseActivity = baseActivity;
        mImageManagerService = dataManager.getImageManagerService();
        mSubjectsBeanList = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_top250_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final SubjectsBean subjectsBean = mSubjectsBeanList.get(position);
        holder.mTvNo.setText("No " + (position + 1));
        holder.mTvAverage.setText(Double.toString(subjectsBean.getRating().getAverage()));
        mImageManagerService.getBitmap(mBaseActivity, subjectsBean.getImages().getSmall(), holder.mIvImg, R.drawable.img_default);
        holder.mTvName.setText(subjectsBean.getTitle());
        SpannableStringUtil.setClickText(subjectsBean.getYear() + " / ", R.color.font_link, subjectsBean.getGenres(), holder.mTvType);
        SpannableStringUtil.setClickText(R.string.director, R.color.font_link, subjectsBean.getDirectors(), holder.mTvDirector);
        SpannableStringUtil.setClickText(R.string.cast, R.color.font_link, subjectsBean.getCasts(), holder.mTvCast);
        holder.mLlItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActionUtil.gotoActivity(mBaseActivity, MovieActivity.class, subjectsBean.getId());
            }
        });
        holder.startAnimator();
    }

    @Override
    public int getItemCount() {
        return mSubjectsBeanList == null ? 0 : mSubjectsBeanList.size();
    }

    class ViewHolder extends AnimatorViewHolder {
        @BindView(R.id.top_tv_no)
        TextView mTvNo;
        @BindView(R.id.top_tv_average)
        TextView mTvAverage;
        @BindView(R.id.top_iv_img)
        ImageView mIvImg;
        @BindView(R.id.top_tv_name)
        TextView mTvName;
        @BindView(R.id.top_tv_type)
        TextView mTvType;
        @BindView(R.id.top_tv_director)
        TextView mTvDirector;
        @BindView(R.id.top_tv_cast)
        TextView mTvCast;
        @BindView(R.id.top_ll_item)
        LinearLayout mLlItem;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
