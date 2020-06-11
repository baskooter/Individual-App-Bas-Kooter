package com.example.notepad2

import android.app.Application
import androidx.lifecycle.AndroidViewModel


class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val noteRepository = NoteRepository(application.applicationContext)

    val note = noteRepository.getNotepad()

}
