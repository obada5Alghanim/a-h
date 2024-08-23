package com.example.al_rewaq;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Create ImageView programmatically
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.logo);  // Replace with your image resource
        setContentView(imageView);

        // Create an alpha animation (fade-in effect)
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(4000);  // Duration of the animation in milliseconds

        // Start the animation
        imageView.startAnimation(alphaAnimation);

        // Set an animation listener to start the main activity after the animation ends
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                // Optional: Perform any action when the animation starts
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // Delay the start of the next activity by 2 seconds (same as the animation duration)
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(MainActivity.this, Sign.class);
                        startActivity(intent);
                        finish();  // Close the current activity
                    }
                }, 0);  // Set to 0 if you want the transition immediately after the animation ends
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // Optional: Perform any action if the animation repeats
            }
        });
    }
}