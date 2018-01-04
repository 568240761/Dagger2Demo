package com.ly.mymovie.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.ly.mymovie.R;
import com.ly.mymovie.ui.base.BaseActivity;
import com.ly.mymovie.ui.search.SearchActivity;
import com.ly.mymovie.util.ActionUtil;
import com.ly.mymovie.util.Constant;
import com.ly.mymovie.util.PermissionUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by LanYang on 2017/12/07.
 * Email:568240761@qq.com
 */

public class MainActivity extends BaseActivity {
    private final int STATUS_HIT = 0;
    private final int STATUS_COMING = STATUS_HIT + 1;
    private final int STATUS_SORT = STATUS_COMING + 1;
    private final int STATUS_LIST = STATUS_SORT + 1;

    @BindView(R.id.activity_main_bottom_bar_tv_hit)
    TextView mTvHit;
    @BindView(R.id.activity_main_bottom_bar_tv_coming)
    TextView mTvComing;
    @BindView(R.id.activity_main_bottom_bar_tv_list)
    TextView mTvList;
    @BindView(R.id.activity_main_bottom_bar_tv_sort)
    TextView mTvSort;
    @BindView(R.id.activity_main_bottom_bar_iv_hit)
    ImageView mIvHit;
    @BindView(R.id.activity_main_bottom_bar_iv_coming)
    ImageView mIvComing;
    @BindView(R.id.activity_main_bottom_bar_iv_sort)
    ImageView mIvSort;
    @BindView(R.id.activity_main_bottom_bar_iv_list)
    ImageView mIvList;
    @BindView(R.id.toolbar_city)
    TextView mToolbarLocation;
    @BindView(R.id.activity_main_content)
    FrameLayout mContent;


    private FragmentManager mFragmentManager;
    private HitFragment mHitFragment;
    private ComingFragment mComingFragment;
    private SortFragment mSortFragment;
    private ListFragment mListFragment;

    //默认选中正在热映，即为0；即将上映-1；分类-2；排行榜-3
    private int status = 0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindView(this);

