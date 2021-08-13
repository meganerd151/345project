package com.example.antitime_wasting

import kotlin.collections.ArrayList
import android.content.Context
import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

object DataPointFinder {

    fun findDataPoints(type: String, scope: Scope, context: Context?): ArrayList<Point>{
        var points: ArrayList<Point> = ArrayList<Point>()
        var sessions: ArrayList<Session> = DBInterface.queryType(type, context)
        var numPoints: Int = when (scope){
            Scope.BY_DAY -> 31
            Scope.BY_MONTH -> 12
        }
        for (i:Int in 1..numPoints){
            val point: Point = Point()
            point.x = i
            points.add(point)
        }
        for (session in sessions){
            if (inScope(session.date.toString(), scope)){
                Log.i("datapoints", "INFO: In Scope")
                points[findIndexOfPoint(session.date.toString(), scope)].y += session.timeSpent!!
            }
        }
        return points
    }

    private fun inScope(date: String, scope: Scope): Boolean{
        val test: String = SimpleDateFormat("yyyy-MM",Locale.US).format((Date()))
        val test2: String = date.substring(0, 7)
        Log.i("Datapoints", "INFO: $test $test2")
        return when (scope){
            Scope.BY_DAY -> {
                SimpleDateFormat("yyyy", Locale.US).format(Date()) == date.substring(0, 4)
            }
            Scope.BY_MONTH -> {
                SimpleDateFormat("yyyy-MM", Locale.US).format((Date())) == date.substring(0, 7)
            }
        }
    }
    // yyyy-mm-dd
    // 0123456789
    private fun findIndexOfPoint(date: String, scope: Scope): Int{
        return when (scope){
            Scope.BY_DAY -> date.substring(8, 10).toInt() - 1
            Scope.BY_MONTH -> date.substring(5, 7).toInt() - 1
        }
    }
}