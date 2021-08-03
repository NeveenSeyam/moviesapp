package com.cinema.cinema.Fragment;


import android.os.Bundle;

import androidx.fragment.app.testing.FragmentScenario;

import com.cinema.cinema.MainActivity;
import com.cinema.cinema.Model.Movie;
import com.cinema.cinema.R;
import com.google.firebase.auth.FirebaseAuth;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(JUnit4ClassRunner.class)
public class MovieDetailsFragmentUITest {
    CountDownLatch latch = new CountDownLatch(1);
    Movie movie;

    @BeforeClass
    public static void beforeClass(){
        FirebaseAuth.getInstance().signInWithEmailAndPassword("test@test.com", "test123");

    }

    public FragmentScenario<MovieDetailsFragment> fragment;

    public void initMovies(){
        movie = new Movie();
        movie.setTitle("Avengers: Infinity War");
        movie.setYear("1999");
        movie.setUrlPoster("https://www.joblo.com/assets/images/joblo/posters/2019/01/IO-poster-1.jpg");
        movie.setUrlIMDB("https://www.imdb.com");
        movie.setPlot("Avengers: Infinity War");
        movie.setRating("7.9");
        movie.setType("Action");
        movie.setGeners(new String[]{"Action", "Drama"});
        movie.setRuntime("120");

        MainActivity.moviesList = new ArrayList<>();
        MainActivity.moviesList.add(movie);
        MainActivity.moviesList.add(movie);
        MainActivity.moviesList.add(movie);
        MainActivity.moviesList.add(movie);
        MainActivity.moviesList.add(movie);
    }

    @Before
    public void setup() throws InterruptedException {
        initMovies();

        Bundle bundle = new Bundle();
        bundle.putSerializable("clickedMovie", (Serializable) movie);
        fragment = FragmentScenario.launchInContainer(MovieDetailsFragment.class, bundle);
    }

    @Test
    public void checkImageDisplayed()  {
        onView(withId(R.id.filmIv)).check(matches(isDisplayed()));
    }

    @Test
    public void clickOnWatchButton() {
        onView(withId(R.id.watchBtn)).perform(click());
    }

}