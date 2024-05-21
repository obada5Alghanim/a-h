package com.example.al_rewaq;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;


public class are_you_sure_logout_fragment extends Fragment {

    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME ="remberMeForAlrewaq";
    private static final String KEY_NAME ="USERNAME";
    private static final String KEY_PASSWORD ="PASSWORDUSER";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_are_you_sure_logout_fragment, container, false);


        TextView logout = view.findViewById(R.id.logout_btn);


        String checkedPref = sharedPreferences.getString(KEY_NAME,null);
        String checkedPrefPassword = sharedPreferences.getString(KEY_PASSWORD,null);
        //in this code we will change the name of user

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();
                FirebaseAuth.getInstance().signOut();

                // توجيه المستخدم إلى الـ Activity التي تحوي Fragment الـ SignIn
                Intent intent = new Intent(getActivity(), Sign.class);
                startActivity(intent);
                getActivity().finish(); // اختياري: لإغلاق الـ Activity الحالية بعد التوجيه

            }
        });

        return view;
    }
}