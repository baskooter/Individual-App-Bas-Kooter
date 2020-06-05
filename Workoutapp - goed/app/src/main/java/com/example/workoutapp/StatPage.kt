package com.example.workoutapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
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

        backStat.setOnClickListener(){
            val intent = Intent(this, MainActivity::class.java)

            startActivity(intent)
        }

        delBut.setOnClickListener(){

            CoroutineScope(Dispatchers.Main).launch {
                withContext(Dispatchers.IO) {
                    statRepository.deleteAllStats()
                }
                getStatsFromDatabase()
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
                    statRepository.insertStat(stat)
                }
                getRemindersFromDatabase()
            }


        }

            updateBut.setOnClickListener(){
                val date : Long = System.currentTimeMillis()
                val gewicht = "test"
                val reps = "test"

                val stat = Stats(
                    datum = date,
                    gewicht = gewicht,
                    reps = reps
                )

                CoroutineScope(Dispatchers.Main).launch {
                    withContext(Dispatchers.IO) {
                        statRepository.updateStat(stat)
                    }
                    getRemindersFromDatabase()
                }

            }
    }

    private fun getRemindersFromDatabase() {
        CoroutineScope(Dispatchers.Main).launch {
            val reminders = withContext(Dispatchers.IO) {
                statRepository.getAllStats()
            }
            this@StatPage.statList.clear()
            this@StatPage.statList.addAll(reminders)
            statAdapter.notifyDataSetChanged()
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

