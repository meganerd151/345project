package com.example.antitime_wasting
import androidx.test.core.app.ApplicationProvider
import android.content.Context
import junit.framework.TestCase
import org.junit.Test

/*
* @created 16/Aug/2021 - 1:18 PM
* @project Anti time-wasting
* @author Blake MacDade
*/ class ___DBHandler___Test : TestCase() {

    val context = ApplicationProvider.getApplicationContext<Context>()

    val dbHandler = ___DBHandler___(context,null,null,0)
    val testSession = Session(1000,2000,"Test")

    @Test fun testWipeDatabase() {
        assertEquals(dbHandler.wipeDatabase(),true)
    }

    @Test fun testAddHandler() {
        dbHandler.addHandler(testSession)
    }

    @Test fun testFindHandler() {
        val recoveredHandler = dbHandler.findHandler(1)
        assertNotNull(recoveredHandler)

    }

    @Test fun testDeleteHandler() {
        assertTrue(dbHandler.deleteHandler(1))
    }

    @Test fun testUpdateHandler() {
        assertEquals(dbHandler.updateHandler(testSession),true)
    }

    @Test fun testQueryType() {
        assertNotNull(dbHandler.queryType("Test"))
    }
}