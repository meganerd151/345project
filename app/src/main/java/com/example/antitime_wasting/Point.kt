package com.example.antitime_wasting

/**
 * Used to indicate a point on a graph.
 *
 * @author Sam Fern
 */
class Point {
    var x: Int = 0
    var y: Int = 0

    constructor(){}

    constructor(xVal: Int, yVal: Int){
        x = xVal
        y = yVal
    }

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