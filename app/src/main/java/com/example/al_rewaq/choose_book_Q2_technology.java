package com.example.al_rewaq;

import android.graphics.Color;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class choose_book_Q2_technology extends Fragment {

    Button click_answar2_1_t, click_answar2_2_t;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_choose_book__q2_technology, container, false);

        click_answar2_1_t = view.findViewById(R.id.answar2_1_t);
        click_answar2_2_t = view.findViewById(R.id.answar2_2_t);

        click_answar2_1_t.setOnClickListener(new View.OnClickListener() {
            boolean Clicked1 = false;
            @Override
            public void onClick(View v) {


                if (Clicked1 == false){
                    click_answar2_1_t.setTextColor(Color.WHITE);
                    click_answar2_1_t.setBackgroundColor(Color.BLACK);
                    int color = ContextCompat.getColor(requireContext(), R.color.white2);

                    click_answar2_2_t.setTextColor(Color.BLACK);
                    click_answar2_2_t.setBackgroundColor(color);

                    Clicked1 = true;}
                else if (Clicked1 == true) {
                    click_answar2_1_t.setTextColor(Color.BLACK);
                    int color = ContextCompat.getColor(requireContext(), R.color.white2);
                    click_answar2_1_t.setBackgroundColor(color);
                    Clicked1 = false;}
            }
        });

        click_answar2_2_t.setOnClickListener(new View.OnClickListener() {
            boolean Clicked2 = false;
            @Override
            public void onClick(View v) {


                if (Clicked2 == false){
                    click_answar2_2_t.setTextColor(Color.WHITE);
                    click_answar2_2_t.setBackgroundColor(Color.BLACK);
                    int color = ContextCompat.getColor(requireContext(), R.color.white2);

                    click_answar2_1_t.setTextColor(Color.BLACK);
                    click_answar2_1_t.setBackgroundColor(color);

                    Clicked2 = true;}
                else if (Clicked2 == true) {
                    click_answar2_2_t.setTextColor(Color.BLACK);
                    int color = ContextCompat.getColor(requireContext(), R.color.white2);
                    click_answar2_2_t.setBackgroundColor(color);
                    Clicked2 = false;}
            }
        });

        return view;
    }
}