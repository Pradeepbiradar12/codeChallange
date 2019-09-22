package com.practice.monitoringBackend.ApiGateWay;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface WikiServie {

    @GET("wiki/Intuit")
    Call<ResponseBody> handShake();
}
