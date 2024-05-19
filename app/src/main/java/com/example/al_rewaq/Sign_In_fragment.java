package com.example.al_rewaq;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Sign_In_fragment extends Fragment {

    public  Sign_In_fragment() {
        // Required empty public constructor

    }
    private FirebaseAuth mAuth;
    EditText usernameIN,passwordIN;
    TextView txt;
    Button btn;
    boolean passwordVisiable;

    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME ="remberMeForAlrewaq";
    private static final String KEY_NAME ="USERNAME";
    private static final String KEY_PASSWORD ="PASSWORDUSER";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_sign__in_fragment, container, false);

        //firebase Auth
        mAuth = FirebaseAuth.getInstance();

        usernameIN = rootView.findViewById(R.id.Edt_userName_singIn);
        passwordIN = rootView.findViewById(R.id.Edt_password_singIn);
        // Find the view by its ID
        txt = rootView.findViewById(R.id.singIn_to_singUp_click);
        btn = rootView.findViewById(R.id.signIn_button);
        sharedPreferences = getActivity().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        // Set click listener or perform any other operation on the view
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Sign_Up_fragment SUSe = new Sign_Up_fragment();
                ft.replace(android.R.id.content,SUSe);
                ft.commit();
            }
        });

        passwordIN.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int Right=2;
                if (event.getAction()==MotionEvent.ACTION_UP){
                    if(event.getRawX()>= passwordIN.getRight()-passwordIN.getCompoundDrawables()[Right].getBounds().width()) {
                        int selection = passwordIN.getSelectionEnd();
                        if (passwordVisiable) {
                            //set drawable Image
                            passwordIN.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.outline_lock_24, 0, R.drawable.hide, 0);
                            passwordIN.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordVisiable = false;
                        } else {
                            passwordIN.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.outline_lock_24, 0, R.drawable.clarity_eye_show_line, 0);
                            passwordIN.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordVisiable = true;
                        }
                        passwordIN.setSelection(selection);
                        return true;
                    }
                }

                return false;
            }
        });


        //when open it checked shared prefernce data avilable or not
        String checkedPref = sharedPreferences.getString(KEY_NAME,null);
        if (checkedPref != null){
            Intent intent = new Intent(getActivity(),menu_main.class);
            startActivity(intent);

        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = usernameIN.getText().toString();
                String password = passwordIN.getText().toString();


                if(!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    if (!password.isEmpty()){

                        mAuth.signInWithEmailAndPassword(email, password)
                                .addOnCompleteListener(requireActivity(), task -> {
                                    if (task.isSuccessful()) {
                                        // تم تسجيل الدخول بنجاح
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        editor.putString(KEY_NAME,usernameIN.getText().toString());
                                        editor.putString(KEY_PASSWORD,passwordIN.getText().toString());
                                        editor.apply();
                                        Intent intent = new Intent(getActivity(),menu_main.class);
                                        startActivity(intent);

                                        // إظهار رسالة تأكيد للمستخدم
                                    } else {
                                        // فشلت عملية تسجيل الدخول
                                        // عرض رسالة خطأ للمستخدم
                                    }

                         }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getActivity(), "LogIN failed", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }else {
                        passwordIN.setError("password cannot be empty");
                    }
                } else if (email.isEmpty()) {
                    usernameIN.setError("user Name cannto be empty");

                }else {
                    usernameIN.setError("enter valid userName");
                }


            }
        });









        return rootView;




    }
}








/* SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString(KEY_NAME,usernameIN.getText().toString());
                                editor.putString(KEY_PASSWORD,passwordIN.getText().toString());
                                editor.apply();
                                Intent intent = new Intent(getActivity(),menu_main.class);
                                startActivity(intent);*/



/* Intent intent = new Intent(getActivity(),menu_main.class);
                startActivity(intent);*/





 /*        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_sing__in_fragment, container, false);

        EditText passwordEditText = rootView.findViewById(R.id.Edt_password_singIn);
        ImageView eyeImageView = rootView.findViewById(R.id.eyeImageView);

        // Set OnClickListener on eye icon ImageView
        eyeImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toggle password visibility
                if (passwordEditText.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                    // If password is visible, hide it
                    passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    eyeImageView.setImageResource(R.drawable.ic_eye_closed);
                } else {
                    // If password is hidden, show it
                    passwordEditText.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    eyeImageView.setImageResource(R.drawable.ic_eye_open);
                }

                // Move cursor to the end of the input to maintain cursor position
                passwordEditText.setSelection(passwordEditText.getText().length());
            }
        });

        return rootView;*/