        setDefaultContent();
    }

    private void setDefaultContent() {
        mHitFragment = new HitFragment();
        mFragmentManager = getSupportFragmentManager();
        FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.activity_main_content, mHitFragment);
        mFragmentTransaction.commit();
        changeBottomBarItem(status);
        mToolbarTitle.setText(R.string.activity_main_bottom_bar_item_hit);
        mToolbarLocation.setText(R.string.locating);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == HitMvpPresenter.REQUEST_CODE_LOCATION) {
            mHitFragment.mHitMvpPresenter.startLocation();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == Constant.REQUEST_CODE_PERMISSION_LOCATION) {
            if (PermissionUtil.verifyPermissions(grantResults)) {
                mHitFragment.mHitMvpPresenter.startLocation();
            }
        }
    }

    @OnClick({R.id.activity_main_bottom_bar_ll_hit, R.id.activity_main_bottom_bar_ll_coming, R.id.activity_main_bottom_bar_ll_sort, R.id.activity_main_bottom_bar_ll_list, R.id.toolbar_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.activity_main_bottom_bar_ll_hit:
                if (status != STATUS_HIT) {
                    status = STATUS_HIT;
                    changeBottomBarItem(status);
                    FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
                    mFragmentTransaction.show(mHitFragment);
                    if (mComingFragment != null) {
                        mFragmentTransaction.hide(mComingFragment);
                    }
                    if (mSortFragment != null) {
                        mFragmentTransaction.hide(mSortFragment);
                    }
                    if (mListFragment != null) {
                        mFragmentTransaction.hide(mListFragment);
                    }
                    mFragmentTransaction.commit();
                }
                break;
            case R.id.activity_main_bottom_bar_ll_coming:
                if (status != STATUS_COMING) {
                    status = STATUS_COMING;
                    changeBottomBarItem(status);
                    FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
                    if (mComingFragment == null) {
                        mComingFragment = new ComingFragment();
                        mFragmentTransaction.add(R.id.activity_main_content, mComingFragment);
                    } else {
                        mFragmentTransaction.show(mComingFragment);
                    }
                    if (mHitFragment != null) {
                        mFragmentTransaction.hide(mHitFragment);
                    }
                    if (mSortFragment != null) {
                        mFragmentTransaction.hide(mSortFragment);
                    }
                    if (mListFragment != null) {
                        mFragmentTransaction.hide(mListFragment);
                    }
                    mFragmentTransaction.commit();
                }
                break;
            case R.id.activity_main_bottom_bar_ll_sort:
                if (status != STATUS_SORT) {
                    status = STATUS_SORT;
                    changeBottomBarItem(status);
                    FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
                    if (mSortFragment == null) {
                        mSortFragment = new SortFragment();
                        mFragmentTransaction.add(R.id.activity_main_content, mSortFragment);
                    } else {
                        mFragmentTransaction.show(mSortFragment);
                    }
                    if (mHitFragment != null) {
                        mFragmentTransaction.hide(mHitFragment);
                    }
                    if (mComingFragment != null) {
                        mFragmentTransaction.hide(mComingFragment);
                    }
                    if (mListFragment != null) {
                        mFragmentTransaction.hide(mListFragment);
                    }
                    mFragmentTransaction.commit();
                }
                break;
            case R.id.activity_main_bottom_bar_ll_list:
                if (status != STATUS_LIST) {
                    status = STATUS_LIST;
                    changeBottomBarItem(status);
                    FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
                    if (mListFragment == null) {
                        mListFragment = new ListFragment();
                        mFragmentTransaction.add(R.id.activity_main_content, mListFragment);
                    } else {
                        mFragmentTransaction.show(mListFragment);
                    }
                    if (mHitFragment != null) {
                        mFragmentTransaction.hide(mHitFragment);
                    }
                    if (mComingFragment != null) {
                        mFragmentTransaction.hide(mComingFragment);
                    }
                    if (mSortFragment != null) {
                        mFragmentTransaction.hide(mSortFragment);
                    }
                    mFragmentTransaction.commit();
                }
                break;
            case R.id.toolbar_search:
                ActionUtil.gotoActivity(this, SearchActivity.class, null);
                break;
        }
    }

    private void changeBottomBarItem(int status) {
        mTvHit.setTextColor(getResources().getColor(R.color.activity_main_bottom_bar_text_default));
        mIvHit.setBackgroundResource(R.drawable.bottom_bar_img_hit_default);

        mTvComing.setTextColor(getResources().getColor(R.color.activity_main_bottom_bar_text_default));
        mIvComing.setBackgroundResource(R.drawable.bottom_bar_img_coming_default);

        mTvSort.setTextColor(getResources().getColor(R.color.activity_main_bottom_bar_text_default));
        mIvSort.setBackgroundResource(R.drawable.bottom_bar_img_sort_default);

        mTvList.setTextColor(getResources().getColor(R.color.activity_main_bottom_bar_text_default));
        mIvList.setBackgroundResource(R.drawable.bottom_bar_img_list_default);

        switch (status) {
            case STATUS_HIT:
                mTvHit.setTextColor(getResources().getColor(R.color.activity_main_bottom_bar_text_selected));
                mIvHit.setBackgroundResource(R.drawable.bottom_bar_img_hit_selected);
                mToolbarTitle.setText(getString(R.string.activity_main_bottom_bar_item_hit));
                break;
            case STATUS_COMING:
                mTvComing.setTextColor(getResources().getColor(R.color.activity_main_bottom_bar_text_selected));
                mIvComing.setBackgroundResource(R.drawable.bottom_bar_img_coming_selected);
                mToolbarTitle.setText(getString(R.string.activity_main_bottom_bar_item_coming));
                break;
            case STATUS_SORT:
                mTvSort.setTextColor(getResources().getColor(R.color.activity_main_bottom_bar_text_selected));
                mIvSort.setBackgroundResource(R.drawable.bottom_bar_img_sort_selected);
                mToolbarTitle.setText(getString(R.string.activity_main_bottom_bar_item_sort));
                break;
            case STATUS_LIST:
                mTvList.setTextColor(getResources().getColor(R.color.activity_main_bottom_bar_text_selected));
                mIvList.setBackgroundResource(R.drawable.bottom_bar_img_list_selected);
                mToolbarTitle.setText(getString(R.string.activity_main_bottom_bar_item_list));
                break;
        }
    }

    public void showLocation(String city) {
        if (TextUtils.isEmpty(city)) {
            mToolbarLocation.setText(R.string.located);
        } else {
            mToolbarLocation.setText(city);
        }
    }
}
