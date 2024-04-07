package com.example.al_rewaq;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Sign_In_fragment extends Fragment {

//TextView txt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_sign__in_fragment, container, false);
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