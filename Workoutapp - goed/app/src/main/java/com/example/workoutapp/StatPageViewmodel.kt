package com.example.workoutapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StatPageViewmodel(application: Application) : AndroidViewModel(application) {

    private val ioScope = CoroutineScope(Dispatchers.IO)
    private val statRepository = StatRepository(application.applicationContext)

    val stats: LiveData<List<Stats>> = statRepository.getAllStats()

    fun insertStat(stat: Stats) {
        ioScope.launch {
            statRepository.insertStat(stat)
        }
    }

    fun deleteStat(stat: Stats) {
        ioScope.launch {
            statRepository.deleteStat(stat)
        }
    }

    fun deleteAllStats() {
        ioScope.launch {
            statRepository.deleteAllStats()
        }
    }

}
