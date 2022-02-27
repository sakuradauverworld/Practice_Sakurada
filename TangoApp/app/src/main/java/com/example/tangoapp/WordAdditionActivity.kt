package com.example.tangoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.example.tangoapp.databinding.ActivityWordAdditionBinding


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



        binding.mainBackButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)

            startActivity(intent)
        }
    }
}