package com.example.antitime_wasting

import android.content.Context

private const val settingsName = "numSessions"
/*
* @created 08/Aug/2021 - 2:50 PM
* @project Anti time-wasting
* @author Blake MacDade
*/class DBInterface {
    var debugTag = "DBInterface"
    private var talker: SettingsTalker? = null


    fun findSession(ID:Int, context:Context?) : Session{
        val dbHandler = ___DBHandler___(context,null,null,1)
        return dbHandler.findHandler(ID)
    }

    fun addSession(session: Session,context: Context?){
        var id:Int
        talker = SettingsTalker(context!!)
        id = talker!!.getIntEntry(settingsName)
        val dbHandler = ___DBHandler___(context,null,null,1)
        if(id == -1){
            talker!!.addEntry(settingsName,1)
            id = 1
        }
        session.setID(id)
        dbHandler.addHandler(session)
        talker!!.updateEntry(settingsName,id+1)
    }

    fun removeSession(ID: Int, context: Context?): Boolean {
        val dbHandler = ___DBHandler___(context, null, null, 1)
        return dbHandler.deleteHandler(ID)
    }

    fun updateSession(session: Session, context: Context?): Boolean {
        val dbHandler = ___DBHandler___(context, null, null, 1)
        return dbHandler.updateHandler(session)
    }

}