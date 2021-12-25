package com.example.testclientjodit2.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class User {

    @SerializedName("idUser")
    @Expose
    public int idUser;

    @SerializedName("firstName")
    @Expose
    public String firstName;

    @SerializedName("secondName")
    @Expose
    public String secondName;


    @SerializedName("lastName")
    @Expose
    public String lastName;


    @SerializedName("login")
    @Expose
    public String login;


    @SerializedName("phone")
    @Expose
    public String phone;


    @SerializedName("email")
    @Expose
    public String email;


    @SerializedName("userPassword")
    @Expose
    public String userPassword;



    @SerializedName("groups")
    @Expose
    // UserGroup
    public List<Group> groups;
    
    public List<UserGroup> userGroups;


    @SerializedName("groupInvitations")
    @Expose
    // GroupInvite
    public List<GroupInvite> groupInvitations;

    @SerializedName("groupApplications")
    @Expose
    public List<GroupInvite> groupApplications;


    @SerializedName("authors")
    @Expose
    // UserJoditTask
    public List<UserMission> authors;


    @SerializedName("executors")
    @Expose
    public List<UserMission> executors;

    // ScheduleChange
    @SerializedName("scheduleChangesBeforeUsers")
    @Expose
    public List<ScheduleChange> scheduleChangesBeforeUsers;

    @SerializedName("scheduleChangesAfterUsers")
    @Expose
    public List<ScheduleChange> scheduleChangesAfterUsers;

    // ScheduleStatement
    @SerializedName("beforeUsers")
    @Expose
    public List<ScheduleStatement> beforeUsers;


    public User(int idUser, String firstName, String secondName, String lastName,
                String login, String phone, String email, String userPassword, List<Group> groups,
                List<UserGroup> userGroups, List<GroupInvite> groupInvitations,
                List<GroupInvite> groupApplications, List<UserMission> authors,
                List<UserMission> executors, List<ScheduleChange> scheduleChangesBeforeUsers,
                List<ScheduleChange> scheduleChangesAfterUsers,
                List<ScheduleStatement> beforeUsers) {
        this.idUser = idUser;
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.login = login;
        this.phone = phone;
        this.email = email;
        this.userPassword = userPassword;
        this.groups = groups;
        this.userGroups = userGroups;
        this.groupInvitations = groupInvitations;
        this.groupApplications = groupApplications;
        this.authors = authors;
        this.executors = executors;
        this.scheduleChangesBeforeUsers = scheduleChangesBeforeUsers;
        this.scheduleChangesAfterUsers = scheduleChangesAfterUsers;
        this.beforeUsers = beforeUsers;
    }
}
