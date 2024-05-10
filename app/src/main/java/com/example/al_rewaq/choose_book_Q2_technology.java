package com.example.al_rewaq;

import android.graphics.Color;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class choose_book_Q2_technology extends Fragment {

    Button click_answar2_1_t, click_answar2_2_t;

    TextView next_Q_btn, prev_Q_btn;
    private  int sum =0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_choose_book__q2_technology, container, false);

        click_answar2_1_t = view.findViewById(R.id.answar2_1_t);
        click_answar2_2_t = view.findViewById(R.id.answar2_2_t);

        next_Q_btn = view.findViewById(R.id.next_Q);
        prev_Q_btn = view.findViewById(R.id.previous_Q);
        choose_book_Q1 choose_book_q1 = new choose_book_Q1();
        choose_book_Q3 chooseBookQ3 = new choose_book_Q3();

        click_answar2_1_t.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                sum =1;

                    click_answar2_1_t.setTextColor(Color.WHITE);
                    click_answar2_1_t.setBackgroundColor(Color.BLACK);
                    int color = ContextCompat.getColor(requireContext(), R.color.white2);

                    click_answar2_2_t.setTextColor(Color.BLACK);
                    click_answar2_2_t.setBackgroundColor(color);


            }
        });

        click_answar2_2_t.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                sum =2;

                    click_answar2_2_t.setTextColor(Color.WHITE);
                    click_answar2_2_t.setBackgroundColor(Color.BLACK);
                    int color = ContextCompat.getColor(requireContext(), R.color.white2);

                    click_answar2_1_t.setTextColor(Color.BLACK);
                    click_answar2_1_t.setBackgroundColor(color);


            }
        });

        next_Q_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(sum == 1){
                    sum = 0;
                    getActivity().getSupportFragmentManager().beginTransaction().add(android.R.id.content,chooseBookQ3).commit();
                    removeFragment();
                } else if (sum == 2) {
                    sum = 0;
                    getActivity().getSupportFragmentManager().beginTransaction().add(android.R.id.content,chooseBookQ3).commit();
                    removeFragment();
                } else{
                    Toast.makeText(getContext(), "قم بتحديد إحدى الخيارات", Toast.LENGTH_LONG).show();
                }

            }
        });

        prev_Q_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                sum = 0 ;
                getActivity().getSupportFragmentManager().beginTransaction().add(android.R.id.content,choose_book_q1).commit();
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