package com.iot.myapplication7;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Frag1Fragment extends Fragment {

    private int id;

    public Frag1Fragment(){

    }

    // add, replace될 떄 콜백됨
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.setNum(10);

        // 프래그먼트를 inflate하고 컨테이너에 붙여달라는 뜻
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_frag1, container, false);

        return view;
    }
}
