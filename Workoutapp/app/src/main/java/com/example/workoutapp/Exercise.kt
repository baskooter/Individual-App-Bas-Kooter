package com.example.workoutapp

import androidx.annotation.DrawableRes

data class Exercise(
        var name: String,
        var types: String,
        @DrawableRes var imageResId: Int
    ) {
        companion object {
            val EXERCISE_NAMES = arrayOf(
                "Benchpress",
                "Chestdip",
                "Incline dumbell press",
                "Upper cable fly"
            )
            val EXERCISE_TYPES= arrayOf(
                "Compound Mid chest",
                "Compound Lower chest",
                "Compound Upper chest",
                "Isolation Lower chest"
            )
            val EXERCISE_PIC= arrayOf(
                R.drawable.benchpress,
                R.drawable.chestdip,
                R.drawable.inclinepress,
                R.drawable.uppercable
            )
        }
    }

