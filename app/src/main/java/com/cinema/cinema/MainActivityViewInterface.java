package com.cinema.cinema;

import com.cinema.cinema.Model.Movie;
import java.util.List;

public interface MainActivityViewInterface {
  void onGetMoviesSuccess(List<Movie> moviesList);

  void onGetMoviesFailed(String message);
}
