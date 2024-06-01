package com.example.al_rewaq;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Book_Title_fragment extends Fragment {

    private FirebaseFirestore db;
    private FirebaseAuth auth;
    private String userId;
    private RelativeLayout bottom_sheet;
    private TextView add_to_favorite, add_to_inprogress, add_to_want_to_read, add_to_read_it;
    private TextView BookName, txt1;

    private static final String ARG_IMAGE_URL = "Image_URL";
    private static final String ARG_SECTION = "Section";
    private static final String ARG_AUTHOR = "Author";
    private static final String ARG_DESCRIPTION = "Description";
    private static final String ARG_TITLE = "Book_Name";
    private static final String ARG_YEAR = "years";
    private static final String ARG_NO_PAGE = "NoPage";

    public static Book_Title_fragment newInstance(String imageUrl, String section, String author, String description, String title, Long noPage, Long year) {
        Book_Title_fragment fragment = new Book_Title_fragment();
        Bundle args = new Bundle();
        args.putString(ARG_IMAGE_URL, imageUrl);
        args.putString(ARG_SECTION, section);
        args.putString(ARG_AUTHOR, author);
        args.putString(ARG_DESCRIPTION, description);
        args.putString(ARG_TITLE, title);
        args.putLong(ARG_YEAR, year);
        args.putLong(ARG_NO_PAGE, noPage);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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
        Button PDFAddInprogress = view.findViewById(R.id.bu_reader);

        openPdfButton.setOnClickListener(v -> {
            String bookName = BookName.getText().toString().trim();
            if (!bookName.isEmpty()) {
                openPdfFromFirestore(bookName);
            } else {
                Toast.makeText(getActivity(), "Book Name is Empty", Toast.LENGTH_SHORT).show();
            }
        });

        PDFAddInprogress.setOnClickListener(v -> {
            String bookName = BookName.getText().toString().trim();
            if (!bookName.isEmpty()) {
                String bookName1 = txt1.getText().toString();
                addBookToReadingInProgress(bookName1, add_to_inprogress, R.drawable.clock2);
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

        btn.setOnClickListener(v -> bottom_sheet.setVisibility(View.VISIBLE));
        bottom_sheet.setOnClickListener(v -> bottom_sheet.setVisibility(View.GONE));

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
            Long year = bundle.getLong("years");
            Long noPage = bundle.getLong("NoPage");
            String bookDescription = bundle.getString("Description");
            String imageUrl = bundle.getString("Image_URL");

            String noPage1 = Long.toString(noPage);
            String year1  = Long.toString(year);
            txt1.setText(bookTitle);
            txt2.setText(bookAuthor);
            txt3.setText(section);
            txt4.setText(year1);
            txt5.setText(noPage1);
            txt6.setText(bookDescription);
            Picasso.get().load(imageUrl).into(img);

            checkIfBookIsFavorite(bookTitle);
            checkIfBookIsInProgress(bookTitle);
            checkIfBookIsWantToRead(bookTitle);
            checkIfBookIsRead(bookTitle);
        }

        add_to_favorite.setOnClickListener(v -> {
            String bookName = txt1.getText().toString();
            toggleBookInList(bookName, "Favorite_books", add_to_favorite, R.drawable.vector12_2, R.drawable.vector12);
        });

        add_to_inprogress.setOnClickListener(v -> {
            String bookName = txt1.getText().toString();
            toggleBookInList(bookName, "Reading_inprogress", add_to_inprogress, R.drawable.clock2, R.drawable.clock);
        });

        add_to_want_to_read.setOnClickListener(v -> {
            String bookName = txt1.getText().toString();
            toggleBookInList(bookName, "Want_to_read", add_to_want_to_read, R.drawable.book2, R.drawable.book);
        });

        add_to_read_it.setOnClickListener(v -> {
            String bookName = txt1.getText().toString();
            toggleBookInList(bookName, "Read_it", add_to_read_it, R.drawable.resource_true2, R.drawable.resource_true);
        });

        return view;
    }

    private void checkIfBookIsFavorite(String bookTitle) {
        checkIfBookInList(bookTitle, "Favorite_books", add_to_favorite, R.drawable.vector12_2);
    }

    private void checkIfBookIsInProgress(String bookTitle) {
        checkIfBookInList(bookTitle, "Reading_inprogress", add_to_inprogress, R.drawable.clock2);
    }

    private void checkIfBookIsWantToRead(String bookTitle) {
        checkIfBookInList(bookTitle, "Want_to_read", add_to_want_to_read, R.drawable.book2);
    }

    private void checkIfBookIsRead(String bookTitle) {
        checkIfBookInList(bookTitle, "Read_it", add_to_read_it, R.drawable.resource_true2);
    }

    private void checkIfBookInList(String bookTitle, String listName, TextView textView, int drawableResId) {
        DocumentReference userRef = db.collection("users").document(userId);
        userRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    List<String> bookList = (List<String>) documentSnapshot.get(listName);
                    if (bookList != null && bookList.contains(bookTitle)) {
                        Drawable drawable = ContextCompat.getDrawable(getActivity(), drawableResId);
                        textView.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
                    }
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w("Firestore", "Error fetching book list", e);
            }
        });
    }

    private void addBookToReadingInProgress(String bookTitle, TextView textView, int drawableResId) {
        DocumentReference userRef = db.collection("users").document(userId);
        userRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    List<String> readingInProgressList = (List<String>) documentSnapshot.get("Reading_inprogress");
                    if (readingInProgressList == null || !readingInProgressList.contains(bookTitle)) {
                        userRef.update("Reading_inprogress", FieldValue.arrayUnion(bookTitle))
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Drawable drawable = ContextCompat.getDrawable(getActivity(), drawableResId);
                                        textView.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
                                        Toast.makeText(getActivity(), "تم الأضافة الى أقرأه حاليا", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(getActivity(), "لم يتم الأضافة الى اقرأه حاليا", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w("Firestore", "Error fetching Reading in Progress list", e);
            }
        });
    }

    private void toggleBookInList(String bookTitle, String listName, TextView textView, int drawableResId, int oldDrawableResId) {
        DocumentReference userRef = db.collection("users").document(userId);
        userRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                if (documentSnapshot.exists()) {
                    List<String> bookList = (List<String>) documentSnapshot.get(listName);
                    Drawable drawable = ContextCompat.getDrawable(getActivity(), drawableResId);
                    Drawable oldDrawable = ContextCompat.getDrawable(getActivity(), oldDrawableResId);
                    if (bookList != null && bookList.contains(bookTitle)) {
                        userRef.update(listName, FieldValue.arrayRemove(bookTitle))
                                .addOnSuccessListener(new OnSuccessListener<Void>() {

                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        String erorr = "";
                                        if (listName=="Favorite_books"){
                                            erorr = "المفضلة";
                                        } else if (listName=="Reading_inprogress") {
                                            erorr = "أقرأه حاليا";
                                        } else if (listName=="Want_to_read") {
                                            erorr = "انوي قراءتة";
                                        } else if (listName=="Read_it") {
                                            erorr = "قرأتة";
                                        }

                                        textView.setCompoundDrawablesWithIntrinsicBounds(null, null, oldDrawable, null);
                                        Toast.makeText(getActivity(), "تمت الأزالة من " + erorr, Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {

                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        String erorr = "";
                                        if (listName=="Favorite_books"){
                                            erorr = "المفضلة";
                                        } else if (listName=="Reading_inprogress") {
                                            erorr = "أقرأه حاليا";
                                        } else if (listName=="Want_to_read") {
                                            erorr = "انوي قراءتة";
                                        } else if (listName=="Read_it") {
                                            erorr = "قرأتة";
                                        }
                                        Toast.makeText(getActivity(), "لم يتم الأزالة من " + erorr, Toast.LENGTH_SHORT).show();
                                    }
                                });
                    } else {
                        userRef.update(listName, FieldValue.arrayUnion(bookTitle))
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        String sucss = "";
                                        if (listName=="Favorite_books"){
                                            sucss = "المفضلة";
                                        } else if (listName=="Reading_inprogress") {
                                            sucss = "أقرأه حاليا";
                                        } else if (listName=="Want_to_read") {
                                            sucss = "انوي قراءتة";
                                        } else if (listName=="Read_it") {
                                            sucss = "قرأتة";
                                        }
                                        textView.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
                                        Toast.makeText(getActivity(), "تم الأضافة الى " + sucss, Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        String sucss = "";
                                        if (listName=="Favorite_books"){
                                            sucss = "المفضلة";
                                        } else if (listName=="Reading_inprogress") {
                                            sucss = "أقرأه حاليا";
                                        } else if (listName=="Want_to_read") {
                                            sucss = "انوي قراءتة";
                                        } else if (listName=="Read_it") {
                                            sucss = "قرأتة";
                                        }
                                        Toast.makeText(getActivity(), "لم يتم الأضافة الى " + sucss, Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w("Firestore", "Error fetching book list", e);
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