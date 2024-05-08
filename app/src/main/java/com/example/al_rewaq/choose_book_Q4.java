package com.example.al_rewaq;

import android.graphics.Color;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class choose_book_Q4 extends Fragment {

    Button click_answar4_1, click_answar4_2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_choose_book__q4, container, false);

        click_answar4_1 = view.findViewById(R.id.answar4_1);
        click_answar4_2 = view.findViewById(R.id.answar4_2);

        click_answar4_1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {



                    click_answar4_1.setTextColor(Color.WHITE);
                    click_answar4_1.setBackgroundColor(Color.BLACK);
                    int color = ContextCompat.getColor(requireContext(), R.color.white2);

                    click_answar4_2.setTextColor(Color.BLACK);
                    click_answar4_2.setBackgroundColor(color);


            }
        });

        click_answar4_2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {



                    click_answar4_2.setTextColor(Color.WHITE);
                    click_answar4_2.setBackgroundColor(Color.BLACK);
                    int color = ContextCompat.getColor(requireContext(), R.color.white2);

                    click_answar4_1.setTextColor(Color.BLACK);
                    click_answar4_1.setBackgroundColor(color);


            }
        });

        return view;
    }
}