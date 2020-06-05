package com.example.workoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_chest_exercises.*
import kotlinx.android.synthetic.main.activity_stat_page.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class StatPage : AppCompatActivity() {

    private val statList = arrayListOf<Stats>()
    private val statAdapter = StatAdapter(statList)
    private lateinit var statRepository: StatRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stat_page)

        statAdapter.notifyDataSetChanged()
        statRepository = StatRepository(this)


        rvStats.layoutManager = LinearLayoutManager(this@StatPage, RecyclerView.VERTICAL, false)
        rvStats.adapter = statAdapter
        rvStats.addItemDecoration(DividerItemDecoration(this@StatPage, DividerItemDecoration.VERTICAL))

        getStatsFromDatabase()

        editBut.setOnClickListener(){
            addBut.visibility = View.VISIBLE
            repsInput.visibility = View.VISIBLE
            gewichtInput.visibility = View.VISIBLE
            repsTxt.visibility = View.VISIBLE
            gewichtTxt.visibility = View.VISIBLE
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

            CoroutineScope(Dispatchers.Main).launch {
                withContext(Dispatchers.IO) {
                    statRepository.insertStat(stat)
                }
            }


        }
    }

    private fun getStatsFromDatabase() {
        CoroutineScope(Dispatchers.Main).launch {
            val stats = withContext(Dispatchers.IO) {
                statRepository.getAllStats()
            }
            this@StatPage.statList.clear()
            this@StatPage.statList.addAll(stats)
            statAdapter.notifyDataSetChanged()
        }
    }
}

