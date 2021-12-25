package com.example.testclientjodit2.api;

import com.example.testclientjodit2.activities.MissoinActivity;
import com.example.testclientjodit2.models.Group;
import com.example.testclientjodit2.models.Mission;
import com.example.testclientjodit2.models.User;
import com.example.testclientjodit2.models.UserSession;
import com.example.testclientjodit2.models.viewModels.LoginModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ServerApi {

        @GET("GetUserAPI")
        Call<User> GetUserAPI(@Query("idSession") String idSession);

        @GET("GetMissionsExecutors")
        Call<List<Mission>> GetMissionsExecutors(@Query("idSession") String idSession);

        @DELETE("CloseSessionAPI")
        void closeSession(@Query("idSession") String idSession);

        @POST("LoginAPI")
        Call<String> LoginAPI(@Body LoginModel model);

        @DELETE("LogoutAPI")
        Call<Boolean> LogoutAPI(@Query("idSession") String idSession);

        @POST("AddGroup")
        Call<Integer> AddGroup(@Query("idSession") String idSession, @Body Group group);

}
