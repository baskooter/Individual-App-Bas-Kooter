package com.example.workoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        arrowBut.setOnClickListener{
            arrowClicked()
        }
        chestBut.setOnClickListener{
            chestClicked()
        }
        menuBut.setOnClickListener{
            menuClicked()
        }
        voortgangBut.setOnClickListener() {
            voortgangClicked()
        }

    }

    fun arrowClicked() {
        if (chestBut.visibility == View.VISIBLE) {
            chestBut.visibility = View.GONE
            backgroundMan.setImageResource(R.drawable.manachter)
        }
        else if (chestBut.visibility == GONE) {
            chestBut.visibility = View.VISIBLE
            backgroundMan.setImageResource(R.drawable.backgroundmain)
        }


    }

    fun chestClicked(){

    }

    fun menuClicked(){
        if (voortgangBut.visibility == View.VISIBLE) {
            voortgangBut.visibility = View.GONE
        }
        else if (voortgangBut.visibility == View.GONE) {
            voortgangBut.visibility = View.VISIBLE
        }
    }

    fun voortgangClicked(){

    }
}



