package com.example.testclientjodit2.activities;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.testclientjodit2.R;
import com.example.testclientjodit2.adapters.AdapterStringItem;
import com.example.testclientjodit2.api.ServerApi;
import com.example.testclientjodit2.api.ServerController;
import com.example.testclientjodit2.database.JSONHelper;
import com.example.testclientjodit2.models.UserMission;
import java.util.ArrayList;


public class MissoinActivity extends AppCompatActivity {
    UserMission userMission;
    TextView txtTitle;
    ListView list;
    Button btnTake, btnRefuse, btnPass;
    AdapterStringItem adapterStringItem;
    ServerApi api;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_missoin);
        init();
        adapterStringItem = new AdapterStringItem(this, new ArrayList<String>() {{
            add(userMission.Mission.Description);
        }});
        list.setAdapter(adapterStringItem);
        txtTitle.setText(userMission.Mission.Title);
    }

    public void init() {

        txtTitle = findViewById(R.id.txtTitle);
        list = findViewById(R.id.list);
        btnTake = findViewById(R.id.btnTake);
        btnRefuse = findViewById(R.id.btnRefuse);
        btnPass = findViewById(R.id.btnPass);

        ServerController controller = new ServerController();
        controller.startRx();
        api = controller.getRetrofit().create(ServerApi.class);


        Bundle arguments = getIntent().getExtras();
        if (arguments != null) {
            userMission = JSONHelper.importListUserMissionFromJSON(arguments.getString("mission"));


            if (userMission.Status.equals("PENDING")) {
                setViewStatusPENDING();
            }

            if (userMission.Status.equals("TAKE")) {
                setViewStatusTAKE();
            }

            if (userMission.Status.equals("PASS")) {
                setViewStatusPASS();
            }


            btnTake.setOnClickListener(this::onClickTake);
            btnRefuse.setOnClickListener(this::onClickRefuse);
        }
    }

    public void onClickTake(View view) {
        if (userMission != null) {
            userMission.Status = "TAKE";
/*
            api.takeMission(userMission.Mission.IdMission, HomeActivity.idSession) // Получить наблюдаемый объект
                    .subscribeOn(Schedulers.newThread()) // Запрос выполняется в новом потоке
                    .observeOn(Schedulers.io()) // Выполнить в потоке io после завершения запроса
                    .doOnNext(new Action1<Boolean>() {
                        @Override
                        public void call(Boolean res) {

                        }
                    })
                    .observeOn(mainThread()) // Наконец выполняется в основном потоке
                    .subscribe(new Subscriber<Boolean>() {
                        @Override
                        public void onCompleted() {
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(Boolean res) {
                            if(res){
                                setViewStatusTAKE();
                            }
                        }
                    });*/
        }
    }




    public void onClickRefuse(View view) {
        if (userMission != null) {
            userMission.Status = "REFUSE";
            String jsonUserMission = JSONHelper.exportListUserMissionToJSON(userMission);
            Intent i = new Intent();
            i.putExtra("jsonUserMission", jsonUserMission);
            setResult(RESULT_OK, i);
            finish();
/*
            api.refuseMission(userMission.Mission.IdMission, HomeActivity.idSession) // Получить наблюдаемый объект
                    .subscribeOn(Schedulers.newThread()) // Запрос выполняется в новом потоке
                    .observeOn(Schedulers.io()) // Выполнить в потоке io после завершения запроса
                    .doOnNext(new Action1<Boolean>() {
                        @Override
                        public void call(Boolean res) {

                        }
                    })
                    .observeOn(mainThread()) // Наконец выполняется в основном потоке
                    .subscribe(new Subscriber<Boolean>() {
                        @Override
                        public void onCompleted() {
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(Boolean res) {
                            if(res){


                            }

                        }
                    });
            super.finish();*/
        }
    }

    public void setViewStatusPENDING() {
        btnTake.setEnabled(true);
        btnRefuse.setEnabled(true);
        btnPass.setEnabled(false);
    }

    public void setViewStatusTAKE() {
        btnTake.setEnabled(false);
        btnRefuse.setEnabled(false);
        btnPass.setEnabled(true);
    }

    public void setViewStatusPASS() {
        btnTake.setEnabled(false);
        btnRefuse.setEnabled(false);
        btnPass.setEnabled(false);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        String jsonUserMission = JSONHelper.exportListUserMissionToJSON(userMission);
        Intent i = new Intent();
        i.putExtra("jsonUserMission", jsonUserMission);
        setResult(RESULT_OK, i);
        finish();
    }
}