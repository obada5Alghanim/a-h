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
import androidx.recyclerview.widget.ItemTouchHelper;
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

public class Reading_Later_Fragment extends Fragment {

    private RecyclerView recyclerView;
    private List<Book> readingLaterBooks;
    private FirebaseFirestore db;
    private FirebaseAuth auth;
    private String userId;
    private RecyclerView.Adapter<RecyclerView.ViewHolder> adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reading_later_, container, false);

        recyclerView = view.findViewById(R.id.recycler_view_reading_later);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));

        readingLaterBooks = new ArrayList<>();
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        userId = auth.getCurrentUser().getUid();

        loadReadingLaterBooks();

        return view;
    }

    private void loadReadingLaterBooks() {
        DocumentReference userRef = db.collection("users").document(userId);
        userRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    List<String> readingLaterList = (List<String>) documentSnapshot.get("Want_to_read");
                    if (readingLaterList != null) {
                        for (String bookTitle : readingLaterList) {
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
                                Long year = documentSnapshot.getLong("Year");
                                Long noPage = documentSnapshot.getLong("No_Page");

                                readingLaterBooks.add(new Book(bookName, imageUrl, section, author, description, year, noPage));
                            }
                            updateRecyclerView();
                        }
                    }
                });
    }

    private void updateRecyclerView() {
        adapter = new RecyclerView.Adapter<RecyclerView.ViewHolder>() {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favorite_book, parent, false);
                return new RecyclerView.ViewHolder(view) {};
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
                Book book = readingLaterBooks.get(position);
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
                    removeBookFromReadingLater(holder.getAdapterPosition());
                });
            }

            @Override
            public int getItemCount() {
                return readingLaterBooks.size();
            }
        };
        recyclerView.setAdapter(adapter);

        // Attach ItemTouchHelper to RecyclerView
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.UP) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                removeBookFromReadingLater(viewHolder.getAdapterPosition());
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    private void removeBookFromReadingLater(int position) {
        Book book = readingLaterBooks.get(position);
        DocumentReference userRef = db.collection("users").document(userId);
        userRef.update("Want_to_read", FieldValue.arrayRemove(book.getBookName()))
                .addOnSuccessListener(aVoid -> {
                    readingLaterBooks.remove(position);
                    adapter.notifyItemRemoved(position);
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
        private Long year;
        private Long noPage;

        public Book(String bookName, String imageUrl, String section, String author, String description, Long year, Long noPage) {
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

        public Long getYear() {
            return year;
        }

        public Long getnoPage() {
            return noPage;
        }
    }
}
