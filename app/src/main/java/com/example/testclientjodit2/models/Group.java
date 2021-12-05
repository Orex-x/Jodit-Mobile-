package com.example.testclientjodit2.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.sql.Date;
import java.util.List;

public class Group {
    
    public int IdGroup;

    @SerializedName("groupName")
    @Expose
    public String GroupName;

    @SerializedName("description")
    @Expose
    public String Description;

    @SerializedName("dateOfCreation")
    @Expose
    public String DateOfCreation;

    public Boolean IsPrivate;

    public List<User> Users;

    @SerializedName("userGroups")
    @Expose
    public List<UserGroup> UserGroups;
    
    public List<GroupInvite> GroupInvites;
    
    public List<UserMission> UserMissions;
    
    public List<ScheduleChange> ScheduleChanges;
    
    public List<ScheduleStatement> ScheduleStatements;

    public Group(int idGroup, String groupName, String description,
                 String dateOfCreation, Boolean isPrivate, List<User> users, List<UserGroup> userGroups,
                 List<GroupInvite> groupInvites, List<UserMission> userMissions, List<ScheduleChange> scheduleChanges,
                 List<ScheduleStatement> scheduleStatements) {
        IdGroup = idGroup;
        GroupName = groupName;
        Description = description;
        DateOfCreation = dateOfCreation;
        IsPrivate = isPrivate;
        Users = users;
        UserGroups = userGroups;
        GroupInvites = groupInvites;
        UserMissions = userMissions;
        ScheduleChanges = scheduleChanges;
        ScheduleStatements = scheduleStatements;
    }

    public int getIdGroup() {
        return IdGroup;
    }

    public void setIdGroup(int idGroup) {
        IdGroup = idGroup;
    }

    public String getGroupName() {
        return GroupName;
    }

    public void setGroupName(String groupName) {
        GroupName = groupName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getDateOfCreation() {
        return DateOfCreation;
    }

    public void setDateOfCreation(String dateOfCreation) {
        DateOfCreation = dateOfCreation;
    }

    public Boolean getPrivate() {
        return IsPrivate;
    }

    public void setPrivate(Boolean aPrivate) {
        IsPrivate = aPrivate;
    }

    public List<User> getUsers() {
        return Users;
    }

    public void setUsers(List<User> users) {
        Users = users;
    }

    public List<UserGroup> getUserGroups() {
        return UserGroups;
    }

    public void setUserGroups(List<UserGroup> userGroups) {
        UserGroups = userGroups;
    }

    public List<GroupInvite> getGroupInvites() {
        return GroupInvites;
    }

    public void setGroupInvites(List<GroupInvite> groupInvites) {
        GroupInvites = groupInvites;
    }

    public List<UserMission> getUserMissions() {
        return UserMissions;
    }

    public void setUserMissions(List<UserMission> userMissions) {
        UserMissions = userMissions;
    }

    public List<ScheduleChange> getScheduleChanges() {
        return ScheduleChanges;
    }

    public void setScheduleChanges(List<ScheduleChange> scheduleChanges) {
        ScheduleChanges = scheduleChanges;
    }

    public List<ScheduleStatement> getScheduleStatements() {
        return ScheduleStatements;
    }

    public void setScheduleStatements(List<ScheduleStatement> scheduleStatements) {
        ScheduleStatements = scheduleStatements;
    }
}
