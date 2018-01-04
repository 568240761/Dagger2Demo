package com.ly.mymovie.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewPropertyAnimator;

/**
 * Created by LanYang on 2017/12/27.
 * Email:568240761@qq.com
 */

public class AnimatorViewHolder extends RecyclerView.ViewHolder {
    private ViewPropertyAnimator animation;

    public AnimatorViewHolder(View itemView) {
        super(itemView);
        itemView.setAlpha(0f);
        animation = itemView.animate();
        animation.setDuration(500).alpha(1);
    }

    public void startAnimator(){
        animation.start();
    }
}
