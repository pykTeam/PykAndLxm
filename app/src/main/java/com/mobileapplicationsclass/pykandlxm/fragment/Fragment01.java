package com.mobileapplicationsclass.pykandlxm.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;
import com.jude.rollviewpager.hintview.IconHintView;
import com.mobileapplicationsclass.pykandlxm.R;
import com.mobileapplicationsclass.pykandlxm.activity.StarActivity;
import com.mobileapplicationsclass.pykandlxm.adapter.SgridAdapter;
import com.mobileapplicationsclass.pykandlxm.adapter.TestLoopAdapter;
import com.mobileapplicationsclass.pykandlxm.base.BaseFragment;
import com.mobileapplicationsclass.pykandlxm.model.StarGrid;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;


/**
 * Created by Administrator on 2016/9/4 0004.
 */
public class Fragment01 extends BaseFragment {

    private String[] star_name = {"水瓶座", "双鱼座", "白羊座", "金牛座", "双子座", "巨蟹座"
            , "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "摩羯座"};
    private int[] star_logo = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};


    private List<StarGrid> list;
    private StarGrid mSd_data;
    private SgridAdapter sgridAdapter;


    @Bind(R.id.top_news_viewpager)
    RollPagerView topNewsViewpager;
    @Bind(R.id.star_grid)
    GridView mGridView;

    @Override
    public int setLayoutResouceId() {
        return R.layout.fragment01;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        initViewPager();
        initContent();
    }

    private void initViewPager() {

        //设置播放时间间隔
        topNewsViewpager.setPlayDelay(4000);
        //设置适配器
        topNewsViewpager.setAdapter(new TestLoopAdapter(topNewsViewpager, getActivity()));

        //设置指示器（顺序依次）
        //自定义指示器图片
        //设置圆点指示器颜色
        //设置文字指示器
        //隐藏指示器
        topNewsViewpager.setHintView(new IconHintView(getActivity(), R.mipmap.point_focus, R.mipmap.point_normal));
//        topNewsViewpager.setHintView(new ColorPointHintView(getActivity(), Color.YELLOW, Color.WHITE));
        //mRollViewPager.setHintView(new TextHintView(this));
        //mRollViewPager.setHintView(null);

    }

    private void initContent() {
        list = new ArrayList<>();
        for (int i = 0; i < star_name.length; i++) {
            String name = star_name[i];
            int logo = star_logo[i];
            mSd_data = new StarGrid(name, logo);
            list.add(mSd_data);
        }
        sgridAdapter = new SgridAdapter(getActivity(), list);
        mGridView.setAdapter(sgridAdapter);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), StarActivity.class);
                Bundle bundle = new Bundle();
                switch (position) {
                    case 0:
                        bundle.putString("starname", "水瓶座");
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                    case 1:
                        bundle.putString("starname", "双鱼座");
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                    case 2:
                        bundle.putString("starname", "白羊座");
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        topNewsViewpager.resume();
        topNewsViewpager.isPlaying();
    }

    @Override
    public void onPause() {
        super.onPause();
        topNewsViewpager.pause();

    }
    //    private class TestLoopAdapter extends LoopPagerAdapter {
//        private int[] imgs = {
//                R.mipmap.img1,
//                R.mipmap.img2,
//                R.mipmap.img3,
//                R.mipmap.img4,
//        };
//
//        public void setImgs(int[] imgs) {
//            this.imgs = imgs;
//        }
//
//
//        public TestLoopAdapter(RollPagerView viewPager) {
//            super(viewPager);
//        }
//
//        @Override
//        public View getView(ViewGroup container, int position) {
//            Log.i("RollViewPager", "getView:" + imgs[position]);
//
//            ImageView view = new ImageView(container.getContext());
//            view.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Log.i("RollViewPager", "onClick");
//                }
//            });
//            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//            Glide.with(getActivity())
//                    .load(imgs[position])
//                    .placeholder(R.mipmap.is_loading)
//                    .error(R.mipmap.is_loading)
//                    .into(view);
//            return view;
//        }
//
//        @Override
//        public int getRealCount() {
//            return imgs.length;
//        }
//    }
}
