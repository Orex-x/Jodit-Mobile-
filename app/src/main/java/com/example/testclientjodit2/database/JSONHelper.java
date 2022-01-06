package com.example.testclientjodit2.database;

import android.content.Context;

import com.example.testclientjodit2.activities.MissoinActivity;
import com.example.testclientjodit2.models.Group;
import com.example.testclientjodit2.models.Mission;
import com.example.testclientjodit2.models.User;
import com.example.testclientjodit2.models.UserMission;
import com.example.testclientjodit2.models.UserSession;
import com.google.gson.Gson;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class JSONHelper {
    private static final String FILE_NAME = "data.json";


    public static String exportUserToJSON(User user) {

        Gson gson = new Gson();
        DataItems dataItems = new DataItems();
        dataItems.setUser(user);
        String jsonString = gson.toJson(dataItems);
        return jsonString;
    }


    public static User importUserFromJSON(String jsonString) {

        try{
            Gson gson = new Gson();
            DataItems dataItems = gson.fromJson(jsonString, DataItems.class);
            return dataItems.getUser();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

        return null;
    }

    public static String exportMissionToJSON(Mission mission) {

        Gson gson = new Gson();
        DataItems dataItems = new DataItems();
        dataItems.setMission(mission);
        String jsonString = gson.toJson(dataItems);
        return jsonString;
    }


    public static Mission importMissionFromJSON(String jsonString) {

        try{
            Gson gson = new Gson();
            DataItems dataItems = gson.fromJson(jsonString, DataItems.class);
            return dataItems.getMission();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    public static String exportListUserMissionsToJSON(List<UserMission> missions) {

        Gson gson = new Gson();
        DataItems dataItems = new DataItems();
        dataItems.setUserMissions(missions);
        String jsonString = gson.toJson(dataItems);
        return jsonString;
    }


    public static List<UserMission> importListUserMissionsFromJSON(String jsonString) {

        try{
            Gson gson = new Gson();
            DataItems dataItems = gson.fromJson(jsonString, DataItems.class);
            return dataItems.getUserMissions();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

        return null;
    }

    public static String exportListUserMissionToJSON(UserMission mission) {

        Gson gson = new Gson();
        DataItems dataItems = new DataItems();
        dataItems.setUserMission(mission);
        String jsonString = gson.toJson(dataItems);
        return jsonString;
    }


    public static UserMission importListUserMissionFromJSON(String jsonString) {

        try{
            Gson gson = new Gson();
            DataItems dataItems = gson.fromJson(jsonString, DataItems.class);
            return dataItems.getUserMission();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

        return null;
    }


    public static List<Group> importGroupsFromJSON(String jsonString) {

        try{
            Gson gson = new Gson();
            DataItems dataItems = gson.fromJson(jsonString, DataItems.class);
            return dataItems.getGroup();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

        return null;
    }

    public static String exportGroupsToJSON(List<Group> groups) {

        Gson gson = new Gson();
        DataItems dataItems = new DataItems();
        dataItems.setGroup(groups);
        String jsonString = gson.toJson(dataItems);
        return jsonString;
    }


    private static class DataItems {
        private User user;
        private UserSession userSession;
        private UserMission userMission;
        private Mission mission;
        private List<Group> groups;
        private List<UserMission> userMissions;

        public UserSession getUserSession() {
            return userSession;
        }

        public void setUserSession(UserSession userSession) {
            this.userSession = userSession;
        }

        User getUser() {
            return user;
        }
        void setUser(User user) {
            this.user = user;
        }

        List<Group> getGroup() {
            return groups;
        }
        void setGroup(List<Group> groups) {
            this.groups = groups;
        }

        public List<UserMission> getUserMissions() {
            return userMissions;
        }

        public void setUserMissions(List<UserMission> userMission) {
            this.userMissions = userMission;
        }

        public Mission getMission() {
            return mission;
        }

        public void setMission(Mission mission) {
            this.mission = mission;
        }

        public UserMission getUserMission() {
            return userMission;
        }

        public void setUserMission(UserMission userMission) {
            this.userMission = userMission;
        }
    }
}
