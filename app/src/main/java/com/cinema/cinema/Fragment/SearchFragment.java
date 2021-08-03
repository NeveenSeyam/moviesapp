package com.cinema.cinema.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.cinema.cinema.Adapter.HomePageFeaturedAdapter;
import com.cinema.cinema.Model.HomePage_Featured;
import com.cinema.cinema.Model.Movie;
import com.cinema.cinema.R;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {

  View root;
  List<Movie> moviesList;
    ArrayList<HomePage_Featured> SrearchArray;
    SearchView searchView;
    Movie movie;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root =   inflater.inflate(R.layout.fragment_search, container, false);
        searchView = root.findViewById(R.id.SearchView);

        Bundle bundle = this.getArguments();
        if(bundle != null){
            moviesList = (List<Movie>) bundle.getSerializable("moviesList");

            // recyclerViewFeature
            RecyclerView recyclerView =  root.findViewById(R.id.result_list);
            HomePageFeaturedAdapter adapter = new HomePageFeaturedAdapter(getContext(), moviesList, position -> {
                movie = moviesList.get(position);
                HomePageFragment.goToDetailsFragment(getActivity(), movie);

            });
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(),  2));
            recyclerView.setAdapter(adapter);
        }



        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }});



        return root ;

    }
}