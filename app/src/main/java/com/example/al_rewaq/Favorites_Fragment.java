package com.example.al_rewaq;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Favorites_Fragment extends Fragment {

    private RecyclerView recyclerView;
    private List<Book> favoriteBooks;
    private FirebaseFirestore db;
    private FirebaseAuth auth;
    private String userId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites_, container, false);

        recyclerView = view.findViewById(R.id.recycler_view_favorites);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));

        favoriteBooks = new ArrayList<>();
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        userId = auth.getCurrentUser().getUid();

        loadFavoriteBooks();

        return view;
    }

    private void loadFavoriteBooks() {
        DocumentReference userRef = db.collection("users").document(userId);
        userRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    List<String> favoriteBooksList = (List<String>) documentSnapshot.get("Favorite_books");
                    if (favoriteBooksList != null) {
                        for (String bookTitle : favoriteBooksList) {
                            fetchBookDetails(bookTitle);
                        }
                    }
                }
            }
        });
    }

    private void fetchBookDetails(String bookTitle) {
        db.collection("Book").whereEqualTo("Book_Name", bookTitle)
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (!queryDocumentSnapshots.isEmpty()) {
                            for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                                String bookName = documentSnapshot.getString("Book_Name");
                                String imageUrl = documentSnapshot.getString("Image_URL");
                                String section = documentSnapshot.getString("Section");
                                String author = documentSnapshot.getString("Author");
                                String description = documentSnapshot.getString("Description");
                                String year = documentSnapshot.getString("Year");
                                String noPage = documentSnapshot.getString("No_Page");

                                favoriteBooks.add(new Book(bookName, imageUrl, section, author, description, year, noPage));
                            }
                            updateRecyclerView();
                        }
                    }
                });
    }

    private void updateRecyclerView() {
        recyclerView.setAdapter(new RecyclerView.Adapter<RecyclerView.ViewHolder>() {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favorite_book, parent, false);
                return new RecyclerView.ViewHolder(view) {};
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
                Book book = favoriteBooks.get(position);
                ImageView bookImage = holder.itemView.findViewById(R.id.book_image);
                ImageButton deleteButton = holder.itemView.findViewById(R.id.delete_button);
                Picasso.get().load(book.getImageUrl()).into(bookImage);

                bookImage.setOnClickListener(v -> {
                    Book_Title_fragment bookTitleFragment = Book_Title_fragment.newInstance(
                            book.getImageUrl(), book.getSection(), book.getAuthor(), book.getDescription(), book.getBookName(), book.getnoPage(), book.getYear());
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(android.R.id.content, bookTitleFragment)
                            .commit();
                });

                deleteButton.setOnClickListener(v -> {
                    removeBookFromFavorites(position);
                });
            }

            @Override
            public int getItemCount() {
                return favoriteBooks.size();
            }
        });
    }

    private void removeBookFromFavorites(int position) {
        Book book = favoriteBooks.get(position);
        DocumentReference userRef = db.collection("users").document(userId);
        userRef.update("Favorite_books", FieldValue.arrayRemove(book.getBookName()))
                .addOnSuccessListener(aVoid -> {
                    favoriteBooks.remove(position);
                    updateRecyclerView();
                })
                .addOnFailureListener(e -> {
                    // Handle any errors here
                });
    }

    static class Book {
        private String bookName;
        private String imageUrl;
        private String section;
        private String author;
        private String description;
        private String year;
        private String noPage;

        public Book(String bookName, String imageUrl, String section, String author, String description, String year, String noPage) {
            this.bookName = bookName;
            this.imageUrl = imageUrl;
            this.section = section;
            this.author = author;
            this.description = description;
            this.year = year;
            this.noPage = noPage;
        }

        public String getBookName() {
            return bookName;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public String getSection() {
            return section;
        }

        public String getAuthor() {
            return author;
        }

        public String getDescription() {
            return description;
        }

        public String getYear() {
            return year;
        }

        public String getnoPage() {
            return noPage;
        }
    }
}
