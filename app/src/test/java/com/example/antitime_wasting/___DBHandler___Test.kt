package com.example.antitime_wasting

import android.content.Context
import junit.framework.TestCase

/*
* @created 16/Aug/2021 - 1:18 PM
* @project Anti time-wasting
* @author Blake MacDade
*/ class ___DBHandler___Test : TestCase() {

    val context:Context = TODO()
    val dbHandler = ___DBHandler___(context,null,null,0)
    val testSession = Session(1000,2000,"Test")

    fun testWipeDatabase() {
        assertEquals(dbHandler.wipeDatabase(),true)
    }

    fun testAddHandler() {
        dbHandler.addHandler(testSession)
    }

    fun testFindHandler() {
        val recoveredHandler = dbHandler.findHandler(1)
        assertNotNull(recoveredHandler)

    }

    fun testDeleteHandler() {
        assertEquals(dbHandler.deleteHandler(1),true)
    }

    fun testUpdateHandler() {
        assertEquals(dbHandler.updateHandler(testSession),true)
    }

    fun testQueryType() {
        assertNotNull(dbHandler.queryType("Test"))
    }
}