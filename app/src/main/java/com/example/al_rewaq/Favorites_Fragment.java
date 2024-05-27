package com.example.al_rewaq;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.android.gms.tasks.OnSuccessListener;
import java.util.ArrayList;
import java.util.List;

public class Favorites_Fragment extends Fragment {

    private RecyclerView recyclerView;
    private FavoritesAdapter adapter;
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
        adapter = new FavoritesAdapter(favoriteBooks, new FavoritesAdapter.OnDeleteClickListener() {
            @Override
            public void onDeleteClick(int position) {
                removeBookFromFavorites(position);
            }
        });
        recyclerView.setAdapter(adapter);

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
                                favoriteBooks.add(new Book(bookName, imageUrl));
                            }
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
    }

    private void removeBookFromFavorites(int position) {
        Book book = favoriteBooks.get(position);
        DocumentReference userRef = db.collection("users").document(userId);
        userRef.update("Favorite_books", FieldValue.arrayRemove(book.getBookName()))
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        favoriteBooks.remove(position);
                        adapter.notifyItemRemoved(position);
                        reloadFragment();
                    }
                });
    }

    private void reloadFragment() {
        FragmentManager fragmentManager = getFragmentManager();
        if (fragmentManager != null) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.detach(this).attach(this).commit();
        }
    }
}
