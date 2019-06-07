package com.iot.eround.VO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Feeling {

    @SerializedName("feelingNum")@Expose private int feelingNum;
    @SerializedName("feelingName")@Expose private String feelingName;
    @SerializedName("feelingEmoticon")@Expose private String feelingEmoticon;
    @SerializedName("board")@Expose private List<Board> board;
    @SerializedName("feelingCreateDate")@Expose private String feelingCreateDate;
    @SerializedName("feelingUpdateDate")@Expose private String feelingUpdateDate;

    public Feeling(int feelingNum, String feelingName, String feelingEmoticon, List<Board> board, String feelingCreateDate, String feelingUpdateDate) {
        this.feelingNum = feelingNum;
        this.feelingName = feelingName;
        this.feelingEmoticon = feelingEmoticon;
        this.board = board;
        this.feelingCreateDate = feelingCreateDate;
        this.feelingUpdateDate = feelingUpdateDate;
    }

    public int getFeelingNum() {
        return feelingNum;
    }

    public void setFeelingNum(int feelingNum) {
        this.feelingNum = feelingNum;
    }

    public String getFeelingName() {
        return feelingName;
    }

    public void setFeelingName(String feelingName) {
        this.feelingName = feelingName;
    }

    public String getFeelingEmoticon() {
        return feelingEmoticon;
    }

    public void setFeelingEmoticon(String feelingEmoticon) {
        this.feelingEmoticon = feelingEmoticon;
    }

    public List<Board> getBoard() {
        return board;
    }

    public void setBoard(List<Board> board) {
        this.board = board;
    }

    public String getFeelingCreateDate() {
        return feelingCreateDate;
    }

    public void setFeelingCreateDate(String feelingCreateDate) {
        this.feelingCreateDate = feelingCreateDate;
    }

    public String getFeelingUpdateDate() {
        return feelingUpdateDate;
    }

    public void setFeelingUpdateDate(String feelingUpdateDate) {
        this.feelingUpdateDate = feelingUpdateDate;
    }
}
