package com.example.al_rewaq;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

public class Book_Title_fragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book__title_fragment, container, false);

        TextView textViewContent = view.findViewById(R.id.textViewContent);
        Button btn = view.findViewById(R.id.bu_menu);
        TextView textSeeLess = view.findViewById(R.id.textSee);



        textSeeLess.setOnClickListener(new View.OnClickListener() {
            int less = 0;

            @Override
            public void onClick(View v) {


                if (less == 0) {
                    textViewContent.setMaxLines(Integer.MAX_VALUE);
                    textSeeLess.setText("عرض أقل");
                    less++;
                } else if (less == 1) {
                    textViewContent.setMaxLines(2);
                    textSeeLess.setText("عرض المزيد");
                    less--;
                }

            }
        });
        textViewContent.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                textViewContent.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                // Detect when text is fully displayed
                if (textViewContent.getLayout() != null && textViewContent.getLineCount() > 0) {
                    if (textViewContent.getLayout().getEllipsisCount(textViewContent.getLineCount() - 1) > 0) {
                        // Text is truncated, show "See More" button
                        textSeeLess.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottom_sheet_fragment bottom_sheet_fragment1 = new bottom_sheet_fragment();
                getActivity().getSupportFragmentManager().beginTransaction().add(android.R.id.content,bottom_sheet_fragment1).commit();
            }
        });

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        ImageView img = view.findViewById(R.id.image_Book);
        TextView txt1 = view.findViewById(R.id.BookName);
        TextView txt2 = view.findViewById(R.id.Author_Name);
        TextView txt3 = view.findViewById(R.id.Classification_type);
        TextView txt4 = view.findViewById(R.id.Datecreated_data);
        TextView txt5 = view.findViewById(R.id.numberOfPages_data);
        TextView txt6 = view.findViewById(R.id.textViewContent);

        db.collection("Book").document("TC0005B").get().addOnSuccessListener(documentSnapshot -> {

                    // الحصول على بيانات المستخدم بنجاح
                    String img1 = documentSnapshot.getString("Image_URL");
                    Picasso.get().load(img1).into(img);
                    String S1 = documentSnapshot.getString("Book_Name");
                    txt1.setText(S1);
                    String S2 = documentSnapshot.getString("Author");
                    txt2.setText(S2);
                    String S3 = documentSnapshot.getString("Section");
                    txt3.setText(S3);
                    String S4 = documentSnapshot.getString("Year");
                    txt4.setText(S4);
                    String S5 = documentSnapshot.getString("No_Page");
                    txt5.setText(S5);
                    String S6 = documentSnapshot.getString("Description");
                    txt6.setText(S6);

                })
                .addOnFailureListener(e -> {
                    // فشل في استرجاع بيانات المستخدم
                });

            return view;


            //Basel---------------------------------------------------------------------------------------------

        }
    }