package com.mobileapplicationsclass.pykandlxm.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Administrator on 2016/9/6 0006.
 * 小工具类
 */
public class CommonUtils {
    /**
     * 检查是否有网络
     */
    public static boolean isNetworkAvailable(Context context)
    {

        NetworkInfo info = getNetworkInfo(context);
        if (info != null)
        {
            return info.isAvailable();
        }
        return false;
    }
    private static NetworkInfo getNetworkInfo(Context context)
    {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo();
    }
}
