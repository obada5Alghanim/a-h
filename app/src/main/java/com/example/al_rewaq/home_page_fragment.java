package com.example.al_rewaq;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class home_page_fragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_page_fragment, container, false);
        Button btn1 = view.findViewById(R.id.example);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book_Title_fragment bookTitleFragment = new Book_Title_fragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(android.R.id.content,bookTitleFragment).commit();
            }
        });

        return view;

    }
}