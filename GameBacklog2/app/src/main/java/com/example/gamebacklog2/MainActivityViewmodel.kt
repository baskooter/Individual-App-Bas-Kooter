package com.example.gamebacklog2

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val gameRepository: GameRepository = GameRepository(application.applicationContext)

    val games: LiveData<List<Game>> = gameRepository.getAllGames()

    /**
     * Count example to illistrate LiveData besides the RecyclerView
     * Note: use of encapsulation
     */

    private val _count: MutableLiveData<Int> = MutableLiveData(0)

    val count: LiveData<Int>
        get() = _count

    fun increment() {
        _count.value = _count.value?.plus(1)
    }

    fun deleteAllGames() {
        ioScope.launch {
            gameRepository.deleteAllGames()
        }
    }

    private val ioScope = CoroutineScope(Dispatchers.IO)

    fun insertGame(game: Game) {
        ioScope.launch {
            gameRepository.insertGame(game)
        }
    }

    fun deleteGame(game: Game) {
        ioScope.launch {
            gameRepository.deleteGame(game)
        }
    }
}