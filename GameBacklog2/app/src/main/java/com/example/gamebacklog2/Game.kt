package com.example.gamebacklog2

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "gameTable")
data class Game(
    var title: String,
    var console: String,
    var day: String,
    var month: String,
    var year: String,

    @PrimaryKey var id : Long? = null
) : Parcelable