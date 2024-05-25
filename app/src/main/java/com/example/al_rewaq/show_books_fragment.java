package com.example.al_rewaq;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class show_books_fragment extends Fragment {

    private static String ARG_CATEGORY;
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
                        List<String> imageUrls = new ArrayList<>();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            String imageUrl = document.getString("Image_URL");
                            if (imageUrl != null) {
                                imageUrls.add(imageUrl);
                            }
                        }
                        displayImages(linearLayout, imageUrls);
                    }
                });
    }

    private void displayImages(LinearLayout linearLayout, List<String> imageUrls) {
        LinearLayout currentRow = null;
        int count = 0;

        for (String url : imageUrls) {
            if (count % 3 == 0) {
                currentRow = new LinearLayout(getContext());
                currentRow.setOrientation(LinearLayout.HORIZONTAL);
                currentRow.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                linearLayout.addView(currentRow);
            }

            ImageView imageView = new ImageView(getContext());
            Picasso.get().load(url).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

            float density = getResources().getDisplayMetrics().density;
            int imageSizeInDp = 130; // حجم الصورة بوحدة dp
            int imageSizeInPixels = (int) (imageSizeInDp * density); // تحويل dp إلى بكسل

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    imageSizeInPixels, imageSizeInPixels);
            layoutParams.setMargins(15, 0, 15, 30); // تعيين الهوامش بين الصور
            imageView.setLayoutParams(layoutParams);

            if (currentRow != null) {
                currentRow.addView(imageView);
            }

            count++;
        }
    }
}
