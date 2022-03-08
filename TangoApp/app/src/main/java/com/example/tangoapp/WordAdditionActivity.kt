package com.example.tangoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import com.example.tangoapp.databinding.ActivityWordAdditionBinding
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
        val prefEnglishWordList = pref.getString("englishWordList", "")
        val prefJapaneseWordList = pref.getString("japaneseWordList", "")
        var jsonEnglishWordList = JSONArray(emptyArray<String>())
        var jsonJapaneseWordList = JSONArray(emptyArray<String>())

        //共有プリファレンスから取得した単語のリストをJson形式に変換
        if (prefEnglishWordList != "") {
            jsonEnglishWordList = JSONArray(prefEnglishWordList)
            jsonJapaneseWordList = JSONArray(prefJapaneseWordList)
        }

        //単語の追加
        jsonEnglishWordList.put(englishWord)
        jsonJapaneseWordList.put(japaneseWord)

        pref.edit {
            putString("englishWordList", jsonEnglishWordList.toString())
            putString("japaneseWordList", jsonJapaneseWordList.toString())
        }

        val alert = SaveConfirmationFragment()
        alert.show(supportFragmentManager, "")

        binding.englishInput.text = null
        binding.japaneseInput.text = null
    }
}