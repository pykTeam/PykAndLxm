package com.mobileapplicationsclass.pykandlxm.activity;


import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.mobileapplicationsclass.pykandlxm.R;
import com.mobileapplicationsclass.pykandlxm.adapter.DlistAdapter;
import com.mobileapplicationsclass.pykandlxm.adapter.MyFragmentPagerAdapter;
import com.mobileapplicationsclass.pykandlxm.base.BaseActivity;
import com.mobileapplicationsclass.pykandlxm.fragment.Fragment01;
import com.mobileapplicationsclass.pykandlxm.fragment.Fragment02;
import com.mobileapplicationsclass.pykandlxm.fragment.Fragment03;
import com.mobileapplicationsclass.pykandlxm.fragment.Fragment04;
import com.mobileapplicationsclass.pykandlxm.model.DrawerList;
import com.mobileapplicationsclass.pykandlxm.utils.ToastUtil;
import com.mobileapplicationsclass.pykandlxm.widget.ActionSheetDialog;
import com.mobileapplicationsclass.pykandlxm.widget.CircleImageView;
import com.mobileapplicationsclass.pykandlxm.widget.SlidingTabLayout;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;


/**
 * Created by Administrator on 2016/8/31 0031.
 */
public class MainActivity extends BaseActivity {


    //定义假数据
    private String[] list_name = {"主页", "登录", "退出", "关于"};
    private int[] list_logo = {R.mipmap.bili_anim_tv_chan_1, R.mipmap.bili_anim_tv_chan_3
            , R.mipmap.bili_anim_tv_chan_5, R.mipmap.bili_anim_tv_chan_7};
    private String[] constellation_name = {"运势 ", "物语", "精选", "收藏"};

    /**
     * 指定拍摄图片文件位置避免获取到缩略图
     */
    private File outFile;
    /**
     * 标记是拍照还是相册 0是拍照1是相册
     */
    private int cameraorpic;
    /**
     * 选择头像相册选取
     */
    private static final int REQUESTCODE_PICK = 1;
    /**
     * 裁剪好头像-设置头像
     */
    private static final int REQUESTCODE_CUTTING = 2;
    /**
     * 选择头像拍照选取
     */
    private static final int PHOTO_REQUEST_TAKEPHOTO = 3;
    /**
     * 裁剪好的头像的Bitmap
     */
    private Bitmap currentBitmap;

    private DrawerList mDl_data;
    private List<DrawerList> list;
    private DlistAdapter dlistAdapter;

    //页卡标题集合
    private List<String> mTitleList;
    //页卡视图集合
    private List<Fragment> mViewList;

    private ActionBarDrawerToggle mdrawerToggle;


