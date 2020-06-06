package com.example.gamebacklog2

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Game::class], version = 1, exportSchema = false)
abstract class GameRoomDatabase : RoomDatabase() {
    abstract fun gameDao(): GameDao

    companion object {
        private const val DB_NAME = "gameDB"

        @Volatile
        private var gameRoomDatabase : GameRoomDatabase? = null

        fun getGameRoomDatabase(context: Context) : GameRoomDatabase? {
            if(gameRoomDatabase != null) return gameRoomDatabase

            synchronized(GameRoomDatabase::class.java) {
                if(gameRoomDatabase == null) {
                    gameRoomDatabase = Room.databaseBuilder(
                        context.applicationContext,
                        GameRoomDatabase::class.java, DB_NAME
                    )
                        .build()

                }
            }

            return gameRoomDatabase
        }
    }
}