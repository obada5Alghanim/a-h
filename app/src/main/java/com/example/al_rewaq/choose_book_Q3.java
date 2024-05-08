package com.example.al_rewaq;

import android.graphics.Color;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class choose_book_Q3 extends Fragment {

    Button click_answar3_1, click_answar3_2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_choose_book__q3, container, false);

        click_answar3_1 = view.findViewById(R.id.answar3_1);
        click_answar3_2 = view.findViewById(R.id.answar3_2);

        click_answar3_1.setOnClickListener(new View.OnClickListener() {
            boolean Clicked1 = false;
            @Override
            public void onClick(View v) {


                if (Clicked1 == false){
                    click_answar3_1.setTextColor(Color.WHITE);
                    click_answar3_1.setBackgroundColor(Color.BLACK);
                    int color = ContextCompat.getColor(requireContext(), R.color.white2);

                    click_answar3_2.setTextColor(Color.BLACK);
                    click_answar3_2.setBackgroundColor(color);

                    Clicked1 = true;}
                else if (Clicked1 == true) {
                    click_answar3_1.setTextColor(Color.BLACK);
                    int color = ContextCompat.getColor(requireContext(), R.color.white2);
                    click_answar3_1.setBackgroundColor(color);
                    Clicked1 = false;}
            }
        });

        click_answar3_2.setOnClickListener(new View.OnClickListener() {
            boolean Clicked2 = false;
            @Override
            public void onClick(View v) {


                if (Clicked2 == false){
                    click_answar3_2.setTextColor(Color.WHITE);
                    click_answar3_2.setBackgroundColor(Color.BLACK);
                    int color = ContextCompat.getColor(requireContext(), R.color.white2);

                    click_answar3_1.setTextColor(Color.BLACK);
                    click_answar3_1.setBackgroundColor(color);

                    Clicked2 = true;}
                else if (Clicked2 == true) {
                    click_answar3_2.setTextColor(Color.BLACK);
                    int color = ContextCompat.getColor(requireContext(), R.color.white2);
                    click_answar3_2.setBackgroundColor(color);
                    Clicked2 = false;}
            }
        });

        return view;
    }
}