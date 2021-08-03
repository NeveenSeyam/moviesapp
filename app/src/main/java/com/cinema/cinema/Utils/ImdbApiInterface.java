package com.cinema.cinema.Utils;

import com.cinema.cinema.Model.Data;
import com.cinema.cinema.Model.MainObject;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ImdbApiInterface {

    @GET
    Call<MainObject> getMovies(@Url String url);

}