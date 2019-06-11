package com.iot.myapplication6;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


// 프래그먼트는 setContentView()로 객체 할당이 불가능하다.
public class Frag1Fragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // 안드로이드의 모든 컴포넌트는 View를 상속받고 있음
        // ViewGroup으로 다운캐스팅 가능
        ViewGroup frag1 = (ViewGroup) inflater.inflate(R.layout.fragment_frag1, container, false);

        return frag1;

    }

}
