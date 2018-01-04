package com.ly.mymovie.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ly.mymovie.R;
import com.ly.mymovie.ui.base.BaseFragment;
import com.ly.mymovie.ui.main.list.ListFragmentPagerAdapter;
import com.ly.mymovie.ui.main.list.NorthAmericaFragment;
import com.ly.mymovie.ui.main.list.TopFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by LanYang on 2017/12/08.
 * Email:568240761@qq.com
 */

public class ListFragment extends BaseFragment {
    @BindView(R.id.tv_north_america)
    TextView mTvNorthAmerica;
    @BindView(R.id.tv_top)
    TextView mTvTop;
    @BindView(R.id.fg_list_view_pager)
    ViewPager mViewPager;

    private List<TextView> mTextViewList = new ArrayList<TextView>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        bindView(this, view);
        mToolbarStatus = parentActivity.enableToolbar();

        mTextViewList.add(mTvNorthAmerica);
        mTextViewList.add(mTvTop);

        initViewPager();

        return view;
    }

    private void initViewPager() {

        List<BaseFragment> list = new ArrayList<BaseFragment>();
        list.add(new NorthAmericaFragment());
        list.add(new TopFragment());

        ListFragmentPagerAdapter fragmentPagerAdapter = new ListFragmentPagerAdapter(parentActivity.getSupportFragmentManager());
        fragmentPagerAdapter.setBaseFragmentList(list);
        mViewPager.setAdapter(fragmentPagerAdapter);
        mViewPager.setCurrentItem(0);
        mViewPager.setOffscreenPageLimit(list.size());
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                changeTextColor(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @OnClick({R.id.tv_north_america, R.id.tv_top})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_north_america:
                mViewPager.setCurrentItem(0);
                changeTextColor(0);
                break;
            case R.id.tv_top:
                mViewPager.setCurrentItem(1);
                changeTextColor(1);
                break;
        }
    }

    private void changeTextColor(int position) {
        for (int i = 0; i < mTextViewList.size(); i++) {
            if (position == i) {
                mTextViewList.get(i).setTextColor(getResources().getColor(R.color.font_white));
            } else {
                mTextViewList.get(i).setTextColor(getResources().getColor(R.color.font_unselected));
            }
        }
    }
}
