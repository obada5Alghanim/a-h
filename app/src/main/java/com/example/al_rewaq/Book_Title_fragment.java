package com.example.al_rewaq;

import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Book_Title_fragment extends Fragment {

    private TextView BookName, txt1, add_to_favorite, add_to_inprogress, add_to_want_to_read, add_to_read_it;
    private ArrayList<String> select_favorite = new ArrayList<>();
    private Map<TextView, String> buttonCategoryMap = new HashMap<>();
    private List<String> Favorite_books_List;
    private FirebaseFirestore db;
    private String userId;
    private FirebaseAuth auth;
    RelativeLayout bottom_sheet;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book__title_fragment, container, false);

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        userId = auth.getCurrentUser().getUid();
        bottom_sheet = view.findViewById(R.id.bottom_sheet_shadow_book_title);


        add_to_favorite = view.findViewById(R.id.addToFavorat);
        add_to_inprogress = view.findViewById(R.id.add_to_reading_inprogress);
        add_to_want_to_read = view.findViewById(R.id.add_to_want_to_read);
        add_to_read_it = view.findViewById(R.id.add_to_read_it);

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
                if (textViewContent.getLayout() != null && textViewContent.getLineCount() > 0) {
                    if (textViewContent.getLayout().getEllipsisCount(textViewContent.getLineCount() - 1) > 0) {
                        textSeeLess.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottom_sheet.setVisibility(View.VISIBLE);
            }
        });

        bottom_sheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottom_sheet.setVisibility(View.GONE);
            }
        });

        ImageView img = view.findViewById(R.id.image_Book);
        txt1 = view.findViewById(R.id.BookName);
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

            checkIfBookIsFavorite(bookTitle);  // Check if the book is in the favorites list
            checkIfBookIsInProgress(bookTitle); // Check if the book is in the reading in progress list
            checkIfBookIsWantToRead(bookTitle); // Check if the book is in the want to read list
            checkIfBookIsRead(bookTitle); // Check if the book is in the read list
        }

        add_to_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bookName = txt1.getText().toString();
                addBookToFavorite(bookName);
            }
        });

        add_to_inprogress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bookName = txt1.getText().toString();
                addBookToInProgress(bookName);
            }
        });

        add_to_want_to_read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bookName = txt1.getText().toString();
                addBookToWantToRead(bookName);
            }
        });

        add_to_read_it.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bookName = txt1.getText().toString();
                addBookToReadIt(bookName);
            }
        });

        return view;
    }

    private void checkIfBookIsFavorite(String bookTitle) {
        DocumentReference userRef = db.collection("users").document(userId);
        userRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    Favorite_books_List = (List<String>) documentSnapshot.get("Favorite_books");
                    if (Favorite_books_List != null && Favorite_books_List.contains(bookTitle)) {
                        add_to_favorite.setTextColor(Color.RED);  // Change color to red if the book is in the favorites list
                    }
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w("Firestore", "Error fetching favorite books", e);
            }
        });
    }

    private void addBookToFavorite(String bookTitle) {
        DocumentReference userRef = db.collection("users").document(userId);
        userRef.update("Favorite_books", FieldValue.arrayUnion(bookTitle))
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        add_to_favorite.setTextColor(Color.RED);  // Change color to red when added to favorites
                        Toast.makeText(getActivity(), "Added to favorites", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(), "Failed to add to favorites", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void checkIfBookIsInProgress(String bookTitle) {
        DocumentReference userRef = db.collection("users").document(userId);
        userRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    List<String> inProgress_books_List = (List<String>) documentSnapshot.get("Reading_inprogress");
                    if (inProgress_books_List != null && inProgress_books_List.contains(bookTitle)) {
                        add_to_inprogress.setTextColor(Color.BLUE);  // Change color to blue if the book is in the reading in progress list
                    }
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w("Firestore", "Error fetching in progress books", e);
            }
        });
    }

    private void addBookToInProgress(String bookTitle) {
        DocumentReference userRef = db.collection("users").document(userId);
        userRef.update("Reading_inprogress", FieldValue.arrayUnion(bookTitle))
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        add_to_inprogress.setTextColor(Color.BLUE);  // Change color to blue when added to reading in progress
                        Toast.makeText(getActivity(), "Added to reading in progress", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(), "Failed to add to reading in progress", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void checkIfBookIsWantToRead(String bookTitle) {
        DocumentReference userRef = db.collection("users").document(userId);
        userRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    List<String> wantToRead_books_List = (List<String>) documentSnapshot.get("Want_to_read");
                    if (wantToRead_books_List != null && wantToRead_books_List.contains(bookTitle)) {
                        add_to_want_to_read.setTextColor(Color.GREEN);  // Change color to green if the book is in the want to read list
                    }
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w("Firestore", "Error fetching want to read books", e);
            }
        });
    }

    private void addBookToWantToRead(String bookTitle) {
        DocumentReference userRef = db.collection("users").document(userId);
        userRef.update("Want_to_read", FieldValue.arrayUnion(bookTitle))
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        add_to_want_to_read.setTextColor(Color.GREEN);  // Change color to green when added to want to read
                        Toast.makeText(getActivity(), "Added to want to read", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(), "Failed to add to want to read", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void checkIfBookIsRead(String bookTitle) {
        DocumentReference userRef = db.collection("users").document(userId);
        userRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    List<String> read_books_List = (List<String>) documentSnapshot.get("Read_it");
                    if (read_books_List != null && read_books_List.contains(bookTitle)) {
                        add_to_read_it.setTextColor(Color.MAGENTA);  // Change color to magenta if the book is in the read list
                    }
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w("Firestore", "Error fetching read books", e);
            }
        });
    }

    private void addBookToReadIt(String bookTitle) {
        DocumentReference userRef = db.collection("users").document(userId);
        userRef.update("Read_it", FieldValue.arrayUnion(bookTitle))
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        add_to_read_it.setTextColor(Color.MAGENTA);  // Change color to magenta when added to read
                        Toast.makeText(getActivity(), "Added to read", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(), "Failed to add to read", Toast.LENGTH_SHORT).show();
                    }
                });
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
                            } catch (ActivityNotFoundException e) {
                                Toast.makeText(getActivity(), "No PDF Viewer Installed", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getActivity(), "PDF is Not Available", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    }
                } else {
                    Toast.makeText(getActivity(), "Document does not exist", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(), "Error Getting Document", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
