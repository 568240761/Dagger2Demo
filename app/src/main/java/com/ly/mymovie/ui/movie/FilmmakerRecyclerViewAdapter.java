package com.ly.mymovie.ui.movie;

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
import com.ly.mymovie.data.model.ImageMessage;
import com.ly.mymovie.ui.base.BaseActivity;
import com.ly.mymovie.ui.filmmaker.FilmmakerActivity;
import com.ly.mymovie.util.ActionUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by LanYang on 2017/12/16.
 * Email:568240761@qq.com
 */

public class FilmmakerRecyclerViewAdapter extends RecyclerView.Adapter<FilmmakerRecyclerViewAdapter.ViewHolder> {
    private final ImageManagerService mImageManagerService;
    private final BaseActivity mBaseActivity;
    private List<ImageMessage> mImageMessageList;

    public void setImageMessageList(List<ImageMessage> imageMessageList) {
        mImageMessageList.addAll(imageMessageList);
    }

    @Inject
    public FilmmakerRecyclerViewAdapter(DataManager dataManager, BaseActivity baseActivity) {
        mBaseActivity = baseActivity;
        mImageManagerService = dataManager.getImageManagerService();
        mImageMessageList = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_filmmaker_item,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ImageMessage imageMessage = mImageMessageList.get(position);
        mImageManagerService.getBitmap(mBaseActivity,imageMessage.getUrl(),holder.mImageView,R.drawable.default_img);
        holder.mTextView.setText(imageMessage.getName());
        holder.mLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActionUtil.gotoActivity(mBaseActivity, FilmmakerActivity.class,imageMessage.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mImageMessageList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_iv_img)
        ImageView mImageView;
        @BindView(R.id.item_tv_name)
        TextView mTextView;
        @BindView(R.id.ll_movie)
        LinearLayout mLinearLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
