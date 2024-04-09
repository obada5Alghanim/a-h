package com.example.al_rewaq;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class menu_main extends AppCompatActivity {


BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_main);

        bottomNavigationView = findViewById(R.id.navigation_menu_id);
        home_page_fragment homePageFragment = new home_page_fragment();
        Categories_fragment categoriesFragment = new Categories_fragment();


   getSupportFragmentManager().beginTransaction().replace(android.R.id.content,homePageFragment).commit();
    bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected( MenuItem item) {
           if(item.getItemId() == R.id.home_icon1){
               getSupportFragmentManager().beginTransaction().replace(android.R.id.content,homePageFragment).commit();
           } else if (item.getItemId() == R.id.category_icon) {
               getSupportFragmentManager().beginTransaction().replace(android.R.id.content,categoriesFragment).commit();

           }
            return true;
        }
    });



    }
}