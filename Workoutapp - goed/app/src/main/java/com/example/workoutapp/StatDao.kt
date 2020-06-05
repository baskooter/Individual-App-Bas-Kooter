package com.example.workoutapp

import androidx.room.*

@Dao
interface StatDao {

    @Query("SELECT * FROM statTable")
    suspend  fun getAllStats(): List<Stats>

    @Insert
    suspend fun insertStat(stat: Stats)

    @Delete
    suspend fun deleteStat(stat: Stats)

    @Update
    suspend fun updatestat(stat: Stats)

    @Query("DELETE FROM statTable")
    suspend fun deleteAllStats()

}
