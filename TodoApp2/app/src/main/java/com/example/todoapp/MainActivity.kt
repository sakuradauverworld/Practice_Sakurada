package com.example.todoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.databinding.ActivityMainBinding
import org.json.JSONArray

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    lateinit var adapter: CustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //共有プリファレンスから単語を持ってくる。
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val prefTaskList = pref.getString("taskList", "")

        //空のリストを生成。
        var taskList = mutableListOf<Task>()

        if (prefTaskList != "") {
            val jsonArray = JSONArray(prefTaskList)

            for (i in 0 until jsonArray.length()) {
                var jsonWord = jsonArray.getJSONObject(i)
                var task = Task(jsonWord.getString("title"),jsonWord.getString("fixedDate"))
                taskList.add(task)
            }
        }

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)

        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = CustomAdapter(taskList)
        recyclerView.adapter = adapter

        binding.addTaskButton.setOnClickListener {
            val intent = Intent(this, AddTaskActivity::class.java)

            startActivity(intent)
        }
    }
}