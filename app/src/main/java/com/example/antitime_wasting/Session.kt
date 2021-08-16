package com.example.antitime_wasting

import java.text.SimpleDateFormat
import java.util.*

/**
 * A representation of a single study Session object
 *
* @created 08/Aug/2021 - 2:20 PM
* @project Anti time-wasting
* @author Blake MacDade
*/
class Session {

    var id = 0
        private set

    var startTime:Int? = null
        private set

    var endTime:Int? = null
        private set

    var sessionType:String? = null
        private set

    var date:String? = null
        private set

    var timeSpent:Int? = null
        private set

    constructor(id:Int,start:Int,end:Int,sessionType:String){
        this.id = id
        this.startTime = start
        this.endTime = end
        this.sessionType = sessionType
        this.date = SimpleDateFormat("yyyy-MM-dd",Locale.US).format(Date())
        this.timeSpent = (end - start)
    }

    constructor(id:Int){
        this.id = id
    }

    constructor(){}

    constructor(start:Int?,end:Int?,sessionType:String?){
        this.startTime = start
        this.endTime = end
        this.sessionType = sessionType
        this.date = SimpleDateFormat("yyyy-MM-dd", Locale.US).format(Date())
        this.timeSpent = (end!! - start!!)
    }

    /**
     * Sets the id of the session
     * @param ID id to assign
     * */
    fun setID(ID:Int){
        this.id = ID
    }

    /**
     *  Sets the start time of the session
     *  @param start the time to assign
     * */
    fun setStart(start:Int){
        this.startTime = start
    }

    /**
     * Sets the end time of the session
     * @param end the time to assign
     * */
    fun setEnd(end:Int){
        this.endTime = end
    }

    /**
     * Sets the type of the session
     * @param session the type to assign
     * */
    fun setSessionType(session:String){
        this.sessionType = session
    }

    /**
     * Sets the date the session was started on
     * @param date the date the assign
     * */
    fun setDate(date: String){
        this.date = date
    }

    /**
     * Sets the total time spend on a session
     * @param time the time spent to assign
     * */
    fun setTimeSpent(time:Int){
        this.timeSpent = time
    }
}
