package com.example.testclientjodit2.activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import com.example.testclientjodit2.R;
import com.example.testclientjodit2.activities.ui.main.SectionsPagerAdapter;
import com.example.testclientjodit2.api.ServerApi;
import com.example.testclientjodit2.api.ServerController;
import com.example.testclientjodit2.database.DBHelper;
import com.example.testclientjodit2.database.JSONHelper;
import com.example.testclientjodit2.database.MySQL;
import com.example.testclientjodit2.models.Group;
import com.example.testclientjodit2.models.Mission;
import com.example.testclientjodit2.models.User;
import com.example.testclientjodit2.models.UserMission;
import com.example.testclientjodit2.models.UserSession;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;


import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeActivity extends AppCompatActivity {


    public static ImageView im1, im2, im3;
    public static ArrayList<String> list_groups = new ArrayList<>();
    SectionsPagerAdapter sectionsPagerAdapter;
    DBHelper dbHelper;
    User mainUser;
    public static String idSession;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Bundle arguments = getIntent().getExtras();

        im1 = findViewById(R.id.im1);
        im2 = findViewById(R.id.im2);
        im3 = findViewById(R.id.im3);

        dbHelper = new DBHelper(this);


        if (arguments != null) {
            idSession = arguments.getString(DBHelper.INTENT_KEY_SESSION);
            loadData();
        }

        sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);

        mainUser = getUserFromStorage();
        if (mainUser != null) {
            Bundle bundle0 = new Bundle();
            bundle0.putString("listMission", JSONHelper.exportListUserMissionToJSON(mainUser.executors));
            sectionsPagerAdapter.fragments[0].setArguments(bundle0);

            Bundle bundle1 = new Bundle();
            bundle1.putString("FirstName", mainUser.firstName);
            bundle1.putString("SecondName", mainUser.secondName);
            bundle1.putString("JSONGroups", JSONHelper.exportGroupsToJSON(mainUser.groups));
            bundle1.putString(DBHelper.INTENT_KEY_USER, JSONHelper.exportUserToJSON(mainUser));
            sectionsPagerAdapter.fragments[1].setArguments(bundle1);

            Bundle bundle2 = new Bundle();
            bundle2.putString("idSession", idSession);
            sectionsPagerAdapter.fragments[2].setArguments(bundle2);
        }



    }


    public void loadData() {
        if (hasConnection(this)) {
            ServerController controller = new ServerController();
            controller.start();
            ServerApi api = controller.getRetrofit().create(ServerApi.class);

            Call<User> user = api.GetUserAPI(idSession);

            user.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    try{
                        User user = response.body();
                        saveUser(user);
                    }catch (Exception e){
                        Toast.makeText(HomeActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Toast.makeText(HomeActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            Toast.makeText(HomeActivity.this,
                    "Отсутствует интернет подключение", Toast.LENGTH_LONG).show();
        }
    }

    void saveUser(User user) {
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                    openFileOutput(DBHelper.FILE_NAME_USER, MODE_PRIVATE)));
            String jsonString = JSONHelper.exportUserToJSON(user);
            bw.write(jsonString);
            bw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // открытие файла
    public User getUserFromStorage() {
        FileInputStream fin = null;
        try {
            fin = openFileInput(DBHelper.FILE_NAME_USER);
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);
            String jsonUser = new String(bytes);
            return JSONHelper.importUserFromJSON(jsonUser);
        } catch (IOException ex) {

            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            try {
                if (fin != null)
                    fin.close();
            } catch (IOException ex) {
                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        return null;
    }

    public static boolean hasConnection(final Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiInfo != null && wifiInfo.isConnected()) {
            return true;
        }
        wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (wifiInfo != null && wifiInfo.isConnected()) {
            return true;
        }
        wifiInfo = cm.getActiveNetworkInfo();
        if (wifiInfo != null && wifiInfo.isConnected()) {
            return true;
        }
        return false;
    }

    public static Group addGroupAPI(Group group) {
        if (idSession != null) {
            ServerController controller = new ServerController();
            controller.start();
            ServerApi api = controller.getRetrofit().create(ServerApi.class);
            Call<Integer> CallIdGroup = api.AddGroup(idSession, group);
            CallIdGroup.enqueue(new Callback<Integer>() {
                @Override
                public void onResponse(Call<Integer> call, Response<Integer> response) {
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            group.setIdGroup(response.body());
                        }
                    }
                }

                @Override
                public void onFailure(Call<Integer> call, Throwable t) {

                }
            });
            return group;
        }
        return new Group();
    }

}