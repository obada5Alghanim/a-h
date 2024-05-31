package com.example.al_rewaq;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;



public class nav_drawer_menu_fragment extends Fragment {

    TextView textViewUserName;
    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME ="remberMeForAlrewaq";
    private static final String KEY_NAME ="USERNAME";
    private static final String KEY_PASSWORD ="PASSWORDUSER";
    private nav_drawer_menu_fragment navDrawerMenuFragment;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_nav_drawer_menu_fragment, container, false);
        navDrawerMenuFragment = new nav_drawer_menu_fragment();
        Button logout_btn = view.findViewById(R.id.logout_btn);
        Button cancel_btn = view.findViewById(R.id.cancel_btn);
        RelativeLayout remove_menu = view.findViewById(R.id.remove_menu);
        TextView textViewLogOut = view.findViewById(R.id.logOutID);
        TextView textViewSetting = view.findViewById(R.id.settingTextClick);
        TextView textViewMyLibrary = view.findViewById(R.id.myLib1);
        TextView textViewspeedTest = view.findViewById(R.id.speedTestID);
        TextView textViewchooesBook = view.findViewById(R.id.chooes_book);
        textViewUserName = view.findViewById(R.id.userNameTextViewInMenu);
        LinearLayout linearLayout = view.findViewById(R.id.navDrawerMenu2);
        RelativeLayout are_you_sure_logout = view.findViewById(R.id.are_you_sure_logout);
        sharedPreferences = getActivity().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        Bundle readUserName = this.getArguments();
        String rec = readUserName.getString("msg");
        textViewUserName.setText(rec);



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

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

        String checkedPref = sharedPreferences.getString(KEY_NAME,null);
        String checkedPrefPassword = sharedPreferences.getString(KEY_PASSWORD,null);
        if (checkedPref != null || checkedPrefPassword != null){

        }


        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();
                FirebaseAuth.getInstance().signOut();

                Intent intent = new Intent(getActivity(), Sign.class);
                startActivity(intent);
                getActivity().finish();
            }
        });


        are_you_sure_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                are_you_sure_logout.setVisibility(View.GONE);
            }
        });


        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                are_you_sure_logout.setVisibility(View.GONE);
            }
        });


        textViewLogOut.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                are_you_sure_logout.setVisibility(View.VISIBLE);
            }
        });


        remove_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeFragment();
            }
        });



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

}
