package com.example.testclientjodit2.models;

import java.sql.Date;

public class ScheduleChange {
    
    public int IdScheduleChange;
        
     
    public int BeforeUserId;
    public User BeforeUser;
        

    public int AfterUserId;
    public User AfterUser;
        

    public int GroupId;
    public Group Group;
        

    public Date AfterUserDate;
        

    public Date BeforeUserDate;


    public ScheduleChange(int idScheduleChange, int beforeUserId,
                          User beforeUser, int afterUserId, User afterUser,
                          int groupId, Group group,
                          Date afterUserDate, Date beforeUserDate) {
        IdScheduleChange = idScheduleChange;
        BeforeUserId = beforeUserId;
        BeforeUser = beforeUser;
        AfterUserId = afterUserId;
        AfterUser = afterUser;
        GroupId = groupId;
        Group = group;
        AfterUserDate = afterUserDate;
        BeforeUserDate = beforeUserDate;
    }

    public int getIdScheduleChange() {
        return IdScheduleChange;
    }

    public void setIdScheduleChange(int idScheduleChange) {
        IdScheduleChange = idScheduleChange;
    }

    public int getBeforeUserId() {
        return BeforeUserId;
    }

    public void setBeforeUserId(int beforeUserId) {
        BeforeUserId = beforeUserId;
    }

    public User getBeforeUser() {
        return BeforeUser;
    }

    public void setBeforeUser(User beforeUser) {
        BeforeUser = beforeUser;
    }

    public int getAfterUserId() {
        return AfterUserId;
    }

    public void setAfterUserId(int afterUserId) {
        AfterUserId = afterUserId;
    }

    public User getAfterUser() {
        return AfterUser;
    }

    public void setAfterUser(User afterUser) {
        AfterUser = afterUser;
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

    public Date getAfterUserDate() {
        return AfterUserDate;
    }

    public void setAfterUserDate(Date afterUserDate) {
        AfterUserDate = afterUserDate;
    }

    public Date getBeforeUserDate() {
        return BeforeUserDate;
    }

    public void setBeforeUserDate(Date beforeUserDate) {
        BeforeUserDate = beforeUserDate;
    }
}
