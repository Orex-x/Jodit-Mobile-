package com.example.testclientjodit2.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testclientjodit2.R;
import com.example.testclientjodit2.api.ServerController;
import com.example.testclientjodit2.api.ServerApi;
import com.example.testclientjodit2.models.User;
import com.example.testclientjodit2.models.UserGroup;
import com.example.testclientjodit2.models.viewModels.LoginModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView txt;
    ServerController serverController = new ServerController();
    private String idSession = "k.LvN>8=NQvj;ES0yG=*svDLk";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt = findViewById(R.id.txt);
        Bundle arguments = getIntent().getExtras();

        if (arguments != null) {
            idSession = arguments.getString(ServerController.KEY_ID_SESSION);
        }
        serverController.start();
    }


    public void getGroups(View view) {
        if(idSession != null){

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://192.168.0.103:5000/api/")
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            ServerApi api = retrofit.create(ServerApi.class);

            Call<List<UserGroup>> usergr = api.getUserGroup(idSession);

            usergr.enqueue(new Callback<List<UserGroup>>() {
                @Override
                public void onResponse(Call<List<UserGroup>> call, Response<List<UserGroup>> response) {
                    String res = "";
                    for (UserGroup g : response.body()) {
                        res += g.Group.GroupName + "\n";
                    }
                    txt.setText(res);
                }

                @Override
                public void onFailure(Call<List<UserGroup>> call, Throwable t) {

                }
            });

/*
            Call<List<User>> users = api.getUsers(idSession);

            users.enqueue(new Callback<List<User>>() {
                @Override
                public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                    String res = "";
                    for (User g : response.body()) {
                        res += g.email + "\n";
                    }
                    txt.setText(res);
                }

                @Override
                public void onFailure(Call<List<User>> call, Throwable t) {

                }
            });*/






           /* messages.enqueue(new Callback<List<UserGroup>>() {
                @Override
                public void onResponse(Call<List<UserGroup>> call, Response<List<UserGroup>> response) {
                    if (response.isSuccessful()) {
                        String res = "";
                        for (UserGroup g : response.body()) {
                            res += g.Group.GroupName + "\n";
                        }
                        txt.setText(res);
                    }
                }
                @Override
                public void onFailure(Call<List<UserGroup>> call, Throwable t) {

                }
            });
*/

           /* String res = "";
            ArrayList<UserGroup> list = serverController.getGroups(idSession);
            for (UserGroup g : list) {
                res += g.Group.GroupName + "\n";
            }
            txt.setText(res);*/
        }
    }
}
