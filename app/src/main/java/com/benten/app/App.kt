package com.benten.app

import android.app.Application
import com.benten.booklist.database.BooksDatabase

class App : Application() {
    private lateinit var instance: App

    val database by lazy {
        BooksDatabase.buildDatabase(instance)
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}