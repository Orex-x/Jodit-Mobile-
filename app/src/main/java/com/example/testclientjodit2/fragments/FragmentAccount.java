package com.example.testclientjodit2.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testclientjodit2.DataListeners.IonActivityDataListenerToFragmentAccount;
import com.example.testclientjodit2.R;
import com.example.testclientjodit2.activities.HomeActivity;
import com.example.testclientjodit2.adapters.AdapterGroupItem;
import com.example.testclientjodit2.api.ServerController;
import com.example.testclientjodit2.database.DBHelper;
import com.example.testclientjodit2.database.DataLoader;
import com.example.testclientjodit2.database.JSONHelper;
import com.example.testclientjodit2.models.Group;
import com.example.testclientjodit2.models.User;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class FragmentAccount extends Fragment implements IonActivityDataListenerToFragmentAccount {

    ImageView avatarView;

    public ListView listviewGroups;

    public TextView txtFirstName, txtSecondName;
    public static AdapterGroupItem adapterGroupItem;
    public Button btnAddGroup;
    List<Group> groups = new ArrayList<>();

    final String LOG_TAG = "myLogs";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_account, null);

        init(v);

        String uri = "https://www.allmmorpg.ru/wp-content/uploads/2021/02/fito-azury-gambar-gw-5-original.jpg";
        new DownloadImageTask((ImageView) avatarView)
                .execute(uri);
        btnAddGroup.setOnClickListener(v1 -> {
            v1 = inflater.inflate(R.layout.dialog_add_group, null);
            AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
            mBuilder.setView(v1);
            EditText group_name = v1.findViewById(R.id.group_name);
            EditText group_description = v1.findViewById(R.id.group_description);
            CheckBox box = v1.findViewById(R.id.chkPrivate);
            mBuilder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    Group group = new Group();
                    group.GroupName = group_name.getText().toString();
                    group.Description = group_description.getText().toString();
                    group.IsPrivate = box.isChecked();
                    Group gr = HomeActivity.addGroupAPI(group);
                    groups.add(gr);
                    adapterGroupItem.notifyDataSetChanged();
                }
            });
            mBuilder.show();
        });

        if(!ServerController.hasConnection(getContext())){
            User user = DataLoader.getUserFromStorage(getContext());
            txtFirstName.setText(user.firstName);
            txtSecondName.setText(user.secondName);
            adapterGroupItem = new AdapterGroupItem(getActivity(), user.groups);
            listviewGroups.setAdapter(adapterGroupItem);
        }

        return v;
    }

    public void init(View v){
        avatarView = v.findViewById(R.id.avatarView);

        txtFirstName = v.findViewById(R.id.txtFirstName);
        txtSecondName = v.findViewById(R.id.txtSecondName);
        listviewGroups = v.findViewById(R.id.listviewGroups);
        btnAddGroup = v.findViewById(R.id.btnAddGroup);
    }

    @Override
    public void onActivityDataListener(String firstName,
                                       String secondName,
                                       List<Group> groups) {
        try {
            txtFirstName.setText(firstName);
            txtSecondName.setText(secondName);
            this.groups = groups;
            adapterGroupItem = new AdapterGroupItem(getActivity(), groups);
            listviewGroups.setAdapter(adapterGroupItem);
        } catch (Exception e) {

        }
    }


    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }


}