package com.example.al_rewaq;

import android.graphics.Color;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class choose_book_Q2_etiquette extends Fragment {

    Button click_answar2_1_e, click_answar2_2_e ,click_answar2_3_e;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_choose_book__q2_etiquette, container, false);

        click_answar2_1_e = view.findViewById(R.id.answar2_1_e);
        click_answar2_2_e = view.findViewById(R.id.answar2_2_e);
        click_answar2_3_e = view.findViewById(R.id.answar2_3_e);

        click_answar2_1_e.setOnClickListener(new View.OnClickListener() {
            boolean Clicked1 = false;
            @Override
            public void onClick(View v) {


                if (Clicked1 == false){
                    click_answar2_1_e.setTextColor(Color.WHITE);
                    click_answar2_1_e.setBackgroundColor(Color.BLACK);
                    int color = ContextCompat.getColor(requireContext(), R.color.white2);

                    click_answar2_2_e.setTextColor(Color.BLACK);
                    click_answar2_2_e.setBackgroundColor(color);

                    click_answar2_3_e.setTextColor(Color.BLACK);
                    click_answar2_3_e.setBackgroundColor(color);

                   }

            }
        });

        click_answar2_2_e.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                    click_answar2_2_e.setTextColor(Color.WHITE);
                    click_answar2_2_e.setBackgroundColor(Color.BLACK);
                    int color = ContextCompat.getColor(requireContext(), R.color.white2);

                    click_answar2_1_e.setTextColor(Color.BLACK);
                    click_answar2_1_e.setBackgroundColor(color);

                    click_answar2_3_e.setTextColor(Color.BLACK);
                    click_answar2_3_e.setBackgroundColor(color);



            }
        });

        click_answar2_3_e.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                    click_answar2_3_e.setTextColor(Color.WHITE);
                    click_answar2_3_e.setBackgroundColor(Color.BLACK);
                    int color = ContextCompat.getColor(requireContext(), R.color.white2);

                    click_answar2_1_e.setTextColor(Color.BLACK);
                    click_answar2_1_e.setBackgroundColor(color);

                    click_answar2_2_e.setTextColor(Color.BLACK);
                    click_answar2_2_e.setBackgroundColor(color);

                    }

        });

        return view;
    }
}