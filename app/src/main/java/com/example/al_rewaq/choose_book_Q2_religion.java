package com.example.al_rewaq;

import android.graphics.Color;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class choose_book_Q2_religion extends Fragment {

    Button click_answar2_1_r, click_answar2_2_r ,click_answar2_3_r;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_choose_book__q2_religion, container, false);

        click_answar2_1_r = view.findViewById(R.id.answar2_1_r);
        click_answar2_2_r = view.findViewById(R.id.answar2_2_r);
        click_answar2_3_r = view.findViewById(R.id.answar2_3_r);

        click_answar2_1_r.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {



                    click_answar2_1_r.setTextColor(Color.WHITE);
                    click_answar2_1_r.setBackgroundColor(Color.BLACK);
                    int color = ContextCompat.getColor(requireContext(), R.color.white2);

                    click_answar2_2_r.setTextColor(Color.BLACK);
                    click_answar2_2_r.setBackgroundColor(color);

                    click_answar2_3_r.setTextColor(Color.BLACK);
                    click_answar2_3_r.setBackgroundColor(color);


            }
        });

        click_answar2_2_r.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {



                    click_answar2_2_r.setTextColor(Color.WHITE);
                    click_answar2_2_r.setBackgroundColor(Color.BLACK);
                    int color = ContextCompat.getColor(requireContext(), R.color.white2);

                    click_answar2_1_r.setTextColor(Color.BLACK);
                    click_answar2_1_r.setBackgroundColor(color);

                    click_answar2_3_r.setTextColor(Color.BLACK);
                    click_answar2_3_r.setBackgroundColor(color);


            }
        });

        click_answar2_3_r.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {



                    click_answar2_3_r.setTextColor(Color.WHITE);
                    click_answar2_3_r.setBackgroundColor(Color.BLACK);
                    int color = ContextCompat.getColor(requireContext(), R.color.white2);

                    click_answar2_1_r.setTextColor(Color.BLACK);
                    click_answar2_1_r.setBackgroundColor(color);

                    click_answar2_2_r.setTextColor(Color.BLACK);
                    click_answar2_2_r.setBackgroundColor(color);


            }
        });

        return view;
    }
}