package com.example.testclientjodit2.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.testclientjodit2.R;
import com.example.testclientjodit2.activities.MainActivity2;
import com.example.testclientjodit2.adapters.AdapterEditTextItem;
import com.example.testclientjodit2.adapters.AdapterGroupItem;
import com.example.testclientjodit2.database.JSONHelper;
import com.example.testclientjodit2.models.Group;

import java.net.Inet4Address;
import java.util.List;

import me.fahmisdk6.avatarview.AvatarView;


public class FragmentAccount extends Fragment {

    AvatarView avatarView;

    public ListView listviewGroups;

    public TextView txtFirstName, txtSecondName;
    public static AdapterGroupItem adapterGroupItem;
    public Button btnAddGroup;
    List<Group> groups;

    final String LOG_TAG = "myLogs";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_account, null);

        avatarView = v.findViewById(R.id.avatarView);

        txtFirstName = v.findViewById(R.id.txtFirstName);
        txtSecondName = v.findViewById(R.id.txtSecondName);
        listviewGroups = v.findViewById(R.id.listviewGroups);
        btnAddGroup = v.findViewById(R.id.btnAddGroup);


        btnAddGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v = inflater.inflate(R.layout.dialog_add_group, null);
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
                mBuilder.setView(v);
                EditText group_name = v.findViewById(R.id.group_name);
                EditText group_description = v.findViewById(R.id.group_description);
                CheckBox box = v.findViewById(R.id.chkPrivate);
                mBuilder.setPositiveButton("Add", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int id)
                    {
                        Group group = new Group();
                        group.GroupName = group_name.getText().toString();
                        group.Description = group_description.getText().toString();
                        group.IsPrivate = box.isChecked();
                        Group gr = MainActivity2.addGroupAPI(group);
                        groups.add(gr);
                        adapterGroupItem.notifyDataSetChanged();
                    }
                });
                mBuilder.show();
            }
        });

        try{
            String FirstName = getArguments().getString("FirstName");
            String SecondName = getArguments().getString("SecondName");
            String JSONGroups = getArguments().getString("JSONGroups");
            groups = JSONHelper.importGroupsFromJSON(JSONGroups);
            adapterGroupItem = new AdapterGroupItem(getActivity(), groups);
            listviewGroups.setAdapter(adapterGroupItem);


            txtFirstName.setText(FirstName);
            txtSecondName.setText(SecondName);
        }catch (Exception ex){

        }



       //avatarView.bind("Mean","android.resource://res/drawable/ic_user_foto");
        return v;
    }
}