    @Bind(R.id.drawer_list)
    ListView mListView;
    @Bind(R.id.head_image)
    CircleImageView headImage;
    @Bind(R.id.life_drawer)
    LinearLayout mLinearLayout;
    @Bind(R.id.tb_toolbar)
    Toolbar mToolbar;
    @Bind(R.id.tab)
    SlidingTabLayout tab;
    @Bind(R.id.dl_root)
    DrawerLayout mDrawerLayout;
    @Bind(R.id.vpView)
    ViewPager mViewPager;
    @Bind(R.id.iv_background)
    ImageView ivBackground;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        initDrawer();
        contentDrawe();
        initTab();
        initContent();
    }

    @Override
    public void initToolBar() {
        mToolbar.setTitle("星座运势");
        mToolbar.setLogo(R.mipmap.ic_bili_logo);
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

        headImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ActionSheetDialog(MainActivity.this).Builder()

                        .addSheetItem("拍照", ActionSheetDialog.SheetItemColor.BULE, new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int witch) {
                                cameraorpic = 1;
                                openCamera();
                            }
                        }).addSheetItem("打开相册", ActionSheetDialog.SheetItemColor.BULE, new ActionSheetDialog.OnSheetItemClickListener() {
                    @Override
                    public void onClick(int witch) {
                        cameraorpic = 0;
                        openPic();
                    }
                }).show();
            }
        });

        list = new ArrayList<>();
        for (int i = 0; i < list_name.length; i++) {
            String name = list_name[i];
            int logo = list_logo[i];
            mDl_data = new DrawerList(name, logo);
            list.add(mDl_data);
        }
        dlistAdapter = new DlistAdapter(this, list);
        mListView.setAdapter(dlistAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 1:
                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
                        finish();
                        break;
                }
            }
        });
    }

    private void initTab() {
        tab.setTabTitleTextSize(15);//标题字体大小
        tab.setTitleTextColor(Color.WHITE, 0xFFFFCCD0);//标题字体颜色
        tab.setTabStripWidth(120);//滑动条宽度
        tab.setSelectedIndicatorColors(Color.WHITE);//滑动条颜色
        tab.setDistributeEvenly(true); //均匀平铺选项卡

    }

    private void initContent() {

        Fragment01 fre01 = new Fragment01();
        Fragment02 fre02 = new Fragment02();
        Fragment03 fre03 = new Fragment03();
        Fragment04 fre04 = new Fragment04();

        mViewList = new ArrayList<>();
        mViewList.add(fre01);
        mViewList.add(fre02);
        mViewList.add(fre03);
        mViewList.add(fre04);

        mTitleList = new ArrayList<>();
        for (int i = 0; i < constellation_name.length; i++) {
            mTitleList.add(constellation_name[i]);
        }

        MyFragmentPagerAdapter myFragmentPagerAdapter = new MyFragmentPagerAdapter(
                getSupportFragmentManager(), mViewList, mTitleList);
        mViewPager.setAdapter(myFragmentPagerAdapter);
        mViewPager.setOffscreenPageLimit(4);//缓存其他所有fragment的内容
        tab.setViewPager(mViewPager);//最后调用此方法
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
        menu.findItem(R.id.id_action_share).setVisible(!isDrawerOpen);
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
            case R.id.id_action_share:
                //分享
                ToastUtil.showToast(this, "分享");
                break;

            case R.id.id_action_search:
                //搜索
//                startActivity(new Intent(MainActivity.this, WebActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //头像背景点击效果
    @OnClick(R.id.iv_background)
    public void onClick() {

    }

    /**
     * 打开相册
     */
    private void openPic() {
        Intent pickIntent = new Intent(Intent.ACTION_PICK, null);
        pickIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(pickIntent, REQUESTCODE_PICK);

    }

    /**
     * 打开相机
     */
    private void openCamera() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            File outDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            if (!outDir.exists()) {
                outDir.mkdirs();
            }
            outFile = new File(outDir, System.currentTimeMillis() + ".jpg");
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(outFile));
            intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
            startActivityForResult(intent, PHOTO_REQUEST_TAKEPHOTO);
        } else {
            Log.e("CAMERA", "请确认已经插入SD卡");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //  进行判断是那个操作跳转回来的，如果是裁剪跳转回来的这块就要把图片现实到View上，其他两种的话都把数据带入裁剪界面
        switch (requestCode) {
            //相册
            case REQUESTCODE_PICK:
                if (data == null || data.getData() == null) {
                    return;
                }
                startPhotoZoom(data.getData());
                break;
            //裁剪
            case REQUESTCODE_CUTTING:
                if (data != null) {
                    setPicToView(data);
                }
                break;
            //拍照
            case PHOTO_REQUEST_TAKEPHOTO:
                startPhotoZoom(Uri.fromFile(outFile));
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 把裁剪好的图片设置到View上或者上传到网络
     *
     * @param data
     */
    private void setPicToView(Intent data) {
        Bundle extras = data.getExtras();
        if (extras != null) {
            /** 可用于图像上传 */
            currentBitmap = extras.getParcelable("data");
            headImage.setImageBitmap(currentBitmap);
        }
    }

    /**
     * 调用系统的图片裁剪
     *
     * @param data
     */
    private void startPhotoZoom(Uri data) {

        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(data, "image/*");
        intent.putExtra("crop", true);
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("scale", true);//黑边
        intent.putExtra("scaleUpIfNeeded", true);//黑边
        intent.putExtra("return-data", true);
        intent.putExtra("noFaceDetection", true);
        startActivityForResult(intent, REQUESTCODE_CUTTING);

    }



}
