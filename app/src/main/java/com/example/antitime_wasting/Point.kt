package com.example.antitime_wasting

/**
 * Used to indicate a point on a graph.
 */
class Point {
    var x: Int = 0
    var y: Int = 0

    constructor(){}

    constructor(xVal: Int, yVal: Int){
        x = xVal
        y = yVal
    }

    fun setx(xval: Int){
        x = xval
    }

    fun sety(yval: Int){
        y = yval
    }
}