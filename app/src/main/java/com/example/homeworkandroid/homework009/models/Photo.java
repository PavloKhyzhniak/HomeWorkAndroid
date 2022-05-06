package com.example.homeworkandroid.homework009.models;

public class Photo {
    private long albumID;
    private long id;
    private String title;
    private String url;
    private String thumbnailURL;

    public long getAlbumID() { return albumID; }
    public void setAlbumID(long value) { this.albumID = value; }

    public long getID() { return id; }
    public void setID(long value) { this.id = value; }

    public String getTitle() { return title; }
    public void setTitle(String value) { this.title = value; }

    public String getURL() { return url; }
    public void setURL(String value) { this.url = value; }

    public String getThumbnailURL() { return thumbnailURL; }
    public void setThumbnailURL(String value) { this.thumbnailURL = value; }
}
