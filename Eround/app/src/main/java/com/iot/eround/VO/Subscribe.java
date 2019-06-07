package com.iot.eround.VO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Subscribe {

    @SerializedName("subscribeNum")@Expose private int subscribeNum;
    @SerializedName("tag")@Expose private Tags tag;
    @SerializedName("user")@Expose private Users user;
    @SerializedName("subscribeCreateDate")@Expose private String subscribeCreateDate;
    @SerializedName("subscribeUpdateDate")@Expose private String subscribeUpdateDate;

    public Subscribe(int subscribeNum, Tags tag, Users user, String subscribeCreateDate, String subscribeUpdateDate) {
        this.subscribeNum = subscribeNum;
        this.tag = tag;
        this.user = user;
        this.subscribeCreateDate = subscribeCreateDate;
        this.subscribeUpdateDate = subscribeUpdateDate;
    }

    public int getSubscribeNum() {
        return subscribeNum;
    }

    public void setSubscribeNum(int subscribeNum) {
        this.subscribeNum = subscribeNum;
    }

    public Tags getTag() {
        return tag;
    }

    public void setTag(Tags tag) {
        this.tag = tag;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public String getSubscribeCreateDate() {
        return subscribeCreateDate;
    }

    public void setSubscribeCreateDate(String subscribeCreateDate) {
        this.subscribeCreateDate = subscribeCreateDate;
    }

    public String getSubscribeUpdateDate() {
        return subscribeUpdateDate;
    }

    public void setSubscribeUpdateDate(String subscribeUpdateDate) {
        this.subscribeUpdateDate = subscribeUpdateDate;
    }
}
