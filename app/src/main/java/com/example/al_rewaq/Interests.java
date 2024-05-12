package com.example.al_rewaq;


import static androidx.core.content.ContentProviderCompat.requireContext;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;



public class Interests extends AppCompatActivity {

    Button bt1,bt2,bt3,bt4,bt5,bt6,bt7,bt8,bt9 ,bt10,bt11,bt12,btn1,btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interests);
        bt1=findViewById(R.id.Button1);
        bt2=findViewById(R.id.Button2);
        bt3=findViewById(R.id.Button3);
        bt4=findViewById(R.id.Button5);
        bt6=findViewById(R.id.Button6);
        bt7=findViewById(R.id.Button7);
        bt8=findViewById(R.id.Button8);
        bt9=findViewById(R.id.Button9);
        bt10=findViewById(R.id.Button10);
        bt11=findViewById(R.id.Button11);
        bt12=findViewById(R.id.Button12);
        btn1 =findViewById(R.id.selected_btn);
        btn2 = findViewById(R.id.cancel_btn);
        bt1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {



                bt1.setTextColor(Color.WHITE);
                bt1.setBackgroundColor(Color.BLACK);
                int color = ContextCompat.getColor(Interests.this, R.color.white2);

                bt2.setTextColor(Color.BLACK);
                bt2.setBackgroundColor(color);

                bt3.setTextColor(Color.BLACK);
                bt3.setBackgroundColor(color);

                bt4.setTextColor(Color.BLACK);
                bt4.setBackgroundColor(color);

                bt5.setTextColor(Color.BLACK);
                bt5.setBackgroundColor(color);

                bt6.setTextColor(Color.BLACK);
                bt6.setBackgroundColor(color);


                bt7.setTextColor(Color.BLACK);
                bt7.setBackgroundColor(color);

                bt8.setTextColor(Color.BLACK);
                bt8.setBackgroundColor(color);

                bt9.setTextColor(Color.BLACK);
                bt9.setBackgroundColor(color);

                bt10.setTextColor(Color.BLACK);
                bt10.setBackgroundColor(color);

                bt11.setTextColor(Color.BLACK);
                bt11.setBackgroundColor(color);

                bt12.setTextColor(Color.BLACK);
                bt12.setBackgroundColor(color);

            }
        });



        bt2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                bt2.setTextColor(Color.WHITE);
                bt2.setBackgroundColor(Color.BLACK);
               // int color = ContextCompat.getColor(requireContext(), R.color.white2);
                int color = ContextCompat.getColor(Interests.this, R.color.white2);
                bt1.setTextColor(Color.BLACK);
                bt1.setBackgroundColor(color);

                bt3.setTextColor(Color.BLACK);
                bt3.setBackgroundColor(color);

                bt4.setTextColor(Color.BLACK);
                bt4.setBackgroundColor(color);

                bt5.setTextColor(Color.BLACK);
                bt5.setBackgroundColor(color);

                bt6.setTextColor(Color.BLACK);
                bt6.setBackgroundColor(color);


                bt7.setTextColor(Color.BLACK);
                bt7.setBackgroundColor(color);

                bt8.setTextColor(Color.BLACK);
                bt8.setBackgroundColor(color);

                bt9.setTextColor(Color.BLACK);
                bt9.setBackgroundColor(color);

                bt10.setTextColor(Color.BLACK);
                bt10.setBackgroundColor(color);

                bt11.setTextColor(Color.BLACK);
                bt11.setBackgroundColor(color);

                bt12.setTextColor(Color.BLACK);
                bt12.setBackgroundColor(color);
            }
        });

        bt3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                bt3.setTextColor(Color.WHITE);
                bt3.setBackgroundColor(Color.BLACK);

                int color = ContextCompat.getColor(Interests.this, R.color.white2);

                bt1.setTextColor(Color.BLACK);
                bt1.setBackgroundColor(color);

                bt2.setTextColor(Color.BLACK);
                bt2.setBackgroundColor(color);

                bt4.setTextColor(Color.BLACK);
                bt4.setBackgroundColor(color);

                bt5.setTextColor(Color.BLACK);
                bt5.setBackgroundColor(color);

                bt6.setTextColor(Color.BLACK);
                bt6.setBackgroundColor(color);


                bt7.setTextColor(Color.BLACK);
                bt7.setBackgroundColor(color);

                bt8.setTextColor(Color.BLACK);
                bt8.setBackgroundColor(color);

                bt9.setTextColor(Color.BLACK);
                bt9.setBackgroundColor(color);

                bt10.setTextColor(Color.BLACK);
                bt10.setBackgroundColor(color);

                bt11.setTextColor(Color.BLACK);
                bt11.setBackgroundColor(color);

                bt12.setTextColor(Color.BLACK);
                bt12.setBackgroundColor(color);

            }
        });

        bt4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                bt4.setTextColor(Color.WHITE);
                bt4.setBackgroundColor(Color.BLACK);

                int color = ContextCompat.getColor(Interests.this, R.color.white2);

                bt1.setTextColor(Color.BLACK);
                bt1.setBackgroundColor(color);

                bt2.setTextColor(Color.BLACK);
                bt2.setBackgroundColor(color);

                bt3.setTextColor(Color.BLACK);
                bt3.setBackgroundColor(color);

                bt5.setTextColor(Color.BLACK);
                bt5.setBackgroundColor(color);

                bt6.setTextColor(Color.BLACK);
                bt6.setBackgroundColor(color);


                bt7.setTextColor(Color.BLACK);
                bt7.setBackgroundColor(color);

                bt8.setTextColor(Color.BLACK);
                bt8.setBackgroundColor(color);

                bt9.setTextColor(Color.BLACK);
                bt9.setBackgroundColor(color);

                bt10.setTextColor(Color.BLACK);
                bt10.setBackgroundColor(color);

                bt11.setTextColor(Color.BLACK);
                bt11.setBackgroundColor(color);

                bt12.setTextColor(Color.BLACK);
                bt12.setBackgroundColor(color);

            }
        });

        bt5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                bt5.setTextColor(Color.WHITE);
                bt5.setBackgroundColor(Color.BLACK);

                int color = ContextCompat.getColor(Interests.this, R.color.white2);

                bt1.setTextColor(Color.BLACK);
                bt1.setBackgroundColor(color);

                bt2.setTextColor(Color.BLACK);
                bt2.setBackgroundColor(color);

                bt3.setTextColor(Color.BLACK);
                bt3.setBackgroundColor(color);

                bt4.setTextColor(Color.BLACK);
                bt4.setBackgroundColor(color);

                bt6.setTextColor(Color.BLACK);
                bt6.setBackgroundColor(color);


                bt7.setTextColor(Color.BLACK);
                bt7.setBackgroundColor(color);

                bt8.setTextColor(Color.BLACK);
                bt8.setBackgroundColor(color);

                bt9.setTextColor(Color.BLACK);
                bt9.setBackgroundColor(color);

                bt10.setTextColor(Color.BLACK);
                bt10.setBackgroundColor(color);

                bt11.setTextColor(Color.BLACK);
                bt11.setBackgroundColor(color);

                bt12.setTextColor(Color.BLACK);
                bt12.setBackgroundColor(color);

            }
        });

        bt6.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                bt6.setTextColor(Color.WHITE);
                bt6.setBackgroundColor(Color.BLACK);

                int color = ContextCompat.getColor(Interests.this, R.color.white2);

                bt1.setTextColor(Color.BLACK);
                bt1.setBackgroundColor(color);

                bt2.setTextColor(Color.BLACK);
                bt2.setBackgroundColor(color);

                bt3.setTextColor(Color.BLACK);
                bt3.setBackgroundColor(color);

                bt4.setTextColor(Color.BLACK);
                bt4.setBackgroundColor(color);

                bt5.setTextColor(Color.BLACK);
                bt5.setBackgroundColor(color);


                bt7.setTextColor(Color.BLACK);
                bt7.setBackgroundColor(color);

                bt8.setTextColor(Color.BLACK);
                bt8.setBackgroundColor(color);

                bt9.setTextColor(Color.BLACK);
                bt9.setBackgroundColor(color);

                bt10.setTextColor(Color.BLACK);
                bt10.setBackgroundColor(color);

                bt11.setTextColor(Color.BLACK);
                bt11.setBackgroundColor(color);

                bt12.setTextColor(Color.BLACK);
                bt12.setBackgroundColor(color);

            }
        });


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Interests.this,menu_main.class);
                startActivity(intent);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Interests.this,menu_main.class);
                startActivity(intent);

            }
        });

}
}
//
//        bt1.setOnClickListener(new MaterialButton(bt1.getContext()).addOnCheckedChangeListener(){
//            public void onCheckedStateChangedListener(MaterialButton()  MaterialButton, int i) {
//                bt1.setOnClickListener(new View.OnClickListener() {
//                    boolean Clicked1 = false;
//                    @Override
//                    public void onClick(View v) {
//
//
//                        if (Clicked1 == false){
//                            bt1.setTextColor(Color.WHITE);
//                            bt1.setBackgroundColor(Color.BLACK);
//                            int color = ContextCompat.getColor(requireContext(), R.color.white2);
//
//                            bt2.setTextColor(Color.BLACK);
//                            bt2.setBackgroundColor(color);
//
//                            bt3.setTextColor(Color.BLACK);
//                            bt3.setBackgroundColor(color);
//
//                            bt4.setTextColor(Color.BLACK);
//                            bt4.setBackgroundColor(color);
//
//                            bt5.setTextColor(Color.BLACK);
//                            bt5.setBackgroundColor(color);
//
//                            bt6.setTextColor(Color.BLACK);
//                            bt6.setBackgroundColor(color);
//
//                            Clicked1 = true;}
//                        else if (Clicked1 == true) {
//                            bt1.setTextColor(Color.BLACK);
//                            int color = ContextCompat.getColor(requireContext(), R.color.white2);
//                            bt1.setBackgroundColor(color);
//                            Clicked1 = false;}
//                    }
//                });
//}
//
//    });
//            @Override
//            public void change(View v) {
//
//
//
//                ch1.setTextColor(Color.WHITE);
//                ch1.setBackgroundColor(Color.BLACK);
//                int color = ContextCompat.getColor(requireContext(), R.color.white2);
//
//                ch2.setTextColor(Color.BLACK);
//                ch2.setBackgroundColor(color);
//
//                ch3.setTextColor(Color.BLACK);
//                ch3.setBackgroundColor(color);
//
//                ch4.setTextColor(Color.BLACK);
//                ch4.setBackgroundColor(color);
//
//                ch5.setTextColor(Color.BLACK);
//                ch5.setBackgroundColor(color);
//
//                ch6.setTextColor(Color.BLACK);
//                ch6.setBackgroundColor(color);
//
//
//
//            }
//        });

