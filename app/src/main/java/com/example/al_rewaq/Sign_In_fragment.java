package com.example.al_rewaq;

import android.annotation.SuppressLint;
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
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
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



public class Sign_In_fragment extends Fragment {

    public  Sign_In_fragment() {

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
    private EncryptionUtil encryptionUtil;



    @SuppressLint("ClickableViewAccessibility")

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_sign__in_fragment, container, false);

        try {
            encryptionUtil = new EncryptionUtil(); // إذا كنت ترغب في توليد مفتاح جديد
        } catch (Exception e) {
            Log.e("EncryptionError", "Error initializing EncryptionUtil: " + e.getMessage());
        }

        mAuth = FirebaseAuth.getInstance();
        usernameIN = rootView.findViewById(R.id.Edt_userName_singIn);
        passwordIN = rootView.findViewById(R.id.Edt_password_singIn);
        txt = rootView.findViewById(R.id.singIn_to_singUp_click);
        btn = rootView.findViewById(R.id.signIn_button);
        sharedPreferences = getActivity().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);


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


                if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    if (!password.isEmpty()) {
                        try {


                            String encryptedEmail = encryptionUtil.encrypt(email);
                            String encryptedPassword = encryptionUtil.encrypt(password);

                            mAuth.signInWithEmailAndPassword(email, password)
                                    .addOnCompleteListener(requireActivity(), task -> {
                                        if (task.isSuccessful()) {

                                            FirebaseUser user = mAuth.getCurrentUser();
                                            SharedPreferences.Editor editor = sharedPreferences.edit();
                                            editor.putString(KEY_NAME, encryptedEmail);
                                            editor.putString(KEY_PASSWORD, encryptedPassword);
                                            editor.apply();
                                            Intent intent = new Intent(getActivity(), menu_main.class);
                                            startActivity(intent);

                                        }

                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(getActivity(), "LogIN failed", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        }
                        catch (Exception e) {
                            Log.e("EncryptionError", "Error encrypting data: " + e.getMessage());
                        }
                        }else{
                            passwordIN.setError("password cannot be empty");
                        }
                    } else if (email.isEmpty()) {
                        usernameIN.setError("user Name cannto be empty");

                    } else {
                        usernameIN.setError("enter valid userName");
                    }

                }

        });



        return rootView;
    }

}
