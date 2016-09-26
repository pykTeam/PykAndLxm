package com.mobileapplicationsclass.pykandlxm.utils;

import okhttp3.OkHttpClient;

/**
 * Created by Administrator on 2016/9/17 0017.
 */
public class OkHttp3Utils {

    private static OkHttpClient okHttpClient = null;

    public static OkHttpClient getOkHttpSingletonInstance() {
        if (okHttpClient == null) {
            synchronized (OkHttp3Utils.class) {
                if (okHttpClient == null) {
                    okHttpClient = new OkHttpClient();
                }
            }
        }
        return okHttpClient;
    }

}