package com.example.antitime_wasting

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.*
import androidx.annotation.RequiresApi
import java.util.*


private const val SettingsName = "TimeRunning"
/**
 * Second page of our App, with Study Button, Exercise button
 */
class TimeActivity : AppCompatActivity() {

    /**
     * This object is a companion assignment for the val 'TAG' -> which is used for
     * Log statements to check if the app is working.
     * The Log is basically just logging activity
     */
    companion object{
        private const val TAG = "TimeActivity"

    }

    private lateinit var mascotTimeActivity: ImageView
    private lateinit var createActivity: Button
    private lateinit var timeText: TextView
    private var inSession: Boolean = false
    private var startTime: Int = 0
    private var endTime: Int = 0
    private var sessionType : String = "Study"


    /**
     * Standard onCreate function of every page in the app
     * main bulk of functions, buttons etc go into here
     */
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time)
        Log.i("start", "start pressed")

        mascotTimeActivity = findViewById(R.id.mascotTimeActivity)
        //createActivity = findViewById(R.id.createNewActivity)
        timeText = findViewById((R.id.timeText))

        val spinner : Spinner = findViewById(R.id.sessionSpinner)
        val adapter:SpinnerAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.SessionTypes,
            R.layout.spinner_item
        )
        spinner.setAdapter(adapter)


        val studybtn = findViewById<Button>(R.id.studybtn)
        studybtn.setOnClickListener {
            timeMethod(studybtn)

        }

        //back button on the top left corner of the second page
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //Title on the top
        supportActionBar?.title = "Start activity"

    }


    /**
     * function for the back button
     * @return returns Boolean and returns user to the home page
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    /**
     * Keeps track of the time the user is in an activity.
     */
    @RequiresApi(Build.VERSION_CODES.O)
    private fun timeMethod(pressedButton:Button) {
        val studybtn : Button = findViewById(R.id.studybtn)
        val spinner : Spinner = findViewById(R.id.sessionSpinner)
        if (inSession){
            endTime = getCurrentTime()
            DBInterface.addSession(Session(startTime, endTime, sessionType), this)
            timeText.setText(displayLayout(startTime.toLong(), endTime.toLong(), sessionType))
            inSession = false
            studybtn.text = "Start Session"
            spinner.setEnabled(true)
        } else {
            startTime = getCurrentTime()
            sessionType = spinner.getSelectedItem().toString()
            inSession = true
            studybtn.text = "End Session"
            spinner.setEnabled(false)
        }
    }

    /**
     * Used internally to get the current time.
     *
     * @return the current time as an Int.
     */
    private fun getCurrentTime(): Int {
        return (Date().time).toInt()
    }

    /**
     * Used internally to get the String to display the session information.
     *
     * @param start the start time
     * @param end the end time
     * @param type the session type as a String.
     *
     * @return a String that can be displayed.
     */
    private fun displayLayout(start:Long,end:Long, type:String): String {
        val difference = end-start
        val diffDays = (difference / (24 * 60 * 60 * 1000)).toInt()
        val diffhours = (difference / (60 * 60 * 1000)).toInt() % 24
        val diffmin = (difference / (60 * 1000)).toInt() % 60
        val diffsec = (difference / 1000).toInt() % 60
        return "Time spent on $type:  \n $diffDays days \n $diffhours hours \n $diffmin minutes \n $diffsec seconds"
    }


}
