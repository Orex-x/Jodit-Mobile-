package com.example.testclientjodit2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;


import com.example.testclientjodit2.R;
import com.example.testclientjodit2.models.EditTextAndTextView;

import java.util.List;

public class AdapterEditTextItem extends BaseAdapter {

    private List<EditTextAndTextView> list;
    private LayoutInflater layoutInflater;

    public AdapterEditTextItem(Context context, List<EditTextAndTextView> list) {
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
            view = layoutInflater.inflate(R.layout.item_edit_text, parent, false);
        }

        EditTextAndTextView str = getEditTextAndTextView(position);
        TextView textView = view.findViewById(R.id.text_item_edit_text);
        EditText editText = view.findViewById(R.id.edit_text_item_edit_text);
        textView.setText(str.getText());
        editText.setText(str.getEdit_text());
        return view;
    }

    private EditTextAndTextView getEditTextAndTextView(int position){
        return (EditTextAndTextView) getItem(position);
    }
}
