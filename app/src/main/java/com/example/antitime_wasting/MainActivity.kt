package com.example.antitime_wasting

import android.content.Intent
import android.content.res.Resources

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.mbms.FileInfo
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import com.google.android.material.snackbar.Snackbar
import java.io.FileInputStream
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random

/**
 *Home Page
 */
class MainActivity : AppCompatActivity() {

    //Standard 'late' initialisation of buttons, Imageview, Containers etc.
    //'lateinit' means that a particular variable will be initialize later in the
    //program
    private lateinit var mascot: ImageView
    private lateinit var startButton: Button

    /**
     * Standard onCreate function of the main activity screen
     * this is the main function which creates the buttons, containers etc.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //initializing buttons, texts etc by finding them with 'findViewById' in the
        //resources of the app
        mascot = findViewById(R.id.mascot)
        startButton = findViewById(R.id.startButton)

        /**
         * Start Button gets a Button Listener
         * Context is set to this home page
         * Standard Structure to move to a new page - > TimeActivity
         * Time activity is the second page in our app
         */
        startButton.setOnClickListener {
            val intent = Intent(this, TimeActivity::class.java)
            startActivity(intent)
        }



    }

    /**
     * Function which creates the top right Menu
     * utilising Standard menuInflator commands for these menus.
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
   }

    /**
     * function which will lunch the statistics menu with Graph,
     * nothing assign so far
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.statisticsMenu -> {
                val intent = Intent(this, StatsMenuActivity::class.java)
                startActivity(intent)
            }
            R.id.clearData ->{
                DBInterface.wipeDatabase(this)
                Snackbar.make(
                    findViewById(R.id.coordinator),
                    R.string.dataClearedSucessfully,
                    Snackbar.LENGTH_SHORT
                ).show()
            }
            R.id.testData -> {
                DBInterface.wipeDatabase(this)
                Snackbar.make(
                    findViewById(R.id.coordinator),
                    R.string.testDataInserted,
                    Snackbar.LENGTH_SHORT
                ).show()
                insertTestData()
            }
        }
        return true
    }

    fun insertTestData(){

        val res: Resources = resources
        val sessions = ArrayList<Session>()

        var currentDay: String = SimpleDateFormat("dd", Locale.US).format((Date()))
        var currentMonth: String = SimpleDateFormat("MM", Locale.US).format((Date()))
        var dayString: String
        for (type in res.getStringArray(R.array.SessionTypes)) {
            for (day: Int in 1..currentDay.toInt()) {
                dayString = if (day < 10) {
                    "0$day"
                } else {
                    day.toString()
                }
                sessions.add(Session(0, Random.nextInt(1000, 150000), type, "2021-$currentMonth-$dayString"))
            }
            //sessions.add(Session(0, Random.nextInt(1000, 100000), type, "2021-08-01"))
        }

        for (session in sessions){
            DBInterface.addSession(session, this)
        }

    }


}