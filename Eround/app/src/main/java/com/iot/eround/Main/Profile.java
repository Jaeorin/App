package com.iot.eround.Main;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iot.eround.Main.ProfileFrag.Bookmark;
import com.iot.eround.Main.ProfileFrag.Comment;
import com.iot.eround.Main.ProfileFrag.ProfileContent;
import com.iot.eround.R;

public class Profile extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_main_profile, container, false);

        ProfileContent profileContent = new ProfileContent();
        Comment comment = new Comment();
        Bookmark bookmark = new Bookmark();

        getFragmentManager().beginTransaction().add(R.id.activity_main_profile_id_content, profileContent).show(profileContent).commit();
        getFragmentManager().beginTransaction().add(R.id.activity_main_profile_id_comment, comment).hide(comment).commit();
        getFragmentManager().beginTransaction().add(R.id.activity_main_profile_id_bookmark, bookmark).hide(bookmark).commit();

        TabLayout tab = view.findViewById(R.id.activity_main_profile_id_tab);
        tab.addTab(tab.newTab().setText("이야기"));
        tab.addTab(tab.newTab().setText("내 댓글"));
        tab.addTab(tab.newTab().setText("북마크"));

        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        getFragmentManager().beginTransaction().show(profileContent).commit();
                        getFragmentManager().beginTransaction().hide(comment).commit();
                        getFragmentManager().beginTransaction().hide(bookmark).commit();
                        break;
                    case 1:
                        getFragmentManager().beginTransaction().hide(profileContent).commit();
                        getFragmentManager().beginTransaction().show(comment).commit();
                        getFragmentManager().beginTransaction().hide(bookmark).commit();
                        break;
                    case 2:
                        getFragmentManager().beginTransaction().hide(profileContent).commit();
                        getFragmentManager().beginTransaction().hide(comment).commit();
                        getFragmentManager().beginTransaction().show(bookmark).commit();
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        getFragmentManager().beginTransaction().hide(profileContent).commit();
                        getFragmentManager().beginTransaction().hide(comment).commit();
                        getFragmentManager().beginTransaction().hide(bookmark).commit();
                    case 1:
                        getFragmentManager().beginTransaction().hide(profileContent).commit();
                        getFragmentManager().beginTransaction().hide(comment).commit();
                        getFragmentManager().beginTransaction().hide(bookmark).commit();
                    case 2:
                        getFragmentManager().beginTransaction().hide(profileContent).commit();
                        getFragmentManager().beginTransaction().hide(comment).commit();
                        getFragmentManager().beginTransaction().hide(bookmark).commit();
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return view;
    }

}

