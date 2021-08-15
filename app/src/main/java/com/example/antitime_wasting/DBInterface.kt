package com.example.antitime_wasting

import android.content.Context

private const val settingsName = "numSessions"
/*
* @created 08/Aug/2021 - 2:50 PM
* @project Anti time-wasting
* @author Blake MacDade
*/

/**
 * This Class acts as an abstraction layer between DBHandler and any activity that may want to
 * query the database.
 *
 * @author Blake MacDade
 */

object DBInterface {
    var debugTag = "DBInterface"
    private var talker: SettingsTalker? = null

    /**
     * findSession is a simple method with no error checking whatsoever, does a simple
     * database lookup and returns any result.
     *
     * @param ID The session ID that the database will be queried with.
     * @param context Application Context.
     *
     * @return new Session object that represents data with primary key of ID, CAN BE NULL!
     */

    fun findSession(ID:Int, context:Context?) : Session{
        val dbHandler = ___DBHandler___(context,null,null,1)
        return dbHandler.findHandler(ID)
    }

    /**
     * addSession is a simple method with no error checking, adds a new task to the database.
     *
     * @param session Session object to add to the database.
     * @param context Application Context.
     *
     */
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

    /**
     * removeSession is a simple method with no error checking, deletes a session from the database.
     *
     * @param ID The session ID of the record to delete.
     * @param context Application Context.
     *
     * @return returns the state of the deletion, true if successful.
     */
    fun removeSession(ID: Int, context: Context?): Boolean {
        val dbHandler = ___DBHandler___(context, null, null, 1)
        return dbHandler.deleteHandler(ID)
    }

    /**
     * updateSession is a simple method with no error checking, updates a session in the database.
     * pass in an updated version of the Session object you want to change.
     *
     * @param session The updated session object.
     * @param context Application Context.
     *
     * @return returns the state of the update, true if successful.
     */
    fun updateSession(session: Session, context: Context?): Boolean {
        val dbHandler = ___DBHandler___(context, null, null, 1)
        return dbHandler.updateHandler(session)
    }

    fun getLastSession(context: Context?): Session{
        talker = SettingsTalker(context!!)
        val dbHandler = ___DBHandler___(context,null,null,1)
        return dbHandler.findHandler(talker!!.getIntEntry(settingsName) -1)
    }

    /**
     * queryType returns an ArrayList of sessions of a given type.
     *
     * @param type the sessionType to query.
     * @param context Application Context.
     *
     * @return ArrayList of sessions from query.
     */
    fun queryType(type:String, context: Context?): ArrayList<Session>{
        val dbHandler = ___DBHandler___(context, null, null, 1)
        return dbHandler.queryType(type)
    }

    /**
     * wipeDatabase removes all entries in the timesDB.db database.
     *
     * @param context Application Context.
     * */
    fun wipeDatabase(context: Context?){
        val dbHandler = ___DBHandler___(context,null,null,1)
        dbHandler.wipeDatabase()
    }

}