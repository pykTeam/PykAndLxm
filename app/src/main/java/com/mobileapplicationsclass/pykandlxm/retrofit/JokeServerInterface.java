package com.mobileapplicationsclass.pykandlxm.retrofit;

import com.mobileapplicationsclass.pykandlxm.model.JokeModel;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by Administrator on 2016/9/22 0022.
 */
public interface JokeServerInterface {
    @GET("content/text.from?")
    Call<JokeModel> getResult(@QueryMap Map<String, String> map);
}
