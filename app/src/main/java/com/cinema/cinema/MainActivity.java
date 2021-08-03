package com.cinema.cinema;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import com.cinema.cinema.Adapter.DetiailsPage_Similar_MoviesAdapter;
import com.cinema.cinema.Adapter.HomePageCategoriesAdapter;
import com.cinema.cinema.Adapter.HomePageFeaturedAdapter;
import com.cinema.cinema.Adapter.ImageSliderAdapter;
import com.cinema.cinema.Fragment.CatalogiesFragment;
import com.cinema.cinema.Fragment.HomePageFragment;
import com.cinema.cinema.Fragment.ProfileFragment;
import com.cinema.cinema.Fragment.SearchFragment;
import com.cinema.cinema.Model.Data;
import com.cinema.cinema.Model.DetiailsPage_Similar_Movies;
import com.cinema.cinema.Model.HomePage_Featured;
import com.cinema.cinema.Model.HomePage_categories;
import com.cinema.cinema.Model.MainObject;
import com.cinema.cinema.Model.Movie;
import com.cinema.cinema.Utils.ImdbApiInterface;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements MainActivityViewInterface {
  BottomNavigationView bottomNavigationView;
  public  View root;
  public static List<Movie> moviesList;
  public  List<Movie> featuredMovies;
  boolean doubleBackToExitPressedOnce = false;
  private MainActivityPresenter presenter;
  public String url =
          "imdb/top?start=1&end=20&token=43cce8b1-f534-4f38-a797-aa2b29ce30e0&format=json&data=1";

  @Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    bottomNavigationView = findViewById(R.id.BottomNavigationView);
    presenter = new MainActivityPresenter(this);

    presenter.getMoviesData();

    bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
      switch (item.getItemId()) {
        case R.id.Home:
          goToHomeFragment();
          return true;
        case R.id.Search:
          goToSearchFragment();
          return true;

        case R.id.Profile:
          goToMainFragment(new ProfileFragment());
          return true;
        default: return false;
      }

    }
    );


  }
  /**
     * <p>'p'</p>. OK
     */

  public void goToMainFragment(Fragment fragmentName) {
    Fragment fragment =  fragmentName;
    FragmentTransaction ftConfig = getSupportFragmentManager().beginTransaction();
    ftConfig.replace(R.id.FrameLayout, fragment);
    ftConfig.commit();
  }
  /**
     * <p>'p' </p>. OK
     */

  public void goToSearchFragment() {
    SearchFragment searchFragment = new SearchFragment();
    Fragment fragment =  searchFragment;
    FragmentTransaction ftConfig = getSupportFragmentManager().beginTransaction();
    ftConfig.replace(R.id.FrameLayout, fragment);
    // send movie
    Bundle bundle = new Bundle();
    bundle.putSerializable("moviesList", (Serializable) moviesList);
    fragment.setArguments(bundle);

    ftConfig.commit();
  }

  /**
     * <p>'p' </p>. OK
     */

  public void getMoviesData() {
    Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.myapifilms.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    ImdbApiInterface service = retrofit.create(ImdbApiInterface.class);

    service.getMovies(
            url)
            .enqueue(new Callback<MainObject>() {
              @Override
            public void onResponse(Call<MainObject> call, Response<MainObject> response) {

                featuredMovies = new ArrayList<>();

                MainObject object = response.body();
                Data data = object.getData();
                moviesList =  data.getMovies();

                for (int i = 0; i < 5; i++) {
                    featuredMovies.add(moviesList.get(i));
                }

                goToHomeFragment();

                }

              @Override
            public void onFailure(Call<MainObject> call, Throwable t) {
                Log.d("test", t.getMessage());
              }
            });
  }
  /**
     * <p>'p' </p>. OK
     */

  public void goToHomeFragment() {
    HomePageFragment homePageFragment = new HomePageFragment();
    Fragment fragment =  homePageFragment;
    FragmentTransaction ftConfig = getSupportFragmentManager().beginTransaction();
    ftConfig.replace(R.id.FrameLayout, fragment);
    // send movie
    Bundle bundle = new Bundle();
    bundle.putSerializable("featuredList", (Serializable) featuredMovies);
    bundle.putSerializable("moviesList", (Serializable) moviesList);
    fragment.setArguments(bundle);

    ftConfig.commit();
  }

  @Override
    public void onBackPressed() {
    if (doubleBackToExitPressedOnce) {
      this.finishAffinity();
      System.exit(0);
      return;
    }

    this.doubleBackToExitPressedOnce = true;
    Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

    new Handler().postDelayed(() -> doubleBackToExitPressedOnce = false, 2000);
  }

  @Override
    public void onGetMoviesSuccess(List<Movie> moviesList) {
    featuredMovies = new ArrayList<>();

    for (int i = 0; i < 5; i++) {
      featuredMovies.add(moviesList.get(i));
    }

    goToHomeFragment();
  }

  @Override
    public void onGetMoviesFailed(String message) {
    Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT).show();
  }
}