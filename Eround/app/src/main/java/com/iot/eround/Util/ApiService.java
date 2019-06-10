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

    // String setBoardContent
    // int setUser
    // String setBoardRegion
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

    // int boardNum
    // int userNum
    @POST("/bookmark/save")
    Call<BookMark> bookmarkSave(@Body BookMark bookMark);

    @GET("/bookmark/findall")
    Call<List<BookMark>> bookmarkFindall();

    @GET("/bookmark/findby/{path1}")
    Call<BookMark> bookmarkFindby(@Path("path1") int path1);

    @POST("/bookmark/update")
    Call<BookMark> bookmarkUpdate(@Body BookMark bookMark);

    @POST("/bookmark/delete/{path1}")
    Call<BookMark> bookmarkDelete(@Path("path1") int path1);

    // String feelingName
    // String feelingEmoticon
    @POST("/feeling/save")
    Call<Feeling> feelingSave(@Body Feeling feeling);

    @GET("/feeling/findall")
    Call<List<Feeling>> feelingFindall();

    @GET("/feeling/findby/{path1}")
    Call<Feeling> feelingFindby(@Path("path1") int path1);

    @POST("/feeling/update")
    Call<Feeling> feelingUpdate(@Body Feeling feeling);

    @POST("/feeling/delete/{path1}")
    Call<Feeling> feelingDelete(@Path("path1") int path1);

    // int userNum
    // int BoardNum
    // int heartStatus
    @POST("/heart/save")
    Call<Heart> heartSave(@Body Heart heart);

    @GET("/heart/findall")
    Call<List<Heart>> heartFindall();

    @GET("/heart/findby/{path1}")
    Call<Heart> heartFindby(@Path("path1") int path1);

    @POST("/heart/update")
    Call<Heart> heartUpdate(@Body Heart heart);

    @POST("/heart/delete/{path1}")
    Call<Heart> heartDelete(@Path("path1") int path1);

    //
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

    // String replyContent
    // int replyStatus
    // int boardNum
    // int userNum
    @POST("/reply/save")
    Call<Reply> replySave(@Body Reply reply);

    @GET("/reply/findall")
    Call<List<Reply>> replyFindall();

    @GET("/reply/findby/{path1}")
    Call<Reply> replyFindby(@Path("path1") int path1);

    @POST("/reply/update")
    Call<Reply> replyUpdate(@Body Reply reply);

    @POST("/reply/delete/{path1}")
    Call<Reply> replyDelete(@Path("path1") int path1);

    // int tagNum
    // int userNum
    @POST("/subscribe/save")
    Call<Subscribe> subscribeSave(@Body Subscribe subscribe);

    @GET("/subscribe/findall")
    Call<List<Subscribe>> subscribeFindall();

    @GET("/subscribe/findby/{path1}")
    Call<Subscribe> subscribeFindby(@Path("path1") int path1);

    @POST("/subscribe/update")
    Call<Subscribe> subscribeUpdate(@Body Subscribe subscribe);

    @POST("/subscribe/delete/{path1}")
    Call<Subscribe> subscribeDelete(@Path("path1") int path1);

    // String tagName
    @POST("/tags/save")
    Call<Tags> tagsSave(@Body Tags tags);

    @GET("/tags/findall")
    Call<List<Tags>> tagsFindall();

    @GET("/tags/findby/{path1}")
    Call<Tags> tagsFindby(@Path("path1") int path1);

    @POST("/tags/update")
    Call<Tags> tagsUpdate(@Body Tags tags);

    @POST("/tags/delete/{path1}")
    Call<Tags> tagsDelete(@Path("path1") int path1);

    // int userAge
    // String userGender
    // String userSearchRegion
    // String userRegion
    // int userEmail
    // int userSearchMinAge
    // int userSearchMaxAge
    @POST("/users/save")
    Call<Users> usersSave(@Body Users users);

    @GET("/users/findall")
    Call<List<Users>> usersFindall();

    @GET("/users/findby/{path1}")
    Call<Users> usersFindby(@Path("path1") int path1);

    @POST("/users/update")
    Call<Users> usersUpdate(@Body Users users);

    @POST("/users/delete/{path1}")
    Call<Users> usersDelete(@Path("path1") int path1);

}
