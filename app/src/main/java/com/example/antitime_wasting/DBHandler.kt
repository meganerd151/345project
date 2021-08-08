package com.example.antitime_wasting

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteDatabase.CursorFactory
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.util.*

class DBHandler(context: Context?, name: String?, factory: CursorFactory?, version: Int) : SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        var CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
        CREATE_TABLE += COLUMN_ID + " INTEGER PRIMARY KEY NOT NULL, " +
                COLUMN_START_TIME + " timestamp, " +
                COLUMN_END_TIME + " timestamp)"
        if (db != null) {
            db.execSQL(CREATE_TABLE)
        }
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
}