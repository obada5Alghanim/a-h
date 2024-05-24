package com.example.al_rewaq;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class choose_book_Q5 extends Fragment {

    Button click_answar5_1, click_answar5_2;

    TextView result_Q;

    FirebaseFirestore db;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_choose_book__q5, container, false);

        click_answar5_1 = view.findViewById(R.id.answar5_1);
        click_answar5_2 = view.findViewById(R.id.answar5_2);

        result_Q = view.findViewById(R.id.next_Q);
        // add the object of new result Fragmentssss  here
        db = FirebaseFirestore.getInstance();
        Bundle reciveFromYear = this.getArguments();
        String fromYear = reciveFromYear.getString("year");
        String fromYearyear = reciveFromYear.getString("yearyear");




        click_answar5_1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {



                    click_answar5_1.setTextColor(Color.WHITE);
                    click_answar5_1.setBackgroundColor(Color.BLACK);
                    int color = ContextCompat.getColor(requireContext(), R.color.white2);

                    click_answar5_2.setTextColor(Color.BLACK);
                    click_answar5_2.setBackgroundColor(color);

            }
        });

        click_answar5_2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {



                    click_answar5_2.setTextColor(Color.WHITE);
                    click_answar5_2.setBackgroundColor(Color.BLACK);
                    int color = ContextCompat.getColor(requireContext(), R.color.white2);

                    click_answar5_1.setTextColor(Color.BLACK);
                    click_answar5_1.setBackgroundColor(color);


            }
        });

result_Q.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        db.collection("Book").whereEqualTo("Section",fromYear).whereEqualTo("Language",fromYearyear).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    List<DocumentSnapshot> documents = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        documents.add(document);
                        if (!documents.isEmpty()) {
                            Random rand = new Random();
                            int randomIndex = rand.nextInt(documents.size());
                            DocumentSnapshot randomBook = documents.get(randomIndex);

                            // الحصول على بيانات الكتاب العشوائي
                            String bookTitle = randomBook.getString("Book_Name");
                            String bookImageUrl = randomBook.getString("Image_URL");
                            String bookauth = randomBook.getString("Author");
                            String booksect = randomBook.getString("Section");
                            String bookyear = randomBook.getString("Year");
                            String bookdesc = randomBook.getString("Description");
                            String bookpage = randomBook.getString("No_Page");



                            // تمرير البيانات إلى Fragment
                            Bundle bundle = new Bundle();
                            bundle.putString("Book_Name", bookTitle);
                            bundle.putString("Author", bookauth);
                            bundle.putString("Image_URL", bookImageUrl);
                            bundle.putString("Section", booksect);
                            bundle.putString("years", bookyear);
                            bundle.putString("Description", bookdesc);
                            bundle.putString("NoPage", bookpage);

                            Book_Title_fragment bookFragment = new Book_Title_fragment();
                            bookFragment.setArguments(bundle);

                            getActivity().getSupportFragmentManager().beginTransaction().replace(android.R.id.content, bookFragment).commit();

                        } else {
                            System.out.println("No books found in this category.");
                        }
                    }
                } else {
                    System.out.println("Error getting documents: " + task.getException());
                }



                    }

        });

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