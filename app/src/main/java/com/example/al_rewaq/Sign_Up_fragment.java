package com.example.al_rewaq;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class Sign_Up_fragment extends Fragment {
 TextView txt;
 Button btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_sign__up_fragment, container, false);

        // Find the view by its ID
        txt = rootView.findViewById(R.id.signUP_to_sing_click);
        btn = rootView.findViewById(R.id.signUp_button);
        // Set click listener or perform any other operation on the view
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Sign_In_fragment SUF = new Sign_In_fragment();
                ft.replace(android.R.id.content,SUF);
                ft.commit();
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Interests.class);
                startActivity(intent);
            }
        });


        return rootView;

    }
}