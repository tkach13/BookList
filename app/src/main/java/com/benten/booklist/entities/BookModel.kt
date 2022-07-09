package com.benten.booklist.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "books")
data class BookModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val bookName: String,
    @ColumnInfo(name = "poster")
    val posterUrl: String,
    val rating: Int
) : Parcelable
