package com.example.homeworkandroid.homework009.models;

public class Album
{

    private long userID;
    private long id;
    private String title;

    public static String TAG_TITLE = "title";
    public static String TAG_ID = "id";
    public static String TAG_USERID = "userId";

    public long getUserID() { return userID; }
    public void setUserID(long value) { this.userID = value; }

    public long getID() { return id; }
    public void setID(long value) { this.id = value; }

    public String getTitle() { return title; }
    public void setTitle(String value) { this.title = value; }
}
