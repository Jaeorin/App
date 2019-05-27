package com.iot.eround.Util;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Streaming;

public interface ApiService {

    public static final String URL = "http://192.168.0.16:8000/";

    @GET("/{path1}/test/{path2}/{path3}")
    Call<ResponseBody> content(@Path("path1") String path1, @Path("path2") String path2, @Path("path3") int path3);

    @GET("/{path1}/{path2}")
    Call<ResponseBody> mainBackgroundImage(@Path("path1") String path1, @Path("path2") String path2);

}
