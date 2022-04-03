package com.example.homeworkandroid.homework004.models;

public class User
{
    private String
            login, pass, email,phone;

    public User(){};

    public User(String login, String pass, String email, String phone){
        this.login = login;
        this.pass = pass;
        this.email = email;
        this.phone = phone;
    };

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
