package com.example.al_rewaq;

import android.graphics.Outline;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class home_page_fragment extends Fragment {

    private LinearLayout categoriesContainer;
    private Handler handler = new Handler();
    private int scrollSpeed = 100; // سرعة التمرير بالمللي ثانية



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_page_fragment, container, false);

        categoriesContainer = view.findViewById(R.id.categoriesContainer);

        loadUserSelections();

        return view;
    }

    private void loadUserSelections() {
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("users").document(userId).get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        List<String> selectedCategories = (List<String>) documentSnapshot.get("selectedCategories");
                        if (selectedCategories != null && !selectedCategories.isEmpty()) {
                            for (String category : selectedCategories) {
                                loadBooks(category);
                            }
                        } else {
                            loadRandomCategories();
                        }
                    }
                });
    }

    private void loadBooks(String category) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Book").whereEqualTo("Section", category).get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    // إنشاء نص لاسم التصنيف
                    TextView categoryTitle = new TextView(getContext());
                    categoryTitle.setText(category);
                    categoryTitle.setTextSize(20);
                    categoryTitle.setPadding(16, 16, 16, 16);

                    // إضافة النص إلى container الرئيسي
                    categoriesContainer.addView(categoryTitle);

                    // إنشاء شريط أفقي للكتب
                    HorizontalScrollView scrollView = new HorizontalScrollView(getContext());
                    LinearLayout booksContainer = new LinearLayout(getContext());
                    booksContainer.setOrientation(LinearLayout.HORIZONTAL);

                    for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                        String imageUrl = document.getString("Image_URL");
                        ImageView imageView = new ImageView(getContext());

                        // تحميل الصورة باستخدام Picasso
                        Picasso.get().load(imageUrl).into(imageView);

                        // إعداد الحواف الدائرية للصورة
                        imageView.setClipToOutline(true);
                        imageView.setOutlineProvider(new ViewOutlineProvider() {
                            @Override
                            public void getOutline(View view, Outline outline) {
                                outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), 5);
                            }
                        });

                        // إعداد التخطيط للصورة
                        LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(
                                500,
                                500
                        );
                        imageParams.setMargins(16, 0, 16, 0); // تعديل المسافة بين الصور
                        imageView.setLayoutParams(imageParams);
                        imageView.setPadding(0, 0, 0, 0);

                        // إضافة حدث الضغط على الصورة لنقل معلومات الكتاب إلى Fragment آخر
                        imageView.setOnClickListener(v -> {
                            // جمع معلومات الكتاب من المستند
                            String bookTitle = document.getString("Book_Name");
                            String bookAuthor = document.getString("Author");
                            String section = document.getString("Section");
                            String  year  = document.getString("Year");
                            String  noPage  = document.getString("No_Page");
                            String bookDescription = document.getString("Description");

                            // نقل البيانات إلى Fragment آخر
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
                           getActivity().getSupportFragmentManager().beginTransaction().replace(android.R.id.content,bookTitleFragment ).commit();

                        });

                        booksContainer.addView(imageView);
                    }

                    scrollView.addView(booksContainer);
                    categoriesContainer.addView(scrollView);

                    // إعداد التمرير الأفقي التلقائي
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            scrollView.smoothScrollBy(-1, 0);
                            handler.postDelayed(this, scrollSpeed);
                        }
                    }, scrollSpeed);

                });

    }



    private void loadRandomCategories() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Book").get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    List<String> allCategories = new ArrayList<>();
                    for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                        allCategories.add(document.getId());
                    }
                    Collections.shuffle(allCategories);
                    List<String> randomCategories = allCategories.subList(0, 3);
                    for (String category : randomCategories) {
                        loadBooks(category);
                    }
                });
    }
}

/* TextView txt1 = view.findViewById(R.id.bookSection);
    ImageView img1 = view.findViewById(R.id.imageView1);
    ImageView img2 = view.findViewById(R.id.imageView2);
    ImageView img3 = view.findViewById(R.id.imageView3);
    ImageView img4 = view.findViewById(R.id.imageView4);
    ImageView img5 = view.findViewById(R.id.imageView5);
        img1.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
        Book_Title_fragment bookTitleFragment = new Book_Title_fragment();
        getActivity().getSupportFragmentManager().beginTransaction().replace(android.R.id.content,bookTitleFragment).commit();
        }
        });

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("Book").document("TC0001B").get().addOnSuccessListener(documentSnapshot -> {
        String img_1 = documentSnapshot.getString("Image_URL");
        Picasso.get().load(img_1).into(img1);

        String txt_1 = documentSnapshot.getString("Section");
        txt1.setText(txt_1);

        })
        .addOnFailureListener(e -> {
        // فشل في استرجاع بيانات المستخدم
        });

        db.collection("Book").document("TC0002B").get().addOnSuccessListener(documentSnapshot -> {
        String img_2 = documentSnapshot.getString("Image_URL");
        Picasso.get().load(img_2).into(img2);
        })
        .addOnFailureListener(e -> {
        // فشل في استرجاع بيانات المستخدم
        });

        db.collection("Book").document("TC0003B").get().addOnSuccessListener(documentSnapshot -> {
        String img_3 = documentSnapshot.getString("Image_URL");
        Picasso.get().load(img_3).into(img3);
        })
        .addOnFailureListener(e -> {
        // فشل في استرجاع بيانات المستخدم
        });

        db.collection("Book").document("TC0004B").get().addOnSuccessListener(documentSnapshot -> {
        String img_4 = documentSnapshot.getString("Image_URL");
        Picasso.get().load(img_4).into(img4);
        })
        .addOnFailureListener(e -> {
        // فشل في استرجاع بيانات المستخدم
        });

        db.collection("Book").document("TC0005B").get().addOnSuccessListener(documentSnapshot -> {
        String img_5 = documentSnapshot.getString("Image_URL");
        Picasso.get().load(img_5).into(img5);
        })
        .addOnFailureListener(e -> {
        // فشل في استرجاع بيانات المستخدم
        });

        return view;
*/