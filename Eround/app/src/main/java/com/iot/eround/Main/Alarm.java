package com.iot.eround.Main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.iot.eround.MainActivity;
import com.iot.eround.R;

public class Alarm extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_main_alarm, container, false);

        MainActivity mainActivity = (MainActivity) getActivity();

        FrameLayout button = view.findViewById(R.id.activity_main_alarm_id_content_1);
        button.setOnClickListener(v -> Toast.makeText(mainActivity.getApplicationContext(), "(대충 공지내용 팝업한다는 내용)", Toast.LENGTH_LONG).show());


        return view;
    }

}

