package com.iot.eround.VO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Reply {

    @SerializedName("replyNum")@Expose private int replyNum;
    @SerializedName("replyContent")@Expose private String replyContent;
    @SerializedName("replyStatus")@Expose private int replyStatus;
    @SerializedName("replyCreateDate")@Expose private String replyCreateDate;
    @SerializedName("replyUpdateDate")@Expose private String replyUpdateDate;
    @SerializedName("board")@Expose private Board board;
    @SerializedName("toReply")@Expose private Reply toReply;
    @SerializedName("user")@Expose private Users user;
    @SerializedName("heart")@Expose private List<Heart> heart;

    public Reply(int replyNum, String replyContent, int replyStatus, String replyCreateDate, String replyUpdateDate, Board board, Reply toReply, Users user, List<Heart> heart) {
        this.replyNum = replyNum;
        this.replyContent = replyContent;
        this.replyStatus = replyStatus;
        this.replyCreateDate = replyCreateDate;
        this.replyUpdateDate = replyUpdateDate;
        this.board = board;
        this.toReply = toReply;
        this.user = user;
        this.heart = heart;
    }

    public int getReplyNum() {
        return replyNum;
    }

    public void setReplyNum(int replyNum) {
        this.replyNum = replyNum;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public int getReplyStatus() {
        return replyStatus;
    }

    public void setReplyStatus(int replyStatus) {
        this.replyStatus = replyStatus;
    }

    public String getReplyCreateDate() {
        return replyCreateDate;
    }

    public void setReplyCreateDate(String replyCreateDate) {
        this.replyCreateDate = replyCreateDate;
    }

    public String getReplyUpdateDate() {
        return replyUpdateDate;
    }

    public void setReplyUpdateDate(String replyUpdateDate) {
        this.replyUpdateDate = replyUpdateDate;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Reply getToReply() {
        return toReply;
    }

    public void setToReply(Reply toReply) {
        this.toReply = toReply;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public List<Heart> getHeart() {
        return heart;
    }

    public void setHeart(List<Heart> heart) {
        this.heart = heart;
    }
}
