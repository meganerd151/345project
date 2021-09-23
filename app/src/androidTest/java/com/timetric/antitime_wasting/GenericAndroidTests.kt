package com.example.antitime_wasting

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*
import org.junit.runner.RunWith
import kotlin.jvm.Throws

/*
* @created 16/Aug/2021 - 8:25 PM
* @project Anti time-wasting
* @author Blake MacDade
*/
/**
 * Class to do assorted tests on a running device
 * */
@RunWith(AndroidJUnit4::class)
class GenericAndroidTests {

    val context = ApplicationProvider.getApplicationContext<Context>()
    lateinit var settingsTalker: SettingsTalker


    /**
     * Called before the other tests, sets up needed variables
     * */
    @Before
    fun setup(){
        settingsTalker = SettingsTalker(context)
    }

    /**
     * Tests adding to settings file
     */

    @Test
    @Throws(Exception::class)
    fun addSettingsEntryTest(){
        settingsTalker.addEntry("123","Test!")
        settingsTalker.addEntry("456",123)
        assertNotNull(settingsTalker.getStringEntry("123"))
        assertNotNull(settingsTalker.getIntEntry("456"))
    }

    /**
     * Tests removing from settings file
     * */
    @Test
    @Throws(Exception::class)
    fun removeSettingsEntryTest(){
        settingsTalker.addEntry("456",123)
        settingsTalker.removeEntry("456")
        assertEquals(settingsTalker.getIntEntry("456"),-1)
    }

    /**
     * Tests clearing all settings
     * */
    @Test
    @Throws(Exception::class)
    fun clearAllSettingsTest(){
        settingsTalker.addEntry("456",123)
        settingsTalker.clearAll(true)
        assertEquals(settingsTalker.getIntEntry("456"),-1)
    }

    /**
     * Tests updating a settings entry
     * */
    @Test
    @Throws(Exception::class)
    fun updateSettingsTest(){
        settingsTalker.addEntry("123","Test!")
        settingsTalker.updateEntry("123","456")
    }
}