package com.ly.mymovie.ui.main.list;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ly.mymovie.ui.base.BaseFragment;

import java.util.List;

/**
 * Created by LanYang on 2017/12/14.
 * Email:568240761@qq.com
 */

public class ListFragmentPagerAdapter extends FragmentPagerAdapter {
    private List<BaseFragment> mBaseFragmentList;

    public void setBaseFragmentList(List<BaseFragment> baseFragmentList) {
        mBaseFragmentList = baseFragmentList;
    }

    public ListFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return mBaseFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mBaseFragmentList == null ? 0 : mBaseFragmentList.size();
    }
}
