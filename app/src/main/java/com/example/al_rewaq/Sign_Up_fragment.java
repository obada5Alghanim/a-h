package com.example.al_rewaq;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;



public class Sign_Up_fragment extends Fragment {


   public Sign_Up_fragment(){

   }


    private FirebaseAuth mAuth;
    private EditText userName, password , FirstName, LastName ,confirmPassword;
    TextView txt,Gender ,Day ,maleTextView,femaleTextView;
    Button btn;
    boolean passwordVisiable;


    DatePickerDialog.OnDateSetListener onDateSetListener;

    @SuppressLint("ClickableViewAccessibility")

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_sign__up_fragment, container, false);

        mAuth = FirebaseAuth.getInstance();
        userName = rootView.findViewById(R.id.Edt_signUP_UserName);
        password = rootView.findViewById(R.id.Edt_signUp_password);
        FirstName = rootView.findViewById(R.id.Edt_signUp_firstName);
        LastName = rootView.findViewById(R.id.Edt_signUp_lastName);
        Gender = rootView.findViewById(R.id.Edt_signUP_gender);
        maleTextView = rootView.findViewById(R.id.male_genderText);
        femaleTextView = rootView.findViewById(R.id.female_genderText);
        Day = rootView.findViewById(R.id.DOB);
        confirmPassword = rootView.findViewById(R.id.confirmPassword);
        txt = rootView.findViewById(R.id.signUP_to_sing_click);
        btn = rootView.findViewById(R.id.signUp_button);


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

        Day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year  = calendar.get(Calendar.YEAR);
                int month  = calendar.get(Calendar.MONTH);
                int day  = calendar.get(Calendar.DAY_OF_MONTH);

                onDateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Day.setText(String.valueOf(dayOfMonth +" / "+(month+1)+" / "+year));
                    }
                };

                DatePickerDialog datePickerDialog = new DatePickerDialog(requireContext(),
                        android.R.style.Theme_Holo_Light_Dialog,onDateSetListener,year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
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
                final String day  = Day.getText().toString();


                if (firstName.isEmpty()){
                    FirstName.setError("First Name cannot be empty ");
                }else if (lastName.isEmpty()){
                    LastName.setError("Last Name cannot be empty ");
                }else if (email.isEmpty()) {
                    userName.setError("User name cannot be empty ");
                }else if (gender.isEmpty()){
                    Gender.setError("Gender cannot be empty");
                }else if (day.isEmpty()){
                    Day.setError("day cannot be empty");
                }else if (pass.isEmpty()) {
                    Toast.makeText(getActivity(),"Password cannot be empty", Toast.LENGTH_SHORT).show();
                }else if (ConfirmPassword.isEmpty()){
                    Toast.makeText(getActivity(),"Confirm Password cannot be empty", Toast.LENGTH_SHORT).show();
                }else if (!pass.equals(ConfirmPassword)){
                    Toast.makeText(getActivity(),"Password don't Match", Toast.LENGTH_SHORT).show();
                }

                else {
                    mAuth.createUserWithEmailAndPassword(email, pass)
                            .addOnCompleteListener(requireActivity(), task -> {
                                if (task.isSuccessful()) {
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                                    Map<String, Object> userData = new HashMap<>();
                                    userData.put("First_Name", FirstName.getText().toString());
                                    userData.put("Last_Name", LastName.getText().toString());
                                    userData.put("User_name", email);
                                    userData.put(("Gender"),gender);
                                    userData.put(("Date_of_birth"),day);
                                    userData.put(("Passowrd"),pass);

                                    db.collection("users").document(user.getUid())
                                            .set(userData)
                                            .addOnSuccessListener(documentReference -> {
                                              Intent intent = new Intent(getActivity(),Interests.class);
                                              startActivity(intent);
                                            })
                                            .addOnFailureListener(e -> {
                                            });
                                }
                            });
                }

            }
        });



        return rootView;
    }

}
