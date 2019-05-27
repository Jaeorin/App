package com.iot.eround;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.iot.eround.Util.ApiService;
import com.iot.eround.VO.Content;

import java.io.IOException;
import java.util.Random;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    Fragment mainFragment = new MainFragment();
    Fragment hashFragment = new HashFragment();
    Fragment writeFragment = new WriteFragment();
    Fragment alarmFragment = new AlarmFragment();
    Fragment profileFragment = new ProfileFragment();

    // 레트로핏에 필요한 객체 생성
    Retrofit retrofit;
    ApiService apiService;
    Gson gson = new Gson();

    // 메인화면 랜덤 배경 전환에 필요한 변수들
    int[] randomNumberArray = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    Random random = new Random();
    int randomNumber = random.nextInt(randomNumberArray.length);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        getSupportFragmentManager().beginTransaction().add(R.id.mainFrag_activity, mainFragment).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.hashFrag_activity, hashFragment).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.writeFrag_activity, writeFragment).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.alarmFrag_activity, alarmFragment).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.profileFrag_activity, profileFragment).commit();
        getSupportFragmentManager().beginTransaction().hide(hashFragment).commit();
        getSupportFragmentManager().beginTransaction().hide(writeFragment).commit();
        getSupportFragmentManager().beginTransaction().hide(alarmFragment).commit();
        getSupportFragmentManager().beginTransaction().hide(profileFragment).commit();

        retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);

        Call<ResponseBody> mainBackgroundImage = apiService.mainBackgroundImage("image", randomNumber + ".jpg");

        mainBackgroundImage.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {

                    Bitmap bitmap = BitmapFactory.decodeStream(response.body().byteStream());

                    MainFragment backGroundImage_mainFragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.backGroundImage_mainFragment);
                    backGroundImage_mainFragment.changeFragmentImageView(bitmap);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
            }
        });

        Call<ResponseBody> content = apiService.content("board", "findby", 1);

        content.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                try {
                    String json = response.body().string();
                    Content VO = gson.fromJson(json, Content.class);

                    MainFragment content_mainFragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.content_mainFragment);
                    content_mainFragment.changeFragmentTextView(VO.getBoardContent());
                    MainFragment location_mainFragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.location_mainFragment);
                    location_mainFragment.changeFragmentTextView(VO.getBoardRegion().getRegionName());
                    MainFragment time_mainFragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.time_mainFragment);
                    time_mainFragment.changeFragmentTextView(VO.getBoardCreateDate());
                    MainFragment emotion_mainFragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.emotion_mainFragment);
                    emotion_mainFragment.changeFragmentTextView(VO.getFeeling().getFeelingEmoticon());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
            }
        });

        Button locationButton_activity = findViewById(R.id.locationButton_activity);
        locationButton_activity.setOnClickListener(view -> {
            getSupportFragmentManager().beginTransaction().show(mainFragment).commit();
            getSupportFragmentManager().beginTransaction().hide(hashFragment).commit();
            getSupportFragmentManager().beginTransaction().hide(writeFragment).commit();
            getSupportFragmentManager().beginTransaction().hide(alarmFragment).commit();
            getSupportFragmentManager().beginTransaction().hide(profileFragment).commit();
            LinearLayout footer_activity = findViewById(R.id.footer_activity);
            footer_activity.setBackgroundColor(Color.argb(0,0,0,0));
        });

        Button compassButton_activity = findViewById(R.id.compassButton_activity);
        compassButton_activity.setOnClickListener(view -> {
            getSupportFragmentManager().beginTransaction().hide(mainFragment).commit();
            getSupportFragmentManager().beginTransaction().show(hashFragment).commit();
            getSupportFragmentManager().beginTransaction().hide(writeFragment).commit();
            getSupportFragmentManager().beginTransaction().hide(alarmFragment).commit();
            getSupportFragmentManager().beginTransaction().hide(profileFragment).commit();
            LinearLayout footer_activity = findViewById(R.id.footer_activity);
            footer_activity.setBackgroundColor(Color.rgb(0,0,0));
        });

        Button writeButton_activity = findViewById(R.id.writeButton_activity);
        writeButton_activity.setOnClickListener(view -> {
            getSupportFragmentManager().beginTransaction().hide(mainFragment).commit();
            getSupportFragmentManager().beginTransaction().hide(hashFragment).commit();
            getSupportFragmentManager().beginTransaction().show(writeFragment).commit();
            getSupportFragmentManager().beginTransaction().hide(alarmFragment).commit();
            getSupportFragmentManager().beginTransaction().hide(profileFragment).commit();
            LinearLayout footer_activity = findViewById(R.id.footer_activity);
            footer_activity.setBackgroundColor(Color.rgb(0,0,0));
        });

        Button alarmButton_activity = findViewById(R.id.alarmButton_activity);
        alarmButton_activity.setOnClickListener(view -> {
            getSupportFragmentManager().beginTransaction().hide(mainFragment).commit();
            getSupportFragmentManager().beginTransaction().hide(hashFragment).commit();
            getSupportFragmentManager().beginTransaction().hide(writeFragment).commit();
            getSupportFragmentManager().beginTransaction().show(alarmFragment).commit();
            getSupportFragmentManager().beginTransaction().hide(profileFragment).commit();
            LinearLayout footer_activity = findViewById(R.id.footer_activity);
            footer_activity.setBackgroundColor(Color.rgb(0,0,0));
        });

        Button profileButton_activity = findViewById(R.id.profileButton_activity);
        profileButton_activity.setOnClickListener(view -> {
            getSupportFragmentManager().beginTransaction().hide(mainFragment).commit();
            getSupportFragmentManager().beginTransaction().hide(hashFragment).commit();
            getSupportFragmentManager().beginTransaction().hide(writeFragment).commit();
            getSupportFragmentManager().beginTransaction().hide(alarmFragment).commit();
            getSupportFragmentManager().beginTransaction().show(profileFragment).commit();
            LinearLayout footer_activity = findViewById(R.id.footer_activity);
            footer_activity.setBackgroundColor(Color.rgb(0,0,0));
        });


    }

}
