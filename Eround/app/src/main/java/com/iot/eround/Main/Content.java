package com.iot.eround.Main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iot.eround.MainActivity;
import com.iot.eround.R;
import com.iot.eround.Util.ApiService;
import com.iot.eround.Util.ImageRoader;
import com.iot.eround.VO.Board;
import com.iot.eround.VO.Feeling;

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

    List<Integer> boardRandomNumberArray = new ArrayList<>();
    Random random = new Random();
    ImageRoader imageRoader = new ImageRoader();

    int[] defaultRandomNumberArray = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    int commentMode = 0;


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

        Call<List<Board>> boardFindAll = apiService.boardFindall();
        boardFindAll.enqueue(new Callback<List<Board>>() {
            @Override
            public void onResponse(Call<List<Board>> call, Response<List<Board>> response) {

                List<Board> responseBody = response.body();

                for(int i = 0; responseBody.size() > i; i++){
                    boardRandomNumberArray.add(responseBody.get(i).getBoardNum());

                }

                int boardRandomNumber = boardRandomNumberArray.get(random.nextInt(responseBody.size()));

                Call<Board> mainContent = apiService.boardFindby(boardRandomNumber);

                mainContent.enqueue(new Callback<Board>() {
                    @Override
                    public void onResponse(Call<Board> call, Response<Board> response) {

                        Board board = Translate(response.body());
                        String url;

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

                            TextView favorite = mainActivity.findViewById(R.id.activity_main_content_id_body_favorite);
                            favorite.setText(String.valueOf(board.getHeart().size()));

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
            contentHeaderTitle.setText("EROUND");
        });

        contentHeaderClose.setOnClickListener(view2 -> {
            contentHeaderMenu.setVisibility(View.VISIBLE);
            contentHeaderClose.setVisibility(View.INVISIBLE);
            contentHeaderMore.setVisibility(View.INVISIBLE);
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
                contentHeaderTitle.setText("");

            } else {
                contentHeaderMenu.setVisibility(View.VISIBLE);
                contentHeaderClose.setVisibility(View.INVISIBLE);
                contentHeaderMore.setVisibility(View.INVISIBLE);
                contentHeaderTitle.setText("EROUND");
            }
        });

        return  view;

    }

}
