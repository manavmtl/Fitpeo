package com.example.fitpeo

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class FitPeoApp:Application() {

    override fun onCreate() {
        super.onCreate()

    }

    override fun onLowMemory() {
        super.onLowMemory()
        System.gc()
    }
}