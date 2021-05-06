package com.example.antitime_wasting

import android.content.ContentValues
import android.content.Intent
import android.nfc.Tag
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.*


class TimeActivity : AppCompatActivity() {

    companion object{
        private const val TAG = "TimeActivity"

    }


    private lateinit var mascotTimeActivity: ImageView
    private lateinit var createActivity: Button
    private lateinit var studybtn: Button
    private lateinit var excercisebtn: Button
    private lateinit var timeText: TextView
    private var twoDimArrayDateTime = arrayListOf<Int>()








    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time)

        mascotTimeActivity = findViewById(R.id.mascotTimeActivity)
        createActivity = findViewById(R.id.createNewActivity)
        excercisebtn = findViewById(R.id.excercisebtn)
        timeText = findViewById((R.id.timeText))


        //set Button Listeners with Methods
         val studybtn = findViewById<Button>(R.id.studybtn)
        studybtn.setOnClickListener {
            timeMethod()


        }





        //back button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //Title on the top
        supportActionBar?.title = "Start activity"
    }



    //function for the back button
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun timeMethod() {

        SaveAndDisplayTime.timeValues.add(SaveAndDisplayTime.saveOnClick())
        Log.i(TAG, "Clicked on Start Button" )
        Log.i(TAG, "${SaveAndDisplayTime.timeValues}" )
        SaveAndDisplayTime.WriteToFile(SaveAndDisplayTime.timeValuesToString)
        Log.i(TAG, "${SaveAndDisplayTime.timeValuesToString}" )


        /*
        if (SaveAndDisplayTime.timeValues.size >= 2) {
            SaveAndDisplayTime.calculateTimeSpentOnActivity()
            Log.i(TAG, "Calculated time ${SaveAndDisplayTime.calculateTimeSpentOnActivity()}")
        }
        if (SaveAndDisplayTime.timeValues.size >= 2) {
            Log.w(TAG, "Output ${SaveAndDisplayTime.timeInStringForOutput()}")
        }
        */
        if (SaveAndDisplayTime.timeValues.size < 2){

            Log.i(TAG, "First Loop" )
            return
        }else{
            if (SaveAndDisplayTime.timeValues.size % 2 != 0){
                Log.i(TAG, "Second Loop" )
                return
            }else{
                Log.i(TAG, "Third Loop" )
                SaveAndDisplayTime.calculateTimeSpentOnActivity()
                timeText.setText(SaveAndDisplayTime.timeInStringForOutput())
            }
        }

    }


}

