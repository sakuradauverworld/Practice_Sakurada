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

        var englishWordList: MutableList<String> = mutableListOf()
        var japaneseWordList: MutableList<String> = mutableListOf()

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
            saveData(englishWord, japaneseWord, englishWordList, japaneseWordList)
        }

        binding.mainBackButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)

            startActivity(intent)
        }
    }

    private fun saveData(englishWord: String, japaneseWord: String, englishWordList: MutableList<String>,japaneseWordList: MutableList<String> ) {
        val pref = PreferenceManager.getDefaultSharedPreferences(this)

        val prefEnglishWordList = pref.getString("englishWordList", "")
        var englishWordList = emptyArray<String>()
        val prefJapaneseWordList = pref.getString("japaneseWordList", "")
        var japaneseWordList = emptyArray<String>()


        if (prefEnglishWordList != "") {
            val json = JSONArray(prefEnglishWordList)
            for (i in 0 until json.length()) {
                englishWordList += json.getString(i)
            }
        }
        val jsonEnglishWordList = JSONArray(englishWordList)
        jsonEnglishWordList.put(englishWord)

        if (prefJapaneseWordList != "") {
            val json = JSONArray(prefJapaneseWordList)
            for (i in 0 until json.length()) {
                japaneseWordList += json.getString(i)
            }
        }
        val jsonJapaneseWordList = JSONArray(japaneseWordList)
        jsonJapaneseWordList.put(japaneseWord)

        pref.edit {
            putString("englishWordList", jsonEnglishWordList.toString())
            putString("japaneseWordList", jsonJapaneseWordList.toString())
        }

        val a = SaveConfirmationFragment()
        a.show(supportFragmentManager, "missiles")

        binding.englishInput.text = null
        binding.japaneseInput.text = null
    }
}