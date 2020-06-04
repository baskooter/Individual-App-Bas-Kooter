package com.example.workoutapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.placesofinterest.ExerciseAdapter
import kotlinx.android.synthetic.main.activity_chest_exercises.*
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T



class ChestExercises : AppCompatActivity() {
    private val exercises = arrayListOf<Exercise>()
    private val exerciseAdapter = ExerciseAdapter(exercises, {exercise : Exercise -> exerciseClicked(exercise)})


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chest_exercises)
        initViews()

        backList.setOnClickListener(){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    fun initViews () {
        for (i in Exercise.EXERCISE_NAMES.indices) {
            rvExercises.layoutManager = LinearLayoutManager(this@ChestExercises, RecyclerView.VERTICAL, false)
            rvExercises.adapter = exerciseAdapter
            exercises.add(
                Exercise(
                    Exercise.EXERCISE_NAMES[i],
                    Exercise.EXERCISE_TYPES[i],
                    Exercise.EXERCISE_PIC[i]
                )
            )


        }
        exerciseAdapter.notifyDataSetChanged()
    }

    fun exerciseClicked(exercise : Exercise){
        if (exercise.name == "Benchpress"){
            val intent = Intent(this, BenchpressPage::class.java)
            startActivity(intent)
        }

    }
}
