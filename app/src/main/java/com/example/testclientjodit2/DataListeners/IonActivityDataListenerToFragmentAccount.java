package com.example.testclientjodit2.DataListeners;

import com.example.testclientjodit2.models.Group;

import java.util.List;

public interface IonActivityDataListenerToFragmentAccount {
    void onActivityDataListener(
            String firstName, String secondName, List<Group> groups);
}
