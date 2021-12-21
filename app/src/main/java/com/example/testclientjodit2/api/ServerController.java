package com.example.testclientjodit2.api;



import android.view.View;
import android.widget.Toast;

import com.example.testclientjodit2.models.User;
import com.example.testclientjodit2.models.UserGroup;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ServerController {

    static final String BASE_URL = "http://192.168.0.103:5000/api/";
    public static final String KEY_ID_SESSION = "idSession";

    private String session;
    private Retrofit retrofit;

    public void start() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public void setRetrofit(Retrofit retrofit) {
        this.retrofit = retrofit;
    }
}
