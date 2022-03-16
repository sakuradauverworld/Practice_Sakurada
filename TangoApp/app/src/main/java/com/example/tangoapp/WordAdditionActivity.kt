package com.example.tangoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AlertDialog
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import com.example.tangoapp.databinding.ActivityWordAdditionBinding
import org.json.JSONArray
import com.google.gson.Gson
import com.google.gson.JsonArray
import org.json.JSONObject
import java.util.ArrayList


class WordAdditionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWordAdditionBinding
    private var englishWord = ""
    private var japaneseWord = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWordAdditionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.englishInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                englishWord = binding.englishInput.text.toString()
                binding.saveButton.isEnabled = !(englishWord == "" || japaneseWord == "")
            }
        })

        binding.japaneseInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                japaneseWord = binding.japaneseInput.text.toString()
                binding.saveButton.isEnabled = !(englishWord == "" || japaneseWord == "")
            }
        })

        binding.saveButton.setOnClickListener {
            saveData(englishWord, japaneseWord)
        }

        binding.mainBackButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)

            startActivity(intent)
        }
    }

    private fun saveData(englishWord: String, japaneseWord: String) {
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val prefWordList = pref.getString("wordList", "")
        val word = Word(englishWord, japaneseWord )
        var wordList = mutableListOf<Word>()

        //共有プリファレンスから取得した単語のリストをJson形式に変換
        if (prefWordList != "") {
            val jsonArray = JSONArray(prefWordList)
            for (i in 0 until jsonArray.length()) {
                var jsonWord = jsonArray.getJSONObject(i)
                var word = Word.makeWordFromJson(jsonWord)
                wordList.add(word)
            }
        }

        wordList.add(word)

        val gson = Gson()
        val jsonArray = gson.toJson(wordList)

        pref.edit {
            putString("wordList", jsonArray.toString())
        }

        AlertDialog.Builder(this) // FragmentではActivityを取得して生成
            .setMessage("保存しました。")
            .setPositiveButton("OK", { dialog, which -> })
            .show()

        binding.englishInput.text = null
        binding.japaneseInput.text = null
    }
}