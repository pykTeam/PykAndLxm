package com.mobileapplicationsclass.pykandlxm.retrofit;

import com.mobileapplicationsclass.pykandlxm.model.WandNModel;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by Administrator on 2016/9/14 0014.
 */
public interface WandNServerInterface {
    @GET("constellation/getAll?")
    Call<WandNModel> getResult(@QueryMap Map<String, String> map);
}
