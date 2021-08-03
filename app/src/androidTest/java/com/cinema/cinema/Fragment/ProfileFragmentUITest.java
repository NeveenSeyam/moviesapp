package com.cinema.cinema.Fragment;

import androidx.fragment.app.testing.FragmentScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.cinema.cinema.R;
import com.google.firebase.auth.FirebaseAuth;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(JUnit4ClassRunner.class)
public class ProfileFragmentUITest {
    static CountDownLatch latch = new CountDownLatch(1);

    @Rule
    public FragmentScenario<ProfileFragment> fragment;

    @BeforeClass
    public static void setup() throws InterruptedException {
        if(FirebaseAuth.getInstance().getCurrentUser() == null) {
            FirebaseAuth.getInstance().signInWithEmailAndPassword("test@test.com", "test123");
            latch.await(5, TimeUnit.SECONDS);
        }
    }

    @Before
    public void runFragment() throws InterruptedException {
        fragment = FragmentScenario.launchInContainer(ProfileFragment.class);
        latch.await(5, TimeUnit.SECONDS);
    }

    @Test
    public void logoutClickTest() {
              onView(withId(R.id.LogOut)).perform(scrollTo(), click());
    }

    @Test
    public void checkUserData() {
        onView(withText("Khaled Dokhan")).check(matches(isDisplayed()));
        onView(withText("0595166453")).check(matches(isDisplayed()));
        onView(withText("test@test.com")).check(matches(isDisplayed()));
    }

    @Test
    public void performActionOnImage(){
        onView(withId(R.id.profile_img)).perform(click());
    }

}
