package com.iot.eround.Util;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    public static final String URL = "http://192.168.0.9:8000/";

    @GET("applicant/test/{path}")
    Call<ResponseBody> content(@Path("path") String path);

}
