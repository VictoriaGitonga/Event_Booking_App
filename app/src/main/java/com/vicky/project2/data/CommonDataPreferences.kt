package com.vicky.project2.data

import android.content.Context
import android.content.SharedPreferences


class CommonDataPreferences (context: Context) {
    val applicationContext = context.applicationContext

    private var prefs: SharedPreferences =
        context.getSharedPreferences(
            "EVENT_APP_PREFERENCE_DATA",
            Context.MODE_PRIVATE
        )

    private var pastrolistDataPreference: SharedPreferences =
        context.getSharedPreferences(
            "EVENT_APP_PREFERENCE_DATA",
            Context.MODE_PRIVATE
        )

    //    private object PreferenceKey {
//        val key = preferencesKey<String>("authToken")
//        val guestDataTime = preferencesKey<String>("guestDataTime")
//    }
    companion object {

        const val church_codes_for_login: String = "church_codes_for_login"
        const val phoneNumber: String = "phoneNumber"
        const val is_term_accepted:String= "is_term_accepted"
        const val is_first_time: String = "is_first_time"
        const val CONFERENCE_TPYE: String = "conference"
        const val IS_UPDATE_DATA: String = "IS_UPDATE_DATA"
        const val USER_NAME: String = "accountID"
        const val county_key: String = "county_key"
        const val USER_DETAILS: String = "USER_DETAILS"
        const val EMAIL:String="speakerEmail"
    }

    /**
     * Function to save timestamp  when data is loaded
     * Function to fetch timestamp when data was loaded*/

    fun setPhoneNumber(phone: String) {
        val editor = prefs.edit()
        editor.putString(phoneNumber, phone)
        editor.apply()
    }

    fun getPhoneNumber(): String {
        return prefs.getString(phoneNumber, "").toString()
    }

    fun setUserName(phone: String) {
        val editor = prefs.edit()
        editor.putString(USER_NAME, phone)
        editor.apply()
    }

    fun getUserName(): String {
        return prefs.getString(USER_NAME, "").toString()
    }

    fun setEMAIL(speakerEmail: String) {
        val editor = prefs.edit()
        editor.putString(EMAIL, speakerEmail)
        editor.apply()
    }

    fun getEmail(): String {
        return prefs.getString(EMAIL, "").toString()
    }

    fun setIsUpdateData(isUpdateData: Boolean) {
        val editor = prefs.edit()
        editor.putBoolean(IS_UPDATE_DATA, isUpdateData)
        editor.apply()
    }

    fun getIsUpdateData(): Boolean {
        return prefs.getBoolean(IS_UPDATE_DATA, false)
    }

    fun setAgentCounty(orgID: String?) {
        val editor = prefs.edit()
        editor.putString(county_key, orgID)
        editor.apply()
    }

    fun getAgentCounty(): String? {
        return prefs.getString(county_key, "").toString()
    }


    /**
     * Function to save timestamp  when data is loaded
     * Function to fetch timestamp when data was loaded*/

    fun setIsFirstTimerUser(isFirstTime: Boolean) {
        val editor = prefs.edit()
        editor.putBoolean(is_first_time, isFirstTime)
        editor.apply()
    }

    fun getIsFirstTimerUser(): Boolean {
        return prefs.getBoolean(is_first_time, true)
    }


    fun checkIsEventOrganizer(isTermAccepted: Boolean) {
        val editor = prefs.edit()
        editor.putBoolean(is_term_accepted, isTermAccepted)
        editor.apply()
    }

    fun getIsEventOrganizer(): Boolean {
        return prefs.getBoolean(is_term_accepted, true)
    }

    /**
     * Function to save timestamp  when data is loaded
     * Function to fetch timestamp when data was loaded*/

    fun saveStringData(key: String, value: String) {
        val editor = pastrolistDataPreference.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getStringData(key: String): String {

        return pastrolistDataPreference.getString(key, "").toString()
    }

    fun clearPastrolistData() {
        pastrolistDataPreference.edit().clear().commit()
    }


}