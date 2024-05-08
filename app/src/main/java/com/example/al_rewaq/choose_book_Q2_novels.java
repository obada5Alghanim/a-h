package com.example.al_rewaq;

import android.graphics.Color;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class choose_book_Q2_novels extends Fragment {

    Button click_answar2_1_n, click_answar2_2_n ,click_answar2_3_n, click_answar2_4_n;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_chooes_book__q2_novels, container, false);

        click_answar2_1_n = view.findViewById(R.id.answar2_1_n);
        click_answar2_2_n = view.findViewById(R.id.answar2_2_n);
        click_answar2_3_n = view.findViewById(R.id.answar2_3_n);
        click_answar2_4_n = view.findViewById(R.id.answar2_4_n);

        click_answar2_1_n.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {



                    click_answar2_1_n.setTextColor(Color.WHITE);
                    click_answar2_1_n.setBackgroundColor(Color.BLACK);
                    int color = ContextCompat.getColor(requireContext(), R.color.white2);

                    click_answar2_2_n.setTextColor(Color.BLACK);
                    click_answar2_2_n.setBackgroundColor(color);

                    click_answar2_3_n.setTextColor(Color.BLACK);
                    click_answar2_3_n.setBackgroundColor(color);

                    click_answar2_4_n.setTextColor(Color.BLACK);
                    click_answar2_4_n.setBackgroundColor(color);


            }
        });

        click_answar2_2_n.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {



                    click_answar2_2_n.setTextColor(Color.WHITE);
                    click_answar2_2_n.setBackgroundColor(Color.BLACK);
                    int color = ContextCompat.getColor(requireContext(), R.color.white2);

                    click_answar2_1_n.setTextColor(Color.BLACK);
                    click_answar2_1_n.setBackgroundColor(color);

                    click_answar2_3_n.setTextColor(Color.BLACK);
                    click_answar2_3_n.setBackgroundColor(color);

                    click_answar2_4_n.setTextColor(Color.BLACK);
                    click_answar2_4_n.setBackgroundColor(color);


            }
        });

        click_answar2_3_n.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {



                    click_answar2_3_n.setTextColor(Color.WHITE);
                    click_answar2_3_n.setBackgroundColor(Color.BLACK);
                    int color = ContextCompat.getColor(requireContext(), R.color.white2);

                    click_answar2_1_n.setTextColor(Color.BLACK);
                    click_answar2_1_n.setBackgroundColor(color);

                    click_answar2_2_n.setTextColor(Color.BLACK);
                    click_answar2_2_n.setBackgroundColor(color);

                    click_answar2_4_n.setTextColor(Color.BLACK);
                    click_answar2_4_n.setBackgroundColor(color);


            }
        });

        click_answar2_4_n.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {



                    click_answar2_4_n.setTextColor(Color.WHITE);
                    click_answar2_4_n.setBackgroundColor(Color.BLACK);
                    int color = ContextCompat.getColor(requireContext(), R.color.white2);

                    click_answar2_1_n.setTextColor(Color.BLACK);
                    click_answar2_1_n.setBackgroundColor(color);

                    click_answar2_2_n.setTextColor(Color.BLACK);
                    click_answar2_2_n.setBackgroundColor(color);

                    click_answar2_3_n.setTextColor(Color.BLACK);
                    click_answar2_3_n.setBackgroundColor(color);


            }
        });

        return view;
    }
}