package com.cinema.cinema.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import com.cinema.cinema.Adapter.HomePageCategoriesAdapter;
import com.cinema.cinema.Adapter.HomePageFeaturedAdapter;
import com.cinema.cinema.Adapter.ImageSliderAdapter;
import com.cinema.cinema.Model.HomePage_categories;
import com.cinema.cinema.Model.Movie;
import com.cinema.cinema.R;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class HomePageFragment extends Fragment {
  View root;
  ArrayList<HomePage_categories> myListDataCategories;
  List<Movie> featuredList;
  List<Movie> moviesList;
  List<String> sliderImageUrls;
  Movie movie;

  @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    root = inflater.inflate(R.layout.fragment_home_page, container, false);




    Bundle bundle = this.getArguments();
    if (bundle != null) {

      featuredList = (List<Movie>) bundle.getSerializable("featuredList");
      moviesList = (List<Movie>) bundle.getSerializable("moviesList");



      // recyclerViewFeature
      RecyclerView recyclerView =  root.findViewById(R.id.RecyclerView);
      HomePageFeaturedAdapter adapter = new HomePageFeaturedAdapter(getContext(),
              featuredList, position -> {
                movie = featuredList.get(position);
                goToDetailsFragment(getActivity(), movie);
      });
      recyclerView.setHasFixedSize(true);
      recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),
              LinearLayoutManager.HORIZONTAL, false));
      recyclerView.setAdapter(adapter);

      myListDataCategories = new ArrayList<>();
      sliderImageUrls = new ArrayList<>();

      for (int i = 0; i < featuredList.size(); i++) {
        sliderImageUrls.add(featuredList.get(i).getUrlPoster());
        Collections.reverse(sliderImageUrls);
      }

      ViewPager viewPager = (ViewPager) root.findViewById(R.id.imageView3);
      ImageSliderAdapter adapterView = new ImageSliderAdapter(getContext(), sliderImageUrls);
      viewPager.setAdapter(adapterView);

      myListDataCategories.add(
                    new HomePage_categories(R.drawable.img2, "Drama"));
      myListDataCategories.add(
                    new HomePage_categories(R.drawable.img1, "Crime")
      );
      myListDataCategories.add(
                    new HomePage_categories(R.drawable.img2, "Adventure")
      );




    }






    // recyclerViewCategories
    RecyclerView recyclerViewCategories =
             (RecyclerView) root.findViewById(R.id.CategoriesRecyclerView);
    HomePageCategoriesAdapter adapterCategories =
                new HomePageCategoriesAdapter(myListDataCategories, position -> {

                  goToCategoryFragment(myListDataCategories.get(position).getCagTitle());

                });
    recyclerViewCategories.setHasFixedSize(true);
    recyclerViewCategories.setLayoutManager(new LinearLayoutManager(getContext(),
            LinearLayoutManager.HORIZONTAL, false));
    recyclerViewCategories.setAdapter(adapterCategories);

    return  root;
  }
  /**
     * <p>'p' </p>. OK
   */

  public static void goToDetailsFragment(FragmentActivity activity, Movie m) {

    MovieDetailsFragment detailsFragment = new MovieDetailsFragment();
    Fragment fragment =  detailsFragment;
    FragmentTransaction ftConfig = activity.getSupportFragmentManager().beginTransaction();
    ftConfig.replace(R.id.FrameLayout, fragment);
    // send movie
    Bundle bundle = new Bundle();
    bundle.putSerializable("clickedMovie", (Serializable) m);
    fragment.setArguments(bundle);

    ftConfig.commit();
  }
  /**
     * <p>'p' </p>. OK
     */

  public void goToCategoryFragment(String catName) {

    CatalogiesFragment catFragment = new CatalogiesFragment();
    Fragment fragment =  catFragment;
    FragmentTransaction ftConfig = getFragmentManager().beginTransaction();
    ftConfig.replace(R.id.FrameLayout, fragment);
    // send movie
    Bundle bundle = new Bundle();
    bundle.putString("catName", catName);
    fragment.setArguments(bundle);

    ftConfig.commit();
  }


}