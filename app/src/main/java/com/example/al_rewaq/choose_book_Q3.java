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


public class choose_book_Q3 extends Fragment {

    Button click_answar3_1, click_answar3_2;

    TextView next_Q_btn, prev_Q_btn;
    private  int sum =0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_choose_book__q3, container, false);

        click_answar3_1 = view.findViewById(R.id.answar3_1);
        click_answar3_2 = view.findViewById(R.id.answar3_2);

        next_Q_btn = view.findViewById(R.id.next_Q);
        prev_Q_btn = view.findViewById(R.id.previous_Q);
        choose_book_Q4 chooseBookQ4 = new choose_book_Q4();


        click_answar3_1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                  sum =1;
                    click_answar3_1.setTextColor(Color.WHITE);
                    click_answar3_1.setBackgroundColor(Color.BLACK);
                    int color = ContextCompat.getColor(requireContext(), R.color.white2);

                    click_answar3_2.setTextColor(Color.BLACK);
                    click_answar3_2.setBackgroundColor(color);


            }
        });

        click_answar3_2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                sum =2;
                    click_answar3_2.setTextColor(Color.WHITE);
                    click_answar3_2.setBackgroundColor(Color.BLACK);
                    int color = ContextCompat.getColor(requireContext(), R.color.white2);

                    click_answar3_1.setTextColor(Color.BLACK);
                    click_answar3_1.setBackgroundColor(color);


            }
        });

        next_Q_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(sum == 1){
                    sum = 0;
                    getActivity().getSupportFragmentManager().beginTransaction().add(android.R.id.content,chooseBookQ4).commit();
                    removeFragment();
                } else if (sum == 2) {
                    sum = 0;
                    getActivity().getSupportFragmentManager().beginTransaction().add(android.R.id.content,chooseBookQ4).commit();
                    removeFragment();
                } else{
                    Toast.makeText(getContext(), "قم بتحديد إحدى الخيارات", Toast.LENGTH_LONG).show();
                }

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