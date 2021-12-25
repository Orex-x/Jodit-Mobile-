package com.example.testclientjodit2.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.sql.Date;
import java.util.List;

public class Mission {

    @SerializedName("idMission")
    @Expose
    public int IdMission;

    @SerializedName("title")
    @Expose
    public String Title;

    @SerializedName("description")
    @Expose
    public String Description;


    public Date Deadline;


    public Date DateOfCreation;

    @SerializedName("userMissions")
    @Expose
    public List<UserMission> UserMissions;

    public Mission(int idMission, String title, String description, Date deadline, Date dateOfCreation, List<UserMission> userMissions) {
        IdMission = idMission;
        Title = title;
        Description = description;
        Deadline = deadline;
        DateOfCreation = dateOfCreation;
        UserMissions = userMissions;
    }

    public int getIdMission() {
        return IdMission;
    }

    public void setIdMission(int idMission) {
        IdMission = idMission;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Date getDeadline() {
        return Deadline;
    }

    public void setDeadline(Date deadline) {
        Deadline = deadline;
    }

    public Date getDateOfCreation() {
        return DateOfCreation;
    }

    public void setDateOfCreation(Date dateOfCreation) {
        DateOfCreation = dateOfCreation;
    }

    public List<UserMission> getUserMissions() {
        return UserMissions;
    }

    public void setUserMissions(List<UserMission> userMissions) {
        UserMissions = userMissions;
    }
}
