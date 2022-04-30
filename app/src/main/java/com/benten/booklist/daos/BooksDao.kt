package com.benten.booklist.daos

import androidx.room.*
import com.benten.booklist.entities.BookModel

@Dao
interface BooksDao {
    @Query("SELECT * FROM books_read_list ")
    fun getAll(): List<BookModel>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertBook(bookModel: BookModel)

    @Query("DELETE  FROM books_read_list")
    fun deleteAll()

    @Delete
    fun deleteBook(bookModel: BookModel)

    @Update
    fun updateBookModel(bookModel: BookModel)
}