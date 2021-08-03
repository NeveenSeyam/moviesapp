package com.cinema.cinema.Model;

public class HomePage_Featured {
    String title;
    String cataloge;
    String retting;
    String date;

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    int img;

    public HomePage_Featured(String title, String cataloge, String retting, String date , int img) {
        this.title = title;
        this.cataloge = cataloge;
        this.retting = retting;
        this.date = date;
        this.img = img;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCataloge(String cataloge) {
        this.cataloge = cataloge;
    }

    public void setRetting(String retting) {
        this.retting = retting;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public String getCataloge() {
        return cataloge;
    }

    public String getRetting() {
        return retting;
    }

    public String getDate() {
        return date;
    }
}
