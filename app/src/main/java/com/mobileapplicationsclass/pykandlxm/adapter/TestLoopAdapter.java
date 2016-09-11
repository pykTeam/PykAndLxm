package com.mobileapplicationsclass.pykandlxm.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;
import com.mobileapplicationsclass.pykandlxm.R;

/**
 * Created by Administrator on 2016/9/11 0011.
 */
public class TestLoopAdapter extends LoopPagerAdapter {
    private Context context;
    private int[] imgs = {
            R.mipmap.img1,
            R.mipmap.img2,
            R.mipmap.img3,
            R.mipmap.img4,
    };

    public void setImgs(int[] imgs) {
        this.imgs = imgs;
    }


    public TestLoopAdapter(RollPagerView viewPager, Context context) {
        super(viewPager);
        this.context = context;
    }


    @Override
    public View getView(ViewGroup container, int position) {
        Log.i("RollViewPager", "getView:" + imgs[position]);

        ImageView view = new ImageView(container.getContext());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("RollViewPager", "onClick");
            }
        });
        view.setScaleType(ImageView.ScaleType.CENTER_CROP);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        Glide.with(context)
                .load(imgs[position])
                .placeholder(R.mipmap.is_loading)
                .error(R.mipmap.is_loading)
                .into(view);
        return view;
    }

    @Override
    public int getRealCount() {
        return imgs.length;
    }
}

