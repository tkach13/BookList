package com.benten.booklist.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.benten.booklist.daos.BooksDao
import com.benten.booklist.entities.BookModel

const val DATABASE_VERSION = 2

@Database(
    entities = [BookModel::class],
    version = DATABASE_VERSION
)
abstract class BooksDatabase : RoomDatabase() {

    abstract fun booksDao(): BooksDao

    companion object {
        const val DATABASE_NAME = "books_database"

        fun buildDatabase(context: Context): BooksDatabase {
            return Room.databaseBuilder(context, BooksDatabase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()
        }
    }
}