package com.example.workoutapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Stats::class], version = 1, exportSchema = false)
abstract class StatRoomDatabase : RoomDatabase() {

    abstract fun statDao(): StatDao

    companion object {
        private const val DATABASE_NAME = "STAT_DATABASE"

        @Volatile
        private var RoomDatabaseInstance: StatRoomDatabase? = null

        fun getDatabase(context: Context): StatRoomDatabase? {
            if (RoomDatabaseInstance == null) {
                synchronized(StatRoomDatabase::class.java) {
                    if (RoomDatabaseInstance == null) {
                        RoomDatabaseInstance = Room.databaseBuilder(
                            context.applicationContext,
                            StatRoomDatabase::class.java, DATABASE_NAME
                        )

                            .build()
                    }
                }
            }
            return RoomDatabaseInstance
        }
    }

}
