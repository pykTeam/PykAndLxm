package com.mobileapplicationsclass.pykandlxm.retrofit;

import com.mobileapplicationsclass.pykandlxm.model.StarModel;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by Administrator on 2016/9/11 0011.
 */
public interface StarServerInterface {
    @GET("constellation/getAll?")
    Call<StarModel> getResult(@QueryMap Map<String, String> map);
}
