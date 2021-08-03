package com.cinema.cinema.Fragment;

import android.os.Bundle;
import android.widget.ListView;

import androidx.fragment.app.testing.FragmentScenario;
import androidx.test.espresso.PerformException;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.cinema.cinema.MainActivity;
import com.cinema.cinema.MainActivityPresenter;
import com.cinema.cinema.MainActivityViewInterface;
import com.cinema.cinema.Model.Movie;
import com.cinema.cinema.R;
import com.google.firebase.auth.FirebaseAuth;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


@RunWith(AndroidJUnit4.class)
public class SearchFragmentListTest implements MainActivityViewInterface {
    CountDownLatch latch = new CountDownLatch(1);
    List<Movie> moviesList;

    MainActivityPresenter presenter = new MainActivityPresenter(this);



    @BeforeClass
    public static void signUserIn(){
        if(FirebaseAuth.getInstance().getCurrentUser() == null) {
            FirebaseAuth.getInstance().signInWithEmailAndPassword("test@test.com", "test123");
        }
    }

    public FragmentScenario<SearchFragment> fragment;

    @Before
    public void setup() throws InterruptedException {
        presenter.getMoviesData();
        latch.await(5, TimeUnit.SECONDS);
        Bundle bundle = new Bundle();
        bundle.putSerializable("moviesList", (Serializable) moviesList);
        fragment =  FragmentScenario.launchInContainer(SearchFragment.class, bundle);
    }

    @Test
    public void scrollToItem() throws InterruptedException {
        onView(ViewMatchers.withId(R.id.result_list))
                .perform(RecyclerViewActions.scrollTo(
                        hasDescendant(withText("The Matrix"))
                ));
    }

    @Test(expected = PerformException.class)
    public void itemWithText_doesNotExist() {
        // Attempt to scroll to an item that contains the special text.
        onView(ViewMatchers.withId(R.id.result_list))
                // scrollTo will fail the test if no item matches.
                .perform(RecyclerViewActions.scrollTo(
                        hasDescendant(withText("Item that is not in the list"))
                ));
    }

    @Test
    public void performClickOnItem(){
        onView(ViewMatchers.withId(R.id.result_list))
                .perform(RecyclerViewActions.scrollTo(
                        hasDescendant(withText("Seven Samurai"))
                ), click());

    }



    @Override
    public void onGetMoviesSuccess(List<Movie> moviesList) {
        this.moviesList = moviesList;
    }

    @Override
    public void onGetMoviesFailed(String message) {

    }
}
