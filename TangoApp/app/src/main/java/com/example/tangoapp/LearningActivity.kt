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

        //共有プリファレンスから単語を持ってくる。
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val prefWordList = pref.getString("wordList", "")

        //空のリストを生成。
        var wordList = mutableListOf<Word>()

        //共有プリファレンスから取ってきたデータをJson形式に変換してから上記のリストに入れていく。
        val jsonArray = JSONArray(prefWordList)

        for (i in 0 until jsonArray.length()) {
            var jsonWord = jsonArray.getJSONObject(i)
            var word = Word(jsonWord.getString("englishWord"),jsonWord.getString("japaneseWord"))
            wordList.add(word)
        }

        //最初に遷移した時に単語をランダムに決めて表示する。
        var indexNumber = Random().nextInt(wordList.count())
        var word = wordList[indexNumber]

        binding.englishTextView.text = word.englishWord

        binding.nextButton.setOnClickListener {
            indexNumber = Random().nextInt(wordList.count())
            word = wordList[indexNumber]

            binding.englishTextView.text = word.englishWord
            binding.japaneseTextView.text = null
        }

        binding.japaneseButton.setOnClickListener {
            binding.japaneseTextView.text = word.japaneseWord
        }

    }
}