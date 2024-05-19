package com.example.al_rewaq;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class menu_main extends AppCompatActivity {


    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_main);


        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        bottomNavigationView = findViewById(R.id.navigation_menu_id);
        home_page_fragment homePageFragment = new home_page_fragment();
        Categories_fragment categoriesFragment = new Categories_fragment();
        search_fragment searchFragment = new search_fragment();
        nav_drawer_menu_fragment navDrawerMenuFragment = new nav_drawer_menu_fragment();
        MyLibrary_MainFragment myLibraryMainFragment = new MyLibrary_MainFragment();
        Book_Title_fragment Book_Title_fragment = new Book_Title_fragment();
        Bundle bun = new Bundle();
        // استرجاع بيانات المستخدم من Firestore
        db.collection("users").document(userId)
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        // الحصول على بيانات المستخدم بنجاح
                        String firstName = documentSnapshot.getString("First_Name");
                        String lastName = documentSnapshot.getString("Last_Name");
                        String email = documentSnapshot.getString("User_name");
                        // يمكنك استخدام البيانات كما تحتاج
                        bun.putString("msg",firstName+" "+lastName);
                        navDrawerMenuFragment.setArguments(bun);



                    } else {
                        // لا يوجد بيانات لهذا المستخدم
                    }
                })
                .addOnFailureListener(e -> {
                    // فشل في استرجاع بيانات المستخدم
                });

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
                    getSupportFragmentManager().beginTransaction().replace(android.R.id.content,myLibraryMainFragment ).commit();
                }if (item.getItemId() == R.id.menu_icon){
                    getSupportFragmentManager().beginTransaction().add(android.R.id.content,navDrawerMenuFragment).commit();
                    getSupportFragmentManager().beginTransaction().remove(searchFragment).commit();
                }

                return true;
            }
        });



    }
}