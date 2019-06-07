package com.iot.eround.VO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Tags {

    @SerializedName("tagNum")@Expose private int tagNum;
    @SerializedName("tagName")@Expose private String tagName;
    @SerializedName("insertTag")@Expose private List<String> insertTag;
    @SerializedName("tagCreateDate")@Expose private String tagCreateDate;
    @SerializedName("tagUpdateDate")@Expose private String tagUpdateDate;

    public Tags(int tagNum, String tagName, List<String> insertTag, String tagCreateDate, String tagUpdateDate) {
        this.tagNum = tagNum;
        this.tagName = tagName;
        this.insertTag = insertTag;
        this.tagCreateDate = tagCreateDate;
        this.tagUpdateDate = tagUpdateDate;
    }

    public int getTagNum() {
        return tagNum;
    }

    public void setTagNum(int tagNum) {
        this.tagNum = tagNum;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public List<String> getInsertTag() {
        return insertTag;
    }

    public void setInsertTag(List<String> insertTag) {
        this.insertTag = insertTag;
    }

    public String getTagCreateDate() {
        return tagCreateDate;
    }

    public void setTagCreateDate(String tagCreateDate) {
        this.tagCreateDate = tagCreateDate;
    }

    public String getTagUpdateDate() {
        return tagUpdateDate;
    }

    public void setTagUpdateDate(String tagUpdateDate) {
        this.tagUpdateDate = tagUpdateDate;
    }
}
