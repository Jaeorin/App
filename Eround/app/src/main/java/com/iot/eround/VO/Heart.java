package com.iot.eround.VO;

import android.graphics.Region;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Heart {

    @SerializedName("heartNum")@Expose private int heartNum;
    @SerializedName("user")@Expose private Users user;
    @SerializedName("board")@Expose private Board board;
    @SerializedName("reply")@Expose private Reply reply;
    @SerializedName("heartStatus")@Expose private int heartStatus;
    @SerializedName("heartCreateDate")@Expose private String heartCreateDate;
    @SerializedName("heartUpdateDate")@Expose private String heartUpdateDate;

    public Heart(int heartNum, Users user, Board board, Reply reply, int heartStatus, String heartCreateDate, String heartUpdateDate) {
        this.heartNum = heartNum;
        this.user = user;
        this.board = board;
        this.reply = reply;
        this.heartStatus = heartStatus;
        this.heartCreateDate = heartCreateDate;
        this.heartUpdateDate = heartUpdateDate;
    }

    public Heart(){}

    public int getHeartNum() {
        return heartNum;
    }

    public void setHeartNum(int heartNum) {
        this.heartNum = heartNum;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Reply getReply() {
        return reply;
    }

    public void setReply(Reply reply) {
        this.reply = reply;
    }

    public int getHeartStatus() {
        return heartStatus;
    }

    public void setHeartStatus(int heartStatus) {
        this.heartStatus = heartStatus;
    }

    public String getHeartCreateDate() {
        return heartCreateDate;
    }

    public void setHeartCreateDate(String heartCreateDate) {
        this.heartCreateDate = heartCreateDate;
    }

    public String getHeartUpdateDate() {
        return heartUpdateDate;
    }

    public void setHeartUpdateDate(String heartUpdateDate) {
        this.heartUpdateDate = heartUpdateDate;
    }
}
