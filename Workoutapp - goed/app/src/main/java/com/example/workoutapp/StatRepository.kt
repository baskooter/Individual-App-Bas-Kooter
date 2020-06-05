package com.example.workoutapp

import android.content.Context

public class StatRepository(context: Context) {

    private var statDao: StatDao

    init {
        val statRoomDatabase = StatRoomDatabase.getDatabase(context)
        statDao = statRoomDatabase!!.statDao()
    }

    suspend fun getAllStats(): List<Stats> {
        return statDao.getAllStats()
    }

    suspend fun insertStat(stat: Stats) {
        statDao.insertStat(stat)
    }

    suspend fun deleteStgat(stat: Stats) {
        statDao.deleteStat(stat)
    }

    suspend fun updateStat(stat: Stats) {
        statDao.updatestat(stat)
    }

    suspend fun deleteAllStats() = statDao.deleteAllStats()

}
