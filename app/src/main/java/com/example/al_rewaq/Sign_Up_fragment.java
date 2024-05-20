package com.example.al_rewaq;

import static android.content.ContentValues.TAG;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;



import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Sign_Up_fragment extends Fragment {

   public Sign_Up_fragment(){

   }

    private FirebaseAuth mAuth;

    private EditText userName, password , FirstName, LastName ,confirmPassword;
    TextView txt,Gender , Day1 , month1 , year1,maleTextView,femaleTextView;
    Button btn;

    boolean passwordVisiable;
    DatePickerDialog.OnDateSetListener onDateSetListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_sign__up_fragment, container, false);

        //
        mAuth = FirebaseAuth.getInstance();


        userName = rootView.findViewById(R.id.Edt_signUP_UserName);
        password = rootView.findViewById(R.id.Edt_signUp_password);
        FirstName = rootView.findViewById(R.id.Edt_signUp_firstName);
        LastName = rootView.findViewById(R.id.Edt_signUp_lastName);
        Gender = rootView.findViewById(R.id.Edt_signUP_gender);
        maleTextView = rootView.findViewById(R.id.male_genderText);
        femaleTextView = rootView.findViewById(R.id.female_genderText);
        Day1 = rootView.findViewById(R.id.DOB1);
        month1 = rootView.findViewById(R.id.DOB2);
        year1 = rootView.findViewById(R.id.DOB3);
        confirmPassword = rootView.findViewById(R.id.confirmPassword);
        // Find the view by its ID
        txt = rootView.findViewById(R.id.signUP_to_sing_click);
        btn = rootView.findViewById(R.id.signUp_button);


        // Set click listener or perform any other operation on the view
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Sign_In_fragment SUF = new Sign_In_fragment();
                ft.replace(android.R.id.content, SUF);
                ft.commit();
            }
        });
        Gender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                maleTextView.setVisibility(View.VISIBLE);
                femaleTextView.setVisibility(View.VISIBLE);
                maleTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Gender.setText("Male");
                        maleTextView.setVisibility(View.GONE);
                        femaleTextView.setVisibility(View.GONE);

                    }
                });
                femaleTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Gender.setText("Female");
                        maleTextView.setVisibility(View.GONE);
                        femaleTextView.setVisibility(View.GONE);

                    }
                });

            }
        });

        Day1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year  = calendar.get(Calendar.YEAR);
                int month  = calendar.get(Calendar.MONTH);
                int day  = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(requireContext(),
                        android.R.style.Theme_Holo_Light_Dialog,onDateSetListener,year,month,day);

                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();

                onDateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Day1.setText(String.valueOf(dayOfMonth));
                        month1.setText(String.valueOf(month));
                        year1.setText(String.valueOf(year));
                    }
                };


            }
        });

        month1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year  = calendar.get(Calendar.YEAR);
                int month  = calendar.get(Calendar.MONTH);
                int day  = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(requireContext(),
                        android.R.style.Theme_Holo_Light_Dialog,onDateSetListener,year,month,day);

                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();

                onDateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        Day1.setText(String.valueOf(dayOfMonth));
                        month1.setText(String.valueOf(month));
                        year1.setText(String.valueOf(year));
                    }
                };


            }
        });

        year1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year  = calendar.get(Calendar.YEAR);
                int month  = calendar.get(Calendar.MONTH);
                int day  = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(requireContext(),
                        android.R.style.Theme_Holo_Light_Dialog,onDateSetListener,year,month,day);

                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();

                onDateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Day1.setText(String.valueOf(dayOfMonth));
                        month1.setText(String.valueOf(month));
                        year1.setText(String.valueOf(year));
                    }
                };


            }
        });

        password.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int Right=2;
                if (event.getAction()==MotionEvent.ACTION_UP){
                    if(event.getRawX()>= password.getRight()-password.getCompoundDrawables()[Right].getBounds().width()) {
                        int selection = password.getSelectionEnd();
                        if (passwordVisiable) {
                            //set drawable Image
                            password.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.outline_lock_24, 0, R.drawable.hide, 0);
                            password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordVisiable = false;
                        } else {
                            password.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.outline_lock_24, 0, R.drawable.clarity_eye_show_line, 0);
                            password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordVisiable = true;
                        }
                        password.setSelection(selection);
                        return true;
                    }
                }

                return false;
            }
        });

        confirmPassword.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int Right=2;
                if (event.getAction()==MotionEvent.ACTION_UP){
                    if(event.getRawX()>= confirmPassword.getRight()-confirmPassword.getCompoundDrawables()[Right].getBounds().width()) {
                        int selection = confirmPassword.getSelectionEnd();
                        if (passwordVisiable) {
                            //set drawable Image
                            confirmPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.outline_lock_24, 0, R.drawable.hide, 0);
                            confirmPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordVisiable = false;
                        } else {
                            confirmPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.outline_lock_24, 0, R.drawable.clarity_eye_show_line, 0);
                            confirmPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordVisiable = true;
                        }
                        confirmPassword.setSelection(selection);
                        return true;
                    }
                }

                return false;
            }
        });





        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = userName.getText().toString().trim();
                final String pass = password.getText().toString().trim();
                // we need to make password and confirm password  same
                final String ConfirmPassword = confirmPassword.getText().toString().trim();
                final String firstName = FirstName.getText().toString();
                final String lastName = LastName.getText().toString();
                final String gender  = Gender.getText().toString();
                //the Day will not be empty we should write a code to it later
                final String day  = Day1.getText().toString();
                //the Month will not be empty we should write a code to it later
                final String month  = month1.getText().toString();
                //the year will not be empty we should write a code to it later
                final String year  = year1.getText().toString();


                if (email.isEmpty()) {
                    userName.setError("User name cannot be empty ");
                }
                if (pass.isEmpty()) {
                    password.setError("password cannot be empty ");
                }if (firstName.isEmpty()){
                    FirstName.setError("First Name cannot be empty ");
                }if (lastName.isEmpty()){
                    LastName.setError("Last Name cannot be empty ");
                }if (ConfirmPassword.isEmpty()){
                    confirmPassword.setError("Confirm Password cannot be empty ");
                }if (gender.isEmpty()){
                    Gender.setError("Gender cannot be empty");
                }if (day.isEmpty()){
                    Day1.setError("day cannot be empty");
                }if (month.isEmpty()){
                    month1.setError("day cannot be empty");
                }if (year.isEmpty()){
                    year1.setError("day cannot be empty");
                }
                else {
                    mAuth.createUserWithEmailAndPassword(email, pass)
                            .addOnCompleteListener(requireActivity(), task -> {
                                if (task.isSuccessful()) {
                                    // تم إنشاء الحساب بنجاح
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                                    Map<String, Object> userData = new HashMap<>();
                                    userData.put("First_Name", FirstName.getText().toString());
                                    userData.put("Last_Name", LastName.getText().toString());
                                    userData.put("User_name", email);
                                    userData.put(("Gender"),gender);
                                    userData.put(("Date_of_birth"),day+"/"+month+"/"+year);
                                    userData.put(("Passowrd"),pass);
// يمكنك إضافة المزيد من البيانات إلى هذا الكائن
                                    db.collection("users").document(user.getUid())
                                            .set(userData)
                                            .addOnSuccessListener(documentReference -> {
                                                // تم تخزين بيانات المستخدم بنجاح
                                              Intent intent = new Intent(getActivity(),Interests.class);
                                              startActivity(intent);
                                            })
                                            .addOnFailureListener(e -> {
                                                // فشلت عملية تخزين بيانات المستخدم
                                            });



                                    // إظهار رسالة تأكيد للمستخدم
                                } else {
                                    // فشلت عملية إنشاء الحساب
                                    // عرض رسالة خطأ للمستخدم
                                }
                            });

                }





            }
        });




        return rootView;
    }






}