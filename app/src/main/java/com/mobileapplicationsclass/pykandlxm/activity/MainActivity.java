package com.mobileapplicationsclass.pykandlxm.activity;


import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.mobileapplicationsclass.pykandlxm.R;
import com.mobileapplicationsclass.pykandlxm.adapter.DlistAdapter;
import com.mobileapplicationsclass.pykandlxm.base.BaseActivity;
import com.mobileapplicationsclass.pykandlxm.model.DrawerList;
import com.mobileapplicationsclass.pykandlxm.utils.ToastUtil;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by Administrator on 2016/8/31 0031.
 */
public class MainActivity extends BaseActivity {

    private DrawerList dl_data;
    private List<DrawerList> dl_List;
    private DlistAdapter dlistAdapter;
    private ActionBarDrawerToggle mdrawerToggle;
    @Bind(R.id.drawer_list)
    ListView mListView;
    @Bind(R.id.life_drawer)
    LinearLayout mLinearLayout;
    @Bind(R.id.tb_toolbar)
    Toolbar mToolbar;
    @Bind(R.id.dl_root)
    DrawerLayout mDrawerLayout;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {

        initDrawer();
        contentDrawe();

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
        dl_List = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            dl_data = new DrawerList("关于星座"+i);
            dl_List.add(dl_data);
        }
        dlistAdapter = new DlistAdapter(this, dl_List);
        mListView.setAdapter(dlistAdapter);
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
