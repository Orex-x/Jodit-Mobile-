package com.example.testclientjodit2.activities;
import static rx.android.schedulers.AndroidSchedulers.mainThread;

import android.content.Intent;
import android.os.Bundle;

import com.example.testclientjodit2.DataListeners.IonActivityDataListenerToFragmentAccount;
import com.example.testclientjodit2.DataListeners.IonActivityDataListenerToFragmentMissions;
import com.example.testclientjodit2.R;
import com.example.testclientjodit2.activities.ui.main.SectionsPagerAdapter;
import com.example.testclientjodit2.api.ServerApi;
import com.example.testclientjodit2.api.ServerController;
import com.example.testclientjodit2.database.DBHelper;
import com.example.testclientjodit2.database.DataLoader;
import com.example.testclientjodit2.database.JSONHelper;
import com.example.testclientjodit2.models.Group;
import com.example.testclientjodit2.models.User;
import com.example.testclientjodit2.models.UserMission;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;


import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Subscriber;
import rx.functions.Action1;
import rx.schedulers.Schedulers;


public class HomeActivity extends AppCompatActivity {


    public static ImageView im1, im2, im3;
    SectionsPagerAdapter sectionsPagerAdapter;
    DBHelper dbHelper;
    User mainUser;
    public static String idSession;
    ServerApi api;
    private static final String TAG = "myLogs";


    

    private IonActivityDataListenerToFragmentAccount mListenerAccount;
    private IonActivityDataListenerToFragmentMissions mListenerMissions;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Bundle arguments = getIntent().getExtras();



        im1 = findViewById(R.id.im1);
        im2 = findViewById(R.id.im2);
        im3 = findViewById(R.id.im3);

        dbHelper = new DBHelper(this);



        ServerController controller = new ServerController();
        controller.startRx();
        api = controller.getRetrofit().create(ServerApi.class);

        sectionsPagerAdapter = new SectionsPagerAdapter(HomeActivity.this,
                getSupportFragmentManager());

        if(sectionsPagerAdapter.fragments[0] instanceof IonActivityDataListenerToFragmentMissions){
            mListenerMissions = (IonActivityDataListenerToFragmentMissions)
                    sectionsPagerAdapter.fragments[0];
        }

        if(sectionsPagerAdapter.fragments[1] instanceof IonActivityDataListenerToFragmentAccount){
            mListenerAccount = (IonActivityDataListenerToFragmentAccount)
                    sectionsPagerAdapter.fragments[1];
        }



        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);


        if (arguments != null) {
            idSession = arguments.getString(DBHelper.INTENT_KEY_SESSION);
            loadData();
        }
    }




    public void loadData() {
        if (ServerController.hasConnection(this)) {
            api.getUserAPI (idSession) // Получить наблюдаемый объект
                    .subscribeOn (Schedulers.newThread ()) // Запрос выполняется в новом потоке
                    .observeOn (Schedulers.io ()) // Выполнить в потоке io после завершения запроса
                    .doOnNext(new Action1<User>() {
                        @Override
                        public void call(User user) {
                           DataLoader.saveUser(user, HomeActivity.this); // Сохраняем информацию о пользователе локально
                        }
                    })
                    .observeOn (mainThread ()) // Наконец выполняется в основном потоке
                    .subscribe(new Subscriber<User>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            Toast.makeText(HomeActivity.this,
                                    e.getMessage(), Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onNext(User user) {
                            // Запрос успешен
                            mainUser = user;
                            mListenerAccount.onActivityDataListener
                                    (user.firstName, user.secondName, user.groups);

                            mListenerMissions.onActivityDataListener(user.executors);

                        }
                    });

        } else {
            Toast.makeText(HomeActivity.this,
                    "Отсутствует интернет подключение", Toast.LENGTH_LONG).show();
            mainUser = DataLoader.getUserFromStorage(this);
        }
    }

    public void updateData(){
        mainUser = DataLoader.getUserFromStorage(this);
        mListenerAccount.onActivityDataListener
                (mainUser.firstName, mainUser.secondName, mainUser.groups);
        mListenerMissions.onActivityDataListener(mainUser.executors);
    }

    public static Group addGroupAPI(Group group) {
        if (idSession != null) {
            ServerController controller = new ServerController();
            controller.startRx();
            ServerApi api = controller.getRetrofit().create(ServerApi.class);
            Call<Integer> CallIdGroup = api.addGroup(idSession, group);
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

    @Override
    public void onBackPressed() {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                mListenerMissions.setChange(data.getStringExtra("jsonUserMission"));
            }
        }

    }



}