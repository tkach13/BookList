package com.benten.app

import android.app.Application

class App : Application() {
    private lateinit var instance: App


    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}