package com.cinema.cinema;


import android.util.Log;
import com.cinema.cinema.Model.Data;
import com.cinema.cinema.Model.MainObject;
import com.cinema.cinema.Utils.ImdbApiInterface;
import java.util.ArrayList;
import static com.cinema.cinema.MainActivity.moviesList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivityPresenter implements MainActivityPresenterInterface {
  private MainActivityViewInterface view;
  public  String url =
          "imdb/top?start=1&end=20&token=43cce8b1-f534-4f38-a797-aa2b29ce30e0&format=json&data=1";

  public MainActivityPresenter(MainActivityViewInterface view) {
    this.view = view;
  }

  @Override
    public void getMoviesData() {

    Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.myapifilms.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    ImdbApiInterface service = retrofit.create(ImdbApiInterface.class);

    service.getMovies(url).enqueue(new Callback<MainObject>() {
      @Override
            public void onResponse(Call<MainObject> call, Response<MainObject> response) {
        MainObject object = response.body();
        Data data = object.getData();
        moviesList =  data.getMovies();
        view.onGetMoviesSuccess(moviesList);
      }

      @Override
            public void onFailure(Call<MainObject> call, Throwable t) {
          view.onGetMoviesFailed(t.getMessage());
      }
        });
  }
}