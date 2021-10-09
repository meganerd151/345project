package com.timetric.antitime_wasting
/*

import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.example.antitime_wasting.MainActivity
import com.example.antitime_wasting.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class mainUIelementsTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun mainUIelementsTest() {
        val materialButton = onView(
            allOf(
                withId(R.id.startButton), withText("Start"),
                childAtPosition(
                    allOf(
                        withId(R.id.linearLayoutBottom),
                        childAtPosition(
                            withId(R.id.ConstraintLayout),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        materialButton.perform(click())

        val button = onView(
            allOf(
                withId(R.id.studybtn), withText("START SESSION"),
                withParent(
                    allOf(
                        withId(R.id.timeConstraintLayout),
                        withParent(withId(android.R.id.content))
                    )
                ),
                isDisplayed()
            )
        )
        button.check(matches(isDisplayed()))

        val textView = onView(
            allOf(
                withId(R.id.sessionSpinner), withText("Study"),
                withParent(
                    allOf(
                        withId(R.id.sessionSpinner),
                        withParent(withId(R.id.timeConstraintLayout))
                    )
                ),
                isDisplayed()
            )
        )
        textView.check(matches(withText("Study")))

        val imageView = onView(
            allOf(
                withId(R.id.mascotTimeActivity),
                withParent(
                    allOf(
                        withId(R.id.frameTimeActivity),
                        withParent(withId(R.id.timeConstraintLayout))
                    )
                ),
                isDisplayed()
            )
        )
        imageView.check(matches(isDisplayed()))

        val textView2 = onView(
            allOf(
                withId(R.id.timeText),
                withText("\n\nChoose an activity and start a new session\n\n"),
                withParent(
                    allOf(
                        withId(R.id.timeConstraintLayout),
                        withParent(withId(android.R.id.content))
                    )
                ),
                isDisplayed()
            )
        )
        textView2.check(matches(withText("  Choose an activity and start a new session  ")))

        val textView3 = onView(
            allOf(
                withId(R.id.timeText),
                withText("\n\nChoose an activity and start a new session\n\n"),
                withParent(
                    allOf(
                        withId(R.id.timeConstraintLayout),
                        withParent(withId(android.R.id.content))
                    )
                ),
                isDisplayed()
            )
        )
        textView3.check(matches(withText("  Choose an activity and start a new session  ")))

        val materialButton2 = onView(
            allOf(
                withId(R.id.studybtn), withText("Start Session"),
                childAtPosition(
                    allOf(
                        withId(R.id.timeConstraintLayout),
                        childAtPosition(
                            withId(android.R.id.content),
                            0
                        )
                    ),
                    4
                ),
                isDisplayed()
            )
        )
        materialButton2.perform(click())

        val materialButton3 = onView(
            allOf(
                withId(R.id.studybtn), withText("End Session"),
                childAtPosition(
                    allOf(
                        withId(R.id.timeConstraintLayout),
                        childAtPosition(
                            withId(android.R.id.content),
                            0
                        )
                    ),
                    4
                ),
                isDisplayed()
            )
        )
        materialButton3.perform(click())

        val appCompatImageButton = onView(
            allOf(
                withContentDescription("Navigate up"),
                childAtPosition(
                    allOf(
                        withId(R.id.action_bar),
                        childAtPosition(
                            withId(R.id.action_bar_container),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatImageButton.perform(click())

        val actionMenuItemView = onView(
            allOf(
                withId(R.id.statisticsMenu), withContentDescription("Statistics"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.action_bar),
                        1
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        actionMenuItemView.perform(click())

        val view = onView(
            allOf(
                withId(R.id.idGraphView),
                withParent(
                    allOf(
                        withId(R.id.statsConstraintLayout),
                        withParent(withId(android.R.id.content))
                    )
                ),
                isDisplayed()
            )
        )
        view.check(matches(isDisplayed()))

        val textView4 = onView(
            allOf(
                withId(R.id.sessionSpinner), withText("Study"),
                withParent(
                    allOf(
                        withId(R.id.sessionTypeSelector),
                        withParent(withId(R.id.statsConstraintLayout))
                    )
                ),
                isDisplayed()
            )
        )
        textView4.check(matches(isDisplayed()))

        val textView5 = onView(
            allOf(
                withId(R.id.sessionSpinner), withText("Month View"),
                withParent(
                    allOf(
                        withId(R.id.scopeSelector),
                        withParent(withId(R.id.statsConstraintLayout))
                    )
                ),
                isDisplayed()
            )
        )
        textView5.check(matches(isDisplayed()))

        val textView6 = onView(
            allOf(
                withId(R.id.sessionSpinner), withText("Month View"),
                withParent(
                    allOf(
                        withId(R.id.scopeSelector),
                        withParent(withId(R.id.statsConstraintLayout))
                    )
                ),
                isDisplayed()
            )
        )
        textView6.check(matches(isDisplayed()))

        val appCompatImageButton2 = onView(
            allOf(
                withContentDescription("Navigate up"),
                childAtPosition(
                    allOf(
                        withId(R.id.action_bar),
                        childAtPosition(
                            withId(R.id.action_bar_container),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatImageButton2.perform(click())

        val overflowMenuButton = onView(
            allOf(
                withContentDescription("More options"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.action_bar),
                        1
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        overflowMenuButton.perform(click())

        val textView7 = onView(
            allOf(
                withId(R.id.title), withText("Clear Data"),
                withParent(withParent(withId(R.id.content))),
                isDisplayed()
            )
        )
        textView7.check(matches(isDisplayed()))

        val textView8 = onView(
            allOf(
                withId(R.id.title), withText("Facts page"),
                withParent(withParent(withId(R.id.content))),
                isDisplayed()
            )
        )
        textView8.check(matches(isDisplayed()))

        val textView9 = onView(
            allOf(
                withId(R.id.title), withText("Facts page"),
                withParent(withParent(withId(R.id.content))),
                isDisplayed()
            )
        )
        textView9.check(matches(isDisplayed()))

        val materialTextView = onView(
            allOf(
                withId(R.id.title), withText("Facts page"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.content),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        materialTextView.perform(click())

        val textView10 = onView(
            allOf(
                withId(R.id.factbox_5),
                withText("Health Tip: Ages 18yrs-25yrs need 7hrs-9hrs of sleep!"),
                withParent(
                    allOf(
                        withId(R.id.statsConstraintLayout),
                        withParent(withId(android.R.id.content))
                    )
                ),
                isDisplayed()
            )
        )
        textView10.check(matches(isDisplayed()))

        val textView11 = onView(
            allOf(
                withId(R.id.factbox_5),
                withText("Health Tip: Ages 18yrs-25yrs need 7hrs-9hrs of sleep!"),
                withParent(
                    allOf(
                        withId(R.id.statsConstraintLayout),
                        withParent(withId(android.R.id.content))
                    )
                ),
                isDisplayed()
            )
        )
        textView11.check(matches(isDisplayed()))
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
*/