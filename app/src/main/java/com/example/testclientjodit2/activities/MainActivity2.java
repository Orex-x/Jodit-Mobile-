package com.example.testclientjodit2.activities;

import android.os.Bundle;

import com.example.testclientjodit2.R;
import com.example.testclientjodit2.activities.ui.main.SectionsPagerAdapter;
import com.example.testclientjodit2.api.ServerApi;
import com.example.testclientjodit2.api.ServerController;
import com.example.testclientjodit2.database.JSONHelper;
import com.example.testclientjodit2.database.MySQL;
import com.example.testclientjodit2.databinding.ActivityMain2Binding;
import com.example.testclientjodit2.models.EditTextAndTextView;
import com.example.testclientjodit2.models.User;
import com.example.testclientjodit2.models.UserGroup;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;


import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

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

    private String idSession = "/R|B5<Y-Vv\\gtNf1oV\\dP,{:?/6HCX";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        im1 = findViewById(R.id.im1);
        im2 = findViewById(R.id.im2);
        im3 = findViewById(R.id.im3);

        Log.d("LOG_TAG", "Button click in Fragment1");

        loadData();

        sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);

    }


    public void loadData(){
        if(idSession != null){

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://192.168.0.116:5000/api/")
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            ServerApi api = retrofit.create(ServerApi.class);

            Call<List<UserGroup>> usergr = api.getUserGroup(idSession);

            Call<User> mainUser = api.getUser(idSession);

            mainUser.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    User user = response.body();
                    boolean result = JSONHelper.exportToJSON(
                            MainActivity2.this, user);
                    if(result){
                        Toast.makeText(MainActivity2.this,
                                "Данные сохранены", Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(MainActivity2.this,
                                "Не удалось сохранить данные", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {

                }
            });




            usergr.enqueue(new Callback<List<UserGroup>>() {
                @Override
                public void onResponse(Call<List<UserGroup>> call, Response<List<UserGroup>> response) {
                    for (UserGroup g : response.body()) {
                        list_groups.add(g.Group.GroupName);
                    }
                    sectionsPagerAdapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<List<UserGroup>> call, Throwable t) {

                }
            });
        }
    }
}