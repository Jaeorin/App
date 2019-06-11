package com.iot.eround;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.iot.eround.Adapter.MenuAdapter;
import com.iot.eround.Fragment.Alarm.Alarm;
import com.iot.eround.Fragment.Hash.Hash;
import com.iot.eround.Fragment.Main.Main;
import com.iot.eround.Fragment.Profile.Profile;
import com.iot.eround.Fragment.Story.Story;
import com.iot.eround.Fragment.Write.Write;
import com.iot.eround.Util.ApiService;
import com.iot.eround.Util.ImageRoader;
import com.iot.eround.Util.RetroCallback;
import com.iot.eround.VO.Board;
import com.iot.eround.VO.Feeling;
import com.iot.eround.VO.Region;
import com.iot.eround.VO.Subscribe;
import com.iot.eround.VO.Tags;
import com.iot.eround.VO.Users;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    LinearLayout header_activity;
    LinearLayout hashHeader_activity;
    LinearLayout writeHeader_activity;
    LinearLayout alarmHeader_activity;
    LinearLayout writeFooter_activity;
    Button headerMenuButton;
    Button headerCancleButton;
    Button headerMoreButton;
    TextView headerText;

    public Fragment mainFragment = new Main();
    public Fragment hashFragment = new Hash();
    public Fragment writeFragment = new Write();
    public Fragment alarmFragment = new Alarm();
    public Fragment profileFragment = new Profile();
    public Fragment storyFragment = new Story();
    public Retrofit retrofit;
    public static ApiService apiService;

    public Random random = new Random();
    public ImageRoader imageRoader = new ImageRoader();
    public List<Integer> boardRandomNumberArray = new ArrayList<>();
    public int[] defaultRandomNumberArray = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    public int boardRandomNumber;
    public String url;

    DrawerLayout drawer;

    ExpandableListView expandableListView;
    private ArrayList<String> arrayGroup = new ArrayList<>();
    private HashMap<String, ArrayList<String>> arrayChild = new HashMap<>();

    private long pressedTime;

    TextView writeHeaderSave;

    Context mContext;

    public Context getContext() {
        return mContext;
    }

    public void setContext(Context mContext) {
        this.mContext = mContext;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setContext(this);

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);

        header_activity = findViewById(R.id.header_activity);
        hashHeader_activity = findViewById(R.id.hashHeader_activity);
        writeHeader_activity = findViewById(R.id.writeHeader_activity);
        alarmHeader_activity = findViewById(R.id.alarmHeader_activity);
        writeFooter_activity = findViewById(R.id.writeFooter_activity);
        headerMenuButton = findViewById(R.id.headerMenu_activity);
        headerCancleButton = findViewById(R.id.headerCancle_activity);
        headerMoreButton = findViewById(R.id.headerMore_activity);
        headerText = findViewById(R.id.headerName_activity);

        getSupportFragmentManager().beginTransaction().add(R.id.mainFrag_activity, mainFragment).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.hashFrag_activity, hashFragment).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.writeFrag_activity, writeFragment).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.alarmFrag_activity, alarmFragment).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.profileFrag_activity, profileFragment).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.storyFrag_activity, storyFragment).commit();
        getSupportFragmentManager().beginTransaction().hide(hashFragment).commit();
        getSupportFragmentManager().beginTransaction().hide(writeFragment).commit();
        getSupportFragmentManager().beginTransaction().hide(alarmFragment).commit();
        getSupportFragmentManager().beginTransaction().hide(profileFragment).commit();
        getSupportFragmentManager().beginTransaction().hide(storyFragment).commit();
        hashHeader_activity.setVisibility(View.GONE);
        writeHeader_activity.setVisibility(View.GONE);
        alarmHeader_activity.setVisibility(View.GONE);
        writeFooter_activity.setVisibility(View.GONE);

        drawer = findViewById(R.id.container);
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);


        expandableListView = this.findViewById(R.id.expandableListView_activity);

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

        Call<List<Tags>> tagsFindall = apiService.tagsFindall();

        tagsFindall.enqueue(new Callback<List<Tags>>() {
            @Override
            public void onResponse(Call<List<Tags>> call, Response<List<Tags>> response) {

                List<Tags> responsebody = response.body();

                try {

                    for(int i = 0; responsebody.size() > i; i++){
                        arrayTest2.add(responsebody.get(i).getTagName());

                    }

                } catch (Exception e) {

                    Log.i("test5 Exception e", e.toString());

                }
            }

            @Override
            public void onFailure(Call<List<Tags>> call, Throwable t) {

                Log.i("test1 onFailure", t.toString());

            }
        });


        ArrayList<String> arrayTest3 = new ArrayList<>();

        Call<List<Subscribe>> subscribeFindall = apiService.subscribeFindall();

        subscribeFindall.enqueue(new Callback<List<Subscribe>>() {
            @Override
            public void onResponse(Call<List<Subscribe>> call, Response<List<Subscribe>> response) {

                List<Subscribe> responsebody = response.body();

                try {

                    for(int i = 0; responsebody.size() > i; i++){
                        if(responsebody.get(i).getUser().getUserNum() == 1) {
                            arrayTest3.add(responsebody.get(i).getTag().getTagName());
                        }
                    }

                    if(arrayTest3.isEmpty()){
                        arrayTest3.add("구독 중인 태그가 없습니다");
                    }

                } catch (Exception e) {

                    Log.i("test5 Exception e", e.toString());

                }
            }

            @Override
            public void onFailure(Call<List<Subscribe>> call, Throwable t) {

                Log.i("test1 onFailure", t.toString());

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


        expandableListView.setAdapter(new MenuAdapter(this, arrayGroup, arrayChild));
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

        Bundle listBundle = new Bundle();

        expandableListView.setOnChildClickListener((expandableListView, view, groupPosition, childPosition, id) -> {
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
                    switch (childPosition) {
                        case 0:
                        case 1:
                        case 2:
                        case 3:
                        case 4:
                        case 5:
                            listBundle.putInt("groupPosition", groupPosition);
                            listBundle.putInt("childPosition", childPosition);
                            storyFragment.setArguments(listBundle);
                            storyFragShow();
                            drawer.closeDrawer(Gravity.LEFT);
                            break;
                    }
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

        Call<List<Board>> boardFindall = apiService.boardFindall();

        boardFindall.enqueue(new Callback<List<Board>>() {
            @Override
            public void onResponse(Call<List<Board>> call, Response<List<Board>> response) {

                List<Board> responsebody = response.body();

                for(int i = 0; responsebody.size() > i; i++){
                    boardRandomNumberArray.add(responsebody.get(i).getBoardNum());

                }

                boardRandomNumber = boardRandomNumberArray.get(random.nextInt(responsebody.size()));

                Call<Board> mainContent = apiService.boardFindby(boardRandomNumber);

                mainContent.enqueue(new Callback<Board>() {
                    @Override
                    public void onResponse(Call<Board> call, Response<Board> response) {

                        Board board = Translate(response.body());

                        if(board.getAttachFile().size() == 0) {

                            url = ApiService.URL + "/image/" + (random.nextInt(defaultRandomNumberArray.length) + 1) + ".jpg";

                        } else {

                            url = ApiService.URL + board.getAttachFile().get(1).getFilePath();

                        }

                        try {

                            ImageView backGroundImage_mainFragment = mainFragment.getView().findViewById(R.id.backGroundImage_mainFragment);
                            backGroundImage_mainFragment.setImageBitmap(imageRoader.getBitmapImg(url));

                            TextView contentText_mainFragment = mainFragment.getView().findViewById(R.id.contentText_mainFragment);
                            contentText_mainFragment.setText(board.getBoardContent());

                            TextView location_mainFragment = mainFragment.getView().findViewById(R.id.location_mainFragment);
                            location_mainFragment.setText(board.getBoardRegion().toString());

                            TextView time_mainFragment = mainFragment.getView().findViewById(R.id.time_mainFragment);
                            time_mainFragment.setText(board.getBoardCreateDate());

                            if (board.getFeeling().getFeelingNum() == 0) {
                                TextView emotion_mainFragment = mainFragment.getView().findViewById(R.id.emotion_mainFragment);
                                emotion_mainFragment.setVisibility(View.INVISIBLE);
                            } else {
                                TextView emotion_mainFragment = mainFragment.getView().findViewById(R.id.emotion_mainFragment);
                                emotion_mainFragment.setText(board.getFeeling().getFeelingEmoticon());
                            }

                            TextView favorite_mainFragment = mainFragment.getView().findViewById(R.id.favorite_mainFragment);
                            favorite_mainFragment.setText(String.valueOf(board.getReply().size()));

                            TextView comment_mainFragment = mainFragment.getView().findViewById(R.id.comment_mainFragment);
                            comment_mainFragment.setText(String.valueOf(board.getHeart().size()));



                        } catch (Exception e) {

                            Log.i("test5 Exception e", e.toString());

                        }
                    }

                    @Override
                    public void onFailure(Call<Board> call, Throwable t) {

                        Log.i("test1 onFailure", t.toString());

                    }
                });

            }

            @Override
            public void onFailure(Call<List<Board>> call, Throwable t) {

                Log.i("test2 onFailure", t.toString());

            }
        });

        Button locationButton_activity = findViewById(R.id.locationButton_activity);
        locationButton_activity.setOnClickListener(view -> mainFragShow());

        Button compassButton_activity = findViewById(R.id.compassButton_activity);
        compassButton_activity.setOnClickListener(view -> hashFragShow());

        Button writeButton_activity = findViewById(R.id.writeButton_activity);
        writeButton_activity.setOnClickListener(view -> writeFragShow());

        Button alarmButton_activity = findViewById(R.id.alarmButton_activity);
        alarmButton_activity.setOnClickListener(view -> alarmFragShow());

        Button profileButton_activity = findViewById(R.id.profileButton_activity);
        profileButton_activity.setOnClickListener(view -> profileFragShow());

        Button writeHeaderCancle = findViewById(R.id.writeHeaderCancle);
        writeHeaderCancle.setOnClickListener(view -> mainFragShow());

        writeHeaderSave = findViewById(R.id.writeHeaderSave);
        writeHeaderSave.setOnClickListener(view -> {

            EditText editText_writeFragment = writeFragment.getView().findViewById(R.id.editText_writeFragment);
            String text = editText_writeFragment.getText().toString();

            Board board = new Board();
            Users user = new Users();
            user.setUserNum(1);

            board.setBoardContent(text);
            board.setUser(user);
            board.setBoardRegion(Region.전국);

            postFirst(board, new RetroCallback() {
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

            mainFragShow();

        });


        Button headerMenu_activity = findViewById(R.id.headerMenu_activity);
        headerMenu_activity.setOnClickListener(view -> drawer.openDrawer(Gravity.LEFT));

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



    public Board Translate(Board board) {

        Optional<Board> optionalBoard = Optional.of(board);

        Feeling feeling = new Feeling(0, "", "", null, null, null);

        board.setFeeling(optionalBoard.map(Board::getFeeling).orElse(feeling));

        return board;
    }

    public void postFirst(Board board, final RetroCallback callback) {
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

    public void mainFragShow(){
        getSupportFragmentManager().beginTransaction().show(mainFragment).commit();
        getSupportFragmentManager().beginTransaction().hide(hashFragment).commit();
        getSupportFragmentManager().beginTransaction().hide(writeFragment).commit();
        getSupportFragmentManager().beginTransaction().hide(alarmFragment).commit();
        getSupportFragmentManager().beginTransaction().hide(profileFragment).commit();
        getSupportFragmentManager().beginTransaction().hide(storyFragment).commit();
        LinearLayout footer_activity = findViewById(R.id.footer_activity);
        footer_activity.setBackgroundColor(Color.argb(0,0,0,0));
        footer_activity.setVisibility(View.VISIBLE);
        header_activity.setVisibility(View.VISIBLE);
        hashHeader_activity.setVisibility(View.GONE);
        writeHeader_activity.setVisibility(View.GONE);
        alarmHeader_activity.setVisibility(View.GONE);
        writeFooter_activity.setVisibility(View.GONE);
        headerMenuButton.setVisibility(View.VISIBLE);
        headerCancleButton.setVisibility(View.INVISIBLE);
        headerMoreButton.setVisibility(View.INVISIBLE);
        headerText.setText("AROUND");
    }

    public void hashFragShow(){
        getSupportFragmentManager().beginTransaction().hide(mainFragment).commit();
        getSupportFragmentManager().beginTransaction().show(hashFragment).commit();
        getSupportFragmentManager().beginTransaction().hide(writeFragment).commit();
        getSupportFragmentManager().beginTransaction().hide(alarmFragment).commit();
        getSupportFragmentManager().beginTransaction().hide(profileFragment).commit();
        getSupportFragmentManager().beginTransaction().hide(storyFragment).commit();
        LinearLayout footer_activity = findViewById(R.id.footer_activity);
        footer_activity.setBackgroundColor(Color.rgb(0,0,0));
        header_activity.setVisibility(View.GONE);
        hashHeader_activity.setVisibility(View.VISIBLE);
        writeHeader_activity.setVisibility(View.GONE);
        alarmHeader_activity.setVisibility(View.GONE);
        writeFooter_activity.setVisibility(View.GONE);
    }

    public void writeFragShow(){
        getSupportFragmentManager().beginTransaction().hide(mainFragment).commit();
        getSupportFragmentManager().beginTransaction().hide(hashFragment).commit();
        getSupportFragmentManager().beginTransaction().show(writeFragment).commit();
        getSupportFragmentManager().beginTransaction().hide(alarmFragment).commit();
        getSupportFragmentManager().beginTransaction().hide(profileFragment).commit();
        getSupportFragmentManager().beginTransaction().hide(storyFragment).commit();
        LinearLayout footer_activity = findViewById(R.id.footer_activity);
        footer_activity.setVisibility(View.GONE);
        header_activity.setVisibility(View.GONE);
        hashHeader_activity.setVisibility(View.GONE);
        writeHeader_activity.setVisibility(View.VISIBLE);
        alarmHeader_activity.setVisibility(View.GONE);
        writeFooter_activity.setVisibility(View.VISIBLE);
        url = ApiService.URL + "/image/" + (random.nextInt(defaultRandomNumberArray.length) + 1) + ".jpg";
        Log.i("test image", url);
        ImageView backGroundImage_writeFragment = writeFragment.getView().findViewById(R.id.backGroundImage_writeFragment);
        backGroundImage_writeFragment.setImageBitmap(imageRoader.getBitmapImg(url));
    }

    public void alarmFragShow(){

        getSupportFragmentManager().beginTransaction().hide(mainFragment).commit();
        getSupportFragmentManager().beginTransaction().hide(hashFragment).commit();
        getSupportFragmentManager().beginTransaction().hide(writeFragment).commit();
        getSupportFragmentManager().beginTransaction().show(alarmFragment).commit();
        getSupportFragmentManager().beginTransaction().hide(profileFragment).commit();
        getSupportFragmentManager().beginTransaction().hide(storyFragment).commit();
        LinearLayout footer_activity = findViewById(R.id.footer_activity);
        footer_activity.setBackgroundColor(Color.rgb(0,0,0));
        header_activity.setVisibility(View.GONE);
        hashHeader_activity.setVisibility(View.GONE);
        writeHeader_activity.setVisibility(View.GONE);
        alarmHeader_activity.setVisibility(View.VISIBLE);
        writeFooter_activity.setVisibility(View.GONE);

    }

    public void profileFragShow(){
        getSupportFragmentManager().beginTransaction().hide(mainFragment).commit();
        getSupportFragmentManager().beginTransaction().hide(hashFragment).commit();
        getSupportFragmentManager().beginTransaction().hide(writeFragment).commit();
        getSupportFragmentManager().beginTransaction().hide(alarmFragment).commit();
        getSupportFragmentManager().beginTransaction().show(profileFragment).commit();
        getSupportFragmentManager().beginTransaction().hide(storyFragment).commit();
        LinearLayout footer_activity = findViewById(R.id.footer_activity);
        footer_activity.setBackgroundColor(Color.rgb(0,0,0));
        header_activity.setVisibility(View.VISIBLE);
        hashHeader_activity.setVisibility(View.GONE);
        writeHeader_activity.setVisibility(View.GONE);
        alarmHeader_activity.setVisibility(View.GONE);
        writeFooter_activity.setVisibility(View.GONE);
        headerMenuButton.setVisibility(View.INVISIBLE);
        headerCancleButton.setVisibility(View.INVISIBLE);
        headerMoreButton.setVisibility(View.INVISIBLE);
        headerText.setText("ME");
    }

    public void storyFragShow(){
        getSupportFragmentManager().beginTransaction().hide(mainFragment).commit();
        getSupportFragmentManager().beginTransaction().hide(hashFragment).commit();
        getSupportFragmentManager().beginTransaction().hide(writeFragment).commit();
        getSupportFragmentManager().beginTransaction().hide(alarmFragment).commit();
        getSupportFragmentManager().beginTransaction().hide(profileFragment).commit();
        getSupportFragmentManager().beginTransaction().show(storyFragment).commit();
    }

}