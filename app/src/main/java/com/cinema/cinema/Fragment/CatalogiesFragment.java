package com.cinema.cinema.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cinema.cinema.Adapter.HomePageCategoriesAdapter;
import com.cinema.cinema.Adapter.HomePageFeaturedAdapter;
import com.cinema.cinema.MainActivity;
import com.cinema.cinema.Model.Movie;
import com.cinema.cinema.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CatalogiesFragment extends Fragment {
    TextView categoryTv;
    View root;
    List<Movie> moviesList, catMoviesList;
    String catName;
    RecyclerView RecyclerViewCatalogesItme;
    Movie movie;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_catalogies, container, false);
        categoryTv = root.findViewById(R.id.categoryTv) ;


        Bundle bundle = this.getArguments();
        if (bundle != null) {
            catName = bundle.getString("catName");
            categoryTv.setText(catName);
        }

        moviesList = MainActivity.moviesList;
        catMoviesList = new ArrayList<>();

        for(Movie m : moviesList){
            String[] genres = m.getGeners();
            boolean isExist = useArraysBinarySearch(genres, catName);
            if(isExist){
                catMoviesList.add(m);
            }
        }

        // category movies list
        RecyclerViewCatalogesItme = root.findViewById(R.id.RecyclerViewCatalogesItme);
        HomePageFeaturedAdapter adapter = new HomePageFeaturedAdapter(getContext(), catMoviesList, position -> {
            movie = moviesList.get(position);
            HomePageFragment.goToDetailsFragment(getActivity(), movie);
        });
        RecyclerViewCatalogesItme.setHasFixedSize(true);
        RecyclerViewCatalogesItme.setLayoutManager(new GridLayoutManager(getContext(),  2));
        RecyclerViewCatalogesItme.setAdapter(adapter);

        return root;

    }

    public static boolean useArraysBinarySearch(String[] arr, String targetValue) {
        int a =  Arrays.binarySearch(arr, targetValue);
        if(a > 0)
            return true;
        else
            return false;
    }
}