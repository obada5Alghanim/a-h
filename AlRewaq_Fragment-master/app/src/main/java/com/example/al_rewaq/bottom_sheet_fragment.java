package com.example.al_rewaq;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;


public class bottom_sheet_fragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottom_sheet_fragment, container, false);
        RelativeLayout relativeLayout = view.findViewById(R.id.realtiveLayoutForBottomSheet);
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