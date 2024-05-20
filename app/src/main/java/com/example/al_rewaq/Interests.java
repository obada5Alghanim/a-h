package com.example.al_rewaq;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import androidx.appcompat.app.AppCompatActivity;

public class Interests extends AppCompatActivity {

    Button bt1,bt2,bt3,bt4,bt5,bt6,bt7,bt8,bt9 ,bt10,bt11,bt12,bt13,bt14,bt15,bt16,bt17,btn1,btn2;

    private ArrayList<String> selectedCategories = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interests);
        bt1= findViewById(R.id.Button1);
        bt2= findViewById(R.id.Button2);
        bt3= findViewById(R.id.Button3);
        bt4= findViewById(R.id.Button4);
        bt5 = findViewById(R.id.Button5);
        bt6= findViewById(R.id.Button6);
        bt7= findViewById(R.id.Button7);
        bt8= findViewById(R.id.Button8);
        bt9= findViewById(R.id.Button9);
        bt10= findViewById(R.id.Button10);
        bt11= findViewById(R.id.Button11);
        bt12= findViewById(R.id.Button12);
        bt13= findViewById(R.id.Button13);
        bt14= findViewById(R.id.Button14);
        bt15= findViewById(R.id.Button15);
        bt16= findViewById(R.id.Button16);
        bt17= findViewById(R.id.Button17);
        btn1 = findViewById(R.id.selected_btn);
        btn2 = findViewById(R.id.cancel_btn);
        bt1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                bt1.setTextColor(Color.BLACK);
                bt1.setBackgroundColor(Color.WHITE);
                //add the catgory to array
                selectedCategories.add("روايات دينية");



            }
        });



        bt2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                bt2.setTextColor(Color.BLACK);
                bt2.setBackgroundColor(Color.WHITE);

                selectedCategories.add("روايات مغامرات");


            }
        });

        bt3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                bt3.setTextColor(Color.BLACK);
                bt3.setBackgroundColor(Color.WHITE);




            }
        });

        bt4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                bt4.setTextColor(Color.BLACK);
                bt4.setBackgroundColor(Color.WHITE);




            }
        });

        bt5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                bt5.setTextColor(Color.BLACK);
                bt5.setBackgroundColor(Color.WHITE);





            }
        });

        bt6.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                bt6.setTextColor(Color.BLACK);
                bt6.setBackgroundColor(Color.WHITE);




            }
        });


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Interests.this,menu_main.class);
                intent.putStringArrayListExtra("selectedCategories", selectedCategories);
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