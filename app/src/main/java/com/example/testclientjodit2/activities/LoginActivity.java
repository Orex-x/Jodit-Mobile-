package com.example.testclientjodit2.activities;

import static rx.android.schedulers.AndroidSchedulers.mainThread;

import com.example.testclientjodit2.R;
import com.example.testclientjodit2.api.ServerController;
import com.example.testclientjodit2.api.ServerApi;
import com.example.testclientjodit2.database.DBHelper;
import com.example.testclientjodit2.database.DataLoader;
import com.example.testclientjodit2.fragments.FragmentAccount;
import com.example.testclientjodit2.models.User;
import com.example.testclientjodit2.models.viewModels.LoginModel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Subscriber;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

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
        if (ServerController.hasConnection(this)) {
            String email = edtEmail.getText().toString();
            String password = edtPassword.getText().toString();
            if (email.length() > 0 && password.length() > 0) {
                ServerController controller = new ServerController();
                controller.startRx();
                ServerApi api = controller.getRetrofit().create(ServerApi.class);

                api.loginAPI(new LoginModel(email, password)) // Получить наблюдаемый объект
                        .subscribeOn(Schedulers.newThread()) // Запрос выполняется в новом потоке
                        .observeOn(Schedulers.io()) // Выполнить в потоке io после завершения запроса
                        .doOnNext(new Action1<String>() {
                            @Override
                            public void call(String str) {
                                saveSession(str);
                            }
                        })
                        .observeOn(mainThread()) // Наконец выполняется в основном потоке
                        .subscribe(new Subscriber<String>() {
                            @Override
                            public void onCompleted() {
                            }

                            @Override
                            public void onError(Throwable e) {
                                Toast.makeText(LoginActivity.this, "Ошибка" + e.getMessage(), Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onNext(String str) {
                                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                intent.putExtra(DBHelper.INTENT_KEY_SESSION, str);
                                startActivity(intent);
                            }
                        });

            } else {
                Toast.makeText(LoginActivity.this,
                        "Отсутствует интернет подключение", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void checkSession() {
        FileInputStream fin = null;
        try {
            fin = openFileInput(DataLoader.FILE_NAME_SESSION);
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
                    openFileOutput(DataLoader.FILE_NAME_SESSION, MODE_PRIVATE)));
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
        serverController.startRx();
    }


}