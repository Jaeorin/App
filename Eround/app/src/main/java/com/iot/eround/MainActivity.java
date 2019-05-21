package com.iot.eround;

import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import com.iot.eround.Util.ApiService;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    Retrofit retrofit;
    ApiService apiService;
    int commentMode = 0;

    public static final String TAG = "MainActivity";

    // 앱이 최초 실행될 때!!
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        Log.d(TAG, "onCreate()");

        retrofit = new Retrofit.Builder().baseUrl(ApiService.URL).build();
        apiService = retrofit.create(ApiService.class);

        Call<ResponseBody> content = apiService.content("list");

        content.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.i("test1", response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
            }
        });

        LinearLayout header = (LinearLayout) findViewById(R.id.header);
        header.setOnClickListener(new LinearLayout.OnClickListener() {
            @Override
            public void onClick(View v) {
                commentMode = 1 - commentMode;
                Button headerMenuButton = (Button) findViewById(R.id.headerMenuButton);

                if (commentMode == 0) {
                    headerMenuButton.setBackgroundResource(R.drawable.baseline_close_white_36dp);
                } else {
                    headerMenuButton.setBackgroundResource(R.drawable.baseline_menu_white_36dp);
                }
            }
        });

        LinearLayout mainContent = (LinearLayout) findViewById(R.id.mainContent);
        mainContent.setOnClickListener(new LinearLayout.OnClickListener() {
            @Override
            public void onClick(View v) {
                commentMode = 1 - commentMode;
                Button headerMenuButton = (Button) findViewById(R.id.headerMenuButton);

                if (commentMode == 0) {
                    headerMenuButton.setBackgroundResource(R.drawable.baseline_close_white_36dp);
                } else {
                    headerMenuButton.setBackgroundResource(R.drawable.baseline_menu_white_36dp);
                }
            }
        });
    }

    // 앱 창을 올리면!!
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart()");
    }

    // 앱을 실행하는 중에 SMS가 날라오면!!
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause()");
    }

    // 앱 창을 내리면!!
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop()");
    }

    // 앱을 완전종료!!
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy()");
    }

    // SMS처리하고 다시 화면이 돌아오면!!
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume()");
    }

    // onStop() -> 다시 실행 -> onRestart(), onStart(), onResume()
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart()");
    }

}
