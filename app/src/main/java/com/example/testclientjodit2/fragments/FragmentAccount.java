package com.example.testclientjodit2.fragments;

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

import com.example.testclientjodit2.R;
import com.example.testclientjodit2.activities.HomeActivity;
import com.example.testclientjodit2.adapters.AdapterGroupItem;
import com.example.testclientjodit2.database.DBHelper;
import com.example.testclientjodit2.database.JSONHelper;
import com.example.testclientjodit2.models.Group;

import java.io.InputStream;
import java.util.List;


public class FragmentAccount extends Fragment {

    ImageView avatarView;

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
                        Group gr = HomeActivity.addGroupAPI(group);
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

        String uri = "https://www.allmmorpg.ru/wp-content/uploads/2021/02/fito-azury-gambar-gw-5-original.jpg";

        new DownloadImageTask((ImageView) avatarView)
                .execute(uri);


        return v;
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