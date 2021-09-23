package com.example.antitime_wasting


import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.util.Log

/*
* @created 08/Aug/2021 - 2:56 PM
* @project Anti time-wasting
* @author Blake MacDade
*/
/**
 * This file is intended to let you save and retrieve settings data on the device.
 */
class SettingsTalker(var context: Context) {
    private var preferences: SharedPreferences = context.applicationContext.getSharedPreferences(settingsFileName, 0)
    private var editor: Editor = preferences.edit()

    /**
     * addEntry adds an entry to the settings file.
     * @param key key string to store the data at.
     * @param value value string to store.
     */
    fun addEntry(key: String?, value: String?) {
        //editor = preferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    /**
     * addEntry adds an entry to the settings file.
     * @param key key string to store the data at.
     * @param value value int to store.
     */
    fun addEntry(key: String?, value: Int) {
        //editor = preferences.edit()
        editor.putInt(key, value)
        editor.apply()
    }

    /**
     * updateEntry updates the given key pair, where a String is required
     * @param key the key of the stored item to update.
     * @param value the value to update.
     * */
    fun updateEntry(key: String?, value: String?) {
        addEntry(key, value)
    }

    /**
     * updateEntry updates the given key pair, where an Integer is required
     * @param key the key of the stored item to update.
     * @param value the value to update.
     * */
    fun updateEntry(key: String?, value: Int) {
        addEntry(key, value)
    }

    /**
     * getEntry returns an entry from the settings file.
     * @param key key string to look up and return data from settings file.
     * @return String value stored with the key.
     */
    fun getStringEntry(key: String?): String? {
        return preferences.getString(key, null)
    }

    /**
     * getEntry returns an entry from the settings file.
     * @param key key string to look up and return data from settings file.
     * @return Int value stored with the key.
     */
    fun getIntEntry(key: String?): Int {
        return preferences.getInt(key, -1)
    }

    /**
     * removeEntry removes an entry from the settings file.
     * @param key string to remove from the settings file.
     */
    fun removeEntry(key: String?) {
        //editor = preferences.edit()
        editor.remove(key)
        editor.apply()
    }

    /**
     * clearAll CLEARS ENTIRE SETTINGS FILE
     * @param iReallyWantToClearEverything just to double check :)
     */
    fun clearAll(iReallyWantToClearEverything: Boolean) {
        Log.d(debugTag, "Someone is trying to clear the settings file!!!")
        if (iReallyWantToClearEverything) {
            //editor = preferences.edit()
            editor.clear()
            editor.apply()
        }
    }

    companion object {
        private const val settingsFileName = "settings"
        private const val debugTag = "settingsTalker"
    }

}