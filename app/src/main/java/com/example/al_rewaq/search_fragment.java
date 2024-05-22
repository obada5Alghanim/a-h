package com.example.al_rewaq;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.squareup.picasso.Picasso;

public class search_fragment extends Fragment {

    private EditText searchField;
    private Button searchButton;
    private TextView resultTextView;
    private FirebaseFirestore db;
    ImageView img;
    RelativeLayout RL, SRLID;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_fragment, container, false);

        img = view.findViewById(R.id.image_Book1);
        searchField = view.findViewById(R.id.Search_Field);
        searchButton = view.findViewById(R.id.Search_Button);
        resultTextView = view.findViewById(R.id.Search_Results);
        db = FirebaseFirestore.getInstance();
        RL = view.findViewById(R.id.RelativeLayout);
        SRLID = view.findViewById(R.id.searchRealativelayoutID);

        SRLID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RL.setVisibility(View.INVISIBLE);

            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                searchBook();
            }
        });

        return view;
    }

    private void searchBook() {
        String Book_Name = searchField.getText().toString();

        db.collection("Book").whereEqualTo("Book_Name", Book_Name).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    StringBuilder result = new StringBuilder();
                    RL.setVisibility(View.VISIBLE);
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String imageUrl = document.getString("Image_URL");

                        if (imageUrl != null) {
                            img.setVisibility(View.VISIBLE);
                            Picasso.get().load(imageUrl).into(img);
                        } else {
                            img.setVisibility(View.GONE);
                            RL.setVisibility(View.INVISIBLE);
                        }
                        result.append("").append(document.getString("Book_Name")).append("\n");

                        SRLID.setOnClickListener(v -> {
                            String bookTitle = document.getString("Book_Name");
                            String bookAuthor = document.getString("Author");
                            String section = document.getString("Section");
                            String year = document.getString("Year");
                            String noPage = document.getString("No_Page");
                            String bookDescription = document.getString("Description");

                            Bundle bundle = new Bundle();
                            bundle.putString("Book_Name", bookTitle);
                            bundle.putString("Author", bookAuthor);
                            bundle.putString("Section", section);
                            bundle.putString("years", year);
                            bundle.putString("NoPage", noPage);
                            bundle.putString("Description", bookDescription);
                            bundle.putString("Image_URL", imageUrl);

                            Book_Title_fragment bookTitleFragment = new Book_Title_fragment();
                            bookTitleFragment.setArguments(bundle);

                            // تبديل Fragment
                            getActivity().getSupportFragmentManager().beginTransaction().replace(android.R.id.content, bookTitleFragment).commit();
                        });
                    }

                    if (result.length() == 0) {
                        Toast.makeText(getActivity(), "لا يوجد كتاب بهذا الأسم", Toast.LENGTH_SHORT).show();
                        RL.setVisibility(View.INVISIBLE);
                    } else {
                        resultTextView.setText(result.toString());
                    }
                } else {
                    resultTextView.setText("Error getting documents: " + task.getException().getMessage());
                }
            }
        });
    }

}