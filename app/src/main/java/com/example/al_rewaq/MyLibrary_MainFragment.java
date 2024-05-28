package com.example.al_rewaq;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MyLibrary_MainFragment extends Fragment {
    BottomNavigationView bottomNavigationView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_library__main, container, false);
        bottomNavigationView = view.findViewById(R.id.myLibr1);

        Reading_in_Progress_Fragment readingInProgressFragment = new Reading_in_Progress_Fragment();
        Reading_Later_Fragment readingLaterFragment = new Reading_Later_Fragment();
        Favorites_Fragment favoritesFragment = new Favorites_Fragment();
        Read_it_Fragment readItFragment = new Read_it_Fragment();

        // Set default fragment
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.MyLibrary, readingInProgressFragment).commit();
        bottomNavigationView.setSelectedItemId(R.id.readingInProgressID); // Set the default selected item

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            String chr = "A";

            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                if (item.getItemId() == R.id.readingInProgressID) {
                    if (!chr.equals("A")) {
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.MyLibrary, readingInProgressFragment).commit();
                        chr = "A";
                    }
                } else if (item.getItemId() == R.id.readingLaterID) {
                    if (!chr.equals("B")) {
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.MyLibrary, readingLaterFragment).commit();
                        chr = "B";
                    }
                } else if (item.getItemId() == R.id.favoritesBookID) {
                    if (!chr.equals("C")) {
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.MyLibrary, favoritesFragment).commit();
                        chr = "C";
                    }
                } else if (item.getItemId() == R.id.readItID) {
                    if (!chr.equals("D")) {
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.MyLibrary, readItFragment).commit();
                        chr = "D";
                    }
                }
                return true;
            }
        });

        return view;
    }
}
