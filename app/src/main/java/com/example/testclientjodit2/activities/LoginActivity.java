package com.example.testclientjodit2.activities;

import com.example.testclientjodit2.R;
import com.example.testclientjodit2.api.ServerController;
import com.example.testclientjodit2.api.ServerApi;
import com.example.testclientjodit2.models.viewModels.LoginModel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class LoginActivity extends AppCompatActivity {


    EditText edtEmail, edtPassword;
    ServerController serverController = new ServerController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    public void Register(View view) {

    }

    public void SignIn(View view) {
        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();
        if (email.length() > 0 && password.length() > 0) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://192.168.0.103:5000/api/")
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            ServerApi api = retrofit.create(ServerApi.class);


            Call<String> idSession = api.login(new LoginModel(email, password));
            idSession.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            Toast.makeText(LoginActivity.this, response.body(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.putExtra(ServerController.KEY_ID_SESSION, response.body());
                            startActivity(intent);
                        }
                    } else {
                        Toast.makeText(LoginActivity.this, "response is not Successful", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Toast.makeText(LoginActivity.this, "error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


    public void init() {
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        serverController.start();
    }
}