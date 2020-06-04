package com.example.workoutapp

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "statTable")
data class Stats(
    @ColumnInfo(name = "stat")
    var statTableText: String,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null,

    @ColumnInfo(name = "datum")
    var datum: Date,

    @ColumnInfo(name = "gewicht")
    var gewicht: Int,

    @ColumnInfo(name = "reps")
    var reps: Int

) : Parcelable
