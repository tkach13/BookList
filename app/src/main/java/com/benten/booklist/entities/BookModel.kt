package com.benten.booklist.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

data class BookModel(
    val id: Int,
    val bookName: String,
    val posterUrl: String,
    val rating: Int
)
