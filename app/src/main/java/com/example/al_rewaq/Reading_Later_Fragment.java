package com.example.al_rewaq;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
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
import com.google.android.gms.tasks.OnFailureListener;
import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.List;

public class Reading_Later_Fragment extends Fragment {

    private RecyclerView recyclerView;
    private FavoritesAdapter adapter;
    private List<Book> wantToRead;
    private FirebaseFirestore db;
    private FirebaseAuth auth;
    private String userId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reading_later_, container, false);

        recyclerView = view.findViewById(R.id.recycler_view_reading_later);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));

        wantToRead = new ArrayList<>();
        adapter = new FavoritesAdapter(wantToRead, new FavoritesAdapter.OnDeleteClickListener() {
            @Override
            public void onDeleteClick(int position) {
                removeBookFromReadingInProgress(position);
            }
        });
        recyclerView.setAdapter(adapter);

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        userId = auth.getCurrentUser().getUid();

        loadReadingInProgressBooks();

        return view;
    }

    private void loadReadingInProgressBooks() {
        DocumentReference userRef = db.collection("users").document(userId);
        userRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    List<String> readingInProgressBooksList = (List<String>) documentSnapshot.get("Want_to_read");
                    if (readingInProgressBooksList != null) {
                        for (String bookTitle : readingInProgressBooksList) {
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
                                wantToRead.add(new Book(bookName, imageUrl));
                            }
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
    }

    private void removeBookFromReadingInProgress(int position) {
        Book book = wantToRead.get(position);
        DocumentReference userRef = db.collection("users").document(userId);
        userRef.update("Want_to_read", FieldValue.arrayRemove(book.getBookName()))
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        wantToRead.remove(position);
                        adapter.notifyItemRemoved(position);
                        reloadFragment();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Handle any errors here
                    }
                });
    }

    private void reloadFragment() {
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.detach(this).attach(this).commit();
    }
}
