package com.example.al_rewaq;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;


public class reading_speed_test_subject_fragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reading_speed_test_subject_fragment, container, false);
        RelativeLayout relativeLayout = view.findViewById(R.id.rst_subject_id);
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