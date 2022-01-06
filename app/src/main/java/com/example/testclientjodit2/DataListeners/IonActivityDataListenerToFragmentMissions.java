package com.example.testclientjodit2.DataListeners;

import com.example.testclientjodit2.models.UserMission;

import java.util.List;

public interface IonActivityDataListenerToFragmentMissions {
    void onActivityDataListener(List<UserMission> list);
    void setChange(String jsonUserMission);
}
