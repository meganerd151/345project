package com.example.antitime_wasting

import kotlin.collections.ArrayList
import android.content.Context
import android.util.Log
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
                Log.i("datapoints", "IN SCOPE ${session.date.toString()}")
                points[findIndexOfPoint(session.date.toString(), scope)].y += session.timeSpent!!
            } else{
                Log.i("datapoints", "OUT OF SCOPE ${session.date.toString()}")
            }
        }
        return points
    }

    /**
     * Used internally to determine whether a date will be in the data set based on the scope.
     *
     * @param date the date from a session in String form.
     * @param scope the scope of the data of the enum type Scope.
     *
     * @return a boolean indicating whether the date is in the scope or not.
     */
    private fun inScope(date: String, scope: Scope): Boolean{
        return when (scope){
            Scope.BY_DAY -> {
                SimpleDateFormat("yyyy-MM", Locale.US).format(Date()) == date.substring(0, 7)
            }
            Scope.BY_MONTH -> {
                SimpleDateFormat("yyyy", Locale.US).format((Date())) == date.substring(0, 4)
            }
        }
    }

    /**
     * Used internally to find the index of a Point in the points array using the date of a session.
     *
     * @param date the date from a session in String form.
     * @param scope the scope of the data of enum type Scope.
     *
     * @return an integer indicating the index.
     */
    private fun findIndexOfPoint(date: String, scope: Scope): Int{
        return when (scope){
            Scope.BY_DAY -> date.substring(8, 10).toInt() - 1
            Scope.BY_MONTH -> date.substring(5, 7).toInt() - 1
        }
    }

    fun getMaxY(points: ArrayList<Point>): Double{
        var maxY = 0
        for (point in points){
            if (point.y > maxY){
                maxY = point.y
            }
        }
        return (maxY).toDouble()/(1000)
    }
}