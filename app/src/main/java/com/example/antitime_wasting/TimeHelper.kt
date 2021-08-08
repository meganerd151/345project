package com.example.antitime_wasting

import android.util.Log
import java.io.*
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList


object TimeHelper {


    val timeValues = ArrayList<Int>()
    val timeValuesToString = timeValues.joinToString()

    /**
     * function to Save Data to internal Storage
     *
     * INCOMPLETE - POTENTIAL FOR THESE CODE TO BE REPLACED
     *
     * Problem and Solution:
     * We will connect the App to the 'Room'
     * function of Android, where we can save
     * the time values
     */
    fun WriteToFile(str: String){
        try{
        var fo = FileWriter("Time", true)
        fo.write(str)
        fo.close()
    } catch(ex:Exception){
        print(ex.message)
    }
    }

    /**
     *  save date and time on Click into Array
     *   This functions returns the date - from the function Date() -
     *   and converts the 'long' number into an integer by dividing it
     *   by 1000.
     *   Below are more function to retrieve the integers from the Array
     *   and converts these integers back to longs by the correct magic
     *   number
     *   @return Time as an integer from the Date() function
     */
    fun getCurrentTime(): Int {


        return (Date().time / 1000).toInt()

    }

    /**
     * The start and stop will always add 2 values to the growing array. The last and second last values in the array are the start and stop position
     * for calculation purposes.
     * Retrieve Last element of Array and convert them to long for calculation purposes
     * @return last element of the Array
     */
    fun getLastTimeElement(): Long {
        return (timeValues[timeValues.size - 1] * 1000L)
    }

    /**
     * Retrieve Second Last Element of Array
     * @return second last element of the Array
     */
    fun getSecondLastTimeElement(): Long {
        //Date d2 = new Date(((long) timeValues.get(timeValues.size()-1)*1000L));
        //return d2;
        return (timeValues[timeValues.size - 2] * 1000L)
    }

    //function to calculate time passed between pressing the button two times
    //used for checking purposes at the moment
    fun getTimeSpent(s:Int, f:Int): Date {
        val start = s*1000L
        val finish = f *1000L
        //val start = getSecondLastTimeElement()
        //val finish = getLastTimeElement()
        return Date(finish - start)
    }

    /**
     * the correct time output sent back in String format to
     * the app screen
     *@return Time spent on activity in String format
     */


    fun toString(start:Long,end:Long): String {
        val difference = end-start
        //val difference = getLastTimeElement() - getSecondLastTimeElement()
        Log.i("TimeHelper","Start: $start, End: $end, Difference: $difference")
        val diffDays = (difference / (24 * 60 * 60 * 1000)).toInt()
        val diffhours = (difference / (60 * 60 * 1000)).toInt()
        val diffmin = (difference / (60 * 1000)).toInt()
        val diffsec = (difference / 1000).toInt()
        return "Time spent on Activity:  \n $diffDays days \n $diffhours hours \n $diffmin minutes \n $diffsec seconds"
    }

    /**
     * UNDER DEVELOPMENT
     *
     * WILL PROPABLY BE DROPPED IN FAVOR OF 'Room'
     */
    fun saveDate(){


    }
}