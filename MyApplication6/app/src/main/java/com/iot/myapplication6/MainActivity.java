package com.iot.myapplication6;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //프래그먼트 사용
        button = findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // add시에 Fragment객체 만들어짐짐
               getSupportFragmentManager().beginTransaction().add(R.id.container, new Frag1Fragment()).commit();

            }

        });

    }

}