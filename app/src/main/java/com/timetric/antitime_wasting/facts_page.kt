package com.example.antitime_wasting


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * This represents a simple view, that shows facts about studying and exercies to help motivate
 * the user.
 * */
class facts_page : AppCompatActivity() {

    /**
     * onCreate is called when android wants to display the view.
     * */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_facts_page)
    }
}