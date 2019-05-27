package com.iot.eround.VO;

import com.google.gson.annotations.SerializedName;

public class BoardRegion {

    @SerializedName("regionName")
    private String regionName;

    public BoardRegion(String regionName) {
        this.regionName = regionName;
    }

    public String getRegionName() {
        return regionName;
    }

}
