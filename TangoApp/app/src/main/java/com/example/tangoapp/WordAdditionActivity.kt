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
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.json.JSONArray

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
        var wordList = WordList(word)

        //共有プリファレンスから取得した単語のリストをJson形式に変換
        if (prefWordList != "") {
            val jsonArray = JSONArray(prefWordList)
            for (i in 0 until jsonArray.length()) {
                var jsonWord = jsonArray.getJSONObject(i)
                var word = Word(jsonWord.getString("englishWord"),jsonWord.getString("japaneseWord"))
                wordList.wordList.add(word)
            }
        }

        val jsonArray = Json.encodeToString(wordList.wordList)

        pref.edit {
            putString("wordList", jsonArray)
        }

        AlertDialog.Builder(this) // FragmentではActivityを取得して生成
            .setMessage("保存しました。")
            .setPositiveButton("OK", { dialog, which -> })
            .show()

        binding.englishInput.text = null
        binding.japaneseInput.text = null
    }
}