package com.example.antitime_wasting

import org.junit.Test
import org.junit.Assert.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * Class that tests to see if the correct values get saved to a session
 * object when you create a new session
 * @author Amy Lloyd
 */
class SessionTest {

    /**
     * Testing setting and getting methods for session
     * */
    @Test fun makeAndChangeSession(){
        val st = 0
        val et = 5
        val s = Session(st, et, "Study")

        //check to see if the initialization in this constructor works
        assertEquals(0, s.startTime)
        assertEquals(5, s.endTime)
        assertEquals("Study", s.sessionType)
        assertEquals(5, s.timeSpent)

        //check to see if the setDate method works
        val d: String = SimpleDateFormat("yyyy-MM-dd", Locale.US).format(Date())
        s.setDate(d)
        assertEquals(d, s.date)

        //check to see if the setID method works
        s.setID(10)
        assertEquals(10, s.id)

        //check to see if the setSessionType method works
        s.setSessionType("Exercise")
        assertEquals("Exercise", s.sessionType)

        //check to see if the setStart method works
        s.setStart(2)
        assertEquals(2, s.startTime)

        //check to see if the setEnd method works
        s.setEnd(8)
        assertEquals(8, s.endTime)

        //check to see if the setTimeSpent method works
        s.setTimeSpent(6)
        assertEquals(6, s.timeSpent)
    }

    /**
     * Test Session Constructor
     * */
    @Test fun sessionIDConstructorTest(){
        val s = Session(5)

        assertEquals(5, s.id)
    }

    /**
     * Test empty Session constructor
     * */
    @Test fun sessionUnusedConstructorTest(){
        val s = Session(3, 0, 5, "Study")

        assertEquals(3, s.id)
        assertEquals(0, s.startTime)
        assertEquals(5, s.endTime)
        assertEquals("Study", s.sessionType)
        assertEquals(5, s.timeSpent)
    }
}