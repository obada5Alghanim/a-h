package com.example.al_rewaq;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


public class chooesBookResulte extends Fragment {
TextView txt;
ImageView img;

    private String bookTitle;
    private String bookAuthor;
    private String bookImageUrl;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chooes_book_resulte, container, false);
        txt = view.findViewById(R.id.txt3);
        img = view.findViewById(R.id.imageView13);

        if (getArguments() != null) {
            bookTitle = getArguments().getString("bookTitle");
            bookImageUrl = getArguments().getString("bookImageUrl");
        }



        txt.setText(bookTitle);


        // تحميل الصورة باستخدام مكتبة Picasso
        Picasso.get().load(bookImageUrl).into(img);




        return view;
    }
}