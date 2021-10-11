package com.example.antitime_wasting

import android.content.Context
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.rules.ActivityScenarioRule

import org.junit.runner.RunWith
import org.junit.Test
import org.junit.Assert.*
import org.junit.Rule
//import kotlin.jvm.Throws
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import org.junit.Before
import kotlin.Throws

/*
* @created 16/Aug/2021 - 8:58 PM
* @project Anti time-wasting
* @author Blake MacDade
*/
/**
 * Class to test activities
 * */
@RunWith(AndroidJUnit4::class)
class testActivities {

    /**
     * Tests that the key components of MainActivity are there and working.
     * */
    @Test
    @Throws(Exception::class)
    fun testMainActivity(){
        val mainActivityScenario = ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.coordinator)).check(matches(isDisplayed()))
        onView(withId(R.id.startButton)).check(matches(isDisplayed()))
        onView(withId(R.id.mascot)).check(matches(isDisplayed()))
        onView(withId(R.id.coordinator)).check(matches(isDisplayed()))

        onView(withId(R.id.startButton)).perform(click())
    }

    /**
     * Tests that the key components of statsMenuActivity are there and working.
     */
    @Test
    @Throws(Exception::class)
    fun testStatsMenuActivity(){
        val statsActivityScenario = ActivityScenario.launch(StatsMenuActivity::class.java)

        onView(withId(R.id.statsConstraintLayout)).check(matches(isDisplayed()))
        onView(withId(R.id.idGraphView)).check(matches(isDisplayed()))
        onView(withId(R.id.view)).check(matches(isDisplayed()))
        onView(withId(R.id.textView2)).check(matches(isDisplayed()))
        onView(withId(R.id.sessionTypeSelector)).check(matches(isDisplayed()))
        onView(withId(R.id.textView3)).check(matches(isDisplayed()))
        onView(withId(R.id.scopeSelector)).check(matches(isDisplayed()))
    }

    /**
     * Tests that the key components of timeActivity are there and working.
     */
    @Test
    @Throws(Exception::class)
    fun testTimeMenuActivity(){
        val timeActivityScenario = ActivityScenario.launch(TimeActivity::class.java)

        onView(withId(R.id.timeConstraintLayout)).check(matches(isDisplayed()))
        onView(withId(R.id.frameTimeActivity)).check(matches(isDisplayed()))
        onView(withId(R.id.mascotTimeActivity)).check(matches(isDisplayed()))
        onView(withId(R.id.timeText)).check(matches(isDisplayed()))
        onView(withId(R.id.view2)).check(matches(isDisplayed()))
        onView(withId(R.id.studybtn)).check(matches(isDisplayed()))

        onView(withId(R.id.studybtn)).perform(click())


    }

    /**
     * Tests that the components of the facts page are loaded and displayed on the screen.
     * */
    @Test
    @Throws(Exception::class)
    fun testFactsPage(){
        val FactsPageScenario = ActivityScenario.launch(facts_page::class.java)
        onView(withId(R.id.statsConstraintLayout)).check(matches(isDisplayed()))
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()))
        onView(withId(R.id.factbox_1)).check(matches(isDisplayed()))
        onView(withId(R.id.factbox_2)).check(matches(isDisplayed()))
        onView(withId(R.id.factbox_3)).check(matches(isDisplayed()))
        onView(withId(R.id.factbox_4)).check(matches(isDisplayed()))
        onView(withId(R.id.factbox_5)).check(matches(isDisplayed()))
        onView(withId(R.id.factbox_6)).check(matches(isDisplayed()))
        onView(withId(R.id.factbox_7)).check(matches(isDisplayed()))
        onView(withId(R.id.factbox_8)).check(matches(isDisplayed()))
    }

}
