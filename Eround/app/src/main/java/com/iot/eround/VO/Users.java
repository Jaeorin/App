package com.iot.eround.VO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Users {

    @SerializedName("userNum")@Expose private int userNum;
    @SerializedName("userAge")@Expose private int userAge;
    @SerializedName("userGender")@Expose private String userGender;
    @SerializedName("userSearchRegion")@Expose private Region userSearchRegion;
    @SerializedName("userRegion")@Expose private Region userRegion;
    @SerializedName("heart")@Expose private List<Heart> heart;
    @SerializedName("bookMark")@Expose private List<BookMark> bookMark;
    @SerializedName("subscribe")@Expose private List<Subscribe> subscribe;
    @SerializedName("board")@Expose private List<Board> board;
    @SerializedName("reply")@Expose private List<Reply> reply;
    @SerializedName("userEmail")@Expose private String userEmail;
    @SerializedName("userSearchMinAge")@Expose private int userSearchMinAge;
    @SerializedName("userSearchMaxAge")@Expose private int userSearchMaxAge;
    @SerializedName("userActivate")@Expose private int userActivate;
    @SerializedName("userCreateDate")@Expose private String userCreateDate;
    @SerializedName("userUpdateDate")@Expose private String userUpdateDate;

    public Users(int userNum, int userAge, String userGender, Region userSearchRegion, Region userRegion, List<Heart> heart, List<BookMark> bookMark, List<Subscribe> subscribe, List<Board> board, List<Reply> reply, String userEmail, int userSearchMinAge, int userSearchMaxAge, int userActivate, String userCreateDate, String userUpdateDate) {
        this.userNum = userNum;
        this.userAge = userAge;
        this.userGender = userGender;
        this.userSearchRegion = userSearchRegion;
        this.userRegion = userRegion;
        this.heart = heart;
        this.bookMark = bookMark;
        this.subscribe = subscribe;
        this.board = board;
        this.reply = reply;
        this.userEmail = userEmail;
        this.userSearchMinAge = userSearchMinAge;
        this.userSearchMaxAge = userSearchMaxAge;
        this.userActivate = userActivate;
        this.userCreateDate = userCreateDate;
        this.userUpdateDate = userUpdateDate;
    }

    public Users() {

    }

    public int getUserNum() {
        return userNum;
    }

    public void setUserNum(int userNum) {
        this.userNum = userNum;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public Region getUserSearchRegion() {
        return userSearchRegion;
    }

    public void setUserSearchRegion(Region userSearchRegion) {
        this.userSearchRegion = userSearchRegion;
    }

    public Region getUserRegion() {
        return userRegion;
    }

    public void setUserRegion(Region userRegion) {
        this.userRegion = userRegion;
    }

    public List<Heart> getHeart() {
        return heart;
    }

    public void setHeart(List<Heart> heart) {
        this.heart = heart;
    }

    public List<BookMark> getBookMark() {
        return bookMark;
    }

    public void setBookMark(List<BookMark> bookMark) {
        this.bookMark = bookMark;
    }

    public List<Subscribe> getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(List<Subscribe> subscribe) {
        this.subscribe = subscribe;
    }

    public List<Board> getBoard() {
        return board;
    }

    public void setBoard(List<Board> board) {
        this.board = board;
    }

    public List<Reply> getReply() {
        return reply;
    }

    public void setReply(List<Reply> reply) {
        this.reply = reply;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public int getUserSearchMinAge() {
        return userSearchMinAge;
    }

    public void setUserSearchMinAge(int userSearchMinAge) {
        this.userSearchMinAge = userSearchMinAge;
    }

    public int getUserSearchMaxAge() {
        return userSearchMaxAge;
    }

    public void setUserSearchMaxAge(int userSearchMaxAge) {
        this.userSearchMaxAge = userSearchMaxAge;
    }

    public int getUserActivate() {
        return userActivate;
    }

    public void setUserActivate(int userActivate) {
        this.userActivate = userActivate;
    }

    public String getUserCreateDate() {
        return userCreateDate;
    }

    public void setUserCreateDate(String userCreateDate) {
        this.userCreateDate = userCreateDate;
    }

    public String getUserUpdateDate() {
        return userUpdateDate;
    }

    public void setUserUpdateDate(String userUpdateDate) {
        this.userUpdateDate = userUpdateDate;
    }
}
