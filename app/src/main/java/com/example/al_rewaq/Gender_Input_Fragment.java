package com.example.al_rewaq;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Gender_Input_Fragment extends Fragment {

  TextView maleTextView , femaleTextView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_gender__input_, container, false);
        RelativeLayout relativeLayout = view.findViewById(R.id.genderFragment);
        maleTextView   = view.findViewById(R.id.maleId);
        femaleTextView = view.findViewById(R.id.femaleId);


        maleTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textToPass = maleTextView.getText().toString();
                Bundle bundle = new Bundle();
                bundle.putString("male1", textToPass);
                Sign_Up_fragment signUpFragment = new Sign_Up_fragment();
                signUpFragment.setArguments(bundle);
                removeFragment();



            }
        });


        femaleTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textToPass = maleTextView.getText().toString();
                Bundle bundle = new Bundle();
                bundle.putString("female2", textToPass);
                Sign_Up_fragment signUpFragment = new Sign_Up_fragment();
                signUpFragment.setArguments(bundle);
                removeFragment();
            }
        });


        relativeLayout.setOnClickListener(new View.OnClickListener() {
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



/* ImageButton imageButton = view.findViewById(R.id.imageButton3);
        RelativeLayout relativeLayout = view.findViewById(R.id.realtiveLayoutForRST_result);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeFragment();
            }
        });

        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeFragment();
            }
        });
*/