package com.iot.eround.VO;

import com.google.gson.annotations.SerializedName;

public class Content {

    @SerializedName("boardContent")
    private String boardContent;
    @SerializedName("boardRegion")
    private BoardRegion boardRegion;
    @SerializedName("boardCreateDate")
    private String boardCreateDate;
    @SerializedName("feeling")
    private Feeling feeling;

    public Content(String boardContent, BoardRegion boardRegion, String boardCreateDate, Feeling feeling) {
        this.boardContent = boardContent;
        this.boardRegion = boardRegion;
        this.boardCreateDate = boardCreateDate;
        this.feeling = feeling;
    }

    public String getBoardContent() {
        return boardContent;
    }

    public BoardRegion getBoardRegion() {
        return boardRegion;
    }

    public String getBoardCreateDate() {
        return boardCreateDate;
    }

    public Feeling getFeeling() {
        return feeling;
    }
}