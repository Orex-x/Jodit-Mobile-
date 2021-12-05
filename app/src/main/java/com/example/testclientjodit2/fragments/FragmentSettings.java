package com.example.testclientjodit2.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.testclientjodit2.R;
import com.example.testclientjodit2.activities.MainActivity2;


public class
FragmentSettings extends Fragment {

    Button btnLogOut;

     @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("my_logs", "Fragment3");
        View v = inflater.inflate(R.layout.fragment_settings, null);

         btnLogOut = v.findViewById(R.id.btnLogOut);
         btnLogOut.setOnClickListener(v1 -> {
             logOut();
         });

        return v;
    }


    public void logOut(){

    }


}