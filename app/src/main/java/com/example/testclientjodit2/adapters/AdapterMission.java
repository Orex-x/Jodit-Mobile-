package com.example.testclientjodit2.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.testclientjodit2.R;
import com.example.testclientjodit2.activities.HomeActivity;
import com.example.testclientjodit2.activities.LoginActivity;
import com.example.testclientjodit2.activities.MissoinActivity;
import com.example.testclientjodit2.database.DBHelper;
import com.example.testclientjodit2.models.Mission;
import com.example.testclientjodit2.models.Mission;
import com.example.testclientjodit2.models.UserMission;

import java.util.List;

public class AdapterMission extends BaseAdapter {

    private List<UserMission> list;
    private LayoutInflater layoutInflater;

    public AdapterMission(Context context, List<UserMission> list) {
        this.list = list;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null){
            view = layoutInflater.inflate(R.layout.item_mission, parent, false);
        }
        UserMission mission = getUserMission(position);
        TextView txtFrame = view.findViewById(R.id.txtFrame);
        TextView txtTitle = view.findViewById(R.id.txtTitle);
        TextView txtDescription = view.findViewById(R.id.txtDescription);
        txtTitle.setText(mission.Mission.Title);
        txtDescription.setText(mission.Mission.Description);
        txtFrame.setOnClickListener(v -> {
            Intent intent = new Intent(layoutInflater.getContext(), MissoinActivity.class);
            intent.putExtra("idMission", mission.Mission.IdMission);
            layoutInflater.getContext().startActivity(intent);
        });
        return view;
    }

    private UserMission getUserMission(int position){
        return (UserMission) getItem(position);
    }
}
