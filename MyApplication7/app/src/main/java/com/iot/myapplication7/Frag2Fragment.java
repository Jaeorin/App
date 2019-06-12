package com.iot.myapplication7;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class Frag2Fragment extends Fragment {

    private Button button3;

    // add, replace될 떄 콜백됨
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final MainActivity mainActivity = (MainActivity) getActivity();
        int num = mainActivity.getNum();

        Toast.makeText(mainActivity, "num : " + num, Toast.LENGTH_LONG).show();

        // 프래그먼트를 inflate하고 컨테이너에 붙여달라는 뜻
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_frag2, container, false);

        button3 = view.findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            mainActivity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, new Frag1Fragment())
                .commit();

            }
        });

        return view;

    }

}