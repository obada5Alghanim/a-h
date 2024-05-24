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


public class choose_book_Q1 extends Fragment {

     Button click_answar1_1, click_answar1_2 ,click_answar1_3, click_answar1_4, click_answar1_5, click_answar1_6;
     TextView next_Q_btn;
    private   int sum = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_choose_book__q1, container, false);

        click_answar1_1 = view.findViewById(R.id.answar1_1);
        click_answar1_2 = view.findViewById(R.id.answar1_2);
        click_answar1_3 = view.findViewById(R.id.answar1_3);
        click_answar1_4 = view.findViewById(R.id.answar1_4);
        click_answar1_5 = view.findViewById(R.id.answar1_5);
        click_answar1_6 = view.findViewById(R.id.answar1_6);
        next_Q_btn = view.findViewById(R.id.next_Q);


        choose_book_Q2_novels chooseBookQ2Novels = new choose_book_Q2_novels();
        choose_book_Q2_etiquette chooseBookQ2Etiquette = new choose_book_Q2_etiquette();
        choose_book_Q2_history chooseBookQ2History = new choose_book_Q2_history();
        choose_book_Q2_psychology chooseBookQ2Psychology = new choose_book_Q2_psychology();
        choose_book_Q2_religion chooseBookQ2Religion = new choose_book_Q2_religion();
        choose_book_Q2_technology chooseBookQ2Technology = new choose_book_Q2_technology();
        click_answar1_1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

               sum  = 1;


                    click_answar1_1.setTextColor(Color.WHITE);
                    click_answar1_1.setBackgroundColor(Color.BLACK);
                    int color = ContextCompat.getColor(requireContext(), R.color.white2);

                    click_answar1_2.setTextColor(Color.BLACK);
                    click_answar1_2.setBackgroundColor(color);

                    click_answar1_3.setTextColor(Color.BLACK);
                    click_answar1_3.setBackgroundColor(color);

                    click_answar1_4.setTextColor(Color.BLACK);
                    click_answar1_4.setBackgroundColor(color);

                    click_answar1_5.setTextColor(Color.BLACK);
                    click_answar1_5.setBackgroundColor(color);

                    click_answar1_6.setTextColor(Color.BLACK);
                    click_answar1_6.setBackgroundColor(color);





            }
        });



        click_answar1_2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                sum  = 2;
                    click_answar1_2.setTextColor(Color.WHITE);
                    click_answar1_2.setBackgroundColor(Color.BLACK);
                    int color = ContextCompat.getColor(requireContext(), R.color.white2);

                    click_answar1_1.setTextColor(Color.BLACK);
                    click_answar1_1.setBackgroundColor(color);

                    click_answar1_3.setTextColor(Color.BLACK);
                    click_answar1_3.setBackgroundColor(color);

                    click_answar1_4.setTextColor(Color.BLACK);
                    click_answar1_4.setBackgroundColor(color);

                    click_answar1_5.setTextColor(Color.BLACK);
                    click_answar1_5.setBackgroundColor(color);

                    click_answar1_6.setTextColor(Color.BLACK);
                    click_answar1_6.setBackgroundColor(color);


            }
        });

        click_answar1_3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                   sum = 3;
                    click_answar1_3.setTextColor(Color.WHITE);
                    click_answar1_3.setBackgroundColor(Color.BLACK);

                    int color = ContextCompat.getColor(requireContext(), R.color.white2);

                    click_answar1_1.setTextColor(Color.BLACK);
                    click_answar1_1.setBackgroundColor(color);

                    click_answar1_2.setTextColor(Color.BLACK);
                    click_answar1_2.setBackgroundColor(color);

                    click_answar1_4.setTextColor(Color.BLACK);
                    click_answar1_4.setBackgroundColor(color);

                    click_answar1_5.setTextColor(Color.BLACK);
                    click_answar1_5.setBackgroundColor(color);

                    click_answar1_6.setTextColor(Color.BLACK);
                    click_answar1_6.setBackgroundColor(color);



            }
        });

        click_answar1_4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                sum = 4;
                    click_answar1_4.setTextColor(Color.WHITE);
                    click_answar1_4.setBackgroundColor(Color.BLACK);

                    int color = ContextCompat.getColor(requireContext(), R.color.white2);

                    click_answar1_1.setTextColor(Color.BLACK);
                    click_answar1_1.setBackgroundColor(color);

                    click_answar1_2.setTextColor(Color.BLACK);
                    click_answar1_2.setBackgroundColor(color);

                    click_answar1_3.setTextColor(Color.BLACK);
                    click_answar1_3.setBackgroundColor(color);

                    click_answar1_5.setTextColor(Color.BLACK);
                    click_answar1_5.setBackgroundColor(color);

                    click_answar1_6.setTextColor(Color.BLACK);
                    click_answar1_6.setBackgroundColor(color);



            }
        });

        click_answar1_5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                     sum =5;
                    click_answar1_5.setTextColor(Color.WHITE);
                    click_answar1_5.setBackgroundColor(Color.BLACK);

                    int color = ContextCompat.getColor(requireContext(), R.color.white2);

                    click_answar1_1.setTextColor(Color.BLACK);
                    click_answar1_1.setBackgroundColor(color);

                    click_answar1_2.setTextColor(Color.BLACK);
                    click_answar1_2.setBackgroundColor(color);

                    click_answar1_3.setTextColor(Color.BLACK);
                    click_answar1_3.setBackgroundColor(color);

                    click_answar1_4.setTextColor(Color.BLACK);
                    click_answar1_4.setBackgroundColor(color);

                    click_answar1_6.setTextColor(Color.BLACK);
                    click_answar1_6.setBackgroundColor(color);



            }
        });

        click_answar1_6.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                    sum =6;
                    click_answar1_6.setTextColor(Color.WHITE);
                    click_answar1_6.setBackgroundColor(Color.BLACK);

                    int color = ContextCompat.getColor(requireContext(), R.color.white2);

                    click_answar1_1.setTextColor(Color.BLACK);
                    click_answar1_1.setBackgroundColor(color);

                    click_answar1_2.setTextColor(Color.BLACK);
                    click_answar1_2.setBackgroundColor(color);

                    click_answar1_3.setTextColor(Color.BLACK);
                    click_answar1_3.setBackgroundColor(color);

                    click_answar1_4.setTextColor(Color.BLACK);
                    click_answar1_4.setBackgroundColor(color);

                    click_answar1_5.setTextColor(Color.BLACK);
                    click_answar1_5.setBackgroundColor(color);



            }
        });

        Bundle justCheck = new Bundle();
    next_Q_btn.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if(sum == 1){
                sum = 0;
                justCheck.putString("Q1check","A");
                chooseBookQ2Novels.setArguments(justCheck);
               getActivity().getSupportFragmentManager().beginTransaction().add(android.R.id.content,chooseBookQ2Novels).commit();
               removeFragment();
            } else if (sum == 2) {
                sum = 0;
                justCheck.putString("Q1check","B");
                chooseBookQ2Etiquette.setArguments(justCheck);
                getActivity().getSupportFragmentManager().beginTransaction().add(android.R.id.content,chooseBookQ2Etiquette).commit();
                removeFragment();
            } else if (sum == 3) {
                sum = 0;
                justCheck.putString("Q1check","C");
                chooseBookQ2Religion.setArguments(justCheck);
                getActivity().getSupportFragmentManager().beginTransaction().add(android.R.id.content,chooseBookQ2Religion).commit();
                removeFragment();

            }else if (sum == 4) {
                sum = 0;
                justCheck.putString("Q1check","D");
                chooseBookQ2Psychology.setArguments(justCheck);
                getActivity().getSupportFragmentManager().beginTransaction().add(android.R.id.content,chooseBookQ2Psychology).commit();
                removeFragment();
            }else if (sum == 5) {
                sum = 0;
                justCheck.putString("Q1check","E");
                chooseBookQ2History.setArguments(justCheck);
                getActivity().getSupportFragmentManager().beginTransaction().add(android.R.id.content,chooseBookQ2History).commit();
                removeFragment();
            }else if (sum == 6) {
                sum = 0;
                justCheck.putString("Q1check","F");
                chooseBookQ2Technology.setArguments(justCheck);
                getActivity().getSupportFragmentManager().beginTransaction().add(android.R.id.content,chooseBookQ2Technology).commit();
                removeFragment();
            }else{
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



/*
   click_answar1_1.setOnClickListener(new View.OnClickListener() {
           boolean Clicked1 = false;
@Override
public void onClick(View v) {


        if (Clicked1 == false){
        click_answar1_1.setTextColor(Color.WHITE);
        click_answar1_1.setBackgroundColor(Color.BLACK);
        int color = ContextCompat.getColor(requireContext(), R.color.white2);

        click_answar1_2.setTextColor(Color.BLACK);
        click_answar1_2.setBackgroundColor(color);

        click_answar1_3.setTextColor(Color.BLACK);
        click_answar1_3.setBackgroundColor(color);

        click_answar1_4.setTextColor(Color.BLACK);
        click_answar1_4.setBackgroundColor(color);

        click_answar1_5.setTextColor(Color.BLACK);
        click_answar1_5.setBackgroundColor(color);

        click_answar1_6.setTextColor(Color.BLACK);
        click_answar1_6.setBackgroundColor(color);

        Clicked1 = true;}
        else if (Clicked1 == true) {
        click_answar1_1.setTextColor(Color.BLACK);
        int color = ContextCompat.getColor(requireContext(), R.color.white2);
        click_answar1_1.setBackgroundColor(color);
        Clicked1 = false;}
        }
        });



        click_answar1_2.setOnClickListener(new View.OnClickListener() {
        boolean Clicked2 = false;
@Override
public void onClick(View v) {
        if (Clicked2 == false){
        click_answar1_2.setTextColor(Color.WHITE);
        click_answar1_2.setBackgroundColor(Color.BLACK);
        int color = ContextCompat.getColor(requireContext(), R.color.white2);

        click_answar1_1.setTextColor(Color.BLACK);
        click_answar1_1.setBackgroundColor(color);

        click_answar1_3.setTextColor(Color.BLACK);
        click_answar1_3.setBackgroundColor(color);

        click_answar1_4.setTextColor(Color.BLACK);
        click_answar1_4.setBackgroundColor(color);

        click_answar1_5.setTextColor(Color.BLACK);
        click_answar1_5.setBackgroundColor(color);

        click_answar1_6.setTextColor(Color.BLACK);
        click_answar1_6.setBackgroundColor(color);
        Clicked2 = true;}
        else if (Clicked2 == true) {
        click_answar1_2.setTextColor(Color.BLACK);
        int color = ContextCompat.getColor(requireContext(), R.color.white2);
        click_answar1_2.setBackgroundColor(color);
        Clicked2 = false;}
        }
        });

        click_answar1_3.setOnClickListener(new View.OnClickListener() {
        boolean Clicked3 = false;
@Override
public void onClick(View v) {
        if (Clicked3 == false){
        click_answar1_3.setTextColor(Color.WHITE);
        click_answar1_3.setBackgroundColor(Color.BLACK);

        int color = ContextCompat.getColor(requireContext(), R.color.white2);

        click_answar1_1.setTextColor(Color.BLACK);
        click_answar1_1.setBackgroundColor(color);

        click_answar1_2.setTextColor(Color.BLACK);
        click_answar1_2.setBackgroundColor(color);

        click_answar1_4.setTextColor(Color.BLACK);
        click_answar1_4.setBackgroundColor(color);

        click_answar1_5.setTextColor(Color.BLACK);
        click_answar1_5.setBackgroundColor(color);

        click_answar1_6.setTextColor(Color.BLACK);
        click_answar1_6.setBackgroundColor(color);

        Clicked3 = true;}
        else if (Clicked3 == true) {
        click_answar1_3.setTextColor(Color.BLACK);
        int color = ContextCompat.getColor(requireContext(), R.color.white2);
        click_answar1_3.setBackgroundColor(color);
        Clicked3 = false;}
        }
        });

        click_answar1_4.setOnClickListener(new View.OnClickListener() {
        boolean Clicked4 = false;
@Override
public void onClick(View v) {
        if (Clicked4 == false){
        click_answar1_4.setTextColor(Color.WHITE);
        click_answar1_4.setBackgroundColor(Color.BLACK);

        int color = ContextCompat.getColor(requireContext(), R.color.white2);

        click_answar1_1.setTextColor(Color.BLACK);
        click_answar1_1.setBackgroundColor(color);

        click_answar1_2.setTextColor(Color.BLACK);
        click_answar1_2.setBackgroundColor(color);

        click_answar1_3.setTextColor(Color.BLACK);
        click_answar1_3.setBackgroundColor(color);

        click_answar1_5.setTextColor(Color.BLACK);
        click_answar1_5.setBackgroundColor(color);

        click_answar1_6.setTextColor(Color.BLACK);
        click_answar1_6.setBackgroundColor(color);

        Clicked4 = true;}
        else if (Clicked4 == true) {
        click_answar1_4.setTextColor(Color.BLACK);
        int color = ContextCompat.getColor(requireContext(), R.color.white2);
        click_answar1_4.setBackgroundColor(color);
        Clicked4 = false;}
        }
        });

        click_answar1_5.setOnClickListener(new View.OnClickListener() {
        boolean Clicked5 = false;
@Override
public void onClick(View v) {
        if (Clicked5 == false){
        click_answar1_5.setTextColor(Color.WHITE);
        click_answar1_5.setBackgroundColor(Color.BLACK);

        int color = ContextCompat.getColor(requireContext(), R.color.white2);

        click_answar1_1.setTextColor(Color.BLACK);
        click_answar1_1.setBackgroundColor(color);

        click_answar1_2.setTextColor(Color.BLACK);
        click_answar1_2.setBackgroundColor(color);

        click_answar1_3.setTextColor(Color.BLACK);
        click_answar1_3.setBackgroundColor(color);

        click_answar1_4.setTextColor(Color.BLACK);
        click_answar1_4.setBackgroundColor(color);

        click_answar1_6.setTextColor(Color.BLACK);
        click_answar1_6.setBackgroundColor(color);

        Clicked5 = true;}
        else if (Clicked5 == true) {
        click_answar1_5.setTextColor(Color.BLACK);
        int color = ContextCompat.getColor(requireContext(), R.color.white2);
        click_answar1_5.setBackgroundColor(color);
        Clicked5 = false;}
        }
        });

        click_answar1_6.setOnClickListener(new View.OnClickListener() {
        boolean Clicked6 = false;
@Override
public void onClick(View v) {
        if (Clicked6 == false){
        click_answar1_6.setTextColor(Color.WHITE);
        click_answar1_6.setBackgroundColor(Color.BLACK);

        int color = ContextCompat.getColor(requireContext(), R.color.white2);

        click_answar1_1.setTextColor(Color.BLACK);
        click_answar1_1.setBackgroundColor(color);

        click_answar1_2.setTextColor(Color.BLACK);
        click_answar1_2.setBackgroundColor(color);

        click_answar1_3.setTextColor(Color.BLACK);
        click_answar1_3.setBackgroundColor(color);

        click_answar1_4.setTextColor(Color.BLACK);
        click_answar1_4.setBackgroundColor(color);

        click_answar1_5.setTextColor(Color.BLACK);
        click_answar1_5.setBackgroundColor(color);

        Clicked6 = true;}
        else if (Clicked6 == true) {
        click_answar1_6.setTextColor(Color.BLACK);
        int color = ContextCompat.getColor(requireContext(), R.color.white2);
        click_answar1_6.setBackgroundColor(color);
        Clicked6 = false;}
        }
        });

*/