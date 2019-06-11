package com.iot.eround.Fragment.Main;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.iot.eround.Adapter.ListAdapter;
import com.iot.eround.MainActivity;
import com.iot.eround.R;
import com.iot.eround.Util.ApiService;
import com.iot.eround.VO.Board;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Main extends Fragment {

    Board board;
    private List<Bitmap> bitmap;
    private List<String> content;

    // 메인화면 탭 시 답글작성 모드인지 확인하는 값(0 = 통상, 1 = 댓글작성)
    int commentMode = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        Call<List<Board>> boardFindall = ((MainActivity)getActivity()).apiService.boardFindall();

        boardFindall.enqueue(new Callback<List<Board>>() {
            @Override
            public void onResponse(Call<List<Board>> call, Response<List<Board>> response) {

                try {

                    List<Board> responsebody = response.body();

                    for(int i = 0; responsebody.size() > i; i++) {

                        board = ((MainActivity) getActivity()).Translate(response.body().get(i));

                        if (board.getAttachFile().size() == 0) {

                            ((MainActivity) getActivity()).url = ApiService.URL + "/image/" + (((MainActivity) getActivity()).random.nextInt(((MainActivity) getActivity()).defaultRandomNumberArray.length) + 1) + ".jpg";

                        } else {

                            ((MainActivity) getActivity()).url = ApiService.URL + board.getAttachFile().get(1).getFilePath();

                        }

//                        bitmap.add(((MainActivity) getActivity()).imageRoader.getBitmapImg(((MainActivity) getActivity()).url));
//                        content.add(board.getBoardContent());

                    }

                } catch (Exception e) {

                    Log.i("mainFragTest Exception e", e.toString());

                }

            }

            @Override
            public void onFailure(Call<List<Board>> call, Throwable t) {

                Log.i("mainFragTest onFailure", t.toString());

            }
        });

        LinearLayout header =  getActivity().findViewById(R.id.header_activity);
        header.setOnClickListener(view1 -> {
            Button headerMenuButton = getActivity().findViewById(R.id.headerMenu_activity);
            Button headerCancleButton = getActivity().findViewById(R.id.headerCancle_activity);
            Button headerMoreButton = getActivity().findViewById(R.id.headerMore_activity);
            TextView headerText = getActivity().findViewById(R.id.headerName_activity);
            headerMenuButton.setVisibility(View.VISIBLE);
            headerCancleButton.setVisibility(View.INVISIBLE);
            headerMoreButton.setVisibility(View.INVISIBLE);
            headerText.setText("EROUND");
        });

        Button headerCancle_activity =  getActivity().findViewById(R.id.headerCancle_activity);
        headerCancle_activity.setOnClickListener(view1 -> {
            Button headerMenuButton = getActivity().findViewById(R.id.headerMenu_activity);
            Button headerCancleButton = getActivity().findViewById(R.id.headerCancle_activity);
            Button headerMoreButton = getActivity().findViewById(R.id.headerMore_activity);
            TextView headerText = getActivity().findViewById(R.id.headerName_activity);
            headerMenuButton.setVisibility(View.VISIBLE);
            headerCancleButton.setVisibility(View.INVISIBLE);
            headerMoreButton.setVisibility(View.INVISIBLE);
            headerText.setText("EROUND");
        });

        Button contentMore_mainFragment = view.findViewById(R.id.contentMore_mainFragment);
        contentMore_mainFragment.setOnClickListener(view1 -> {
            Intent intent = getActivity().getIntent();
            getActivity().finish();
            getActivity().startActivity(intent);
        });

        LinearLayout mainContent = view.findViewById(R.id.content_mainFragment);
        mainContent.setOnClickListener(view1 -> {
            commentMode = 1 - commentMode;
            Button headerMenuButton = getActivity().findViewById(R.id.headerMenu_activity);
            Button headerCancleButton = getActivity().findViewById(R.id.headerCancle_activity);
            Button headerMoreButton = getActivity().findViewById(R.id.headerMore_activity);
            TextView headerText = getActivity().findViewById(R.id.headerName_activity);
            if (commentMode == 0) {
                headerMenuButton.setVisibility(View.INVISIBLE);
                headerCancleButton.setVisibility(View.VISIBLE);
                headerMoreButton.setVisibility(View.VISIBLE);
                headerText.setText("");

            } else {
                headerMenuButton.setVisibility(View.VISIBLE);
                headerCancleButton.setVisibility(View.INVISIBLE);
                headerMoreButton.setVisibility(View.INVISIBLE);
                headerText.setText("EROUND");
            }
        });

        return  view;

    }

}
