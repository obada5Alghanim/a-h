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
        choose_book_Q2_technology chooseBookQ2Technology =new choose_book_Q2_technology();
        choose_book_Q2_religion chooseBookQ2Religion = new choose_book_Q2_religion();
        choose_book_Q2_history chooseBookQ2History = new choose_book_Q2_history();
        choose_book_Q2_psychology chooseBookQ2Psychology = new choose_book_Q2_psychology();
        choose_book_Q2_novels chooseBookQ2Novels = new choose_book_Q2_novels();
        choose_book_Q2_etiquette chooseBookQ2Etiquette =  new choose_book_Q2_etiquette();
        Bundle recevieFromSect = this.getArguments();
        String fromSect = recevieFromSect.getString("sect");
        String justCheck2 = recevieFromSect.getString("Q2check");

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

        Bundle langBun = new Bundle();
        next_Q_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(sum == 1){
                    sum = 0;
                    langBun.putString("lang",fromSect);
                    langBun.putString("langlang","Arabic");
                    chooseBookQ4.setArguments(langBun);
                    getActivity().getSupportFragmentManager().beginTransaction().add(android.R.id.content,chooseBookQ4).commit();
                    removeFragment();
                } else if (sum == 2) {
                    sum = 0;
                    langBun.putString("lang",fromSect);
                    langBun.putString("langlang","English");
                    chooseBookQ4.setArguments(langBun);
                    getActivity().getSupportFragmentManager().beginTransaction().add(android.R.id.content,chooseBookQ4).commit();
                    removeFragment();
                } else{
                    Toast.makeText(getContext(), "قم بتحديد إحدى الخيارات", Toast.LENGTH_LONG).show();
                }

            }
        });


        prev_Q_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (justCheck2 == "A"){
                    sum = 0;
                   clearArguments();
                    getActivity().getSupportFragmentManager().beginTransaction().add(android.R.id.content,chooseBookQ2Novels).commit();
                       removeFragment();
                } else if (justCheck2 == "B") {
                    sum = 0;
                    clearArguments();
                    getActivity().getSupportFragmentManager().beginTransaction().add(android.R.id.content,chooseBookQ2Etiquette).commit();
                       removeFragment();
                } else if (justCheck2 == "C") {
                    sum = 0;
                    clearArguments();
                    getActivity().getSupportFragmentManager().beginTransaction().add(android.R.id.content,chooseBookQ2Religion).commit();
                         removeFragment();
                } else if (justCheck2 == "D") {
                    sum = 0;
                    clearArguments();
                    getActivity().getSupportFragmentManager().beginTransaction().add(android.R.id.content,chooseBookQ2Psychology).commit();
                        removeFragment();
                } else if (justCheck2 == "E") {
                    sum = 0;
                    clearArguments();
                    getActivity().getSupportFragmentManager().beginTransaction().add(android.R.id.content,chooseBookQ2History).commit();
                       removeFragment();
                } else if (justCheck2 == "F") {
                    sum = 0;
                    clearArguments();
                    getActivity().getSupportFragmentManager().beginTransaction().replace(android.R.id.content,chooseBookQ2Technology).commit();

                }else {


                }
            }
        });


        return view;
    }

    private void clearArguments() {
        setArguments(new Bundle()); // Set an empty Bundle to clear arguments
        Toast.makeText(getContext(), "Arguments cleared", Toast.LENGTH_SHORT).show();
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