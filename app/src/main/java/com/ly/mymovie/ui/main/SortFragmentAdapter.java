package com.ly.mymovie.ui.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ly.mymovie.R;
import com.ly.mymovie.data.model.bean.SortBean;
import com.ly.mymovie.ui.base.BaseActivity;
import com.ly.mymovie.ui.sort.SortActivity;
import com.ly.mymovie.util.ActionUtil;
import com.ly.mymovie.util.IdentifierUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by LanYang on 2017/12/24.
 * Email:568240761@qq.com
 */

public class SortFragmentAdapter extends RecyclerView.Adapter<SortFragmentAdapter.ViewHolder> {

    private List<SortBean> mSortBeanList;
    private final BaseActivity mBaseActivity;

    public void setSortBeanList(List<SortBean> sortBeanList) {
        mSortBeanList.addAll(sortBeanList);
    }

    @Inject
    public SortFragmentAdapter(BaseActivity baseActivity) {
        mBaseActivity = baseActivity;
        mSortBeanList = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_sort_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final SortBean sortBean = mSortBeanList.get(position);
        int id = IdentifierUtil.getMipmapId(mBaseActivity,sortBean.getImg());
        holder.mIvImg.setImageResource(id);
        holder.mTvName.setText(sortBean.getName());
        holder.mSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActionUtil.gotoActivity(mBaseActivity, SortActivity.class,sortBean.getName().replace("\u3000",""));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mSortBeanList == null ? 0 : mSortBeanList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_sort_img)
        ImageView mIvImg;
        @BindView(R.id.tv_sort_name)
        TextView mTvName;
        @BindView(R.id.ll_sort)
        LinearLayout mSort;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
