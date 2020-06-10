package com.example.gamebacklog2

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface GameDao {
    @Query("SELECT * FROM gameTable ORDER BY year ASC")
    fun getAllGames() : LiveData<List<Game>>

    @Insert
    suspend fun insertGame(game: Game)

    @Delete
    suspend fun deleteGame(game: Game)

    @Query("DELETE FROM gameTable")
    suspend fun deleteAllGames()

    @Update
    suspend fun updateGame(game: Game)
}