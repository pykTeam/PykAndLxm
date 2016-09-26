package com.mobileapplicationsclass.pykandlxm.retrofit;

import com.mobileapplicationsclass.pykandlxm.model.FineModel;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by Administrator on 2016/9/19 0019.
 */
public interface FineServerInterface {
    @GET("weixin/query?")
    Call<FineModel> getResult(@QueryMap Map<String, String> map);
}
