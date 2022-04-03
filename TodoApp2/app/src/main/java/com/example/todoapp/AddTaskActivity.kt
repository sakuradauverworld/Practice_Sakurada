package com.example.todoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AlertDialog
import androidx.core.content.edit
import com.example.todoapp.databinding.ActivityAddTaskBinding
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.json.JSONArray
import androidx.preference.PreferenceManager


class AddTaskActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddTaskBinding

    private var title = ""
    private var fixedDate = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.save.isEnabled = false
        binding.title.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                title = binding.title.text.toString()
                binding.save.isEnabled = !(title == "" || fixedDate == "")
            }
        })

        binding.fixedDate.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                fixedDate = binding.fixedDate.text.toString()
                binding.save.isEnabled = !(title == "" || fixedDate == "")
            }
        })

        binding.save.setOnClickListener {
            save(title, fixedDate)
        }

        binding.back.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)

            startActivity(intent)
        }
    }

    private fun save(title: String, fixedDate: String) {
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val prefTaskList = pref.getString("taskList", "")
        val task = Task(title, fixedDate )
        var taskList = TaskList(task)

        //共有プリファレンスから取得した単語のリストをJson形式に変換
        if (prefTaskList != "") {
            val jsonArray = JSONArray(prefTaskList)
            for (i in 0 until jsonArray.length()) {
                var jsonWord = jsonArray.getJSONObject(i)
                var task = Task(jsonWord.getString("task"),jsonWord.getString("fixedDate"))
                taskList.taskList.add(task)
            }
        }

        val jsonArray = Json.encodeToString(taskList.taskList)

        pref.edit {
            putString("taskList", jsonArray)
        }

        AlertDialog.Builder(this) // FragmentではActivityを取得して生成
            .setMessage("保存しました。")
            .setPositiveButton("OK", { dialog, which -> })
            .show()

        binding.title.text = null
        binding.fixedDate.text = null
    }
}