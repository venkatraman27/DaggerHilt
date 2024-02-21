package com.dagger.daggerhilt.data.preferences

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class AppPreference @Inject constructor(context: Context)  {

    companion object{

        private const val PREFERENCE_NAME = "NUTRAL_PREFERENCE"
        private val USERID = "USERID"
        private val EMAIL = "EMAIL"
    }

    private val preference: SharedPreferences =
        context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)


    fun clearAppPreference() {
        preference.edit().clear().apply()
    }

    var USERID: String
        set(value) = preference.edit().putString(Companion.USERID, value).apply()
        get() = preference.getString(Companion.USERID, "")!!

    var EMAIL: String
        set(value) = preference.edit().putString(Companion.EMAIL, value).apply()
        get() = preference.getString(Companion.EMAIL, "")!!

}