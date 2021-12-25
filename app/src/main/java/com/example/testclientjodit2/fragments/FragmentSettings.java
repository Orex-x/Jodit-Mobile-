package com.example.testclientjodit2.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.testclientjodit2.R;
import com.example.testclientjodit2.activities.HomeActivity;
import com.example.testclientjodit2.activities.LoginActivity;
import com.example.testclientjodit2.adapters.AdapterGroupItem;
import com.example.testclientjodit2.api.ServerApi;
import com.example.testclientjodit2.api.ServerController;
import com.example.testclientjodit2.database.DBHelper;
import com.example.testclientjodit2.database.JSONHelper;
import com.example.testclientjodit2.models.User;
import com.example.testclientjodit2.models.viewModels.LoginModel;

import java.io.File;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class
FragmentSettings extends Fragment {

    Button btnLogOut;
    String idSession;

     @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("my_logs", "Fragment3");
        View v = inflater.inflate(R.layout.fragment_settings, null);

         try{
             String idSession = getArguments().getString("idSession");
             this.idSession = idSession;
         }catch (Exception ex){

         }


         btnLogOut = v.findViewById(R.id.btnLogOut);
         btnLogOut.setOnClickListener(v1 -> {
             logOut();
         });

        return v;
    }


    public void logOut(){
         if(idSession != null){
             //создаем retrofit контроллер
             ServerController controller = new ServerController();
             controller.start();
             ServerApi api = controller.getRetrofit().create(ServerApi.class);
             Call<Boolean> answer = api.LogoutAPI(idSession);

             answer.enqueue(new Callback<Boolean>() {
                 @Override
                 public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                     getContext().deleteFile(DBHelper.FILE_NAME_USER);
                     getContext().deleteFile(DBHelper.FILE_NAME_SESSION);
                     Intent intent = new Intent(getContext(), LoginActivity.class);
                     startActivity(intent);
                 }
                 @Override
                 public void onFailure(Call<Boolean> call, Throwable t) {
                     Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
                 }
             });


         }else{
             Toast.makeText(getActivity(), "idSession = null", Toast.LENGTH_SHORT).show();

         }
    }
}