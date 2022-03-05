package com.example.tangoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.preference.PreferenceManager
import com.example.tangoapp.databinding.ActivityLearningBinding
import org.json.JSONArray
import java.util.*

class LearningActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLearningBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLearningBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val prefEnglishWordList = pref.getString("englishWordList", "")
        val prefJapaneseWordList = pref.getString("japaneseWordList", "")
        var englishWordList = emptyArray<String>()
        var japaneseWordList = emptyArray<String>()

        if (prefEnglishWordList != "") {
            val json = JSONArray(prefEnglishWordList)
            for (i in 0 until json.length()) {
                englishWordList += json.getString(i)
            }
        }

        if (prefJapaneseWordList != "") {
            val json = JSONArray(prefJapaneseWordList)
            for (i in 0 until json.length()) {
                japaneseWordList += json.getString(i)
            }
        }

        val num = Random().nextInt(englishWordList.count())
        var indexNumber = num

        binding.englishTextView.text = englishWordList[num]

        binding.nextButton.setOnClickListener {
            val num = Random().nextInt(englishWordList.count())
            indexNumber = num
            binding.englishTextView.text = englishWordList[num]
            binding.japaneseTextView.text = null
        }

        binding.japaneseButton.setOnClickListener {
            binding.japaneseTextView.text = japaneseWordList[indexNumber]
        }

    }
}