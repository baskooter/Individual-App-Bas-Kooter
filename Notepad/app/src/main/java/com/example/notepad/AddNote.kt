package com.androidcourse.notepad

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.notepad.EditViewModel
import com.example.notepad.R
import kotlinx.android.synthetic.main.activity_add_note.*
import kotlinx.android.synthetic.main.content_add_note.*
import java.util.*

class AddNote : AppCompatActivity() {

    private lateinit var editViewModel: EditViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        setSupportActionBar(toolbar)
        supportActionBar?.title = "Edit Notepad"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initViews()
        initViewModel()
    }

    private fun initViews() {
        fab.setOnClickListener {

            editViewModel.note.value?.apply {
                title = inputTitle.text.toString()
                lastUpdated = Date()
                text = inputNote.text.toString()
            }

            editViewModel.updateNote()
        }
    }

    private fun initViewModel() {
        editViewModel = ViewModelProvider(this).get(EditViewModel::class.java)
        editViewModel.note.value = intent.extras?.getParcelable(EXTRA_NOTE)!!

        editViewModel.note.observe(this, Observer { note ->
            if (note != null) {
                inputTitle.setText(note.title)
                inputNote.setText(note.text)
            }
        })

        editViewModel.error.observe(this, Observer { message ->
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        })

        editViewModel.success.observe(this, Observer { success ->
            if (success) finish()
        })
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> { // Used to identify when the user has clicked the back button
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        const val EXTRA_NOTE = "EXTRA_NOTE"
    }

}