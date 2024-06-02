package com.example.al_rewaq;

import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;



public class choose_book_Q5 extends Fragment {

    Button click_answar5_1, click_answar5_2;
    TextView result_Q;
    FirebaseFirestore db;
    int sum = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_choose_book__q5, container, false);

        click_answar5_1 = view.findViewById(R.id.answar5_1);
        click_answar5_2 = view.findViewById(R.id.answar5_2);
        result_Q = view.findViewById(R.id.next_Q);


        db = FirebaseFirestore.getInstance();
        Bundle reciveFromYear = this.getArguments();
        String q3 = reciveFromYear.getString("year");
        String q2 = reciveFromYear.getString("yearyear");
        Long q4 = reciveFromYear.getLong("2000year");


        click_answar5_1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                    click_answar5_1.setTextColor(Color.WHITE);
                    click_answar5_1.setBackgroundColor(Color.BLACK);

                    int color = ContextCompat.getColor(requireContext(), R.color.white2);

                    click_answar5_2.setTextColor(Color.BLACK);
                    click_answar5_2.setBackgroundColor(color);
                    sum =1;

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
                    sum  = 2;

            }
        });

        result_Q.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // تحديد الشرط بناءً على السنة
                Query query = null;
                if (q4 >= 2000 && sum ==1) {
                    query = db.collection("Book").whereEqualTo("Section", q3).whereEqualTo("Language", q2).whereGreaterThanOrEqualTo("Year", 2000).whereLessThan("No_Page",300);
                } else if (q4 >= 2000 && sum ==2){
                    query = db.collection("Book").whereEqualTo("Section", q3).whereEqualTo("Language", q2).whereGreaterThanOrEqualTo("Year", 2000).whereGreaterThanOrEqualTo("No_Page",300);
                } else if (q4 < 2000 && sum ==1) {
                    query = db.collection("Book").whereEqualTo("Section", q3).whereEqualTo("Language", q2).whereLessThan("Year", 2000).whereLessThan("No_Page",300);

                } else if (q4 < 2000 && sum ==2) {
                    query = db.collection("Book").whereEqualTo("Section", q3).whereEqualTo("Language", q2).whereLessThan("Year", 2000).whereGreaterThanOrEqualTo("No_Page",300);

                }



                query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<DocumentSnapshot> documents = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                documents.add(document);
                            }

                            if (!documents.isEmpty()) {
                                Random rand = new Random();
                                int randomIndex = rand.nextInt(documents.size());
                                DocumentSnapshot randomBook = documents.get(randomIndex);

                                // الحصول على بيانات الكتاب العشوائي
                                String bookTitle = randomBook.getString("Book_Name");
                                String bookImageUrl = randomBook.getString("Image_URL");
                                String bookauth = randomBook.getString("Author");
                                String booksect = randomBook.getString("Section");
                                Long bookyear = randomBook.getLong("Year");
                                String bookdesc = randomBook.getString("Description");
                                Long bookpage = randomBook.getLong("No_Page");

                                // تمرير البيانات إلى Fragment
                                Bundle bundle = new Bundle();
                                bundle.putString("Book_Name", bookTitle);
                                bundle.putString("Author", bookauth);
                                bundle.putString("Image_URL", bookImageUrl);
                                bundle.putString("Section", booksect);
                                bundle.putLong("years", bookyear);
                                bundle.putString("Description", bookdesc);
                                bundle.putLong("NoPage", bookpage);
                                Log.d("Bundle Values", "Section: " + q3 + ", Language: " + q2 + ", Year: " + q4);


                                Book_Title_fragment bookFragment = new Book_Title_fragment();
                                bookFragment.setArguments(bundle);

                                getActivity().getSupportFragmentManager().beginTransaction().replace(android.R.id.content, bookFragment).commit();
                            } else {
                                Toast.makeText(getActivity(), "الكتاب غير متوفر", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getActivity(), "Error getting documents: "+ task.getException(), Toast.LENGTH_SHORT).show();
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