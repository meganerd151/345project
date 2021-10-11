package com.example.antitime_wasting


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class BasicUITest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun basicUITest() {
        val button = onView(
            allOf(
                withId(R.id.startButton), withText("START"),
                withParent(
                    allOf(
                        withId(R.id.linearLayoutBottom),
                        withParent(withId(R.id.ConstraintLayout))
                    )
                ),
                isDisplayed()
            )
        )
        button.check(matches(isDisplayed()))

        val textView = onView(
            allOf(
                withId(R.id.statisticsMenu), withContentDescription("Statistics"),
                withParent(withParent(withId(R.id.action_bar))),
                isDisplayed()
            )
        )
        textView.check(matches(isDisplayed()))

        val imageView = onView(
            allOf(
                withContentDescription("More options"),
                withParent(withParent(withId(R.id.action_bar))),
                isDisplayed()
            )
        )
        imageView.check(matches(isDisplayed()))

        val imageView2 = onView(
            allOf(
                withId(R.id.mascot),
                withParent(withParent(withId(R.id.ConstraintLayout))),
                isDisplayed()
            )
        )
        imageView2.check(matches(isDisplayed()))

        val imageView3 = onView(
            allOf(
                withId(R.id.mascot),
                withParent(withParent(withId(R.id.ConstraintLayout))),
                isDisplayed()
            )
        )
        imageView3.check(matches(isDisplayed()))

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

        val button2 = onView(
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
        button2.check(matches(isDisplayed()))

        val textView2 = onView(
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
        textView2.check(matches(isDisplayed()))

        val imageView4 = onView(
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
        imageView4.check(matches(isDisplayed()))


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
                withId(R.id.textView3), withText("Select Scope"),
                withParent(
                    allOf(
                        withId(R.id.statsConstraintLayout),
                        withParent(withId(android.R.id.content))
                    )
                ),
                isDisplayed()
            )
        )
        textView6.check(matches(isDisplayed()))

        val textView7 = onView(
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
        textView7.check(matches(isDisplayed()))

        val textView8 = onView(
            allOf(
                withId(R.id.textView2), withText("Select Activity Type"),
                withParent(
                    allOf(
                        withId(R.id.statsConstraintLayout),
                        withParent(withId(android.R.id.content))
                    )
                ),
                isDisplayed()
            )
        )
        textView8.check(matches(isDisplayed()))

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

        pressBack()
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
