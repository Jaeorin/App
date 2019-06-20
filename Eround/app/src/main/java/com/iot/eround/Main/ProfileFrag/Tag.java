package com.iot.eround.Main.ProfileFrag;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iot.eround.Adapter.ProfileContentAdapter;
import com.iot.eround.Adapter.ProfileTagAdapter;
import com.iot.eround.MainActivity;
import com.iot.eround.R;
import com.iot.eround.Util.ApiService;
import com.iot.eround.Util.ImageRoader;
import com.iot.eround.VO.Board;
import com.iot.eround.VO.Feeling;
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

public class Tag extends Fragment {

    public Tag(){}

    MainActivity mainActivity;
    ProfileTagAdapter adapter;
    ArrayList<String> tagList = new ArrayList<>();
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

                    List<Board> responseList = response.body().getBoard();
                    try {
                        ArrayList<String> responseTagList = new ArrayList<>();
                        for (int i = 0; responseList.size() > i; i++) {
                            for(int i1 = 0; responseList.get(i).getInsertTag().size() > i1; i1++){
                                responseTagList.add(responseList.get(i).getInsertTag().get(i1).getTag().getTagName());
                            }
                            tagList = responseTagList;
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

            adapter = new ProfileTagAdapter();

            adapter.setItems(tagList);

            profileContentRecycler.setAdapter(adapter);

        });

        thread.start();

        try{

            thread.join();

        }catch (Exception e){

            Log.i("test1 Join Exception", e.toString());

        }

//        adapter.setOnItemClickListener((v, position) -> {
//
//            ArrayList<Board> reBoard = adapter.gettagList();
//            String url;
//
//            if(reBoard.get(position).getAttachFile().size() == 0) {
//
//                url = ApiService.URL + "/image/" + (random.nextInt(defaultRandomNumberArray.length) + 1) + ".jpg";
//
//            } else {
//
//                url = ApiService.URL + reBoard.get(position).getAttachFile().get(1).getFilePath();
//
//            }
//
//            ImageView backgroundImage = mainActivity.findViewById(R.id.activity_main_content_id_background_image);
//            backgroundImage.setImageBitmap(imageRoader.getBitmapImg(url));
//
//            TextView text = mainActivity.findViewById(R.id.activity_main_content_id_body_text);
//            text.setText(reBoard.get(position).getBoardContent());
//
//            TextView location = mainActivity.findViewById(R.id.activity_main_content_id_body_location);
//            location.setText(reBoard.get(position).getBoardRegion().toString());
//
//            TextView time = mainActivity.findViewById(R.id.activity_main_content_id_body_time);
//            time.setText(reBoard.get(position).getBoardCreateDate());
//
//            if (Translate(reBoard.get(position)).getFeeling().getFeelingNum() == 0) {
//                TextView emotion = mainActivity.findViewById(R.id.activity_main_content_id_body_emotion);
//                emotion.setVisibility(View.INVISIBLE);
//            } else {
//                TextView emotion = mainActivity.findViewById(R.id.activity_main_content_id_body_emotion);
//                emotion.setText(reBoard.get(position).getFeeling().getFeelingEmoticon());
//            }
//
//            TextView favorite = mainActivity.findViewById(R.id.activity_main_content_id_body_favorite);
//            favorite.setText(String.valueOf(reBoard.get(position).getHeart().size()));
//
//            TextView comment = mainActivity.findViewById(R.id.activity_main_content_id_body_comment);
//            comment.setText(String.valueOf(reBoard.get(position).getReply().size()));
//
//            Button contentHeaderMenu1 = mainActivity.findViewById(R.id.activity_main_id_content_header_menu);
//            Button contentHeaderClose1 = mainActivity.findViewById(R.id.activity_main_id_content_header_close);
//            Button contentHeaderMore1 = mainActivity.findViewById(R.id.activity_main_id_content_header_more);
//            TextView contentHeaderTitle1 = mainActivity.findViewById(R.id.activity_main_id_content_header_title);
//            LinearLayout contentHeader1 = mainActivity.findViewById(R.id.activity_main_id_content_header);
//            LinearLayout hashHeader1 = mainActivity.findViewById(R.id.activity_main_id_hash_header);
//            LinearLayout writeHeader1 = mainActivity.findViewById(R.id.activity_main_id_write_header);
//            LinearLayout alarmHeader1 = mainActivity.findViewById(R.id.activity_main_id_alarm_header);
//            LinearLayout writeFooter1 = mainActivity.findViewById(R.id.activity_main_id_write_footer);
//            LinearLayout contentFooter1 = mainActivity.findViewById(R.id.activity_main_id_content_footer);
//
//            mainActivity.changeFragment();
//
//            contentFooter1.setBackgroundColor(Color.argb(0,0,0,0));
//            contentFooter1.setVisibility(View.VISIBLE);
//
//            contentHeader1.setVisibility(View.VISIBLE);
//            hashHeader1.setVisibility(View.GONE);
//            writeHeader1.setVisibility(View.GONE);
//            alarmHeader1.setVisibility(View.GONE);
//            writeFooter1.setVisibility(View.GONE);
//
//            contentHeaderMenu1.setVisibility(View.VISIBLE);
//            contentHeaderClose1.setVisibility(View.INVISIBLE);
//            contentHeaderMore1.setVisibility(View.INVISIBLE);
//            contentHeaderTitle1.setText("AROUND");
//
//        }) ;
//
        return view;
//
//    }
//
//    public Board Translate(Board board) {
//
//        Optional<Board> optionalBoard = Optional.of(board);
//
//        Feeling feeling = new Feeling(0, "", "", null, null, null);
//
//        board.setFeeling(optionalBoard.map(Board::getFeeling).orElse(feeling));
//
//        return board;
    }
}

