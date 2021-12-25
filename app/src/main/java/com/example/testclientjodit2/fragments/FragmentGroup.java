 package com.example.testclientjodit2.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.testclientjodit2.R;
import com.example.testclientjodit2.activities.HomeActivity;
import com.example.testclientjodit2.activities.MissoinActivity;
import com.example.testclientjodit2.adapters.AdapterGroupItem;
import com.example.testclientjodit2.adapters.AdapterMission;
import com.example.testclientjodit2.database.JSONHelper;
import com.example.testclientjodit2.models.Mission;
import com.example.testclientjodit2.models.UserMission;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;


 public class FragmentGroup extends Fragment {
     ListView listView;
     AdapterMission adapterMission;
     List<UserMission> list;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_mission, null);
        listView = v.findViewById(R.id.list_groups);
        try{
            String jsonListMissions = getArguments().getString("listMission");
            list = JSONHelper.importListUserMissionFromJSON(jsonListMissions);
            adapterMission = new AdapterMission(getActivity(), list);
            listView.setAdapter(adapterMission);
        }catch (Exception ex){

        }
        return v;
    }


}