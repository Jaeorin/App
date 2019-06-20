package com.iot.eround.Main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iot.eround.Main.ProfileFrag.Comment;
import com.iot.eround.MainActivity;
import com.iot.eround.R;
import com.iot.eround.Util.ApiService;
import com.iot.eround.Util.ImageRoader;
import com.iot.eround.Util.RetroCallback;
import com.iot.eround.VO.Board;
import com.iot.eround.VO.Feeling;
import com.iot.eround.VO.Heart;
import com.iot.eround.VO.Region;
import com.iot.eround.VO.Reply;
import com.iot.eround.VO.Users;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Content extends Fragment {

    LinearLayout.LayoutParams params;

    List<Integer> boardRandomNumberArray = new ArrayList<>();
    ImageRoader imageRoader = new ImageRoader();
    Random random = new Random();
    int[] defaultRandomNumberArray = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    int commentMode = 0;
    int boardRandomNumber;
    boolean heartStatus;
    int heartNum;
    int heartSize;
    int boardnum;
    TextView favorite;
    public Board Translate(Board board) {

        Optional<Board> optionalBoard = Optional.of(board);

        Feeling feeling = new Feeling(0, "", "", null, null, null);

        board.setFeeling(optionalBoard.map(Board::getFeeling).orElse(feeling));

        return board;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_main_content, container, false);

        MainActivity mainActivity = (MainActivity) getActivity();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);

        Button contentHeaderMenu = mainActivity.findViewById(R.id.activity_main_id_content_header_menu);
        Button contentHeaderClose = mainActivity.findViewById(R.id.activity_main_id_content_header_close);
        Button contentHeaderMore = mainActivity.findViewById(R.id.activity_main_id_content_header_more);
        TextView contentHeaderTitle = mainActivity.findViewById(R.id.activity_main_id_content_header_title);
        EditText commentText = view.findViewById(R.id.activity_main_content_id_text);

        Call<List<Board>> boardFindAll = apiService.boardFindall();
        boardFindAll.enqueue(new Callback<List<Board>>() {
            @Override
            public void onResponse(Call<List<Board>> call, Response<List<Board>> response) {

                List<Board> responseBody = response.body();

                for(int i = 0; responseBody.size() > i; i++){
                    boardRandomNumberArray.add(responseBody.get(i).getBoardNum());

                }

                boardRandomNumber = boardRandomNumberArray.get(random.nextInt(responseBody.size()));

                Call<Board> mainContent = apiService.boardFindby(boardRandomNumber);
                mainContent.enqueue(new Callback<Board>() {
                    @Override
                    public void onResponse(Call<Board> call, Response<Board> response) {

                        Board board = Translate(response.body());
                        String url;
                        heartSize = board.getHeart().size();
                        boardnum = board.getBoardNum();

                        Log.i("test1 countTest", String.valueOf(heartSize));
                        Log.i("test1 countTest", "start");

                        try{
                            for(int i = 0; i < heartSize; i++){

                                Log.i("test1 countTest", "1");

                                Heart heartTest = board.getHeart().get(i);

                                Log.i("test1 countTest", "2");

                                if(heartTest.getUser().getUserNum() == 1){

                                    Log.i("test1 countTest", "3");

                                    heartNum = heartTest.getHeartNum();

                                    Log.i("test1 countTest", "4");

                                    heartStatus = true;

                                    Log.i("test1 countTest", "5");

                                }
                            }
                        }catch (Exception e){
                            Log.i("test1 countTest Exception", e.toString());
                        }

                        Log.i("test1 countTest", "end");

                        if(board.getAttachFile().size() == 0) {

                            url = ApiService.URL + "/image/" + (random.nextInt(defaultRandomNumberArray.length) + 1) + ".jpg";

                        } else {

                            url = ApiService.URL + board.getAttachFile().get(1).getFilePath();

                        }

                        try {

                            ImageView backgroundImage = mainActivity.findViewById(R.id.activity_main_content_id_background_image);
                            backgroundImage.setImageBitmap(imageRoader.getBitmapImg(url));

                            TextView text = mainActivity.findViewById(R.id.activity_main_content_id_body_text);
                            text.setText(board.getBoardContent());

                            TextView location = mainActivity.findViewById(R.id.activity_main_content_id_body_location);
                            location.setText(board.getBoardRegion().toString());

                            TextView time = mainActivity.findViewById(R.id.activity_main_content_id_body_time);
                            time.setText(board.getBoardCreateDate());

                            if (board.getFeeling().getFeelingNum() == 0) {
                                TextView emotion = mainActivity.findViewById(R.id.activity_main_content_id_body_emotion);
                                emotion.setVisibility(View.INVISIBLE);
                            } else {
                                TextView emotion = mainActivity.findViewById(R.id.activity_main_content_id_body_emotion);
                                emotion.setText(board.getFeeling().getFeelingEmoticon());
                            }

                            if(heartStatus = false){
                                favorite = mainActivity.findViewById(R.id.activity_main_content_id_body_favorite);
                                favorite.setText(String.valueOf(board.getHeart().size()));
                            }else{
                                favorite = mainActivity.findViewById(R.id.activity_main_content_id_body_favorite);
                                favorite.setText(String.valueOf(board.getHeart().size()));
                            }


                            TextView comment = mainActivity.findViewById(R.id.activity_main_content_id_body_comment);
                            comment.setText(String.valueOf(board.getReply().size()));

                        } catch (Exception e) {

                            Log.i("mainContent Exception", e.toString());

                        }
                    }

                    @Override
                    public void onFailure(Call<Board> call, Throwable t) {

                        Log.i("mainContent onFailure", t.toString());

                    }
                });

            }

            @Override
            public void onFailure(Call<List<Board>> call, Throwable t) {

                Log.i("boardFindAll onFailure", t.toString());

            }
        });

        LinearLayout contentHeader =  mainActivity.findViewById(R.id.activity_main_id_content_header);
        contentHeader.setOnClickListener(view1 -> {
            contentHeaderMenu.setVisibility(View.VISIBLE);
            contentHeaderClose.setVisibility(View.INVISIBLE);
            contentHeaderMore.setVisibility(View.INVISIBLE);
            commentText.setVisibility(View.INVISIBLE);

            InputMethodManager inputMethodManager = (InputMethodManager) mainActivity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);

            contentHeaderTitle.setText("EROUND");
        });

        contentHeaderClose.setOnClickListener(view2 -> {
            contentHeaderMenu.setVisibility(View.VISIBLE);
            contentHeaderClose.setVisibility(View.INVISIBLE);
            contentHeaderMore.setVisibility(View.INVISIBLE);
            commentText.setVisibility(View.INVISIBLE);

            InputMethodManager inputMethodManager = (InputMethodManager) mainActivity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);

            contentHeaderTitle.setText("EROUND");
        });

        Button bodyMore = view.findViewById(R.id.activity_main_content_id_body_more);
        bodyMore.setOnClickListener(view1 -> {
            Intent intent = mainActivity.getIntent();
            mainActivity.finish();
            mainActivity.startActivity(intent);
        });

        LinearLayout mainContent = view.findViewById(R.id.activity_main_content_id_body);
        mainContent.setOnClickListener(view1 -> {
            commentMode = 1 - commentMode;

            if (commentMode == 0) {
                contentHeaderMenu.setVisibility(View.INVISIBLE);
                contentHeaderClose.setVisibility(View.VISIBLE);
                contentHeaderMore.setVisibility(View.VISIBLE);
                commentText.setVisibility(View.VISIBLE);

                commentText.requestFocus();

                InputMethodManager inputMethodManager = (InputMethodManager) mainActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

                contentHeaderTitle.setText("");

            } else {
                contentHeaderMenu.setVisibility(View.VISIBLE);
                contentHeaderClose.setVisibility(View.INVISIBLE);
                contentHeaderMore.setVisibility(View.INVISIBLE);
                commentText.setVisibility(View.INVISIBLE);

                InputMethodManager inputMethodManager = (InputMethodManager) mainActivity.getSystemService(Activity.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);

                contentHeaderTitle.setText("EROUND");
            }
        });

        contentHeaderMore.setOnClickListener(v -> {



        });

        contentHeaderMore.setOnClickListener(v -> {

            contentHeaderMenu.setVisibility(View.VISIBLE);
            contentHeaderClose.setVisibility(View.INVISIBLE);
            contentHeaderMore.setVisibility(View.INVISIBLE);
            commentText.setVisibility(View.INVISIBLE);

            InputMethodManager inputMethodManager = (InputMethodManager) mainActivity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);

            contentHeaderTitle.setText("EROUND");

            String text = commentText.getText().toString();

            Reply reply = new Reply();
            Board board = new Board();
            board.setBoardNum(boardnum);
            Users user = new Users();
            user.setUserNum(1);

            reply.setReplyContent(text);
            reply.setReplyStatus(0);
            reply.setUser(user);
            reply.setBoard(board);

            commentSave(reply, apiService, new RetroCallback() {

                @Override
                public void onError(Throwable t) {
                    Log.i("test onError", t.toString());
                }

                @Override
                public void onSuccess(int code, Object receivedData) {
                    Reply data = (Reply) receivedData;
                    Log.i("test onSuccess codeResultTextView", String.valueOf(code));
                    Log.i("test onSuccess idResultTextView", String.valueOf(data.getReplyContent()));
                }

                @Override
                public void onFailure(int code) {
                    Log.i("test onFailure", String.valueOf(code));
                }

            });
        });


        TextView bodyFavorite = view.findViewById(R.id.activity_main_content_id_body_favorite);
        bodyFavorite.setOnClickListener(v -> {

            if (heartStatus == false) {
                Heart heart = new Heart();
                Board board = new Board();
                board.setBoardNum(boardRandomNumber);
                Users user = new Users();
                user.setUserNum(1);
                heart.setBoard(board);
                heart.setUser(user);
                heart.setHeartStatus(0);

                heartSave(heart, apiService, new RetroCallback() {

                    @Override
                    public void onError(Throwable t) {
                        Log.i("test onError", t.toString());
                    }

                    @Override
                    public void onSuccess(int code, Object receivedData) {
                        Heart data = (Heart) receivedData;
                        Log.i("test heartSave onSuccess codeResultTextView", String.valueOf(code));
                        Log.i("test heartSave onSuccess idResultTextView", String.valueOf(data.getUser().getUserNum()));
                        heartStatus = true;
                        Thread thread = new Thread(() -> mainActivity.runOnUiThread(() -> favorite.setText(String.valueOf(heartSize + 1))));
                        thread.start();
                        try{
                            thread.join();
                        }catch (Exception e){
                            Log.i("test1 ThreadException", e.toString());
                        }
                    }

                    @Override
                    public void onFailure(int code) {
                        Log.i("test heartSave onFailure", String.valueOf(code));
                    }

                });
            } else {
                heartDelete(heartNum, apiService, new RetroCallback() {

                    @Override
                    public void onError(Throwable t) {
                        Log.i("test onError", t.toString());
                    }

                    @Override
                    public void onSuccess(int code, Object receivedData) {
                        Heart data = (Heart) receivedData;
                        Log.i("test heartDelete onSuccess codeResultTextView", String.valueOf(code));
                        Log.i("test heartDelete onSuccess idResultTextView", String.valueOf(data.getUser().getUserNum()));
                        heartStatus = false;
                        Thread thread = new Thread(() -> mainActivity.runOnUiThread(() -> favorite.setText(String.valueOf(heartSize - 1))));
                        thread.start();
                        try{
                            thread.join();
                        }catch (Exception e){
                            Log.i("test1 ThreadException", e.toString());
                        }
                    }

                    @Override
                    public void onFailure(int code) {
                        Log.i("test heartDelete onFailure", String.valueOf(code));
                    }

                });

            }

        });

        return  view;

    }

    public void heartSave(Heart heart, ApiService apiService, final RetroCallback callback) {

        apiService.heartSave(heart).enqueue(new Callback<Heart>() {
            @Override
            public void onResponse(Call<Heart> call, Response<Heart> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.code(), response.body());
                } else {
                    callback.onFailure(response.code());
                }
            }

            @Override
            public void onFailure(Call<Heart> call, Throwable t) {
                Log.i("test1 fail", t.toString());
                callback.onError(t);
            }

        });

    }

    public void heartDelete(int heartNum, ApiService apiService, final RetroCallback callback) {

        apiService.heartDelete(heartNum).enqueue(new Callback<Heart>() {
            @Override
            public void onResponse(Call<Heart> call, Response<Heart> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.code(), response.body());
                } else {
                    callback.onFailure(response.code());
                }
            }

            @Override
            public void onFailure(Call<Heart> call, Throwable t) {
                Log.i("test1 fail", t.toString());
                callback.onError(t);
            }

        });

    }

    public void commentSave(Reply reply, ApiService apiService, final RetroCallback callback) {

        apiService.replySave(reply).enqueue(new Callback<Reply>() {
            @Override
            public void onResponse(Call<Reply> call, Response<Reply> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.code(), response.body());
                } else {
                    callback.onFailure(response.code());
                }
            }

            @Override
            public void onFailure(Call<Reply> call, Throwable t) {
                callback.onError(t);
            }

        });

    }

}
