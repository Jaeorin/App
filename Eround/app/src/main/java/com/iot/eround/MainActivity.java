package com.iot.eround;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.iot.eround.Adapter.MainMenuAdapter;
import com.iot.eround.Main.Alarm;
import com.iot.eround.Main.Content;
import com.iot.eround.Main.Hash;
import com.iot.eround.Main.Profile;
import com.iot.eround.Main.Story;
import com.iot.eround.Main.Write;
import com.iot.eround.Util.ApiService;
import com.iot.eround.Util.ImageRoader;
import com.iot.eround.Util.RetroCallback;
import com.iot.eround.VO.Board;
import com.iot.eround.VO.Region;
import com.iot.eround.VO.Subscribe;
import com.iot.eround.VO.Tags;
import com.iot.eround.VO.Users;
import com.iot.eround.VOApp.MenuContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public Fragment mainFragment = new Content();
    public Fragment hashFragment = new Hash();
    public Fragment writeFragment = new Write();
    public Fragment alarmFragment = new Alarm();
    public Fragment profileFragment = new Profile();
    public Fragment storyFragment = new Story();


    Random random = new Random();
    ImageRoader imageRoader = new ImageRoader();
    ArrayList<String> arrayGroup = new ArrayList<>();
    HashMap<String, ArrayList<String>> arrayChild = new HashMap<>();
    MenuContext menuContext = new MenuContext();

    long pressedTime = 0;

    int[] defaultRandomNumberArray = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        menuContext.setMenuContext(this);

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);

        getSupportFragmentManager().beginTransaction().add(R.id.activity_main_id_mainFragment, mainFragment).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.activity_main_id_hashFragment, hashFragment).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.activity_main_id_writeFragment, writeFragment).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.activity_main_id_alarmFragment, alarmFragment).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.activity_main_id_profileFragment, profileFragment).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.activity_main_id_storyFragment, storyFragment).commit();

        getSupportFragmentManager().beginTransaction().hide(hashFragment).commit();
        getSupportFragmentManager().beginTransaction().hide(writeFragment).commit();
        getSupportFragmentManager().beginTransaction().hide(alarmFragment).commit();
        getSupportFragmentManager().beginTransaction().hide(profileFragment).commit();
        getSupportFragmentManager().beginTransaction().hide(storyFragment).commit();

        LinearLayout contentHeader = findViewById(R.id.activity_main_id_content_header);
        LinearLayout hashHeader = findViewById(R.id.activity_main_id_hash_header);
        hashHeader.setVisibility(View.GONE);
        LinearLayout writeHeader = findViewById(R.id.activity_main_id_write_header);
        writeHeader.setVisibility(View.GONE);
        LinearLayout alarmHeader = findViewById(R.id.activity_main_id_alarm_header);
        alarmHeader.setVisibility(View.GONE);
        LinearLayout writeFooter = findViewById(R.id.activity_main_id_write_footer);
        writeFooter.setVisibility(View.GONE);
        LinearLayout contentFooter = findViewById(R.id.activity_main_id_content_footer);

        Button contentHeaderMenu = findViewById(R.id.activity_main_id_content_header_menu);
        Button contentHeaderClose = findViewById(R.id.activity_main_id_content_header_close);
        Button contentHeaderMore = findViewById(R.id.activity_main_id_content_header_more);
        TextView contentHeaderTitle = findViewById(R.id.activity_main_id_content_header_title);

        DrawerLayout drawer = findViewById(R.id.container);
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        ExpandableListView expandableListView = this.findViewById(R.id.expandable_list_menu);

        arrayGroup.add("일반");
        arrayGroup.add("추천 보기");
        arrayGroup.add("구독 중인 태그");
        arrayGroup.add("부가 기능");

        ArrayList<String> arrayTest1 = new ArrayList<>();
        arrayTest1.add("시작하는 이야기");
        arrayTest1.add("많이 공유된 이야기");
        arrayTest1.add("더 공감 받는 이야기");
        arrayTest1.add("거리가 가까운 이야기");

        ArrayList<String> arrayTest2 = new ArrayList<>();

        Call<List<Tags>> tagsFindAll = apiService.tagsFindall();
        tagsFindAll.enqueue(new Callback<List<Tags>>() {
            @Override
            public void onResponse(Call<List<Tags>> call, Response<List<Tags>> response) {

                List<Tags> responseBody = response.body();
                Log.i("test1 Tags", responseBody.toString());

                try {
                    for(int i = 0; responseBody.size() > i; i++){
                        arrayTest2.add(responseBody.get(i).getTagName());
                    }
                } catch (Exception e) {
                    Log.i("test1 tagsFindAll Exception", e.toString());
                }

            }

            @Override
            public void onFailure(Call<List<Tags>> call, Throwable t) {

                Log.i("test1 tagsFindAll onFailure", t.toString());

            }

        });

        ArrayList<String> arrayTest3 = new ArrayList<>();

        Call<List<Subscribe>> subscribeFindAll = apiService.subscribeFindall();
        subscribeFindAll.enqueue(new Callback<List<Subscribe>>() {
            @Override
            public void onResponse(Call<List<Subscribe>> call, Response<List<Subscribe>> response) {

                List<Subscribe> responseBody = response.body();

                try {
                    for(int i = 0; responseBody.size() > i; i++){
                        if(responseBody.get(i).getUser().getUserNum() == 1) {
                            arrayTest3.add(responseBody.get(i).getTag().getTagName());
                        }
                    }
                    if(arrayTest3.isEmpty()){
                        arrayTest3.add("구독 중인 태그가 없습니다");
                    }
                } catch (Exception e) {
                    Log.i("subscribeFindAll Exception", e.toString());
                }

            }

            @Override
            public void onFailure(Call<List<Subscribe>> call, Throwable t) {

                Log.i("subscribeFindAll onFailure", t.toString());

            }

        });

        ArrayList<String> arrayTest4 = new ArrayList<>();
        arrayTest4.add("탐색 환경 설정");
        arrayTest4.add("설정");
        arrayTest4.add("도움말과 의견 보내기");

        arrayChild.put(arrayGroup.get(0), arrayTest1);
        arrayChild.put(arrayGroup.get(1), arrayTest2);
        arrayChild.put(arrayGroup.get(2), arrayTest3);
        arrayChild.put(arrayGroup.get(3), arrayTest4);

        expandableListView.setAdapter(new MainMenuAdapter(this, arrayGroup, arrayChild));
        expandableListView.expandGroup(0);
        expandableListView.expandGroup(1);
        expandableListView.expandGroup(2);
        expandableListView.expandGroup(3);

        expandableListView.setOnGroupClickListener((arg0, itemView, itemPosition, itemId) -> {

            if(itemPosition == 1 || itemPosition == 2 ) {

                if(!expandableListView.isGroupExpanded(itemPosition)) {
                    expandableListView.expandGroup(itemPosition);
                }else{
                    expandableListView.collapseGroup(itemPosition);
                }

            }

            return true;

        });

        Bundle listBundle = new Bundle(2);
        expandableListView.setOnChildClickListener((listView, view, groupPosition, childPosition, id) -> {

            switch (groupPosition) {
                case 0:
                    switch (childPosition) {
                        case 0:
                        case 1:
                        case 2:
                        case 3:
                            break;
                    }
                    break;
                case 1:
                    getSupportFragmentManager().beginTransaction().detach(storyFragment).commit();
                    listBundle.putInt("groupPosition", groupPosition);
                    listBundle.putInt("childPosition", childPosition);
                    storyFragment.setArguments(listBundle);
                    getSupportFragmentManager().beginTransaction().attach(storyFragment).commit();
                    getSupportFragmentManager().beginTransaction().hide(mainFragment).commit();
                    getSupportFragmentManager().beginTransaction().hide(hashFragment).commit();
                    getSupportFragmentManager().beginTransaction().hide(writeFragment).commit();
                    getSupportFragmentManager().beginTransaction().hide(alarmFragment).commit();
                    getSupportFragmentManager().beginTransaction().hide(profileFragment).commit();
                    getSupportFragmentManager().beginTransaction().show(storyFragment).commit();
                    drawer.closeDrawer(Gravity.LEFT);
                    break;
                case 2:
                    switch (childPosition) {
                        case 0:
                            break;
                    }
                    break;
                case 3:
                    switch (childPosition) {
                        case 0:
                            break;
                        case 1:
                            break;
                        case 2:
                            break;
                    }
                    break;
            }
            return true;
        });

        Button footerLocation = findViewById(R.id.activity_main_id_content_footer_location);
        footerLocation.setOnClickListener(view -> {

            getSupportFragmentManager().beginTransaction().show(mainFragment).commit();
            getSupportFragmentManager().beginTransaction().hide(hashFragment).commit();
            getSupportFragmentManager().beginTransaction().hide(writeFragment).commit();
            getSupportFragmentManager().beginTransaction().hide(alarmFragment).commit();
            getSupportFragmentManager().beginTransaction().hide(profileFragment).commit();
            getSupportFragmentManager().beginTransaction().hide(storyFragment).commit();

            contentFooter.setBackgroundColor(Color.argb(0,0,0,0));
            contentFooter.setVisibility(View.VISIBLE);

            contentHeader.setVisibility(View.VISIBLE);
            hashHeader.setVisibility(View.GONE);
            writeHeader.setVisibility(View.GONE);
            alarmHeader.setVisibility(View.GONE);
            writeFooter.setVisibility(View.GONE);

            contentHeaderMenu.setVisibility(View.VISIBLE);
            contentHeaderClose.setVisibility(View.INVISIBLE);
            contentHeaderMore.setVisibility(View.INVISIBLE);
            contentHeaderTitle.setText("AROUND");

        });

        Button footerCompass = findViewById(R.id.activity_main_id_content_footer_compass);
        footerCompass.setOnClickListener(view -> {

            getSupportFragmentManager().beginTransaction().hide(mainFragment).commit();
            getSupportFragmentManager().beginTransaction().show(hashFragment).commit();
            getSupportFragmentManager().beginTransaction().hide(writeFragment).commit();
            getSupportFragmentManager().beginTransaction().hide(alarmFragment).commit();
            getSupportFragmentManager().beginTransaction().hide(profileFragment).commit();
            getSupportFragmentManager().beginTransaction().hide(storyFragment).commit();

            contentFooter.setBackgroundColor(Color.rgb(0,0,0));

            contentHeader.setVisibility(View.GONE);
            hashHeader.setVisibility(View.VISIBLE);
            writeHeader.setVisibility(View.GONE);
            alarmHeader.setVisibility(View.GONE);
            writeFooter.setVisibility(View.GONE);

        });

        Button writeButton_activity = findViewById(R.id.activity_main_id_content_footer_write);
        writeButton_activity.setOnClickListener(view -> {

            getSupportFragmentManager().beginTransaction().hide(mainFragment).commit();
            getSupportFragmentManager().beginTransaction().hide(hashFragment).commit();
            getSupportFragmentManager().beginTransaction().show(writeFragment).commit();
            getSupportFragmentManager().beginTransaction().hide(alarmFragment).commit();
            getSupportFragmentManager().beginTransaction().hide(profileFragment).commit();
            getSupportFragmentManager().beginTransaction().hide(storyFragment).commit();

            contentFooter.setVisibility(View.GONE);

            contentHeader.setVisibility(View.GONE);
            hashHeader.setVisibility(View.GONE);
            writeHeader.setVisibility(View.VISIBLE);
            alarmHeader.setVisibility(View.GONE);
            writeFooter.setVisibility(View.VISIBLE);

            String url = ApiService.URL + "/image/" + (random.nextInt(defaultRandomNumberArray.length) + 1) + ".jpg";
            Log.i("test image", url);

            ImageView backGroundImage_writeFragment = writeFragment.getView().findViewById(R.id.activity_main_write_id_image);
            backGroundImage_writeFragment.setImageBitmap(imageRoader.getBitmapImg(url));

            EditText editText = writeFragment.getView().findViewById(R.id.activity_main_write_id_text);
            editText.requestFocus();

            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

        });

        Button footerAlarm = findViewById(R.id.activity_main_id_content_footer_alarm);
        footerAlarm.setOnClickListener(view -> {

            getSupportFragmentManager().beginTransaction().hide(mainFragment).commit();
            getSupportFragmentManager().beginTransaction().hide(hashFragment).commit();
            getSupportFragmentManager().beginTransaction().hide(writeFragment).commit();
            getSupportFragmentManager().beginTransaction().show(alarmFragment).commit();
            getSupportFragmentManager().beginTransaction().hide(profileFragment).commit();
            getSupportFragmentManager().beginTransaction().hide(storyFragment).commit();

            contentFooter.setBackgroundColor(Color.rgb(0,0,0));

            contentHeader.setVisibility(View.GONE);
            hashHeader.setVisibility(View.GONE);
            writeHeader.setVisibility(View.GONE);
            alarmHeader.setVisibility(View.VISIBLE);
            writeFooter.setVisibility(View.GONE);

        });

        Button footerProfile = findViewById(R.id.activity_main_id_content_footer_profile);
        footerProfile.setOnClickListener(view -> {

            getSupportFragmentManager().beginTransaction().hide(mainFragment).commit();
            getSupportFragmentManager().beginTransaction().hide(hashFragment).commit();
            getSupportFragmentManager().beginTransaction().hide(writeFragment).commit();
            getSupportFragmentManager().beginTransaction().hide(alarmFragment).commit();
            getSupportFragmentManager().beginTransaction().show(profileFragment).commit();
            getSupportFragmentManager().beginTransaction().hide(storyFragment).commit();

            contentFooter.setBackgroundColor(Color.rgb(0,0,0));

            contentHeader.setVisibility(View.VISIBLE);
            hashHeader.setVisibility(View.GONE);
            writeHeader.setVisibility(View.GONE);
            alarmHeader.setVisibility(View.GONE);
            writeFooter.setVisibility(View.GONE);

            contentHeaderMenu.setVisibility(View.INVISIBLE);
            contentHeaderClose.setVisibility(View.INVISIBLE);
            contentHeaderMore.setVisibility(View.INVISIBLE);
            contentHeaderTitle.setText("ME");

        });

        Button writeHeaderClose = findViewById(R.id.activity_main_id_write_header_close);
        writeHeaderClose.setOnClickListener(view -> {

            getSupportFragmentManager().beginTransaction().show(mainFragment).commit();
            getSupportFragmentManager().beginTransaction().hide(hashFragment).commit();
            getSupportFragmentManager().beginTransaction().hide(writeFragment).commit();
            getSupportFragmentManager().beginTransaction().hide(alarmFragment).commit();
            getSupportFragmentManager().beginTransaction().hide(profileFragment).commit();
            getSupportFragmentManager().beginTransaction().hide(storyFragment).commit();

            contentFooter.setBackgroundColor(Color.argb(0,0,0,0));
            contentFooter.setVisibility(View.VISIBLE);

            contentHeader.setVisibility(View.VISIBLE);
            hashHeader.setVisibility(View.GONE);
            writeHeader.setVisibility(View.GONE);
            alarmHeader.setVisibility(View.GONE);
            writeFooter.setVisibility(View.GONE);

            contentHeaderMenu.setVisibility(View.VISIBLE);
            contentHeaderClose.setVisibility(View.INVISIBLE);
            contentHeaderMore.setVisibility(View.INVISIBLE);
            contentHeaderTitle.setText("AROUND");

            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);

        });

        TextView writeHeaderSave = findViewById(R.id.activity_main_id_write_header_save);
        writeHeaderSave.setOnClickListener(view -> {

            EditText editText = writeFragment.getView().findViewById(R.id.activity_main_write_id_text);
            String text = editText.getText().toString();

            Board board = new Board();
            Users user = new Users();
            user.setUserNum(1);

            board.setBoardContent(text);
            board.setUser(user);
            board.setBoardRegion(Region.전국);

            boardSave(board, apiService, new RetroCallback() {

                @Override
                public void onError(Throwable t) {
                    Log.i("test onError", t.toString());
                }

                @Override
                public void onSuccess(int code, Object receivedData) {
                    Board data = (Board) receivedData;
                    Log.i("test onSuccess codeResultTextView", String.valueOf(code));
                    Log.i("test onSuccess idResultTextView", String.valueOf(data.getBoardNum()));
                    Log.i("test onSuccess titleResultTextView", data.getBoardContent());
                    Log.i("test onSuccess useridResultTextView", String.valueOf(data.getBoardRegion()));
                    Log.i("test onSuccess bodyResultTextView", String.valueOf(data.getUser().getUserNum()));
                }

                @Override
                public void onFailure(int code) {
                    Log.i("test onFailure", String.valueOf(code));
                }

            });

            getSupportFragmentManager().beginTransaction().show(mainFragment).commit();
            getSupportFragmentManager().beginTransaction().hide(hashFragment).commit();
            getSupportFragmentManager().beginTransaction().hide(writeFragment).commit();
            getSupportFragmentManager().beginTransaction().hide(alarmFragment).commit();
            getSupportFragmentManager().beginTransaction().hide(profileFragment).commit();
            getSupportFragmentManager().beginTransaction().hide(storyFragment).commit();

            contentFooter.setBackgroundColor(Color.argb(0,0,0,0));
            contentFooter.setVisibility(View.VISIBLE);

            contentHeader.setVisibility(View.VISIBLE);
            hashHeader.setVisibility(View.GONE);
            writeHeader.setVisibility(View.GONE);
            alarmHeader.setVisibility(View.GONE);
            writeFooter.setVisibility(View.GONE);

            contentHeaderMenu.setVisibility(View.VISIBLE);
            contentHeaderClose.setVisibility(View.INVISIBLE);
            contentHeaderMore.setVisibility(View.INVISIBLE);
            contentHeaderTitle.setText("AROUND");

            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);

            editText.setText("");

        });

        contentHeaderMenu.setOnClickListener(view -> drawer.openDrawer(Gravity.LEFT));

        Button writeFooterShop = findViewById(R.id.activity_main_id_write_footer_shop);
        writeFooterShop.setOnClickListener(view -> {

            EditText editText = writeFragment.getView().findViewById(R.id.activity_main_write_id_text);
            editText.append("#");

        });

    }

    @Override
    public void onBackPressed() {
        if ( pressedTime == 0 ) {
            Toast.makeText(MainActivity.this, " 한 번 더 누르면 종료됩니다." , Toast.LENGTH_LONG).show();
            pressedTime = System.currentTimeMillis();
        }
        else {
            int seconds = (int) (System.currentTimeMillis() - pressedTime);

            if (seconds > 2000) {
                Toast.makeText(MainActivity.this, " 한 번 더 누르면 종료됩니다.", Toast.LENGTH_LONG).show();
                pressedTime = 0;
            } else {
                super.onBackPressed();
            }
        }
    }

    public void boardSave(Board board, ApiService apiService, final RetroCallback callback) {

        apiService.boardSave(board).enqueue(new Callback<Board>() {
            @Override
            public void onResponse(Call<Board> call, Response<Board> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.code(), response.body());
                } else {
                    callback.onFailure(response.code());
                }
            }

            @Override
            public void onFailure(Call<Board> call, Throwable t) {
                callback.onError(t);
            }

        });

    }

    public void changeFragment(){
        getSupportFragmentManager().beginTransaction().replace(R.id.activity_main_id_mainFragment, mainFragment).commit();
        getSupportFragmentManager().beginTransaction().show(mainFragment).commit();
        getSupportFragmentManager().beginTransaction().hide(hashFragment).commit();
        getSupportFragmentManager().beginTransaction().hide(writeFragment).commit();
        getSupportFragmentManager().beginTransaction().hide(alarmFragment).commit();
        getSupportFragmentManager().beginTransaction().hide(profileFragment).commit();
        getSupportFragmentManager().beginTransaction().hide(storyFragment).commit();
    }

}