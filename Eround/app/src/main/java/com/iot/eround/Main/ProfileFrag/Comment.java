package com.iot.eround.Main.ProfileFrag;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iot.eround.Adapter.ProfileReplyAdapter;
import com.iot.eround.MainActivity;
import com.iot.eround.R;
import com.iot.eround.Util.ApiService;
import com.iot.eround.Util.ImageRoader;
import com.iot.eround.VO.Reply;
import com.iot.eround.VO.Users;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Comment extends Fragment {

    public Comment(){}

    MainActivity mainActivity;
    ProfileReplyAdapter adapter;
    ArrayList<Reply> replyList = new ArrayList<>();
    RecyclerView profileContentRecycler;

    Random random = new Random();
    ImageRoader imageRoader = new ImageRoader();
    int[] defaultRandomNumberArray = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_main_profile_content, container, false);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);

        mainActivity = (MainActivity) getActivity();

        profileContentRecycler = view.findViewById(R.id.activity_main_profile_content_id_recycler);

        LinearLayoutManager layoutManager =
                new LinearLayoutManager(mainActivity, LinearLayoutManager.VERTICAL, false);

        profileContentRecycler.setLayoutManager(layoutManager);

        Thread thread = new Thread(() -> {

            Call<Users> usersFindBy = apiService.usersFindby(1);
            usersFindBy.enqueue(new Callback<Users>() {
                @Override
                public void onResponse(Call<Users> call, Response<Users> response) {

                    List<Reply> responseList = response.body().getReply();
                    try {

                        for (int i = 0; responseList.size() > i; i++) {

                            replyList.add(responseList.get(i));

                        }

                    } catch (Exception e) {

                        Log.i("test1 Exception", e.toString());

                    }

                }

                @Override
                public void onFailure(Call<Users> call, Throwable t) {

                    Log.i("test1 onFailure", t.toString());

                }
            });

            adapter = new ProfileReplyAdapter();

            adapter.setItems(replyList);

            profileContentRecycler.setAdapter(adapter);

        });

        thread.start();

        try{

            thread.join();

        }catch (Exception e){

            Log.i("test1 Join Exception", e.toString());

        }


        return view;

    }
}

