package com.example.antitime_wasting

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteDatabase.CursorFactory
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import kotlin.collections.ArrayList


/**
 * This is a class to directly interact with the Database.
 * Note: you should not directly interact with this class, you should instead interract with DBInterface.kt
 *
 * @param context the application context (unused)
 * @param name unused
 * @param factory unused
 * @param version unused
 * */
class ___DBHandler___(context: Context?, name: String?, factory: CursorFactory?, version: Int) : SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    /**
     * onCreate is an overloading method who is called when the database is created.
     *
     * @param db the database instance to work on.
     * */
    override fun onCreate(db: SQLiteDatabase?) {
        var createTableQuery = "CREATE TABLE " + TABLE_NAME + "("
        createTableQuery += COLUMN_ID + " INTEGER PRIMARY KEY NOT NULL, " +
                COLUMN_START_TIME + " INTEGER, " +
                COLUMN_END_TIME + " INTEGER, " +
                COLUMN_SESSION_TYPE+ " TEXT, " +
                COLUMN_DATE+" DATE, "+
                COLUMN_DELTA_T+" INTEGER"+
                ");"
        db?.execSQL(createTableQuery)
    }
    /**
     * onUpgrade is an overloading method who is called when the database is created, but another one exists.
     *
     * @param db the database instance to work on.
     * @param oldVersion unused
     * @param newVersion unused
     * */
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS "+ DATABASE_NAME)
        onCreate(db)
    }

    /**
     * wipeDatabase removes all entries from the main database.
     * Note: does not drop the table or database, simply removes all the entries.
     * */
    fun wipeDatabase():Boolean{
        val db = this.writableDatabase
        db.execSQL("delete from $TABLE_NAME")
        return true
    }

    companion object {
        private const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "timesDB.db"
        const val TABLE_NAME = "Times"
        const val COLUMN_ID = "TimeID"
        const val COLUMN_START_TIME = "StartTime"
        const val COLUMN_END_TIME = "EndTime"
        const val COLUMN_SESSION_TYPE = "SessionType"
        const val COLUMN_DATE = "Date"
        const val COLUMN_DELTA_T = "TimeSpent"
        const val debugTag = "DBHandler"
    }

    /**
     * addHandler adds a session object to the database.
     *
     * @param session the session object to add to the database.
     * */
    fun addHandler(session: Session) {
        try {
            val values = ContentValues()
            values.put(COLUMN_ID, session.id)
            values.put(COLUMN_START_TIME, session.startTime)
            values.put(COLUMN_END_TIME, session.endTime)
            values.put(COLUMN_SESSION_TYPE, session.sessionType)
            values.put(COLUMN_DATE,session.date)
            values.put(COLUMN_DELTA_T,session.timeSpent)
            val db = this.writableDatabase
            db.insert(TABLE_NAME, null, values)
            db.close()
        } catch (e: SQLiteConstraintException) {
            Log.d(debugTag, "Got SQLiteConstraintException in addHandler method of DBHandler")
        }
    }

    /**
     * Finds a session with the given identifier in the database.
     *
     * @param sessionId the id to find.
     * @return the found session object, can be null.
     * */
    fun findHandler(sessionId: Int): Session {
        val query = "SELECT * FROM $TABLE_NAME WHERE $COLUMN_ID = '$sessionId'"
        val db = this.writableDatabase
        val cursor = db.rawQuery(query, null)
        val session = Session()
        if (cursor.moveToFirst()) {
            cursor.moveToFirst()
            session.setID(cursor.getInt(0))
            session.setStart(cursor.getInt(1))
            session.setEnd(cursor.getInt(2))
            session.setSessionType(cursor.getString(3))
            session.setDate(cursor.getString(4))
            session.setTimeSpent(cursor.getInt(5))
        }
        cursor.close()
        db.close()
        return session
    }

    /**
     * deletes a handler from the database (if it exists)
     *
     * @param ID the of the session to delete
     * @return returns the state of the delete.
     * */
    fun deleteHandler(ID: Int): Boolean {
        var result = false
        val query = "SELECT * FROM $TABLE_NAME WHERE $COLUMN_ID = '$ID'"
        val db = this.writableDatabase
        val cursor = db.rawQuery(query, null)
        val session = Session()
        if (cursor.moveToFirst()) {
            session.setID(cursor.getInt(0))
            db.delete(TABLE_NAME, "$COLUMN_ID=?", arrayOf(java.lang.String.valueOf(session.id)))
            // for the love of GOD, please revisit this steaming pile of crap, took from online tutorial and just want it to work :)
            cursor.close()
            result = true
        }
        db.close()
        return result
    }

    /**
     * updates a session object in the database.
     *
     * @param session an updated version of the session object to update in the database.
     * @return returns the state of the operation.
     * */
    fun updateHandler(session: Session): Boolean {
        val db = this.writableDatabase
        val args = ContentValues()
        //args.put(COLUMN_ID,ID);
        args.put(COLUMN_START_TIME, session.startTime)
        args.put(COLUMN_END_TIME, session.endTime)
        args.put(COLUMN_SESSION_TYPE, session.sessionType)
        args.put(COLUMN_DATE,session.date)
        args.put(COLUMN_DELTA_T,session.timeSpent)
        return db.update(TABLE_NAME, args, COLUMN_ID + " = " + session.id, null) > 0

        //return false
    }
    /**
     * Returns all session objects that match the given type
     *
     * @param type the type of session that we want to find
     * @return returns an ArrayList of all session objects that are of the given type.
     * */
    fun queryType(type: String): ArrayList<Session>{
        val sessions = ArrayList<Session>()
        val db = this.writableDatabase
        val query = "SELECT $COLUMN_ID FROM $TABLE_NAME WHERE $COLUMN_SESSION_TYPE = '$type'"
        val cursor: Cursor = db.rawQuery(query, null)
        if (cursor.moveToFirst()){
            do {
                sessions.add(findHandler(cursor.getInt(0)))
            } while (cursor.moveToNext())
        }
        db.close()
        cursor.close()
        return sessions
    }


}