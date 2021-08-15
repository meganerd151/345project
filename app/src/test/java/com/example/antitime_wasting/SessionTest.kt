package com.example.antitime_wasting

import org.junit.Test
import org.junit.Assert.*

class SessionTest {
    @Test fun makeAndChangeSession(){
        val st: Int = 0
        val et: Int = 5
        val s = Session(st, et, "Study")

        assertEquals(0, s.startTime)
        assertEquals(5, s.endTime)
        assertEquals("Study", s.sessionType)
        assertEquals(5, s.timeSpent)
    }
}