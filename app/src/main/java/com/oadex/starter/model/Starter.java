package com.oadex.starter.model;

import android.graphics.Bitmap;

/**
 * Created by Malik on 01-Nov-17.
 */

public class Starter {
    private String picture;
    private String title;
    private String description;
    private String video;
    private String time;

    public Starter(String title, String description, String time, String picture, String video)
    {
        setPicture(picture);
        setVideo(video);
        setTime(time);
        setDescription(description);
        setTitle(title);
    }


    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getVideo()
    {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
