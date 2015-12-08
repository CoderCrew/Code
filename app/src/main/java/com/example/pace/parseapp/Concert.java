package com.example.pace.parseapp;

/**
 * Created by pace on 2/11/15.
 */
public class Concert {

    private String title;
    private String link;
    private String image_url;

    public Concert(String title, String link, String image_url) {
        this.title = title;
        this.link = link;
        this.image_url = image_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