//        ch2.setOnClickListener(new View.OnClickListener() {
//            boolean Clicked2 = false;
//            @Override
//            public void onClick(View v) {
//
//
//                if (Clicked2 == false){
//                    ch2.setTextColor(Color.WHITE);
//                    ch2.setBackgroundColor(Color.BLACK);
//                    int color = ContextCompat.getColor(requireContext(), R.color.white2);
//
//                    ch2.setTextColor(Color.BLACK);
//                    ch2.setBackgroundColor(color);
//
//                    ch3.setTextColor(Color.BLACK);
//                    ch3.setBackgroundColor(color);
//
//                    ch4.setTextColor(Color.BLACK);
//                    ch4.setBackgroundColor(color);
//
//                    ch5.setTextColor(Color.BLACK);
//                    ch5.setBackgroundColor(color);
//
//                    ch6.setTextColor(Color.BLACK);
//                    ch6.setBackgroundColor(color);
//
//                    Clicked2 = true;}
//                else if (Clicked2 == true) {
//                    ch2.setTextColor(Color.BLACK);
//                    int color = ContextCompat.getColor(requireContext(), R.color.white2);
//                    ch2.setBackgroundColor(color);
//                    Clicked2 = false;}
//            }
//        });
//        ch3.setOnClickListener(new View.OnClickListener() {
//            boolean Clicked3 = false;
//            @Override
//            public void onClick(View v) {
//
//
//                if (Clicked3 == false){
//                    ch3.setTextColor(Color.WHITE);
//                    ch3.setBackgroundColor(Color.BLACK);
//                    int color = ContextCompat.getColor(requireContext(), R.color.white2);
//
//                    ch2.setTextColor(Color.BLACK);
//                    ch2.setBackgroundColor(color);
//
//                    ch3.setTextColor(Color.BLACK);
//                    ch3.setBackgroundColor(color);
//
//                    ch4.setTextColor(Color.BLACK);
//                    ch4.setBackgroundColor(color);
//
//                    ch5.setTextColor(Color.BLACK);
//                    ch5.setBackgroundColor(color);
//
//                    ch6.setTextColor(Color.BLACK);
//                    ch6.setBackgroundColor(color);
//
//                    Clicked3 = true;}
//                else if (Clicked3 == true) {
//                    ch3.setTextColor(Color.BLACK);
//                    int color = ContextCompat.getColor(requireContext(), R.color.white2);
//                    ch3.setBackgroundColor(color);
//                    Clicked3 = false;}
//            }
//        });
//
//    }

//    public void change(View view) {
//        x.setTextColor(getColor(R.color.black));
//        x.setBackgroundColor(getColor(R.color.white));
//    }
