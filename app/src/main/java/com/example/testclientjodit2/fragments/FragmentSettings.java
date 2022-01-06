package com.example.testclientjodit2.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.testclientjodit2.R;
import com.example.testclientjodit2.activities.LoginActivity;
import com.example.testclientjodit2.api.ServerApi;
import com.example.testclientjodit2.api.ServerController;
import com.example.testclientjodit2.database.DataLoader;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class
FragmentSettings extends Fragment {

    Button btnLogOut;
    String idSession;
    private static final String TAG = "myLogs";
     @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("my_logs", "Fragment3");
        View v = inflater.inflate(R.layout.fragment_settings, null);
         Log.i(TAG, "init fragment_settings");
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
             controller.startRx();
             ServerApi api = controller.getRetrofit().create(ServerApi.class);
             Call<Boolean> answer = api.logoutAPI(idSession);

             answer.enqueue(new Callback<Boolean>() {
                 @Override
                 public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                     getContext().deleteFile(DataLoader.FILE_NAME_USER);
                     getContext().deleteFile(DataLoader.FILE_NAME_SESSION);
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