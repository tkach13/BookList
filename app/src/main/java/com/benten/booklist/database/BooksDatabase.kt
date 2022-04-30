package com.benten.booklist.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.benten.booklist.daos.BooksDao
import com.benten.booklist.entities.BookModel

const val DATABASE_VERSION = 1

@Database(entities = [BookModel::class], version = DATABASE_VERSION, exportSchema = false)
abstract class BooksDatabase : RoomDatabase() {
    abstract fun bookDao(): BooksDao

    companion object {
        @Volatile
        private var INSTANCE: BooksDatabase? = null
        fun getDatabase(context: Context): BooksDatabase {
            var tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val newInstance =
                    Room.databaseBuilder(context, BooksDatabase::class.java, "books_database")
                        .allowMainThreadQueries()
                        .build()
                INSTANCE = newInstance
                return newInstance
            }
        }
    }
}