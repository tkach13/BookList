package com.benten.booklist.entities


data class BookModel(
    val id: Int,
    val bookName: String,
    val posterUrl: String,
    val rating: Int
)
