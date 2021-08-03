package com.cinema.cinema.Model;

import java.io.Serializable;

public class Movie implements Serializable {
    String title, year, urlPoster, simplePlot, urlIMDB, type, rating, runtime;
    String[] genres;

    public Movie(){}

    public Movie(String title, String year, String urlPoster, String simplePlot, String urlIMDB, String type, String rating, String[] genres) {
        this.title = title;
        this.year = year;
        this.urlPoster = urlPoster;
        this.simplePlot = simplePlot;
        this.urlIMDB = urlIMDB;
        this.type = type;
        this.rating = rating;
        this.genres = genres;
        this.runtime = runtime;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getUrlPoster() {
        return urlPoster;
    }

    public void setUrlPoster(String urlPoster) {
        this.urlPoster = urlPoster;
    }

    public String getPlot() {
        return simplePlot;
    }

    public void setPlot(String plot) {
        this.simplePlot = simplePlot;
    }

    public String getUrlIMDB() {
        return urlIMDB;
    }

    public void setUrlIMDB(String urlIMDB) {
        this.urlIMDB = urlIMDB;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String[] getGeners() {
        return genres;
    }

    public void setGeners(String[] geners) {
        this.genres = geners;
    }


}
