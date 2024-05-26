package com.example.al_rewaq;

import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class Categories_fragment extends Fragment {

 Button BTN1,BTN2,BTN3,BTN4,BTN5,BTN6,BTN7,BTN8,BTN9,BTN10,BTN11,BTN12,BTN13,BTN14,BTN15,BTN16,BTN17;

    private TextView Show_Cat1,Show_Cat2,Show_Cat3,Show_Cat4,Show_Cat5,Show_Cat6,
            TextColor1,TextColor2,TextColor3,TextColor4,TextColor5,TextColor6;

    private LinearLayout Show_Button1,Show_Button1_2,Show_Button2,Show_Button3,Show_Button4,Show_Button5,Show_Button6;

    private ImageView ImageAr1,ImageAr3,ImageAr5,ImageAr7,ImageAr9,ImageAr11,
            ImageBk2,ImageBk4,ImageBk6,ImageBk8,ImageBk10,ImageBk12;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_categories_fragment, container, false);


        BTN1=view.findViewById(R.id.button1);
        BTN2=view.findViewById(R.id.button2);
        BTN3=view.findViewById(R.id.button3);
        BTN4=view.findViewById(R.id.button10);
        BTN5=view.findViewById(R.id.button4);
        BTN6=view.findViewById(R.id.button5);
        BTN7=view.findViewById(R.id.button6);
        BTN8=view.findViewById(R.id.button7);
        BTN9=view.findViewById(R.id.button8);
        BTN10=view.findViewById(R.id.button9);
        BTN11=view.findViewById(R.id.button11);
        BTN12=view.findViewById(R.id.button12);
        BTN13=view.findViewById(R.id.button13);
        BTN14=view.findViewById(R.id.button14);
        BTN15=view.findViewById(R.id.button15);
        BTN16=view.findViewById(R.id.button16);
        BTN17=view.findViewById(R.id.button17);

        Show_Cat1 = view.findViewById(R.id.Cat1);
        Show_Cat2 = view.findViewById(R.id.Cat2);
        Show_Cat3 = view.findViewById(R.id.Cat3);
        Show_Cat4 = view.findViewById(R.id.Cat4);
        Show_Cat5 = view.findViewById(R.id.Cat5);
        Show_Cat6 = view.findViewById(R.id.Cat6);

        Show_Button1 = view.findViewById(R.id.Click_bt1);
        Show_Button2 = view.findViewById(R.id.Click_bt2);
        Show_Button1_2 = view.findViewById(R.id.Click_bt1_2);
        Show_Button3 = view.findViewById(R.id.Click_bt3);
        Show_Button4 = view.findViewById(R.id.Click_bt4);
        Show_Button5 = view.findViewById(R.id.Click_bt5);
        Show_Button6 = view.findViewById(R.id.Click_bt6);

        TextColor1 = view.findViewById(R.id.Cat1);
        TextColor2 = view.findViewById(R.id.Cat2);
        TextColor3 = view.findViewById(R.id.Cat3);
        TextColor4 = view.findViewById(R.id.Cat4);
        TextColor5 = view.findViewById(R.id.Cat5);
        TextColor6 = view.findViewById(R.id.Cat6);

        ImageAr1 = view.findViewById(R.id.imageView1);
        ImageAr3 = view.findViewById(R.id.imageView3);
        ImageAr5 = view.findViewById(R.id.imageView5);
        ImageAr7 = view.findViewById(R.id.imageView7);
        ImageAr9 = view.findViewById(R.id.imageView9);
        ImageAr11 = view.findViewById(R.id.imageView11);

        ImageBk2 = view.findViewById(R.id.imageView2);
        ImageBk4 = view.findViewById(R.id.imageView4);
        ImageBk6 = view.findViewById(R.id.imageView6);
        ImageBk8 = view.findViewById(R.id.imageView8);
        ImageBk10 = view.findViewById(R.id.imageView10);
        ImageBk12 = view.findViewById(R.id.imageView12);



        Show_Cat1.setOnClickListener(new View.OnClickListener() {
            int Clicked1 = 0;

            @Override
            public void onClick(View v) {
                if (Clicked1 == 0){
                    Show_Button1.setVisibility(View.VISIBLE);
                    Show_Button1_2.setVisibility(View.VISIBLE);
                    TextColor1.setTextColor(Color.CYAN);
                    ImageAr1.setImageDrawable(requireContext().getDrawable(R.drawable.arrow_up));
                    ImageBk2.setColorFilter(Color.CYAN);
                    Clicked1++;}
                else if (Clicked1 == 1) {
                    Show_Button1.setVisibility(View.GONE);
                    Show_Button1_2.setVisibility(View.GONE);
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

        Show_Cat4.setOnClickListener(new View.OnClickListener() {
            int Clicked4 = 0;

            @Override
            public void onClick(View v) {
                if (Clicked4 == 0){
                    Show_Button4.setVisibility(View.VISIBLE);
                    TextColor4.setTextColor(Color.CYAN);
                    ImageAr7.setImageDrawable(requireContext().getDrawable(R.drawable.arrow_up));
                    ImageBk8.setColorFilter(Color.CYAN);
                    Clicked4++;}
                else if (Clicked4 == 1) {
                    Show_Button4.setVisibility(View.GONE);
                    TextColor4.setTextColor(Color.WHITE);
                    ImageAr7.setImageDrawable(requireContext().getDrawable(R.drawable.arrow_down));
                    ImageBk8.setColorFilter(Color.WHITE);
                    Clicked4--;}
            }
        });

        Show_Cat5.setOnClickListener(new View.OnClickListener() {
            int Clicked5 = 0;
            @Override
            public void onClick(View v) {
                if (Clicked5 == 0){
                    Show_Button5.setVisibility(View.VISIBLE);
                    TextColor5.setTextColor(Color.CYAN);
                    ImageAr9.setImageDrawable(requireContext().getDrawable(R.drawable.arrow_up));
                    ImageBk10.setColorFilter(Color.CYAN);
                    Clicked5++;}
                else if (Clicked5 == 1) {
                    Show_Button5.setVisibility(View.GONE);
                    TextColor5.setTextColor(Color.WHITE);
                    ImageAr9.setImageDrawable(requireContext().getDrawable(R.drawable.arrow_down));
                    ImageBk10.setColorFilter(Color.WHITE);
                    Clicked5--;}
            }

        });

        Show_Cat6.setOnClickListener(new View.OnClickListener() {
            int Clicked6 = 0;
            @Override
            public void onClick(View v) {
                if (Clicked6 == 0){
                    Show_Button6.setVisibility(View.VISIBLE);
                    TextColor6.setTextColor(Color.CYAN);
                    ImageAr11.setImageDrawable(requireContext().getDrawable(R.drawable.arrow_up));
                    ImageBk12.setColorFilter(Color.CYAN);
                    Clicked6++;}
                else if (Clicked6 == 1) {
                    Show_Button6.setVisibility(View.GONE);
                    TextColor6.setTextColor(Color.WHITE);
                    ImageAr11.setImageDrawable(requireContext().getDrawable(R.drawable.arrow_down));
                    ImageBk12.setColorFilter(Color.WHITE);
                    Clicked6--;}
            }

        });

        BTN1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String buttonText = BTN1.getText().toString();
                loadBook(buttonText);
            }
        });

        BTN2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String buttonText = BTN2.getText().toString();
                loadBook(buttonText);
            }
        });

        BTN3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String buttonText = BTN3.getText().toString();
                loadBook(buttonText);
            }
        });

        BTN4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String buttonText = BTN4.getText().toString();
                loadBook(buttonText);
            }
        });


        BTN5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String buttonText = BTN5.getText().toString();
                loadBook(buttonText);
            }
        });

        BTN6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String buttonText = BTN6.getText().toString();
                loadBook(buttonText);
            }
        });

        BTN7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String buttonText = BTN7.getText().toString();
                loadBook(buttonText);
            }
        });


        BTN8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String buttonText = BTN8.getText().toString();
                loadBook(buttonText);
            }
        });

        BTN9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String buttonText = BTN9.getText().toString();
                loadBook(buttonText);
            }
        });

        BTN10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String buttonText = BTN10.getText().toString();
                loadBook(buttonText);
            }
        });

        BTN11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String buttonText = BTN11.getText().toString();
                loadBook(buttonText);
            }
        });


        BTN12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String buttonText = BTN12.getText().toString();
                loadBook(buttonText);
            }
        });

        BTN13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String buttonText = BTN13.getText().toString();
                loadBook(buttonText);
            }
        });

        BTN14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String buttonText = BTN14.getText().toString();
                loadBook(buttonText);
            }
        });


        BTN15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String buttonText = BTN15.getText().toString();
                loadBook(buttonText);
            }
        });

        BTN16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String buttonText = BTN16.getText().toString();
                loadBook(buttonText);
            }
        });

        BTN17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String buttonText = BTN17.getText().toString();
                loadBook(buttonText);
            }
        });


        return view;
    }
    private void loadBook(String category) {
//        show_books_fragment showBooksFragment = show_books_fragment.newInstance(category);
//        FragmentManager fragmentManager = getParentFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.layoutBook, showBooksFragment);
//        fragmentTransaction.addToBackStack(null);
//        fragmentTransaction.commit();
        show_books_fragment ShowBooksFragment=  show_books_fragment.newInstance(category);

        getActivity().getSupportFragmentManager().beginTransaction().replace(android.R.id.content,ShowBooksFragment).commit();


    }

}