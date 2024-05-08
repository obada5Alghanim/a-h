package com.example.al_rewaq;

import android.graphics.Color;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class choose_book_Q2_history extends Fragment {

    Button click_answar2_1_h, click_answar2_2_h ,click_answar2_3_h;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_choose_book__q2_history, container, false);

        click_answar2_1_h = view.findViewById(R.id.answar2_1_h);
        click_answar2_2_h = view.findViewById(R.id.answar2_2_h);
        click_answar2_3_h = view.findViewById(R.id.answar2_3_h);

        click_answar2_1_h.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {



                    click_answar2_1_h.setTextColor(Color.WHITE);
                    click_answar2_1_h.setBackgroundColor(Color.BLACK);
                    int color = ContextCompat.getColor(requireContext(), R.color.white2);

                    click_answar2_2_h.setTextColor(Color.BLACK);
                    click_answar2_2_h.setBackgroundColor(color);

                    click_answar2_3_h.setTextColor(Color.BLACK);
                    click_answar2_3_h.setBackgroundColor(color);

                   }

        });

        click_answar2_2_h.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {



                    click_answar2_2_h.setTextColor(Color.WHITE);
                    click_answar2_2_h.setBackgroundColor(Color.BLACK);
                    int color = ContextCompat.getColor(requireContext(), R.color.white2);

                    click_answar2_1_h.setTextColor(Color.BLACK);
                    click_answar2_1_h.setBackgroundColor(color);

                    click_answar2_3_h.setTextColor(Color.BLACK);
                    click_answar2_3_h.setBackgroundColor(color);


            }
        });

        click_answar2_3_h.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {



                    click_answar2_3_h.setTextColor(Color.WHITE);
                    click_answar2_3_h.setBackgroundColor(Color.BLACK);
                    int color = ContextCompat.getColor(requireContext(), R.color.white2);

                    click_answar2_1_h.setTextColor(Color.BLACK);
                    click_answar2_1_h.setBackgroundColor(color);

                    click_answar2_2_h.setTextColor(Color.BLACK);
                    click_answar2_2_h.setBackgroundColor(color);

            }
        });

        return view;
    }
}