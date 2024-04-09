package com.example.al_rewaq;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class Categories_fragment extends Fragment {
    private TextView Show_Cat1,Show_Cat2,Show_Cat3,TextColor1,TextColor2,TextColor3;
    private LinearLayout Show_Button1,Show_Button2,Show_Button3;
    private ImageView ImageAr1,ImageAr3,ImageAr5,ImageBk2,ImageBk4,ImageBk6;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_categories_fragment, container, false);

        Show_Cat1 = view.findViewById(R.id.Cat1);
        Show_Cat2 = view.findViewById(R.id.Cat2);
        Show_Cat3 = view.findViewById(R.id.Cat3);

        Show_Button1 = view.findViewById(R.id.Click_bt1);
        Show_Button2 = view.findViewById(R.id.Click_bt2);
        Show_Button3 = view.findViewById(R.id.Click_bt3);

        TextColor1 = view.findViewById(R.id.Cat1);
        TextColor2 = view.findViewById(R.id.Cat2);
        TextColor3 = view.findViewById(R.id.Cat3);

        ImageAr1 = view.findViewById(R.id.imageView1);
        ImageAr3 = view.findViewById(R.id.imageView3);
        ImageAr5 = view.findViewById(R.id.imageView5);

        ImageBk2 = view.findViewById(R.id.imageView2);
        ImageBk4 = view.findViewById(R.id.imageView4);
        ImageBk6 = view.findViewById(R.id.imageView6);

        Show_Cat1.setOnClickListener(new View.OnClickListener() {
            int Clicked1 = 0;

            @Override
            public void onClick(View v) {
                if (Clicked1 == 0){
                    Show_Button1.setVisibility(View.VISIBLE);
                    TextColor1.setTextColor(Color.CYAN);
                    ImageAr1.setImageDrawable(requireContext().getDrawable(R.drawable.arrow_up));
                    ImageBk2.setColorFilter(Color.CYAN);
                    Clicked1++;}
                else if (Clicked1 == 1) {
                    Show_Button1.setVisibility(View.GONE);
                    TextColor1.setTextColor(Color.WHITE);
                    ImageAr1.setImageDrawable(requireContext().getDrawable(R.drawable.arrow_down));
                    ImageBk2.setColorFilter(Color.WHITE);
                    Clicked1--;}
            }
        });

        Show_Cat2.setOnClickListener(new View.OnClickListener() {
            int Clicked2 = 0;

            @Override
            public void onClick(View v) {
                if (Clicked2 == 0){
                    Show_Button2.setVisibility(View.VISIBLE);
                    TextColor2.setTextColor(Color.CYAN);
                    ImageAr3.setImageDrawable(requireContext().getDrawable(R.drawable.arrow_up));
                    ImageBk4.setColorFilter(Color.CYAN);
                    Clicked2++;}
                else if (Clicked2 == 1) {
                    Show_Button2.setVisibility(View.GONE);
                    TextColor2.setTextColor(Color.WHITE);
                    ImageAr3.setImageDrawable(requireContext().getDrawable(R.drawable.arrow_down));
                    ImageBk4.setColorFilter(Color.WHITE);
                    Clicked2--;}
            }
        });

        Show_Cat3.setOnClickListener(new View.OnClickListener() {
            int Clicked3 = 0;

            @Override
            public void onClick(View v) {
                if (Clicked3 == 0){
                    Show_Button3.setVisibility(View.VISIBLE);
                    TextColor3.setTextColor(Color.CYAN);
                    ImageAr5.setImageDrawable(requireContext().getDrawable(R.drawable.arrow_up));
                    ImageBk6.setColorFilter(Color.CYAN);
                    Clicked3++;}
                else if (Clicked3 == 1) {
                    Show_Button3.setVisibility(View.GONE);
                    TextColor3.setTextColor(Color.WHITE);
                    ImageAr5.setImageDrawable(requireContext().getDrawable(R.drawable.arrow_down));
                    ImageBk6.setColorFilter(Color.WHITE);
                    Clicked3--;}
            }
        });

        return view;
    }
}