package com.iot.eroundRetrofit;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    public static final String URL = "http://192.168.0.82:8000/";

    @GET("eround/{path}")
    Call<ResponseBody> content(@Path("path") String path, @Query("num") String query);

}
