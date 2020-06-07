package com.example.workoutapp

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface StatDao {

    @Query("SELECT * FROM statTable")
    fun getAllStats(): LiveData<List<Stats>>

    @Insert
    suspend fun insertStat(stat: Stats)

    @Delete
    suspend fun deleteStat(stat: Stats)

    @Update
    suspend fun updatestat(stat: Stats)

    @Query("DELETE FROM statTable")
    suspend fun deleteAllStats()

}
