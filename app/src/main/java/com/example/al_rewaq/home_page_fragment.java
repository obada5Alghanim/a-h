package com.example.al_rewaq;

import android.graphics.Color;
import android.graphics.Outline;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;
import java.util.List;



public class home_page_fragment extends Fragment {

    private LinearLayout categoriesContainer;
    private Handler handler = new Handler();
    private int scrollSpeed = 12000; // سرعة التمرير بالمللي ثانية



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

                            loadBooks("الرسل والأنبياء");
                            loadBooks("كمبيوتر وانترنت");
                            loadBooks("أدب انجليزي");
                            loadBooks("الحضارات القديمة");
                            loadBooks("روايات بوليسية");
                            loadBooks("علم النفس التربوي");

                        }
                    }
                });
    }


    private void loadBooks(String category) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Task<QuerySnapshot> querySnapshotTask = db.collection("Book").whereEqualTo("Section", category).get();

        db.collection("Book").whereEqualTo("Section", category).get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    // إنشاء نص لاسم التصنيف

                    TextView categoryTitle = new TextView(getContext());
                    categoryTitle.setText(category);
                    categoryTitle.setTextSize(22);
                    categoryTitle.setPadding(16, 16, 16, 16);
                    categoryTitle.setTextColor(Color.WHITE);


                    // إضافة النص إلى container الرئيسي
                    categoriesContainer.addView(categoryTitle);

                    // إنشاء شريط أفقي للكتب
                    HorizontalScrollView scrollView = new HorizontalScrollView(getContext());
                    LinearLayout booksContainer = new LinearLayout(getContext());
                    booksContainer.setOrientation(LinearLayout.HORIZONTAL);
                    scrollView.setHorizontalScrollBarEnabled(false);


                    for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                        String imageUrl = document.getString("Image_URL");
                        ImageView imageView = new ImageView(getContext());

                        Picasso.get().load(imageUrl).resize(330, 500).into(imageView);

                        // إعداد الحواف الدائرية للصورة
                        imageView.setClipToOutline(true);
                        imageView.setOutlineProvider(new ViewOutlineProvider() {
                            @Override
                            public void getOutline(View view, Outline outline) {
                                outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), 5);
                            }
                        });

                        // إعداد حواف للصورة
                        LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(
                                300,
                                500
                        );
                        imageParams.setMargins(16, 0, 16, 0); // تعديل المسافة بين الصور
                        imageView.setLayoutParams(imageParams);
                        imageView.setPadding(0, 0, 0, 0);


                        imageView.setOnClickListener(v -> {

                            // جمع معلومات الكتاب من المستند
                            String bookTitle = document.getString("Book_Name");
                            String bookAuthor = document.getString("Author");
                            String section = document.getString("Section");
                            Long  year  = document.getLong("Year");
                            Long  noPage  = document.getLong("No_Page");
                            String bookDescription = document.getString("Description");

                            // نقل البيانات إلى Fragment آخر
                            Bundle bundle = new Bundle();
                            bundle.putString("Book_Name", bookTitle);
                            bundle.putString("Author", bookAuthor);
                            bundle.putString("Section", section);
                            bundle.putLong("years", year);
                            bundle.putLong("NoPage", noPage);
                            bundle.putString("Description", bookDescription);
                            bundle.putString("Image_URL", imageUrl);

                            Book_Title_fragment bookTitleFragment = new Book_Title_fragment();
                            bookTitleFragment.setArguments(bundle);

                           getActivity().getSupportFragmentManager().beginTransaction().replace(android.R.id.content,bookTitleFragment ).commit();

                        });

                        booksContainer.addView(imageView);
                    }

                    scrollView.addView(booksContainer);
                    categoriesContainer.addView(scrollView);

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            int currentScrollX = scrollView.getScrollX();

                            if (currentScrollX <= 0) {

                                scrollSpeed = Math.abs(scrollSpeed);
                            } else if (currentScrollX >= (booksContainer.getWidth() - scrollView.getWidth())) {
                                scrollSpeed = -Math.abs(scrollSpeed);
                            }

                            scrollView.smoothScrollBy(scrollSpeed / 200, 0);

                            handler.postDelayed(this, 12000);
                        }
                    }, scrollSpeed);

                });
    }

}
