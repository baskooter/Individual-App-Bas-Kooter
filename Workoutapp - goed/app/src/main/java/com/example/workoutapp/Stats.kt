package com.example.workoutapp

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity(tableName = "statTable")
data class Stats(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null,

    @ColumnInfo(name = "datum")
    var datum: Long,

    @ColumnInfo(name = "gewicht")
    var gewicht: String,

    @ColumnInfo(name = "reps")
    var reps: String

) : Parcelable
