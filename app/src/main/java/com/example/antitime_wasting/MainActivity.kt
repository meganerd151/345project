package com.example.antitime_wasting

import android.content.Intent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.mbms.FileInfo
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import java.io.FileInputStream

class MainActivity : AppCompatActivity() {


    private lateinit var mascot: ImageView
    private lateinit var startButton: Button




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mascot = findViewById(R.id.mascot)
        startButton = findViewById(R.id.startButton)





        startButton.setOnClickListener {
            val intent = Intent(this, TimeActivity::class.java)
            startActivity(intent)
        }



    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
   }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.statisticsMenu -> {
                //setup statistics page
            }
        }
        return true
    }


}