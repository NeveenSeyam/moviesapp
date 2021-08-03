package com.cinema.cinema.registeractivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import com.cinema.cinema.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class RegisterActivityUITest {

    @Rule
    public ActivityScenarioRule<RegisterActivity> activityActivityTestRule = new ActivityScenarioRule<>(RegisterActivity.class);


    @Test
    public void view_isCorrect() {
        onView(withId(R.id.input_name)).perform(click(), typeText("test"));
        onView(withId(R.id.input_email)).perform(scrollTo(), typeText("test@test.com"));
        onView(withId(R.id.input_password)).perform(scrollTo(), typeText("123456"));
        onView(withId(R.id.input_phonenumber)).perform(scrollTo(), typeText("+9725965163"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.btn_signup)).perform(click());
    }

}