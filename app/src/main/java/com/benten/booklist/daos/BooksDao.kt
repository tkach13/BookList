package com.benten.booklist.daos

import androidx.room.*
import com.benten.booklist.entities.BookModel

@Dao
interface BooksDao {
    @Query("SELECT * FROM books")
    suspend fun getBooks(): List<BookModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(bookModel: BookModel)

    @Delete
    suspend fun deleteBook(bookModel: BookModel)

    @Update
    suspend fun updateItem(bookModel: BookModel)
}