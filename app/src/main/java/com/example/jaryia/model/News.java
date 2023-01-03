package com.example.jaryia.model;

public class News {
    private String info,date,socialMedia;
    private String image;

    public News(String info, String date, String socialMedia, String image) {
        this.info = info;
        this.date = date;
        this.socialMedia = socialMedia;
        this.image = image;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSocialMedia() {
        return socialMedia;
    }

    public void setSocialMedia(String socialMedia) {
        this.socialMedia = socialMedia;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
