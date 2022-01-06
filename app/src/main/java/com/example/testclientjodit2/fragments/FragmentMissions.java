package com.example.testclientjodit2.fragments;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.testclientjodit2.DataListeners.IonActivityDataListenerToFragmentMissions;
import com.example.testclientjodit2.R;
import com.example.testclientjodit2.activities.HomeActivity;
import com.example.testclientjodit2.adapters.AdapterMission;
import com.example.testclientjodit2.api.ServerApi;
import com.example.testclientjodit2.api.ServerController;
import com.example.testclientjodit2.database.DataLoader;
import com.example.testclientjodit2.database.JSONHelper;
import com.example.testclientjodit2.models.User;
import com.example.testclientjodit2.models.UserMission;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


public class FragmentMissions extends Fragment implements IonActivityDataListenerToFragmentMissions {
    ListView listView;
    AdapterMission adapterMission;
    List<UserMission> list = new ArrayList<>();
    ServerApi api;
    private static final String TAG = "myLogs";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_missions, null);
        init(v);
        Log.i(TAG, "init fragment_missions");



        if(!ServerController.hasConnection(getContext())){
            Log.i(TAG, "dont hasConnection");
            User user = DataLoader.getUserFromStorage(getContext());
            adapterMission = new AdapterMission(getActivity(), user.executors);
            listView.setAdapter(adapterMission);
        }
        return v;
    }

    public void init(View v) {
        ServerController controller = new ServerController();
        controller.startRx();
        api = controller.getRetrofit().create(ServerApi.class);
        listView = v.findViewById(R.id.list_groups);


    }

    @Override
    public void onActivityDataListener(List<UserMission> list) {
        try {
            this.list = list;
            adapterMission = new AdapterMission(getActivity(), list);
            listView.setAdapter(adapterMission);
        } catch (Exception e) {

        }

    }

    @Override
    public void setChange(String jsonUserMission) {
        UserMission userMission = JSONHelper.importListUserMissionFromJSON(jsonUserMission);
        for (int i = 0; i < list.size(); i++){
            if(list.get(i).IdUserMission == userMission.IdUserMission){
                list.set(i, userMission);
            }
        }
        adapterMission.notifyDataSetChanged();
    }

    @Override
    public void onStart() {
        Log.i(TAG, "START");

        super.onStart();
    }
}