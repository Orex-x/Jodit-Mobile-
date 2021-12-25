package com.example.testclientjodit2.activities;

import static android.provider.Telephony.Mms.Part.FILENAME;

import com.example.testclientjodit2.R;
import com.example.testclientjodit2.api.ServerController;
import com.example.testclientjodit2.api.ServerApi;
import com.example.testclientjodit2.database.DBHelper;
import com.example.testclientjodit2.database.JSONHelper;
import com.example.testclientjodit2.models.UserSession;
import com.example.testclientjodit2.models.viewModels.LoginModel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

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
        checkSession();

    }

    public void Register(View view) {

    }

    public void SignIn(View view) {
        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();
        if (email.length() > 0 && password.length() > 0) {
            ServerController controller = new ServerController();
            controller.start();
            ServerApi api = controller.getRetrofit().create(ServerApi.class);

            Call<String> idSessionCall = api.LoginAPI(new LoginModel(email, password));

            idSessionCall.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            saveSession(response.body());
                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                            intent.putExtra(DBHelper.INTENT_KEY_SESSION, response.body());
                            startActivity(intent);
                        }
                    } else {
                        Toast.makeText(LoginActivity.this, "response is not Successful", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {

                }
            });
        }
    }

    public void checkSession() {
          FileInputStream fin = null;
        try {
            fin = openFileInput(DBHelper.FILE_NAME_SESSION);
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);
            String id_session = new String(bytes);

            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            intent.putExtra(DBHelper.INTENT_KEY_SESSION, id_session);
            startActivity(intent);

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
    }

    void saveSession(String idSession) {
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                    openFileOutput(DBHelper.FILE_NAME_SESSION, MODE_PRIVATE)));
            bw.write(idSession);
            bw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void init() {
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        serverController.start();
    }
}