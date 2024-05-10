package com.example.al_rewaq;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;





public class nav_drawer_menu_fragment extends Fragment {

    FirebaseAuth logOutAuth;

    TextView textViewUserName;

    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME ="remberMeForAlrewaq";
    private static final String KEY_NAME ="USERNAME";
    private static final String KEY_PASSWORD ="PASSWORDUSER";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_nav_drawer_menu_fragment, container, false);
        logOutAuth = FirebaseAuth.getInstance();
        TextView textViewLogOut = view.findViewById(R.id.logOutID);
        TextView textViewSetting = view.findViewById(R.id.settingTextClick);
        TextView textViewMyLibrary = view.findViewById(R.id.myLib1);
        TextView textViewspeedTest = view.findViewById(R.id.speedTestID);
        TextView textViewchooesBook = view.findViewById(R.id.chooes_book);
        textViewUserName = view.findViewById(R.id.userNameTextViewInMenu);
        LinearLayout linearLayout = view.findViewById(R.id.navDrawerMenu2);
        sharedPreferences = getActivity().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        //READING TABLE FOR TEST THE NEW SPEED TEST READING
        TextView textViewReadingTable = view.findViewById(R.id.READINGTABLE);


       /* DocumentReference documentReference = fStore.collection("Users").document(userId);

        documentReference.addSnapshotListener( this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                textViewUserName.setText(value.getString("First Name"));
            }
        });*/



        textViewMyLibrary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                MyLibrary_MainFragment myLibraryMainFragment = new MyLibrary_MainFragment();
                ft.replace(android.R.id.content,myLibraryMainFragment);
                ft.commit();
            }
        });
        textViewchooesBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                choose_book_Q1 choose_book_q1 = new choose_book_Q1();
                ft.replace(android.R.id.content,choose_book_q1);
                ft.commit();
            }
        });


        textViewspeedTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft  = fm.beginTransaction();
                reading_speed_test_new readingSpeedTestNew =new reading_speed_test_new();
                ft.replace(android.R.id.content,readingSpeedTestNew);
                ft.commit();


            }
        });

        textViewReadingTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeFragment();

            }
        });
        textViewSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                settings_page_fragment settingsPageFragment = new settings_page_fragment();
                ft.replace(android.R.id.content,settingsPageFragment);
                ft.commit();
            }
        });
        //cheack pref/////////////////////////////////////////////////////////
        String checkedPref = sharedPreferences.getString(KEY_NAME,null);
        String checkedPrefPassword = sharedPreferences.getString(KEY_PASSWORD,null);
        //in this code we will change the name of user
        if (checkedPref != null || checkedPrefPassword != null){

        }
        textViewLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();
                logOutAuth.signOut();
                Intent intent = new Intent(getActivity(),Sign_In_fragment.class);
                startActivity(intent);

            }
        });

       // fetchUserData();





        return view;
    }

    private void removeFragment() {
        if (requireActivity().getSupportFragmentManager() != null) {
            // Begin a transaction to remove this fragment
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .remove(this)
                    .commit();
        }

    }

   /* private void fetchUserData() {
        FirebaseUser currentUser = fAuth.getCurrentUser();
        if (currentUser != null) {
            String userId = currentUser.getUid();
            fStore.collection("Users").document(userId)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot document = task.getResult();
                                if (document.exists()) {

                                    String username = document.getString("First Name");

                                    textViewUserName.setText(username);
                                } else {
                                    textViewUserName.setText("اسم المستخدم");
                                }
                            } else {
                                textViewUserName.setText("Failed to fetch username");
                            }
                        }
                    });
        } else {
            textViewUserName.setText("User not logged in");
        }

    }*/





 }