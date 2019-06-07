package com.iot.eround.VO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookMark {

    @SerializedName("bookMarkNum")@Expose private int bookMarkNum;
    @SerializedName("board")@Expose private Board board;
    @SerializedName("user")@Expose private Users user;
    @SerializedName("bookMarkCreateDate")@Expose private String bookMarkCreateDate;
    @SerializedName("bookMarkUpdateDate")@Expose private String bookMarkUpdateDate;

    public BookMark(int bookMarkNum, Board board, Users user, String bookMarkCreateDate, String bookMarkUpdateDate) {
        this.bookMarkNum = bookMarkNum;
        this.board = board;
        this.user = user;
        this.bookMarkCreateDate = bookMarkCreateDate;
        this.bookMarkUpdateDate = bookMarkUpdateDate;
    }

    public int getBookMarkNum() {
        return bookMarkNum;
    }

    public void setBookMarkNum(int bookMarkNum) {
        this.bookMarkNum = bookMarkNum;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public String getBookMarkCreateDate() {
        return bookMarkCreateDate;
    }

    public void setBookMarkCreateDate(String bookMarkCreateDate) {
        this.bookMarkCreateDate = bookMarkCreateDate;
    }

    public String getBookMarkUpdateDate() {
        return bookMarkUpdateDate;
    }

    public void setBookMarkUpdateDate(String bookMarkUpdateDate) {
        this.bookMarkUpdateDate = bookMarkUpdateDate;
    }
}
