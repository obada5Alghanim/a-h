package com.example.al_rewaq;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Sign_Up_fragment extends Fragment {
    private FirebaseAuth auth;
    private EditText userName, password , FirstName, LastName;
    TextView txt;
    Button btn;
    FirebaseFirestore Fstore;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_sign__up_fragment, container, false);
        auth = FirebaseAuth.getInstance();
        Fstore = FirebaseFirestore.getInstance();
        userName = rootView.findViewById(R.id.Edt_signUP_UserName);
        password = rootView.findViewById(R.id.Edt_signUp_password);
        FirstName = rootView.findViewById(R.id.Edt_signUp_firstName);
        LastName = rootView.findViewById(R.id.Edt_signUp_lastName);
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
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String user = userName.getText().toString().trim();
                final String pass = password.getText().toString().trim();

                if (user.isEmpty()) {
                    userName.setError("User name cannot be empty ");
                }
                if (pass.isEmpty()) {
                    password.setError("password cannot be empty ");
                } else {
                    auth.createUserWithEmailAndPassword(user, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Add user details to Firestore
                                String firstName = FirstName.getText().toString().trim();
                                String lastName = LastName.getText().toString().trim();

                                Map<String, Object> userTable = new HashMap<>();
                                userTable.put("First Name", firstName);
                                userTable.put("Last Name", lastName);
                                userTable.put(("User Name"),user);
                                userTable.put(("Password"),pass);

                                Fstore.collection("Users").add(userTable).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DocumentReference> task) {
                                        if (task.isSuccessful()) {

                                            Intent intent = new Intent(getActivity(), Interests.class);
                                            startActivity(intent);

                                        } else {
                                            Toast.makeText(getActivity(), "Failed to add user details to Firestore.", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            } else {
                                Toast.makeText(getActivity(), "Failed to create user: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        return rootView;
    }
}