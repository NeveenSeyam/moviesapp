package com.cinema.cinema;


import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.cinema.cinema.Model.Movie;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;


@RunWith(MockitoJUnitRunner.class)
public class MainActivityPresenterTest {
    CountDownLatch latch = new CountDownLatch(1);
    MainActivityPresenter presenter;

    @Rule
    public ActivityScenarioRule<MainActivity> activity = new ActivityScenarioRule<MainActivity>(MainActivity.class);

    @Mock
    MainActivityViewInterface view;

    @Before
    public void setup(){
        presenter = new MainActivityPresenter(view);
    }

    @Test
    public void testGetMoviesSuccess() throws InterruptedException {
        // given

        // when
        presenter.getMoviesData();
        // then
        latch.await(8, TimeUnit.SECONDS);
        Mockito.verify(view).onGetMoviesSuccess(Mockito.anyListOf(Movie.class));
        Assert.assertFalse(MainActivity.moviesList.isEmpty());
        onView(withId(R.id.Home)).check(matches(isDisplayed()));
    }

}