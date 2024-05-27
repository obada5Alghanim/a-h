package com.example.al_rewaq;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class menu_main extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private home_page_fragment homePageFragment;
    private Categories_fragment categoriesFragment;
    private search_fragment searchFragment;
    private nav_drawer_menu_fragment navDrawerMenuFragment;
    private MyLibrary_MainFragment myLibraryMainFragment;
    private Book_Title_fragment Book_Title_fragment;

    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(android.R.id.content);
        if (currentFragment instanceof home_page_fragment) {
            finishAffinity();  // Close the application
        } else {
            // Move to home_page_fragment and update the navigation view
            getSupportFragmentManager().beginTransaction().replace(android.R.id.content, homePageFragment).commit();
            bottomNavigationView.setSelectedItemId(R.id.home_icon1);
        }
        //super.onBackPressed(); // This line is intentionally commented out to avoid the default behavior.
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_main);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        bottomNavigationView = findViewById(R.id.navigation_menu_id);
        homePageFragment = new home_page_fragment();
        categoriesFragment = new Categories_fragment();
        searchFragment = new search_fragment();
        navDrawerMenuFragment = new nav_drawer_menu_fragment();
        myLibraryMainFragment = new MyLibrary_MainFragment();
        Book_Title_fragment = new Book_Title_fragment();
        Bundle bun = new Bundle();

        db.collection("users").document(userId)
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        String firstName = documentSnapshot.getString("First_Name");
                        String lastName = documentSnapshot.getString("Last_Name");
                        String email = documentSnapshot.getString("User_name");
                        bun.putString("msg", firstName + " " + lastName);
                        navDrawerMenuFragment.setArguments(bun);
                    }
                })
                .addOnFailureListener(e -> {
                    // Handle failure
                });

        getSupportFragmentManager().beginTransaction().replace(android.R.id.content, homePageFragment).commit();
        bottomNavigationView.setSelectedItemId(R.id.home_icon1); // Set initial selection
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
