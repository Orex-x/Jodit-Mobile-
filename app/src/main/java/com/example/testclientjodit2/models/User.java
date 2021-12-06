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

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public List<UserGroup> getUserGroups() {
        return userGroups;
    }

    public void setUserGroups(List<UserGroup> userGroups) {
        this.userGroups = userGroups;
    }

    public List<GroupInvite> getGroupInvitations() {
        return groupInvitations;
    }

    public void setGroupInvitations(List<GroupInvite> groupInvitations) {
        this.groupInvitations = groupInvitations;
    }

    public List<GroupInvite> getGroupApplications() {
        return groupApplications;
    }

    public void setGroupApplications(List<GroupInvite> groupApplications) {
        this.groupApplications = groupApplications;
    }

    public List<UserMission> getAuthors() {
        return authors;
    }

    public void setAuthors(List<UserMission> authors) {
        this.authors = authors;
    }

    public List<UserMission> getExecutors() {
        return executors;
    }

    public void setExecutors(List<UserMission> executors) {
        this.executors = executors;
    }

    public List<ScheduleChange> getScheduleChangesBeforeUsers() {
        return scheduleChangesBeforeUsers;
    }

    public void setScheduleChangesBeforeUsers(List<ScheduleChange> scheduleChangesBeforeUsers) {
        this.scheduleChangesBeforeUsers = scheduleChangesBeforeUsers;
    }

    public List<ScheduleChange> getScheduleChangesAfterUsers() {
        return scheduleChangesAfterUsers;
    }

    public void setScheduleChangesAfterUsers(List<ScheduleChange> scheduleChangesAfterUsers) {
        this.scheduleChangesAfterUsers = scheduleChangesAfterUsers;
    }

    public List<ScheduleStatement> getBeforeUsers() {
        return beforeUsers;
    }

    public void setBeforeUsers(List<ScheduleStatement> beforeUsers) {
        this.beforeUsers = beforeUsers;
    }
}
