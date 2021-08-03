package com.cinema.cinema.Fragment;


import android.os.Bundle;
import androidx.fragment.app.testing.FragmentScenario;
import com.cinema.cinema.MainActivityPresenter;
import com.cinema.cinema.MainActivityViewInterface;
import com.cinema.cinema.Model.Movie;
import com.cinema.cinema.R;
import com.google.firebase.auth.FirebaseAuth;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(MockitoJUnitRunner.class)
public class HomePageFragmentUITest implements MainActivityViewInterface {
    CountDownLatch latch = new CountDownLatch(1);
    List<Movie> moviesList;

    MainActivityPresenter presenter = new MainActivityPresenter(this);

    @BeforeClass
    public static void beforeClass(){
        FirebaseAuth.getInstance().signInWithEmailAndPassword("test@test.com", "test123");
    }

    public FragmentScenario<HomePageFragment> fragment;

    @Before
    public void setup() throws InterruptedException {
        presenter.getMoviesData();
        latch.await(5, TimeUnit.SECONDS);
        Bundle bundle = new Bundle();
        bundle.putSerializable("featuredList", (Serializable) moviesList.subList(0, 4));
        bundle.putSerializable("moviesList", (Serializable) moviesList);
        fragment = FragmentScenario.launchInContainer(HomePageFragment.class, bundle);
    }

    @Test
    public void checkImageSliderDisplayed()  {
        onView(withId(R.id.imageView3)).check(matches(isDisplayed()));
    }

    @Override
    public void onGetMoviesSuccess(List<Movie> moviesList)
    {
        this.moviesList = moviesList;
    }

    @Override
    public void onGetMoviesFailed(String message) {

    }
}