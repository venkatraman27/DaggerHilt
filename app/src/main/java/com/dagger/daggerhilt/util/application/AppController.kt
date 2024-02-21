package com.dagger.daggerhilt.util.application

import android.app.Application
import com.dagger.daggerhilt.data.preferences.AppPreference
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class AppController : Application() {


    private var appPreference: AppPreference? = null

    companion object {
        private var appController: AppController? = null

        @Synchronized
        fun getInstance(): AppController? {
            return appController
        }
    }

    override fun onCreate() {
        super.onCreate()
        appController = this
        appPreference = AppPreference(this)
    }

}

