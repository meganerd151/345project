package com.example.antitime_wasting

import kotlin.collections.ArrayList
import android.util.Log

object DataPointFinder {

    fun findDataPoints(type: String, scope: Scope): ArrayList<Point>{
        var points: ArrayList<Point> = ArrayList<Point>()
        var sessions: ArrayList<Session> = DBInterface.queryType(type, null)
        var yVal: Int = 0
        var numPoints: Int
        when (scope){
            Scope.BY_DAY -> numPoints = 31
            Scope.BY_MONTH -> numPoints = 12
        }
        for (i:Int in 1..numPoints){
            var point: Point = Point()
            point.x = i
        }
        var index: Int = 0
        for (point in points){
            var session: Session = sessions[index]
            var lastDate: String = session.date.toString()
            while (inSameTimeRange(session, lastDate, scope)){
                yVal++
                index++
            }
            point.sety(yVal)
            yVal = 0
        }
    }
    // yyyy-mm-dd
    fun inSameTimeRange(session: Session, lastDate: String, range: Scope): Boolean{
        var returnVal: Boolean = false
        val date: String = session.date.toString()
        when (range){
            Scope.BY_DAY -> returnVal = date == lastDate
            Scope.BY_MONTH -> returnVal = date.subSequence(0, 7) == lastDate.subSequence(0, 7)
        }
        return returnVal
    }
}