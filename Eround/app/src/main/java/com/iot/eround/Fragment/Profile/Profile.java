package com.iot.eround.Fragment.Profile;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iot.eround.Fragment.Profile.Tab.Bookmark;
import com.iot.eround.Fragment.Profile.Tab.Comment;
import com.iot.eround.Fragment.Profile.Tab.Content;
import com.iot.eround.Fragment.Profile.Tab.Tag;
import com.iot.eround.R;

public class Profile extends Fragment {

    Fragment profileContentFragment = new Content();
    Fragment profileTagFragment = new Tag();
    Fragment profileCommentFragment = new Comment();
    Fragment profileBookmarkFragment = new Bookmark();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        getFragmentManager().beginTransaction().replace(R.id.Fragment_profile, profileContentFragment).commit();

        TabLayout tab = view.findViewById(R.id.tab_profile);
        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                int position = tab.getPosition();

                if (position == 0) {

                    getFragmentManager().beginTransaction().replace(R.id.Fragment_profile, profileContentFragment).commit();

                } else if (position == 1) {

                    getFragmentManager().beginTransaction().replace(R.id.Fragment_profile, profileTagFragment).commit();

                } else if (position == 2) {

                    getFragmentManager().beginTransaction().replace(R.id.Fragment_profile, profileCommentFragment).commit();

                } else if (position == 3) {

                    getFragmentManager().beginTransaction().replace(R.id.Fragment_profile, profileBookmarkFragment).commit();

                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return view;
    }

}

