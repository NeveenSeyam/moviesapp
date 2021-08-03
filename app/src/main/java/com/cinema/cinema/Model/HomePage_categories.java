package com.cinema.cinema.Model;

public class HomePage_categories {
    int img ;
    String cagTitle;

    public HomePage_categories(int img, String cagTitle) {
        this.img = img;
        this.cagTitle = cagTitle;
    }


    public int getImg() {
        return img;
    }

    public String getCagTitle() {
        return cagTitle;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public void setCagTitle(String cagTitle) {
        this.cagTitle = cagTitle;
    }


}
