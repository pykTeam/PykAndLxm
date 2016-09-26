package com.mobileapplicationsclass.pykandlxm.app;

import android.app.Application;
import android.graphics.Bitmap;

import com.mobileapplicationsclass.pykandlxm.utils.OkHttp3Utils;
import com.mobileapplicationsclass.pykandlxm.widget.MyDownLoader;
import com.squareup.picasso.LruCache;
import com.squareup.picasso.Picasso;

import okhttp3.OkHttpClient;

/**
 * Created by Administrator on 2016/9/19 0019.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        //初始化单例的OKHttpClient对象
        initOkHttpUtils();

        //初始化单例的Picasso对象
        initPaicsso();
    }

    private void initOkHttpUtils() {
        OkHttpClient okHttpClient = OkHttp3Utils.getOkHttpSingletonInstance();
    }

    private void initPaicsso() {
        Picasso picasso = new Picasso.Builder(this)
                .memoryCache(new LruCache(10 << 20))
                .defaultBitmapConfig(Bitmap.Config.RGB_565)
                .downloader(new MyDownLoader())
                .indicatorsEnabled(true)
                .build();
        Picasso.setSingletonInstance(picasso);
    }

}
