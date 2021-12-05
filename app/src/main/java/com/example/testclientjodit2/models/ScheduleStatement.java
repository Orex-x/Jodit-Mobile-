package com.example.testclientjodit2.models;

import java.sql.Date;

public class ScheduleStatement {
    
    public int IdScheduleStatement;

    public int BeforeUserId;
    public User BeforeUser;


    public int GroupId;
    public Group Group;
    
    public Date ReplacementDate;
    
    public String Comment;

    public ScheduleStatement(int idScheduleStatement, int beforeUserId,
                             User beforeUser, int groupId,
                             Group group,
                             Date replacementDate, String comment) {
        IdScheduleStatement = idScheduleStatement;
        BeforeUserId = beforeUserId;
        BeforeUser = beforeUser;
        GroupId = groupId;
        Group = group;
        ReplacementDate = replacementDate;
        Comment = comment;
    }

    public int getIdScheduleStatement() {
        return IdScheduleStatement;
    }

    public void setIdScheduleStatement(int idScheduleStatement) {
        IdScheduleStatement = idScheduleStatement;
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

    public Date getReplacementDate() {
        return ReplacementDate;
    }

    public void setReplacementDate(Date replacementDate) {
        ReplacementDate = replacementDate;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }
}
