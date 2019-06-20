package com.iot.movie2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rvMovie = findViewById(R.id.rv_movie);
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        rvMovie.setLayoutManager(layoutManager);

        APIService service = APIService.retrofit.create(APIService.class);
        Call<List<Contributor>> call = gitHubService.repoContributors("square", "retrofit");
        call.enqueue(new Callback<List<Contributor>>() {
            @Override
            public void onResponse(Call<List<Contributor>> call,
                                   Response<List<Contributor>> response) {

                TextView textView = findViewById(R.id.textview1);
                textView.setText(response.body().toString());
            }

            @Override
            public void onFailure(Call<List<Contributor>> call, Throwable t) {

            }
        });

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GithubService gitHubService = GithubService.retrofit.create(GithubService.class);
                final Call<List<Contributor>> call = gitHubService.repoContributors("square", "retrofit");
                new NetworkCall().execute(call);
            }
        });

    }


        ArrayList<Person> persons = new ArrayList<>();
        persons.add(new Person("김민수", "010-2222-7777", R.mipmap.ic_launcher));
        persons.add(new Person("홍길동", "010-2222-7777", R.mipmap.ic_launcher));
        persons.add(new Person("장동건", "010-2222-7777", R.mipmap.ic_launcher));
        persons.add(new Person("김하늘", "010-2222-7777", R.mipmap.ic_launcher));
        persons.add(new Person("유지태", "010-2222-7777", R.mipmap.ic_launcher));
        persons.add(new Person("박보영", "010-2222-7777", R.mipmap.ic_launcher));
        persons.add(new Person("손담비", "010-2222-7777", R.mipmap.ic_launcher));
        persons.add(new Person("이보영", "010-2222-7777", R.mipmap.ic_launcher));
        persons.add(new Person("오보은", "010-2222-7777", R.mipmap.ic_launcher));
        persons.add(new Person("정보라", "010-2222-7777", R.mipmap.ic_launcher));
        persons.add(new Person("심민정", "010-2222-7777", R.mipmap.ic_launcher));
        persons.add(new Person("김현정", "010-2222-7777", R.mipmap.ic_launcher));
        persons.add(new Person("오달수", "010-2222-7777", R.mipmap.ic_launcher));

        PersonAdapter adapter = new PersonAdapter();
        adapter.setItems(persons);

        rvMovie.setAdapter(adapter);

    }
}
