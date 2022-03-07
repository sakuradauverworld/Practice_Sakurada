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

        //共有プリファレンスから英単語と日本語を持ってくる。
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val prefEnglishWordList = pref.getString("englishWordList", "")
        val prefJapaneseWordList = pref.getString("japaneseWordList", "")

        //空のリストを生成。
        var englishWordList = emptyArray<String>()
        var japaneseWordList = emptyArray<String>()

        //共有プリファレンスから取ってきたデータをJson形式に変換してから上記のリストに入れていく。
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

        //最初に遷移した時の英単語をランダムに決めて表示する。
        var indexNumber = Random().nextInt(englishWordList.count())
        binding.englishTextView.text = englishWordList[indexNumber]

        binding.nextButton.setOnClickListener {
            indexNumber = Random().nextInt(englishWordList.count())

            binding.englishTextView.text = englishWordList[indexNumber]
            binding.japaneseTextView.text = null
        }

        binding.japaneseButton.setOnClickListener {
            binding.japaneseTextView.text = japaneseWordList[indexNumber]
        }

    }
}