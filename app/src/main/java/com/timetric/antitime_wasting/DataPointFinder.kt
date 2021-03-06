package com.example.antitime_wasting

import kotlin.collections.ArrayList
import android.content.Context
import java.text.SimpleDateFormat
import java.util.*

/**
 * Object used for finding the data points to plot from the database information.
 *
 * TODO : allow for vary in month lengths (not just a set 31 days)
 * @author Sam Fern
 */
object DataPointFinder {

    /**
     * Method can be called externally to get the data points (of type Point) to plot given the type
     * of sessions, the scope (e.g. days in a month, months in a year).
     *
     * @param type the session type you want to plot (Study, Exercise, etc.).
     * @param scope of enum Scope - the total amount of time graphed and how it is grouped.
     * @param context used to access the database.
     *
     * @return ArrayList of Points.
     */
    fun findDataPoints(type: String, scope: Scope, context: Context?): ArrayList<Point>{
        val points = ArrayList<Point>()
        val sessions: ArrayList<Session> = DBInterface.queryType(type, context)
        val numPoints: Int = when (scope){
            Scope.BY_DAY -> 31
            Scope.BY_MONTH -> 12
        }
        for (i:Int in 1..numPoints){
            val point = Point(i, 0)
            points.add(point)
        }
        for (session in sessions){
            if (inScope(session.date.toString(), scope)){
                points[findIndexOfPoint(session.date.toString(), scope)].y += session.timeSpent!! / 60
            }
        }
        return points
    }


    private fun inScope(date: String, scope: Scope): Boolean{
        /**
         * Used internally to determine whether a date will be in the data set based on the scope.
         *
         * @param date the date from a session in String form.
         * @param scope the scope of the data of the enum type Scope.
         *
         * @return a boolean indicating whether the date is in the scope or not.
         */
        return when (scope){
            Scope.BY_DAY -> {
                SimpleDateFormat("yyyy-MM", Locale.US).format(Date()) == date.substring(0, 7)
            }
            Scope.BY_MONTH -> {
                SimpleDateFormat("yyyy", Locale.US).format((Date())) == date.substring(0, 4)
            }
        }
    }


    private fun findIndexOfPoint(date: String, scope: Scope): Int{
        /**
         * Used internally to find the index of a Point in the points array using the date of a session.
         *
         * @param date the date from a session in String form.
         * @param scope the scope of the data of enum type Scope.
         *
         * @return an integer indicating the index.
         */
        return when (scope){
            Scope.BY_DAY -> date.substring(8, 10).toInt() - 1
            Scope.BY_MONTH -> date.substring(5, 7).toInt() - 1
        }
    }

    /**
     * getMaxY iterates through the avaiable points and gets the maximum Y value,
     *
     * @param points the array of points to iterate through
     *
     * @return the maximum Y value found in the given array of points.
     * */
    fun getMaxY(points: ArrayList<Point>): Double{
        var maxY = 0
        for (point in points){
            if (point.y > maxY){
                maxY = point.y
            }
        }
        return (maxY).toDouble()/(1000)
    }

    fun testInScope(): Boolean{
        var result = false
        inScope("2021-10",Scope.BY_DAY)
        result = inScope("2021",Scope.BY_MONTH)
        return result
    }

    fun testFindIndexOfPoint(): Int{
        findIndexOfPoint("012345678910",Scope.BY_DAY)
        findIndexOfPoint("012345678910",Scope.BY_MONTH)
        return 1
    }
}