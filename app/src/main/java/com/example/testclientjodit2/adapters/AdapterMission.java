package com.example.testclientjodit2.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.testclientjodit2.R;
import com.example.testclientjodit2.activities.HomeActivity;
import com.example.testclientjodit2.activities.LoginActivity;
import com.example.testclientjodit2.activities.MissoinActivity;
import com.example.testclientjodit2.database.DBHelper;
import com.example.testclientjodit2.database.JSONHelper;
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
        if (view == null) {
            view = layoutInflater.inflate(R.layout.item_mission, parent, false);
        }

        UserMission userMission = getUserMission(position);
        if (userMission.Status.equals("PENDING") || userMission.Status.equals("TAKE")) {


            TextView txtFrame = view.findViewById(R.id.txtFrame);
            TextView txtTitle = view.findViewById(R.id.txtTitle);
            TextView txtDescription = view.findViewById(R.id.txtDescription);
            txtTitle.setText(userMission.Mission.Title);
            String des = "";
            if (userMission.Mission.Description.length() > 200) {
                des = userMission.Mission.Description.substring(0, 197) + "...";
            } else {
                des = userMission.Mission.Description;
            }
            txtDescription.setText(des);
            txtFrame.setOnClickListener(v -> {
                Intent intent = new Intent(layoutInflater.getContext(), MissoinActivity.class);
                intent.putExtra("mission", JSONHelper.exportListUserMissionToJSON(userMission));
                ((Activity) layoutInflater.getContext()).startActivityForResult(intent, 1);
            });

            return view;
        }else{
            return layoutInflater.inflate(R.layout.null_item, parent,  false);

        }
    }

    private UserMission getUserMission(int position) {
        return (UserMission) getItem(position);
    }
}
