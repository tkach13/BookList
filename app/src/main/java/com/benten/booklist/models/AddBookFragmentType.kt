package com.benten.booklist.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class AddBookFragmentType : Parcelable {
    ADD_MODE,
    UPDATE
}