package com.iot.eround.Main;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.iot.eround.Adapter.HashAdapter;
import com.iot.eround.MainActivity;
import com.iot.eround.R;
import com.iot.eround.Util.ApiService;
import com.iot.eround.Util.ImageRoader;
import com.iot.eround.VO.Board;
import com.iot.eround.VO.Feeling;
import com.iot.eround.VO.Tags;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Hash extends Fragment {

    MainActivity mainActivity;
    int i;
    ArrayList<Tags> tagList = new ArrayList<>();
    ArrayList<Bitmap> bitmapList = new ArrayList<>();
    ImageRoader imageRoader = new ImageRoader();
    String url;
    int[] defaultRandomNumberArray = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    Random random = new Random();
    Bitmap bitmap;
    HashAdapter adapter = new HashAdapter();
    ListView listview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_main_hash, container, false);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);

        mainActivity = (MainActivity) getActivity();

        Call<List<Tags>> tagsFindall = apiService.tagsFindall();
        tagsFindall.enqueue(new Callback<List<Tags>>() {
            @Override
            public void onResponse(Call<List<Tags>> call, Response<List<Tags>> response) {

                List<Tags> responseBody = response.body();
                Log.i("test1 Tags", responseBody.toString());

                try {

                    for (i = 0; i < responseBody.size(); i++) {

                        tagList.add(responseBody.get(i));

                        url = ApiService.URL + "/image/" + (random.nextInt(defaultRandomNumberArray.length) + 1) + ".jpg";
                        bitmap = imageRoader.getBitmapImg(url);
                        bitmapList.add(bitmap);

                    }

                    Log.i("test1 boardList", tagList.toString());

                    adapter.addItem(tagList, bitmapList);
                    listview = view.findViewById(android.R.id.list);
                    listview.setAdapter(adapter);

                    listview.setOnItemClickListener((parent, view1, position, id) -> {
                        Bundle bundle = new Bundle();
                        getFragmentManager().beginTransaction().detach(mainActivity.storyFragment).commit();
                        bundle.putInt("childPosition", position);
                        mainActivity.storyFragment.setArguments(bundle);
                        getFragmentManager().beginTransaction().attach(mainActivity.storyFragment).commit();
                        getFragmentManager().beginTransaction().hide(mainActivity.mainFragment).commit();
                        getFragmentManager().beginTransaction().hide(mainActivity.hashFragment).commit();
                        getFragmentManager().beginTransaction().hide(mainActivity.writeFragment).commit();
                        getFragmentManager().beginTransaction().hide(mainActivity.alarmFragment).commit();
                        getFragmentManager().beginTransaction().hide(mainActivity.profileFragment).commit();
                        getFragmentManager().beginTransaction().show(mainActivity.storyFragment).commit();
                    });

                } catch (Exception e) {
                    Log.i("test1 tagsFindAll Exception", e.toString());
                }

            }

            @Override
            public void onFailure(Call<List<Tags>> call, Throwable t) {

                Log.i("test1 onFailure", t.toString());

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

