package com.benten.booklist.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.benten.booklist.entities.BookModel

@Dao
interface BooksDao {
    @Query("SELECT * FROM books")
    fun getBooks(): List<BookModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE )
    fun insertBook(bookModel: BookModel)
}