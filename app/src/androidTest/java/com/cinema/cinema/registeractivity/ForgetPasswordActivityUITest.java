package com.cinema.cinema.registeractivity;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.cinema.cinema.R;
import com.google.firebase.auth.FirebaseAuth;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class ForgetPasswordActivityUITest {


    @Rule
    public ActivityScenarioRule<ForgetPasswordActivity> activityActivityTestRule = new ActivityScenarioRule<>(ForgetPasswordActivity.class);

    @BeforeClass
    public static void setup(){
        FirebaseAuth.getInstance().signOut();
    }

    @Test
    public void resetUITest() {
        onView(withId(R.id.email)).perform(typeText("test@test.com"));
        onView(withId(R.id.email)).perform(closeSoftKeyboard());
        onView(withId(R.id.btn_reset_password)).perform(click());


    }
    @Test
    public void clickgoSingup() {

        onView(withId(R.id.link_login)).perform(click());


    }

}