package com.iot.eround.VO;

import com.google.gson.annotations.SerializedName;

public class Feeling {

    @SerializedName("feelingEmoticon")
    private String feelingEmoticon;

    public Feeling(String feelingEmoticon) {
        this.feelingEmoticon = feelingEmoticon;
    }

    public String getFeelingEmoticon() {
        return feelingEmoticon;
    }

}