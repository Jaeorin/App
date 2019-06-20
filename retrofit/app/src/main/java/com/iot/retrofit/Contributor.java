package com.iot.retrofit;

public class Contributor {

    String login;
    int contributions;
    String type;

    @Override
    public String toString() {
        return login + " (" + contributions + type + ")";
    }

}