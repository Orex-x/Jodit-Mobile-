package com.example.testclientjodit2.models;

import java.sql.Date;
import java.util.List;

public class Mission {
          
    public int IdMission;

    public String Title;
        
    public String Description;
        
      
    public Date Deadline;
        
      
    public Date DateOfCreation;
    
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
