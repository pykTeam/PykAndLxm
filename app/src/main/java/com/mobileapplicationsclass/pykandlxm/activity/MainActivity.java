package com.mobileapplicationsclass.pykandlxm.activity;


import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.mobileapplicationsclass.pykandlxm.Fragemnt.Fragment01;
import com.mobileapplicationsclass.pykandlxm.Fragemnt.Fragment02;
import com.mobileapplicationsclass.pykandlxm.Fragemnt.Fragment03;
import com.mobileapplicationsclass.pykandlxm.Fragemnt.Fragment04;
import com.mobileapplicationsclass.pykandlxm.Fragemnt.Fragment05;
import com.mobileapplicationsclass.pykandlxm.Fragemnt.Fragment06;
import com.mobileapplicationsclass.pykandlxm.Fragemnt.Fragment07;
import com.mobileapplicationsclass.pykandlxm.Fragemnt.Fragment08;
import com.mobileapplicationsclass.pykandlxm.Fragemnt.Fragment09;
import com.mobileapplicationsclass.pykandlxm.Fragemnt.Fragment10;
import com.mobileapplicationsclass.pykandlxm.Fragemnt.Fragment11;
import com.mobileapplicationsclass.pykandlxm.Fragemnt.Fragment12;
import com.mobileapplicationsclass.pykandlxm.R;
import com.mobileapplicationsclass.pykandlxm.adapter.DlistAdapter;
import com.mobileapplicationsclass.pykandlxm.adapter.MyFragmentPagerAdapter;
import com.mobileapplicationsclass.pykandlxm.base.BaseActivity;
import com.mobileapplicationsclass.pykandlxm.model.DrawerList;
import com.mobileapplicationsclass.pykandlxm.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;


/**
 * Created by Administrator on 2016/8/31 0031.
 */
public class MainActivity extends BaseActivity {
    //定义假数据
    private String[] list_name = {"主页", "登录", "退出", "关于"};
    private int[] list_logo = {R.mipmap.bili_anim_tv_chan_1, R.mipmap.bili_anim_tv_chan_3
            , R.mipmap.bili_anim_tv_chan_5, R.mipmap.bili_anim_tv_chan_7};
    private String[] constellation_name = {"水瓶座 ", "双鱼座", "双鱼座", "白羊座", "双子座", "巨蟹座"
            , "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "摩羯座"};

    private DrawerList mDl_data;
    private List<DrawerList> mDl_List;
    private DlistAdapter dlistAdapter;

    //页卡标题集合
    private List<String> mTitleList;
    //页卡视图集合
    private List<Fragment> mViewList;

    private ActionBarDrawerToggle mdrawerToggle;

    private List<String> tabIndicators;


    @Bind(R.id.drawer_list)
    ListView mListView;
    @Bind(R.id.life_drawer)
    LinearLayout mLinearLayout;
    @Bind(R.id.tb_toolbar)
    Toolbar mToolbar;
    @Bind(R.id.tab)
    TabLayout tab;
    @Bind(R.id.dl_root)
    DrawerLayout mDrawerLayout;
    @Bind(R.id.vpView)
    ViewPager mViewPager;


    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        initDrawer();
        contentDrawe();
        initContent();
    }

    @Override
    public void initToolBar() {
        setSupportActionBar(mToolbar);
        //开启ActionBar上App icon功能
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initDrawer() {
        mdrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
//                mToolbar.setTitle("关于星座");
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                String mTitle = (String) getTitle();
//                mToolbar.setTitle(mTitle);
                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mdrawerToggle);

    }

    private void contentDrawe() {
        mDl_List = new ArrayList<>();
        for (int i = 0; i < list_name.length; i++) {
            String name = list_name[i];
            int logo = list_logo[i];
            mDl_data = new DrawerList(name, logo);
            mDl_List.add(mDl_data);
        }
        dlistAdapter = new DlistAdapter(this, mDl_List);
        mListView.setAdapter(dlistAdapter);
    }

    private void initContent() {

        Fragment01 fre01 = new Fragment01();
        Fragment02 fre02 = new Fragment02();
        Fragment03 fre03 = new Fragment03();
        Fragment04 fre04 = new Fragment04();
        Fragment05 fre05 = new Fragment05();
        Fragment06 fre06 = new Fragment06();
        Fragment07 fre07 = new Fragment07();
        Fragment08 fre08 = new Fragment08();
        Fragment09 fre09 = new Fragment09();
        Fragment10 fre10 = new Fragment10();
        Fragment11 fre11 = new Fragment11();
        Fragment12 fre12 = new Fragment12();

        mViewList = new ArrayList<>();
        mViewList.add(fre01);
        mViewList.add(fre02);
        mViewList.add(fre03);
        mViewList.add(fre04);
        mViewList.add(fre05);
        mViewList.add(fre06);
        mViewList.add(fre07);
        mViewList.add(fre08);
        mViewList.add(fre09);
        mViewList.add(fre10);
        mViewList.add(fre11);
        mViewList.add(fre12);


        mTitleList = new ArrayList<>();
        for (int i = 0; i < constellation_name.length; i++) {
            mTitleList.add(constellation_name[i]);
        }

        tab.setTabMode(TabLayout.MODE_SCROLLABLE);
        for (int i = 0; i < constellation_name.length; i++) {
            tab.addTab(tab.newTab().setText(mTitleList.get(i)));
        }

        MyFragmentPagerAdapter myFragmentPagerAdapter = new MyFragmentPagerAdapter(
                getSupportFragmentManager(), mViewList, mTitleList);
        mViewPager.setAdapter(myFragmentPagerAdapter);
        tab.setupWithViewPager(mViewPager);
        tab.setTabsFromPagerAdapter(myFragmentPagerAdapter);


    }

    //在配置中改变打开抽屉的图标
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mdrawerToggle.onConfigurationChanged(newConfig);
    }

    //创建图标，实现动画
    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mdrawerToggle.syncState();
    }

    //选项菜单的准备动作
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean isDrawerOpen = mDrawerLayout.isDrawerOpen(mLinearLayout);
        menu.findItem(R.id.id_action_download).setVisible(!isDrawerOpen);
        menu.findItem(R.id.id_action_search).setVisible(!isDrawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    //如果有Menu,创建完后,系统会自动添加到ToolBar上
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //将toolbar上的图标与Drawer结合。实现点击开关抽屉
        if (mdrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {
            case R.id.id_action_download:
                //离线缓存
                ToastUtil.showToast(this, "清理缓存");
                break;

            case R.id.id_action_search:
                //搜索
                ToastUtil.showToast(this, "搜索");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
