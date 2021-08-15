package com.example.antitime_wasting

import org.junit.Test
import org.junit.Assert.*

/**
 * A class that tests point and its setter methods
 */
class pointTest {
    @Test fun makeAndChangePoint(){
        //makes point and checks to see if it was created correctly
        val p = Point(1, 2)
        assertEquals(1, p.x)
        assertEquals(2, p.y)

        //changes the values and checks to see if the changes held
        p.setx(4)
        p.sety(7)
        assertEquals(4, p.x)
        assertEquals(7, p.y)
    }
}