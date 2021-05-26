package com.example.antitime_wasting

import android.content.ContentValues.TAG
import android.content.Context
import android.content.SharedPreferences
import android.nfc.Tag
import android.util.Log
import android.util.Log.*
import com.example.antitime_wasting.SaveAndDisplayTime
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.*
import java.lang.Exception
import java.nio.file.attribute.FileOwnerAttributeView
import java.util.*
import kotlin.collections.ArrayList


object SaveAndDisplayTime {


    val timeValues = ArrayList<Int>()
    val timeValuesToString = timeValues.joinToString()

    //function to Save Data to internal Storage
    fun WriteToFile(str: String){
        try{
        var fo = FileWriter("Time", true)
        fo.write(str)
        fo.close()
    } catch(ex:Exception){
        print(ex.message)
    }
    }

    //save date and time on Click into Array
    //This functions returns the date - from the function Date() -
    //and converts the 'long' number into an integer by dividing it
    //by 1000.
    //Below are more function to retrieve the integers from the Array
    //and converts these integers back to longs by the correct magic
    //number
    fun saveOnClick(): Int {


        return (Date().time / 1000).toInt()

    }

    //The start and stop will always add 2 values to the growing array. The last and second last values in the array are the start and stop position
    //for calculation purposes. 
    //Retrieve Last element of Array and convert them to long for calculation purposes
    fun retrieveLastElementOfTimeValues(): Long {
        return (timeValues[timeValues.size - 1] * 1000L)
    }

    //Retrieve Second Last Element of Array
    fun retrieveSecondLastElementOfTimeValues(): Long {
        //Date d2 = new Date(((long) timeValues.get(timeValues.size()-1)*1000L));
        //return d2;
        return (timeValues[timeValues.size - 2] * 1000L)
    }

    //function to calculate time passed between pressing the button two times
    //used for checking purposes at the moment
    fun calculateTimeSpentOnActivity(): Date {
        val start = retrieveSecondLastElementOfTimeValues()
        val finish = retrieveLastElementOfTimeValues()
        return Date(finish - start)
    }

    //the correct time output sent back in String format to
    //the app screen
    fun timeInStringForOutput(): String {
        val diff1 = retrieveLastElementOfTimeValues() - retrieveSecondLastElementOfTimeValues()
        val diffDays = (diff1 / (24 * 60 * 60 * 1000)).toInt()
        val diffhours = (diff1 / (60 * 60 * 1000)).toInt()
        val diffmin = (diff1 / (60 * 1000)).toInt()
        val diffsec = (diff1 / 1000).toInt()
        return "Time spent on Activity:  \n $diffDays days \n $diffhours hours \n $diffmin minutes \n $diffsec seconds"
    }

    fun saveDate(){


    }
}