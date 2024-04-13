package com.example.al_rewaq;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class menu_main extends AppCompatActivity {

  //  private TextView textViewContent;
//    private Button buttonSeeMore;
BottomNavigationView bottomNavigationView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_main);


        bottomNavigationView = findViewById(R.id.navigation_menu_id);
        home_page_fragment homePageFragment = new home_page_fragment();
        Categories_fragment categoriesFragment = new Categories_fragment();
        search_fragment searchFragment = new search_fragment();
        nav_drawer_menu_fragment navDrawerMenuFragment = new nav_drawer_menu_fragment();
        Book_Title_fragment Book_Title_fragment = new Book_Title_fragment();

        getSupportFragmentManager().beginTransaction().replace(android.R.id.content,homePageFragment).commit();
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected( MenuItem item) {
                if(item.getItemId() == R.id.home_icon1){
                    getSupportFragmentManager().beginTransaction().replace(android.R.id.content,homePageFragment).commit();
                } else if (item.getItemId() == R.id.category_icon) {
                    getSupportFragmentManager().beginTransaction().replace(android.R.id.content,categoriesFragment).commit();
                } else if (item.getItemId() == R.id.search_icon) {
                    getSupportFragmentManager().beginTransaction().add(android.R.id.content,searchFragment).commit();
                    getSupportFragmentManager().beginTransaction().remove(navDrawerMenuFragment).commit();
                } if (item.getItemId() == R.id.library_icon) {
                    getSupportFragmentManager().beginTransaction().replace(android.R.id.content,Book_Title_fragment ).commit();
                }if (item.getItemId() == R.id.menu_icon){
                    getSupportFragmentManager().beginTransaction().add(android.R.id.content,navDrawerMenuFragment).commit();
                    getSupportFragmentManager().beginTransaction().remove(searchFragment).commit();
                }

                return true;
            }
        });



    }
}