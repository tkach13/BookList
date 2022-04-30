package com.benten.booklist.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books_read_list")
data class BookModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val bookName: String,
    val posterUrl: String,
    val rating: Int
)
