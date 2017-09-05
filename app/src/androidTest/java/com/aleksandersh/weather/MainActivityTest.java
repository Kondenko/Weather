package com.aleksandersh.weather;

import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.espresso.contrib.NavigationViewActions;
import android.support.test.filters.SmallTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.Gravity;

import com.aleksandersh.weather.features.main.MainActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.DrawerMatchers.isClosed;
import static android.support.test.espresso.contrib.DrawerMatchers.isOpen;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;

/**
 * Created by Vladimir Kondenko on 27.07.17.
 */
@RunWith(AndroidJUnit4.class)
@SmallTest
public class MainActivityTest {

    private static final int DRAWER_ID = R.id.drawer_layout;
    private static final int NAV_VIEW_ID = R.id.navigation_view;

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule(MainActivity.class);

    @Before
    public void setUp() {
        onView(withId(DRAWER_ID)).perform(DrawerActions.open());
        onView(withId(DRAWER_ID)).check(matches(isOpen(Gravity.START)));
    }

    @After
    public void tearDown() {
        onView(withId(DRAWER_ID)).perform(DrawerActions.close());
        onView(withId(DRAWER_ID)).check(matches(isClosed(Gravity.START)));
    }

    @Test
    public void shouldOpenWeatherFragment()  {
        onView(withId(NAV_VIEW_ID)).perform(NavigationViewActions.navigateTo(R.id.nav_weather_fragment));
        onView(withId(R.id.weather_swipe_refresh_layout)).check(matches(isDisplayed()));
    }
    
    @Test
    public void shouldOpenSettingsFragment()  {
        onView(withId(NAV_VIEW_ID)).perform(NavigationViewActions.navigateTo(R.id.nav_settings_fragment));
        // Нельзя получить id корневого элемента PreferenceFragment
        onView(allOf(withText("Settings"), withParent(withClassName(containsString("Toolbar"))))).check(matches(isDisplayed()));
    }
    
    @Test
    public void shouldOpenAboutFragment()  {
        onView(withId(NAV_VIEW_ID)).perform(NavigationViewActions.navigateTo(R.id.nav_about_fragment));
        onView(withId(R.id.fragment_about_root)).check(matches(isDisplayed()));
    }

}
