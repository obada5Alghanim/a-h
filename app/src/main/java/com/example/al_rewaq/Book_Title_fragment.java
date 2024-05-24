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
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;
import androidx.annotation.NonNull;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class Book_Title_fragment extends Fragment {

    private TextView BookName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book__title_fragment, container, false);

        BookName = view.findViewById(R.id.BookName);
        Button openPdfButton = view.findViewById(R.id.bu_browse);

        openPdfButton.setOnClickListener(v -> {
            String bookName = BookName.getText().toString().trim();
            if (!bookName.isEmpty()) {
                openPdfFromFirestore(bookName);
            } else {
                Toast.makeText(getActivity(), "Book Name is Empty", Toast.LENGTH_SHORT).show();
            }
        });

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




        Bundle bundle = getArguments();
        if (bundle != null) {
            String bookTitle = bundle.getString("Book_Name");
            String bookAuthor = bundle.getString("Author");
            String section = bundle.getString("Section");
            String year = bundle.getString("years");
            String noPage = bundle.getString("NoPage");
            String bookDescription = bundle.getString("Description");
            String imageUrl = bundle.getString("Image_URL");

            txt1.setText(bookTitle);
            txt2.setText(bookAuthor);
            txt3.setText(section);
            txt4.setText(year);
            txt5.setText(noPage);
            txt6.setText(bookDescription);
            Picasso.get().load(imageUrl).into(img);

        }

            return view;

        }

    private void openPdfFromFirestore(String bookName) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Query query = db.collection("Book").whereEqualTo("Book_Name", bookName);

        query.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (!queryDocumentSnapshots.isEmpty()) {
                    for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                        String pdfUrl = documentSnapshot.getString("PDF");
                        if (pdfUrl != null && !pdfUrl.isEmpty()) {
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setDataAndType(Uri.parse(pdfUrl), "application/pdf");
                            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                            Intent chooser = Intent.createChooser(intent, "Open PDF");

                            try {
                                startActivity(chooser);
                            }
                            catch (ActivityNotFoundException e) {
                                Toast.makeText(getActivity(), "No PDF Viewer Installed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(getActivity(), "PDF is Not Available", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    }
                }
                else {
                    Toast.makeText(getActivity(), "Document does not exist", Toast.LENGTH_SHORT).show();
                }
            }
        })
                .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(), "Error Getting Document", Toast.LENGTH_SHORT).show();
            }
        });
    }

}