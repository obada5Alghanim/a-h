package com.example.al_rewaq;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.os.Handler;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;



public class reading_speed_test_new extends Fragment {

    FirebaseFirestore db;
    private TextView RST_textView, timerTxt , resultTxt , degree , background , nameOfParagraph;
    private Button RST_btn, RST_btn_end ;
    private RelativeLayout relativeLayoutResulte;
    private ImageButton closeRuslet;
    private long startTime;
    private boolean isRunning = false;

    private Handler handler = new Handler();
    private Runnable timerRunnable = new Runnable() {

        @Override
        public void run() {
            long elapsedTime = SystemClock.elapsedRealtime() - startTime;
            int minutes = (int) (elapsedTime / 60000);
            int seconds = (int) (elapsedTime / 1000) % 60;
            String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
            timerTxt.setText(timeFormatted);
            handler.postDelayed(this, 1000);
        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_reading_speed_test_new, container, false);
        RST_textView = view.findViewById(R.id.RSTTXT);
        RST_btn = view.findViewById(R.id.start_btn);
        RST_btn_end = view.findViewById(R.id.end_btn);
        timerTxt = view.findViewById(R.id.timerText);
        resultTxt = view.findViewById(R.id.textView7);
        relativeLayoutResulte = view.findViewById(R.id.showTheResult);
        degree =view.findViewById(R.id.textView8);
        background =view.findViewById(R.id.textView9);
        nameOfParagraph = view.findViewById(R.id.textView2);
        closeRuslet = view.findViewById(R.id.imageButton3);
        final ScrollView scrollView  = view.findViewById(R.id.scrollView2);
        db = FirebaseFirestore.getInstance();



RST_btn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        db.collection("Reading_Speed_Test").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    List<DocumentSnapshot> documents = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        documents.add(document);
                        if (!documents.isEmpty()) {
                            Random rand = new Random();
                            int randomIndex = rand.nextInt(documents.size());
                            DocumentSnapshot randomBook = documents.get(randomIndex);

                            // الحصول على بيانات الكتاب العشوائي
                            String Title = randomBook.getString("PName");
                            String Paragraph = randomBook.getString("paragraph");

                            RST_textView.setText(Paragraph);
                            nameOfParagraph.setText(Title);

                        } else {
                            System.out.println("No books found in this category.");
                        }
                    }
                } else {
                    System.out.println("Error getting documents: " + task.getException());
                }

            }
        });

        scrollView.scrollTo(0,0);

        startTest();

        RST_btn_end.setVisibility(View.VISIBLE);
        RST_btn.setVisibility(View.GONE);

    }
});


RST_btn_end.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
       finishTest();
       background.setVisibility(View.VISIBLE);
       relativeLayoutResulte.setVisibility(View.VISIBLE);
        RST_btn_end.setVisibility(View.GONE);
        RST_btn.setVisibility(View.VISIBLE);
        scrollView.scrollTo(0,0);

    }
});

background.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        background.setVisibility(View.GONE);
        relativeLayoutResulte.setVisibility(View.GONE);
    }
});
closeRuslet.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        background.setVisibility(View.GONE);
        relativeLayoutResulte.setVisibility(View.GONE);
    }
});



        return view;
    }



    private void startTest() {
        if (!isRunning) {
            startTime = SystemClock.elapsedRealtime();
            handler.postDelayed(timerRunnable, 0); // Start the timer
            isRunning = true;
        }
    }

   private void finishTest() {
    String passage = RST_textView.getText().toString();
       if (isRunning) {
           handler.removeCallbacks(timerRunnable); // Stop the timer
           isRunning = false;
           long endTime = SystemClock.elapsedRealtime();
           long elapsedTime = endTime - startTime;

           // Calculate reading speed
           int wordCount = passage.split("\\s+").length; // Splitting by spaces to count words
           double minutes = elapsedTime / 60000.0; // Converting milliseconds to minutes
           double speed = wordCount / minutes;

           // Display reading speed
           String result = String.format("%.0f كلمة/دقيقة ", speed);
           if (0 <= speed && speed<=150){
               resultTxt.setText(result);
               degree.setText("المستوى البطيء");
           } else if (speed >150 && speed<=250) {
               resultTxt.setText(result);
               degree.setText("المستوى المتوسط");

           } else if (speed >250 && speed<=300) {
               resultTxt.setText(result);
               degree.setText("المستوى المتقدم");

           } else if (speed >300 && speed<=400) {
               resultTxt.setText(result);
               degree.setText("مستوى متقدم جدا ");

           } else if (speed >400 ) {
               resultTxt.setText(result);
               degree.setText("مستوى الخارق  ");

           }else {
               resultTxt.setText(result);
           }
       }
    }

}
