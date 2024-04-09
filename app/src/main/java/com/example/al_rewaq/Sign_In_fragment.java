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

public class Sign_In_fragment extends Fragment {

TextView txt;
Button btn;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_sign__in_fragment, container, false);

        // Find the view by its ID
        txt = rootView.findViewById(R.id.singIn_to_singUp_click);
        btn = rootView.findViewById(R.id.signIn_button);
        // Set click listener or perform any other operation on the view
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Sign_Up_fragment SUSe = new Sign_Up_fragment();
                ft.replace(android.R.id.content,SUSe);
                ft.commit();
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),menu_main.class);
                startActivity(intent);
            }
        });




        return rootView;




    }
}







 /*        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_sing__in_fragment, container, false);

        EditText passwordEditText = rootView.findViewById(R.id.Edt_password_singIn);
        ImageView eyeImageView = rootView.findViewById(R.id.eyeImageView);

        // Set OnClickListener on eye icon ImageView
        eyeImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toggle password visibility
                if (passwordEditText.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                    // If password is visible, hide it
                    passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    eyeImageView.setImageResource(R.drawable.ic_eye_closed);
                } else {
                    // If password is hidden, show it
                    passwordEditText.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    eyeImageView.setImageResource(R.drawable.ic_eye_open);
                }

                // Move cursor to the end of the input to maintain cursor position
                passwordEditText.setSelection(passwordEditText.getText().length());
            }
        });

        return rootView;*/