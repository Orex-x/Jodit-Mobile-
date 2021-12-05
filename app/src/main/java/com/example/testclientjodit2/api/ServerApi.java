package com.example.testclientjodit2.api;

import com.example.testclientjodit2.models.Group;
import com.example.testclientjodit2.models.User;
import com.example.testclientjodit2.models.UserGroup;
import com.example.testclientjodit2.models.viewModels.LoginModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ServerApi {

        @GET("Account/GetUser")
        Call<User> getUser(@Query("idSession") String idSession);

        @GET("Account/GetUserGroup")
        Call<List<UserGroup>> getUserGroup(@Query("idSession") String idSession);

        @DELETE("Account/CloseSession")
        void closeSession(@Query("idSession") String idSession);

        @POST("Account/LoginAPI")
        Call<String> login(@Body LoginModel model);
}
