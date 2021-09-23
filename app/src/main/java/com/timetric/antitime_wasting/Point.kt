package com.example.antitime_wasting

/**
 * Used to indicate a point on a graph.
 *
 * @author Sam Fern
 */
class Point(xVal: Int, yVal: Int) {
    var x: Int = xVal
    var y: Int = yVal

    /**
     * Sets the value of the x datafield
     * @param xval value to store in x
     * */
    fun setx(xval: Int){
        x = xval
    }
    /**
     * Sets the value of the y datafield
     * @param yval value to store in y
     * */
    fun sety(yval: Int){
        y = yval
    }
}