package com.example.testclientjodit2.activities;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
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
import com.example.testclientjodit2.databinding.ActivityMain2Binding;
import com.example.testclientjodit2.fragments.FragmentAccount;
import com.example.testclientjodit2.models.EditTextAndTextView;
import com.example.testclientjodit2.models.Group;
import com.example.testclientjodit2.models.User;
import com.example.testclientjodit2.models.UserGroup;
import com.example.testclientjodit2.models.viewModels.LoginModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;


import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class MainActivity2 extends AppCompatActivity {



    public static ImageView im1, im2, im3;
    public static ArrayList<String> list_groups = new ArrayList<>();
    SectionsPagerAdapter sectionsPagerAdapter;
    DBHelper dbHelper;
    User mainUser;
    public static String idSession = "k.LvN>8=NQvj;ES0yG=*svDLk";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        im1 = findViewById(R.id.im1);
        im2 = findViewById(R.id.im2);
        im3 = findViewById(R.id.im3);

        Log.d("LOG_TAG", "Button click in Fragment1");
        // создаем объект для создания и управления версиями БД
        dbHelper = new DBHelper(this);

        loadData();

        sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);


        if(mainUser != null){
            Bundle bundle = new Bundle();
            bundle.putString("FirstName", mainUser.firstName);
            bundle.putString("SecondName", mainUser.secondName);
            bundle.putString("JSONGroups", JSONHelper.exportGroupsToJSON(mainUser.groups));
            sectionsPagerAdapter.fragments[1].setArguments(bundle);
        }


    }



    public void loadData(){
        if(idSession != null){
            if(hasConnection(this)){
                ServerController controller = new ServerController();
                controller.start();
                ServerApi api = controller.getRetrofit().create(ServerApi.class);

                Call<User> user = api.GetUserAPI(idSession);

                user.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        User user = response.body();
                        String jsonString = JSONHelper.exportUserToJSON(user);

                        if(jsonString.length() > 0){
                            Toast.makeText(MainActivity2.this,
                                    "Строка json успешно создана", Toast.LENGTH_LONG).show();

                            MySQL.delete_all(DBHelper.TABLE_USERS, dbHelper);
                            boolean resust = MySQL.add(jsonString, DBHelper.TABLE_USERS, DBHelper.KEY_USER_JSON, dbHelper);
                            if(resust){
                                Toast.makeText(MainActivity2.this,
                                        "Данные обновлены", Toast.LENGTH_LONG).show();
                            }
                            else{
                                Toast.makeText(MainActivity2.this,
                                        "Не удалось сохранить данные", Toast.LENGTH_LONG).show();
                            }
                        }
                        else{
                            Toast.makeText(MainActivity2.this,
                                    "Не удалось создать строку json", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(MainActivity2.this, t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
            }else{
                Toast.makeText(MainActivity2.this,
                        "Отсутствует интернет подключение", Toast.LENGTH_LONG).show();
            }
            ArrayList<String> list = MySQL.get_all_value(DBHelper.TABLE_USERS, DBHelper.KEY_USER_JSON, dbHelper);
            mainUser = JSONHelper.importUserFromJSON(list.get(0));
        }
    }

    public static boolean hasConnection(final Context context)
    {
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiInfo != null && wifiInfo.isConnected())
        {
            return true;
        }
        wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (wifiInfo != null && wifiInfo.isConnected())
        {
            return true;
        }
        wifiInfo = cm.getActiveNetworkInfo();
        if (wifiInfo != null && wifiInfo.isConnected())
        {
            return true;
        }
        return false;
    }


    public static Group addGroupAPI(Group group){
        if(idSession != null){
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