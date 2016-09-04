package com.mobileapplicationsclass.pykandlxm.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/9/4 0004.
 */
public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mViewList;
    private List<String> mTitleList;

    public MyFragmentPagerAdapter(FragmentManager fm, List<Fragment> mViewList, List<String> mTitleList) {
        super(fm);
        this.mViewList = mViewList;
        this.mTitleList = mTitleList;
    }

    @Override
    public Fragment getItem(int position) {
        return mViewList.get(position);
    }

    @Override
    public int getCount() {
        return mViewList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitleList.get(position);
    }
}
