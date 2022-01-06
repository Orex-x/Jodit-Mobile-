package com.example.testclientjodit2.api;

import com.example.testclientjodit2.models.Group;
import com.example.testclientjodit2.models.Mission;
import com.example.testclientjodit2.models.User;
import com.example.testclientjodit2.models.UserMission;
import com.example.testclientjodit2.models.viewModels.LoginModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;


public interface ServerApi {

        @GET("GetUserAPI")
        Observable<User> getUserAPI(@Query("idSession") String idSession);


        @GET("GetListMissionsExecutors")
        Observable<List<UserMission>> getListMissionsExecutors(@Query("idSession") String idSession);

        @GET("GetMissionsExecutors")
        Observable<List<Mission>> getMissionsExecutors(@Query("idSession") String idSession);

        @GET("TakeMission")
        Observable<Boolean> takeMission(@Query("idMission") int idMission,
                                  @Query("idSession") String idSession);

        @GET("RefuseMission")
        Observable<Boolean> refuseMission(@Query("idMission") int idMission,
                                    @Query("idSession") String idSession);

        @DELETE("CloseSessionAPI")
        void closeSession(@Query("idSession") String idSession);

        @POST("LoginAPI")
        Observable<String> loginAPI(@Body LoginModel model);

        @DELETE("LogoutAPI")
        Call<Boolean> logoutAPI(@Query("idSession") String idSession);

        @POST("AddGroup")
        Call<Integer> addGroup(@Query("idSession") String idSession, @Body Group group);

}
