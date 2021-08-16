package com.example.antitime_wasting

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*
import org.junit.runner.RunWith
import java.io.IOException
import kotlin.jvm.Throws

/*
* @created 16/Aug/2021 - 8:25 PM
* @project Anti time-wasting
* @author Blake MacDade
*/
@RunWith(AndroidJUnit4::class)
class GenericAndroidTests {

    val context = ApplicationProvider.getApplicationContext<Context>()
    lateinit var settingsTalker: SettingsTalker

    @Before
    fun createDBHandler(){
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

    @Test
    @Throws(Exception::class)
    fun clearAllSettingsTest(){
        settingsTalker.addEntry("456",123)
        settingsTalker.clearAll(true)
        assertEquals(settingsTalker.getIntEntry("456"),-1)
    }
}