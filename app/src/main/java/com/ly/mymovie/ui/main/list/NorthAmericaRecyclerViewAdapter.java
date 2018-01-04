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
import com.ly.mymovie.data.model.bean.SubjectsBox;
import com.ly.mymovie.ui.adapter.AnimatorViewHolder;
import com.ly.mymovie.ui.base.BaseActivity;
import com.ly.mymovie.ui.movie.MovieActivity;
import com.ly.mymovie.util.ActionUtil;
import com.ly.mymovie.util.SpannableStringUtil;
import com.ly.mymovie.util.TextUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by LanYang on 2017/12/14.
 * Email:568240761@qq.com
 */

public class NorthAmericaRecyclerViewAdapter extends RecyclerView.Adapter<NorthAmericaRecyclerViewAdapter.ViewHolder>{
    private BaseActivity mBaseActivity;
    private List<SubjectsBox> mSubjectsBeanList;
    private final ImageManagerService mImageManagerService;
    public void addSubjectsBeanList(List<SubjectsBox> subjectsBeanList) {
        mSubjectsBeanList.addAll(subjectsBeanList);
        notifyDataSetChanged();
    }

    public void clearSubjectsBeanList(){
        mSubjectsBeanList.clear();
        notifyDataSetChanged();
    }
    @Inject
    public NorthAmericaRecyclerViewAdapter(BaseActivity baseActivity, DataManager dataManager){
        mBaseActivity = baseActivity;
        mImageManagerService = dataManager.getImageManagerService();
        mSubjectsBeanList = new ArrayList<>();
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_north_america_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SubjectsBox subjectsBox = mSubjectsBeanList.get(position);
        final SubjectsBean subjectsBean = subjectsBox.getSubject();
        holder.mTvMoney.setText(TextUtil.formatMoney(subjectsBox.getBox()));
        holder.mTvAverage.setText(Double.toString(subjectsBean.getRating().getAverage()));
        mImageManagerService.getBitmap(mBaseActivity,subjectsBean.getImages().getSmall(),holder.mIvImg,R.drawable.img_default);
        holder.mTvName.setText(subjectsBean.getTitle());
        SpannableStringUtil.setClickText(R.string.type,R.color.font_link,subjectsBean.getGenres(),holder.mTvType);
        SpannableStringUtil.setClickText(R.string.director,R.color.font_link,subjectsBean.getDirectors(),holder.mTvDirector);
        SpannableStringUtil.setClickText(R.string.cast,R.color.font_link,subjectsBean.getCasts(),holder.mTvCast);
        holder.mLlItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActionUtil.gotoActivity(mBaseActivity, MovieActivity.class,subjectsBean.getId());
            }
        });
        holder.startAnimator();
    }

    @Override
    public int getItemCount() {
        return mSubjectsBeanList == null ? 0 : mSubjectsBeanList.size();
    }

    class ViewHolder extends AnimatorViewHolder{
        @BindView(R.id.box_tv_money)
        TextView mTvMoney;
        @BindView(R.id.box_tv_average)
        TextView mTvAverage;
        @BindView(R.id.box_iv_img)
        ImageView mIvImg;
        @BindView(R.id.box_tv_name)
        TextView mTvName;
        @BindView(R.id.box_tv_type)
        TextView mTvType;
        @BindView(R.id.box_tv_director)
        TextView mTvDirector;
        @BindView(R.id.box_tv_cast)
        TextView mTvCast;
        @BindView(R.id.box_ll_item)
        LinearLayout mLlItem;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
