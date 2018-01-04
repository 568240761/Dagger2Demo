package com.ly.mymovie.ui.main.list;

import com.ly.mymovie.data.model.Message;
import com.ly.mymovie.data.model.bean.SubjectsBox;
import com.ly.mymovie.ui.base.MvpView;

import java.util.List;

/**
 * Created by LanYang on 2017/12/14.
 * Email:568240761@qq.com
 */

public interface ListMvpView extends MvpView {
    void notifyAdapter(Message message);
    void notifyAdapterBox(List<SubjectsBox> list);
    void showErrorView();
}
