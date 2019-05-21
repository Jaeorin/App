package com.iot.eroundRetrofit;

import com.google.gson.annotations.SerializedName;

public class ContentVOData {

    @SerializedName("content")
    private String content;
    @SerializedName("createDate")
    private String createDate;
    @SerializedName("emotion")
    private String emotion;
    @SerializedName("location")
    private String location;
}