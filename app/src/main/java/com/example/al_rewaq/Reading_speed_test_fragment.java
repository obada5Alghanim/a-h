package com.example.al_rewaq;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;


public class Reading_speed_test_fragment extends Fragment {


    private TextView textView2, TextColor1;

    private LinearLayout Linear1;

    private ImageView imageView1;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reading_speed_test_fragment , container, false);

        Button start = view.findViewById(R.id.start_btn);

        Button end = view.findViewById(R.id.end_btn);

        textView2 = view.findViewById(R.id.textView2);

        imageView1 = view.findViewById(R.id.imageView1);

        TextColor1 = view.findViewById(R.id.textView2);

        TextView textsubjects = view.findViewById(R.id.textView2);


        textView2.setOnClickListener(new View.OnClickListener() {
            int Clicked1 = 0;

            @Override
            public void onClick(View v) {
                if (Clicked1 == 0){
//                    Linear1.setVisibility(View.VISIBLE);
                    imageView1.setImageDrawable(requireContext().getDrawable(R.drawable.arrow_up));
                    Clicked1++;}
                else if (Clicked1 == 1) {
//                    Linear1.setVisibility(View.GONE);
                    imageView1.setImageDrawable(requireContext().getDrawable(R.drawable.arrow_down));
                    Clicked1--;}
            }
        });

        textsubjects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reading_speed_test_subject_fragment readingSpeedTestSubjectFragment = new reading_speed_test_subject_fragment();
                getActivity().getSupportFragmentManager().beginTransaction().add(android.R.id.content,readingSpeedTestSubjectFragment).commit();

            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                start.setVisibility(View.GONE);
                end.setVisibility(View.VISIBLE);


            }
        });

        end.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                end.setVisibility(View.GONE);
                start.setVisibility(View.VISIBLE);

                  RST_result_fragment rstResultFragment = new RST_result_fragment();
                  getActivity().getSupportFragmentManager().beginTransaction().add(android.R.id.content,rstResultFragment).commit();

            }
        });

        return view;
    }


}