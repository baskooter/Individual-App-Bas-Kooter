package com.example.workoutapp

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

public class StatRepository(context: Context) {

    private var statDao: StatDao

    init {
        val statRoomDatabase = StatRoomDatabase.getDatabase(context)
        statDao = statRoomDatabase!!.statDao()
    }

    fun getAllStats() : LiveData<List<Stats>> {
        return statDao?.getAllStats() ?: MutableLiveData(emptyList())
    }


    suspend fun insertStat(stat: Stats) {
        statDao.insertStat(stat)
    }

    suspend fun deleteStat(stat: Stats) {
        statDao.deleteStat(stat)
    }

    suspend fun updateStat(stat: Stats) {
        statDao.updatestat(stat)
    }

    suspend fun deleteAllStats() = statDao.deleteAllStats()

}
