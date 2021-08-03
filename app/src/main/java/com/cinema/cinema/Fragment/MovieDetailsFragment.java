package com.cinema.cinema.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cinema.cinema.Adapter.DetiailsPage_Similar_MoviesAdapter;
import com.cinema.cinema.MainActivity;
import com.cinema.cinema.Model.DetiailsPage_Similar_Movies;
import com.cinema.cinema.Model.Movie;
import com.cinema.cinema.R;

import java.util.ArrayList;
import java.util.List;


public class MovieDetailsFragment extends Fragment {
    View root ;
    Movie movie;
    ImageView backgroundIv, filmIv;
    TextView movieTitleTv, movieRuntimeTv, movieTypeTv, movieRateTv, movieDescriptionTv;
    Button watchBtn;
    List<Movie> similarMoviesList;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_movie_details, container, false);
        getViews(root);


        Bundle bundle = this.getArguments();
        if(bundle != null){
           movie = (Movie) bundle.getSerializable("clickedMovie");
           //set data
            Glide.with(getContext()).load(movie.getUrlPoster()).into(backgroundIv);
            Glide.with(getContext()).load(movie.getUrlPoster()).into(filmIv);

            String[] genres = movie.getGeners();
            String genresString = String.join(", ", genres);
            if(genres.length != 0) {
                movieTypeTv.setText(genresString);
            }else{
                movieTypeTv.setText("Drama");
            }

            movieTitleTv.setText(movie.getTitle());
            movieRuntimeTv.setText(movie.getRuntime());
            movieRateTv.setText(movie.getRating());
            movieDescriptionTv.setText(movie.getPlot());

            //add similar films
            similarMoviesList = new ArrayList<>();
            List<Movie> moviesList = MainActivity.moviesList;
            moviesList.remove(movie);

            for(int i=0; i<3; i++){
                similarMoviesList.add(moviesList.get(i));
            }
        }

        watchBtn.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(movie.getUrlIMDB()));
            startActivity(browserIntent);
        });








       // recyclerViewSimilar_Movies
        RecyclerView RecyclerVueSimilar_Movies = (RecyclerView) root.findViewById(R.id.RecyclerVueSimilar_Movies);
        DetiailsPage_Similar_MoviesAdapter adapterSimilar_Movies = new DetiailsPage_Similar_MoviesAdapter(getContext(), similarMoviesList, position -> {




        });
        RecyclerVueSimilar_Movies.setHasFixedSize(true);
        RecyclerVueSimilar_Movies.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        RecyclerVueSimilar_Movies.setAdapter(adapterSimilar_Movies);



        return  root ;
    }

    private void getViews(View view){
        backgroundIv =  view.findViewById(R.id.backgroundIv);
        filmIv =  view.findViewById(R.id.filmIv);
        movieTitleTv =  view.findViewById(R.id.movieTitleTv);
        movieRuntimeTv =  view.findViewById(R.id.movieRuntimeTv);
        movieTypeTv =  view.findViewById(R.id.movieTypeTv);
        movieRateTv =  view.findViewById(R.id.movieRateTv);
        movieDescriptionTv =  view.findViewById(R.id.movieDescriptionTv);
        watchBtn = view.findViewById(R.id.watchBtn);
    }
}