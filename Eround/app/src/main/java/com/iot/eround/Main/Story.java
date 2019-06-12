package com.iot.eround.Main;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.iot.eround.Adapter.StoryAdapter;
import com.iot.eround.R;
import com.iot.eround.Util.ApiService;
import com.iot.eround.Util.ImageRoader;
import com.iot.eround.VO.Board;
import com.iot.eround.VO.Feeling;
import com.iot.eround.VO.InsertTag;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Story extends ListFragment {

    ListView listview;
    StoryAdapter adapter;
    int childPosition;
    Bitmap bitmap;

    public Random random = new Random();
    public ImageRoader imageRoader = new ImageRoader();
    public List<Integer> boardRandomNumberArray = new ArrayList<>();
    public int[] defaultRandomNumberArray = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    public int boardRandomNumber;
    public String url;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_main_story, container, false);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);

        adapter = new StoryAdapter();

        if(getArguments() != null){

            childPosition = getArguments().getInt("childPosition");

        } else {

            childPosition = 0;

        }

        Call<List<Board>> boardFindall = apiService.boardFindall();

        boardFindall.enqueue(new Callback<List<Board>>() {
            @Override
            public void onResponse(Call<List<Board>> call, Response<List<Board>> response) {

                List<Board> responsebody = response.body();

                try {

                    for(int i = 0; responsebody.size() > i; i++){

                        List<InsertTag> tagbody = responsebody.get(i).getInsertTag();

                        for(int i1 = 0; tagbody.size() > i1; i1++){

                            int i2 = i;

                            if(tagbody.get(i1).getTag().getTagNum() == (childPosition+1)){

                                Call<Board> mainContent = apiService.boardFindby(i);

                                mainContent.enqueue(new Callback<Board>() {
                                    @Override
                                    public void onResponse(Call<Board> call, Response<Board> response) {

                                        Board board = Translate(response.body());

                                        if(board.getAttachFile().size() == 0) {

                                            url = ApiService.URL + "/image/" +( random.nextInt(defaultRandomNumberArray.length) + 1) + ".jpg";

                                        } else {

                                            url = ApiService.URL + board.getAttachFile().get(1).getFilePath();

                                        }

                                        bitmap = imageRoader.getBitmapImg(url);

                                        adapter.addItem(responsebody.get(i2).getBoardContent(), responsebody.get(i2).getBoardCreateDate(), bitmap);

                                    }

                                    @Override
                                    public void onFailure(Call<Board> call, Throwable t) {

                                        Log.i("test1 onFailure", t.toString());

                                    }
                                });



                            }

                        }

                    }

                    for (int i = 0; responsebody.size() > i; i++) {

                        adapter.addItem(responsebody.get(i).getBoardContent(), responsebody.get(i).getBoardCreateDate(), bitmap);

                    }

                    listview = view.findViewById(android.R.id.list);
                    listview.setAdapter(adapter);

                    if(listview == null){

                        Log.i("testList Exception", "널입니다");
                    }else{

                        Log.i("testList Exception", "아입니다");
                    }

                }catch (Exception e){

                    Log.i("testList Exception", e.toString());

                }

            }

            @Override
            public void onFailure(Call<List<Board>> call, Throwable t) {

                Log.i("testList onFailure", t.toString());

            }
        });

        return view;
    }

    public Board Translate(Board board) {

        Optional<Board> optionalBoard = Optional.of(board);

        Feeling feeling = new Feeling(0, "", "", null, null, null);

        board.setFeeling(optionalBoard.map(Board::getFeeling).orElse(feeling));

        return board;
    }

}
