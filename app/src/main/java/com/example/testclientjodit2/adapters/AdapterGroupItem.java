package com.example.testclientjodit2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.testclientjodit2.R;
import com.example.testclientjodit2.models.Group;

import java.util.List;

public class AdapterGroupItem extends BaseAdapter {

    private List<Group> list;
    private LayoutInflater layoutInflater;

    public AdapterGroupItem(Context context, List<Group> list) {
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
            view = layoutInflater.inflate(R.layout.item_group, parent, false);
        }

        Group group = getGroup(position);
        TextView textView = (TextView) view.findViewById(R.id.textView);
        textView.setText(group.GroupName);
        return view;
    }

    private Group getGroup(int position){
        return (Group) getItem(position);
    }
}