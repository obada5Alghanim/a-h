package com.example.al_rewaq;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

public class Interests extends AppCompatActivity {

    Button bt1, bt2, bt3, bt4, bt5, bt6, bt7, bt8, bt9, bt10, bt11, bt12, bt13, bt14, bt15, bt16, bt17, btn1, btn2;
    private ArrayList<String> selectedCategories = new ArrayList<>();
    private Map<Button, String> buttonCategoryMap = new HashMap<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interests);
        bt1 = findViewById(R.id.Button1);
        bt2 = findViewById(R.id.Button2);
        bt3 = findViewById(R.id.Button3);
        bt4 = findViewById(R.id.Button4);
        bt5 = findViewById(R.id.Button5);
        bt6 = findViewById(R.id.Button6);
        bt7 = findViewById(R.id.Button7);
        bt8 = findViewById(R.id.Button8);
        bt9 = findViewById(R.id.Button9);
        bt10 = findViewById(R.id.Button10);
        bt11 = findViewById(R.id.Button11);
        bt12 = findViewById(R.id.Button12);
        bt13 = findViewById(R.id.Button13);
        bt14 = findViewById(R.id.Button14);
        bt15 = findViewById(R.id.Button15);
        bt16 = findViewById(R.id.Button16);
        bt17 = findViewById(R.id.Button17);

        buttonCategoryMap.put(bt1, "روايات دينية");
        buttonCategoryMap.put(bt2, "روايات مغامرات");
        buttonCategoryMap.put(bt3, "روايات رعب");
        buttonCategoryMap.put(bt4, "روايات بوليسية");
        buttonCategoryMap.put(bt5, "أدب انجليزي");
        buttonCategoryMap.put(bt6, "أدب عربي");
        buttonCategoryMap.put(bt7, "الحضارات القديمة");
        buttonCategoryMap.put(bt8, "أدب روسي");
        buttonCategoryMap.put(bt9, "الشرق الأوسط");
        buttonCategoryMap.put(bt10, "تاريخ أوروبا");
        buttonCategoryMap.put(bt11, "العلوم الشرعية");
        buttonCategoryMap.put(bt12, "السيرة النبوية");
        buttonCategoryMap.put(bt13, "الرسل والأنبياء");
        buttonCategoryMap.put(bt14, "لغات البرمجة");
        buttonCategoryMap.put(bt15, "كمبيوتر وانترنت");
        buttonCategoryMap.put(bt16, "سيكولوجيا الجرائم");
        buttonCategoryMap.put(bt17, "علم النفس التربوي");


        for (Button button : buttonCategoryMap.keySet()) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    toggleSelection(button);
                }
            });
        }


        btn1 = findViewById(R.id.selected_btn);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedCategories.isEmpty()) {
                    Toast.makeText(Interests.this, "يرجى اختيار تصنيف أو النقر على تخطي", Toast.LENGTH_SHORT).show();
                } else {
                    saveSelections();
                    startActivity(new Intent(Interests.this, menu_main.class));
                }
            }
        });


        btn2 = findViewById(R.id.cancel_btn);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadRandomCategoriesAndProceed();


            }
        });

    }

    private void toggleSelection(Button button) {
        String category = buttonCategoryMap.get(button);
        if (selectedCategories.contains(category)) {
            selectedCategories.remove(category);
            button.setBackgroundColor(Color.BLACK);
            button.setTextColor(Color.WHITE);
        } else {
            selectedCategories.add(category);
            button.setBackgroundColor(Color.WHITE);
            button.setTextColor(Color.BLACK);
        }
    }

    private void saveSelections() {
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("users").document(userId)
                .update("selectedCategories", selectedCategories)
                .addOnSuccessListener(aVoid -> {
                })
                .addOnFailureListener(e -> {
                });
    }


    private void loadRandomCategoriesAndProceed() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Book").get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    List<String> allCategories = new ArrayList<>();
                    for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                        allCategories.add(document.getId());
                    }
                    Collections.shuffle(allCategories);
                    List<String> randomCategories = allCategories.subList(1, 3);

                    String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    db.collection("users").document(userId)
                            .update("selectedCategories", randomCategories)
                            .addOnSuccessListener(aVoid -> {

                                Intent intent = new Intent(Interests.this, menu_main.class);
                                startActivity(intent);
                                finish();
                            })
                            .addOnFailureListener(e -> {
                                Toast.makeText(Interests.this, "حدث خطأ أثناء التخطي", Toast.LENGTH_SHORT).show();
                            });
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(Interests.this, "حدث خطأ أثناء جلب التصنيفات العشوائية", Toast.LENGTH_SHORT).show();
                });
    }


}