package com.iot.eround.VO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InsertTag {

    @SerializedName("insertTagNum")@Expose private int insertTagNum;
    @SerializedName("tag")@Expose private Tags tag;
    @SerializedName("board")@Expose private Board board;
    @SerializedName("insertTagCreateDate")@Expose private String insertTagCreateDate;
    @SerializedName("insertTagUpdateDate")@Expose private String insertTagUpdateDate;

    public InsertTag(int insertTagNum, Tags tag, Board board, String insertTagCreateDate, String insertTagUpdateDate) {
        this.insertTagNum = insertTagNum;
        this.tag = tag;
        this.board = board;
        this.insertTagCreateDate = insertTagCreateDate;
        this.insertTagUpdateDate = insertTagUpdateDate;
    }

    public int getInsertTagNum() {
        return insertTagNum;
    }

    public void setInsertTagNum(int insertTagNum) {
        this.insertTagNum = insertTagNum;
    }

    public Tags getTag() {
        return tag;
    }

    public void setTag(Tags tag) {
        this.tag = tag;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public String getInsertTagCreateDate() {
        return insertTagCreateDate;
    }

    public void setInsertTagCreateDate(String insertTagCreateDate) {
        this.insertTagCreateDate = insertTagCreateDate;
    }

    public String getInsertTagUpdateDate() {
        return insertTagUpdateDate;
    }

    public void setInsertTagUpdateDate(String insertTagUpdateDate) {
        this.insertTagUpdateDate = insertTagUpdateDate;
    }
}
