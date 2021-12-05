package com.example.testclientjodit2.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserGroup {
   
    public int IdUserGroup;

    public int UserId;
    @SerializedName("user")
    @Expose
    public User User;

    public int GroupId;
    @SerializedName("group")
    @Expose
    public Group Group;

    public Boolean IsAdmin;

    public UserGroup(int idUserGroup, int userId, User user,
                     int groupId, Group group, Boolean isAdmin) {
        IdUserGroup = idUserGroup;
        UserId = userId;
        User = user;
        GroupId = groupId;
        Group = group;
        IsAdmin = isAdmin;
    }

    public int getIdUserGroup() {
        return IdUserGroup;
    }

    public void setIdUserGroup(int idUserGroup) {
        IdUserGroup = idUserGroup;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public User getUser() {
        return User;
    }

    public void setUser(User user) {
        User = user;
    }

    public int getGroupId() {
        return GroupId;
    }

    public void setGroupId(int groupId) {
        GroupId = groupId;
    }

    public Group getGroup() {
        return Group;
    }

    public void setGroup(Group group) {
        Group = group;
    }

    public Boolean getAdmin() {
        return IsAdmin;
    }

    public void setAdmin(Boolean admin) {
        IsAdmin = admin;
    }
}
