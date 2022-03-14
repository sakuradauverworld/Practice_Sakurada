package com.example.tangoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import com.example.tangoapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.learningStartButton.setOnClickListener {
            val intent = Intent(this, LearningActivity::class.java)

            val pref = PreferenceManager.getDefaultSharedPreferences(this)
            val prefEnglishWordList = pref.getString("englishWordList", "")

            //英単語が共有プリファレンスに1つも登録されていなかったらアラートダイアログを表示して登録していたら学習画面に遷移する。
            if (prefEnglishWordList == "") {
                AlertDialog.Builder(this) // FragmentではActivityを取得して生成
                    .setMessage("登録された単語がありません。\n" + "登録してください。")
                    .setPositiveButton("OK", { dialog, which -> })
                    .show()
            } else {
                startActivity(intent)
            }
        }

        binding.wordAdditionButton.setOnClickListener {
            val intent = Intent(this, WordAdditionActivity::class.java)

            startActivity(intent)
        }
    }
}