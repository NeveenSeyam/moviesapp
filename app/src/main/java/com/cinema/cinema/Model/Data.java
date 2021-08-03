package com.cinema.cinema.Model;

import java.util.List;

public class Data {
    List<Movie> movies;

    public Data(){}

    public Data(List<Movie> movies) {
        this.movies = movies;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}