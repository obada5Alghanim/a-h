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


public class choose_book_Q2_religion extends Fragment {

    Button click_answar2_1_r, click_answar2_2_r ,click_answar2_3_r;
    TextView next_Q_btn;
    private  int sum =0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_choose_book__q2_religion, container, false);

        click_answar2_1_r = view.findViewById(R.id.answar2_1_r);
        click_answar2_2_r = view.findViewById(R.id.answar2_2_r);
        click_answar2_3_r = view.findViewById(R.id.answar2_3_r);
        next_Q_btn = view.findViewById(R.id.next_Q);

        choose_book_Q3 chooseBookQ3 = new choose_book_Q3();


        click_answar2_1_r.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                sum =1;

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

                sum =2;

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

                sum =3;

                    click_answar2_3_r.setTextColor(Color.WHITE);
                    click_answar2_3_r.setBackgroundColor(Color.BLACK);
                    int color = ContextCompat.getColor(requireContext(), R.color.white2);

                    click_answar2_1_r.setTextColor(Color.BLACK);
                    click_answar2_1_r.setBackgroundColor(color);

                    click_answar2_2_r.setTextColor(Color.BLACK);
                    click_answar2_2_r.setBackgroundColor(color);


            }
        });

        Bundle sectBun = new Bundle();
        next_Q_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(sum == 1){
                    sum = 0;
                    sectBun.putString("sect","العلوم الشرعية");
                    chooseBookQ3.setArguments(sectBun);
                    getActivity().getSupportFragmentManager().beginTransaction().add(android.R.id.content,chooseBookQ3).commit();
                    removeFragment();
                } else if (sum == 2) {
                    sum = 0;
                    sectBun.putString("sect","السيرة النبوية");
                    chooseBookQ3.setArguments(sectBun);
                    getActivity().getSupportFragmentManager().beginTransaction().add(android.R.id.content,chooseBookQ3).commit();
                    removeFragment();
                } else if (sum == 3) {
                    sum = 0;
                    sectBun.putString("sect","الرسل والأنبياء");
                    chooseBookQ3.setArguments(sectBun);
                    getActivity().getSupportFragmentManager().beginTransaction().add(android.R.id.content,chooseBookQ3).commit();
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