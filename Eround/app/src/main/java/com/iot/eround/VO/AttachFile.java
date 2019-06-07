package com.iot.eround.VO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AttachFile {

    @SerializedName("attchNum")@Expose private int attchNum;
    @SerializedName("mimeType")@Expose private String mimeType;
    @SerializedName("fileName")@Expose private String fileName;
    @SerializedName("filePath")@Expose private String filePath;
    @SerializedName("board")@Expose private Board board;
    @SerializedName("attachCtreateDate")@Expose private String attachCtreateDate;
    @SerializedName("attachUpdateDate")@Expose private String attachUpdateDate;

    public AttachFile(int attchNum, String mimeType, String fileName, String filePath, Board board, String attachCtreateDate, String attachUpdateDate) {
        this.attchNum = attchNum;
        this.mimeType = mimeType;
        this.fileName = fileName;
        this.filePath = filePath;
        this.board = board;
        this.attachCtreateDate = attachCtreateDate;
        this.attachUpdateDate = attachUpdateDate;
    }

    public int getAttchNum() {
        return attchNum;
    }

    public void setAttchNum(int attchNum) {
        this.attchNum = attchNum;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public String getAttachCtreateDate() {
        return attachCtreateDate;
    }

    public void setAttachCtreateDate(String attachCtreateDate) {
        this.attachCtreateDate = attachCtreateDate;
    }

    public String getAttachUpdateDate() {
        return attachUpdateDate;
    }

    public void setAttachUpdateDate(String attachUpdateDate) {
        this.attachUpdateDate = attachUpdateDate;
    }
}
