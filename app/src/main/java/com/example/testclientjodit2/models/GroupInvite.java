package com.example.testclientjodit2.models;

public class GroupInvite {
   
    public int IdGroupInvite;
        

    public int InvitedUserId;
    public User InvitedUser;
        

    public int InvitingUserId;
    public User InvitingUser;
        

    public int GroupId;
    public Group Group;
        

    public String Title;

    public GroupInvite(int idGroupInvite, int invitedUserId, User invitedUser,
                       int invitingUserId, User invitingUser,
                       int groupId, Group group, String title) {
        IdGroupInvite = idGroupInvite;
        InvitedUserId = invitedUserId;
        InvitedUser = invitedUser;
        InvitingUserId = invitingUserId;
        InvitingUser = invitingUser;
        GroupId = groupId;
        Group = group;
        Title = title;
    }

    public int getIdGroupInvite() {
        return IdGroupInvite;
    }

    public void setIdGroupInvite(int idGroupInvite) {
        IdGroupInvite = idGroupInvite;
    }

    public int getInvitedUserId() {
        return InvitedUserId;
    }

    public void setInvitedUserId(int invitedUserId) {
        InvitedUserId = invitedUserId;
    }

    public User getInvitedUser() {
        return InvitedUser;
    }

    public void setInvitedUser(User invitedUser) {
        InvitedUser = invitedUser;
    }

    public int getInvitingUserId() {
        return InvitingUserId;
    }

    public void setInvitingUserId(int invitingUserId) {
        InvitingUserId = invitingUserId;
    }

    public User getInvitingUser() {
        return InvitingUser;
    }

    public void setInvitingUser(User invitingUser) {
        InvitingUser = invitingUser;
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

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }
}
