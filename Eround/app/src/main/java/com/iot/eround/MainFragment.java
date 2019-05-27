package com.iot.eround;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainFragment extends Fragment {

    // 메인화면 탭 시 답글작성 모드인지 확인하는 값(0 = 통상, 1 = 댓글작성)
    int commentMode = 0;

    TextView textView_mainFragment;
    ImageView imageView_mainFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        LinearLayout header =  view.findViewById(R.id.header_mainFragment);
        header.setOnClickListener(view1 -> {
            Button headerMenuButton = view.findViewById(R.id.headerMenuButton_mainFragment);
            Button headerMoreButton = view.findViewById(R.id.headerMoreButton_mainFragment);
            TextView headerText = view.findViewById(R.id.headerName_mainFragment);
            headerMenuButton.setBackgroundResource(R.drawable.baseline_menu_white_36dp);
            headerMoreButton.setVisibility(View.INVISIBLE);
            headerText.setText("EROUND");
        });

        LinearLayout mainContent = view.findViewById(R.id.content_mainFragment);
        mainContent.setOnClickListener(view1 -> {
            commentMode = 1 - commentMode;
            Button headerMenuButton = view.findViewById(R.id.headerMenuButton_mainFragment);
            Button headerMoreButton = view.findViewById(R.id.headerMoreButton_mainFragment);
            TextView headerText = view.findViewById(R.id.headerName_mainFragment);
            if (commentMode == 0) {
                headerMenuButton.setBackgroundResource(R.drawable.baseline_close_white_36dp);
                headerMoreButton.setVisibility(View.VISIBLE);
                headerText.setText("");
            } else {
                headerMenuButton.setBackgroundResource(R.drawable.baseline_menu_white_36dp);
                headerMoreButton.setVisibility(View.INVISIBLE);
                headerText.setText("EROUND");
            }
        });

        Button headerMenuButton = view.findViewById(R.id.headerMenuButton_mainFragment);
        headerMenuButton.setOnClickListener(view1 -> {
            commentMode = 1 - commentMode;
            Button headerMoreButton = view.findViewById(R.id.headerMoreButton_mainFragment);
            TextView headerText = view.findViewById(R.id.headerName_mainFragment);
            if (commentMode == 0) {
                headerMenuButton.setBackgroundResource(R.drawable.baseline_close_white_36dp);
                headerMoreButton.setVisibility(View.VISIBLE);
                headerText.setText("");
            } else {
                headerMenuButton.setBackgroundResource(R.drawable.baseline_menu_white_36dp);
                headerMoreButton.setVisibility(View.INVISIBLE);
                headerText.setText("EROUND");
            }
        });

        return  view;

    }

    public void changeFragmentTextView(String text) {

        textView_mainFragment.setText(text);

    }

    public void changeFragmentImageView(Bitmap bitmap) {

        imageView_mainFragment.setImageBitmap(bitmap);

    }

}
