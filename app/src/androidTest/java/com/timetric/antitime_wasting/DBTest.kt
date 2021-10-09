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
    private lateinit var testdbHandler: ___DBHandler___
    private lateinit var testSession: Session
    private lateinit var testSession2: Session
    val context = ApplicationProvider.getApplicationContext<Context>()

    /**
     * creates the Database
     * */
    @Before
    fun createDBHandler(){

        dbHandler = ___DBHandler___(context, null, null, 0)
        testdbHandler = ___DBHandler___(context, null, null, 1)
        testSession = Session(151, 1000, 2000, "Test")
        testSession2 = Session(152,1000,2000,"Test")
    }

    /**
     * closes the database
     * */
    @After
    @Throws(IOException::class)
    fun closeDB(){
        dbHandler.close()
        wipeDB()

    }

    /**
     * Tests reading and writing to the database
     * */
    @Test
    @Throws(Exception::class)
    fun writeAndRead(){
        dbHandler.addHandler(testSession) //write session to db
        DBInterface.addSession(testSession2,context)
        assertNotNull(DBInterface.getLastSession(context))
        assertNotNull(dbHandler.findHandler(151))
        assertNotNull(DBInterface.findSession(152,context))
    }

    /**
     * Tests writing and querying the database
     * */
    @Test
    @Throws(Exception::class)
    fun writeAndQuery(){
        dbHandler.addHandler(testSession)
        DBInterface.addSession(testSession2,context)
        val points = DataPointFinder.findDataPoints("Test",Scope.BY_MONTH,context)
        assertNotNull(points)
        assertNotNull(DataPointFinder.findDataPoints("Test",Scope.BY_DAY,context))
        DataPointFinder.getMaxY(points)
        assertNotNull(dbHandler.queryType("Test"))
        assertNotNull(DBInterface.queryType("Test",context))
    }


    /**
     * Tests writing and deleting from the database
     * Does not work atm, need to fix
     */
    @Test
    @Throws(Exception::class)
    fun writeAndDelete(){
        dbHandler.addHandler(testSession)
        DBInterface.addSession(testSession2,context)
        //will provide failed test need to revise
        assertTrue(dbHandler.deleteHandler(151))
        DBInterface.removeSession(152,context)
    }

    /**
     * Tests writing and updating a session in the database
     * Does not work atm, need to revise
     */
    @Test
    @Throws(Exception::class)
    fun writeAndUpdate(){
        dbHandler.addHandler(testSession)
        DBInterface.addSession(testSession2,context)
        testSession.setSessionType("TEST")
        testSession2.setSessionType("CoolType")
        //will produce a failed test need to revise
        assertEquals(dbHandler.updateHandler(testSession), true)
        assertEquals(DBInterface.updateSession(testSession2,context),true)
    }


    /**
     * Tests wiping the database
     */
    @Test
    @Throws(Exception::class)
    fun wipeDB(){
        assertEquals(dbHandler.wipeDatabase(), true)
        assertEquals(DBInterface.wipeDatabase(context),true)
    }
}