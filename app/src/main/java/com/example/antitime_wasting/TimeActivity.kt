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


    /**
     * Standard onCreate function of every page in the app
     * main bulk of functions, buttons etc go into here
     */
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time)

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
     * timeMethod which has been called by the studybtn
     *
     * This method combines other methods from the
     * SaveAndDisplayTime object and is the current
     * workhorse of our app
     *
     * It save the start point and ending point in our app
     * in the app.
     *
     * It also displays the String on the second page how much
     * time was spent on a activity.
     *
     */
    @RequiresApi(Build.VERSION_CODES.O)
    private fun timeMethod(pressedButton:Button) {
        var startTime :Long = 0
        var endTime :Long = 0
        //if(TimeHelper.timeValues.size >2) TimeHelper.timeValues.clear()
        if(TimeHelper.timeValues.size <=2){
            TimeHelper.timeValues.add(TimeHelper.getCurrentTime())
        }else{
            startTime = TimeHelper.getSecondLastTimeElement()
            endTime = TimeHelper.getLastTimeElement()
            Log.i(TAG, "Start Time: $startTime End time: $endTime")
            DBInterface.addSession(Session(startTime.toInt(),endTime.toInt(),pressedButton.getText().toString()),this)
            TimeHelper.timeValues.clear()
        }

        //SaveAndDisplayTime is a support class.
        //Call the support class and 'add' and 'save'
        //the time in the timeValues array-> saveOnClick is a function called
        //within the support class SaveAndDisplay




        //Log Statement to check in the logs if the functions
        //are working correctly
        Log.i(TAG, "Clicked on Start Button" )
        //Log.i(TAG, "${TimeHelper.timeValues}" )

        //initial attempt to save the time into Strings
        // -----------NOT FINISHED --------UNDER CONSTRUCTION-------------
        TimeHelper.WriteToFile(TimeHelper.timeValuesToString)
        Log.i(TAG, "${TimeHelper.timeValuesToString}" )


        //If, else structured arguments which call the support class
        //and some functions there in order to
        //calculate and correctly display the time
        //once the study button is pressed two times

        if (startTime > 0 && endTime > 0) {
            Log.i(TAG,"Trying to write data to screen!")

            // val timeSpent = TimeHelper.getTimeSpent(s.startTime!!,s.endTime!!)
            timeText.setText(TimeHelper.toString(startTime,endTime))
        }

    }


}
