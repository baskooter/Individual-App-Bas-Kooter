package com.example.workoutapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_benchpress_page.*

class BenchpressPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_benchpress_page)

        backExercise.setOnClickListener(){
            val intent = Intent(this, ChestExercises::class.java)
            startActivity(intent)
        }

    }
}
