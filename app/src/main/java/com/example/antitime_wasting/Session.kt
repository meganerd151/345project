package com.example.antitime_wasting

/*
* @created 08/Aug/2021 - 2:20 PM
* @project Anti time-wasting
* @author Blake MacDade
*/class Session {

    var id = 0
        private set

    var startTime:Int? = null
        private set

    var endTime:Int? = null
        private set

    var sessionType:String? = null
        private set

    constructor(id:Int,start:Int,end:Int,sessionType:String){
        this.id = id
        this.startTime = start
        this.endTime = end
        this.sessionType = sessionType
    }

    constructor(id:Int){
        this.id = id
    }

    constructor(){}

    constructor(start:Int?,end:Int?,sessionType:String?){
        this.startTime = start
        this.endTime = end
        this.sessionType = sessionType
    }

    fun setID(ID:Int){
        this.id = ID
    }

    fun setStart(start:Int){
        this.startTime = start
    }

    fun setEnd(end:Int){
        this.endTime = end
    }

    fun setSessionType(session:String){
        this.sessionType = session
    }

}