package com.mobileapplicationsclass.pykandlxm.retrofit;

import com.mobileapplicationsclass.pykandlxm.model.TandTModel;
import com.mobileapplicationsclass.pykandlxm.model.WandNModel;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by Administrator on 2016/9/11 0011.
 */
public interface TandTServerInterface {
    @GET("constellation/getAll?")
    Call<TandTModel> getResult(@QueryMap Map<String, String> map);
}

