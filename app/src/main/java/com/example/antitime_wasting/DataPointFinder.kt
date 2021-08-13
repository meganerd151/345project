package com.example.antitime_wasting

import kotlin.collections.ArrayList
import android.util.Log

object DataPointFinder {

    fun findDataPoints(type: String, scope: Scope): ArrayList<Point>{
        var points: ArrayList<Point> = ArrayList<Point>()
        var sessions: ArrayList<Session> = DBInterface.queryType(type, null)
        var yVal: Int = 0
        var numPoints: Int
        numPoints = when (scope){
            Scope.BY_DAY -> 31
            Scope.BY_MONTH -> 12
        }
        for (i:Int in 1..numPoints){
            var point: Point = Point()
            point.x = i
        }
        var index: Int = 0
        for (point in points){
            var session: Session = sessions[index]
            var lastDate: String = session.date.toString()
            while (inSameTimeRange(sessions[index].date.toString(), lastDate, scope)){
                yVal += sessions[index].timeSpent!!
                index++
            }
            point.sety(yVal)
            yVal = 0
        }
        return points
    }
    // yyyy-mm-dd
    fun inSameTimeRange(date: String, lastDate: String, range: Scope): Boolean{
        return when (range){
            Scope.BY_DAY -> date == lastDate
            Scope.BY_MONTH -> date.subSequence(0, 7) == lastDate.subSequence(0, 7)
        }
    }
}