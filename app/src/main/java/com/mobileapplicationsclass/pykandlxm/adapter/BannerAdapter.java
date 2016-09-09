package com.mobileapplicationsclass.pykandlxm.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.List;

/**
 * Created by Administrator on 2016/9/8 0008.
 */
public class BannerAdapter extends PagerAdapter {

    private List<View> banner_list;

    public BannerAdapter(List<View> banner_list) {
        this.banner_list = banner_list;
    }

    @Override
    public int getCount() {
        return banner_list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(View container, int position) {
        // TODO Auto-generated method stub
        ((ViewPager) container).addView(banner_list.get(position % banner_list.size()));
        return banner_list.get(position % banner_list.size());
    }

    @Override
    public void destroyItem(View container, int position, Object object) {
        // TODO Auto-generated method stub
        ((ViewPager) container).removeView(banner_list.get(position % banner_list.size()));
    }
}