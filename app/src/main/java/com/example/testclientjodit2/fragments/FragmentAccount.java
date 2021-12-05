package com.example.testclientjodit2.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.testclientjodit2.R;
import com.example.testclientjodit2.activities.MainActivity2;
import com.example.testclientjodit2.adapters.AdapterEditTextItem;
import com.example.testclientjodit2.adapters.AdapterGroupItem;

import me.fahmisdk6.avatarview.AvatarView;


public class FragmentAccount extends Fragment {

    AvatarView avatarView;

    public ListView listView, listAccountInfo;

    public static AdapterGroupItem adapterGroupItem;
    public static AdapterEditTextItem adapterEditTextItem;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_account, null);

        avatarView = v.findViewById(R.id.avatarView);


        listAccountInfo = v.findViewById(R.id.listAccountInfo);
        adapterEditTextItem = new AdapterEditTextItem(getActivity(), MainActivity2.editTextAndTextViews);
        listAccountInfo.setAdapter(adapterEditTextItem);


        listView = v.findViewById(R.id.list_groups_in_account);
        adapterGroupItem = new AdapterGroupItem(getActivity(), MainActivity2.list_groups);
        listView.setAdapter(adapterGroupItem);



       //avatarView.bind("Mean","android.resource://res/drawable/ic_user_foto");
        return v;
    }
}