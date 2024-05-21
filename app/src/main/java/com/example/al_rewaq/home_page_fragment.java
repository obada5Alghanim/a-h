package com.example.al_rewaq;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

public class home_page_fragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_page_fragment, container, false);

        TextView txt1 = view.findViewById(R.id.bookSection);
        ImageView img1 = view.findViewById(R.id.imageView1);
        ImageView img2 = view.findViewById(R.id.imageView2);
        ImageView img3 = view.findViewById(R.id.imageView3);
        ImageView img4 = view.findViewById(R.id.imageView4);
        ImageView img5 = view.findViewById(R.id.imageView5);
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book_Title_fragment bookTitleFragment = new Book_Title_fragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(android.R.id.content,bookTitleFragment).commit();
            }
        });

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("Book").document("TC0001B").get().addOnSuccessListener(documentSnapshot -> {
            String img_1 = documentSnapshot.getString("Image_URL");
            Picasso.get().load(img_1).into(img1);

            String txt_1 = documentSnapshot.getString("Section");
            txt1.setText(txt_1);

        })
                .addOnFailureListener(e -> {
                    // فشل في استرجاع بيانات المستخدم
                });

        db.collection("Book").document("TC0002B").get().addOnSuccessListener(documentSnapshot -> {
                    String img_2 = documentSnapshot.getString("Image_URL");
                    Picasso.get().load(img_2).into(img2);
                })
                .addOnFailureListener(e -> {
                    // فشل في استرجاع بيانات المستخدم
                });

        db.collection("Book").document("TC0003B").get().addOnSuccessListener(documentSnapshot -> {
                    String img_3 = documentSnapshot.getString("Image_URL");
                    Picasso.get().load(img_3).into(img3);
                })
                .addOnFailureListener(e -> {
                    // فشل في استرجاع بيانات المستخدم
                });

        db.collection("Book").document("TC0004B").get().addOnSuccessListener(documentSnapshot -> {
                    String img_4 = documentSnapshot.getString("Image_URL");
                    Picasso.get().load(img_4).into(img4);
                })
                .addOnFailureListener(e -> {
                    // فشل في استرجاع بيانات المستخدم
                });

        db.collection("Book").document("TC0005B").get().addOnSuccessListener(documentSnapshot -> {
                    String img_5 = documentSnapshot.getString("Image_URL");
                    Picasso.get().load(img_5).into(img5);
                })
                .addOnFailureListener(e -> {
                    // فشل في استرجاع بيانات المستخدم
                });

        return view;

    }
}