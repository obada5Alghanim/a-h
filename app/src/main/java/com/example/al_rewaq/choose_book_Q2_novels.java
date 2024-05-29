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



public class choose_book_Q2_novels extends Fragment {

    Button click_answar2_1_n, click_answar2_2_n ,click_answar2_3_n, click_answar2_4_n;
    TextView next_Q_btn;


    private  int sum =0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_chooes_book__q2_novels, container, false);

        click_answar2_1_n = view.findViewById(R.id.answar2_1_n);
        click_answar2_2_n = view.findViewById(R.id.answar2_2_n);
        click_answar2_3_n = view.findViewById(R.id.answar2_3_n);
        click_answar2_4_n = view.findViewById(R.id.answar2_4_n);
        next_Q_btn = view.findViewById(R.id.next_Q);

        choose_book_Q3 chooseBookQ3 = new choose_book_Q3();


        click_answar2_1_n.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                    sum =1;

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

                   sum =2;

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

                sum =3;

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

                sum =4;

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

Bundle sectBun = new Bundle();
        next_Q_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(sum == 1){
                    sum = 0;
                    sectBun.putString("sect","روايات مغامرات");
                    chooseBookQ3.setArguments(sectBun);
                    getActivity().getSupportFragmentManager().beginTransaction().add(android.R.id.content,chooseBookQ3).commit();
                    removeFragment();
                }
                else if (sum == 2) {
                    sum = 0;
                    sectBun.putString("sect","روايات دينية");
                    chooseBookQ3.setArguments(sectBun);
                    getActivity().getSupportFragmentManager().beginTransaction().add(android.R.id.content,chooseBookQ3).commit();
                    removeFragment();
                }
                else if (sum == 3) {
                    sum = 0;
                    sectBun.putString("sect","روايات بوليسية");
                    chooseBookQ3.setArguments(sectBun);
                    getActivity().getSupportFragmentManager().beginTransaction().add(android.R.id.content,chooseBookQ3).commit();
                    removeFragment();

                }
                else if (sum == 4) {
                    sum = 0;
                    sectBun.putString("sect","روايات رعب");
                    chooseBookQ3.setArguments(sectBun);
                    getActivity().getSupportFragmentManager().beginTransaction().add(android.R.id.content,chooseBookQ3).commit();
                    removeFragment();
                }
                else{
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
