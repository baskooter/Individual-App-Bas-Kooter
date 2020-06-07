package com.example.gamebacklog2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_add_game.*
import kotlinx.android.synthetic.main.content_add_game.*

const val EXTRA_GAME = "EXTRA_REMINDER"

class AddGameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_game)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        fab.setOnClickListener { onAddgame() }
    }

    private fun onAddgame() {
        //verzamelen van textinputlayout gegevens
        val gameTitle = etTitle.text.toString()
        val gamePlatform = etPlatform.text.toString()
        val gameDay = etDay.text.toString()
        val gameMonth = etMonth.text.toString()
        val gameYear = etYear.text.toString()

        if(gameTitle.isNotBlank() and gamePlatform.isNotBlank() and gameDay.isNotBlank() and gameMonth.isNotBlank() and gameYear.isNotBlank()) {
            //maken van model
            val game = Game(gameTitle, gamePlatform, gameDay, gameMonth, gameYear)

            //terugsturen naar MainActivity
            val resultIntent = Intent()
            resultIntent.putExtra(EXTRA_GAME, game)
            setResult(Activity.RESULT_OK, resultIntent)

            finish()
        } else {
            Toast.makeText(this, "Vul alle velden in", Toast.LENGTH_SHORT).show();
        }

    }

}

