package com.example.antitime_wasting

import android.content.Context
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith
import java.io.IOException
import kotlin.jvm.Throws

/*
* @created 16/Aug/2021 - 8:58 PM
* @project Anti time-wasting
* @author Blake MacDade
*/
@RunWith(AndroidJUnit4::class)
class testActivities {

    //@get:Rule var mainActivityScenarioRule = ActivityScenarioRule<MainActivity>()
    @get:Rule var mainActivityScenarioRule = ActivityScenarioRule<MainActivity>(MainActivity::class.java)
    val mainActivityScenario = mainActivityScenarioRule.scenario

    /*@Before fun setup(){

    }*/

    @Test
    @Throws(Exception::class)
    fun testMainActivity(){
        assertTrue(true)
        //mainActivityScenario.moveToState(Lifecycle.State.CREATED)
    }
}