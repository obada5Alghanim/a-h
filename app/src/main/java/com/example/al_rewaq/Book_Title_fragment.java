package com.example.al_rewaq;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.TextView;

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

                // Expand the TextView
//                textViewContent.setMaxLines(Integer.MAX_VALUE);
//                textSeeLess.setVisibility(View.GONE);
                if (less == 0) {
                    textViewContent.setMaxLines(Integer.MAX_VALUE);
//                textSeeLess.setVisibility(View.GONE);
                    textSeeLess.setText("see less");
                    less++;
                } else if (less == 1) {
                    textViewContent.setMaxLines(2);
//                 textViewContent.setMinLines(Integer.MIN_VALUE);
//                 textSeeLess.setVisibility(View.GONE);
                    textSeeLess.setText("see more");
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

        // Inflate the layout for this fragment
        return view;
    }
}