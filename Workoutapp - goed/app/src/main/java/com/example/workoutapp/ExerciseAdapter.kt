package com.example.placesofinterest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutapp.Exercise
import com.example.workoutapp.R
import kotlinx.android.synthetic.main.item_exercise.view.*



class ExerciseAdapter(private val exercises: List<Exercise>, val clickListener: (Exercise) -> Unit) : RecyclerView.Adapter<ExerciseAdapter.ViewHolder>()  {

    inner class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(exercise: Exercise){
            itemView.exerciseName.text = exercise.name
            itemView.exerciseType.text = exercise.types
            itemView.exercisePic.setImageResource(exercise.imageResId)
            itemView.setOnClickListener { clickListener(exercise)}
        }

    }
    override fun getItemCount(): Int {
        return exercises.size
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_exercise, parent, false)
        )
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(exercises[position])
    }
}

