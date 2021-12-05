package com.example.testclientjodit2.models.viewModels;

public class LoginModel {
    public String Email;
    public String Password;

    public LoginModel(String login, String password) {
        Email = login;
        Password = password;
    }

    public String getLogin() {
        return Email;
    }

    public void setLogin(String login) {
        Email = login;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
