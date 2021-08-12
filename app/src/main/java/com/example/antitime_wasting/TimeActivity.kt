package com.example.antitime_wasting

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import java.sql.Time


private const val SettingsName = "TimeRunning"
/**
 * Second page of our App, with Study Button, Excercise button
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

    //Standard 'late' initialising of buttons, Views, etc.
    private lateinit var mascotTimeActivity: ImageView
    private lateinit var createActivity: Button
    private lateinit var studybtn: Button
    private lateinit var excercisebtn: Button
    private lateinit var timeText: TextView
    private var twoDimArrayDateTime = arrayListOf<Int>()
    private lateinit var session:Session
    private var inSession: Boolean = false
    private var startTime: Int = 0
    private var endTime: Int = 0


    /**
     * Standard onCreate function of every page in the app
     * main bulk of functions, buttons etc go into here
     */
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time)
        Log.i("start", "start pressed")

        //initializing buttons, texts etc by finding them with 'findViewById' in the
        //resources of the app
        mascotTimeActivity = findViewById(R.id.mascotTimeActivity)
        createActivity = findViewById(R.id.createNewActivity)
        timeText = findViewById((R.id.timeText))


        //set Button Listeners for the Study Button
        //call the method 'timeMethod' when the
        //studybtn is clicked
        //This method save the time into the array and
        //calculates the time
        val studybtn = findViewById<Button>(R.id.studybtn)
        studybtn.setOnClickListener {
            timeMethod(studybtn)

        }

        val excercisebtn = findViewById<Button>(R.id.excercisebtn)
        excercisebtn.setOnClickListener {
            timeMethod(excercisebtn)

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
     * NOTE: this still relies on methods from TimeHelper - this should be removed
     *
     * NOTE: The time does not display correctly yet - it currently displays the total amount of
     * seconds, minutes, etc. E.g. instead of 1 minute and 30 seconds it will display 1 minute and
     * 90 seconds. - shouldn't be a hard fix.
     *
     * Still need to check database is working fine as this code never actually accesses it - only
     * inserts data (getting database inspector errors)
     *
     * I also believe some DBHandler / DBInterface methods do not work (e.g. getLastSession and updateSession)
     */
    @RequiresApi(Build.VERSION_CODES.O)
    private fun timeMethod(pressedButton:Button) {
        val studybtn : Button = findViewById(R.id.studybtn)
        if (inSession){
            endTime = TimeHelper.getCurrentTime()
            DBInterface.addSession(Session(startTime, endTime, "test"), this)

            timeText.setText(TimeHelper.toString(startTime.toLong(), endTime.toLong()))
            inSession = false
            studybtn.text = "Start Session"
            /* TESTING  */
            var testSession = DBInterface.getLastSession(this)
            var testStart: Int? = testSession.startTime
            var testEnd: Int? = testSession.endTime
            var time: Int? = (testStart!! - testEnd!!)/1000
            Log.i(TAG, "Test: Start Time: $testStart End time: $testEnd difference: $time")


            /*
            val session = DBInterface.getLastSession(this)
            session.setEnd(TimeHelper.getCurrentTime())
            DBInterface.updateSession(session, this)
            timeText.setText(TimeHelper.toString(session.startTime!!.toLong(),session.endTime!!.toLong()))
            inSession = false

            currentSession.setEnd(TimeHelper.getCurrentTime())
            DBInterface.addSession(currentSession, this)
            var session = DBInterface.getLastSession(this)
            timeText.setText(TimeHelper.toString(session.startTime!!.toLong(),session.endTime!!.toLong()))
            inSession = false*/
        } else {
            startTime = TimeHelper.getCurrentTime()
            inSession = true
            studybtn.text = "End Session"
            /*
            val newSession = Session(TimeHelper.getCurrentTime(), null, null)
            DBInterface.addSession(newSession, this)
            inSession = true

            currentSession.setID(currentSession.id + 1)
            currentSession.setStart(TimeHelper.getCurrentTime())
            currentSession.setSessionType("test")
            inSession = true*/
        }
    }


}
