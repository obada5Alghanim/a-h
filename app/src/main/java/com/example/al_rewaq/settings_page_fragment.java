package com.example.al_rewaq;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class settings_page_fragment extends Fragment {

TextView termOfUseTxt,privaciyPolicyTxt,aboutTheAppTxt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings_page_fragment, container, false);
        termOfUseTxt = view.findViewById(R.id.term_of_user_id);
        privaciyPolicyTxt  = view.findViewById(R.id.privacy_policy_id);
        aboutTheAppTxt = view.findViewById(R.id.aboutTheAppTxt);
        term_of_use termOfUse = new term_of_use();
        privacy_policy privacyPolicy = new privacy_policy();
        aboutTheApp  aboutTheApp1 = new aboutTheApp();
        termOfUseTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(android.R.id.content,termOfUse).commit();

            }
        });
        privaciyPolicyTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(android.R.id.content,privacyPolicy).commit();

            }
        });

        aboutTheAppTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(android.R.id.content,aboutTheApp1).commit();

            }
        });


        return view;

    }

}