 package com.example.testclientjodit2.fragments;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
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
import com.example.testclientjodit2.activities.MainActivity2;
import com.example.testclientjodit2.adapters.AdapterGroupItem;
import com.example.testclientjodit2.api.ServerController;


 public class FragmentGroup extends Fragment {

    public ListView listView;
    public Button button;

    public static AdapterGroupItem adapterGroupItem;
    public static Animation move_right_Animation;
    public EditText group_name;

    final String LOG_TAG = "myLogs";

     ServerController serverController = new ServerController();
     private String idSession = "k.LvN>8=NQvj;ES0yG=*svDLk";




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_mission, null);



        button = v.findViewById(R.id.buttonAddGroup);
        listView = v.findViewById(R.id.list_groups);
       // adapterGroupItem = new AdapterGroupItem(getActivity(), MainActivity2.list_groups);
       // listView.setAdapter(adapterGroupItem);
        button = v.findViewById(R.id.buttonAddGroup);

        move_right_Animation = AnimationUtils.loadAnimation(getActivity(), R.anim.move_right);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v = inflater.inflate(R.layout.dialog_add_group, null);
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
                mBuilder.setView(v);
                group_name = v.findViewById(R.id.group_name);
                mBuilder.setPositiveButton("Add", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int id)
                    {
                        MainActivity2.list_groups.add( group_name.getText().toString());
                        adapterGroupItem.notifyDataSetChanged();
                    }
                });
                mBuilder.show();
            }
        });
        setOnClickItem();
        return v;
    }

    private void setOnClickItem(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }
}