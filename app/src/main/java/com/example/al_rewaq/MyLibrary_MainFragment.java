package com.example.al_rewaq;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MyLibrary_MainFragment extends Fragment {
    BottomNavigationView bottomNavigationView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_my_library__main, container, false);
        bottomNavigationView = view.findViewById(R.id.myLibr1);



        Reading_in_Progress_Fragment readingInProgressFragment = new Reading_in_Progress_Fragment();
        Reading_Later_Fragment readingLaterFragment = new Reading_Later_Fragment();
        Favorites_Fragment favoritesFragment = new Favorites_Fragment();
        Read_it_Fragment readItFragment = new Read_it_Fragment();

        getActivity().getSupportFragmentManager().beginTransaction().add(android.R.id.content,readingInProgressFragment).commit();


        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            String chr = "A";
        @Override
        public boolean onNavigationItemSelected( MenuItem item) {
            if(item.getItemId() == R.id.readingInProgressID ){
                if (chr != "A") {
                    getActivity().getSupportFragmentManager().beginTransaction().add(android.R.id.content, readingInProgressFragment).commit();
                    getActivity().getSupportFragmentManager().beginTransaction().remove(readingLaterFragment).commit();
                    getActivity().getSupportFragmentManager().beginTransaction().remove(favoritesFragment).commit();
                    getActivity().getSupportFragmentManager().beginTransaction().remove(readItFragment).commit();
                    chr = "A";
                }


            }  if (item.getItemId() == R.id.readingLaterID ) {
                if (chr != "B") {
                    getActivity().getSupportFragmentManager().beginTransaction().add(android.R.id.content, readingLaterFragment).commit();
                    getActivity().getSupportFragmentManager().beginTransaction().remove(readingInProgressFragment).commit();
                    getActivity().getSupportFragmentManager().beginTransaction().remove(favoritesFragment).commit();
                    getActivity().getSupportFragmentManager().beginTransaction().remove(readItFragment).commit();
                    chr = "B";
                }

            }  if (item.getItemId() == R.id.favoritesBookID ) {
                if (chr != "C") {
                    getActivity().getSupportFragmentManager().beginTransaction().add(android.R.id.content, favoritesFragment).commit();
                    getActivity().getSupportFragmentManager().beginTransaction().remove(readingInProgressFragment).commit();
                    getActivity().getSupportFragmentManager().beginTransaction().remove(readingLaterFragment).commit();
                    getActivity().getSupportFragmentManager().beginTransaction().remove(readItFragment).commit();
                    chr = "C";
                }

            }  if (item.getItemId() == R.id.readItID ) {
                if (chr != "D") {
                    getActivity().getSupportFragmentManager().beginTransaction().add(android.R.id.content, readItFragment).commit();
                    getActivity().getSupportFragmentManager().beginTransaction().remove(readingInProgressFragment).commit();
                    getActivity().getSupportFragmentManager().beginTransaction().remove(readingLaterFragment).commit();
                    getActivity().getSupportFragmentManager().beginTransaction().remove(favoritesFragment).commit();
                    chr = "D";
                }

            }
            return true;
        }
    });





        return view;
    }
}