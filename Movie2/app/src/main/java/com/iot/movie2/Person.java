package com.iot.movie2;

public class Person {

    private String name;
    private String mobile;
    private int profile;

    public Person(String name, String mobile, int profile) {
        this.name = name;
        this.mobile = mobile;
        this.profile = profile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getProfile() {
        return profile;
    }

    public void setProfile(int profile) {
        this.profile = profile;
    }

}
