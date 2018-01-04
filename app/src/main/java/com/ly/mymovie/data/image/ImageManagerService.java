package com.ly.mymovie.data.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.DrawableRes;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.request.target.Target;
import com.ly.mymovie.ui.base.BaseActivity;
import com.ly.mymovie.ui.base.BaseFragment;

import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

/**
 * Created by LanYang on 2017/12/13.
 * Email:568240761@qq.com
 */

public class ImageManagerService {

    @Inject
    public ImageManagerService() {
    }

    /**
     * 获取图片
     *
     * @param baseFragment 传入Fragment实例；Glide会在Fragment销毁时，释放图片资源
     * @param url          图片url
     * @param imageView
     */
    public void getBitmap(BaseFragment baseFragment, String url, ImageView imageView, @DrawableRes int drawable) {
        GlideApp.with(baseFragment)
                .load(url)
                .placeholder(drawable)
                .fitCenter()
                .into(imageView);
    }

    public void getBitmap(BaseActivity baseActivity, String url, ImageView imageView,@DrawableRes int drawable) {
        GlideApp.with(baseActivity)
                .load(url)
                .placeholder(drawable)
                .fitCenter()
                .into(imageView);
    }

    /**
     * 获取原图
     *
     * @param context
     * @param url
     * @return
     */
    public void getBitmap(final Context context, final String url, final View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    final Bitmap bitmap = GlideApp.with(context)
                            .asBitmap()
                            .load(url)
                            .submit(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                            .get();
                    view.post(new Runnable() {
                        @Override
                        public void run() {
                            view.setBackgroundDrawable(new BitmapDrawable(bitmap));
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
