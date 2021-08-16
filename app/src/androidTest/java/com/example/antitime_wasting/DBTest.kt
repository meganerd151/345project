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


/**
 * Class to test the database
 *
 * adapted from ___DBHandler___ unit test that no longer exists
 *
 * @created 16/Aug/2021
 * @author Blake MacDade
 * @author Amy Lloyd
 * */
@RunWith(AndroidJUnit4::class)
class DBTest {
    private lateinit var dbHandler: ___DBHandler___
    private lateinit var testSession: Session

    /**
     * creates the Database
     * */
    @Before
    fun createDBHandler(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        dbHandler = ___DBHandler___(context, null, null, 0)
        testSession = Session(151, 1000, 2000, "Test")
    }

    /**
     * closes the database
     * */
    @After
    @Throws(IOException::class)
    fun closeDB(){
        dbHandler.close()
    }

    /**
     * Tests reading and writing to the database
     * */
    @Test
    @Throws(Exception::class)
    fun writeAndRead(){
        dbHandler.addHandler(testSession) //write session to db
        val e = dbHandler.findHandler(151) //find session in db
        assertNotNull(e) //won't be equal to original session, so need to ensure not null
    }

    /**
     * Tests writing and querying the database
     * */
    @Test
    @Throws(Exception::class)
    fun writeAndQuery(){
        dbHandler.addHandler(testSession)
        assertNotNull(dbHandler.queryType("Test"))
    }


    /**
     * Tests writing and deleting from the database
     * Does not work atm, need to fix
     */
    @Test
    @Throws(Exception::class)
    fun writeAndDelete(){
        dbHandler.addHandler(testSession)
        //will provide failed test need to revise
        assertTrue(dbHandler.deleteHandler(151))
    }

    /**
     * Tests writing and updating a session in the database
     * Does not work atm, need to revise
     */
    @Test
    @Throws(Exception::class)
    fun writeAndUpdate(){
        dbHandler.addHandler(testSession)
        testSession.setSessionType("TEST")
        //will produce a failed test need to revise
        assertEquals(dbHandler.updateHandler(testSession), true)
    }


    /**
     * Tests wiping the database
     */
    @Test
    @Throws(Exception::class)
    fun wipeDB(){
        assertEquals(dbHandler.wipeDatabase(), true)
    }
}