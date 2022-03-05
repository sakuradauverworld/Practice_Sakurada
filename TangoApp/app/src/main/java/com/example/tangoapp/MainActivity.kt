package com.example.tangoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.preference.PreferenceManager
import com.example.tangoapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pref = PreferenceManager.getDefaultSharedPreferences(this)

        binding.learningStartButton.setOnClickListener {
            val intent = Intent(this, LearningActivity::class.java)

            startActivity(intent)
        }

        binding.wordAdditionButton.setOnClickListener {
            val intent = Intent(this, WordAdditionActivity::class.java)

            startActivity(intent)
        }
    }
}