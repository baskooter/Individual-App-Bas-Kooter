package com.example.workoutapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_chest_exercises.*
import kotlinx.android.synthetic.main.activity_stat_page.*
import kotlinx.android.synthetic.main.stat_place.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class StatPage : AppCompatActivity() {

    private val stats = arrayListOf<Stats>()
    private val statAdapter = StatAdapter(stats)
    private val viewModel: StatPageViewmodel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stat_page)

        statAdapter.notifyDataSetChanged()




        rvStats.layoutManager = LinearLayoutManager(this@StatPage, RecyclerView.VERTICAL, false)
        rvStats.adapter = statAdapter
        rvStats.addItemDecoration(DividerItemDecoration(this@StatPage, DividerItemDecoration.VERTICAL))
        createItemTouchHelper().attachToRecyclerView(rvStats)

        observeViewModel()

        editBut.setOnClickListener(){
            addBut.visibility = View.VISIBLE
            repsInput.visibility = View.VISIBLE
            gewichtInput.visibility = View.VISIBLE
            repsTxt.visibility = View.VISIBLE
            gewichtTxt.visibility = View.VISIBLE
        }

        backStat.setOnClickListener(){
            val intent = Intent(this, MainActivity::class.java)

            startActivity(intent)
        }

        delBut.setOnClickListener(){

            CoroutineScope(Dispatchers.Main).launch {
                withContext(Dispatchers.IO) {
                    viewModel.deleteAllStats()
                }
                observeViewModel()
            }
        }

        addBut.setOnClickListener(){
            var reps = repsInput.text.toString()
            var gewicht = gewichtInput.text.toString()
            var dateTime : Long = System.currentTimeMillis()

            val c = Calendar.getInstance()

            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)
            val date = year.toLong() + month.toLong() + day.toLong()

            val stat = Stats(
                datum = date,
                gewicht = gewicht,
                reps = reps
            )

            addBut.visibility = View.GONE
            repsInput.visibility = View.GONE
            gewichtInput.visibility = View.GONE
            repsTxt.visibility = View.GONE
            gewichtTxt.visibility = View.GONE



            CoroutineScope(Dispatchers.Main).launch {
                withContext(Dispatchers.IO) {
                    viewModel.insertStat(stat)
                }
                observeViewModel()
            }


        }


    }

    private fun createItemTouchHelper(): ItemTouchHelper {

        // Callback which is used to create the ItemTouch helper. Only enables left swipe.
        // Use ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) to also enable right swipe.
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            // Enables or Disables the ability to move items up and down.
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition

                val statToDelete = stats[position]

                CoroutineScope(Dispatchers.Main).launch {
                    withContext(Dispatchers.IO) {
                        viewModel.deleteStat(statToDelete)
                    }
                    observeViewModel()
                }
            }
        }
        return ItemTouchHelper(callback)
    }

    private fun observeViewModel() {
        viewModel.stats.observe(this, androidx.lifecycle.Observer { stats ->
            this@StatPage.stats.clear()
            this@StatPage.stats.addAll(stats)
            statAdapter.notifyDataSetChanged()
        })

    }


}

