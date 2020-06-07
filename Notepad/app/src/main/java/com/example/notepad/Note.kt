package com.example.notepad

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*


@Parcelize
@Entity
data class Note(
    var title: String,
    var lastUpdated: Date,
    var text: String,
    @PrimaryKey var id : Long? = null
) : Parcelable

