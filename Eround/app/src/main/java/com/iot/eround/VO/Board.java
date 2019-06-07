package com.iot.eround.VO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Board {

    @SerializedName("boardNum")@Expose private int boardNum;
    @SerializedName("boardContent")@Expose private String boardContent;
    @SerializedName("boardCreateDate")@Expose private String boardCreateDate;
    @SerializedName("boardUpdateDate")@Expose private String boardUpdateDate;
    @SerializedName("user")@Expose private Users user;
    @SerializedName("boardRegion")@Expose private Region boardRegion;
    @SerializedName("feeling")@Expose private Feeling feeling;
    @SerializedName("heart")@Expose private List<Heart> heart;
    @SerializedName("reply")@Expose private List<Reply> reply;
    @SerializedName("insertTag")@Expose private List<InsertTag> insertTag;
    @SerializedName("attachFile")@Expose private List<AttachFile> attachFile;
    @SerializedName("attachSearch")@Expose private String attachSearch;

    public Board(int boardNum, String boardContent, String boardCreateDate, String boardUpdateDate, Users user, Region boardRegion, Feeling feeling, List<Heart> heart, List<Reply> reply, List<InsertTag> insertTag, List<AttachFile> attachFile, String attachSearch) {
        this.boardNum = boardNum;
        this.boardContent = boardContent;
        this.boardCreateDate = boardCreateDate;
        this.boardUpdateDate = boardUpdateDate;
        this.user = user;
        this.boardRegion = boardRegion;
        this.feeling = feeling;
        this.heart = heart;
        this.reply = reply;
        this.insertTag = insertTag;
        this.attachFile = attachFile;
        this.attachSearch = attachSearch;
    }

    public Board() {

    }

    public int getBoardNum() {
        return boardNum;
    }

    public void setBoardNum(int boardNum) {
        this.boardNum = boardNum;
    }

    public String getBoardContent() {
        return boardContent;
    }

    public void setBoardContent(String boardContent) {
        this.boardContent = boardContent;
    }

    public String getBoardCreateDate() {
        return boardCreateDate;
    }

    public void setBoardCreateDate(String boardCreateDate) {
        this.boardCreateDate = boardCreateDate;
    }

    public String getBoardUpdateDate() {
        return boardUpdateDate;
    }

    public void setBoardUpdateDate(String boardUpdateDate) {
        this.boardUpdateDate = boardUpdateDate;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Region getBoardRegion() {
        return boardRegion;
    }

    public void setBoardRegion(Region boardRegion) {
        this.boardRegion = boardRegion;
    }

    public Feeling getFeeling() {
        return feeling;
    }

    public void setFeeling(Feeling feeling) {
        this.feeling = feeling;
    }

    public List<Heart> getHeart() {
        return heart;
    }

    public void setHeart(List<Heart> heart) {
        this.heart = heart;
    }

    public List<Reply> getReply() {
        return reply;
    }

    public void setReply(List<Reply> reply) {
        this.reply = reply;
    }

    public List<InsertTag> getInsertTag() {
        return insertTag;
    }

    public void setInsertTag(List<InsertTag> insertTag) {
        this.insertTag = insertTag;
    }

    public List<AttachFile> getAttachFile() {
        return attachFile;
    }

    public void setAttachFile(List<AttachFile> attachFile) {
        this.attachFile = attachFile;
    }

    public String getAttachSearch() {
        return attachSearch;
    }

    public void setAttachSearch(String attachSearch) {
        this.attachSearch = attachSearch;
    }
}
