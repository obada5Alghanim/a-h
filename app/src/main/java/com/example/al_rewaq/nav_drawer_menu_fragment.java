package com.example.al_rewaq;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;


public class nav_drawer_menu_fragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_nav_drawer_menu_fragment, container, false);
        TextView textViewSetting = view.findViewById(R.id.settingTextClick);

        textViewSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                settings_page_fragment settingsPageFragment = new settings_page_fragment();
                ft.replace(android.R.id.content,settingsPageFragment);
                ft.commit();
            }
        });

        return view;
    }
}