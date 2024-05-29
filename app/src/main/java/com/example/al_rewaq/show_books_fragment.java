package com.example.al_rewaq;

import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;



public class show_books_fragment extends Fragment {

    private static final String ARG_CATEGORY = "category";
    private FirebaseFirestore db;
    private String category;



    public static show_books_fragment newInstance(String category) {
        show_books_fragment fragment = new show_books_fragment();
        Bundle args = new Bundle();
        args.putString(ARG_CATEGORY, category);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            category = getArguments().getString(ARG_CATEGORY);
        }
        db = FirebaseFirestore.getInstance();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_books_fragment, container, false);
        LinearLayout linearLayout = view.findViewById(R.id.layoutBook);
        fetchBooksByCategory(linearLayout, category);
        return view;
    }

    private void fetchBooksByCategory(LinearLayout linearLayout, String category) {
        db.collection("Book")
                .whereEqualTo("Section", category)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        List<Book> books = new ArrayList<>();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            String imageUrl = document.getString("Image_URL");
                            String bookTitle = document.getString("Book_Name");
                            String bookAuthor = document.getString("Author");
                            String section = document.getString("Section");
                            String year = document.getString("Year");
                            String noPage = document.getString("No_Page");
                            String bookDescription = document.getString("Description");
                            if (imageUrl != null) {
                                books.add(new Book(imageUrl, section, bookAuthor, bookDescription, year, noPage, bookTitle));
                            }
                        }
                        displayCategoryNameAndImages(linearLayout, category, books);
                    }
                });
    }

    private void displayCategoryNameAndImages(LinearLayout linearLayout, String category, List<Book> books) {
        TextView categoryTextView = new TextView(getContext());
        categoryTextView.setText(category);
        categoryTextView.setTextSize(26);
        categoryTextView.setTextColor(Color.WHITE);
        categoryTextView.setGravity(Gravity.CENTER);
        categoryTextView.setPadding(15, 15, 15, 30);
        linearLayout.addView(categoryTextView);

        LinearLayout currentRow = null;

        int count = 0;

        for (Book book : books) {
            if (count % 3 == 0) {
                currentRow = new LinearLayout(getContext());
                currentRow.setOrientation(LinearLayout.HORIZONTAL);
                currentRow.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                linearLayout.addView(currentRow);
            }

            ImageView imageView = new ImageView(getContext());
            Picasso.get().load(book.getImageUrl()).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

            float density = getResources().getDisplayMetrics().density;
            int imageWidthInDp = 130;
            int imageHeightInDp = 200;
            int imageWidthInPixels = (int) (imageWidthInDp * density);
            int imageHeightInPixels = (int) (imageHeightInDp * density);

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    imageWidthInPixels, imageHeightInPixels);
            layoutParams.setMargins(15, 10, 15, 30);
            imageView.setLayoutParams(layoutParams);

            imageView.setOnClickListener(v -> {
                Book_Title_fragment bookTitleFragment = Book_Title_fragment.newInstance(
                        book.getImageUrl(), book.getSection(), book.getAuthor(), book.getDescription(), book.getBookTitle(), book.getnoPage(), book.getYear());
                getActivity().getSupportFragmentManager().beginTransaction().replace(android.R.id.content, bookTitleFragment).commit();
            });

            if (currentRow != null) {
                currentRow.addView(imageView);
            }

            count++;

        }
    }

    static class Book {
        private String imageUrl;
        private String section;
        private String year;
        private String noPage;
        private String bookTitle;
        private String bookAuthor;
        private String bookDescription;

        public Book(String imageUrl, String section, String author, String description, String year, String noPage, String bookTitle) {
            this.imageUrl = imageUrl;
            this.section = section;
            this.bookAuthor = author;
            this.bookDescription = description;
            this.noPage = noPage;
            this.year = year;
            this.bookTitle = bookTitle;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public String getYear() {
            return year;
        }

        public String getnoPage() {
            return noPage;
        }

        public String getBookTitle() {
            return bookTitle;
        }

        public String getSection() {
            return section;
        }

        public String getAuthor() {
            return bookAuthor;
        }

        public String getDescription() {
            return bookDescription;
        }

    }

}
