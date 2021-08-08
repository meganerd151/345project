package com.example.antitime_wasting

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteDatabase.CursorFactory
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.util.*

class ___DBHandler___(context: Context?, name: String?, factory: CursorFactory?, version: Int) : SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        var CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
        CREATE_TABLE += COLUMN_ID + " INTEGER PRIMARY KEY NOT NULL, " +
                COLUMN_START_TIME + " INTEGER, " +
                COLUMN_END_TIME + " INTEGER, " +
                COLUMN_SESSION_TYPE+ " TEXT " +
                ")"
        db?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "timesDB.db"
        const val TABLE_NAME = "Times"
        const val COLUMN_ID = "TimeID"
        const val COLUMN_START_TIME = "StartTime"
        const val COLUMN_END_TIME = "EndTime"
        const val COLUMN_SESSION_TYPE = "SessionType"
        const val debugTag = "DBHandler"
    }

    fun addHandler(session: Session) {
        try {
            val values = ContentValues()
            values.put(COLUMN_ID, session.id)
            values.put(COLUMN_START_TIME, session.startTime)
            values.put(COLUMN_END_TIME, session.endTime)
            values.put(COLUMN_SESSION_TYPE, session.sessionType)
            val db = this.writableDatabase
            db.insert(TABLE_NAME, null, values)
            db.close()
        } catch (e: SQLiteConstraintException) {
            Log.d(debugTag, "Got SQLiteConstraintException in addHandler method of DBHandler")
        }
    }


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
        }
        cursor.close()
        db.close()
        return session
    }

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

    fun updateHandler(session: Session): Boolean {
        val db = this.writableDatabase
        val args = ContentValues()
        //args.put(COLUMN_ID,ID);
        args.put(COLUMN_START_TIME, session.startTime)
        args.put(COLUMN_END_TIME, session.endTime)
        args.put(COLUMN_SESSION_TYPE, session.sessionType)
        return db.update(TABLE_NAME, args, COLUMN_ID + " = " + session.id, null) > 0

        //return false
    }



}