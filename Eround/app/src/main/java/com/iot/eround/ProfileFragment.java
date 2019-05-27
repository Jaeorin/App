package com.iot.eround;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ProfileFragment extends Fragment {

    Fragment profileContentFragment = new ProfileContentFragment();
    Fragment profileTagFragment = new ProfileTagFragment();
    Fragment profileCommentFragment = new ProfileCommentFragment();
    Fragment profileBookmarkFragment = new ProfileBookmarkFragment();


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

