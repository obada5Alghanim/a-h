package com.example.al_rewaq;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;


public class bottom_sheet_fragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottom_sheet_fragment, container, false);
        RelativeLayout relativeLayout = view.findViewById(R.id.realtiveLayoutForBottomSheet);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeFragment();
            }
        });
        ////////////////////////////////////////////////////////////////////////////////// shit



//        textViewBook.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                textViewBook.setTextColor(Color.RED);
//                addBookToFirestore(book_favorite);
//
//            }
//        });






        return view;

    }
    private void addBookToFirestore(String Book_Name) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        String userId = auth.getCurrentUser().getUid();

        DocumentReference userRef = db.collection("users").document(userId);
        userRef.update("Favorites_book", FieldValue.arrayUnion(Book_Name))
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("Firestore", "تم اضافة الكتاب الى المفضلة");

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("Firestore", "لم يتم اضافة الكتاب الى المفضلة", e);
                    }
                });
    }
    //////////////////////////////////////////////////////////////////////////////////moh
    private void removeFragment() {
        if (requireActivity().getSupportFragmentManager() != null) {
            // Begin a transaction to remove this fragment
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .remove(this)
                    .commit();
        }
    }
}