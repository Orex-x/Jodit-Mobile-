package com.example.testclientjodit2.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserMission {
    
    public int IdUserMission;
        
      
    public int GroupId;
    @SerializedName("group")
    @Expose
    public Group Group;
        

    public int AuthorId;
    @SerializedName("author")
    @Expose
    public User Author;
        
        

    public int ExecutorId;
    @SerializedName("executor")
    @Expose
    public User Executor;
        

    public int MissionId;
    @SerializedName("mission")
    @Expose
    public Mission Mission;

    @SerializedName("status")
    @Expose
    public String Status;

    public UserMission(int idUserMission, int groupId, Group group,
                       int authorId, User author, int executorId,
                       User executor, int missionId,
                       Mission mission, String status) {
        IdUserMission = idUserMission;
        GroupId = groupId;
        Group = group;
        AuthorId = authorId;
        Author = author;
        ExecutorId = executorId;
        Executor = executor;
        MissionId = missionId;
        Mission = mission;
        Status = status;
    }

    public int getIdUserMission() {
        return IdUserMission;
    }

    public void setIdUserMission(int idUserMission) {
        IdUserMission = idUserMission;
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

    public int getAuthorId() {
        return AuthorId;
    }

    public void setAuthorId(int authorId) {
        AuthorId = authorId;
    }

    public User getAuthor() {
        return Author;
    }

    public void setAuthor(User author) {
        Author = author;
    }

    public int getExecutorId() {
        return ExecutorId;
    }

    public void setExecutorId(int executorId) {
        ExecutorId = executorId;
    }

    public User getExecutor() {
        return Executor;
    }

    public void setExecutor(User executor) {
        Executor = executor;
    }

    public int getMissionId() {
        return MissionId;
    }

    public void setMissionId(int missionId) {
        MissionId = missionId;
    }

    public Mission getMission() {
        return Mission;
    }

    public void setMission(Mission mission) {
        Mission = mission;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
