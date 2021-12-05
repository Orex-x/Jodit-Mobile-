package com.example.testclientjodit2.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserSession {
    public int IdUserGroup;

    public int UserId;
    @SerializedName("user")
    @Expose
    public User User;


    @SerializedName("idSession")
    @Expose
    public String idSession;
}
