package com.iot.eround.Util;

import com.iot.eround.VO.AttachFile;
import com.iot.eround.VO.Board;
import com.iot.eround.VO.BookMark;
import com.iot.eround.VO.Feeling;
import com.iot.eround.VO.Heart;
import com.iot.eround.VO.InsertTag;
import com.iot.eround.VO.Reply;
import com.iot.eround.VO.Subscribe;
import com.iot.eround.VO.Tags;
import com.iot.eround.VO.Users;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    String URL = "http://192.168.0.82:8000";


    @POST("/attach/save")
    Call<AttachFile> attachSave();

    @POST("/attach/deleteall")
    Call<AttachFile> attachDeleteall();

    @POST("/attach/update")
    Call<AttachFile> attachUpdate(@Body AttachFile AttachFile);


    // setBoardContent
    // setUser
    // setBoardRegion
    @POST("/board/save")
    Call<Board> boardSave(@Body Board board);

    @GET("/board/findall")
    Call<List<Board>> boardFindall();

    @GET("/board/findby/{path1}")
    Call<Board> boardFindby(@Path("path1") int path1);

    @POST("/board/update")
    Call<Board> boardUpdate(@Body Board board);

    @POST("/board/delete/{path1}")
    Call<Board> boardDelete(@Path("path1") int path1);



    @POST("/bookmark/save")
    Call<BookMark> bookmarkSave(
            @Query("boardNum") int boardNum,
            @Query("userNum") int userNum);

    @GET("/bookmark/findall")
    Call<List<BookMark>> bookmarkFindall();

    @GET("/bookmark/findby/{path1}")
    Call<BookMark> bookmarkFindby(@Path("path1") int path1);

    @POST("/bookmark/update")
    Call<BookMark> bookmarkUpdate(
            @Query("boardNum") int boardNum,
            @Query("userNum") int userNum);

    @POST("/bookmark/delete/{path1}")
    Call<BookMark> bookmarkDelete(@Path("path1") int path1);



    @POST("/feeling/save")
    Call<Feeling> feelingSave(
            @Query("feelingName") String feelingName,
            @Query("feelingEmoticon") String feelingEmoticon);

    @GET("/feeling/findall")
    Call<List<Feeling>> feelingFindall();

    @GET("/feeling/findby/{path1}")
    Call<Feeling> feelingFindby(@Path("path1") int path1);

    @POST("/feeling/update")
    Call<Feeling> feelingUpdate(
            @Query("feelingName") String feelingName,
            @Query("feelingEmoticon") String feelingEmoticon);

    @POST("/feeling/delete/{path1}")
    Call<Feeling> feelingDelete(@Path("path1") int path1);



    @POST("/heart/save")
    Call<Heart> heartSave(
            @Query("userNum") int userNum,
            @Query("boardNum") int boardNum,
            @Query("heartStatus") int heartStatus);

    @GET("/heart/findall")
    Call<List<Heart>> heartFindall();

    @GET("/heart/findby/{path1}")
    Call<Heart> heartFindby(@Path("path1") int path1);

    @POST("/heart/update")
    Call<Heart> heartUpdate(
            @Query("userNum") int userNum,
            @Query("boardNum") int boardNum,
            @Query("heartStatus") int heartStatus);

    @POST("/heart/delete/{path1}")
    Call<Heart> heartDelete(@Path("path1") int path1);



    @POST("/inserttag/save")
    Call<InsertTag> inserttagSave();

    @GET("/inserttag/findall")
    Call<List<InsertTag>> inserttagFindall();

    @GET("/inserttag/findby/{path1}")
    Call<InsertTag> inserttagFindby(@Path("path1") int path1);

    @POST("/inserttag/update")
    Call<InsertTag> inserttagUpdate();

    @POST("/inserttag/delete/{path1}")
    Call<InsertTag> inserttagDelete(@Path("path1") int path1);



    @POST("/reply/save")
    Call<Reply> replySave(
            @Query("replyContent") String replyContent,
            @Query("replyStatus") int replyStatus,
            @Query("boardNum") int boardNum,
            @Query("userNum") int userNum);

    @GET("/reply/findall")
    Call<List<Reply>> replyFindall();

    @GET("/reply/findby/{path1}")
    Call<Reply> replyFindby(@Path("path1") int path1);

    @POST("/reply/update")
    Call<Reply> replyUpdate(
            @Query("replyContent") String replyContent,
            @Query("replyStatus") int replyStatus,
            @Query("boardNum") int boardNum,
            @Query("userNum") int userNum);

    @POST("/reply/delete/{path1}")
    Call<Reply> replyDelete(@Path("path1") int path1);



    @POST("/subscribe/save")
    Call<Subscribe> subscribeSave(
            @Query("tagNum") int tagNum,
            @Query("userNum") int userNum);

    @GET("/subscribe/findall")
    Call<List<Subscribe>> subscribeFindall();

    @GET("/subscribe/findby/{path1}")
    Call<Subscribe> subscribeFindby(@Path("path1") int path1);

    @POST("/subscribe/update")
    Call<Subscribe> subscribeUpdate(
            @Query("tagNum") int tagNum,
            @Query("userNum") int userNum);

    @POST("/subscribe/delete/{path1}")
    Call<Subscribe> subscribeDelete(@Path("path1") int path1);



    @POST("/tags/save")
    Call<Tags> tagsSave(
            @Query("tagName") String tagName);

    @GET("/tags/findall")
    Call<List<Tags>> tagsFindall();

    @GET("/tags/findby/{path1}")
    Call<Tags> tagsFindby(@Path("path1") int path1);

    @POST("/tags/update")
    Call<Tags> tagsUpdate(
            @Query("tagName") String tagName);

    @POST("/tags/delete/{path1}")
    Call<Tags> tagsDelete(@Path("path1") int path1);



    @POST("/users/save")
    Call<Users> usersSave(
            @Query("userAge") int userAge,
            @Query("userGender") String userGender,
            @Query("userSearchRegion") String userSearchRegion,
            @Query("userRegion") String userRegion,
            @Query("userEmail") String userEmail,
            @Query("userSearchMinAge") int userSearchMinAge,
            @Query("userSearchMaxAge") int userSearchMaxAge);

    @GET("/users/findall")
    Call<List<Users>> usersFindall();

    @GET("/users/findby/{path1}")
    Call<Users> usersFindby(@Path("path1") int path1);

    @POST("/users/update")
    Call<Users> usersUpdate(
            @Query("userAge") int userAge,
            @Query("userGender") String userGender,
            @Query("userSearchRegion") String userSearchRegion,
            @Query("userRegion") String userRegion,
            @Query("userEmail") String userEmail,
            @Query("userSearchMinAge") int userSearchMinAge,
            @Query("userSearchMaxAge") int userSearchMaxAge);

    @POST("/users/delete/{path1}")
    Call<Users> usersDelete(@Path("path1") int path1);

}
